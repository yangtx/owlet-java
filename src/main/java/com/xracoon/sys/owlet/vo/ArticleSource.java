package com.xracoon.sys.owlet.vo;

public enum ArticleSource {
	ORIGINAL(1, "原创"),
	TRANSLATE(2, "翻译"),
	NOTE(3, "笔记"),
	COPY(4, "转载");
	
	private ArticleSource(int id, String name){
		this.id=id;
		this.name=name;
	}
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
