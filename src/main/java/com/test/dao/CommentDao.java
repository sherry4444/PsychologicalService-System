package com.test.dao;

import com.test.domain.Article;
import com.test.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/1.
 */
@Repository
public interface CommentDao {

     List<Comment> findCommentAll(Comment comment);

    int countComment(String title);

    void addComment(Comment comment);

    void deleteComment(Comment comment);
}
