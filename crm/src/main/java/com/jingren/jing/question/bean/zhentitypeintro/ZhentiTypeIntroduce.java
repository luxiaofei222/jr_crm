package com.jingren.jing.question.bean.zhentitypeintro;

import java.io.Serializable;
import java.util.List;

import com.jingren.jing.question.bean.zhentiquestion.ZhentiQuestion;
import com.jingren.jing.question.bean.zhentirecord.ZhentiRecord;
/**
* @Title: ZhentiTypeIntroduce.java 
* @Package com.jingren.jing.question.bean.zhentitypeintro 
* @Description: TODO 历年真题-题型分数介绍
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月9日 下午1:03:04 
* @version 网校+CRM系统 V1.0
 */
public class ZhentiTypeIntroduce implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer introduce_id;
	private Integer zhenti_id;
	private String introduce_content;//介绍
	private String question_type;//题型
	private List<ZhentiQuestion> zhentiQuestions;//真题题目
	private List<ZhentiRecord> zhentiRecords;//答题记录
	
	public List<ZhentiRecord> getZhentiRecords() {
		return zhentiRecords;
	}
	public void setZhentiRecords(List<ZhentiRecord> zhentiRecords) {
		this.zhentiRecords = zhentiRecords;
	}
	public Integer getIntroduce_id() {
		return introduce_id;
	}
	public void setIntroduce_id(Integer introduce_id) {
		this.introduce_id = introduce_id;
	}
	public Integer getZhenti_id() {
		return zhenti_id;
	}
	public void setZhenti_id(Integer zhenti_id) {
		this.zhenti_id = zhenti_id;
	}
	public String getIntroduce_content() {
		return introduce_content;
	}
	public void setIntroduce_content(String introduce_content) {
		this.introduce_content = introduce_content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public List<ZhentiQuestion> getZhentiQuestions() {
		return zhentiQuestions;
	}
	public void setZhentiQuestions(List<ZhentiQuestion> zhentiQuestions) {
		this.zhentiQuestions = zhentiQuestions;
	}

}
