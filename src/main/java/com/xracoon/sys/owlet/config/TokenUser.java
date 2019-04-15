package com.xracoon.sys.owlet.config;

import javax.servlet.ServletRequest;

import com.xracoon.sys.owlet.component.GlobalConfig;

public class TokenUser {
	private long id;
	private String username;
	private String email;
	private int type;
	
	public static TokenUser fetchFrom(ServletRequest req){
		return (TokenUser) req.getAttribute(GlobalConfig.ATTR_USER);
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}
