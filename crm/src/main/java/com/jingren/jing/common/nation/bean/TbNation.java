package com.jingren.jing.common.nation.bean;

import java.io.Serializable;

/**
* @Title: TbNation.java 
* @Package com.jingren.jing.common.nation.bean 
* @Description: TODO 56个民族
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月26日 上午11:20:37 
* @version 网校+CRM系统 V1.0
 */
public class TbNation implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nationid;
	private String nation;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNationid() {
		return nationid;
	}
	public void setNationid(String nationid) {
		this.nationid = nationid;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	
}
