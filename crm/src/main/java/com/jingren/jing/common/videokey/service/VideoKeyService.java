package com.jingren.jing.common.videokey.service;

import java.util.Map;

import com.jingren.jing.common.videokey.bean.VideoKey;

public interface VideoKeyService {
	/**
	* @Title: VideoKeyMapper.java 
	* @Package com.jingren.jing.common.videokey.dao 
	* @Description: TODO 保存视频key
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 下午7:10:41 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveVideoKey(VideoKey videoKey);
	/**
	* @Title: VideoKeyMapper.java 
	* @Package com.jingren.jing.common.videokey.dao 
	* @Description: TODO 删除key
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 下午7:11:22 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteVideoKey(String key_number);
	/**
	* @Title: VideoKeyMapper.java 
	* @Package com.jingren.jing.common.videokey.dao 
	* @Description: TODO 获取key
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月28日 下午7:12:22 
	* @version 网校+CRM系统 V1.0
	 */
	VideoKey getVideoKey(Map<String, Object> map);
	/**
	* @Title: VideoKeyMapper.java 
	* @Package com.jingren.jing.common.videokey.dao 
	* @Description: TODO 删除所有记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月29日 上午8:02:04 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteAllVideoKey();
}
