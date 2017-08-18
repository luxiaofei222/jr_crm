package com.jingren.jing.school.service.course;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.course.CourseVideo;

public interface CourseVideoService {
	/**
	 * 保存课程视频
	* @Title: CourseVideoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:46:46 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCourseVideo(CourseVideo courseVideo);
	/**
	 * 修改课程视频信息
	* @Title: CourseVideoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:47:19 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCourseVideo(CourseVideo courseVideo);
	/**
	 * 删除课程视频信息
	* @Title: CourseVideoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:47:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCourseVideo(Integer coursevideo_id);
	/**
	 * 获取课程视频信息详情
	* @Title: CourseVideoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:48:30 
	* @version 网校+CRM系统 V1.0
	 */
	CourseVideo getCourseVideo(Map<String, Object> map);
	/**
	 * 获取课程视频列表
	* @Title: CourseVideoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:49:18 
	* @version 网校+CRM系统 V1.0
	 */
	List<CourseVideo> getCourseVideoList(Map<String, Object> map);
	/**
	 * 推荐课程列表
	* @Title: CourseVideoService.java 
	* @Package com.jingren.jing.school.service.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午4:11:12 
	* @version 网校+CRM系统 V1.0
	 */
	List<CourseVideo> getCourseVideoTuijianList(Map<String, Object> map);
	
	/**
	 * 获取课程视频数量
	* @Title: CourseVideoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月4日 上午9:49:54 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCourseVideoNumber(Map<String, Object> map);
	/**
	* @Title: CourseVideoMapper.java 
	* @Package com.jingren.jing.school.dao.course 
	* @Description: TODO 一键禁止点击
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月28日 下午6:21:17 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateVideoDianji(CourseVideo courseVideo);
}
