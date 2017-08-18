package com.jingren.jing.school.bean.myshoping;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.course.CourseVideo;
/**
 * 我的购物车
* @Title: MyShoping.java 
* @Package com.jingren.jing.school.bean.myshoping 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月21日 下午2:12:15 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class MyShoping implements Serializable{
	private Integer my_shoping_id;
	private Integer video_id;
	private Integer user_id;
	private Date my_shoping_time;
	private CourseVideo courseVideo;//关联的课程
	public Integer getMy_shoping_id() {
		return my_shoping_id;
	}
	public void setMy_shoping_id(Integer my_shoping_id) {
		this.my_shoping_id = my_shoping_id;
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
	public Date getMy_shoping_time() {
		return my_shoping_time;
	}
	public void setMy_shoping_time(Date my_shoping_time) {
		this.my_shoping_time = my_shoping_time;
	}
	public CourseVideo getCourseVideo() {
		return courseVideo;
	}
	public void setCourseVideo(CourseVideo courseVideo) {
		this.courseVideo = courseVideo;
	}
	
}
