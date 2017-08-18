package com.jingren.jing.personal.bean.emlog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.school.bean.employee.Employee;
/**
* @Title: EmLog.java 
* @Package com.jingren.jing.personal.bean.emlog 
* @Description: TODO 员工日报
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月6日 下午4:51:46 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class EmLog implements Serializable{

	private Integer log_id;
	private String log_content;
	private Date tijiao_time;
	private Integer employee_id;//日报书写人
	private Integer parent_id;
	private Integer pinglun_id;//评论人的ID
	private Integer pingfen;
	private Integer bumen_id;
	private Integer shang_bumen_id;
	
	private  String nianyue;//当前年月
	private  String nianyueri;//当前年月日
	
	private Employee employee;
	private List<EmLog> emLogs;
	private  String bumen;//所在部门
	
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public List<EmLog> getEmLogs() {
		return emLogs;
	}
	public void setEmLogs(List<EmLog> emLogs) {
		this.emLogs = emLogs;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getNianyueri() {
		return nianyueri;
	}
	public void setNianyueri(String nianyueri) {
		this.nianyueri = nianyueri;
	}
	public String getNianyue() {
		return nianyue;
	}
	public void setNianyue(String nianyue) {
		this.nianyue = nianyue;
	}
	public Integer getLog_id() {
		return log_id;
	}
	public void setLog_id(Integer log_id) {
		this.log_id = log_id;
	}
	public String getLog_content() {
		return log_content;
	}
	public void setLog_content(String log_content) {
		this.log_content = log_content;
	}
	public Date getTijiao_time() {
		return tijiao_time;
	}
	public void setTijiao_time(Date tijiao_time) {
		this.tijiao_time = tijiao_time;
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
	public Integer getPinglun_id() {
		return pinglun_id;
	}
	public void setPinglun_id(Integer pinglun_id) {
		this.pinglun_id = pinglun_id;
	}
	public Integer getPingfen() {
		return pingfen;
	}
	public void setPingfen(Integer pingfen) {
		this.pingfen = pingfen;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public Integer getShang_bumen_id() {
		return shang_bumen_id;
	}
	public void setShang_bumen_id(Integer shang_bumen_id) {
		this.shang_bumen_id = shang_bumen_id;
	}
	
}
