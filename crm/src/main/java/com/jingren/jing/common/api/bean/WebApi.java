package com.jingren.jing.common.api.bean;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class WebApi implements Serializable{

	private Integer api_id;
	private String api_info;
	private Date api_time;
	public Integer getApi_id() {
		return api_id;
	}
	public void setApi_id(Integer api_id) {
		this.api_id = api_id;
	}
	public String getApi_info() {
		return api_info;
	}
	public void setApi_info(String api_info) {
		this.api_info = api_info;
	}
	public Date getApi_time() {
		return api_time;
	}
	public void setApi_time(Date api_time) {
		this.api_time = api_time;
	}
	
}
