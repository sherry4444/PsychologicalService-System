package com.test.dao;


import com.test.domain.Student;
import com.test.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface StudentDao {

    List<Student> findstudentAll(Map<String, Object> parameter);
    int countStudent(@Param("title") String title);
    void StudentoUser(UserInfo userInfo);
    int finduserid(UserInfo userInfo);
    void addstudent(Student student);

 }
