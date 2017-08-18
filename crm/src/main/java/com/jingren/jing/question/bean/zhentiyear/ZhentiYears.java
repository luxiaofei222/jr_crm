package com.jingren.jing.question.bean.zhentiyear;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.question.bean.question_course.QuestionCourse;
import com.jingren.jing.question.bean.zhentitypeintro.ZhentiTypeIntroduce;
import com.jingren.jing.school.bean.course.CourseMenu;

/**
* @Title: ZhentiYears.java 
* @Package com.jingren.jing.question.bean.zhentiyear 
* @Description: TODO 历年真题标题
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月9日 上午10:01:22 
* @version 网校+CRM系统 V1.0
 */
public class ZhentiYears implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer zhenti_id;
	private Integer question_course_id;
	private Integer zhenti_year;
	private String zhenti_name;//真题名称,目前用不到
	private Integer zhenti_kaoshi_time;
	private double zhenti_new_price;
	private double zhenti_old_price;
	private String zhneti_shoufei_type;
	private Date zhenti_time;
	private Integer fenzhi;//题目分值
	private QuestionCourse questionCourse;
	private CourseMenu courseMenu;
	private  String is_show;//显示状态
	private String zhentitype;//卷子类型
	private List<ZhentiTypeIntroduce> zhentiTypeIntroduce;//真题类型
	private Integer is_jixu;//是否继续做题 0没有记录 1再做一次 2继续答题
	private int zhenti_zongfen;//总分值
	private int zhenti_zongtishu;//该套试卷多少道题
	private Integer zhenti_record_id;//真题记录ID
	private Integer zhenti_yue;//月份
	
	public Integer getZhenti_yue() {
		return zhenti_yue;
	}
	public void setZhenti_yue(Integer zhenti_yue) {
		this.zhenti_yue = zhenti_yue;
	}
	public Integer getZhenti_record_id() {
		return zhenti_record_id;
	}
	public void setZhenti_record_id(Integer zhenti_record_id) {
		this.zhenti_record_id = zhenti_record_id;
	}
	public Integer getIs_jixu() {
		return is_jixu;
	}
	public void setIs_jixu(Integer is_jixu) {
		this.is_jixu = is_jixu;
	}
	public String getZhentitype() {
		return zhentitype;
	}
	public void setZhentitype(String zhentitype) {
		this.zhentitype = zhentitype;
	}
	public Integer getZhenti_id() {
		return zhenti_id;
	}
	public void setZhenti_id(Integer zhenti_id) {
		this.zhenti_id = zhenti_id;
	}
	public Integer getQuestion_course_id() {
		return question_course_id;
	}
	public void setQuestion_course_id(Integer question_course_id) {
		this.question_course_id = question_course_id;
	}
	public Integer getZhenti_year() {
		return zhenti_year;
	}
	public void setZhenti_year(Integer zhenti_year) {
		this.zhenti_year = zhenti_year;
	}
	public String getZhenti_name() {
		return zhenti_name;
	}
	public void setZhenti_name(String zhenti_name) {
		this.zhenti_name = zhenti_name;
	}
	public Integer getZhenti_kaoshi_time() {
		return zhenti_kaoshi_time;
	}
	public void setZhenti_kaoshi_time(Integer zhenti_kaoshi_time) {
		this.zhenti_kaoshi_time = zhenti_kaoshi_time;
	}
	public double getZhenti_new_price() {
		return zhenti_new_price;
	}
	public void setZhenti_new_price(double zhenti_new_price) {
		this.zhenti_new_price = zhenti_new_price;
	}
	public double getZhenti_old_price() {
		return zhenti_old_price;
	}
	public void setZhenti_old_price(double zhenti_old_price) {
		this.zhenti_old_price = zhenti_old_price;
	}
	public String getZhneti_shoufei_type() {
		return zhneti_shoufei_type;
	}
	public void setZhneti_shoufei_type(String zhneti_shoufei_type) {
		this.zhneti_shoufei_type = zhneti_shoufei_type;
	}
	public Date getZhenti_time() {
		return zhenti_time;
	}
	public void setZhenti_time(Date zhenti_time) {
		this.zhenti_time = zhenti_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getFenzhi() {
		return fenzhi;
	}
	public void setFenzhi(Integer fenzhi) {
		this.fenzhi = fenzhi;
	}
	public QuestionCourse getQuestionCourse() {
		return questionCourse;
	}
	public void setQuestionCourse(QuestionCourse questionCourse) {
		this.questionCourse = questionCourse;
	}
	public CourseMenu getCourseMenu() {
		return courseMenu;
	}
	public void setCourseMenu(CourseMenu courseMenu) {
		this.courseMenu = courseMenu;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public List<ZhentiTypeIntroduce> getZhentiTypeIntroduce() {
		return zhentiTypeIntroduce;
	}
	public void setZhentiTypeIntroduce(List<ZhentiTypeIntroduce> zhentiTypeIntroduce) {
		this.zhentiTypeIntroduce = zhentiTypeIntroduce;
	}
	public int getZhenti_zongfen() {
		return zhenti_zongfen;
	}
	public void setZhenti_zongfen(int zhenti_zongfen) {
		this.zhenti_zongfen = zhenti_zongfen;
	}
	public int getZhenti_zongtishu() {
		return zhenti_zongtishu;
	}
	public void setZhenti_zongtishu(int zhenti_zongtishu) {
		this.zhenti_zongtishu = zhenti_zongtishu;
	}

}
