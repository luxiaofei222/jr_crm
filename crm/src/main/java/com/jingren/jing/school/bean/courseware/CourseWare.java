package com.jingren.jing.school.bean.courseware;

import java.io.Serializable;
import java.util.Date;

/**
 * 课件
* @Title: CourseWare.java 
* @Package com.jingren.jing.school.bean.courseware 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月28日 上午9:38:32 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class CourseWare implements Serializable{

	private Integer courseware_id;
	private String courseware_name;//课件名
	private Integer video_id;
	private String courseware_file;
	private Date courseware_time;
	private Integer top_paixu;
	public Integer getCourseware_id() {
		return courseware_id;
	}
	public void setCourseware_id(Integer courseware_id) {
		this.courseware_id = courseware_id;
	}
	public String getCourseware_name() {
		return courseware_name;
	}
	public void setCourseware_name(String courseware_name) {
		this.courseware_name = courseware_name;
	}
	public Integer getVideo_id() {
		return video_id;
	}
	public void setVideo_id(Integer video_id) {
		this.video_id = video_id;
	}
	public String getCourseware_file() {
		return courseware_file;
	}
	public void setCourseware_file(String courseware_file) {
		this.courseware_file = courseware_file;
	}
	public Date getCourseware_time() {
		return courseware_time;
	}
	public void setCourseware_time(Date courseware_time) {
		this.courseware_time = courseware_time;
	}
	public Integer getTop_paixu() {
		return top_paixu;
	}
	public void setTop_paixu(Integer top_paixu) {
		this.top_paixu = top_paixu;
	}
	
}
