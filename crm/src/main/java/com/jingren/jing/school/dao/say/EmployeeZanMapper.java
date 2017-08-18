package com.jingren.jing.school.dao.say;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.say.EmployeeZan;

public interface EmployeeZanMapper {

	/**
	* @Title: EmployeeZanMapper.java 
	* @Package com.jingren.jing.school.dao.say 
	* @Description: TODO 增加点赞
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 上午8:45:41 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEmployeeZan(EmployeeZan employeeZan);
	/**
	* @Title: EmployeeZanMapper.java 
	* @Package com.jingren.jing.school.dao.say 
	* @Description: TODO 获取列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月30日 上午9:17:16 
	* @version 网校+CRM系统 V1.0
	 */
	List<EmployeeZan> getemployeeZans(Map<String, Object> map);
	
}
