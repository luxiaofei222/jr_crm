package com.jingren.jing.oa.bean.oa_work;

import java.io.Serializable;
/**
* @Title: OAWork.java 
* @Package com.jingren.jing.oa.bean.oa_work 
* @Description: TODO 工作经历-员工信息
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月1日 下午1:52:37 
* @version 网校+CRM系统 V1.0
 */
public class OAWork implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer oa_work_id;
	private Integer oa_employee_id;
	private String start_time;
	private String end_time;
	private String danwei_name;
	private String gangwei;
	private String gongzuo_neirong;
	public Integer getOa_work_id() {
		return oa_work_id;
	}
	public void setOa_work_id(Integer oa_work_id) {
		this.oa_work_id = oa_work_id;
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
	public String getDanwei_name() {
		return danwei_name;
	}
	public void setDanwei_name(String danwei_name) {
		this.danwei_name = danwei_name;
	}
	public String getGangwei() {
		return gangwei;
	}
	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}
	public String getGongzuo_neirong() {
		return gongzuo_neirong;
	}
	public void setGongzuo_neirong(String gongzuo_neirong) {
		this.gongzuo_neirong = gongzuo_neirong;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
