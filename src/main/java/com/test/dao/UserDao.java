package com.test.dao;


import com.test.domain.UserInfo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository
public interface UserDao {
    public void addUser(UserInfo userInfo);
}
