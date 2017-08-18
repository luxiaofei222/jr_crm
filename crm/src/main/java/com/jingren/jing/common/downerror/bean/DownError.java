package com.jingren.jing.common.downerror.bean;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.user.User;
/**
* @Title: DownError.java 
* @Package com.jingren.jing.common.downerror 
* @Description: TODO 用户下载视频次数，每月一日清除
* @author 鲁晓飞 MR.Lu   
* @date 2017年4月13日 下午1:27:58 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class DownError implements Serializable{

	private Integer user_id;
	private String user_ip;
	private String down_type; //下载渠道
	private Date down_time;
	private Integer video_id;
	private User user;
	private CourseVideo courseVideo;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CourseVideo getCourseVideo() {
		return courseVideo;
	}
	public void setCourseVideo(CourseVideo courseVideo) {
		this.courseVideo = courseVideo;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getDown_type() {
		return down_type;
	}
	public void setDown_type(String down_type) {
		this.down_type = down_type;
	}
	public Date getDown_time() {
		return down_time;
	}
	public void setDown_time(Date down_time) {
		this.down_time = down_time;
	}
	public Integer getVideo_id() {
		return video_id;
	}
	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}
	
}
