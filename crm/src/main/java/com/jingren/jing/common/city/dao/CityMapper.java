package com.jingren.jing.common.city.dao;

import java.util.List;
import java.util.Map;

import com.jingren.jing.common.city.bean.City;

public interface CityMapper {

	/**
	 * 获取城市列表
	* @Title: CityMapper.java 
	* @Package com.jingren.jing.common.city.dao 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月25日 上午10:24:30 
	* @version 网校+CRM系统 V1.0
	 */
	List<City> getCityList(Map<String, Object> map);
	/**
	 * 获取城市信息
	* @Title: CityMapper.java 
	* @Package com.jingren.jing.common.city.dao 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月25日 下午5:45:19 
	* @version 网校+CRM系统 V1.0
	 */
	City getCity(Map<String, Object> map);
}
