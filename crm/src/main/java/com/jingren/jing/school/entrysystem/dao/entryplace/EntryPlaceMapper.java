package com.jingren.jing.school.entrysystem.dao.entryplace;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.entrysystem.bean.entryplace.EntryPlace;

public interface EntryPlaceMapper {

	/**
	* @Title: EntryPlaceMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplace 
	* @Description: TODO 获取报名地点列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:53:16 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryPlace> getEntryPlaceList(Map<String, Object> map);
	/**
	* @Title: EntryPlaceMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplace 
	* @Description: TODO获取报名地点
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月26日 上午10:24:37 
	* @version 网校+CRM系统 V1.0
	 */
	EntryPlace getEntryPlace(Map<String, Object> map);
}
