package com.jingren.jing.school.bean.advertising;

import java.io.Serializable;
import java.util.Date;
/**
 * 广告
* @Title: Advertising.java 
* @Package com.jingren.jing.school.bean.advertising 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月8日 上午11:07:29 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Advertising implements Serializable{

	private Integer adver_id;
	private String adver_link;
	private String adver_name;
	private String adver_pic;
	private String adver_type;//广告类型或者位置
	private Date adver_time;
	private String is_show;//是 上架 否 下架
	public Integer getAdver_id() {
		return adver_id;
	}
	public void setAdver_id(Integer adver_id) {
		this.adver_id = adver_id;
	}
	public String getAdver_link() {
		return adver_link;
	}
	public void setAdver_link(String adver_link) {
		this.adver_link = adver_link;
	}
	public String getAdver_name() {
		return adver_name;
	}
	public void setAdver_name(String adver_name) {
		this.adver_name = adver_name;
	}
	public String getAdver_pic() {
		return adver_pic;
	}
	public void setAdver_pic(String adver_pic) {
		this.adver_pic = adver_pic;
	}
	public String getAdver_type() {
		return adver_type;
	}
	public void setAdver_type(String adver_type) {
		this.adver_type = adver_type;
	}
	public Date getAdver_time() {
		return adver_time;
	}
	public void setAdver_time(Date adver_time) {
		this.adver_time = adver_time;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	
	
}
