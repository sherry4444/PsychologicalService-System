package com.test.service.impl;


import com.test.dao.ManagerDao;
import com.test.dao.TeacherDao;
import com.test.domain.Teacher;
import com.test.service.ManagerService;
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
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Transactional
    public List<Teacher> findmanagerAll(Map<String,Object> parameter){ return managerDao.findmanagerAll(parameter);}

    @Transactional
    public int countManager(String title){ return managerDao.countManager(title);}

}
