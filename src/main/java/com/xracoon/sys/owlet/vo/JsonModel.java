/**
* @author yangtianxin
* @date 2016年6月8日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.vo;

import java.io.Serializable;

import com.xracoon.sys.owlet.config.Codes;

public class JsonModel implements Serializable {
	private static final long serialVersionUID = 9063630724817595659L;
	
	private int code;
	private String msg;
	private Object data;
	
	public static JsonModel success(Object data){
		JsonModel jm=new JsonModel();
		jm.code=Codes.SUCCESS;
		jm.data=data;
		return jm;
	}
	public static JsonModel fail(int code, String msg){
		JsonModel jm=new JsonModel();
		jm.code=code;
		jm.msg=msg;
		return jm;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
