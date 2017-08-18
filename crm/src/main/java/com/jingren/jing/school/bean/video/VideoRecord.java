package com.jingren.jing.school.bean.video;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.course.CourseVideo;

@SuppressWarnings("serial")
public class VideoRecord implements Serializable{

	
	private Integer record_id;
	private Integer user_id;
	private String record_time;//观看时长,单位：秒
	private Integer video_id;
	private Integer course_id;
	private Integer video_parent_id;
	private Date paly_time;
	private Integer video_parent;//视频第一级ID
	private String zong_shichang;//总时长
	private CourseVideo courseVideo;
	private CourseVideo parent_video;
	
	public CourseVideo getCourseVideo() {
		return courseVideo;
	}
	public void setCourseVideo(CourseVideo courseVideo) {
		this.courseVideo = courseVideo;
	}
	public CourseVideo getParent_video() {
		return parent_video;
	}
	public void setParent_video(CourseVideo parent_video) {
		this.parent_video = parent_video;
	}
	public Integer getVideo_parent() {
		return video_parent;
	}
	public void setVideo_parent(Integer video_parent) {
		this.video_parent = video_parent;
	}
	public String getZong_shichang() {
		return zong_shichang;
	}
	public void setZong_shichang(String zong_shichang) {
		this.zong_shichang = zong_shichang;
	}
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getRecord_time() {
		return record_time;
	}
	public void setRecord_time(String record_time) {
		this.record_time = record_time;
	}
	public Integer getVideo_id() {
		return video_id;
	}
	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public Integer getVideo_parent_id() {
		return video_parent_id;
	}
	public void setVideo_parent_id(Integer video_parent_id) {
		this.video_parent_id = video_parent_id;
	}
	public Date getPaly_time() {
		return paly_time;
	}
	public void setPaly_time(Date paly_time) {
		this.paly_time = paly_time;
	}
	
}
