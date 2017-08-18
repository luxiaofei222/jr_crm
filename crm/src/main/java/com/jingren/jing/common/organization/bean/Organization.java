package com.jingren.jing.common.organization.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
* @Title: Organization.java 
* @Package com.jingren.jing.common.organization.bean 
* @Description: TODO 组织结构
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月2日 下午4:46:13 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Organization implements Serializable{

	private Integer organization_id;
	private String organization_name;//结构名称
	private Integer organization_level;//结构级别
	private Integer parent_id;
	private String organization_dis;//岗位职责
	private Date organization_time;
	private Integer bumen_id;
	private List<Organization> organizations_sub;//下级岗位
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	public Integer getOrganization_level() {
		return organization_level;
	}
	public void setOrganization_level(Integer organization_level) {
		this.organization_level = organization_level;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getOrganization_dis() {
		return organization_dis;
	}
	public void setOrganization_dis(String organization_dis) {
		this.organization_dis = organization_dis;
	}
	public Date getOrganization_time() {
		return organization_time;
	}
	public void setOrganization_time(Date organization_time) {
		this.organization_time = organization_time;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public List<Organization> getOrganizations_sub() {
		return organizations_sub;
	}
	public void setOrganizations_sub(List<Organization> organizations_sub) {
		this.organizations_sub = organizations_sub;
	}
	
}
