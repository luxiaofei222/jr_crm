package com.jingren.jing.recruit.dao.material;

import java.util.List;
import java.util.Map;

import com.jingren.jing.recruit.bean.material.Material;

public interface MaterialMapper {

	/**
	* @Title: MaterialMapper.java 
	* @Package com.jingren.jing.recruit.dao.material 
	* @Description: TODO 保存教材计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月20日 下午1:49:34 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveMaterial(Material material);
	/**
	* @Title: MaterialMapper.java 
	* @Package com.jingren.jing.recruit.dao.material 
	* @Description: TODO 修改教材计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月20日 下午1:51:12 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateMaterial(Material material);
	/**
	* @Title: MaterialMapper.java 
	* @Package com.jingren.jing.recruit.dao.material 
	* @Description: TODO 删除教材计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月20日 下午1:51:48 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteMaterial(Map<String, Object> map);
	/**
	* @Title: MaterialMapper.java 
	* @Package com.jingren.jing.recruit.dao.material 
	* @Description: TODO 获取教材计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月20日 下午1:52:39 
	* @version 网校+CRM系统 V1.0
	 */
	Material getMaterial(Map<String, Object> map);
	/**
	* @Title: MaterialMapper.java 
	* @Package com.jingren.jing.recruit.dao.material 
	* @Description: TODO 获取教材计划列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月20日 下午1:53:13 
	* @version 网校+CRM系统 V1.0
	 */
	List<Material> getMaterialList(Map<String, Object> map);
	/**
	* @Title: MaterialMapper.java 
	* @Package com.jingren.jing.recruit.dao.material 
	* @Description: TODO 教材计划数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月20日 下午1:53:36 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getMaterialNumber(Map<String, Object> map);
}
