package com.my.board.controller;

import com.my.board.api.service.CommentService;
import com.my.board.dto.ArticleDto;
import com.my.board.service.ArticleService;
import com.my.board.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final PaginationService paginationService;
    private final CommentService commentService;

    @GetMapping({"", "/"})
    public String showArticles(Model model,
                               @PageableDefault(
                                       page = 0,
                                       size = 5,
                                       sort = "id",
                                       direction = Sort.Direction.DESC
                               ) Pageable pageable) {
        // controller -> service -> dao(Data Access Object)
        // List<ArticleDto> articles = articleService.getAllArticle();
        Page<ArticleDto> articles = articleService.getArticlePage(pageable);

        // 페이징 정보를 확인
        // 1. 전체 페이지수
        int totalPage = articles.getTotalPages();
        System.out.println("TotalPage : " + totalPage);
        // 2. 현재의 페이지 번호
        int currentPage = articles.getNumber();
        System.out.println("CurrentPage : " + currentPage);
        // 3. paginatinService에서 페이지블럭을 얻어온다.
        List<Integer> barNumbers = paginationService
                .getPaginationBarNumber(currentPage, totalPage);

        System.out.println("===== " + barNumbers.toString());

        model.addAttribute("pageBars", barNumbers);
        model.addAttribute("articles", articles);
        return "articles/show_all";
    }

    @GetMapping("{id}")
    public String showOneArticle(@PathVariable("id") Long id
            , Model model) {
        // id로 게시글 검색 후
        // DTO로 변환해서 show.html 에 보냄
        // 여기는 댓글인 comment 도 리스트로 갖고 있다.
        ArticleDto dto = articleService.getOneArticle(id);
        model.addAttribute("dto", dto);
        return "/articles/show";
    }

    @GetMapping("{id}/delete")
    public String deleteArticle(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        articleService.deleteArticle(id);
        redirectAttributes.addFlashAttribute("msg",
                "정상적으로 삭제 되었습니다.");
        return "redirect:/articles";
    }

    // 신규 게시글 입력 창 보이기
    @GetMapping("new")
    public String inputForm(Model model) {
        model.addAttribute("dto", new ArticleDto());
        return "/articles/new";
    }

    // 신규 게시글 저장
    @PostMapping("create")
    public String createArticle(ArticleDto dto, RedirectAttributes redirectAttributes) {
        articleService.insertArticle(dto);
        redirectAttributes.addFlashAttribute("msg",
                "새로운 게시글이 등록되었습니다.");
        return "redirect:/articles";
    }

    // 업데이트 화면 보이기
    @GetMapping("{id}/update")
    public String viewUpdateArticle(@PathVariable("id") Long id,
                                    Model model) {
        model.addAttribute("dto",
                articleService.getOneArticle(id));
        return "/articles/update";
    }

    // 게시글 업데이트 처리
    @PostMapping("update")
    public String updateArticle(@ModelAttribute("dto") ArticleDto dto) {
        articleService.updateArticle(dto);
        return "redirect:/articles";
    }

    // 댓글 수정 폼 보이기
    @GetMapping("comments/view/{commentId}")
    public String commentUpdateFormView(
            @PathVariable("commentId") Long commentId,
            Model model) {
        // CommentService의 한 개 댓글 찾기 서비스로 가져오기
        Map<String, Object> comment = commentService.findComment(commentId);
        model.addAttribute("dto", comment.get("dto"));
        model.addAttribute("articleId", comment.get("articleId"));
        return "/articles/update_comment";
    }
}
