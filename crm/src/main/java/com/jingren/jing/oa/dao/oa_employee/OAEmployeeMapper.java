package com.jingren.jing.oa.dao.oa_employee;

import java.util.List;
import java.util.Map;

import com.jingren.jing.oa.bean.oa_employee.OAEmployee;

public interface OAEmployeeMapper {

	/**
	* @Title: OAEmployeeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_employee 
	* @Description: TODO 保存员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午2:14:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveOAEmployee(OAEmployee oaEmployee);
	/**
	* @Title: OAEmployeeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_employee 
	* @Description: TODO 修改员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午2:14:57 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateOAEmployee(OAEmployee oaEmployee);
	/**
	* @Title: OAEmployeeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_employee 
	* @Description: TODO 删除员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午2:15:45 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteOAEmployee(Integer oa_employee_id);
	/**
	* @Title: OAEmployeeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_employee 
	* @Description: TODO 获取员工信息详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午2:16:15 
	* @version 网校+CRM系统 V1.0
	 */
	OAEmployee getOAEmployee(Map<String, Object> map);
	/**
 	* @Title: OAEmployeeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_employee 
	* @Description: TODO 获取员工信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午2:16:48 
	* @version 网校+CRM系统 V1.0
	 */
	List<OAEmployee> getOAEmployeeList(Map<String, Object> map);
	/**
	* @Title: OAEmployeeMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_employee 
	* @Description: TODO 获取员工信息数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午2:17:22 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getOAEmployeeNumber(Map<String, Object> map);
}
