package com.jingren.jing.school.service.mycourse;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.mycourse.MyCourse;

public interface MyCourseService {

	/**
	 * 添加我的课程
	* @Title: MyCourseMapper.java 
	* @Package com.jingren.jing.school.dao.mycourse 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:09:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveMyCourse(MyCourse myCourse);
	/**
	 * 删除我的课程
	* @Title: MyCourseMapper.java 
	* @Package com.jingren.jing.school.dao.mycourse 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:10:30 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteMyCourse(Map<String, Object> map);
	/**
	 * 获取我的课程详情
	* @Title: MyCourseMapper.java 
	* @Package com.jingren.jing.school.dao.mycourse 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:13:15 
	* @version 网校+CRM系统 V1.0
	 */
	MyCourse getMyCourse(Map<String, Object> map);
	/**
	 * 获取我的课程列表
	* @Title: MyCourseMapper.java 
	* @Package com.jingren.jing.school.dao.mycourse 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:13:45 
	* @version 网校+CRM系统 V1.0
	 */
	List<MyCourse> getMyCourseList(Map<String, Object> map);
	/**
	 * 获取我的课程数量
	* @Title: MyCourseMapper.java 
	* @Package com.jingren.jing.school.dao.mycourse 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:14:18 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getMyCourseNumber(Map<String, Object> map);
	/**
	* @Title: MyCourseMapper.java 
	* @Package com.jingren.jing.school.dao.mycourse 
	* @Description: TODO 修改我的课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月14日 上午10:14:04 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateMyCourse(MyCourse myCourse);
}
