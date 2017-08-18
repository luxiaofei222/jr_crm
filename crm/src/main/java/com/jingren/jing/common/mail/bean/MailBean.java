package com.jingren.jing.common.mail.bean;

public class MailBean {
	
	public static final String ENCODEING = "UTF-8";
	private Integer mail_id;
	private String host="smtp.163.com"; // 邮箱服务器
	private String sender="17731183111@163.com"; // 发送的邮箱
	private String receiver; // 接受的邮箱
	private String name="京人教育"; //昵称
	private String username="17731183111"; // 用户名
	private String password="lu070205"; // 密码
	private String subject; // 主题
	private String message; // 内容
	private Integer type;//类型
	private String content;
	public Integer getMail_id() {
		return mail_id;
	}
	public void setMail_id(Integer mail_id) {
		this.mail_id = mail_id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public static String getEncodeing() {
		return ENCODEING;
	}

}
