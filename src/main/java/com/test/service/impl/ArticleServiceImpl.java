package com.test.service.impl;


import com.test.dao.ArticleDao;
import com.test.domain.Article;

import com.test.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao ArticleDao;

    @Transactional
    public List<Article> findArticleAll(Map<String,Object> parameter){ return ArticleDao.findArticleAll(parameter);}

    @Transactional
    public int countArticle(String title){ return ArticleDao.countArticle(title);}

    @Transactional
    public void addArticle(Article article){
        ArticleDao.addArticle(article);}

    @Transactional
    public void deleteArticle(Article article){ ArticleDao.deleteArticle(article);}

    @Transactional
    public void updateArticle(Article article){ ArticleDao.updateArticle(article);}

    @Transactional
    public Article findArticle(Integer id){ return ArticleDao.findArticle(id);}

}
