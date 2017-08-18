package com.jingren.jing.school.entrysystem.service.entrycondition;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;

public interface EntryConditionService {
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 保存报名条件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:57:13 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveEntryCondition(EntryCondition entryCondition);
	/**
 	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 修改报名条件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:57:33 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateEntryCondition(EntryCondition entryCondition);
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 删除报名条件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:58:04 
	* @version 网校+CRM系统 V1.0
	 */
	boolean delteEntryCondition(Integer condi_id);
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 获取报名条件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:58:29 
	* @version 网校+CRM系统 V1.0
	 */
	EntryCondition getEntryCondition(Map<String, Object> map);
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 获取报名条件列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:58:56 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryCondition> getEntryConditionList(Map<String, Object> map);
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 获取报名条件数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 上午11:59:35 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getEntryConditionNumber(Map<String, Object> map);
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 去除重复记录查询
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月23日 下午6:10:56 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryCondition> getEntryConditionListByQuchong(Map<String, Object> map);
	
	List<EntryCondition> getEntryConditionListByQuchonghoutai(Map<String, Object> map);
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 查询去重后的申报条件课程级别
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月5日 下午3:15:11 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryCondition> getEntryConditionListByCourseParent(Map<String, Object> map);
	
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 等级是1001的时候
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月5日 下午5:38:35 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryCondition> getEntryConditionListByDic(Map<String, Object> map);
	/**
	* @Title: EntryConditionMapper.java 
	* @Package com.jingren.jing.school.entrysystem.dao.entrycondition 
	* @Description: TODO 网络教育查询申报条件
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月8日 下午2:25:38 
	* @version 网校+CRM系统 V1.0
	 */
	List<EntryCondition> getEntryConditionListByCourseWangjiao(Map<String, Object> map);
}
