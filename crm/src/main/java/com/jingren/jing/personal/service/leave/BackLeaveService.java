package com.jingren.jing.personal.service.leave;

import java.util.List;
import java.util.Map;

import com.jingren.jing.personal.bean.leave.BackLeave;

public interface BackLeaveService {
	/**
	* @Title: BackLeaveMapper.java 
	* @Package com.jingren.jing.personal.dao.leave 
	* @Description: TODO 保存任务信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月31日 上午9:36:29 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveBackLeave(BackLeave backLeave);
	/**
	* @Title: BackLeaveMapper.java 
	* @Package com.jingren.jing.personal.dao.leave 
	* @Description: TODO 修改任务信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月31日 上午9:36:51 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateBackLeave(BackLeave backLeave);
	/**
	* @Title: BackLeaveMapper.java 
	* @Package com.jingren.jing.personal.dao.leave 
	* @Description: TODO 删除请假信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月31日 上午9:39:50 
	* @version 网校+CRM系统 V1.0
	 */
	boolean delteBackLeave(Integer leave_id);
	/**
	* @Title: BackLeaveMapper.java 
	* @Package com.jingren.jing.personal.dao.leave 
	* @Description: TODO 获取请假详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月31日 上午9:43:00 
	* @version 网校+CRM系统 V1.0
	 */
	BackLeave getBackLeave(Map<String, Object> map);
	/**
	* @Title: BackLeaveMapper.java 
	* @Package com.jingren.jing.personal.dao.leave 
	* @Description: TODO 获取请假列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月31日 上午9:43:54 
	* @version 网校+CRM系统 V1.0
	 */
	List<BackLeave> getBackLeaveList(Map<String, Object> map);
	/**
	* @Title: BackLeaveMapper.java 
	* @Package com.jingren.jing.personal.dao.leave 
	* @Description: TODO 获取请假列表数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月31日 上午9:44:58 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getBackLeaveNumber(Map<String, Object> map);
}
