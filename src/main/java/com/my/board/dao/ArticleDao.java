package com.my.board.dao;

import com.my.board.dto.ArticleDto;
import com.my.board.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Transactional
public class ArticleDao {
    @Autowired
    EntityManager em;

    public List<Article> findAllArticle() {
        String sql = "SELECT a FROM Article a " +
                "ORDER BY a.id DESC";
        List<Article> articles = em.createQuery(sql).getResultList();
        return articles;
    }
}
