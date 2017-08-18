package com.jingren.jing.school.bean.pricesys;

import java.io.Serializable;
import java.util.Date;

/**
* @Title: PriceSys.java 
* @Package com.jingren.jing.school.bean.pricesys 
* @Description: TODO 低价体系
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月15日 下午2:58:07 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class PriceSys implements Serializable{

	private Integer price_sys_id;
	private String price_sys_title;
	private String price_sys_content;
	private Date price_sys_time;
	public Integer getPrice_sys_id() {
		return price_sys_id;
	}
	public void setPrice_sys_id(Integer price_sys_id) {
		this.price_sys_id = price_sys_id;
	}
	public String getPrice_sys_title() {
		return price_sys_title;
	}
	public void setPrice_sys_title(String price_sys_title) {
		this.price_sys_title = price_sys_title;
	}
	public String getPrice_sys_content() {
		return price_sys_content;
	}
	public void setPrice_sys_content(String price_sys_content) {
		this.price_sys_content = price_sys_content;
	}
	public Date getPrice_sys_time() {
		return price_sys_time;
	}
	public void setPrice_sys_time(Date price_sys_time) {
		this.price_sys_time = price_sys_time;
	}
	
}
