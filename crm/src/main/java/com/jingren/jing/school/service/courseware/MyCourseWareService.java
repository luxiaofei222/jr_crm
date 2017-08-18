package com.jingren.jing.school.service.courseware;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.courseware.MyCourseWare;

public interface MyCourseWareService {
	/**
	 * 添加我的课件
	* @Title: MyCourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:57:26 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveMyCourseWare(MyCourseWare myCourseWare);
	/**
	 * 删除我的课件
	* @Title: MyCourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:58:11 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteMyCourseWare(MyCourseWare myCourseWare);
	/**
	 * 获取我的课件
	* @Title: MyCourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午9:59:37 
	* @version 网校+CRM系统 V1.0
	 */
	MyCourseWare getMyCourseWare(Map<String, Object> map);
	/**
	 * 我的课件列表
	* @Title: MyCourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午10:00:03 
	* @version 网校+CRM系统 V1.0
	 */
	List<MyCourseWare> getMyCourseWareList(Map<String, Object> map);
	/**
	 * 我的课件数量
	* @Title: MyCourseWareMapper.java 
	* @Package com.jingren.jing.school.dao.courseware 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 上午10:00:35 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getMyCourseWareNumber(Map<String, Object> map);
}
