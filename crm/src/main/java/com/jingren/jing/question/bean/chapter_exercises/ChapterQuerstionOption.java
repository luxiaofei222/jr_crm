package com.jingren.jing.question.bean.chapter_exercises;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: ChapterQuerstionOption.java 
* @Package com.jingren.jing.question.bean.chapter_exercises 
* @Description: TODO 章节练习问题选项
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月10日 下午2:11:51 
* @version 网校+CRM系统 V1.0
 */
public class ChapterQuerstionOption implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer chapter_question_option_id;
	private Integer chapter_question_id;
	private String option_number;//选项序号
	private String option_content;//选项内容
	private String optioan_pic;//选项图片：如果选项有图片的话
	private Date option_time;
	private Integer duoxuan_type_jiexi;//解析状态;
	public Integer getChapter_question_option_id() {
		return chapter_question_option_id;
	}
	public void setChapter_question_option_id(Integer chapter_question_option_id) {
		this.chapter_question_option_id = chapter_question_option_id;
	}
	public Integer getChapter_question_id() {
		return chapter_question_id;
	}
	public void setChapter_question_id(Integer chapter_question_id) {
		this.chapter_question_id = chapter_question_id;
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
	public Integer getDuoxuan_type_jiexi() {
		return duoxuan_type_jiexi;
	}
	public void setDuoxuan_type_jiexi(Integer duoxuan_type_jiexi) {
		this.duoxuan_type_jiexi = duoxuan_type_jiexi;
	}

}
