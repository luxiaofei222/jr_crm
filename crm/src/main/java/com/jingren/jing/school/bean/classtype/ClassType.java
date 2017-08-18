package com.jingren.jing.school.bean.classtype;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.course.CourseMenu;
/**
* @Title: ClassType.java 
* @Package com.jingren.jing.school.bean.classtype 
* @Description: TODO 班型
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月12日 下午5:12:25 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class ClassType implements Serializable{

	private Integer class_id;
	private Integer dictionary_id;
	private Integer course_id;
	private String class_name;
	private String class_feature;//班型特色
	private String neirong_feature;//内容特色
	private Date class_time;
	private String class_beizhu;//班级备注
	private CourseMenu courseMenu;//课程信息
	private Integer course_parent_id;
	public Integer getClass_id() {
		return class_id;
	}
	public void setClass_id(Integer class_id) {
		this.class_id = class_id;
	}
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getClass_feature() {
		return class_feature;
	}
	public void setClass_feature(String class_feature) {
		this.class_feature = class_feature;
	}
	public String getNeirong_feature() {
		return neirong_feature;
	}
	public void setNeirong_feature(String neirong_feature) {
		this.neirong_feature = neirong_feature;
	}
	public Date getClass_time() {
		return class_time;
	}
	public void setClass_time(Date class_time) {
		this.class_time = class_time;
	}
	public String getClass_beizhu() {
		return class_beizhu;
	}
	public void setClass_beizhu(String class_beizhu) {
		this.class_beizhu = class_beizhu;
	}
	public CourseMenu getCourseMenu() {
		return courseMenu;
	}
	public void setCourseMenu(CourseMenu courseMenu) {
		this.courseMenu = courseMenu;
	}
	public Integer getCourse_parent_id() {
		return course_parent_id;
	}
	public void setCourse_parent_id(Integer course_parent_id) {
		this.course_parent_id = course_parent_id;
	}
	
}
