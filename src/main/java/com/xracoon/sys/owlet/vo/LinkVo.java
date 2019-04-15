/**
* @author yangtianxin
* @date 2016年6月8日
* Copyright 2016, .... All right reserved. 
*/

package com.xracoon.sys.owlet.vo;

import javax.validation.GroupSequence;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.xracoon.sys.owlet.vo.validate.IExistGroup;
import com.xracoon.sys.owlet.vo.validate.IVaildGroup;

@GroupSequence(value = { IExistGroup.class, IVaildGroup.class, LinkVo.class })
public class LinkVo {
    private Long id;
    
	@NotNull(groups = IExistGroup.class)
	private String title;
	
	@NotNull(groups = IExistGroup.class)
	private String url;
	
	@NotNull(groups = IExistGroup.class)
	@Range(min=0, max=10)
    private Integer type;
    
    
    private String info;
    private String snapshot;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getSnapshot() {
		return snapshot;
	}
	public void setSnapshot(String snapshot) {
		this.snapshot = snapshot;
	}
}