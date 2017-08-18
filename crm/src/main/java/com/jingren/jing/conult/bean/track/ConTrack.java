package com.jingren.jing.conult.bean.track;

import java.io.Serializable;
import java.util.Date;

import com.jingren.jing.school.bean.employee.Employee;

@SuppressWarnings("serial")
public class ConTrack implements Serializable{

	private Integer track_id;
	private Integer employee_id;
	private Integer consult_id;
	private String content;
	private Date track_time;
	
	private Integer conult_state;//追踪状态
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Integer getConult_state() {
		return conult_state;
	}
	public void setConult_state(Integer conult_state) {
		this.conult_state = conult_state;
	}
	public Integer getTrack_id() {
		return track_id;
	}
	public void setTrack_id(Integer track_id) {
		this.track_id = track_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getConsult_id() {
		return consult_id;
	}
	public void setConsult_id(Integer consult_id) {
		this.consult_id = consult_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTrack_time() {
		return track_time;
	}
	public void setTrack_time(Date track_time) {
		this.track_time = track_time;
	}
	
}
