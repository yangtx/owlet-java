package com.xracoon.sys.owlet.config;

public enum UploadType {
	Image(0, "upload"),
	TagIcon(1, "tag_icons");
	
	private int value;
	private String path;
	UploadType(int value, String path){
		this.value=value;
		this.path=path;
	}
	public static UploadType ValueOf(int value){
		for(UploadType ut: UploadType.values()){
			if(ut.value==value)
				return ut;
		}
		return null;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
}
