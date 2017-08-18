package com.jingren.jing.school.bean.clearance;

import java.io.Serializable;

import com.jingren.jing.school.bean.course.CourseVideo;
/**
* @Title: ClearanceVideo.java 
* @Package com.jingren.jing.school.bean.clearance 
* @Description: TODO 套餐课程
* @author 鲁晓飞 MR.Lu   
* @date 2017年8月2日 下午3:04:51 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class ClearanceVideo implements Serializable{

	private Integer clear_video_id;
	private Integer clearance_id;
	private Integer video_id;
	private String video_name;//课程名称
	private  String video_price;//视频价格
	
	private CourseVideo courseVideo;
	
	public CourseVideo getCourseVideo() {
		return courseVideo;
	}
	public void setCourseVideo(CourseVideo courseVideo) {
		this.courseVideo = courseVideo;
	}
	public Integer getClear_video_id() {
		return clear_video_id;
	}
	public void setClear_video_id(Integer clear_video_id) {
		this.clear_video_id = clear_video_id;
	}
	public Integer getClearance_id() {
		return clearance_id;
	}
	public void setClearance_id(Integer clearance_id) {
		this.clearance_id = clearance_id;
	}
	public Integer getVideo_id() {
		return video_id;
	}
	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}
	public String getVideo_name() {
		return video_name;
	}
	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}
	public String getVideo_price() {
		return video_price;
	}
	public void setVideo_price(String video_price) {
		this.video_price = video_price;
	}
	
}
