package com.jingren.jing.question.dao.zhentirecord;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.zhentirecord.ZhentiRecord;

public interface ZhentiRecordMapper {

	/**
	* @Title: ZhentiRecordMapper.java 
	* @Package com.jingren.jing.question.dao.zhentirecord 
	* @Description: TODO 保存真题记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月15日 上午10:04:42 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveZhentiRecord(ZhentiRecord zhentiRecord);
	/**
	* @Title: ZhentiRecordMapper.java 
	* @Package com.jingren.jing.question.dao.zhentirecord 
	* @Description: TODO 删除真题记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月15日 上午10:05:18 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteZhentiRecord(ZhentiRecord zhentiRecord);
	/**
	* @Title: ZhentiRecordMapper.java 
	* @Package com.jingren.jing.question.dao.zhentirecord 
	* @Description: TODO 获取真题记录信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月15日 上午10:06:02 
	* @version 网校+CRM系统 V1.0
	 */
	ZhentiRecord getZhentiRecord(Map<String, Object> map);
	/**
	* @Title: ZhentiRecordMapper.java 
	* @Package com.jingren.jing.question.dao.zhentirecord 
	* @Description: TODO 历年真题记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月15日 上午10:06:52 
	* @version 网校+CRM系统 V1.0
	 */
	List<ZhentiRecord> getZhentiRecordList(Map<String, Object> map);
	/**
	* @Title: ZhentiRecordMapper.java 
	* @Package com.jingren.jing.question.dao.zhentirecord 
	* @Description: TODO 历年真题记录数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月15日 上午10:09:09 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getZhentiRecordNumber(Map<String, Object> map);
}
