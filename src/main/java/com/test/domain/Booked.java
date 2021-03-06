package com.test.domain;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/4/14.
 */
public class Booked {

    private	Integer	bookId;//预约ID
    private	Integer	bookState;//预约状态
    private	Integer	bookUserId;//预约者
    private String bookUserName;//预约者名字
    private	Integer	bebookedUserId;//被预约的老师
    private String bebookedUserName;//被预约的老师名字
    private String bookTime;//预约时间
    private	String	bookReason;//预约原因
    private	String	feedback;//反馈信息
    private	Timestamp submitTime;//提交时间
    private String stateName;//预约状态名称

    public Booked() {
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookState() {
        return bookState;
    }

    public void setBookState(Integer bookState) {
        switch (bookState){
            case 0:stateName="预约";break;
            case 1:stateName="预约中";break;
            case 2:stateName="预约成功";break;
            case 3:stateName="预约失败";break;
            case 4:stateName="预约结束";break;
        }
        this.stateName = stateName;
        this.bookState = bookState;
    }

    public Integer getBookUserId() {
        return bookUserId;
    }

    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }

    public Integer getBebookedUserId() {
        return bebookedUserId;
    }

    public void setBebookedUserId(Integer bebookedUserId) {
        this.bebookedUserId = bebookedUserId;
    }

    public String getBookTime() {
        return bookTime;
    }

    public void setBookTime(String bookTime) {
        this.bookTime = bookTime;
    }

    public String getBookReason() {
        return bookReason;
    }

    public void setBookReason(String bookReason) {
        this.bookReason = bookReason;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {

        this.stateName = stateName;
    }

    public String getBookUserName() {
        return bookUserName;
    }

    public void setBookUserName(String bookUserName) {
        this.bookUserName = bookUserName;
    }

    public String getBebookedUserName() {
        return bebookedUserName;
    }

    public void setBebookedUserName(String bebookedUserName) {
        this.bebookedUserName = bebookedUserName;
    }

    @Override
    public String toString() {
        return "Booked{" +
                "bookId=" + bookId +
                ", bookState=" + bookState +
                ", bookUserId=" + bookUserId +
                ", bookUserName='" + bookUserName + '\'' +
                ", bebookedUserId=" + bebookedUserId +
                ", bebookedUserName='" + bebookedUserName + '\'' +
                ", bookTime=" + bookTime +
                ", bookReason='" + bookReason + '\'' +
                ", feedback='" + feedback + '\'' +
                ", submitTime=" + submitTime +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}
