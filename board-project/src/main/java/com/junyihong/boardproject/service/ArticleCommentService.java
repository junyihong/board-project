package com.junyihong.boardproject.service;

import com.junyihong.boardproject.dto.ArticleCommentDto;
import com.junyihong.boardproject.repository.ArticleCommentRepository;
import com.junyihong.boardproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComment(Long articleId){
        return List.of();
    }
}
