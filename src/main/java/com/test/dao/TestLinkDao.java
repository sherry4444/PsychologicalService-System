package com.test.dao;


import com.test.domain.TestLink;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface TestLinkDao {

    List<TestLink> findlinkAll(Map<String, Object> parameter);

    int countlink(@Param("title") String title);

    void addlink(TestLink testLink);

    void deletelink(TestLink testLink);

    void batchDeletelink(String[] ids);
}
