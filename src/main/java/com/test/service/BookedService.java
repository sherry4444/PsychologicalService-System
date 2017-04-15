package com.test.service;

import com.test.domain.Booked;
import com.test.domain.Notice;
import com.test.domain.TestLink;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface BookedService {


    List<Booked> findBookedAll(Map<String, Object> parameter);

    int countBooked(String title);

    void addBooked(Booked booked);

    void deleteBooked(Booked booked);

    void changeBookedState(Booked booked);

    void feedbackBooked(Booked booked);
}
