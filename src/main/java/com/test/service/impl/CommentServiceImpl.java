package com.test.service.impl;




import com.test.dao.CommentDao;
import com.test.domain.Comment;

import com.test.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by wgt on 2017/3/10.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Transactional
    public List<Comment> findCommentAll(Comment comment) {
        return commentDao.findCommentAll(comment);
    }

    @Transactional
    public int countComment(String title) {
        return commentDao.countComment(title);
    }

    @Transactional
    public void addComment(Comment comment) {
        commentDao.addComment(comment);
    }

    @Transactional
    public void deleteComment(Comment comment) {
        commentDao.deleteComment(comment);
    }

}
