package com.jingren.jing.school.bean.cooperation;

import java.io.Serializable;
import java.util.Date;
/**
 * 合作伙伴
* @Title: Cooperation.java 
* @Package com.jingren.jing.school.bean.cooperation 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月4日 上午8:24:22 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Cooperation implements Serializable{

	private Integer cooper_id;
	private String cooper_name;
	private String cooper_link;
	private String cooper_pic;
	private Date cooper_time;
	private String cooper_state;//合作伙伴状态:'隐藏','显示'
	public Integer getCooper_id() {
		return cooper_id;
	}
	public void setCooper_id(Integer cooper_id) {
		this.cooper_id = cooper_id;
	}
	public String getCooper_name() {
		return cooper_name;
	}
	public void setCooper_name(String cooper_name) {
		this.cooper_name = cooper_name;
	}
	public String getCooper_link() {
		return cooper_link;
	}
	public void setCooper_link(String cooper_link) {
		this.cooper_link = cooper_link;
	}
	public String getCooper_pic() {
		return cooper_pic;
	}
	public void setCooper_pic(String cooper_pic) {
		this.cooper_pic = cooper_pic;
	}
	public Date getCooper_time() {
		return cooper_time;
	}
	public void setCooper_time(Date cooper_time) {
		this.cooper_time = cooper_time;
	}
	public String getCooper_state() {
		return cooper_state;
	}
	public void setCooper_state(String cooper_state) {
		this.cooper_state = cooper_state;
	}
	
}
