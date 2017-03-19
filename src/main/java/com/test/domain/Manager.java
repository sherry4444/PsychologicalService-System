package com.test.domain;

/**
 * Created by Administrator on 2017/3/16.
 */
public class Manager {

    private Integer managerId;
    private String  managerName;
    private Integer mg_userId;
    private UserInfo userInfo;

    public Manager() {
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Integer getMg_userId() {
        return mg_userId;
    }

    public void setMg_userId(Integer mg_userId) {
        this.mg_userId = mg_userId;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId=" + managerId +
                ", managerName='" + managerName + '\'' +
                ", mg_userId=" + mg_userId +
                ", userInfo=" + userInfo +
                '}';
    }
}
