package com.jingren.jing.question.dao.zhentiyear;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.zhentiyear.ZhentiYears;

public interface ZhentiYearsMapper {

	/**
	* @Title: ZhentiYearsMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiyear 
	* @Description: TODO 保存历史真题标题信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 上午10:12:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveZhentiYears(ZhentiYears zhentiYears);
	/**
	* @Title: ZhentiYearsMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiyear 
	* @Description: TODO 修改历年真题信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 上午10:13:55 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateZhentiYears(ZhentiYears zhentiYears);
	/**
	* @Title: ZhentiYearsMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiyear 
	* @Description: TODO 获取真题信息详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 上午10:22:28 
	* @version 网校+CRM系统 V1.0
	 */
	ZhentiYears getZhentiYears(Map<String, Object> map);
	/**
	* @Title: ZhentiYearsMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiyear 
	* @Description: TODO 获取历年真题列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 上午10:23:04 
	* @version 网校+CRM系统 V1.0
	 */
	List<ZhentiYears> getZhentiYearsList(Map<String, Object> map);
	/**
	* @Title: ZhentiYearsMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiyear 
	* @Description: TODO 获取历年真题数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 上午10:23:38 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getZhentiYearsNumber(Map<String, Object> map);
	/**
	* @Title: ZhentiYearsMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiyear 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 上午10:32:23 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteZhentiYears(Integer zhenti_id);
}
