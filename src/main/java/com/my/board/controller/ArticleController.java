package com.my.board.controller;

import com.my.board.dto.ArticleDto;
import com.my.board.service.ArticleService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping({"", "/"})
    public String showArticles(Model model) {
        // controller -> service -> dao(Data Access Object)
        List<ArticleDto> articles = articleService.getAllArticle();
        model.addAttribute("articles", articles);
        return "articles/show_all";
    }

}
