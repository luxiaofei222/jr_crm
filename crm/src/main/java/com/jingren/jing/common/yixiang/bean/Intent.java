package com.jingren.jing.common.yixiang.bean;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: Intent.java 
* @Package com.jingren.jing.common.yixiang.bean 
* @Description: TODO 推广页意向记录
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月19日 下午3:11:40 
* @version 网校+CRM系统 V1.0
 */
public class Intent implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer yixiang_id;
	private String yixiang_phone;
	private String yixiang_province;
	private String yixiang_city;
	private String yixiang_ip;
	private Date yixiang_time;
	private Integer course_id;
	public Integer getYixiang_id() {
		return yixiang_id;
	}
	public void setYixiang_id(Integer yixiang_id) {
		this.yixiang_id = yixiang_id;
	}
	public String getYixiang_phone() {
		return yixiang_phone;
	}
	public void setYixiang_phone(String yixiang_phone) {
		this.yixiang_phone = yixiang_phone;
	}
	public String getYixiang_province() {
		return yixiang_province;
	}
	public void setYixiang_province(String yixiang_province) {
		this.yixiang_province = yixiang_province;
	}
	public String getYixiang_city() {
		return yixiang_city;
	}
	public void setYixiang_city(String yixiang_city) {
		this.yixiang_city = yixiang_city;
	}
	public String getYixiang_ip() {
		return yixiang_ip;
	}
	public void setYixiang_ip(String yixiang_ip) {
		this.yixiang_ip = yixiang_ip;
	}
	public Date getYixiang_time() {
		return yixiang_time;
	}
	public void setYixiang_time(Date yixiang_time) {
		this.yixiang_time = yixiang_time;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
