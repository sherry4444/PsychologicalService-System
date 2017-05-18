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
    public int countBooked(Map<String,Object> parameter){ return bookedDao.countBooked(parameter);}

    @Transactional
    public int findhadfinish(Booked booked){
        bookedDao.finishState(booked);
        return bookedDao.findhadfinish(booked);}

    @Transactional
    public void addBooked(Booked booked){ bookedDao.addBooked(booked);}

    @Transactional
    public void deleteBooked(Booked booked){ bookedDao.deleteBooked(booked);};

    @Transactional
    public void changeBookedState(Booked booked){ bookedDao.changeBookedState(booked);}


    @Transactional
    public void feedbackBooked(Booked booked){ bookedDao.feedbackBooked(booked);}

    @Transactional
    public void finishState(Booked booked){ bookedDao.finishState(booked);}

}
