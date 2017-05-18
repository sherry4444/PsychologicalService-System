package com.test.dao;


import com.test.domain.Booked;
import com.test.domain.Notice;
import com.test.domain.TestLink;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface BookedDao {

    List<Booked> findbookedAll(Map<String, Object> parameter);

    int countBooked(Map<String,Object> parameter);

    int findhadfinish(Booked booked);

    void addBooked(Booked booked);

    void deleteBooked(Booked booked);

    void changeBookedState(Booked booked);

    void feedbackBooked(Booked booked);

    void finishState(Booked booked);
}
