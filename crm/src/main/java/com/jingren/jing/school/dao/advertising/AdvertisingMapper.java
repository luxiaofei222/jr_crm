package com.jingren.jing.school.dao.advertising;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.advertising.Advertising;

public interface AdvertisingMapper {

	/**
	 * 保存广告信息
	* @Title: AdvertisingMapper.java 
	* @Package com.jingren.jing.school.dao.advertising 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 上午11:11:22 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveAdvertising(Advertising advertising);
	/**
	 * 删除广告信息
	* @Title: AdvertisingMapper.java 
	* @Package com.jingren.jing.school.dao.advertising 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 上午11:12:23 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteAdvertising(Integer adver_id);
	/**
	 * 广告列表
	* @Title: AdvertisingMapper.java 
	* @Package com.jingren.jing.school.dao.advertising 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 上午11:15:24 
	* @version 网校+CRM系统 V1.0
	 */
	List<Advertising> getAdvertisingList(Map<String, Object> map);
	/**
	 * 获取广告数量
	* @Title: AdvertisingMapper.java 
	* @Package com.jingren.jing.school.dao.advertising 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 上午11:16:00 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getAdvertisingNumber(Map<String, Object> map);
	/**
	* @Title: AdvertisingMapper.java 
	* @Package com.jingren.jing.school.dao.advertising 
	* @Description: TODO 修改广告信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午4:01:16 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateAdvertising(Advertising advertising);
	/**
	* @Title: AdvertisingMapper.java 
	* @Package com.jingren.jing.school.dao.advertising 
	* @Description: TODO 获取广告信息
	* @author 鲁晓飞 MR.Lu    
	* @date 2016年12月7日 下午5:07:07 
	* @version 网校+CRM系统 V1.0
	 */
	Advertising getAdvertising(Map<String, Object> map);
}
