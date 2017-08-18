package com.jingren.jing.school.entrysystem.service.entryplan;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;

public interface EntryPlanService {
	/**
	* @Title: EntryPlanMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplan 
	* @Description: TODO 保存报名计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:31:40 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEntryPlan(EntryPlan entryPlan);
	/**
	* @Title: EntryPlanMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplan 
	* @Description: TODO 修改报名计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:32:02 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEntryPlan(EntryPlan entryPlan);
	/**
	* @Title: EntryPlanMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplan 
	* @Description: TODO 删除报名计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:32:38 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteEntryPlan(Integer plan_id);
	/**
	* @Title: EntryPlanMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplan 
	* @Description: TODO 获取计划信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:33:05 
	* @version 网校+CRM系统 V1.0
	 */
	EntryPlan getEntryPlan(Map<String, Object> map);
	/**
	* @Title: EntryPlanMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplan 
	* @Description: TODO 获取考试计划列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:34:40 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryPlan> getEntryPlanList(Map<String, Object> map);
	/**
	* @Title: EntryPlanMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplan 
	* @Description: TODO 获取考生计划数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:35:16 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getEntryPlanNumber(Map<String, Object> map);
	/**
	* @Title: EntryPlanMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entryplan 
	* @Description: TODO 按照课程分类查询列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月22日 上午10:15:55 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryPlan> getEntryQuChongPlanList(Map<String, Object> map);
}
