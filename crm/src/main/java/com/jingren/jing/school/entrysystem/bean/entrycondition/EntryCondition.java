package com.jingren.jing.school.entrysystem.bean.entrycondition;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
/**
* @Title: EntryCondition.java 
* @Package com.jingren.jing.school.entrysystem.bean.entrycondition 
* @Description: TODO 报名条件
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月21日 上午10:28:19 
* @version 网校+CRM系统 V1.0
 */
public class EntryCondition implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer entrycondition_id;
	private Integer entryplan_id;//报名计划ID
	private Integer dictionary_id;//等级ID
	private Integer parent_id;
	private String entrycondition_content;//报名条件内容
	private Date entrycondition_time;
	private Integer course_id;//课程ID
	private String xiangguang_info;//相关信息
	private CourseMenu courseMenu;
	private List<EntryCondition> sub_conditionlist;
	private Dictionary dictionary;
	private String kaoshi_qici;//考试期次
	private Integer course_parent_id;//课程一级ID
	private List<EntryCondition> houtaisublist;
	private Integer zhuanye_id;//专业ID
    private Integer university_id;//学校ID
    private String baokao_cengci;//专升本，高起专
    private String kaoshi_pici;
    private String zhuanye;//远程教育 报考专业
    private String xuexiao;//远程教育 报考学校
    private List<University> sub_universitylist;
    private University university;
    private List<ChengkaoSc> chengkaoScs;//成考专业
    
    private  String chengkao_kemu;//成考课目
    private Integer review_id;//职称评审三级ID
    
    private String chengkao_xuefei;//成考学费
    
	public String getChengkao_xuefei() {
		return chengkao_xuefei;
	}
	public void setChengkao_xuefei(String chengkao_xuefei) {
		this.chengkao_xuefei = chengkao_xuefei;
	}
	public List<ChengkaoSc> getChengkaoScs() {
		return chengkaoScs;
	}
	public void setChengkaoScs(List<ChengkaoSc> chengkaoScs) {
		this.chengkaoScs = chengkaoScs;
	}
	public Integer getReview_id() {
		return review_id;
	}
	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
	}
	public String getChengkao_kemu() {
		return chengkao_kemu;
	}
	public void setChengkao_kemu(String chengkao_kemu) {
		this.chengkao_kemu = chengkao_kemu;
	}
	public Integer getEntrycondition_id() {
		return entrycondition_id;
	}
	public void setEntrycondition_id(Integer entrycondition_id) {
		this.entrycondition_id = entrycondition_id;
	}
	public Integer getEntryplan_id() {
		return entryplan_id;
	}
	public void setEntryplan_id(Integer entryplan_id) {
		this.entryplan_id = entryplan_id;
	}
	public Integer getDictionary_id() {
		return dictionary_id;
	}
	public void setDictionary_id(Integer dictionary_id) {
		this.dictionary_id = dictionary_id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getEntrycondition_content() {
		return entrycondition_content;
	}
	public void setEntrycondition_content(String entrycondition_content) {
		this.entrycondition_content = entrycondition_content;
	}
	public Date getEntrycondition_time() {
		return entrycondition_time;
	}
	public void setEntrycondition_time(Date entrycondition_time) {
		this.entrycondition_time = entrycondition_time;
	}
	public Integer getCourse_id() {
		return course_id;
	}
	public void setCourse_id(Integer course_id) {
		this.course_id = course_id;
	}
	public String getXiangguang_info() {
		return xiangguang_info;
	}
	public void setXiangguang_info(String xiangguang_info) {
		this.xiangguang_info = xiangguang_info;
	}
	public CourseMenu getCourseMenu() {
		return courseMenu;
	}
	public void setCourseMenu(CourseMenu courseMenu) {
		this.courseMenu = courseMenu;
	}
	public List<EntryCondition> getSub_conditionlist() {
		return sub_conditionlist;
	}
	public void setSub_conditionlist(List<EntryCondition> sub_conditionlist) {
		this.sub_conditionlist = sub_conditionlist;
	}
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	public String getKaoshi_qici() {
		return kaoshi_qici;
	}
	public void setKaoshi_qici(String kaoshi_qici) {
		this.kaoshi_qici = kaoshi_qici;
	}
	public Integer getCourse_parent_id() {
		return course_parent_id;
	}
	public void setCourse_parent_id(Integer course_parent_id) {
		this.course_parent_id = course_parent_id;
	}
	public List<EntryCondition> getHoutaisublist() {
		return houtaisublist;
	}
	public void setHoutaisublist(List<EntryCondition> houtaisublist) {
		this.houtaisublist = houtaisublist;
	}
	public Integer getZhuanye_id() {
		return zhuanye_id;
	}
	public void setZhuanye_id(Integer zhuanye_id) {
		this.zhuanye_id = zhuanye_id;
	}
	public Integer getUniversity_id() {
		return university_id;
	}
	public void setUniversity_id(Integer university_id) {
		this.university_id = university_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getKaoshi_pici() {
		return kaoshi_pici;
	}
	public void setKaoshi_pici(String kaoshi_pici) {
		this.kaoshi_pici = kaoshi_pici;
	}
	public List<University> getSub_universitylist() {
		return sub_universitylist;
	}
	public void setSub_universitylist(List<University> sub_universitylist) {
		this.sub_universitylist = sub_universitylist;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getXuexiao() {
		return xuexiao;
	}
	public void setXuexiao(String xuexiao) {
		this.xuexiao = xuexiao;
	}
	public String getBaokao_cengci() {
		return baokao_cengci;
	}
	public void setBaokao_cengci(String baokao_cengci) {
		this.baokao_cengci = baokao_cengci;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	
}
