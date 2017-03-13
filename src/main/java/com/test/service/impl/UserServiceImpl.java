package com.test.service.impl;

import com.test.dao.UserDao;
import com.test.domain.UserInfo;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wgt on 2017/3/10.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Transactional
    public void addUserInfo(UserInfo userInfo){
        userDao.addUser(userInfo);
    }

}
