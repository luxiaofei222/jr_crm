package com.jingren.jing.school.service.user;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.user.User;


public interface UserService {
	/**
	 * 添加用户信息
	* @Title: UserMapper.java 
	* @Package com.jingren.jing.school.dao.user 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:42:34 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveUser(User user);
	/**
	 * 修改用户信息
	* @Title: UserMapper.java 
	* @Package com.jingren.jing.school.dao.user 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:42:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateUser(User user);
	/**
	 * 删除用户信息
	* @Title: UserMapper.java 
	* @Package com.jingren.jing.school.dao.user 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:42:59 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteUser(Integer userid);
	/**
	 * 获取用户详情
	* @Title: UserMapper.java 
	* @Package com.jingren.jing.school.dao.user 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:43:23 
	* @version 网校+CRM系统 V1.0
	 */
	User getUser(Map<String, Object> map);
	/**
	 * 获取用户列表
	* @Title: UserMapper.java 
	* @Package com.jingren.jing.school.dao.user 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:44:00 
	* @version 网校+CRM系统 V1.0
	 */
	List<User> getUserlist(Map<String, Object> map);
	/**
	 * 获取用户数量
	* @Title: UserMapper.java 
	* @Package com.jingren.jing.school.dao.user 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午8:44:26 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getUserNumber(Map<String, Object> map);
	/**
	 * 修改绑定邮箱或者手机号
	* @Title: UserMapper.java 
	* @Package com.jingren.jing.school.dao.user 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午8:59:03 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateUserMailAndPhone(User user);
}
