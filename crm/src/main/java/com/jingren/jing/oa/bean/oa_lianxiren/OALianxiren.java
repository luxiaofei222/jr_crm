package com.jingren.jing.oa.bean.oa_lianxiren;

import java.io.Serializable;
/**
* @Title: OALianxiren.java 
* @Package com.jingren.jing.oa.bean.oa_lianxiren 
* @Description: TODO 联系人
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月1日 下午2:06:22 
* @version 网校+CRM系统 V1.0
 */
public class OALianxiren implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer oa_lianxiren_id;
	private Integer oa_employee_id;
	private String guanxi;
	private String name;
	private Integer age;
	private String danwei;
	private String zhiwu;
	private String phone;
	private String type;//'紧急','家庭'
	public Integer getOa_lianxiren_id() {
		return oa_lianxiren_id;
	}
	public void setOa_lianxiren_id(Integer oa_lianxiren_id) {
		this.oa_lianxiren_id = oa_lianxiren_id;
	}
	public Integer getOa_employee_id() {
		return oa_employee_id;
	}
	public void setOa_employee_id(Integer oa_employee_id) {
		this.oa_employee_id = oa_employee_id;
	}
	public String getGuanxi() {
		return guanxi;
	}
	public void setGuanxi(String guanxi) {
		this.guanxi = guanxi;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getDanwei() {
		return danwei;
	}
	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}
	public String getZhiwu() {
		return zhiwu;
	}
	public void setZhiwu(String zhiwu) {
		this.zhiwu = zhiwu;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
