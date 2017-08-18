package com.jingren.jing.school.bean.user;

import java.io.Serializable;
import java.util.Date;
/**
 * 网校注册用户
* @Title: User.java 
* @Package com.jingren.jing.school.bean.user 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月4日 上午8:10:08 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class User implements Serializable{

	private Integer user_id;
	private String user_name;
	private String user_password;
	private String user_phone;
	private String user_mail;
	private String user_nickname;
	private String user_card;
	private String user_auth_name;
	private String user_pay_pass;
	private Integer user_vip_leavl;//用户会员等级
	private Date user_time;
	private String user_state;//用户状态：'黑名单','限制','正常'
	private Integer user_integral;//用户积分
	private String user_ip;//用户注册IP
	private String user_pic;
	private String user_qq;
	private String user_city;//用户所在地
	private String user_bus;//用户所在企业
	private String user_sex;
	private Date user_birthday;
	private String user_zhiye;
	private String user_province;
	
	private Integer is_study;
	private Integer employee_id;
	private String employee_name;
	
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public Integer getIs_study() {
		return is_study;
	}
	public void setIs_study(Integer is_study) {
		this.is_study = is_study;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public String getUser_card() {
		return user_card;
	}
	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}
	public String getUser_auth_name() {
		return user_auth_name;
	}
	public void setUser_auth_name(String user_auth_name) {
		this.user_auth_name = user_auth_name;
	}
	public String getUser_pay_pass() {
		return user_pay_pass;
	}
	public void setUser_pay_pass(String user_pay_pass) {
		this.user_pay_pass = user_pay_pass;
	}
	public Integer getUser_vip_leavl() {
		return user_vip_leavl;
	}
	public void setUser_vip_leavl(Integer user_vip_leavl) {
		this.user_vip_leavl = user_vip_leavl;
	}
	public Date getUser_time() {
		return user_time;
	}
	public void setUser_time(Date user_time) {
		this.user_time = user_time;
	}
	public String getUser_state() {
		return user_state;
	}
	public void setUser_state(String user_state) {
		this.user_state = user_state;
	}
	public Integer getUser_integral() {
		return user_integral;
	}
	public void setUser_integral(Integer user_integral) {
		this.user_integral = user_integral;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	public String getUser_qq() {
		return user_qq;
	}
	public void setUser_qq(String user_qq) {
		this.user_qq = user_qq;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public String getUser_bus() {
		return user_bus;
	}
	public void setUser_bus(String user_bus) {
		this.user_bus = user_bus;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public Date getUser_birthday() {
		return user_birthday;
	}
	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}
	public String getUser_zhiye() {
		return user_zhiye;
	}
	public void setUser_zhiye(String user_zhiye) {
		this.user_zhiye = user_zhiye;
	}
	public String getUser_province() {
		return user_province;
	}
	public void setUser_province(String user_province) {
		this.user_province = user_province;
	}
	
}
