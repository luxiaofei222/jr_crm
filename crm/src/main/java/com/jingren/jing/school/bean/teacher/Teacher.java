package com.jingren.jing.school.bean.teacher;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.course.CourseMenu;

@SuppressWarnings("serial")
public class Teacher implements Serializable{
	private Integer teacher_id;
	private String teacher_name;//教师姓名
	private String teacher_sex;
	private String teacher_pic;
	private Integer course_id;//教授课程
	private String teacher_dis;
	private Date teacher_time;
	private String teacher_phone;//老师手机号
	private Integer course_parent_id;//课程一级ID
	private CourseMenu courseMenu;//课程
	private String teacher_course;//授课课程
	public Integer getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public String getTeacher_sex() {
		return teacher_sex;
	}
	public void setTeacher_sex(String teacher_sex) {
		this.teacher_sex = teacher_sex;
	}
	public String getTeacher_pic() {
		return teacher_pic;
	}
	public void setTeacher_pic(String teacher_pic) {
		this.teacher_pic = teacher_pic;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public String getTeacher_dis() {
		return teacher_dis;
	}
	public void setTeacher_dis(String teacher_dis) {
		this.teacher_dis = teacher_dis;
	}
	public Date getTeacher_time() {
		return teacher_time;
	}
	public void setTeacher_time(Date teacher_time) {
		this.teacher_time = teacher_time;
	}
	public CourseMenu getCourseMenu() {
		return courseMenu;
	}
	public void setCourseMenu(CourseMenu courseMenu) {
		this.courseMenu = courseMenu;
	}
	public String getTeacher_phone() {
		return teacher_phone;
	}
	public void setTeacher_phone(String teacher_phone) {
		this.teacher_phone = teacher_phone;
	}
	public Integer getCourse_parent_id() {
		return course_parent_id;
	}
	public void setCourse_parent_id(Integer course_parent_id) {
		this.course_parent_id = course_parent_id;
	}
	public String getTeacher_course() {
		return teacher_course;
	}
	public void setTeacher_course(String teacher_course) {
		this.teacher_course = teacher_course;
	}
	
}
