package com.jingren.jing.question.bean.question_course;

import java.io.Serializable;
import java.util.Date;

public class QuestionCourse implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer question_course_id;
	private Integer course_id;
	private Integer course_parent_id;
	private String question_course_name;//题库课程分类名称
	private String question_course_dis;
	private Date question_course_time;
	private String is_show;//显示或者隐藏
	private Integer question_chapter_number;//章节练习数量
	
	private Integer zhenti_number;//真题的数量
	public Integer getQuestion_course_id() {
		return question_course_id;
	}
	public void setQuestion_course_id(Integer question_course_id) {
		this.question_course_id = question_course_id;
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
	public String getQuestion_course_name() {
		return question_course_name;
	}
	public void setQuestion_course_name(String question_course_name) {
		this.question_course_name = question_course_name;
	}
	public String getQuestion_course_dis() {
		return question_course_dis;
	}
	public void setQuestion_course_dis(String question_course_dis) {
		this.question_course_dis = question_course_dis;
	}
	public Date getQuestion_course_time() {
		return question_course_time;
	}
	public void setQuestion_course_time(Date question_course_time) {
		this.question_course_time = question_course_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public Integer getQuestion_chapter_number() {
		return question_chapter_number;
	}
	public void setQuestion_chapter_number(Integer question_chapter_number) {
		this.question_chapter_number = question_chapter_number;
	}
	public Integer getZhenti_number() {
		return zhenti_number;
	}
	public void setZhenti_number(Integer zhenti_number) {
		this.zhenti_number = zhenti_number;
	}

}
