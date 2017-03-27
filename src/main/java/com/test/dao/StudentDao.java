package com.test.dao;


import com.test.domain.Notice;
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

    Integer finduserid(UserInfo userInfo);

    void addstudent(Student student);

    void deletestudent(Student student);

    void deletestudentinUser(Student student);

    void updatestudent(Student student);

    void updateStudenttoUser(UserInfo userInfo);

    void deleteUserinStudent(Student student);

    void updateUserinStudent(Student student);

 }
