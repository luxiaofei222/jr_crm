package com.jingren.jing.question.bean.zhentioption;

import java.io.Serializable;
import java.util.Date;

public class ZhentiQuestionOption implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer option_id;
	private Integer zhenti_question_id;
	private String option_number;
	private String option_content;
	private String optioan_pic;
	private Date option_time;
	private Integer is_dati;//是否选中此选项
	
	public Integer getIs_dati() {
		return is_dati;
	}
	public void setIs_dati(Integer is_dati) {
		this.is_dati = is_dati;
	}
	public Integer getOption_id() {
		return option_id;
	}
	public void setOption_id(Integer option_id) {
		this.option_id = option_id;
	}
	public Integer getZhenti_question_id() {
		return zhenti_question_id;
	}
	public void setZhenti_question_id(Integer zhenti_question_id) {
		this.zhenti_question_id = zhenti_question_id;
	}
	public String getOption_number() {
		return option_number;
	}
	public void setOption_number(String option_number) {
		this.option_number = option_number;
	}
	public String getOption_content() {
		return option_content;
	}
	public void setOption_content(String option_content) {
		this.option_content = option_content;
	}
	public String getOptioan_pic() {
		return optioan_pic;
	}
	public void setOptioan_pic(String optioan_pic) {
		this.optioan_pic = optioan_pic;
	}
	public Date getOption_time() {
		return option_time;
	}
	public void setOption_time(Date option_time) {
		this.option_time = option_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
