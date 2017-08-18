package com.jingren.jing.question.bean.correction;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.school.bean.user.User;
/**
* @Title: CorrectionQuestion.java 
* @Package com.jingren.jing.question.bean.correction 
* @Description: TODO 题目纠错信息
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月7日 下午5:30:57 
* @version 网校+CRM系统 V1.0
 */
public class CorrectionQuestion implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer correction_id;
	private Integer user_id;
	private String question_type;
	private Integer question_id;
	private Date correction_time;
	private String correction_content;
	private String correction_type;
	private Integer correction_state;//0,未查看 1已查看
	private User user;
	private ChapterQuestion chapterQuestion;//章节练习题目
	public Integer getCorrection_id() {
		return correction_id;
	}
	public void setCorrection_id(Integer correction_id) {
		this.correction_id = correction_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public Integer getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
	public Date getCorrection_time() {
		return correction_time;
	}
	public void setCorrection_time(Date correction_time) {
		this.correction_time = correction_time;
	}
	public String getCorrection_content() {
		return correction_content;
	}
	public void setCorrection_content(String correction_content) {
		this.correction_content = correction_content;
	}
	public String getCorrection_type() {
		return correction_type;
	}
	public void setCorrection_type(String correction_type) {
		this.correction_type = correction_type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getCorrection_state() {
		return correction_state;
	}
	public void setCorrection_state(Integer correction_state) {
		this.correction_state = correction_state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ChapterQuestion getChapterQuestion() {
		return chapterQuestion;
	}
	public void setChapterQuestion(ChapterQuestion chapterQuestion) {
		this.chapterQuestion = chapterQuestion;
	}
	
}
