package com.test.domain;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Student {
    private Integer studentId;
    private String studentName;
    private String studentNumber;
    private Integer stu_userId;
    private Integer collegeId;
    private Integer majorId;
    private UserInfo userInfo;
    private College college;
    private Major major;
    private String collegeName;
    private String majorName;

    public Student() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Integer getStu_userId() {
        return stu_userId;
    }

    public void setStu_userId(Integer stu_userId) {
        this.stu_userId = stu_userId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentNumber='" + studentNumber + '\'' +
                ", stu_userId=" + stu_userId +
                ", collegeId=" + collegeId +
                ", majorId=" + majorId +
                ", userInfo=" + userInfo +
                ", college=" + college +
                ", major=" + major +
                ", collegeName='" + collegeName + '\'' +
                ", majorName='" + majorName + '\'' +
                '}';
    }
}
