package com.jingren.jing.school.bean.employee;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.school.bean.role.Role;
/**
 * 
* @Title: Employee.java 
* @Package com.jingren.jing.school.bean.employee 
* @Description: TODO 员工信息
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月1日 上午10:27:17 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Employee implements Serializable{

	private Integer employee_id;
	private String employee_name;
	private String employee_sex;
	private String employee_qq;
	private String employee_phone;
	private String employee_mail;
	private String employee_trades;//员工个性签名
	private Date employee_time;
	private String login_name;
	private String login_password;
	private Integer role_id;
	private String employee_sector;//员工所属部门
	private Integer parent_id;//员工上级领导
	private String employee_state;//'在职','离职'
	private Role role;//所属角色
	private Integer organization_id;
	private String employee_pic;//头像
	private Organization organization;//所属部门
	private String job_number;//工号
	
	private String call_time_length;//通话总时长
	private Integer call_number;//通话条数
	private Integer bumen_id;//部门ID
	private String zuoxi;//坐席分机号
	private String zhou_call_time_length;//周累计时长
	
	private String paixu_shichang;//通话时长并排序
	private Integer jifen;//积分
	private String fengmian;//封面
	private String beijing;//背景图
	
	public String getFengmian() {
		return fengmian;
	}
	public void setFengmian(String fengmian) {
		this.fengmian = fengmian;
	}
	public String getBeijing() {
		return beijing;
	}
	public void setBeijing(String beijing) {
		this.beijing = beijing;
	}
	public String getZhou_call_time_length() {
		return zhou_call_time_length;
	}
	public void setZhou_call_time_length(String zhou_call_time_length) {
		this.zhou_call_time_length = zhou_call_time_length;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public String getPaixu_shichang() {
		return paixu_shichang;
	}
	public void setPaixu_shichang(String paixu_shichang) {
		this.paixu_shichang = paixu_shichang;
	}
	public String getCall_time_length() {
		return call_time_length;
	}
	public void setCall_time_length(String call_time_length) {
		this.call_time_length = call_time_length;
	}
	public Integer getCall_number() {
		return call_number;
	}
	public void setCall_number(Integer call_number) {
		this.call_number = call_number;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name.replace(" ", "");
	}
	public String getEmployee_sex() {
		return employee_sex;
	}
	public void setEmployee_sex(String employee_sex) {
		this.employee_sex = employee_sex;
	}
	public String getEmployee_qq() {
		return employee_qq;
	}
	public void setEmployee_qq(String employee_qq) {
		this.employee_qq = employee_qq;
	}
	public String getEmployee_phone() {
		return employee_phone;
	}
	public void setEmployee_phone(String employee_phone) {
		this.employee_phone = employee_phone;
	}
	public String getEmployee_mail() {
		return employee_mail;
	}
	public void setEmployee_mail(String employee_mail) {
		this.employee_mail = employee_mail;
	}
	public String getEmployee_trades() {
		return employee_trades;
	}
	public void setEmployee_trades(String employee_trades) {
		this.employee_trades = employee_trades;
	}
	public Date getEmployee_time() {
		return employee_time;
	}
	public void setEmployee_time(Date employee_time) {
		this.employee_time = employee_time;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name.replaceAll(" ", "");
	}
	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_password(String login_password) {
		this.login_password = login_password.replaceAll(" ", "");;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public String getEmployee_sector() {
		return employee_sector;
	}
	public void setEmployee_sector(String employee_sector) {
		this.employee_sector = employee_sector;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getEmployee_state() {
		return employee_state;
	}
	public void setEmployee_state(String employee_state) {
		this.employee_state = employee_state;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Integer getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(Integer organization_id) {
		this.organization_id = organization_id;
	}
	public String getEmployee_pic() {
		return employee_pic;
	}
	public void setEmployee_pic(String employee_pic) {
		this.employee_pic = employee_pic;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	public String getZuoxi() {
		return zuoxi;
	}
	public void setZuoxi(String zuoxi) {
		this.zuoxi = zuoxi;
	}
	public Integer getJifen() {
		return jifen;
	}
	public void setJifen(Integer jifen) {
		this.jifen = jifen;
	}
	
}
