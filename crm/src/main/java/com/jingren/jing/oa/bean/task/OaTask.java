package com.jingren.jing.oa.bean.task;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.employee.Employee;

@SuppressWarnings("serial")
public class OaTask implements Serializable{
	
	private Integer task_id;
	private Integer fabu_employee_id; //发布人
	private Date task_finish_time;//截止日期
	private Date task_time;//发布日期
	private Integer task_employee_id;//任务完成人
	private Integer bumen_id;//部门ID
	private String gangwei; //岗位
	private String task_content;//任务内容
	private String fujian;//附件
	private String task_type;//任务完成状态
	private String pingjia_content;//任务评价
	private Integer pingfen;//评分
	private String task_jibie;//任务级别：'部门','公司'
	private Integer zhongyao_state;//紧急程度：'2特急','1紧急','0正常'
	private String task_jianyi;//任务申请
	private Employee fabuem;
	private Employee jieshouem;
	private String bumen;
	private Date finish_time;//任务完成时间
	
	public Date getFinish_time() {
		return finish_time;
	}
	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}
	public String getBumen() {
		return bumen;
	}
	public void setBumen(String bumen) {
		this.bumen = bumen;
	}
	public Employee getFabuem() {
		return fabuem;
	}
	public void setFabuem(Employee fabuem) {
		this.fabuem = fabuem;
	}
	public Employee getJieshouem() {
		return jieshouem;
	}
	public void setJieshouem(Employee jieshouem) {
		this.jieshouem = jieshouem;
	}
	public Integer getTask_id() {
		return task_id;
	}
	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}
	public Integer getFabu_employee_id() {
		return fabu_employee_id;
	}
	public void setFabu_employee_id(Integer fabu_employee_id) {
		this.fabu_employee_id = fabu_employee_id;
	}
	public Date getTask_finish_time() {
		return task_finish_time;
	}
	public void setTask_finish_time(Date task_finish_time) {
		this.task_finish_time = task_finish_time;
	}
	public Date getTask_time() {
		return task_time;
	}
	public void setTask_time(Date task_time) {
		this.task_time = task_time;
	}
	public Integer getTask_employee_id() {
		return task_employee_id;
	}
	public void setTask_employee_id(Integer task_employee_id) {
		this.task_employee_id = task_employee_id;
	}
	public Integer getBumen_id() {
		return bumen_id;
	}
	public void setBumen_id(Integer bumen_id) {
		this.bumen_id = bumen_id;
	}
	public String getGangwei() {
		return gangwei;
	}
	public void setGangwei(String gangwei) {
		this.gangwei = gangwei;
	}
	public String getTask_content() {
		return task_content;
	}
	public void setTask_content(String task_content) {
		this.task_content = task_content;
	}
	public String getFujian() {
		return fujian;
	}
	public void setFujian(String fujian) {
		this.fujian = fujian;
	}
	public String getTask_type() {
		return task_type;
	}
	public void setTask_type(String task_type) {
		this.task_type = task_type;
	}
	public String getPingjia_content() {
		return pingjia_content;
	}
	public void setPingjia_content(String pingjia_content) {
		this.pingjia_content = pingjia_content;
	}
	public Integer getPingfen() {
		return pingfen;
	}
	public void setPingfen(Integer pingfen) {
		this.pingfen = pingfen;
	}
	public String getTask_jibie() {
		return task_jibie;
	}
	public void setTask_jibie(String task_jibie) {
		this.task_jibie = task_jibie;
	}
	public Integer getZhongyao_state() {
		return zhongyao_state;
	}
	public void setZhongyao_state(Integer zhongyao_state) {
		this.zhongyao_state = zhongyao_state;
	}
	public String getTask_jianyi() {
		return task_jianyi;
	}
	public void setTask_jianyi(String task_jianyi) {
		this.task_jianyi = task_jianyi;
	}

}
