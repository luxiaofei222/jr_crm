package com.jingren.jing.question.bean.zhentiquestion;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.question.bean.zhentioption.ZhentiQuestionOption;
/**
* @Title: ZhentiQuestion.java 
* @Package com.jingren.jing.question.bean.zhentiquestion 
* @Description: TODO 历年真题-题目
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月9日 下午1:39:09 
* @version 网校+CRM系统 V1.0
 */
public class ZhentiQuestion implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer zhenti_question_id;
	private Integer zhenti_id;
	private String question_name;//题目名称
	private String question_name_pic;//题目图片
	private String question_type;//题目类型
	private Integer question_course_id;//课程ID
	private String answer;//答案
	private String analysis;//解析
	private Date question_time;//添加时间
	private Integer difficulty;//试题难度
	private Integer fenzhi;//题目分值
	private List<ZhentiQuestionOption> zhentiQuestionOptions;
	private Integer xuhao;//真题序号
	private String user_answer;//用户答案
	private List<String> duoxuanlisti;//多选题答案
	private String is_right;//是否正确
	
	private Integer zuoguo_number;//该题做过多少次
	private Integer zuodui_number;//作对多少次
	
	private List<ChapterQuestion> chapterQuestions;//技能选择题
	
	public List<ChapterQuestion> getChapterQuestions() {
		return chapterQuestions;
	}
	public void setChapterQuestions(List<ChapterQuestion> chapterQuestions) {
		this.chapterQuestions = chapterQuestions;
	}
	public Integer getZuoguo_number() {
		return zuoguo_number;
	}
	public void setZuoguo_number(Integer zuoguo_number) {
		this.zuoguo_number = zuoguo_number;
	}
	public Integer getZuodui_number() {
		return zuodui_number;
	}
	public void setZuodui_number(Integer zuodui_number) {
		this.zuodui_number = zuodui_number;
	}
	public String getIs_right() {
		return is_right;
	}
	public void setIs_right(String is_right) {
		this.is_right = is_right;
	}
	public List<String> getDuoxuanlisti() {
		return duoxuanlisti;
	}
	public void setDuoxuanlisti(List<String> duoxuanlisti) {
		this.duoxuanlisti = duoxuanlisti;
	}
	public String getUser_answer() {
		return user_answer;
	}
	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}
	public Integer getZhenti_question_id() {
		return zhenti_question_id;
	}
	public void setZhenti_question_id(Integer zhenti_question_id) {
		this.zhenti_question_id = zhenti_question_id;
	}
	public Integer getZhenti_id() {
		return zhenti_id;
	}
	public void setZhenti_id(Integer zhenti_id) {
		this.zhenti_id = zhenti_id;
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
	public Date getQuestion_time() {
		return question_time;
	}
	public void setQuestion_time(Date question_time) {
		this.question_time = question_time;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	public Integer getFenzhi() {
		return fenzhi;
	}
	public void setFenzhi(Integer fenzhi) {
		this.fenzhi = fenzhi;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<ZhentiQuestionOption> getZhentiQuestionOptions() {
		return zhentiQuestionOptions;
	}
	public void setZhentiQuestionOptions(List<ZhentiQuestionOption> zhentiQuestionOptions) {
		this.zhentiQuestionOptions = zhentiQuestionOptions;
	}
	public Integer getXuhao() {
		return xuhao;
	}
	public void setXuhao(Integer xuhao) {
		this.xuhao = xuhao;
	}
	
}
