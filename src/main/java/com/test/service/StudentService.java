package com.test.service;

import com.test.domain.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface StudentService {


    List<Student> findstudentAll(Map<String, Object> parameter);

    int countStudent(String title);

    void addStudent(Student student);

    void deletestudent(Student student);

    void updatestudent(Student student);
}
