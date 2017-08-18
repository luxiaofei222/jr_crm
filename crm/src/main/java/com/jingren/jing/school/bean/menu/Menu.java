package com.jingren.jing.school.bean.menu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
* @Title: Menu.java 
* @Package com.jingren.jing.school.bean.menu 
* @Description: TODO 菜单信息
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月1日 上午11:36:31 
* @version 网校+CRM系统 V1.0
 */
@SuppressWarnings("serial")
public class Menu implements Serializable{

	
	private  Integer menu_id;//菜单ID
	private  Integer parent_id;
	private  String menu_name;
	private  String menu_link;//链接
	private  String menu_dis;
	private  String menu_icon;//菜单图标
	private  Date menu_time;
	private Integer menu_leval;//菜单级别
	private List<Menu> menus_sub;//子级菜单
	private Integer menu_limit;
	
	private boolean is_permission=false;//是否具有权限
	public Integer getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}
	public Integer getParent_id() {
		return parent_id;
	}
	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_link() {
		return menu_link;
	}
	public void setMenu_link(String menu_link) {
		this.menu_link = menu_link;
	}
	public String getMenu_dis() {
		return menu_dis;
	}
	public void setMenu_dis(String menu_dis) {
		this.menu_dis = menu_dis;
	}
	public String getMenu_icon() {
		return menu_icon;
	}
	public void setMenu_icon(String menu_icon) {
		this.menu_icon = menu_icon;
	}
	public Date getMenu_time() {
		return menu_time;
	}
	public void setMenu_time(Date menu_time) {
		this.menu_time = menu_time;
	}
	public Integer getMenu_leval() {
		return menu_leval;
	}
	public void setMenu_leval(Integer menu_leval) {
		this.menu_leval = menu_leval;
	}
	public List<Menu> getMenus_sub() {
		return menus_sub;
	}
	public void setMenus_sub(List<Menu> menus_sub) {
		this.menus_sub = menus_sub;
	}
	public Integer getMenu_limit() {
		return menu_limit;
	}
	public void setMenu_limit(Integer menu_limit) {
		this.menu_limit = menu_limit;
	}
	public boolean isIs_permission() {
		return is_permission;
	}
	public void setIs_permission(boolean is_permission) {
		this.is_permission = is_permission;
	}
	
}
