package com.jingren.jing.recruit.bean.help;

import java.io.Serializable;
import java.util.Date;
/**
* @Title: HelpCenter.java 
* @Package com.jingren.jing.recruit.bean.help 
* @Description: TODO 帮助中心
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月22日 下午2:42:23 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class HelpCenter implements Serializable{

	private Integer help_id;
	private String title;
	private String help_type;
	private String help_laiyuan;
	private String mate_title;
	private String mate_key;
	private String mate_dis;
	private String contente;
	private Date help_time;
	private String bianji;
	private Integer zhaosheng_id;
	public Integer getHelp_id() {
		return help_id;
	}
	public void setHelp_id(Integer help_id) {
		this.help_id = help_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHelp_type() {
		return help_type;
	}
	public void setHelp_type(String help_type) {
		this.help_type = help_type;
	}
	public String getHelp_laiyuan() {
		return help_laiyuan;
	}
	public void setHelp_laiyuan(String help_laiyuan) {
		this.help_laiyuan = help_laiyuan;
	}
	public String getMate_title() {
		return mate_title;
	}
	public void setMate_title(String mate_title) {
		this.mate_title = mate_title;
	}
	public String getMate_key() {
		return mate_key;
	}
	public void setMate_key(String mate_key) {
		this.mate_key = mate_key;
	}
	public String getMate_dis() {
		return mate_dis;
	}
	public void setMate_dis(String mate_dis) {
		this.mate_dis = mate_dis;
	}
	public String getContente() {
		return contente;
	}
	public void setContente(String contente) {
		this.contente = contente;
	}
	public Date getHelp_time() {
		return help_time;
	}
	public void setHelp_time(Date help_time) {
		this.help_time = help_time;
	}
	public String getBianji() {
		return bianji;
	}
	public void setBianji(String bianji) {
		this.bianji = bianji;
	}
	public Integer getZhaosheng_id() {
		return zhaosheng_id;
	}
	public void setZhaosheng_id(Integer zhaosheng_id) {
		this.zhaosheng_id = zhaosheng_id;
	}
	
}
