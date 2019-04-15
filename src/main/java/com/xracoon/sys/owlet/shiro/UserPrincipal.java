package com.xracoon.sys.owlet.shiro;

import java.io.Serializable;

public class UserPrincipal implements Serializable {
	private long id;
	private String username;
	private String email;
	private String tel;
	private int userType;
	private int credType;
	
	public UserPrincipal(long id, String name, int userType, int credType, String email, String tel){
		this.id = id;
		this.username = name;
		this.userType=userType;
		this.credType= credType;
		this.email = email;
		this.tel = tel;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public int getCredType() {
		return credType;
	}
	public void setCredType(int credType) {
		this.credType = credType;
	}
}
