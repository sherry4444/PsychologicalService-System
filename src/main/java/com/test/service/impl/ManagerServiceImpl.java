package com.test.service.impl;


import com.test.dao.ManagerDao;
import com.test.dao.TeacherDao;
import com.test.domain.Manager;
import com.test.domain.Teacher;
import com.test.domain.UserInfo;
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

    @Transactional
    public void addManager(Manager manager){
        managerDao.ManagertoUser(manager.getUserInfo());
        manager.setMg_userId(managerDao.findmanagerid(manager.getUserInfo()));
        managerDao.addmanager(manager);
    }

    @Transactional
    public void deletemanager(Manager manager){
        managerDao.deletemanagerinUser(manager);
        managerDao.deletemanager(manager);
    }

    @Transactional
    public void updatemanager(Manager manager){
        managerDao.updatemanager(manager);
        UserInfo userInfo = manager.getUserInfo();
        userInfo.setUserId(manager.getMg_userId());
        managerDao.updatemanagertoUser(userInfo);
    }

}
