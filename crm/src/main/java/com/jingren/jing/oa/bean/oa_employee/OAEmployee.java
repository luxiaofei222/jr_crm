package com.jingren.jing.oa.bean.oa_employee;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.common.organization.bean.Organization;

/**
* @Title: OAEmployee.java 
* @Package com.jingren.jing.oa.bean.oa_employee 
* @Description: TODO 员工信息管理
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月1日 下午1:45:19 
* @version 网校+CRM系统 V1.0
 */
public class OAEmployee implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer oa_employee_id;
	private Integer employee_id;
	private Integer bumen_id;
	private Integer gangwei_id;
	private Date ruzhi_time;
	private String xingming;
	private String xingbie;
	private String minzu;
	private String birthday;
	private String zhengzhi_mianmao;
	private String hunyin_state;
	private String zuigao_xueli;
	private String phone;
	private String qq;
	private String card_number;
	private String mail;
	private String huji;
	private String now_addr;
	private String gangwei_state;
	private Date oa_employee_time;
	private String employee_pic;
	private Date zhuanzheng_time;//转正日期
	private String jiguan;//籍贯
	private Organization organization;
	private String lizhi_time;//离职时间
	public String getLizhi_time() {
		return lizhi_time;
	}
	public void setLizhi_time(String lizhi_time) {
		this.lizhi_time = lizhi_time;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public String getJiguan() {
		return jiguan;
	}
	public void setJiguan(String jiguan) {
		this.jiguan = jiguan;
	}
	public Date getZhuanzheng_time() {
		return zhuanzheng_time;
	}
	public void setZhuanzheng_time(Date zhuanzheng_time) {
		this.zhuanzheng_time = zhuanzheng_time;
	}
	public Integer getOa_employee_id() {
		return oa_employee_id;
	}
	public void setOa_employee_id(Integer oa_employee_id) {
		this.oa_employee_id = oa_employee_id;
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
	public Integer getGangwei_id() {
		return gangwei_id;
	}
	public void setGangwei_id(Integer gangwei_id) {
		this.gangwei_id = gangwei_id;
	}
	public Date getRuzhi_time() {
		return ruzhi_time;
	}
	public void setRuzhi_time(Date ruzhi_time) {
		this.ruzhi_time = ruzhi_time;
	}
	public String getXingming() {
		return xingming;
	}
	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	public String getXingbie() {
		return xingbie;
	}
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	public String getMinzu() {
		return minzu;
	}
	public void setMinzu(String minzu) {
		this.minzu = minzu;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getZhengzhi_mianmao() {
		return zhengzhi_mianmao;
	}
	public void setZhengzhi_mianmao(String zhengzhi_mianmao) {
		this.zhengzhi_mianmao = zhengzhi_mianmao;
	}
	public String getHunyin_state() {
		return hunyin_state;
	}
	public void setHunyin_state(String hunyin_state) {
		this.hunyin_state = hunyin_state;
	}
	public String getZuigao_xueli() {
		return zuigao_xueli;
	}
	public void setZuigao_xueli(String zuigao_xueli) {
		this.zuigao_xueli = zuigao_xueli;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getHuji() {
		return huji;
	}
	public void setHuji(String huji) {
		this.huji = huji;
	}
	public String getNow_addr() {
		return now_addr;
	}
	public void setNow_addr(String now_addr) {
		this.now_addr = now_addr;
	}
	public String getGangwei_state() {
		return gangwei_state;
	}
	public void setGangwei_state(String gangwei_state) {
		this.gangwei_state = gangwei_state;
	}
	public Date getOa_employee_time() {
		return oa_employee_time;
	}
	public void setOa_employee_time(Date oa_employee_time) {
		this.oa_employee_time = oa_employee_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getEmployee_pic() {
		return employee_pic;
	}
	public void setEmployee_pic(String employee_pic) {
		this.employee_pic = employee_pic;
	}
	
}
