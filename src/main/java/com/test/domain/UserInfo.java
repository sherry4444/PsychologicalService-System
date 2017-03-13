package com.test.domain;

import java.io.Serializable;
import java.security.Timestamp;

/*
userId	int
userName	varchar
Password	varchar
salt	varchar
mobilePhone	varchar
userEmail	varchar
createTime	timestamp
modifyTime	timestamp
state	bit
role	int
*/

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String userName;
	private String Password;
	private String salt;
	private String mobilePhone;
	private String userEmail;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private Integer state;
	private Integer role;

	public UserInfo() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public UserInfo(Integer userId, String userName, String password, String salt, String mobilePhone, String userEmail, Timestamp createTime, Timestamp modifyTime, Integer state, Integer role) {
		this.userId = userId;
		this.userName = userName;
		Password = password;
		this.salt = salt;
		this.mobilePhone = mobilePhone;
		this.userEmail = userEmail;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.state = state;
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", Password='" + Password + '\'' +
				", salt='" + salt + '\'' +
				", mobilePhone='" + mobilePhone + '\'' +
				", userEmail='" + userEmail + '\'' +
				", createTime=" + createTime +
				", modifyTime=" + modifyTime +
				", state=" + state +
				", role=" + role +
				'}';
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}
