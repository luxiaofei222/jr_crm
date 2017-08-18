package com.jingren.jing.school.bean.dictionary;

import java.io.Serializable;
/**
 * 数据字典和课程中间表
* @Title: CourseDictionary.java 
* @Package com.jingren.jing.school.bean.dictionary 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月11日 下午5:50:07 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class CourseDictionary implements Serializable{

	private Integer course_id;
	private Integer dictionary_id;
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	
}
