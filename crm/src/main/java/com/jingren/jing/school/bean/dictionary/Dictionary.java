package com.jingren.jing.school.bean.dictionary;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.school.bean.course.CourseInfo;

/**
 * 数据字典
* @Title: Dictionary.java 
* @Package com.jingren.jing.school.bean.dictionary 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月11日 下午5:47:42 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Dictionary implements Serializable{

	private Integer dictionary_id;
	private String dictionary_name;//字典名称
	private Integer course_id;//课程ID
	private Integer dictionary_parent_id;
	private String dic_dis;//字典描述
	private Date dic_time;
	private List<CourseInfo> courseInfos;//相关课程资讯列表
	private List<CourseInfo> hot_courseInfos;//热点推荐的资讯列表
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	public String getDictionary_name() {
		return dictionary_name;
	}
	public void setDictionary_name(String dictionary_name) {
		this.dictionary_name = dictionary_name;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Integer getDictionary_parent_id() {
		return dictionary_parent_id;
	}
	public void setDictionary_parent_id(Integer dictionary_parent_id) {
		this.dictionary_parent_id = dictionary_parent_id;
	}
	public String getDic_dis() {
		return dic_dis;
	}
	public void setDic_dis(String dic_dis) {
		this.dic_dis = dic_dis;
	}
	public Date getDic_time() {
		return dic_time;
	}
	public void setDic_time(Date dic_time) {
		this.dic_time = dic_time;
	}
	public List<CourseInfo> getCourseInfos() {
		return courseInfos;
	}
	public void setCourseInfos(List<CourseInfo> courseInfos) {
		this.courseInfos = courseInfos;
	}
	public List<CourseInfo> getHot_courseInfos() {
		return hot_courseInfos;
	}
	public void setHot_courseInfos(List<CourseInfo> hot_courseInfos) {
		this.hot_courseInfos = hot_courseInfos;
	}
	
}
