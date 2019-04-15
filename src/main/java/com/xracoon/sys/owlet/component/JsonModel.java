package com.xracoon.sys.owlet.component;

import java.util.HashMap;
import java.util.Map;

public class JsonModel {
	private int code;
	private String msg;
	private Object data;
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
	@SuppressWarnings("unchecked")
	public void putData(String name,Object value) {
		if(this.data==null || !Map.class.isAssignableFrom(this.data.getClass()))
			this.data=new HashMap<String,Object>();
		((Map<String,Object>)this.data).put(name, value);
	}
	
}
