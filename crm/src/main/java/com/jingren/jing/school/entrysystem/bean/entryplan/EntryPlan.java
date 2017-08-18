package com.jingren.jing.school.entrysystem.bean.entryplan;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.common.university.bean.Review;

/**
* @Title: EntryPlan.java 
* @Package com.jingren.jing.school.entrysystem.bean.entryplan 
* @Description: TODO 报名计划
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月21日 上午10:21:23 
* @version 网校+CRM系统 V1.0
 */
public class EntryPlan implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer entryplan_id;
	private String entryplan_content;//报名计划内容
	private String entryplan_explain;//计划相关说明
	private Date validity_time;//有效日期
	private Integer parent_id;//父ID
	private Integer course_id;
	private Date entryplan_time;
	private String time_type;//计划是否过期
	private Integer baomingnumber;//报名人数
	private String is_show;//显示，隐藏
	
	private String baomingfei;
	private String jiaocaifei;
	private String putongbanfei;
	private String jingjiangbanfei;
	private String coursename;//分类名称
	private String chengkao_nian;//每年学费
	private String chengkao_zong;//总学费
	private List<EntryPlan> entryPlans;//计划列表
	private Integer review_id;
	private Review review;//职称评审
	private Integer university_id;//成考大学
	
	public Integer getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(Integer university_id) {
		this.university_id = university_id;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public Integer getReview_id() {
		return review_id;
	}
	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}
	public String getChengkao_nian() {
		return chengkao_nian;
	}
	public void setChengkao_nian(String chengkao_nian) {
		this.chengkao_nian = chengkao_nian;
	}
	public String getChengkao_zong() {
		return chengkao_zong;
	}
	public void setChengkao_zong(String chengkao_zong) {
		this.chengkao_zong = chengkao_zong;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public Integer getEntryplan_id() {
		return entryplan_id;
	}
	public void setEntryplan_id(Integer entryplan_id) {
		this.entryplan_id = entryplan_id;
	}
	public String getEntryplan_content() {
		return entryplan_content;
	}
	public void setEntryplan_content(String entryplan_content) {
		this.entryplan_content = entryplan_content;
	}
	public String getEntryplan_explain() {
		return entryplan_explain;
	}
	public void setEntryplan_explain(String entryplan_explain) {
		this.entryplan_explain = entryplan_explain;
	}
	public Date getValidity_time() {
		return validity_time;
	}
	public void setValidity_time(Date validity_time) {
		this.validity_time = validity_time;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Date getEntryplan_time() {
		return entryplan_time;
	}
	public void setEntryplan_time(Date entryplan_time) {
		this.entryplan_time = entryplan_time;
	}
	public String getTime_type() {
		return time_type;
	}
	public void setTime_type(String time_type) {
		this.time_type = time_type;
	}
	public Integer getBaomingnumber() {
		return baomingnumber;
	}
	public void setBaomingnumber(Integer baomingnumber) {
		this.baomingnumber = baomingnumber;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public String getBaomingfei() {
		return baomingfei;
	}
	public void setBaomingfei(String baomingfei) {
		this.baomingfei = baomingfei;
	}
	public String getJiaocaifei() {
		return jiaocaifei;
	}
	public void setJiaocaifei(String jiaocaifei) {
		this.jiaocaifei = jiaocaifei;
	}
	public String getPutongbanfei() {
		return putongbanfei;
	}
	public void setPutongbanfei(String putongbanfei) {
		this.putongbanfei = putongbanfei;
	}
	public String getJingjiangbanfei() {
		return jingjiangbanfei;
	}
	public void setJingjiangbanfei(String jingjiangbanfei) {
		this.jingjiangbanfei = jingjiangbanfei;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<EntryPlan> getEntryPlans() {
		return entryPlans;
	}
	public void setEntryPlans(List<EntryPlan> entryPlans) {
		this.entryPlans = entryPlans;
	}

}
