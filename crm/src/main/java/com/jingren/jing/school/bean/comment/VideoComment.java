package com.jingren.jing.school.bean.comment;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.user.User;

/**
 * 课程评论
* @Title: VideoComment.java 
* @Package com.jingren.jing.school.bean.comment 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月8日 下午2:02:57 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class VideoComment implements Serializable{
	
	private Integer v_comment_id;
	private String v_comment_content;
	private Integer video_id;
	private Integer user_id;
	private Date v_comment_time;
	private Integer parent_id;
	private String v_comment_ip;
	private User user;
	private Integer pingfen;//评分
	private CourseVideo courseVideo;
	public Integer getV_comment_id() {
		return v_comment_id;
	}
	public void setV_comment_id(Integer v_comment_id) {
		this.v_comment_id = v_comment_id;
	}
	public String getV_comment_content() {
		return v_comment_content;
	}
	public void setV_comment_content(String v_comment_content) {
		this.v_comment_content = v_comment_content;
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
	public Date getV_comment_time() {
		return v_comment_time;
	}
	public void setV_comment_time(Date v_comment_time) {
		this.v_comment_time = v_comment_time;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getV_comment_ip() {
		return v_comment_ip;
	}
	public void setV_comment_ip(String v_comment_ip) {
		this.v_comment_ip = v_comment_ip;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getPingfen() {
		return pingfen;
	}
	public void setPingfen(Integer pingfen) {
		this.pingfen = pingfen;
	}
	public CourseVideo getCourseVideo() {
		return courseVideo;
	}
	public void setCourseVideo(CourseVideo courseVideo) {
		this.courseVideo = courseVideo;
	}

}
