package com.jingren.jing.common.university.bean;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: Review.java 
* @Package com.jingren.jing.common.university.bean 
* @Description: TODO 职称评审三级分类
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月8日 下午1:55:50 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Review implements Serializable{

	private Integer review_id;
	private Integer course_id;
	private Integer course_parent_id;
	private String review_name;
	private Date review_time;
	public Integer getReview_id() {
		return review_id;
	}
	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Integer getCourse_parent_id() {
		return course_parent_id;
	}
	public void setCourse_parent_id(Integer course_parent_id) {
		this.course_parent_id = course_parent_id;
	}
	public String getReview_name() {
		return review_name;
	}
	public void setReview_name(String review_name) {
		this.review_name = review_name;
	}
	public Date getReview_time() {
		return review_time;
	}
	public void setReview_time(Date review_time) {
		this.review_time = review_time;
	}
	
}
