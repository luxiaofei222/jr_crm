package com.jingren.jing.conult.dao.conultinfo;

import java.util.List;
import java.util.Map;

import com.jingren.jing.conult.bean.conultinfo.NetConult;

public interface NetConultMapper {

	/**
	* @Title: NetConultMapper.java 
	* @Package com.jingren.jing.conult.dao.conultinfo 
	* @Description: TODO 保存客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月1日 下午4:13:12 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveNetConult(NetConult netConult);
	/**
	* @Title: NetConultMapper.java 
	* @Package com.jingren.jing.conult.dao.conultinfo 
	* @Description: TODO 修改客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月1日 下午4:13:34 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateNetConult(NetConult netConult);
	/**
	* @Title: NetConultMapper.java 
	* @Package com.jingren.jing.conult.dao.conultinfo 
	* @Description: TODO 删除网络咨询客户
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月1日 下午4:16:34 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteNetConult(Integer conult_id);
	/**
	* @Title: NetConultMapper.java 
	* @Package com.jingren.jing.conult.dao.conultinfo 
	* @Description: TODO 获取咨询客户详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月1日 下午4:17:15 
	* @version 网校+CRM系统 V1.0
	 */
	NetConult getNetConult(Map<String, Object> map);
	/**
	* @Title: NetConultMapper.java 
	* @Package com.jingren.jing.conult.dao.conultinfo 
	* @Description: TODO 获取客户咨询列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月1日 下午4:17:45 
	* @version 网校+CRM系统 V1.0
	 */
	List<NetConult> getNetConultList(Map<String, Object> map);
	/**
	* @Title: NetConultMapper.java 
	* @Package com.jingren.jing.conult.dao.conultinfo 
	* @Description: TODO 获取客户咨询数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月1日 下午4:18:33 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getNetConultNumber(Map<String, Object> map);
}
