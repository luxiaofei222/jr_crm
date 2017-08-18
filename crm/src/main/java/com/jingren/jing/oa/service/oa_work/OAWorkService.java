package com.jingren.jing.oa.service.oa_work;

import java.util.List;
import java.util.Map;

import com.jingren.jing.oa.bean.oa_work.OAWork;

public interface OAWorkService {
	/**
	* @Title: OAWorkMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_work 
	* @Description: TODO 保存工作经历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午4:02:38 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveOAWork(OAWork oAWork);
	/**
	* @Title: OAWorkMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_work 
	* @Description: TODO 修改工作经历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午4:02:28 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateOAWork(OAWork oAWork);
	/**
	* @Title: OAWorkMapper.java 
	* @Package com.jingren.jing.oa.dao.oa_work 
	* @Description: TODO 获取工作经历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午4:02:50 
	* @version 网校+CRM系统 V1.0
	 */
	List<OAWork> getOAWorkList(Map<String, Object> map);
}
