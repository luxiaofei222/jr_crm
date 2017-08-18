package com.jingren.jing.school.bean.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.jingren.jing.school.bean.user.User;
/**
 * 系统信息
* @Title: Message.java 
* @Package com.jingren.jing.school.bean.message 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月28日 上午11:07:05 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class JRMessage implements Serializable{
	
	private Integer message_id;
	private Integer user_id;
	private Integer employee_id;
	private Integer parent_id;
	private String message_title;
	private String message_content;
	private String message_type;
	private Date message_time;
	private String is_read;//是否读取:是否读取
	private String send_type;//前台：前台发送给系统 后台：系统发送给用户
	private List<Annexes> annexes;//附件列表
	private String is_show;//是否显示
	private String user_nickname;//用户昵称
	private User user;
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getMessage_title() {
		return message_title;
	}
	public void setMessage_title(String message_title) {
		this.message_title = message_title;
	}
	public String getMessage_content() {
		return message_content;
	}
	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public Date getMessage_time() {
		return message_time;
	}
	public void setMessage_time(Date message_time) {
		this.message_time = message_time;
	}
	public String getIs_read() {
		return is_read;
	}
	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}
	public String getSend_type() {
		return send_type;
	}
	public void setSend_type(String send_type) {
		this.send_type = send_type;
	}
	public List<Annexes> getAnnexes() {
		return annexes;
	}
	public void setAnnexes(List<Annexes> annexes) {
		this.annexes = annexes;
	}
	public String getIs_show() {
		return is_show;
	}
	public void setIs_show(String is_show) {
		this.is_show = is_show;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
