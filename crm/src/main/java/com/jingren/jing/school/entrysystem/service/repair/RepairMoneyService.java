package com.jingren.jing.school.entrysystem.service.repair;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.entrysystem.bean.repair.RepairMoney;

public interface RepairMoneyService {
	/**
	* @Title: RepairMoneyMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.repair 
	* @Description: TODO 保存补费信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 上午9:01:16 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveRepairMoney(RepairMoney repairMoney);
	/**
	* @Title: RepairMoneyMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.repair 
	* @Description: TODO 删除补费信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 上午9:01:37 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateRepairMoney(RepairMoney repairMoney);
	/**
	* @Title: RepairMoneyMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.repair 
	* @Description: TODO 删除补费信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 上午9:02:25 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteRepairMoney(Integer repair_id);
	/**
	* @Title: RepairMoneyMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.repair 
	* @Description: TODO 获取补缴费用详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 上午9:06:20 
	* @version 网校+CRM系统 V1.0
	 */
	RepairMoney getRepairMoney(Map<String, Object> map);
	/**
	* @Title: RepairMoneyMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.repair 
	* @Description: TODO 获取补缴费用列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 上午9:06:56 
	* @version 网校+CRM系统 V1.0
	 */
	List<RepairMoney> getRepairMoneyList(Map<String, Object> map);
}
