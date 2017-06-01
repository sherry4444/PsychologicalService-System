package com.test.domain;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;


/**
 * Created by Administrator on 2017/5/31.
 */

@Table(name="ps_article")
@Entity
public class Article {

    private Integer article_id;
    private String article_name;
    private String article_author;
    private String article_context;
    private String article_path;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp article_createTime;

    public Article() {
    }

    @GeneratedValue
    @Id
    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public String getArticle_context() {
        return article_context;
    }

    public void setArticle_context(String article_context) {
        this.article_context = article_context;
    }

    public String getArticle_path() {
        return article_path;
    }

    public void setArticle_path(String article_path) {
        this.article_path = article_path;
    }

    @Temporal(TemporalType.DATE)
    public Timestamp getArticle_createTime() {
        return article_createTime;
    }

    public void setArticle_createTime(Timestamp article_createTime) {
        this.article_createTime = article_createTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "article_id=" + article_id +
                ", article_name='" + article_name + '\'' +
                ", article_author='" + article_author + '\'' +
                ", article_context='" + article_context + '\'' +
                ", article_path='" + article_path + '\'' +
                ", article_createTime=" + article_createTime +
                '}';
    }
}
