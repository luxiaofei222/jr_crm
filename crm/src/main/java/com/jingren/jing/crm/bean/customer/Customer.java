package com.jingren.jing.crm.bean.customer;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: Customer.java 
* @Package com.jingren.jing.crm.bean.customer 
* @Description: TODO 客户信息管理-企业库
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月16日 下午1:19:04 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Customer implements Serializable{

	private Integer customer_id;
	private Integer company_id;//单位id
	private String customer_name;//客户姓名
	private String customer_depart;//所属部门
	private String customer_position;//客户职位
	private String customer_officephone;//办公电话
	private String customer_phone;//客户手机号
	private String customer_qq;
	private String customer_weixin;
	private String customer_mail;
	private Integer course_id;
	private Integer employee_id;
	private Integer distribution_employee_id;
	private String customer_situation;//客户情况
	private Date customer_time;//添加时间
	private String customer_sex;//联系人性别
	private Integer course_parent_id;//报考课程的一级ID
	private  String customer_note;//备注信息
	private  Integer is_my_customer;//0不是1是
	
	public Integer getIs_my_customer() {
		return is_my_customer;
	}
	public void setIs_my_customer(Integer is_my_customer) {
		this.is_my_customer = is_my_customer;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_depart() {
		return customer_depart;
	}
	public void setCustomer_depart(String customer_depart) {
		this.customer_depart = customer_depart;
	}
	public String getCustomer_position() {
		return customer_position;
	}
	public void setCustomer_position(String customer_position) {
		this.customer_position = customer_position;
	}
	public String getCustomer_officephone() {
		return customer_officephone;
	}
	public void setCustomer_officephone(String customer_officephone) {
		this.customer_officephone = customer_officephone;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_qq() {
		return customer_qq;
	}
	public void setCustomer_qq(String customer_qq) {
		this.customer_qq = customer_qq;
	}
	public String getCustomer_weixin() {
		return customer_weixin;
	}
	public void setCustomer_weixin(String customer_weixin) {
		this.customer_weixin = customer_weixin;
	}
	public String getCustomer_mail() {
		return customer_mail;
	}
	public void setCustomer_mail(String customer_mail) {
		this.customer_mail = customer_mail;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getDistribution_employee_id() {
		return distribution_employee_id;
	}
	public void setDistribution_employee_id(Integer distribution_employee_id) {
		this.distribution_employee_id = distribution_employee_id;
	}
	public String getCustomer_situation() {
		return customer_situation;
	}
	public void setCustomer_situation(String customer_situation) {
		this.customer_situation = customer_situation;
	}
	public Date getCustomer_time() {
		return customer_time;
	}
	public void setCustomer_time(Date customer_time) {
		this.customer_time = customer_time;
	}
	public String getCustomer_sex() {
		return customer_sex;
	}
	public void setCustomer_sex(String customer_sex) {
		this.customer_sex = customer_sex;
	}
	public Integer getCourse_parent_id() {
		return course_parent_id;
	}
	public void setCourse_parent_id(Integer course_parent_id) {
		this.course_parent_id = course_parent_id;
	}
	public String getCustomer_note() {
		return customer_note;
	}
	public void setCustomer_note(String customer_note) {
		this.customer_note = customer_note;
	}
	
}
