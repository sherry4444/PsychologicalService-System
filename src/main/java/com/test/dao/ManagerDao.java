package com.test.dao;


import com.test.domain.Manager;
import com.test.domain.Student;
import com.test.domain.Teacher;
import com.test.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface ManagerDao {

    List<Teacher> findmanagerAll(Map<String, Object> parameter);
    int countManager(@Param("title") String title);

    void ManagertoUser(UserInfo userInfo);
    Integer findmanagerid(UserInfo userInfo);
    void addmanager(Manager manager);

    void deletemanager(Manager manager);

    void deletemanagerinUser(Manager manager);

    void updatemanager(Manager manager);

    void updatemanagertoUser(UserInfo userInfo);

    void deleteUserinManager(Manager manager);

    void updateUserinManager(Manager manager);
 }
