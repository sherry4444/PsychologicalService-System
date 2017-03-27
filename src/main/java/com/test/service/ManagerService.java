package com.test.service;

import com.test.domain.Manager;
import com.test.domain.Teacher;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface ManagerService {


    List<Teacher> findmanagerAll(Map<String, Object> parameter);

    int countManager(String title);

    void addManager(Manager manager);

    void deletemanager(Manager manager);

    void updatemanager(Manager manager);
}
