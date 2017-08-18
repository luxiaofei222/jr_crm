package com.jingren.jing.conult.service.record;

import java.util.List;
import java.util.Map;

import com.jingren.jing.conult.bean.record.NetConultRecord;

public interface NetConultRecordService {
	/**
	* @Title: NetConultRecordMapper.java 
	* @Package com.jingren.jing.conult.dao.record 
	* @Description: TODO 保存通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月5日 上午9:42:09 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveNetConultRecord(NetConultRecord netConultRecord);
	/**
	* @Title: NetConultRecordMapper.java 
	* @Package com.jingren.jing.conult.dao.record 
	* @Description: TODO 修改通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月5日 上午9:42:31 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateNetConultRecord(NetConultRecord netConultRecord);
	/**
	* @Title: NetConultRecordMapper.java 
	* @Package com.jingren.jing.conult.dao.record 
	* @Description: TODO 删除通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月5日 上午9:42:59 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteNetConultRecord(Integer record_id);
	/**
	* @Title: NetConultRecordMapper.java 
	* @Package com.jingren.jing.conult.dao.record 
	* @Description: TODO 获取通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月5日 上午9:43:26 
	* @version 网校+CRM系统 V1.0
	 */
	NetConultRecord getNetConultRecord(Map<String, Object> map);
	/**
	* @Title: NetConultRecordMapper.java 
	* @Package com.jingren.jing.conult.dao.record 
	* @Description: TODO 获取通话记录列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月5日 上午9:43:56 
	* @version 网校+CRM系统 V1.0
	 */
	List<NetConultRecord> getNetConultRecordList(Map<String, Object> map);
	/**
	* @Title: NetConultRecordMapper.java 
	* @Package com.jingren.jing.conult.dao.record 
	* @Description: TODO 获取通话记录数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月5日 上午9:44:33 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getNetConultRecordNumber(Map<String, Object> map);
}
