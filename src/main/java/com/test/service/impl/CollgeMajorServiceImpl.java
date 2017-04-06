package com.test.service.impl;


import com.test.dao.CollegeMajorDao;
import com.test.dao.NoticeDao;
import com.test.domain.College;
import com.test.domain.Major;
import com.test.domain.Notice;
import com.test.service.CollegeMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
@Service
public class CollgeMajorServiceImpl implements CollegeMajorService {

    @Autowired
    private CollegeMajorDao collegeMajorDao;

    @Transactional
    public List<College> findcollegeAll(String title){return collegeMajorDao.findcollegeAll(title);}

    @Transactional
    public void addcollege(College college){collegeMajorDao.addcollege(college);}

    @Transactional
    public void deletecollege(College college){collegeMajorDao.deletecollege(college);}

    @Transactional
    public void updatecollege(College college){collegeMajorDao.updatecollege(college);}

    @Transactional
    public List<Major> findmajorAll(String title){return collegeMajorDao.findmajorAll(title);}

    @Transactional
    public void addmajor(Major major){ collegeMajorDao.addmajor(major);}

    @Transactional
    public void deletemajor(Major major){ collegeMajorDao.deletemajor(major);}


    @Transactional
    public void updatemajor(Major major){collegeMajorDao.updatemajor(major);}

}
