package com.jingren.jing.educational.bean.grade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
/**
* @Title: UserGrade.java 
* @Package com.jingren.jing.educational.bean.grade 
* @Description: TODO 用户成绩查询
* @author 鲁晓飞 MR.Lu   
* @date 2017年8月17日 下午4:08:43 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class UserGrade implements Serializable{

	private Integer grade_id;
	private String user_name;
	private String user_sex;
	private String user_phone;
	private String user_card;
	private String user_zhunkao;
	private Integer course_id;
	private Integer course_parent_id;
	private Integer review_id;
	private Integer dictionary_id;
	private String xuexiao;
	private String zhuanye;
	private Integer parent_id;
	private String kemu;//课目
	private String grade;//成绩
	private Date grade_time;//添加时间
	private CourseMenu courseMenu;
	private Dictionary dictionary;
	private Review review;
	private List<UserGrade> grades;
	private String kaoshenghao;//考生号
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	public CourseMenu getCourseMenu() {
		return courseMenu;
	}
	public void setCourseMenu(CourseMenu courseMenu) {
		this.courseMenu = courseMenu;
	}
	public Integer getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_card() {
		return user_card;
	}
	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}
	public String getUser_zhunkao() {
		return user_zhunkao;
	}
	public void setUser_zhunkao(String user_zhunkao) {
		this.user_zhunkao = user_zhunkao;
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
	public Integer getReview_id() {
		return review_id;
	}
	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	public String getXuexiao() {
		return xuexiao;
	}
	public void setXuexiao(String xuexiao) {
		this.xuexiao = xuexiao;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getKemu() {
		return kemu;
	}
	public void setKemu(String kemu) {
		this.kemu = kemu;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getGrade_time() {
		return grade_time;
	}
	public void setGrade_time(Date grade_time) {
		this.grade_time = grade_time;
	}
	public List<UserGrade> getGrades() {
		return grades;
	}
	public void setGrades(List<UserGrade> grades) {
		this.grades = grades;
	}
	public String getKaoshenghao() {
		return kaoshenghao;
	}
	public void setKaoshenghao(String kaoshenghao) {
		this.kaoshenghao = kaoshenghao;
	}
}
