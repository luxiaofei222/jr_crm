package com.jingren.jing.school.bean.course;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程资讯
* @Title: CourseInfo.java 
* @Package com.jingren.jing.school.bean.course 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月4日 上午8:30:16 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class CourseInfo implements Serializable{

	private Integer info_id;
	private String info_type;//资讯类型:'考试动态','课程详解','报考指南'
	private String info_title;
	private Integer course_id;
	private String info_content;
	private Date info_time;
	private String info_user;//责任编辑
	private String info_laiyuan;
	private String meta_title;
	private String meta_dis;
	private String meta_key;
	private String info_zhaiyao;//资讯摘要
	private Integer parent_course_id;//课程一级ID
	private Integer dictionary_id;//等级ID
	private String is_hot;//是否是热点推荐
	private String is_dic;//是否课程相关
	public Integer getInfo_id() {
		return info_id;
	}
	public void setInfo_id(Integer info_id) {
		this.info_id = info_id;
	}
	public String getInfo_type() {
		return info_type;
	}
	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}
	public String getInfo_title() {
		return info_title;
	}
	public void setInfo_title(String info_title) {
		this.info_title = info_title;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public String getInfo_content() {
		return info_content;
	}
	public void setInfo_content(String info_content) {
		this.info_content = info_content;
	}
	public Date getInfo_time() {
		return info_time;
	}
	public void setInfo_time(Date info_time) {
		this.info_time = info_time;
	}
	public String getInfo_user() {
		return info_user;
	}
	public void setInfo_user(String info_user) {
		this.info_user = info_user;
	}
	public String getInfo_laiyuan() {
		return info_laiyuan;
	}
	public void setInfo_laiyuan(String info_laiyuan) {
		this.info_laiyuan = info_laiyuan;
	}
	public String getMeta_title() {
		return meta_title;
	}
	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}
	public String getMeta_dis() {
		return meta_dis;
	}
	public void setMeta_dis(String meta_dis) {
		this.meta_dis = meta_dis;
	}
	public String getMeta_key() {
		return meta_key;
	}
	public void setMeta_key(String meta_key) {
		this.meta_key = meta_key;
	}
	public String getInfo_zhaiyao() {
		return info_zhaiyao;
	}
	public void setInfo_zhaiyao(String info_zhaiyao) {
		this.info_zhaiyao = info_zhaiyao;
	}
	public Integer getParent_course_id() {
		return parent_course_id;
	}
	public void setParent_course_id(Integer parent_course_id) {
		this.parent_course_id = parent_course_id;
	}
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	public String getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(String is_hot) {
		this.is_hot = is_hot;
	}
	public String getIs_dic() {
		return is_dic;
	}
	public void setIs_dic(String is_dic) {
		this.is_dic = is_dic;
	}
	
}
