package com.kkp.pelatihanwebservice.internal.services.article;

import com.kkp.pelatihanwebservice.internal.models.Article;

public interface ArticleService {
    Article createArticle(Article article);

    Article updateArticle(Article article, Long id);

    Article deleteArticle(Article article, Long id);
}
