package com.jingren.jing.personal.service.emlog;

import java.util.List;
import java.util.Map;

import com.jingren.jing.personal.bean.emlog.EmLog;

public interface EmLogService {
	/**
	* @Title: EmLogMapper.java 
	* @Package com.jingren.jing.personal.dao.emlog 
	* @Description: TODO 保存员工日报
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午4:57:49 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEmLog(EmLog emLog);
	/**
	* @Title: EmLogMapper.java 
	* @Package com.jingren.jing.personal.dao.emlog 
	* @Description: TODO 修改员工日报
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午4:58:17 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEmLog(EmLog emLog);
	/**
	* @Title: EmLogMapper.java 
	* @Package com.jingren.jing.personal.dao.emlog 
	* @Description: TODO 删除员工日报
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午5:00:21 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteEmLog(Integer emlog_id);
	/**
	* @Title: EmLogMapper.java 
	* @Package com.jingren.jing.personal.dao.emlog 
	* @Description: TODO 获取员工日报详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午5:01:41 
	* @version 网校+CRM系统 V1.0
	 */
	EmLog getEmLog(Map<String, Object> map);
	/**
	* @Title: EmLogMapper.java 
	* @Package com.jingren.jing.personal.dao.emlog 
	* @Description: TODO 员工日报列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午5:02:12 
	* @version 网校+CRM系统 V1.0
	 */
	List<EmLog> getEmLogList(Map<String, Object> map);
	/**
	* @Title: EmLogMapper.java 
	* @Package com.jingren.jing.personal.dao.emlog 
	* @Description: TODO 员工日报数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午5:02:39 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getEmLogNumber(Map<String, Object> map);
}
