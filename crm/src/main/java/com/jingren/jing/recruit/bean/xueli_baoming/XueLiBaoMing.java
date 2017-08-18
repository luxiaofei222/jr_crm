package com.jingren.jing.recruit.bean.xueli_baoming;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class XueLiBaoMing implements Serializable{

	private Integer baoming_id;
	private String baoming_diqu;
	private Integer zhaosheng_id;
	private String xuexi_zhongxin;
	private String yuanxiao;
	private String cnegci;
	private String zhuanye;
	private String user_name;
	private String card_number;
	private String user_phone;
	private String user_mail;
	private String qq;
	private String weixin;
	private Date baoming_time;
	private String type;
	private String user_ip;//用户IP
	private String ip_city;
	
	public String getIp_city() {
		return ip_city;
	}
	public void setIp_city(String ip_city) {
		this.ip_city = ip_city;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public Integer getBaoming_id() {
		return baoming_id;
	}
	public void setBaoming_id(Integer baoming_id) {
		this.baoming_id = baoming_id;
	}
	public String getBaoming_diqu() {
		return baoming_diqu;
	}
	public void setBaoming_diqu(String baoming_diqu) {
		this.baoming_diqu = baoming_diqu;
	}
	public Integer getZhaosheng_id() {
		return zhaosheng_id;
	}
	public void setZhaosheng_id(Integer zhaosheng_id) {
		this.zhaosheng_id = zhaosheng_id;
	}
	public String getXuexi_zhongxin() {
		return xuexi_zhongxin;
	}
	public void setXuexi_zhongxin(String xuexi_zhongxin) {
		this.xuexi_zhongxin = xuexi_zhongxin;
	}
	public String getYuanxiao() {
		return yuanxiao;
	}
	public void setYuanxiao(String yuanxiao) {
		this.yuanxiao = yuanxiao;
	}
	public String getCnegci() {
		return cnegci;
	}
	public void setCnegci(String cnegci) {
		this.cnegci = cnegci;
	}
	public String getZhuanye() {
		return zhuanye;
	}
	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public Date getBaoming_time() {
		return baoming_time;
	}
	public void setBaoming_time(Date baoming_time) {
		this.baoming_time = baoming_time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
