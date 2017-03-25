package com.test.service.impl;


import com.test.dao.NoticeDao;
import com.test.domain.Notice;
import com.test.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Transactional
    public List<Notice> findnoticeAll(Map<String,Object> parameter){ return noticeDao.findnoticeAll(parameter);}

    @Transactional
    public int countNotice(String title){ return noticeDao.countNotice(title);}

    @Transactional
    public void addnotice(Notice notice){ noticeDao.addnotice(notice);}

}
