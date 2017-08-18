package com.jingren.jing.common.organization.dao;

import java.util.List;
import java.util.Map;

import com.jingren.jing.common.organization.bean.Organization;

public interface OrganizationMapper {

	/**
	* @Title: OranizationMapper.java 
	* @Package com.jingren.jing.common.organization.dao 
	* @Description: TODO  保存组织结构信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午4:51:21 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveOranization(Organization organization);
	/**
	* @Title: OranizationMapper.java 
	* @Package com.jingren.jing.common.organization.dao 
	* @Description: TODO 修改组织结构
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午4:51:58 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateOranization(Organization organization);
	/**
	* @Title: OranizationMapper.java 
	* @Package com.jingren.jing.common.organization.dao 
	* @Description: TODO 删除组织结构
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午4:53:15 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteOranization(Integer or_id);
	/**
	* @Title: OranizationMapper.java 
	* @Package com.jingren.jing.common.organization.dao 
	* @Description: TODO 获取组织结构信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午4:53:56 
	* @version 网校+CRM系统 V1.0
	 */
	Organization getOranization(Map<String, Object> map);
	/**
	* @Title: OranizationMapper.java 
	* @Package com.jingren.jing.common.organization.dao 
	* @Description: TODO 组织结构列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午4:54:49 
	* @version 网校+CRM系统 V1.0
	 */
	List<Organization> getOranizationList(Map<String, Object> map);
	/**
	* @Title: OranizationMapper.java 
	* @Package com.jingren.jing.common.organization.dao 
	* @Description: TODO 组织结构数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午4:55:36 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getOranizationNumber(Map<String, Object> map);
}
