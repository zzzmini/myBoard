package com.my.board.service;

import com.my.board.dao.ArticleDao;
import com.my.board.dto.ArticleDto;
import com.my.board.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleDao dao;

    public List<ArticleDto> getAllArticle() {
        List<Article> articles = dao.findAllArticle();
        if (ObjectUtils.isEmpty(articles)) {
            return Collections.emptyList();
        } else {
            return articles.stream()
                    .map(x -> ArticleDto.fromArticle(x))
                    .toList();
        }
    }
}
