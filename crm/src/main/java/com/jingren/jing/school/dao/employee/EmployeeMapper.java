package com.jingren.jing.school.dao.employee;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.employee.Employee;

public interface EmployeeMapper {

	/**
	* @Title: EmployeeMapper.java 
	* @Package com.jingren.jing.school.dao.employee 
	* @Description: TODO 保存员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午10:34:06 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEmployee(Employee employee);
	/**
	* @Title: EmployeeMapper.java 
	* @Package com.jingren.jing.school.dao.employee 
	* @Description: TODO 修改员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午10:34:38 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEmployee(Employee employee);
	/**
	* @Title: EmployeeMapper.java 
	* @Package com.jingren.jing.school.dao.employee 
	* @Description: TODO 删除员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午10:35:13 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteEmployee(Integer employee_id);
	/**
	* @Title: EmployeeMapper.java 
	* @Package com.jingren.jing.school.dao.employee 
	* @Description: TODO 获取员工列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午10:36:02 
	* @version 网校+CRM系统 V1.0
	 */
	List<Employee> getEmployeeList(Map<String, Object> map);
	/**
	* @Title: EmployeeMapper.java 
	* @Package com.jingren.jing.school.dao.employee 
	* @Description: TODO 获取员工数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午10:36:36 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getEmployeeNumber(Map<String, Object> map);
	/**
	* @Title: EmployeeMapper.java 
	* @Package com.jingren.jing.school.dao.employee 
	* @Description: TODO 获取员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月1日 上午10:37:01 
	* @version 网校+CRM系统 V1.0
	 */
	Employee getEmployee(Map<String, Object> map);
	/**
	* @Title: EmployeeMapper.java 
	* @Package com.jingren.jing.school.dao.employee 
	* @Description: TODO 通过ID获取员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 下午2:33:21 
	* @version 网校+CRM系统 V1.0
	 */
	Employee getEmployeeByid(Integer employee_id);
	/**
	* @Title: EmployeeMapper.java 
	* @Package com.jingren.jing.school.dao.employee 
	* @Description: TODO 获取最大的工号
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月13日 下午4:24:04 
	* @version 网校+CRM系统 V1.0
	 */
	String getEmployeeJobnumber();
}
