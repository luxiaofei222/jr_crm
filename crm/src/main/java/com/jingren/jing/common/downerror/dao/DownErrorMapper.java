package com.jingren.jing.common.downerror.dao;


import java.util.List;
import java.util.Map;

import com.jingren.jing.common.downerror.bean.DownError;

public interface DownErrorMapper {

	/**
	* @Title: DownErrorMapper.java 
	* @Package com.jingren.jing.common.downerror.dao 
	* @Description: TODO 保存记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月13日 下午1:32:55 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveDownError(DownError downError);
	/**
	* @Title: DownErrorMapper.java 
	* @Package com.jingren.jing.common.downerror.dao 
	* @Description: TODO 清空记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月13日 下午1:33:26 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteDownError();
	/**
	* @Title: DownErrorMapper.java 
	* @Package com.jingren.jing.common.downerror.dao 
	* @Description: TODO 用户下载列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月13日 下午1:45:32 
	* @version 网校+CRM系统 V1.0
	 */
	List<DownError> getDownErrorList(Map<String, Object> map);
}
