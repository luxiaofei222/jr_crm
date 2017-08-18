package com.jingren.jing.crm.dao.setcalltime;

import com.jingren.jing.crm.bean.setcalltime.SetCallTime;

public interface SetCallTimeMapper {

	/**
	* @Title: SetCallTimeMapper.java 
	* @Package com.jingren.jing.crm.dao.setcalltime 
	* @Description: TODO 修改自定义时长
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月17日 下午3:13:19 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateSetCallTime(SetCallTime setCallTime);
	/**
	* @Title: SetCallTimeMapper.java 
	* @Package com.jingren.jing.crm.dao.setcalltime 
	* @Description: TODO 查询自定义时长
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月17日 下午3:13:48 
	* @version 网校+CRM系统 V1.0
	 */
	SetCallTime getSetCallTime();
}
