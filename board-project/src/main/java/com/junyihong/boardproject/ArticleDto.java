package com.junyihong.boardproject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.junyihong.boardproject.domain.Article}
 */
public record ArticleDto(String title, String content, String hashtag, LocalDateTime createdAt, String createdBy,
                         LocalDateTime modifiedAt, String modifiedBy) implements Serializable {
}