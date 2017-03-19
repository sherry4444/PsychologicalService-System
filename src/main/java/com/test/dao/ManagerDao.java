package com.test.dao;


import com.test.domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface ManagerDao {

    List<Teacher> findmanagerAll(Map<String, Object> parameter);
    int countManager(@Param("title") String title);
 }
