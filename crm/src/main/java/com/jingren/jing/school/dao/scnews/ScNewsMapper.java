package com.jingren.jing.school.dao.scnews;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.scnews.ScNews;

public interface ScNewsMapper {

	/**
	 * 保存信息信息
	* @Title: ScNewsMapper.java 
	* @Package com.jingren.jing.school.dao.scnews 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午1:03:28 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveNews(ScNews scNews);
	/**
	 * 修改新闻信息
	* @Title: ScNewsMapper.java 
	* @Package com.jingren.jing.school.dao.scnews 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午1:03:55 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateNews(ScNews scNews);
	/**
	 * 删除新闻信息
	* @Title: ScNewsMapper.java 
	* @Package com.jingren.jing.school.dao.scnews 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午1:04:31 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteNews(Integer news_id);
	/**
	 * 获取新闻信息
	* @Title: ScNewsMapper.java 
	* @Package com.jingren.jing.school.dao.scnews 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午1:05:08 
	* @version 网校+CRM系统 V1.0
	 */
	ScNews getNews(Map<String, Object> map);
	/**
	 * 获取新闻列表
	* @Title: ScNewsMapper.java 
	* @Package com.jingren.jing.school.dao.scnews 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午1:05:39 
	* @version 网校+CRM系统 V1.0
	 */
	List<ScNews> getNewsList(Map<String, Object> map);
	/**
	 * 获取新闻数量
	* @Title: ScNewsMapper.java 
	* @Package com.jingren.jing.school.dao.scnews 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午1:06:05 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getNewsNumber(Map<String, Object> map);
}
