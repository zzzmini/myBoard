package com.my.board.api.service;

import com.my.board.api.dao.CommentDao;
import com.my.board.dto.CommentDto;
import com.my.board.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentDao dao;

    public Map<String, Object> findComment(Long commentId) {
        Comment comment = dao.findComment(commentId);
        Map<String, Object> map = new HashMap<>();
        if (ObjectUtils.isEmpty(comment)) {
            map.put("dto", null);
            map.put("articleId", null);
            return map;
        } else {
            map.put("dto", CommentDto.fromComment(comment));
            map.put("articleId", comment.getArticle().getId());
            return map;
        }
    }
}
