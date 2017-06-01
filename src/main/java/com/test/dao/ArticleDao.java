package com.test.dao;


import com.test.domain.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface ArticleDao {

    List<Article> findArticleAll(Map<String, Object> parameter);

    int countArticle(@Param("title") String title);

    void addArticle(Article article);

    void deleteArticle(Article article);

    void updateArticle(Article article);

    void batchDeleteArticle(String[] ids);

    Article findArticle(Integer id);
}
