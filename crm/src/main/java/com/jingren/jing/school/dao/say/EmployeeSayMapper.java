package com.jingren.jing.school.dao.say;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.say.EmployeeSay;

public interface EmployeeSayMapper {

	/**
	* @Title: EmployeeSayMapper.java 
	* @Package com.jingren.jing.school.dao.say 
	* @Description: TODO 保存说说
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 上午8:38:36 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEmployeeSay(EmployeeSay employeeSay);
	/**
	* @Title: EmployeeSayMapper.java 
	* @Package com.jingren.jing.school.dao.say 
	* @Description: TODO 编辑说说
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 上午8:39:21 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEmployeeSay(EmployeeSay employeeSay);
	/**
	* @Title: EmployeeSayMapper.java 
	* @Package com.jingren.jing.school.dao.say 
	* @Description: TODO 删除说说
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 上午8:39:48 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteEmployeeSay(Integer say_id);
	/**
	* @Title: EmployeeSayMapper.java 
	* @Package com.jingren.jing.school.dao.say 
	* @Description: TODO 获取说说详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 上午8:43:29 
	* @version 网校+CRM系统 V1.0
	 */
	EmployeeSay getEmployeeSay(Map<String, Object> map);
	/**
	* @Title: EmployeeSayMapper.java 
	* @Package com.jingren.jing.school.dao.say 
	* @Description: TODO 获取说说列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 上午8:43:54 
	* @version 网校+CRM系统 V1.0
	 */
	List<EmployeeSay> getEmployeeSayList(Map<String, Object> map);
	/**
	* @Title: EmployeeSayMapper.java 
	* @Package com.jingren.jing.school.dao.say 
	* @Description: TODO 获取说说数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 上午8:44:23 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getEmployeeSayNumber(Map<String, Object> map);
}
