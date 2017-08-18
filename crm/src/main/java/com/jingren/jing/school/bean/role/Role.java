package com.jingren.jing.school.bean.role;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* @Title: Role.java 
* @Package com.jingren.jing.school.bean.role 
* @Description: TODO 角色信息
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月1日 上午10:30:51 
* @version 网校+CRM系统 V1.0
 */
public class Role implements Serializable{

	/**   
	* @Title: Role.java 
	* @Package com.jingren.jing.school.bean.role 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月10日 上午11:20:01 
	* @version 网校+CRM系统 V1.0   
	*/
	private static final long serialVersionUID = 1L;
	private Integer role_id;
	private String role_name;
	private String role_dis;
	private Date role_time;
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_dis() {
		return role_dis;
	}
	public void setRole_dis(String role_dis) {
		this.role_dis = role_dis;
	}
	public Date getRole_time() {
		return role_time;
	}
	public void setRole_time(Date role_time) {
		this.role_time = role_time;
	}
	
}
