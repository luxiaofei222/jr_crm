package com.jingren.jing.school.dao.role;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.role.Role;

public interface RoleMapper {

	/**
	* @Title: RoleMapper.java 
	* @Package com.jingren.jing.school.dao.role 
	* @Description: TODO 保存角色信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午11:01:33 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveRole(Role role);
	/**
	* @Title: RoleMapper.java 
	* @Package com.jingren.jing.school.dao.role 
	* @Description: TODO 删除角色信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午11:02:12 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteRole(Integer role_id);
	/**
	* @Title: RoleMapper.java 
	* @Package com.jingren.jing.school.dao.role 
	* @Description: TODO 获取角色信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午11:02:52 
	* @version 网校+CRM系统 V1.0
	 */
	Role getRole(Map<String, Object> map);
	/**
	* @Title: RoleMapper.java 
	* @Package com.jingren.jing.school.dao.role 
	* @Description: TODO 获取角色列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午11:03:24 
	* @version 网校+CRM系统 V1.0
	 */
	List<Role> getRoleList(Map<String, Object> map);
	/**
	* @Title: RoleMapper.java 
	* @Package com.jingren.jing.school.dao.role 
	* @Description: TODO 获取角色数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午11:03:49 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getRoleNumber(Map<String, Object> map);
}
