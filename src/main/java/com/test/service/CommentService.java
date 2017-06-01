package com.test.service;

import com.test.domain.Article;
import com.test.domain.Comment;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface CommentService {

    List<Comment> findCommentAll(Comment comment);

    int countComment(String title);

    void addComment(Comment comment);

    void deleteComment(Comment comment);

}
