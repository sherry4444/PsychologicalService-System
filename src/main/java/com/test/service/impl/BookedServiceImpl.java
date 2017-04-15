package com.test.service.impl;


import com.test.dao.BookedDao;
import com.test.dao.TestLinkDao;
import com.test.domain.Booked;
import com.test.domain.Notice;
import com.test.domain.TestLink;
import com.test.service.BookedService;
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
public class BookedServiceImpl implements BookedService {

    @Autowired
    private BookedDao bookedDao;

    @Transactional
    public List<Booked> findBookedAll(Map<String,Object> parameter){ return bookedDao.findbookedAll(parameter);}

    @Transactional
    public int countBooked(String title){ return bookedDao.countBooked(title);}

    @Transactional
    public void addBooked(Booked booked){ bookedDao.addBooked(booked);}

    @Transactional
    public void deleteBooked(Booked booked){ bookedDao.deleteBooked(booked);};

    @Transactional
    public void changeBookedState(Booked booked){ bookedDao.changeBookedState(booked);}


    @Transactional
    public void feedbackBooked(Booked booked){ bookedDao.feedbackBooked(booked);}

}
