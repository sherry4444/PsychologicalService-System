package com.test.domain;

/**
 * Created by Administrator on 2017/3/23.
 */
public class CollegeMajor {
    private College college;
    private Major major;

    public CollegeMajor() {
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "CollegeMajor{" +
                "college=" + college.toString() +
                ", major=" + major.toString() +
                '}';
    }
}
