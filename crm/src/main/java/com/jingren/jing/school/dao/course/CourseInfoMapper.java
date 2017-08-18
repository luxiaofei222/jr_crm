package com.jingren.jing.school.dao.course;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.course.CourseInfo;

public interface CourseInfoMapper {

	/**
	 * 保存课程资讯
	* @Title: CourseInfoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:22:49 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCourseInfo(CourseInfo courseInfo);
	/**
	 * 修改课程资讯信息
	* @Title: CourseInfoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:36:53 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCourseInfo(CourseInfo courseInfo);
	/**
	 * 删除资讯信息
	* @Title: CourseInfoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:37:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCourseInfo(Integer courseinfo_id);
	/**
	 * 获取课程资讯信息
	* @Title: CourseInfoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:37:52 
	* @version 网校+CRM系统 V1.0
	 */
	CourseInfo getCourseInfo(Map<String, Object> map);
	/**
	 * 获取课程信息列表
	* @Title: CourseInfoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:38:40 
	* @version 网校+CRM系统 V1.0
	 */
	List<CourseInfo> getCourseInfoList(Map<String, Object> map);
	/**
	* @Title: CourseInfoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO 资讯列表二级分页
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月12日 上午10:04:48 
	* @version 网校+CRM系统 V1.0
	 */
	List<CourseInfo> getCourseInfoList_erji(Map<String, Object> map);
	/**
	 * 获取课程资讯数量
	* @Title: CourseInfoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:39:16 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCourseInfoNumber(Map<String, Object> map);
	/**
	 * 通过课程id获取相关资讯
	* @Title: CourseInfoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午7:09:00 
	* @version 网校+CRM系统 V1.0
	 */
	List<CourseInfo> getCourseInfoListByCourseId(Integer course_id);
}
