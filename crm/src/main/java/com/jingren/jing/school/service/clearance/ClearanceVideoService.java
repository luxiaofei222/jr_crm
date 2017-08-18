package com.jingren.jing.school.service.clearance;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.clearance.ClearanceVideo;

public interface ClearanceVideoService {
	/**
	* @Title: ClearanceVideoMappr.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 保存套餐课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月2日 下午3:06:27 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveClearanceVideo(ClearanceVideo clearanceVideo);
	/**
	* @Title: ClearanceVideoMappr.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 解除绑定课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月2日 下午3:07:12 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteClearanceVideo(Integer clear_video_id);
	/**
	* @Title: ClearanceVideoMappr.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 课程列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月2日 下午3:08:43 
	* @version 网校+CRM系统 V1.0
	 */
	List<ClearanceVideo> getClearanceVideoList(Map<String, Object> map);
}
