package com.test.service;

import com.test.domain.Article;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface ArticleService {


    List<Article> findArticleAll(Map<String, Object> parameter);

    int countArticle(String title);

    void addArticle(Article article);

    void deleteArticle(Article article);

    void updateArticle(Article article);

    Article findArticle(Integer id);
}
