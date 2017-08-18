package com.jingren.jing.school.bean.courseware;

import java.io.Serializable;
import java.util.Date;
/**
 * 我的课件
* @Title: MyCourseWare.java 
* @Package com.jingren.jing.school.bean.courseware 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月28日 上午9:40:55 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class MyCourseWare implements Serializable{

	private Integer user_id;
	private Integer courseware_id;
	private Date add_time;
	private CourseWare courseWare;//课件
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getCourseware_id() {
		return courseware_id;
	}
	public void setCourseware_id(Integer courseware_id) {
		this.courseware_id = courseware_id;
	}
	public Date getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Date add_time) {
		this.add_time = add_time;
	}
	public CourseWare getCourseWare() {
		return courseWare;
	}
	public void setCourseWare(CourseWare courseWare) {
		this.courseWare = courseWare;
	}
	
}
