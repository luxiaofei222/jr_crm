package com.jingren.jing.educational.bean.family;

import java.io.Serializable;

/**
* @Title: EntryFamily.java 
* @Package com.jingren.jing.educational.bean.family 
* @Description: TODO 报名信息-个人家庭成员信息
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月17日 上午8:05:42 
* @version 网校+CRM系统 V1.0
 */
public class EntryFamily implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer family_id;
	private Integer entry_info_id;
	private String family_name;//姓名
	private String guanxi;//与本人关系
	private String nianling;
	private String gongzuodanwei;//工作单位
	public Integer getFamily_id() {
		return family_id;
	}
	public void setFamily_id(Integer family_id) {
		this.family_id = family_id;
	}
	public Integer getEntry_info_id() {
		return entry_info_id;
	}
	public void setEntry_info_id(Integer entry_info_id) {
		this.entry_info_id = entry_info_id;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getGuanxi() {
		return guanxi;
	}
	public void setGuanxi(String guanxi) {
		this.guanxi = guanxi;
	}
	public String getNianling() {
		return nianling;
	}
	public void setNianling(String nianling) {
		this.nianling = nianling;
	}
	public String getGongzuodanwei() {
		return gongzuodanwei;
	}
	public void setGongzuodanwei(String gongzuodanwei) {
		this.gongzuodanwei = gongzuodanwei;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
