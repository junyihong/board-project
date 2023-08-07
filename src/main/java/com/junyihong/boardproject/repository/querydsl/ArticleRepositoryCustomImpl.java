package com.junyihong.boardproject.repository.querydsl;

import com.junyihong.boardproject.domain.Article;
import com.junyihong.boardproject.domain.QArticle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

        //JPQLQuery 를 사용할때는 from 부터 시작해야함.
        return from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();
    }
}
