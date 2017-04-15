package com.test.service;

import com.test.domain.Notice;
import com.test.domain.TestLink;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface TestLinkService {


    List<TestLink> findlinkAll(Map<String, Object> parameter);

    int countlink(String title);

    void addlink(TestLink testLink);

    void deletelink(TestLink testLink);

    void batchDeletelink(String[] ids);
}
