package com.jingren.jing.oa.bean.oa_notice;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: Notice.java 
* @Package com.jingren.jing.oa.bean.oa_notice 
* @Description: TODO 公司公告
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月31日 上午10:21:15 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Notice implements Serializable{

	private Integer notice_id;
	private String notice_content;
	private Date notice_time;
	private String time;
	public Integer getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(Integer notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public Date getNotice_time() {
		return notice_time;
	}
	public void setNotice_time(Date notice_time) {
		this.notice_time = notice_time;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
