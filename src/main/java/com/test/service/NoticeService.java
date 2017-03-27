package com.test.service;

import com.test.domain.Notice;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface NoticeService {


    List<Notice> findnoticeAll(Map<String, Object> parameter);

    int countNotice(String title);

    void addnotice(Notice notice);

    void deletenotice(Notice notice);

    void updatenotice(Notice notice);

    void batchDeleteNotice(String[] ids);
}
