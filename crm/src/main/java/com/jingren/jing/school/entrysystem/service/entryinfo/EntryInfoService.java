package com.jingren.jing.school.entrysystem.service.entryinfo;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;

public interface EntryInfoService {
	/**
	* @Title: EntryInfoMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryinfo 
	* @Description: TODO 保存报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:43:00 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEntryInfo(EntryInfo entryInfo);
	/**
	* @Title: EntryInfoMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryinfo 
	* @Description: TODO 修改报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:45:05 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEntryInfo(EntryInfo entryInfo);
	/**
	* @Title: EntryInfoMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryinfo 
	* @Description: TODO 删除报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:47:01 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteEntryInfo(Integer info_id);
	/**
	* @Title: EntryInfoMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryinfo 
	* @Description: TODO 获取报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:47:23 
	* @version 网校+CRM系统 V1.0
	 */
	EntryInfo getEntryInfo(Map<String, Object> map);
	/**
	* @Title: EntryInfoMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryinfo 
	* @Description: TODO 获取报名信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:48:29 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryInfo> getEntryInfoList(Map<String, Object> map);
	/**
	* @Title: EntryInfoMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryinfo 
	* @Description: TODO 获取报名信息数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:49:08 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getEntryInfoNumber(Map<String, Object> map);
	/**
	* @Title: EntryInfoMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryinfo 
	* @Description: TODO 批量上传图片
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月5日 下午2:06:17 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEntryInfoPic(EntryInfo entryInfo);
}
