package com.jingren.jing.personal.dao.punch;

import java.util.List;
import java.util.Map;

import com.jingren.jing.personal.bean.punch.Punch;

public interface PunchMapper {

	/**
	* @Title: PunchMapper.java 
	* @Package com.jingren.jing.personal.dao.punch 
	* @Description: TODO 保存忘记打卡申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月17日 上午9:09:23 
	* @version 网校+CRM系统 V1.0
	 */
	boolean savePunch(Punch punch);
	/**
	* @Title: PunchMapper.java 
	* @Package com.jingren.jing.personal.dao.punch 
	* @Description: TODO 修改忘记打卡
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月17日 上午9:09:59 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updatePunch(Punch punch);
	/**
	* @Title: PunchMapper.java 
	* @Package com.jingren.jing.personal.dao.punch 
	* @Description: TODO 删除忘记打卡记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月17日 上午9:10:35 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deletePunch(Integer punch_id);
	/**
	* @Title: PunchMapper.java 
	* @Package com.jingren.jing.personal.dao.punch 
	* @Description: TODO 忘记打卡详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月17日 上午9:11:10 
	* @version 网校+CRM系统 V1.0
	 */
	Punch getPunch(Map<String, Object> map);
	/**
	* @Title: PunchMapper.java 
	* @Package com.jingren.jing.personal.dao.punch 
	* @Description: TODO 忘记打卡列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月17日 上午9:11:36 
	* @version 网校+CRM系统 V1.0
	 */
	List<Punch> getPunchList(Map<String, Object> map);
	/**
	* @Title: PunchMapper.java 
	* @Package com.jingren.jing.personal.dao.punch 
	* @Description: TODO 忘记打卡数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月17日 上午9:12:06 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getPunchNumber(Map<String, Object> map);
}
