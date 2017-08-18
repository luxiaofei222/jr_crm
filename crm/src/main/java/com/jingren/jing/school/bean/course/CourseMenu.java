package com.jingren.jing.school.bean.course;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 课程目录
* @Title: CourseMenu.java 
* @Package com.jingren.jing.school.bean.course 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月4日 上午8:15:17 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class CourseMenu implements Serializable{
	
	private Integer course_id;
	private String course_name;
	private Integer course_parent_id;
	private String course_link;
	private String course_icon;
	private Integer course_leavl;
	private String course_dis;
	private Date course_time;
	private String course_index_pic;
	private List<CourseMenu> sub_list;
	private List<CourseVideo> courseVideos;//对应的课程视频列表
	private CourseMenu courseMenus_video;//默认显示的首页中部课程数据
	private String sub_info_name;//课程咨询所属分类名称
	private List<CourseInfo> courseInfos;//首页默认显示课程资讯
	private String sub_course_id;//二级课程ID_DES加密
	private String is_show;//是否显示
	private String meta_title;
	private String meta_dis;
	private String meta_key;
	private String question_class;//题库首页标题的颜色
	private String question_index_pic;//题库封面图
	private String exam_time;//考试时间:格式2016/12/16
	
	private Integer question_number;//该课程下有多少课程分类数量
	
	private Integer chapter_option_number;//章节练习题目数量
	private Integer shijuan_number;//试卷数量
	private String phone_index_pic;//手机站手机图片
	private String phone_link;//手机站推广页链接
	
	public String getPhone_link() {
		return phone_link;
	}
	public void setPhone_link(String phone_link) {
		this.phone_link = phone_link;
	}
	public String getPhone_index_pic() {
		return phone_index_pic;
	}
	public void setPhone_index_pic(String phone_index_pic) {
		this.phone_index_pic = phone_index_pic;
	}
	public Integer getShijuan_number() {
		return shijuan_number;
	}
	public void setShijuan_number(Integer shijuan_number) {
		this.shijuan_number = shijuan_number;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public Integer getCourse_parent_id() {
		return course_parent_id;
	}
	public void setCourse_parent_id(Integer course_parent_id) {
		this.course_parent_id = course_parent_id;
	}
	public String getCourse_link() {
		return course_link;
	}
	public void setCourse_link(String course_link) {
		this.course_link = course_link;
	}
	public String getCourse_icon() {
		return course_icon;
	}
	public void setCourse_icon(String course_icon) {
		this.course_icon = course_icon;
	}
	public Integer getCourse_leavl() {
		return course_leavl;
	}
	public void setCourse_leavl(Integer course_leavl) {
		this.course_leavl = course_leavl;
	}
	public String getCourse_dis() {
		return course_dis;
	}
	public void setCourse_dis(String course_dis) {
		this.course_dis = course_dis;
	}
	public Date getCourse_time() {
		return course_time;
	}
	public void setCourse_time(Date course_time) {
		this.course_time = course_time;
	}
	public String getCourse_index_pic() {
		return course_index_pic;
	}
	public void setCourse_index_pic(String course_index_pic) {
		this.course_index_pic = course_index_pic;
	}
	public List<CourseMenu> getSub_list() {
		return sub_list;
	}
	public void setSub_list(List<CourseMenu> sub_list) {
		this.sub_list = sub_list;
	}
	public List<CourseVideo> getCourseVideos() {
		return courseVideos;
	}
	public void setCourseVideos(List<CourseVideo> courseVideos) {
		this.courseVideos = courseVideos;
	}
	public CourseMenu getCourseMenus_video() {
		return courseMenus_video;
	}
	public void setCourseMenus_video(CourseMenu courseMenus_video) {
		this.courseMenus_video = courseMenus_video;
	}
	public String getSub_info_name() {
		return sub_info_name;
	}
	public void setSub_info_name(String sub_info_name) {
		this.sub_info_name = sub_info_name;
	}
	public List<CourseInfo> getCourseInfos() {
		return courseInfos;
	}
	public void setCourseInfos(List<CourseInfo> courseInfos) {
		this.courseInfos = courseInfos;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public String getSub_course_id() {
		return sub_course_id;
	}
	public void setSub_course_id(String sub_course_id) {
		this.sub_course_id = sub_course_id;
	}
	public String getMeta_title() {
		return meta_title;
	}
	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}
	public String getMeta_dis() {
		return meta_dis;
	}
	public void setMeta_dis(String meta_dis) {
		this.meta_dis = meta_dis;
	}
	public String getMeta_key() {
		return meta_key;
	}
	public void setMeta_key(String meta_key) {
		this.meta_key = meta_key.replaceAll("，", ",");
	}
	public String getExam_time() {
		return exam_time;
	}
	public void setExam_time(String exam_time) {
		this.exam_time = exam_time;
	}
	public String getQuestion_class() {
		return question_class;
	}
	public void setQuestion_class(String question_class) {
		this.question_class = question_class;
	}
	public String getQuestion_index_pic() {
		return question_index_pic;
	}
	public void setQuestion_index_pic(String question_index_pic) {
		this.question_index_pic = question_index_pic;
	}
	public Integer getQuestion_number() {
		return question_number;
	}
	public void setQuestion_number(Integer question_number) {
		this.question_number = question_number;
	}
	public Integer getChapter_option_number() {
		return chapter_option_number;
	}
	public void setChapter_option_number(Integer chapter_option_number) {
		this.chapter_option_number = chapter_option_number;
	}
	
}
