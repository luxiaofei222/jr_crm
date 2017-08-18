package com.jingren.jing.personal.bean.punch;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.common.organization.bean.Organization;
/**
* @Title: Punch.java 
* @Package com.jingren.jing.personal.bean.punch 
* @Description: TODO 忘记打卡申请
* @author 鲁晓飞 MR.Lu   
* @date 2017年7月17日 上午9:03:16 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Punch implements Serializable{
	
	private Integer punch_id;
	private String employee_name;//员工姓名
	private Integer employee_id;
	private Integer bumen_id;
	private Integer bumen_shangji;
	private Date punch_time;
	private String shijianduan;//时间段
	private Date daka_time;
	private String punch_info;//忘记打卡原因
	private Integer renshi_state;
	private String renshi_info;
	private Integer gangwei_id;//岗位ID
	private Organization organization;
	private String nianyue;
	private String nianyueri;
	
	public String getNianyue() {
		return nianyue;
	}
	public void setNianyue(String nianyue) {
		this.nianyue = nianyue;
	}
	public String getNianyueri() {
		return nianyueri;
	}
	public void setNianyueri(String nianyueri) {
		this.nianyueri = nianyueri;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Integer getGangwei_id() {
		return gangwei_id;
	}
	public void setGangwei_id(Integer gangwei_id) {
		this.gangwei_id = gangwei_id;
	}
	public Integer getPunch_id() {
		return punch_id;
	}
	public void setPunch_id(Integer punch_id) {
		this.punch_id = punch_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
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
	public Integer getBumen_shangji() {
		return bumen_shangji;
	}
	public void setBumen_shangji(Integer bumen_shangji) {
		this.bumen_shangji = bumen_shangji;
	}
	public Date getPunch_time() {
		return punch_time;
	}
	public void setPunch_time(Date punch_time) {
		this.punch_time = punch_time;
	}
	public String getShijianduan() {
		return shijianduan;
	}
	public void setShijianduan(String shijianduan) {
		this.shijianduan = shijianduan;
	}
	public Date getDaka_time() {
		return daka_time;
	}
	public void setDaka_time(Date daka_time) {
		this.daka_time = daka_time;
	}
	public String getPunch_info() {
		return punch_info;
	}
	public void setPunch_info(String punch_info) {
		this.punch_info = punch_info;
	}
	public Integer getRenshi_state() {
		return renshi_state;
	}
	public void setRenshi_state(Integer renshi_state) {
		this.renshi_state = renshi_state;
	}
	public String getRenshi_info() {
		return renshi_info;
	}
	public void setRenshi_info(String renshi_info) {
		this.renshi_info = renshi_info;
	}
	
}
