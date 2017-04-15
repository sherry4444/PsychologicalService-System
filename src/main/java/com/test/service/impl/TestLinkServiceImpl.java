package com.test.service.impl;


import com.test.dao.TestLinkDao;
import com.test.domain.TestLink;
import com.test.service.TestLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
@Service
public class TestLinkServiceImpl implements TestLinkService {

    @Autowired
    private TestLinkDao testLinkDao;

    @Transactional
    public List<TestLink> findlinkAll(Map<String,Object> parameter){ return testLinkDao.findlinkAll(parameter);}

    @Transactional
    public int countlink(String title){ return testLinkDao.countlink(title);}

    @Transactional
    public void addlink(TestLink testLink){ testLinkDao.addlink(testLink);}

    @Transactional
    public void deletelink(TestLink testLink){ testLinkDao.deletelink(testLink);};

    @Transactional
    public void batchDeletelink(String[] ids){ testLinkDao.batchDeletelink(ids);}


}
