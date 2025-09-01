package com.my.board.api.dao;

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
}
