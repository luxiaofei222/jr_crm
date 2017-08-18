package com.jingren.jing.question.bean.chapterrecord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.question.bean.chapter_exercises.ChapterExercises;
import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;

/**
* @Title: ChapterRecord.java 
* @Package com.jingren.jing.question.bean.chapterrecord 
* @Description: TODO 章节练习答题记录
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月20日 下午1:22:19 
* @version 网校+CRM系统 V1.0
 */
public class ChapterRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer chapter_recourd_id;
	private Integer user_id;
	private Integer chapter_exercises_id;//章节ID
	private Integer chapter_question_id;
	private Integer question_course_id;
	private Integer parent_id;
	private Date recourd_time;
	private String user_answer;//用户的答案
	private Integer answer_length;
	private String is_right;//是否正确
	
	private Integer zhang_id;//章ID
	private Integer jie_id;//小节ID
	private Integer question_id;//课目ID
	private String question_type;//题型
	private String question_order_number;//练习记录订单号唯一索引
	private ChapterExercises chapterExercises;//对应的题目以及级别
	private Integer lianxi_number;//本套题用户练习了多少道
	private Integer lianxi_right_number;//本套题用户练习正确多少道
	private ChapterQuestion chapterQuestion;//练习题目
	private Integer user_answer_number;//用户答过该题的数量
	private Integer user_answer_right_number;//用户答对的数量
	private List<String> user_answer_list;//用户答案的集合
	private String user_answer_time;//用户答题时长时：分：秒
	private Integer is_collection=0;//该题是否被用户收藏 0未收藏 1已收藏
	
	private Integer zhenti_record_id;//真题记录ID
	
	public Integer getzhenti_record_id() {
		return zhenti_record_id;
	}
	public void setzhenti_record_id(Integer zhenti_record_id) {
		this.zhenti_record_id = zhenti_record_id;
	}
	public Integer getChapter_recourd_id() {
		return chapter_recourd_id;
	}
	public void setChapter_recourd_id(Integer chapter_recourd_id) {
		this.chapter_recourd_id = chapter_recourd_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getChapter_exercises_id() {
		return chapter_exercises_id;
	}
	public void setChapter_exercises_id(Integer chapter_exercises_id) {
		this.chapter_exercises_id = chapter_exercises_id;
	}
	public Integer getChapter_question_id() {
		return chapter_question_id;
	}
	public void setChapter_question_id(Integer chapter_question_id) {
		this.chapter_question_id = chapter_question_id;
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
	public Date getRecourd_time() {
		return recourd_time;
	}
	public void setRecourd_time(Date recourd_time) {
		this.recourd_time = recourd_time;
	}
	public String getUser_answer() {
		return user_answer;
	}
	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}
	public Integer getAnswer_length() {
		return answer_length;
	}
	public void setAnswer_length(Integer answer_length) {
		this.answer_length = answer_length;
	}
	public String getIs_right() {
		return is_right;
	}
	public void setIs_right(String is_right) {
		this.is_right = is_right;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public String getQuestion_order_number() {
		return question_order_number;
	}
	public void setQuestion_order_number(String question_order_number) {
		
		this.question_order_number = question_order_number;
	}
	public ChapterExercises getChapterExercises() {
		return chapterExercises;
	}
	public void setChapterExercises(ChapterExercises chapterExercises) {
		this.chapterExercises = chapterExercises;
	}
	public Integer getLianxi_number() {
		return lianxi_number;
	}
	public void setLianxi_number(Integer lianxi_number) {
		this.lianxi_number = lianxi_number;
	}
	public Integer getLianxi_right_number() {
		return lianxi_right_number;
	}
	public void setLianxi_right_number(Integer lianxi_right_number) {
		this.lianxi_right_number = lianxi_right_number;
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
	public List<String> getUser_answer_list() {
		return user_answer_list;
	}
	public void setUser_answer_list(List<String> user_answer_list) {
		this.user_answer_list = user_answer_list;
	}
	public String getUser_answer_time() {
		return user_answer_time;
	}
	public void setUser_answer_time(String user_answer_time) {
		this.user_answer_time = user_answer_time;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public Integer getIs_collection() {
		return is_collection;
	}
	public void setIs_collection(Integer is_collection) {
		this.is_collection = is_collection;
	}
	
}
