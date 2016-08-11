package com.alacriti.qandaportal.vo;

public class User {
	private String userName;
	private long userId;
	private String fullName;
	private String password;
	private String email;
	private String role;
	private boolean loginStatus;
	public User(long userId,String userName,  String fullName,String email,
			 String role, boolean loginStatus){
		super();
		this.userName = userName;
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.role = role;
		this.loginStatus = loginStatus;
	}
	public User(){
		
	}
	public User(long userId,String userName,  String fullName, String email,String password) {
		super();
		this.userName = userName;
		this.userId = userId;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
	}
	public User(String userName, String fullName,String email, String password,
			String role) {
		super();
		this.userName = userName;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	public User(long userId,String userName,  String fullName, String email,String password,
			 String role){
		super();
		this.userName = userName;
		this.userId = userId;
		this.fullName = fullName;
		this.password = password;
		this.email = email;
		this.role = role;
	}
	public boolean isLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
