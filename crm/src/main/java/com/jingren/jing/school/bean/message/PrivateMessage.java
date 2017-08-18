package com.jingren.jing.school.bean.message;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.employee.Employee;
/**
* @Title: PrivateMessage.java 
* @Package com.jingren.jing.school.back.message 
* @Description: TODO 员工私信
* @author 鲁晓飞 MR.Lu   
* @date 2017年5月25日 下午1:51:47 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class PrivateMessage implements Serializable{

	private Integer private_message_id;
	private String private_message_content;//私信内容
	private Date private_message_time; //私信发送时间
	private Integer send_employee_id; //发件人
	private Integer get_employee_id; //收件人
	private Integer is_read; //是否已读 1 已读 0 未读
	private String send_employee_name;
	private String get_employee_name;
	private Integer weidu;//未读的数量
	private Employee employee;
	
	public Integer getWeidu() {
		return weidu;
	}
	public void setWeidu(Integer weidu) {
		this.weidu = weidu;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Integer getPrivate_message_id() {
		return private_message_id;
	}
	public void setPrivate_message_id(Integer private_message_id) {
		this.private_message_id = private_message_id;
	}
	public String getPrivate_message_content() {
		return private_message_content;
	}
	public void setPrivate_message_content(String private_message_content) {
		this.private_message_content = private_message_content;
	}
	public Date getPrivate_message_time() {
		return private_message_time;
	}
	public void setPrivate_message_time(Date private_message_time) {
		this.private_message_time = private_message_time;
	}
	public Integer getSend_employee_id() {
		return send_employee_id;
	}
	public void setSend_employee_id(Integer send_employee_id) {
		this.send_employee_id = send_employee_id;
	}
	public Integer getGet_employee_id() {
		return get_employee_id;
	}
	public void setGet_employee_id(Integer get_employee_id) {
		this.get_employee_id = get_employee_id;
	}
	public Integer getIs_read() {
		return is_read;
	}
	public void setIs_read(Integer is_read) {
		this.is_read = is_read;
	}
	public String getSend_employee_name() {
		return send_employee_name;
	}
	public void setSend_employee_name(String send_employee_name) {
		this.send_employee_name = send_employee_name;
	}
	public String getGet_employee_name() {
		return get_employee_name;
	}
	public void setGet_employee_name(String get_employee_name) {
		this.get_employee_name = get_employee_name;
	}
	
}
