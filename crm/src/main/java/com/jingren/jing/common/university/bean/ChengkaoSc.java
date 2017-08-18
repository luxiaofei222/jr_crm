package com.jingren.jing.common.university.bean;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: ChengkaoSc.java 
* @Package com.jingren.jing.common.university.bean 
* @Description: TODO 成考学校
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月9日 上午10:38:56 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class ChengkaoSc implements Serializable{

	private Integer chengkao_id;
	private String chengkao_name;//名称
	private Integer parent_id;
	private String daima;//专业代码
	private String kelei;//科类
	private String cengci;//报考层次
	private String xuezhi;//学制
	private String study_xingshi;//学习形式
	private Date chengkao_time;
	public Integer getChengkao_id() {
		return chengkao_id;
	}
	public void setChengkao_id(Integer chengkao_id) {
		this.chengkao_id = chengkao_id;
	}
	public String getChengkao_name() {
		return chengkao_name;
	}
	public void setChengkao_name(String chengkao_name) {
		this.chengkao_name = chengkao_name;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getDaima() {
		return daima;
	}
	public void setDaima(String daima) {
		this.daima = daima;
	}
	public String getKelei() {
		return kelei;
	}
	public void setKelei(String kelei) {
		this.kelei = kelei;
	}
	public String getCengci() {
		return cengci;
	}
	public void setCengci(String cengci) {
		this.cengci = cengci;
	}
	public String getXuezhi() {
		return xuezhi;
	}
	public void setXuezhi(String xuezhi) {
		this.xuezhi = xuezhi;
	}
	public String getStudy_xingshi() {
		return study_xingshi;
	}
	public void setStudy_xingshi(String study_xingshi) {
		this.study_xingshi = study_xingshi;
	}
	public Date getChengkao_time() {
		return chengkao_time;
	}
	public void setChengkao_time(Date chengkao_time) {
		this.chengkao_time = chengkao_time;
	}
	
}
