package com.jingren.jing.common.university.bean;

import java.io.Serializable;
/**
* @Title: University.java 
* @Package com.jingren.jing.common.university.bean 
* @Description: TODO 大学专业
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月16日 下午4:51:23 
* @version 网校+CRM系统 V1.0
 */
public class University implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer university_id;
	private String university_name;//大学名称
	private String university_zhuanye;//专业
	private Integer parent_id;
	private String university_type;//专升本、高起专
	private String university_kinds;//科类
	private String xuezhi;//学制
	private Integer xuefei;//学费
	
	public Integer getXuefei() {
		return xuefei;
	}
	public void setXuefei(Integer xuefei) {
		this.xuefei = xuefei;
	}
	public String getUniversity_kinds() {
		return university_kinds;
	}
	public void setUniversity_kinds(String university_kinds) {
		this.university_kinds = university_kinds;
	}
	public String getXuezhi() {
		return xuezhi;
	}
	public void setXuezhi(String xuezhi) {
		this.xuezhi = xuezhi;
	}
	public Integer getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(Integer university_id) {
		this.university_id = university_id;
	}
	public String getUniversity_name() {
		return university_name;
	}
	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}
	public String getUniversity_zhuanye() {
		return university_zhuanye;
	}
	public void setUniversity_zhuanye(String university_zhuanye) {
		this.university_zhuanye = university_zhuanye;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getUniversity_type() {
		return university_type;
	}
	public void setUniversity_type(String university_type) {
		this.university_type = university_type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
