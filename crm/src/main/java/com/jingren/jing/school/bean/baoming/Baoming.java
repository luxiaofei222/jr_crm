package com.jingren.jing.school.bean.baoming;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Baoming implements Serializable{

	private Integer baoming_id;
	private String course_name;
	private String course_leibie;
	private String course_dic;
	private String course_xuexiao;
	private String course_zhuanye;
	private String user_name;
	private String user_phone;
	private String user_qq;
	private String course_content;
	private Date baoming_time;
	private String user_ip;
	public Integer getBaoming_id() {
		return baoming_id;
	}
	public void setBaoming_id(Integer baoming_id) {
		this.baoming_id = baoming_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_leibie() {
		return course_leibie;
	}
	public void setCourse_leibie(String course_leibie) {
		this.course_leibie = course_leibie;
	}
	public String getCourse_dic() {
		return course_dic;
	}
	public void setCourse_dic(String course_dic) {
		this.course_dic = course_dic;
	}
	public String getCourse_xuexiao() {
		return course_xuexiao;
	}
	public void setCourse_xuexiao(String course_xuexiao) {
		this.course_xuexiao = course_xuexiao;
	}
	public String getCourse_zhuanye() {
		return course_zhuanye;
	}
	public void setCourse_zhuanye(String course_zhuanye) {
		this.course_zhuanye = course_zhuanye;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_qq() {
		return user_qq;
	}
	public void setUser_qq(String user_qq) {
		this.user_qq = user_qq;
	}
	public String getCourse_content() {
		return course_content;
	}
	public void setCourse_content(String course_content) {
		this.course_content = course_content;
	}
	public Date getBaoming_time() {
		return baoming_time;
	}
	public void setBaoming_time(Date baoming_time) {
		this.baoming_time = baoming_time;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
}
