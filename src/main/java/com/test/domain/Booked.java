package com.test.domain;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/4/14.
 */
public class Booked {

    private	Integer	bookId;//预约ID
    private	Integer	bookState;//预约状态
    private	Integer	bookUserId;//预约者
    private	Integer	bebookedUserId;//被预约的老师
    private Timestamp bookTime;//预约时间
    private	String	bookReason;//预约原因
    private	String	feedback;//反馈信息
    private	Timestamp submitTime;//提交时间

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

    public Timestamp getBookTime() {
        return bookTime;
    }

    public void setBookTime(Timestamp bookTime) {
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

    @Override
    public String toString() {
        return "Booked{" +
                "bookId=" + bookId +
                ", bookState='" + bookState + '\'' +
                ", bookUserId=" + bookUserId +
                ", bebookedUserId=" + bebookedUserId +
                ", bookTime=" + bookTime +
                ", bookReason='" + bookReason + '\'' +
                ", feedback='" + feedback + '\'' +
                ", submitTime=" + submitTime +
                '}';
    }
}
