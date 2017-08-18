package com.jingren.jing.crm.bean.company;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.employee.Employee;
/**
* @Title: Company.java 
* @Package com.jingren.jing.crm.bean.company 
* @Description: TODO 企业信息
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月16日 下午5:00:20 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Company implements Serializable{
	private Integer company_id;
	private String company_guimo;//企业规模
	private String company_zongjingli_one;//总经理1姓名
	private String company_zongjingli_one_phone;//总经理1电话
	private String company_zongjingli_two;//总经理2姓名
	private String company_zongjingli_two_phone;//总经理2电话
	private String company_fax;//企业传真
	private String suoshuhangye;//企业所属行业
	private String company_type;//企业类型
	private String company_jituan;//企业所属集团
	private String company_jingying;//企业经营范围
	private String company_province;//企业所在省
	private String company_city;//企业所在市
	private String company_youbian;//企业邮编
	private String company_quhao;//企业区号
	private String company_mail;//企业邮箱
	private String company_addr;//企业地址
	private String company_web;//企业网址
	private String is_xiehui;//是否协会会员
	private Integer employee_id;//添加信息的员工id
	private Date company_time;//添加时间
	private Employee employee;
	private String company_name;//企业名称
	private String company_note;//备注
	private Integer lianxirennumber;//联系人数量
	private Integer baokaonumber;//企业报考学员数量
	
	private String is_jiaru_mybusiness;//是否加入我得企业库：是 ，否
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public String getCompany_guimo() {
		return company_guimo;
	}
	public void setCompany_guimo(String company_guimo) {
		this.company_guimo = company_guimo;
	}
	public String getCompany_zongjingli_one() {
		return company_zongjingli_one;
	}
	public void setCompany_zongjingli_one(String company_zongjingli_one) {
		this.company_zongjingli_one = company_zongjingli_one;
	}
	public String getCompany_zongjingli_one_phone() {
		return company_zongjingli_one_phone;
	}
	public void setCompany_zongjingli_one_phone(String company_zongjingli_one_phone) {
		this.company_zongjingli_one_phone = company_zongjingli_one_phone;
	}
	public String getCompany_zongjingli_two() {
		return company_zongjingli_two;
	}
	public void setCompany_zongjingli_two(String company_zongjingli_two) {
		this.company_zongjingli_two = company_zongjingli_two;
	}
	public String getCompany_zongjingli_two_phone() {
		return company_zongjingli_two_phone;
	}
	public void setCompany_zongjingli_two_phone(String company_zongjingli_two_phone) {
		this.company_zongjingli_two_phone = company_zongjingli_two_phone;
	}
	public String getCompany_fax() {
		return company_fax;
	}
	public void setCompany_fax(String company_fax) {
		this.company_fax = company_fax;
	}
	public String getSuoshuhangye() {
		return suoshuhangye;
	}
	public void setSuoshuhangye(String suoshuhangye) {
		this.suoshuhangye = suoshuhangye;
	}
	public String getCompany_type() {
		return company_type;
	}
	public void setCompany_type(String company_type) {
		this.company_type = company_type;
	}
	public String getCompany_jituan() {
		return company_jituan;
	}
	public void setCompany_jituan(String company_jituan) {
		this.company_jituan = company_jituan;
	}
	public String getCompany_jingying() {
		return company_jingying;
	}
	public void setCompany_jingying(String company_jingying) {
		this.company_jingying = company_jingying;
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
	public String getCompany_youbian() {
		return company_youbian;
	}
	public void setCompany_youbian(String company_youbian) {
		this.company_youbian = company_youbian;
	}
	public String getCompany_quhao() {
		return company_quhao;
	}
	public void setCompany_quhao(String company_quhao) {
		this.company_quhao = company_quhao;
	}
	public String getCompany_mail() {
		return company_mail;
	}
	public void setCompany_mail(String company_mail) {
		this.company_mail = company_mail;
	}
	public String getCompany_addr() {
		return company_addr;
	}
	public void setCompany_addr(String company_addr) {
		this.company_addr = company_addr;
	}
	public String getCompany_web() {
		return company_web;
	}
	public void setCompany_web(String company_web) {
		this.company_web = company_web;
	}
	public String getIs_xiehui() {
		return is_xiehui;
	}
	public void setIs_xiehui(String is_xiehui) {
		this.is_xiehui = is_xiehui;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Date getCompany_time() {
		return company_time;
	}
	public void setCompany_time(Date company_time) {
		this.company_time = company_time;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_note() {
		return company_note;
	}
	public void setCompany_note(String company_note) {
		this.company_note = company_note;
	}
	public Integer getLianxirennumber() {
		return lianxirennumber;
	}
	public void setLianxirennumber(Integer lianxirennumber) {
		this.lianxirennumber = lianxirennumber;
	}
	public Integer getBaokaonumber() {
		return baokaonumber;
	}
	public void setBaokaonumber(Integer baokaonumber) {
		this.baokaonumber = baokaonumber;
	}
	public String getIs_jiaru_mybusiness() {
		return is_jiaru_mybusiness;
	}
	public void setIs_jiaru_mybusiness(String is_jiaru_mybusiness) {
		this.is_jiaru_mybusiness = is_jiaru_mybusiness;
	}
	
}
