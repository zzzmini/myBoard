package com.my.board.api.controller;

import com.my.board.api.service.CommentService;
import com.my.board.dto.CommentDto;
import com.my.board.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
// 모든 메서드가 @ResponseBody를 자동으로 갖도록 만듦
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 1. 댓글 조회
    // "/api/comments/{commentId}"
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentDto> commentSearch(
            @PathVariable("commentId")Long commentId) {
        Map<String, Object> result = commentService.findComment(commentId);
        CommentDto dto = (CommentDto) result.get("dto");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }
}
