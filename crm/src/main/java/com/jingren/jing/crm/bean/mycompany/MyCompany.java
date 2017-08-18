package com.jingren.jing.crm.bean.mycompany;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.crm.bean.company.Company;
/**
* @Title: MyCompany.java 
* @Package com.jingren.jing.crm.bean.myconpany 
* @Description: TODO 我得企业库
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月16日 上午9:25:18 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class MyCompany implements Serializable{
	private Integer my_company_id;
	private Integer employee_id;//员工
	private Integer company_id;//企业
	private Integer organization_id;//岗位
	private Integer bumen_id;//所属部门
	private Date my_company_time;
	private Company company;//企业信息
	private String suoshuhangye;
	private String company_province;
	private String company_city;
	
	private String company_name;
	private Integer is_flag;//标记
	
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public Integer getIs_flag() {
		return is_flag;
	}
	public void setIs_flag(Integer is_flag) {
		this.is_flag = is_flag;
	}
	public Integer getMy_company_id() {
		return my_company_id;
	}
	public void setMy_company_id(Integer my_company_id) {
		this.my_company_id = my_company_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public Date getMy_company_time() {
		return my_company_time;
	}
	public void setMy_company_time(Date my_company_time) {
		this.my_company_time = my_company_time;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public String getSuoshuhangye() {
		return suoshuhangye;
	}
	public void setSuoshuhangye(String suoshuhangye) {
		this.suoshuhangye = suoshuhangye;
	}
	public String getCompany_province() {
		return company_province;
	}
	public void setCompany_province(String company_province) {
		this.company_province = company_province;
	}
	public String getCompany_city() {
		return company_city;
	}
	public void setCompany_city(String company_city) {
		this.company_city = company_city;
	}
}
