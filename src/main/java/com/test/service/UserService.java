package com.test.service;

import com.test.domain.UserInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface UserService {

    void addUserInfo(UserInfo userInfo);

    UserInfo finduserByName(UserInfo userInfo);

    List<UserInfo> finduserAll(Map<String,Object> parameter);

    int countUser(String title);

    void deleteUser(UserInfo userInfo);

    void updateUser(UserInfo userInfo);

    Integer finduserid(UserInfo userInfo);
}
