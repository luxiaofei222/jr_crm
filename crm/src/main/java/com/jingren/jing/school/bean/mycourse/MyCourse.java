package com.jingren.jing.school.bean.mycourse;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.video.VideoRecord;
/**
 * 我的课程
* @Title: MyCourse.java 
* @Package com.jingren.jing.school.bean.mycourse 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月21日 下午1:07:10 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class MyCourse implements Serializable{

	private Integer my_course_id;
	private Integer video_id;
	private Integer user_id;
	private Date my_course_time;
	private CourseVideo courseVideo;//课程信息
	private VideoRecord videoRecord;//视频播放记录
	private Date daoqi_time;//到期时间
	private Integer tianshu;//距离结束还有多少天
	
	private Integer course_id;
	private Integer course_parent_id;
	private Integer employee_id;
	private String note;//备注
	private String employee_name;
	
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public Integer getTianshu() {
		return tianshu;
	}
	public void setTianshu(Integer tianshu) {
		this.tianshu = tianshu;
	}
	public Date getDaoqi_time() {
		return daoqi_time;
	}
	public void setDaoqi_time(Date daoqi_time) {
		this.daoqi_time = daoqi_time;
	}
	public VideoRecord getVideoRecord() {
		return videoRecord;
	}
	public void setVideoRecord(VideoRecord videoRecord) {
		this.videoRecord = videoRecord;
	}
	public Integer getMy_course_id() {
		return my_course_id;
	}
	public void setMy_course_id(Integer my_course_id) {
		this.my_course_id = my_course_id;
	}
	public Integer getVideo_id() {
		return video_id;
	}
	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getMy_course_time() {
		return my_course_time;
	}
	public void setMy_course_time(Date my_course_time) {
		this.my_course_time = my_course_time;
	}
	public CourseVideo getCourseVideo() {
		return courseVideo;
	}
	public void setCourseVideo(CourseVideo courseVideo) {
		this.courseVideo = courseVideo;
	}
	
}
