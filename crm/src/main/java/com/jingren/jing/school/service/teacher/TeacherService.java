package com.jingren.jing.school.service.teacher;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.teacher.Teacher;

public interface TeacherService {
	/**
	* @Title: TeacherMapper.java 
	* @Package com.jingren.jing.school.dao.teacher 
	* @Description: TODO 保存教师信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午4:02:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveTeacher(Teacher teacher);
	/**
	* @Title: TeacherMapper.java 
	* @Package com.jingren.jing.school.dao.teacher 
	* @Description: TODO 修改教师信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午4:02:52 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateTeacher(Teacher teacher);
	/**
	* @Title: TeacherMapper.java 
	* @Package com.jingren.jing.school.dao.teacher 
	* @Description: TODO 删除教师信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午4:03:21 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteTeacher(Integer teacher_id);
	/**
	* @Title: TeacherMapper.java 
	* @Package com.jingren.jing.school.dao.teacher 
	* @Description: TODO 获取教师信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午4:04:34 
	* @version 网校+CRM系统 V1.0
	 */
	Teacher getTeacher(Map<String, Object> map);
	/**
	* @Title: TeacherMapper.java 
	* @Package com.jingren.jing.school.dao.teacher 
	* @Description: TODO 获取教师列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午4:05:06 
	* @version 网校+CRM系统 V1.0
	 */
	List<Teacher> getTeacherList(Map<String, Object> map);
	/**
	* @Title: TeacherMapper.java 
	* @Package com.jingren.jing.school.dao.teacher 
	* @Description: TODO 获取教师数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午4:05:32 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getTeacherNumber(Map<String, Object> map);
}
