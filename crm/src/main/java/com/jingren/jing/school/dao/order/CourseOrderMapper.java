package com.jingren.jing.school.dao.order;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.order.CourseOrder;

public interface CourseOrderMapper {

	/**
	 * 保存订单信息
	* @Title: CourseOrderMapper.java 
	* @Package com.jingren.jing.school.dao.order 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 上午10:39:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCourseOrder(CourseOrder courseOrder);
	/**
	 * 
	* @Title: CourseOrderMapper.java 
	* @Package com.jingren.jing.school.dao.order 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 上午10:41:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCourseOrder(CourseOrder courseOrder);
	/**
	 * 删除订单
	* @Title: CourseOrderMapper.java 
	* @Package com.jingren.jing.school.dao.order 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 上午10:42:55 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCourseOrder(String order_id);
	/**
	 * 获取订单详细信息
	* @Title: CourseOrderMapper.java 
	* @Package com.jingren.jing.school.dao.order 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 上午10:43:25 
	* @version 网校+CRM系统 V1.0
	 */
	CourseOrder getCourseOrder(Map<String, Object> map);
	/**
	 * 获取订单列表
	* @Title: CourseOrderMapper.java 
	* @Package com.jingren.jing.school.dao.order 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 上午10:44:00 
	* @version 网校+CRM系统 V1.0
	 */
	List<CourseOrder> getCourseOrderList(Map<String, Object> map);
	/**
	 * 获取订单数量
	* @Title: CourseOrderMapper.java 
	* @Package com.jingren.jing.school.dao.order 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 上午10:44:45 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCouseOrderNumber(Map<String, Object> map);
}
