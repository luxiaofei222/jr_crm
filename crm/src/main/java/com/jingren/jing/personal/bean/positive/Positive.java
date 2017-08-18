package com.jingren.jing.personal.bean.positive;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.oa.bean.oa_employee.OAEmployee;
import com.jingren.jing.school.bean.employee.Employee;
/**
* @Title: Positive.java 
* @Package com.jingren.jing.personal.bean.positive 
* @Description: TODO 转正申请
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月5日 上午9:32:45 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Positive implements Serializable{

	private Integer positive_id;
	private Integer employee_id;
	private String positive_content;
	private Integer bumen_id;
	private String gangwei;
	private Date shenqing_time;
	private Date positive_time;
	private Integer jingli_id;
	private String jingli_message;
	private Integer zongjian_id;
	private String zongjian_message;
	private Integer boss_id;
	private String boss_message;
	private Integer hr_id;
	private String hr_message;
	private Integer positive_state;//0 部门经理审批 1 总监审批 2人事审批 3总经理审批 4拒绝转正 5通过转正
	private Employee employee;
	private String bumen;
	private OAEmployee oaEmployee;
	
	public OAEmployee getOaEmployee() {
		return oaEmployee;
	}
	public void setOaEmployee(OAEmployee oaEmployee) {
		this.oaEmployee = oaEmployee;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public Integer getPositive_id() {
		return positive_id;
	}
	public void setPositive_id(Integer positive_id) {
		this.positive_id = positive_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getPositive_content() {
		return positive_content;
	}
	public void setPositive_content(String positive_content) {
		this.positive_content = positive_content;
	}
	public Integer getBuen_id() {
		return bumen_id;
	}
	public void setBuen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public String getGangwei() {
		return gangwei;
	}
	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}
	public Date getShenqing_time() {
		return shenqing_time;
	}
	public void setShenqing_time(Date shenqing_time) {
		this.shenqing_time = shenqing_time;
	}
	public Date getPositive_time() {
		return positive_time;
	}
	public void setPositive_time(Date positive_time) {
		this.positive_time = positive_time;
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
	public Integer getHr_id() {
		return hr_id;
	}
	public void setHr_id(Integer hr_id) {
		this.hr_id = hr_id;
	}
	public String getHr_message() {
		return hr_message;
	}
	public void setHr_message(String hr_message) {
		this.hr_message = hr_message;
	}
	public Integer getPositive_state() {
		return positive_state;
	}
	public void setPositive_state(Integer positive_state) {
		this.positive_state = positive_state;
	}
	
}
