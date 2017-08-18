package com.jingren.jing.school.dao.course;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.course.CourseMenu;

public interface CourseMenuMapper {
	/**
	 * 修改课程目录
	* @Title: CourerMenuMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:11:52 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCourserMuen(CourseMenu courseMenu);
	/**
	 * 获取课程目录信息
	* @Title: CourerMenuMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:12:57 
	* @version 网校+CRM系统 V1.0
	 */
	CourseMenu getCouerseMenu(Map<String, Object> map);
	/**
	 * 获取课程列表
	* @Title: CourerMenuMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:14:22 
	* @version 网校+CRM系统 V1.0
	 */
	List<CourseMenu> getCourserMenuList(Map<String, Object> map);
	/**
	 * 获取课程目录数量
	* @Title: CourerMenuMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:15:36 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCourseMenuNumber(Map<String, Object> map);

}
