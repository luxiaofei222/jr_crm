package com.jingren.jing.question.bean.zhentirecord;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.question.bean.zhentiyear.ZhentiYears;
import com.jingren.jing.util.CommentDate;
/**
* @Title: ZhentiRecord.java 
* @Package com.jingren.jing.question.bean.zhentirecord 
* @Description: TODO 历年真题-历史记录
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月15日 上午9:55:37 
* @version 网校+CRM系统 V1.0
 */
public class ZhentiRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer zhenti_record_id;
	private Integer user_id;
	private Integer zhenti_id;//真题ID
	private Integer zhenti_question_id;//真题题目ID
	private Integer question_course_id;//题目类别
	private Integer parent_id;
	private Date recourd_time;//记录时间
	private String user_answer;//用户答案
	private Integer answer_length;//答题时间
	private String is_right;//答案是否正确
	private String question_order_number;//记录的订单号
	private String question_type;//题目类型
	private Integer recourd_state;//记录状态1 已提交 2保存进度
	private String zhenti_type;//历年真题、模拟考试
	private String zhuanhuan_time;//转换后的答题时间
	private List<Integer> shifenmiao;
	private Integer xuhao;//序号记录
	private ZhentiYears zhentiYears;
	private  Integer fenzhi;//得分
	
	public Integer getFenzhi() {
		return fenzhi;
	}
	public void setFenzhi(Integer fenzhi) {
		this.fenzhi = fenzhi;
	}
	public ZhentiYears getZhentiYears() {
		return zhentiYears;
	}
	public void setZhentiYears(ZhentiYears zhentiYears) {
		this.zhentiYears = zhentiYears;
	}
	public Integer getXuhao() {
		return xuhao;
	}
	public void setXuhao(Integer xuhao) {
		this.xuhao = xuhao;
	}
	public String getZhuanhuan_time() {
		zhuanhuan_time=CommentDate.secToTime(answer_length);
		return zhuanhuan_time;
	}
	public void setZhuanhuan_time(String zhuanhuan_time) {
		this.zhuanhuan_time = zhuanhuan_time;
	}
	public Integer getZhenti_record_id() {
		return zhenti_record_id;
	}
	public void setZhenti_record_id(Integer zhenti_record_id) {
		this.zhenti_record_id = zhenti_record_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getZhenti_id() {
		return zhenti_id;
	}
	public void setZhenti_id(Integer zhenti_id) {
		this.zhenti_id = zhenti_id;
	}
	public Integer getZhenti_question_id() {
		return zhenti_question_id;
	}
	public void setZhenti_question_id(Integer zhenti_question_id) {
		this.zhenti_question_id = zhenti_question_id;
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
	public String getQuestion_order_number() {
		return question_order_number;
	}
	public void setQuestion_order_number(String question_order_number) {
		this.question_order_number = question_order_number;
	}
	public String getQuestion_type() {
		return question_type;
	}
	public void setQuestion_type(String question_type) {
		this.question_type = question_type;
	}
	public Integer getRecourd_state() {
		return recourd_state;
	}
	public void setRecourd_state(Integer recourd_state) {
		this.recourd_state = recourd_state;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getZhenti_type() {
		return zhenti_type;
	}
	public void setZhenti_type(String zhenti_type) {
		this.zhenti_type = zhenti_type;
	}
	public List<Integer> getShifenmiao() {
		return shifenmiao;
	}
	public void setShifenmiao(List<Integer> shifenmiao) {
		this.shifenmiao = shifenmiao;
	}
}
