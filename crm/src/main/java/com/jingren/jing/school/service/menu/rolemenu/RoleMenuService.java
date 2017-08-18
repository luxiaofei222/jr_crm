package com.jingren.jing.school.service.menu.rolemenu;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.menu.rolemenu.RoleMenu;

public interface RoleMenuService {
	/**
	* @Title: RoleMenuMapper.java 
	* @Package com.jingren.jing.school.dao.menu.rolemenu 
	* @Description: TODO 获取菜单-角色列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 下午1:08:02 
	* @version 网校+CRM系统 V1.0
	 */
	List<RoleMenu> getRoleMenuList(Map<String, Object> map);
	/**
	* @Title: RoleMenuMapper.java 
	* @Package com.jingren.jing.school.dao.menu.rolemenu 
	* @Description: TODO 保存角色菜单列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 下午1:08:42 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveRoleMenu(RoleMenu roleMenu);
	/**
	* @Title: RoleMenuMapper.java 
	* @Package com.jingren.jing.school.dao.menu.rolemenu 
	* @Description: TODO 删除菜单-角色信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 下午1:09:24 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteRoleMenu(Integer role_id);
}
