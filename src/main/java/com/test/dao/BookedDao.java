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

    int countBooked(@Param("title") String title);

    void addBooked(Booked booked);

    void deleteBooked(Booked booked);

    void changeBookedState(Booked booked);

    void feedbackBooked(Booked booked);
}
