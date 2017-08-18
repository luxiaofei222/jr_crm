package com.jingren.jing.oa.bean.oa_edu;

import java.io.Serializable;
/**
* @Title: OAEdu.java 
* @Package com.jingren.jing.oa.bean.oa_edu 
* @Description: TODO 教育经历-员工信息
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月1日 下午1:49:26 
* @version 网校+CRM系统 V1.0
 */
public class OAEdu implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer oa_edu_id;
	private Integer oa_employee_id;
	private String start_time;
	private String end_time;
	private String school_name;
	private String zhuanye;
	private String xueli;
	public Integer getOa_edu_id() {
		return oa_edu_id;
	}
	public void setOa_edu_id(Integer oa_edu_id) {
		this.oa_edu_id = oa_edu_id;
	}
	public Integer getOa_employee_id() {
		return oa_employee_id;
	}
	public void setOa_employee_id(Integer oa_employee_id) {
		this.oa_employee_id = oa_employee_id;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getXueli() {
		return xueli;
	}
	public void setXueli(String xueli) {
		this.xueli = xueli;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
