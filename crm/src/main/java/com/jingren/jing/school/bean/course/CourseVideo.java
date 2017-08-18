package com.jingren.jing.school.bean.course;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import com.jingren.jing.school.bean.teacher.Teacher;

/**
 * 课程视频
* @Title: CourseVideo.java 
* @Package com.jingren.jing.school.bean.course 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月4日 上午8:33:35 
* @version 网校+CRM系统 V1.0
 */
public class CourseVideo implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer video_id;
	private Integer course_id;
	private String video_pic;//视频封面
	private String video_file;
	private String video_type;//视频类型:'付费','正常'
	private Date video_time;
	private String video_name;//视频名称
	private Integer video_parent_id;
	private String video_chapter;//视频章节
	private double video_money_now;
	private double video_moey_new;
	private Integer play_times;
	private String is_tuijian;
	private Integer keshi_number=0;//所属课时数量
	private Integer comment_number;//评论数量
	private String video_dis;//课程描述
	private String banxing;
	private Integer dictionary_id;
	private Integer course_parent_id;
	private Integer video_section;//章节
	private Integer video_level;//课程等级 1级课程内容 2级章 3级节
	private Integer teacher_id;
	private List<CourseVideo> courseVideos_sanji;//视频三级小节
	private List<CourseVideo> courseVideos_zhang;//视频章列表
	private boolean is_jiaru_course;
	private CourseMenu courseMenu;//课程分类
	private Teacher teacher;//课程授课老师
	private String course_jieshao;//课程介绍
	private String is_info;//是否显示到资讯二级页面
	private Integer pay_number;//购买数量
	
	private Integer top_paixu;
	
	private Integer is_jinzhi;//是否禁止点击视频
	private Date daoqi_time;//课程到期时间
	
	public Date getDaoqi_time() {
		return daoqi_time;
	}
	public void setDaoqi_time(Date daoqi_time) {
		this.daoqi_time = daoqi_time;
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
	public String getVideo_pic() {
		return video_pic;
	}
	public void setVideo_pic(String video_pic) {
		this.video_pic = video_pic;
	}
	public String getVideo_file() {
		return video_file;
	}
	public void setVideo_file(String video_file) {
		this.video_file = video_file;
	}
	public String getVideo_type() {
		return video_type;
	}
	public void setVideo_type(String video_type) {
		this.video_type = video_type;
	}
	public Date getVideo_time() {
		return video_time;
	}
	public void setVideo_time(Date video_time) {
		this.video_time = video_time;
	}
	public String getVideo_name() {
		return video_name;
	}
	public void setVideo_name(String video_name) {
		this.video_name = video_name;
	}
	public Integer getVideo_parent_id() {
		return video_parent_id;
	}
	public void setVideo_parent_id(Integer video_parent_id) {
		this.video_parent_id = video_parent_id;
	}
	public String getVideo_chapter() {
		return video_chapter;
	}
	public void setVideo_chapter(String video_chapter) {
		this.video_chapter = video_chapter;
	}
	public double getVideo_money_now() {
		 DecimalFormat df = new DecimalFormat("0.00"); 
		return Double.valueOf(df.format(video_money_now));
	}
	public void setVideo_money_now(double video_money_now) {
		this.video_money_now = video_money_now;
	}
	public double getVideo_moey_new() {
		return video_moey_new;
	}
	public void setVideo_moey_new(double video_moey_new) {
		this.video_moey_new = video_moey_new;
	}
	public Integer getPlay_times() {
		return play_times;
	}
	public void setPlay_times(Integer play_times) {
		this.play_times = play_times;
	}
	public String getIs_tuijian() {
		return is_tuijian;
	}
	public void setIs_tuijian(String is_tuijian) {
		this.is_tuijian = is_tuijian;
	}
	public Integer getKeshi_number() {
		return keshi_number;
	}
	public void setKeshi_number(Integer keshi_number) {
		this.keshi_number = keshi_number;
	}
	public Integer getComment_number() {
		return comment_number;
	}
	public void setComment_number(Integer comment_number) {
		this.comment_number = comment_number;
	}
	public String getVideo_dis() {
		return video_dis;
	}
	public void setVideo_dis(String video_dis) {
		this.video_dis = video_dis;
	}
	public String getBanxing() {
		return banxing;
	}
	public void setBanxing(String banxing) {
		this.banxing = banxing;
	}
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	public Integer getCourse_parent_id() {
		return course_parent_id;
	}
	public void setCourse_parent_id(Integer course_parent_id) {
		this.course_parent_id = course_parent_id;
	}
	public Integer getVideo_level() {
		return video_level;
	}
	public void setVideo_level(Integer video_level) {
		this.video_level = video_level;
	}
	public Integer getVideo_section() {
		return video_section;
	}
	public void setVideo_section(Integer video_section) {
		this.video_section = video_section;
	}
	public List<CourseVideo> getCourseVideos_sanji() {
		return courseVideos_sanji;
	}
	public void setCourseVideos_sanji(List<CourseVideo> courseVideos_sanji) {
		this.courseVideos_sanji = courseVideos_sanji;
	}
	public Integer getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}
	public boolean isIs_jiaru_course() {
		return is_jiaru_course;
	}
	public void setIs_jiaru_course(boolean is_jiaru_course) {
		this.is_jiaru_course = is_jiaru_course;
	}
	public CourseMenu getCourseMenu() {
		return courseMenu;
	}
	public void setCourseMenu(CourseMenu courseMenu) {
		this.courseMenu = courseMenu;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getCourse_jieshao() {
		return course_jieshao;
	}
	public void setCourse_jieshao(String course_jieshao) {
		this.course_jieshao = course_jieshao;
	}
	public List<CourseVideo> getCourseVideos_zhang() {
		return courseVideos_zhang;
	}
	public void setCourseVideos_zhang(List<CourseVideo> courseVideos_zhang) {
		this.courseVideos_zhang = courseVideos_zhang;
	}
	public String getIs_info() {
		return is_info;
	}
	public void setIs_info(String is_info) {
		this.is_info = is_info;
	}
	public Integer getPay_number() {
		return pay_number;
	}
	public void setPay_number(Integer pay_number) {
		this.pay_number = pay_number;
	}
	public Integer getTop_paixu() {
		return top_paixu;
	}
	public void setTop_paixu(Integer top_paixu) {
		this.top_paixu = top_paixu;
	}
	public Integer getIs_jinzhi() {
		return is_jinzhi;
	}
	public void setIs_jinzhi(Integer is_jinzhi) {
		this.is_jinzhi = is_jinzhi;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
