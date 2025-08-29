package com.my.board.service;

import com.my.board.ArticleRepository;
import com.my.board.dao.ArticleDao;
import com.my.board.dto.ArticleDto;
import com.my.board.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleDao dao;
    private final ArticleRepository articleRepository;

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

    public Page<ArticleDto> getArticlePage(Pageable pageable) {
        Page<Article> articles = articleRepository.findAll(pageable);
        if (ObjectUtils.isEmpty(articles)) {
            return null;
        }
        return articles.map(x -> ArticleDto.fromArticle(x));
    }

    public ArticleDto getOneArticle(Long id) {
        Article article = dao.getOneArticle(id);
        if (ObjectUtils.isEmpty(article)) {
            return null;
        }
        return ArticleDto.fromArticle(article);
    }

    public void deleteArticle(Long id) {
        dao.deleteArticle(id);
    }
}
