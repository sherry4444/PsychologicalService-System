package com.test.domain;

/**
 * Created by Administrator on 2017/4/6.
 */
public class TestLink {

    private Integer testlinkId;
    private String linkTitle;
    private String linkPath;
    private String linkImg;

    public TestLink() {
    }

    public Integer getTestlinkId() {
        return testlinkId;
    }

    public void setTestlinkId(Integer testlinkId) {
        this.testlinkId = testlinkId;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkPath() {
        return linkPath;
    }

    public void setLinkPath(String linkPath) {
        this.linkPath = linkPath;
    }

    public String getLinkImg() {
        return linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }
}
