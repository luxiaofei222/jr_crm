package com.jingren.jing.school.dao.menu;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.menu.Menu;

public interface MenuMapper {

	/**
	* @Title: MenuMapper.java 
	* @Package com.jingren.jing.school.dao.menu 
	* @Description: TODO 获取菜单列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 下午1:05:16 
	* @version 网校+CRM系统 V1.0
	 */
	List<Menu> getMenuList(Map<String, Object> map);
	/**
	* @Title: MenuMapper.java 
	* @Package com.jingren.jing.school.dao.menu 
	* @Description: TODO 获取菜单数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 下午1:05:55 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getMenuNumber(Map<String, Object> map);
	/**
	* @Title: MenuMapper.java 
	* @Package com.jingren.jing.school.dao.menu 
	* @Description: TODO 获取菜单信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 下午1:06:21 
	* @version 网校+CRM系统 V1.0
	 */
	Menu getMenu(Map<String, Object> map);
	/**
	* @Title: MenuMapper.java 
	* @Package com.jingren.jing.school.dao.menu 
	* @Description: TODO 查询菜单权限
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月19日 下午12:50:46 
	* @version 网校+CRM系统 V1.0
	 */
	List<Menu> getMenuListPermission(Map<String, Object> map);
}
