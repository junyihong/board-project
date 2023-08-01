package com.junyihong.boardproject.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.junyihong.boardproject.domain.ArticleComment}
 */
public record ArticleCommentDto(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        Long id,
        String content
)  {
    public static ArticleCommentDto of(LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy, Long id, String content) {
        return new ArticleCommentDto(createdAt, createdBy, modifiedAt, modifiedBy, id, content);
    }
}