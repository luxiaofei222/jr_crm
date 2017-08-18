package com.jingren.jing.school.entrysystem.bean.entryplace;

import java.io.Serializable;

/**
* @Title: EntryPlace.java 
* @Package com.jingren.jing.school.entrysystem.bean.entryplace 
* @Description: TODO 报名地点
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月21日 上午10:34:49 
* @version 网校+CRM系统 V1.0
 */
public class EntryPlace  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer entryplace_id;
	private String entryplace_name;//报名地点
	private Integer parent_id;
	private Integer entryplace_leavel;
	public Integer getEntryplace_id() {
		return entryplace_id;
	}
	public void setEntryplace_id(Integer entryplace_id) {
		this.entryplace_id = entryplace_id;
	}
	public String getEntryplace_name() {
		return entryplace_name;
	}
	public void setEntryplace_name(String entryplace_name) {
		this.entryplace_name = entryplace_name;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getEntryplace_leavel() {
		return entryplace_leavel;
	}
	public void setEntryplace_leavel(Integer entryplace_leavel) {
		this.entryplace_leavel = entryplace_leavel;
	}
	
}
