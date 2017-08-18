package com.jingren.jing.crm.service.customer;

import java.util.List;
import java.util.Map;

import com.jingren.jing.crm.bean.customer.Customer;

public interface CustomerService {
	/**
	* @Title: CustomerMapper.java 
	* @Package com.jingren.jing.crm.dao.customer 
	* @Description: TODO 保存客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:23:36 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCustomer(Customer customer);
	/**
	* @Title: CustomerMapper.java 
	* @Package com.jingren.jing.crm.dao.customer 
	* @Description: TODO 修改客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:23:59 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCustomer(Customer customer);
	/**
	* @Title: CustomerMapper.java 
	* @Package com.jingren.jing.crm.dao.customer 
	* @Description: TODO 删除客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:24:29 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCustomer(Integer customer_id);
	/**
	* @Title: CustomerMapper.java 
	* @Package com.jingren.jing.crm.dao.customer 
	* @Description: TODO 获取客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:24:58 
	* @version 网校+CRM系统 V1.0
	 */
	Customer getCustomer(Map<String, Object> map);
	/**
	* @Title: CustomerMapper.java 
	* @Package com.jingren.jing.crm.dao.customer 
	* @Description: TODO 获取客户信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:25:27 
	* @version 网校+CRM系统 V1.0
	 */
	List<Customer> getCustomerList(Map<String, Object> map);
	/**
	* @Title: CustomerMapper.java 
	* @Package com.jingren.jing.crm.dao.customer 
	* @Description: TODO 获取客户信息数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:25:57 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCustomerNumber(Map<String, Object> map);
	/**
	* @Title: CustomerMapper.java 
	* @Package com.jingren.jing.crm.dao.customer 
	* @Description: TODO 通过ID获取联系人
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 下午4:00:18 
	* @version 网校+CRM系统 V1.0
	 */
	Customer getCustomerByid(Integer customer_id);
	/**
	* @Title: CustomerMapper.java 
	* @Package com.jingren.jing.crm.dao.customer 
	* @Description: TODO 清除离职员工的痕迹
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月24日 上午11:31:36 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCustomerLizhi(Integer employee_id);
}
