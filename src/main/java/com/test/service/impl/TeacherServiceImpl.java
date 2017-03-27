package com.test.service.impl;


import com.test.dao.TeacherDao;
import com.test.domain.Student;
import com.test.domain.Teacher;
import com.test.domain.UserInfo;
import com.test.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Transactional
    public List<Teacher> findteacherAll(Map<String,Object> parameter){ return teacherDao.findteacherAll(parameter);}

    @Transactional
    public int countTeacher(String title){ return teacherDao.countTeacher(title);}


    @Transactional
    public void addTeacher(Teacher teacher){
        teacherDao.TeachertoUser(teacher.getUserInfo());
        teacher.setTc_userId(teacherDao.finduserid(teacher.getUserInfo()));
        teacherDao.addteacher(teacher);
    }

    @Transactional
    public void deleteteacher(Teacher teacher){
        teacherDao.deleteteacherinUser(teacher);
        teacherDao.deleteteacher(teacher);
    }

    @Transactional
    public void updateteacher(Teacher teacher){
        teacherDao.updateteacher(teacher);
        UserInfo userInfo = teacher.getUserInfo();
        userInfo.setUserId(teacher.getTc_userId());
        teacherDao.updateTeachertoUser(userInfo);
    }

}
