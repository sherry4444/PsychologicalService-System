package com.test.service.impl;

import com.test.dao.UserDao;
import com.test.domain.UserInfo;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Transactional
    public UserInfo finduserByName(UserInfo userInfo){ return userDao.finduserByName(userInfo); }

    @Transactional
    public List<UserInfo> finduserAll(Map<String,Object> parameter){ return  userDao.finduserAll(parameter);}

    @Transactional
    public int countUser(String title){ return userDao.countUser(title);}

}
