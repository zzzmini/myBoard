package com.my.board.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping({"", "/"})
    public String showArticles(Model model,
                               @PageableDefault(
                                       page = 0,
                                       size = 5,
                                       sort = "id",
                                       direction = Sort.Direction.DESC
                               )Pageable pageable) {
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
}
