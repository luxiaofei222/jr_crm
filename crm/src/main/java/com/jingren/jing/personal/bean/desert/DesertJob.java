package com.jingren.jing.personal.bean.desert;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.oa.bean.oa_employee.OAEmployee;
import com.jingren.jing.school.bean.employee.Employee;
/**
* @Title: DesertJob.java 
* @Package com.jingren.jing.personal.bean.desert 
* @Description: TODO 离职申请
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月2日 上午9:17:23 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class DesertJob implements Serializable{

	private Integer desert_id;
	private Integer employee_id;
	private Integer bumen_id;
	private String gangwei;
	private Date desert_time;//预计离职日期
	private Date tijiao_time;//提交日期
	private String desert_content;
	private Integer jingli_id;
	private String jingli_message;
	private Integer zongjian_id;
	private String zongjian_message;
	private Integer boss_id;
	private String boss_message;
	private Integer desert_state;
	
	private Employee employee;
	private String bumen;
	
	private OAEmployee oaEmployee;
	
	public OAEmployee getOaEmployee() {
		return oaEmployee;
	}
	public void setOaEmployee(OAEmployee oaEmployee) {
		this.oaEmployee = oaEmployee;
	}
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Integer getDesert_id() {
		return desert_id;
	}
	public void setDesert_id(Integer desert_id) {
		this.desert_id = desert_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public String getGangwei() {
		return gangwei;
	}
	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}
	public Date getDesert_time() {
		return desert_time;
	}
	public void setDesert_time(Date desert_time) {
		this.desert_time = desert_time;
	}
	public Date getTijiao_time() {
		return tijiao_time;
	}
	public void setTijiao_time(Date tijiao_time) {
		this.tijiao_time = tijiao_time;
	}
	public String getDesert_content() {
		return desert_content;
	}
	public void setDesert_content(String desert_content) {
		this.desert_content = desert_content;
	}
	public Integer getJingli_id() {
		return jingli_id;
	}
	public void setJingli_id(Integer jingli_id) {
		this.jingli_id = jingli_id;
	}
	public String getJingli_message() {
		return jingli_message;
	}
	public void setJingli_message(String jingli_message) {
		this.jingli_message = jingli_message;
	}
	public Integer getZongjian_id() {
		return zongjian_id;
	}
	public void setZongjian_id(Integer zongjian_id) {
		this.zongjian_id = zongjian_id;
	}
	public String getZongjian_message() {
		return zongjian_message;
	}
	public void setZongjian_message(String zongjian_message) {
		this.zongjian_message = zongjian_message;
	}
	public Integer getBoss_id() {
		return boss_id;
	}
	public void setBoss_id(Integer boss_id) {
		this.boss_id = boss_id;
	}
	public String getBoss_message() {
		return boss_message;
	}
	public void setBoss_message(String boss_message) {
		this.boss_message = boss_message;
	}
	public Integer getDesert_state() {
		return desert_state;
	}
	public void setDesert_state(Integer desert_state) {
		this.desert_state = desert_state;
	}
	
}
