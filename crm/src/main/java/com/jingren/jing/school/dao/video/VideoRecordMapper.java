package com.jingren.jing.school.dao.video;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.video.VideoRecord;

public interface VideoRecordMapper {

	/**
	* @Title: VideoRecordMapper.java 
	* @Package com.jingren.jing.school.dao.video 
	* @Description: TODO 保存观看记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 上午8:57:10 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveVideoRecord(VideoRecord videoRecord);
	/**
	* @Title: VideoRecordMapper.java 
	* @Package com.jingren.jing.school.dao.video 
	* @Description: TODO 保存观看记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 上午8:57:34 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateVideoRecord(VideoRecord videoRecord);
	/**
	* @Title: VideoRecordMapper.java 
	* @Package com.jingren.jing.school.dao.video 
	* @Description: TODO 删除观看记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 上午8:58:06 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteVideoRecord(Map<String,Object> map);
	/**
	* @Title: VideoRecordMapper.java 
	* @Package com.jingren.jing.school.dao.video 
	* @Description: TODO 查看视频记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 上午8:58:37 
	* @version 网校+CRM系统 V1.0
	 */
	VideoRecord getVideoRecord(Map<String, Object> map);
	/**
	* @Title: VideoRecordMapper.java 
	* @Package com.jingren.jing.school.dao.video 
	* @Description: TODO 观看记录列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 上午8:59:10 
	* @version 网校+CRM系统 V1.0
	 */
	List<VideoRecord> getVideoRecordList(Map<String, Object> map);
	/**
	* @Title: VideoRecordMapper.java 
	* @Package com.jingren.jing.school.dao.video 
	* @Description: TODO 获取视频数量列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 上午8:59:53 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getVideoRecordNumber(Map<String, Object> map);
}
