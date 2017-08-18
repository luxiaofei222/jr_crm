package com.jingren.jing.school.back.letter;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: Letter.java 
* @Package com.jingren.jing.school.back.letter 
* @Description: TODO 私信
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月30日 下午3:17:37 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Letter implements Serializable{

	private Integer letter_id;
	private Date letter_time;
	private Integer fasong_employee;
	private Integer jieshou_employee;
	private String is_read;
	public Integer getLetter_id() {
		return letter_id;
	}
	public void setLetter_id(Integer letter_id) {
		this.letter_id = letter_id;
	}
	public Date getLetter_time() {
		return letter_time;
	}
	public void setLetter_time(Date letter_time) {
		this.letter_time = letter_time;
	}
	public Integer getFasong_employee() {
		return fasong_employee;
	}
	public void setFasong_employee(Integer fasong_employee) {
		this.fasong_employee = fasong_employee;
	}
	public Integer getJieshou_employee() {
		return jieshou_employee;
	}
	public void setJieshou_employee(Integer jieshou_employee) {
		this.jieshou_employee = jieshou_employee;
	}
	public String getIs_read() {
		return is_read;
	}
	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}
	
}
