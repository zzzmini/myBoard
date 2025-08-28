package com.my.board.dto;

import com.my.board.entity.Article;
import com.my.board.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;
    private String title;
    private String content;
    // 댓글 리스트
    private List<CommentDto> comments = new ArrayList<>();

    // 엔티티 -> DTO
    public static ArticleDto fromArticle(Article article) {
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getComments()
                        .stream().map(x -> CommentDto.fromComment(x))
                        .toList()
        );
    }

    // DTO -> Article
    public static Article fromDto(ArticleDto dto) {
        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        return article;
    }
}
