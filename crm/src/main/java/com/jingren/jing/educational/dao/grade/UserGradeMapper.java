package com.jingren.jing.educational.dao.grade;

import java.util.List;
import java.util.Map;

import com.jingren.jing.educational.bean.grade.UserGrade;

public interface UserGradeMapper {

	/**
	* @Title: UserGradeMapper.java 
	* @Package com.jingren.jing.educational.dao.grade 
	* @Description: TODO 保存学员成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午4:20:30 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveUserGrade(UserGrade userGrade);
	/**
	* @Title: UserGradeMapper.java 
	* @Package com.jingren.jing.educational.dao.grade 
	* @Description: TODO 修改用户成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午4:21:20 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateUserGrade(UserGrade userGrade);
	
	/**
	* @Title: UserGradeMapper.java 
	* @Package com.jingren.jing.educational.dao.grade 
	* @Description: TODO 删除用户成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午4:21:35 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteUserGrade(Map<String, Object> map);
	/**
	* @Title: UserGradeMapper.java 
	* @Package com.jingren.jing.educational.dao.grade 
	* @Description: TODO 获取学员成绩详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午4:23:29 
	* @version 网校+CRM系统 V1.0
	 */
	UserGrade getUserGrade(Map<String, Object> map);
	/**
	* @Title: UserGradeMapper.java 
	* @Package com.jingren.jing.educational.dao.grade 
	* @Description: TODO 获取成绩列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午4:24:13 
	* @version 网校+CRM系统 V1.0
	 */
	List<UserGrade> getUserGradeList(Map<String, Object> map);
	/**
	* @Title: UserGradeMapper.java 
	* @Package com.jingren.jing.educational.dao.grade 
	* @Description: TODO 获取用户成绩数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午4:24:40 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getUserGradeNumber(Map<String, Object> map);
}
