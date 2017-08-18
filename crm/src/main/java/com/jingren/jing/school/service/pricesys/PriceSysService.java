package com.jingren.jing.school.service.pricesys;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.pricesys.PriceSys;

public interface PriceSysService {
	/**
	* @Title: PriceSysMapper.java 
	* @Package com.jingren.jing.school.dao.pricesys 
	* @Description: TODO 添加低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午3:04:15 
	* @version 网校+CRM系统 V1.0
	 */
	boolean addPriceSys(PriceSys priceSys);
	/**
	* @Title: PriceSysMapper.java 
	* @Package com.jingren.jing.school.dao.pricesys 
	* @Description: TODO 修改低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午3:05:17 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updatePriceSys(PriceSys priceSys);
	/**
	* @Title: PriceSysMapper.java 
	* @Package com.jingren.jing.school.dao.pricesys 
	* @Description: TODO 删除低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午3:05:52 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deletePriceSys(Integer price_id);
	/**
	* @Title: PriceSysMapper.java 
	* @Package com.jingren.jing.school.dao.pricesys 
	* @Description: TODO 获取低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午3:06:23 
	* @version 网校+CRM系统 V1.0
	 */
	PriceSys getPriceSys(Map<String, Object> map);
	/**
	* @Title: PriceSysMapper.java 
	* @Package com.jingren.jing.school.dao.pricesys 
	* @Description: TODO 获取低价体系列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午3:07:06 
	* @version 网校+CRM系统 V1.0
	 */
	List<PriceSys> getPriceSysList(Map<String, Object> map);
	/**
	* @Title: PriceSysMapper.java 
	* @Package com.jingren.jing.school.dao.pricesys 
	* @Description: TODO 低价体系数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午3:07:44 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getPriceSysNumber(Map<String, Object> map);
}
