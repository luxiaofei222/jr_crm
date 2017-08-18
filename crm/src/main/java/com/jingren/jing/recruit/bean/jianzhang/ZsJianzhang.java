package com.jingren.jing.recruit.bean.jianzhang;

import java.io.Serializable;
import java.util.Date;

/**
* @Title: ZsJianzhang.java 
* @Package com.jingren.jing.recruit.bean.jianzhang 
* @Description: TODO 招生简章
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月26日 上午8:40:04 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class ZsJianzhang implements Serializable{

	private Integer jianzhang_id;
	private Integer zhaosheng_id;
	private String jianzhang_file;
	private String title;
	private String content;
	private Date tijiao_time;
	private String nianfen;//年份
	public Integer getJianzhang_id() {
		return jianzhang_id;
	}
	public void setJianzhang_id(Integer jianzhang_id) {
		this.jianzhang_id = jianzhang_id;
	}
	public Integer getZhaosheng_id() {
		return zhaosheng_id;
	}
	public void setZhaosheng_id(Integer zhaosheng_id) {
		this.zhaosheng_id = zhaosheng_id;
	}
	public String getJianzhang_file() {
		return jianzhang_file;
	}
	public void setJianzhang_file(String jianzhang_file) {
		this.jianzhang_file = jianzhang_file;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTijiao_time() {
		return tijiao_time;
	}
	public void setTijiao_time(Date tijiao_time) {
		this.tijiao_time = tijiao_time;
	}
	public String getNianfen() {
		return nianfen;
	}
	public void setNianfen(String nianfen) {
		this.nianfen = nianfen;
	}
}
