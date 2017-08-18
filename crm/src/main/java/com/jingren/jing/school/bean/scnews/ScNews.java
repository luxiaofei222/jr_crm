package com.jingren.jing.school.bean.scnews;

import java.util.Date;

/**
 * 新闻动态
* @Title: ScNews.java 
* @Package com.jingren.jing.school.bean.scnews 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月8日 上午11:59:08 
* @version 网校+CRM系统 V1.0
 */
public class ScNews {

	private Integer news_id;
	private String news_title;
	private String news_content;
	private String news_abstrack;
	private String news_eidit;
	private Date news_time;
	private String news_type;
	private String news_laiyuan;//新闻来源
	private String news_yinyong;
	private String meta_title;//页面标题
	private String meta_dis;//页面描述
	private String meta_key;//meta关键字
	public Integer getNews_id() {
		return news_id;
	}
	public void setNews_id(Integer news_id) {
		this.news_id = news_id;
	}
	public String getNews_title() {
		return news_title;
	}
	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}
	public String getNews_content() {
		return news_content;
	}
	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}
	public String getNews_abstrack() {
		return news_abstrack;
	}
	public void setNews_abstrack(String news_abstrack) {
		this.news_abstrack = news_abstrack;
	}
	public String getNews_eidit() {
		return news_eidit;
	}
	public void setNews_eidit(String news_eidit) {
		this.news_eidit = news_eidit;
	}
	public String getNews_type() {
		return news_type;
	}
	public void setNews_type(String news_type) {
		this.news_type = news_type;
	}
	public Date getNews_time() {
		return news_time;
	}
	public void setNews_time(Date news_time) {
		this.news_time = news_time;
	}
	public String getNews_laiyuan() {
		return news_laiyuan;
	}
	public void setNews_laiyuan(String news_laiyuan) {
		this.news_laiyuan = news_laiyuan;
	}
	public String getNews_yinyong() {
		return news_yinyong;
	}
	public void setNews_yinyong(String news_yinyong) {
		this.news_yinyong = news_yinyong;
	}
	public String getMeta_title() {
		return meta_title;
	}
	public void setMeta_title(String meta_title) {
		this.meta_title = meta_title;
	}
	public String getMeta_dis() {
		return meta_dis;
	}
	public void setMeta_dis(String meta_dis) {
		this.meta_dis = meta_dis;
	}
	public String getMeta_key() {
		return meta_key;
	}
	public void setMeta_key(String meta_key) {
		this.meta_key = meta_key;
	}
	
}
