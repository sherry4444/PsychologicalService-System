package com.test.domain;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/6/1.
 *
 * commentId
 * articleId
 * context
 * userId
 * userName
 * createTime
 *
 */
public class Comment {

    private Integer commentId;
    private Integer articleId;
    private String context;
    private Integer userId;
    private String userName;
    private Timestamp createTime;

    public Comment() {
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getuserId() {
        return userId;
    }

    public void setuserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", articleId=" + articleId +
                ", context='" + context + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
