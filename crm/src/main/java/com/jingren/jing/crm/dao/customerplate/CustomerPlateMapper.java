package com.jingren.jing.crm.dao.customerplate;

import java.util.List;
import java.util.Map;

import com.jingren.jing.crm.bean.customerplate.CustomerPlate;


public interface CustomerPlateMapper {
	/**
	* @Title: CustomerPlateMapper.java 
	* @Package com.jingren.jing.crm.dao.CustomerPlate 
	* @Description: TODO 保存客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:23:36 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCustomerPlate(CustomerPlate customerPlate);
	/**
	* @Title: CustomerPlateMapper.java 
	* @Package com.jingren.jing.crm.dao.CustomerPlate 
	* @Description: TODO 修改客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:23:59 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCustomerPlate(CustomerPlate customerPlate);
	/**
	* @Title: CustomerPlateMapper.java 
	* @Package com.jingren.jing.crm.dao.CustomerPlate 
	* @Description: TODO 删除客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:24:29 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCustomerPlate(Integer customerPlate_id);
	/**
	* @Title: CustomerPlateMapper.java 
	* @Package com.jingren.jing.crm.dao.CustomerPlate 
	* @Description: TODO 获取客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:24:58 
	* @version 网校+CRM系统 V1.0
	 */
	CustomerPlate getCustomerPlate(Map<String, Object> map);
	/**
	* @Title: CustomerPlateMapper.java 
	* @Package com.jingren.jing.crm.dao.CustomerPlate 
	* @Description: TODO 获取客户信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:25:27 
	* @version 网校+CRM系统 V1.0
	 */
	List<CustomerPlate> getCustomerPlateList(Map<String, Object> map);
	/**
	* @Title: CustomerPlateMapper.java 
	* @Package com.jingren.jing.crm.dao.CustomerPlate 
	* @Description: TODO 获取客户信息数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 下午1:25:57 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getCustomerPlateNumber(Map<String, Object> map);
}
