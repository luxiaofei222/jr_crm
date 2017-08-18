package com.jingren.jing.question.bean.question_collection;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;

/**
* @Title: QuestionCollection.java 
* @Package com.jingren.jing.question.bean.question_collection 
* @Description: TODO 题目收藏
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月6日 下午3:24:14 
* @version 网校+CRM系统 V1.0
 */
public class QuestionCollection implements Serializable{

	private static final long serialVersionUID = -1886057030421323891L;

	private Integer question_collection_id;
	private Integer user_id;
	private String question_type;
	private Integer question_course_id;
	private Integer question_id;//题目ID
	private String user_answer;//用户的答案
	private Date collection_time;
	private Integer chapter_recourd_id;
	private ChapterQuestion chapterQuestion;
	private Integer user_answer_number;//用户答过该题的数量
	private Integer user_answer_right_number;//用户答对的数量
	private ChapterRecord chapterRecord;//记录
	public Integer getQuestion_collection_id() {
		return question_collection_id;
	}
	public void setQuestion_collection_id(Integer question_collection_id) {
		this.question_collection_id = question_collection_id;
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
	public Integer getQuestion_course_id() {
		return question_course_id;
	}
	public void setQuestion_course_id(Integer question_course_id) {
		this.question_course_id = question_course_id;
	}
	public Integer getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
	public String getUser_answer() {
		return user_answer;
	}
	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}
	public Date getCollection_time() {
		return collection_time;
	}
	public void setCollection_time(Date collection_time) {
		this.collection_time = collection_time;
	}
	public Integer getChapter_recourd_id() {
		return chapter_recourd_id;
	}
	public void setChapter_recourd_id(Integer chapter_recourd_id) {
		this.chapter_recourd_id = chapter_recourd_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ChapterQuestion getChapterQuestion() {
		return chapterQuestion;
	}
	public void setChapterQuestion(ChapterQuestion chapterQuestion) {
		this.chapterQuestion = chapterQuestion;
	}
	public Integer getUser_answer_number() {
		return user_answer_number;
	}
	public void setUser_answer_number(Integer user_answer_number) {
		this.user_answer_number = user_answer_number;
	}
	public Integer getUser_answer_right_number() {
		return user_answer_right_number;
	}
	public void setUser_answer_right_number(Integer user_answer_right_number) {
		this.user_answer_right_number = user_answer_right_number;
	}
	public ChapterRecord getChapterRecord() {
		return chapterRecord;
	}
	public void setChapterRecord(ChapterRecord chapterRecord) {
		this.chapterRecord = chapterRecord;
	}
	
}
