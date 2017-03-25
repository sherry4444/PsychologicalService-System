package com.test.service;

import com.test.domain.Student;
import com.test.domain.Teacher;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface TeacherService {


    List<Teacher> findteacherAll(Map<String, Object> parameter);

    int countTeacher(String title);

    void addTeacher(Teacher teacher);
}
