package com.jingren.jing.question.bean.chapter_exercises;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;
/**
* @Title: ChapterQuestion.java 
* @Package com.jingren.jing.question.bean.chapter_exercises 
* @Description: TODO 章节练习-题目
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月10日 下午2:05:47 
* @version 网校+CRM系统 V1.0
 */
public class ChapterQuestion implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer chapter_question_id;
	private Integer zhang_id;//章名称
	private Integer jie_id;//小节名称
	private Integer question_id;//标题
	private String question_name;//问题题目内容
	private String question_name_pic;//题目图片，如果题目有图片表达的话
	private String question_type;//题目类型：单选 多选
	private Date chapter_question_time;
	private Integer question_course_id;//课程分类ID
	private String answer;//答案
	private String analysis;//答案解析
	private Integer difficulty;//试题难度 0-5
	private List<ChapterQuerstionOption> chapterQuerstionOptions;
	private List<String> answer_list;//正确答案的集合
	private Integer fenzhi;//分值
	private Integer zhenti_question_id;//真题题目ID
	private ChapterRecord chapterRecord;
	private Integer is_dati;//
	
	public Integer getIs_dati() {
		return is_dati;
	}
	public void setIs_dati(Integer is_dati) {
		this.is_dati = is_dati;
	}
	public ChapterRecord getChapterRecord() {
		return chapterRecord;
	}
	public void setChapterRecord(ChapterRecord chapterRecord) {
		this.chapterRecord = chapterRecord;
	}
	public Integer getFenzhi() {
		return fenzhi;
	}
	public void setFenzhi(Integer fenzhi) {
		this.fenzhi = fenzhi;
	}
	public Integer getZhenti_question_id() {
		return zhenti_question_id;
	}
	public void setZhenti_question_id(Integer zhenti_question_id) {
		this.zhenti_question_id = zhenti_question_id;
	}
	public Integer getChapter_question_id() {
		return chapter_question_id;
	}
	public void setChapter_question_id(Integer chapter_question_id) {
		this.chapter_question_id = chapter_question_id;
	}
	public Integer getZhang_id() {
		return zhang_id;
	}
	public void setZhang_id(Integer zhang_id) {
		this.zhang_id = zhang_id;
	}
	public Integer getJie_id() {
		return jie_id;
	}
	public void setJie_id(Integer jie_id) {
		this.jie_id = jie_id;
	}
	public Integer getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_name() {
		return question_name;
	}
	public void setQuestion_name(String question_name) {
		this.question_name = question_name;
	}
	public String getQuestion_name_pic() {
		return question_name_pic;
	}
	public void setQuestion_name_pic(String question_name_pic) {
		this.question_name_pic = question_name_pic;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public Date getChapter_question_time() {
		return chapter_question_time;
	}
	public void setChapter_question_time(Date chapter_question_time) {
		this.chapter_question_time = chapter_question_time;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getQuestion_course_id() {
		return question_course_id;
	}
	public void setQuestion_course_id(Integer question_course_id) {
		this.question_course_id = question_course_id;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	public List<ChapterQuerstionOption> getChapterQuerstionOptions() {
		return chapterQuerstionOptions;
	}
	public void setChapterQuerstionOptions(List<ChapterQuerstionOption> chapterQuerstionOptions) {
		this.chapterQuerstionOptions = chapterQuerstionOptions;
	}
	public List<String> getAnswer_list() {
		return answer_list;
	}
	public void setAnswer_list(List<String> answer_list) {
		this.answer_list = answer_list;
	}
	
}
