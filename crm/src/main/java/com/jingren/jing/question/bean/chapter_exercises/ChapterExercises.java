package com.jingren.jing.question.bean.chapter_exercises;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* @Title: ChapterExercises.java 
* @Package com.jingren.jing.question.bean.chapter_exercises 
* @Description: TODO 题库章节练习-章节
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月10日 下午2:01:59 
* @version 网校+CRM系统 V1.0
 */
public class ChapterExercises implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer chapter_exercises_id;
	private String textbook_version;//教材版本
	private Integer question_course_id;//题库课程分类ID
	private Integer parent_id;
	private Integer chapter_level;//级别：等级1-章 2-节 3-题目
	private String chapter_name;//章节名称
	private Date chapter_time;
	private Integer chapter_number;//章节顺序
	private String chapter_number_str;//转换汉字的章节序号
	private List<ChapterExercises> chapterExercises_jie;//小节列表
	private List<ChapterExercises> chapterExercises_question;//题列表
	private Integer question_number;//题目数量
	
	private Integer yizuo_chapter;//已做的练习题数量
	
	private Integer cuowu_chapter;//错误题目数量
	public Integer getChapter_exercises_id() {
		return chapter_exercises_id;
	}
	public void setChapter_exercises_id(Integer chapter_exercises_id) {
		this.chapter_exercises_id = chapter_exercises_id;
	}
	public String getTextbook_version() {
		return textbook_version;
	}
	public void setTextbook_version(String textbook_version) {
		this.textbook_version = textbook_version;
	}
	public Integer getQuestion_course_id() {
		return question_course_id;
	}
	public void setQuestion_course_id(Integer question_course_id) {
		this.question_course_id = question_course_id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getChapter_level() {
		return chapter_level;
	}
	public void setChapter_level(Integer chapter_level) {
		this.chapter_level = chapter_level;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public Date getChapter_time() {
		return chapter_time;
	}
	public void setChapter_time(Date chapter_time) {
		this.chapter_time = chapter_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getChapter_number() {
		return chapter_number;
	}
	public void setChapter_number(Integer chapter_number) {
		this.chapter_number = chapter_number;
	}
	public List<ChapterExercises> getChapterExercises_jie() {
		return chapterExercises_jie;
	}
	public void setChapterExercises_jie(List<ChapterExercises> chapterExercises_jie) {
		this.chapterExercises_jie = chapterExercises_jie;
	}
	public List<ChapterExercises> getChapterExercises_question() {
		return chapterExercises_question;
	}
	public void setChapterExercises_question(List<ChapterExercises> chapterExercises_question) {
		this.chapterExercises_question = chapterExercises_question;
	}
	public String getChapter_number_str() {
		return chapter_number_str;
	}
	public void setChapter_number_str(String chapter_number_str) {
		this.chapter_number_str = chapter_number_str;
	}
	public Integer getQuestion_number() {
		return question_number;
	}
	public void setQuestion_number(Integer question_number) {
		this.question_number = question_number;
	}
	public Integer getYizuo_chapter() {
		return yizuo_chapter;
	}
	public void setYizuo_chapter(Integer yizuo_chapter) {
		this.yizuo_chapter = yizuo_chapter;
	}
	public Integer getCuowu_chapter() {
		return cuowu_chapter;
	}
	public void setCuowu_chapter(Integer cuowu_chapter) {
		this.cuowu_chapter = cuowu_chapter;
	}
	
}
