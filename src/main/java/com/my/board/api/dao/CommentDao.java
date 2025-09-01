package com.my.board.api.dao;

import com.my.board.entity.Article;
import com.my.board.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class CommentDao {
    @Autowired
    EntityManager em;

    // 1. Comment 찾기
    public Comment findComment(Long commentId) {
        return em.find(Comment.class, commentId);
    }

    public void insertComment(Long articleId, Comment comment) {
        // 1. 해당 게시글을 찾는다.
        Article article = em.find(Article.class, articleId);
        // 2. comment 엔티티에 article을 할당
        comment.setArticle(article);
        // 3. comment를 게시글에 리스트로 추가한다.
        article.getComments().add(comment);
        em.persist(article);
    }
}
