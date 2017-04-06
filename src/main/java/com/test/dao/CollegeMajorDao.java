package com.test.dao;


import com.test.domain.College;
import com.test.domain.Major;
import com.test.domain.Notice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 */
@Repository
public interface CollegeMajorDao {

    List<College> findcollegeAll(@Param("title") String title);
    void addcollege(College college);
    void updatecollege(College college);

    List<Major> findmajorAll(@Param("title") String title);
    void addmajor(Major major);
    void updatemajor(Major major);


    void deletecollege(College college);

    void deletemajor(Major major);
}
