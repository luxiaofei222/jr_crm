package com.jingren.jing.conult.bean.conultinfo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class NetConult implements Serializable{

	private Integer consult_id;
	private String user_name;//客户姓名
	private String user_phone;//客户手机号
	private String consult_type;//咨询方式：百度商桥 53客服 离线宝
	private String search_word;//搜索关键词
	private String now_edu;//目前学历
	private String hope_edu;//意向学历
	private String hope_sc;//意向学校
	private String zhuanye;//意向专业
	private String province;//用户所在省份
	private String city;
	private String area;//所在地区
	private String user_ip;//用户IP
	private String user_sex;//用户性别
	private String user_qq;
	private String user_weixin;
	private String note;//备注
	private Integer huifang;//回访次数
	private Integer course_id;
	private Integer course_parent_id;
	private Integer review_id;
	private Integer employee_id;//业务员
	private String employee_name;//业务员姓名
	private Date consult_time;//录入时间
	private Date yuyue_time;
	private Date zixun_time;//咨询日期
	private Integer conult_state;//客户状态：0 未回访 1正在联系 2客户拒绝 3成交
	private String question_info;//问题描述
	
	private Integer bumen_id;
	private Integer dictionary_id;//等级ID
	private String course_name;
	private String course_parent_name;
	private String dictionary_name;
	
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	public String getDictionary_name() {
		return dictionary_name;
	}
	public void setDictionary_name(String dictionary_name) {
		this.dictionary_name = dictionary_name;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_parent_name() {
		return course_parent_name;
	}
	public void setCourse_parent_name(String course_parent_name) {
		this.course_parent_name = course_parent_name;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public Integer getConsult_id() {
		return consult_id;
	}
	public void setConsult_id(Integer consult_id) {
		this.consult_id = consult_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getConsult_type() {
		return consult_type;
	}
	public void setConsult_type(String consult_type) {
		this.consult_type = consult_type;
	}
	public String getSearch_word() {
		return search_word;
	}
	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}
	public String getNow_edu() {
		return now_edu;
	}
	public void setNow_edu(String now_edu) {
		this.now_edu = now_edu;
	}
	public String getHope_edu() {
		return hope_edu;
	}
	public void setHope_edu(String hope_edu) {
		this.hope_edu = hope_edu;
	}
	public String getHope_sc() {
		return hope_sc;
	}
	public void setHope_sc(String hope_sc) {
		this.hope_sc = hope_sc;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_qq() {
		return user_qq;
	}
	public void setUser_qq(String user_qq) {
		this.user_qq = user_qq;
	}
	public String getUser_weixin() {
		return user_weixin;
	}
	public void setUser_weixin(String user_weixin) {
		this.user_weixin = user_weixin;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getHuifang() {
		return huifang;
	}
	public void setHuifang(Integer huifang) {
		this.huifang = huifang;
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
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public Date getConsult_time() {
		return consult_time;
	}
	public void setConsult_time(Date consult_time) {
		this.consult_time = consult_time;
	}
	public Date getYuyue_time() {
		return yuyue_time;
	}
	public void setYuyue_time(Date yuyue_time) {
		this.yuyue_time = yuyue_time;
	}
	public Date getZixun_time() {
		return zixun_time;
	}
	public void setZixun_time(Date zixun_time) {
		this.zixun_time = zixun_time;
	}
	public Integer getConult_state() {
		return conult_state;
	}
	public void setConult_state(Integer conult_state) {
		this.conult_state = conult_state;
	}
	public String getQuestion_info() {
		return question_info;
	}
	public void setQuestion_info(String question_info) {
		this.question_info = question_info;
	}
	
}
