package com.test.domain;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Teacher {

    private Integer teacherId;
    private String  teacherName;
    private Integer tc_userId;
    private UserInfo userInfo;

    public Teacher() {
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTc_userId() {
        return tc_userId;
    }

    public void setTc_userId(Integer tc_userId) {
        this.tc_userId = tc_userId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
