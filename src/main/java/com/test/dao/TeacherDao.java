package com.test.dao;


import com.test.domain.Student;
import com.test.domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface TeacherDao {

    List<Teacher> findteacherAll(Map<String, Object> parameter);
    int countTeacher(@Param("title") String title);
 }
