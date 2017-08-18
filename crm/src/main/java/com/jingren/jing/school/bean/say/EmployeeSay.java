package com.jingren.jing.school.bean.say;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.util.CommentDate;
/**
* @Title: EmployeeSay.java 
* @Package com.jingren.jing.school.bean.say 
* @Description: TODO 说说
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月28日 上午8:34:25 
* @version 网校+CRM系统 V1.0
 */
public class EmployeeSay implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer say_id;
	private Integer employee_id;
	private Integer parent_id;
	private Integer say_level;
	private String say_pic;
	private String say_content;
	private Date say_time;
	private Integer zan_times;
	private Integer parent_employee_id;
	private List<EmployeeSay> sub_employeeSays;
	private String time_change;//时间转换
	private Employee employee;
	private Employee parent_employee;
	private String content_type;//类型
	private Integer is_zan;//是否赞过本条说说
	
	public Integer getIs_zan() {
		return is_zan;
	}
	public void setIs_zan(Integer is_zan) {
		this.is_zan = is_zan;
	}
	public Employee getParent_employee() {
		return parent_employee;
	}
	public void setParent_employee(Employee parent_employee) {
		this.parent_employee = parent_employee;
	}
	public List<EmployeeSay> getSub_employeeSays() {
		return sub_employeeSays;
	}
	public void setSub_employeeSays(List<EmployeeSay> sub_employeeSays) {
		this.sub_employeeSays = sub_employeeSays;
	}
	public String getTime_change() {
		try {
			time_change=CommentDate.Commentdat(say_time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time_change;
	}
	public void setTime_change(String time_change) {
		this.time_change = time_change;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Integer getSay_id() {
		return say_id;
	}
	public void setSay_id(Integer say_id) {
		this.say_id = say_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getSay_level() {
		return say_level;
	}
	public void setSay_level(Integer say_level) {
		this.say_level = say_level;
	}
	public String getSay_pic() {
		return say_pic;
	}
	public void setSay_pic(String say_pic) {
		this.say_pic = say_pic;
	}
	public String getSay_content() {
		return say_content;
	}
	public void setSay_content(String say_content) {
		this.say_content = say_content;
	}
	public Date getSay_time() {
		return say_time;
	}
	public void setSay_time(Date say_time) {
		this.say_time = say_time;
	}
	public Integer getZan_times() {
		return zan_times;
	}
	public void setZan_times(Integer zan_times) {
		this.zan_times = zan_times;
	}
	public Integer getParent_employee_id() {
		return parent_employee_id;
	}
	public void setParent_employee_id(Integer parent_employee_id) {
		this.parent_employee_id = parent_employee_id;
	}
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
	
}
