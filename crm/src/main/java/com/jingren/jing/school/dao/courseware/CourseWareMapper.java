package com.jingren.jing.school.dao.courseware;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.courseware.CourseWare;

public interface CourseWareMapper {

	/**
	 * 保存课件
	* @Title: CourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:43:35 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCourseWare(CourseWare courseWare);
	/**
	 * 修改课件
	* @Title: CourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:44:03 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCourseWare(CourseWare courseWare);
	/**
	 * 删除课件
	* @Title: CourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:44:44 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCourseWare(Integer ware_id);
	/**
	 * 获取课件信息
	* @Title: CourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:45:23 
	* @version 网校+CRM系统 V1.0
	 */
	CourseWare getCourseWare(Map<String, Object> map);
	/**
	 * 获取课件列表
	* @Title: CourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:45:50 
	* @version 网校+CRM系统 V1.0
	 */
	List<CourseWare> getCourseWareList(Map<String, Object> map);
	/**
	 * 获取课件数量
	* @Title: CourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:46:12 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCourseWareNumber(Map<String, Object> map);
}
