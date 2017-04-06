package com.test.service;

import com.test.domain.College;
import com.test.domain.Major;
import com.test.domain.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by wgt on 2017/3/10.
 */
public interface CollegeMajorService {

    List<College> findcollegeAll(String title);
    void addcollege(College college);
    void deletecollege(College college);
    void updatecollege(College college);

    List<Major> findmajorAll( String title);
    void addmajor(Major major);
    void deletemajor(Major major);
    void updatemajor(Major major);
}
