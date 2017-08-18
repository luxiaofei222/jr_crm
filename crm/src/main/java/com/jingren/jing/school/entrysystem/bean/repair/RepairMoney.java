package com.jingren.jing.school.entrysystem.bean.repair;

import java.io.Serializable;
import java.util.Date;

/**
* @Title: RepairMoney.java 
* @Package com.jingren.jing.school.entrysystem.bean.repair 
* @Description: TODO 学员补费
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月12日 上午8:50:59 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class RepairMoney implements Serializable{
	
	private Integer repair_id;
	private String repair_name;//补费项目
	private String pay_money;//补费金额
	private Date pay_time;
	private Date repair_time;
	private Integer entry_info_id;
	private Integer entryplan_id;
	private Integer entrycondition_id;
	private String note;//备注
	private String paytype;//支付方式
	
	private String employee_name;//业务员
	private String bumen;//所属部门
	private String user_name; //学员姓名
	private String card_number;//身份证号
	private String leibie;//报考类别
	private String zhuanye;//报考专业
	private String qianfei;//初始欠费金额
	private String pay_time_str;//交费时间
	private String repair_time_str;//录入时间
	
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getRepair_id() {
		return repair_id;
	}
	public void setRepair_id(Integer repair_id) {
		this.repair_id = repair_id;
	}
	public String getRepair_name() {
		return repair_name;
	}
	public void setRepair_name(String repair_name) {
		this.repair_name = repair_name;
	}
	public String getPay_money() {
		return pay_money;
	}
	public void setPay_money(String pay_money) {
		this.pay_money = pay_money;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public Date getRepair_time() {
		return repair_time;
	}
	public void setRepair_time(Date repair_time) {
		this.repair_time = repair_time;
	}
	public Integer getEntry_info_id() {
		return entry_info_id;
	}
	public void setEntry_info_id(Integer entry_info_id) {
		this.entry_info_id = entry_info_id;
	}
	public Integer getEntryplan_id() {
		return entryplan_id;
	}
	public void setEntryplan_id(Integer entryplan_id) {
		this.entryplan_id = entryplan_id;
	}
	public Integer getEntrycondition_id() {
		return entrycondition_id;
	}
	public void setEntrycondition_id(Integer entrycondition_id) {
		this.entrycondition_id = entrycondition_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getLeibie() {
		return leibie;
	}
	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getQianfei() {
		return qianfei;
	}
	public void setQianfei(String qianfei) {
		this.qianfei = qianfei;
	}
	public String getPay_time_str() {
		return pay_time_str;
	}
	public void setPay_time_str(String pay_time_str) {
		this.pay_time_str = pay_time_str;
	}
	public String getRepair_time_str() {
		return repair_time_str;
	}
	public void setRepair_time_str(String repair_time_str) {
		this.repair_time_str = repair_time_str;
	}

}
