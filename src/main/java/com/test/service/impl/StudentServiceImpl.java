package com.test.service.impl;

import com.test.dao.StudentDao;
import com.test.domain.Student;
import com.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;


    public List<Student> findstudentAll(Map<String,Object> parameter){ return studentDao.findstudentAll(parameter);}


    public int countStudent(String title){ return studentDao.countStudent(title);}

    @Transactional
    public void addStudent(Student student){
        studentDao.StudentoUser(student.getUserInfo());
        student.setStu_userId(studentDao.finduserid(student.getUserInfo()));
        studentDao.addstudent(student);
    }

}
