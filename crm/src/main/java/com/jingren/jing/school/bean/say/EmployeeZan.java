package com.jingren.jing.school.bean.say;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EmployeeZan implements Serializable{

	private Integer say_id;
	private Integer employee_id;
	public Integer getSay_id() {
		return say_id;
	}
	public void setSay_id(Integer say_id) {
		this.say_id = say_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	
}
