package com.alacriti.qandaportal.vo;

public class User {
	private String userName;
	private long userId;
	private String fullname;
	private String password;
	private String email;
	
	public User(String userName, long userId, String fullname, String password,String email) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
