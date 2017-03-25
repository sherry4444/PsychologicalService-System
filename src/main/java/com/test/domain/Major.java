package com.test.domain;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Major {

    private Integer majorId;
    private String majorName;

    public Major() {
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "Major{" +
                "majorId=" + majorId +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
