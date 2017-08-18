package com.jingren.jing.school.bean.clearance;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.jingren.jing.school.bean.course.CourseMenu;
/**
* @Title: Clearance.java 
* @Package com.jingren.jing.school.bean.clearance 
* @Description: TODO 通关方案
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月12日 下午5:34:17 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Clearance implements Serializable{

	private Integer clearance_id;
	private String clearance_name;
	private String clearance_price;//价格
	private String clearrance_baohan;//包含
	private String clearrance_tese;
	private String clearrance_baozhang;
	private Date clearrance_time;
	private Integer course_id;
	private List<String> tese;//特色列表
	private CourseMenu courseMenu;
	private Integer  course_parent_id;
	
	private Integer new_price;//最新价格
	private Date daoqi_time;//到期时间
	private String dengji;//等级
	
	private List<ClearanceVideo> clearanceVideos;
	
	public String getDengji() {
		return dengji;
	}
	public void setDengji(String dengji) {
		dengji.replace(" ", "");
		this.dengji = dengji;
	}
	public List<ClearanceVideo> getClearanceVideos() {
		return clearanceVideos;
	}
	public void setClearanceVideos(List<ClearanceVideo> clearanceVideos) {
		this.clearanceVideos = clearanceVideos;
	}
	public Integer getNew_price() {
		return new_price;
	}
	public void setNew_price(Integer new_price) {
		this.new_price = new_price;
	}
	public Date getDaoqi_time() {
		return daoqi_time;
	}
	public void setDaoqi_time(Date daoqi_time) {
		this.daoqi_time = daoqi_time;
	}
	public Integer getClearance_id() {
		return clearance_id;
	}
	public void setClearance_id(Integer clearance_id) {
		this.clearance_id = clearance_id;
	}
	public String getClearance_name() {
		return clearance_name;
	}
	public void setClearance_name(String clearance_name) {
		this.clearance_name = clearance_name;
	}
	public String getClearance_price() {
		return clearance_price;
	}
	public void setClearance_price(String clearance_price) {
		this.clearance_price = clearance_price;
	}
	public String getClearrance_baohan() {
		return clearrance_baohan;
	}
	public void setClearrance_baohan(String clearrance_baohan) {
		this.clearrance_baohan = clearrance_baohan;
	}
	public String getClearrance_tese() {
		
		return clearrance_tese;
	}
	public void setClearrance_tese(String clearrance_tese) {
		this.clearrance_tese = clearrance_tese.replaceAll("，", ",");
	}
	public String getClearrance_baozhang() {
		return clearrance_baozhang;
	}
	public void setClearrance_baozhang(String clearrance_baozhang) {
		this.clearrance_baozhang = clearrance_baozhang;
	}
	public Date getClearrance_time() {
		return clearrance_time;
	}
	public void setClearrance_time(Date clearrance_time) {
		this.clearrance_time = clearrance_time;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public List<String> getTese() {
		String[] array=clearrance_tese.split(",");
		LinkedList<String> hh=new LinkedList<String>();
		for (String string : array) {
			hh.add(string);
		}
		tese=hh;
		return tese;
	}
	public void setTese(List<String> tese) {
		this.tese = tese;
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
