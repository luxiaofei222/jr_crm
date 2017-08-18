package com.jingren.jing.oa.dao.task;

import java.util.List;
import java.util.Map;

import com.jingren.jing.oa.bean.task.OaTask;

public interface OaTaskMapper {

	/**
	* @Title: OaTaskMapper.java 
	* @Package com.jingren.jing.oa.dao.task 
	* @Description: TODO 发布任务
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 下午5:28:31 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveOaTask(OaTask oaTask);
	/**
	* @Title: OaTaskMapper.java 
	* @Package com.jingren.jing.oa.dao.task 
	* @Description: TODO 修改任务信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 下午5:29:27 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateOaTask(OaTask oaTask);
	/**
	* @Title: OaTaskMapper.java 
	* @Package com.jingren.jing.oa.dao.task 
	* @Description: TODO 删除任务信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 下午5:30:07 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteOaTask(Integer oatask_id);
	/**
	* @Title: OaTaskMapper.java 
	* @Package com.jingren.jing.oa.dao.task 
	* @Description: TODO 获取任务信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 下午5:31:12 
	* @version 网校+CRM系统 V1.0
	 */
	OaTask getOaTask(Map<String, Object> map);
	/**
	* @Title: OaTaskMapper.java 
	* @Package com.jingren.jing.oa.dao.task 
	* @Description: TODO 获取任务列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 下午5:32:55 
	* @version 网校+CRM系统 V1.0
	 */
	List<OaTask> getOaTaskList(Map<String, Object> map);
	/**
	* @Title: OaTaskMapper.java 
	* @Package com.jingren.jing.oa.dao.task 
	* @Description: TODO 获取任务数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 下午5:33:29 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getOaTaskNumber(Map<String, Object> map);
	/**
	* @Title: OaTaskMapper.java 
	* @Package com.jingren.jing.oa.dao.task 
	* @Description: TODO 获取发布人下的所有接收人
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月27日 上午8:41:03 
	* @version 网校+CRM系统 V1.0
	 */
	List<OaTask> getOataskJieEmployee(Map<String, Object> map);
}
