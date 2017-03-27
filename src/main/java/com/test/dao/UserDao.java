package com.test.dao;


import com.test.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface UserDao {

    UserInfo finduserByName(UserInfo userInfo);
    List<UserInfo> finduserAll(Map<String,Object> parameter);
    int countUser(@Param("title") String title);
    Integer finduserid(UserInfo userInfo);

    void addUser(UserInfo userInfo);
    void deleteUser(UserInfo userInfo);
    void updateUser(UserInfo userInfo);
 }
