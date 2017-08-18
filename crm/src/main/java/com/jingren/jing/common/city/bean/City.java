package com.jingren.jing.common.city.bean;

import java.io.Serializable;
/**
 * 全国城市列表
* @Title: City.java 
* @Package com.jingren.jing.common.city.bean 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月25日 上午10:21:38 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class City implements Serializable{

	private Integer id;
	private String name;
	private Integer parentid;
	private String shortname;
	private Integer leveltype;
	private String citycode;
	private String zipcode;
	private String lng;
	private String lat;
	private String pinyin;
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentid() {
		return parentid;
	}
	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public Integer getLeveltype() {
		return leveltype;
	}
	public void setLeveltype(Integer leveltype) {
		this.leveltype = leveltype;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
