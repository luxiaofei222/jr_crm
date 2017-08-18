package com.jingren.jing.school.service.clearance;

import java.util.List;
import java.util.Map;

import com.jingren.jing.school.bean.clearance.Clearance;

public interface ClearanceService {
	/**
	* @Title: ClearanceMapper.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 保存通过方案
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午8:27:39 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveClearance(Clearance clearance);
	/**
	* @Title: ClearanceMapper.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 修改通关方案
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午8:28:51 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateClearance(Clearance clearance);
	/**
	* @Title: ClearanceMapper.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 删除通关方案
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午8:30:11 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteClearance(Integer clearance_id);
	/**
	* @Title: ClearanceMapper.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 获取通关方案信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午8:30:59 
	* @version 网校+CRM系统 V1.0
	 */
	Clearance getClearance(Map<String, Object> map);
	/**
	* @Title: ClearanceMapper.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 获取通关方案列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午8:31:46 
	* @version 网校+CRM系统 V1.0
	 */
	List<Clearance> getClearanceList(Map<String, Object> map);
	/**
	* @Title: ClearanceMapper.java 
	* @Package com.jingren.jing.school.dao.clearance 
	* @Description: TODO 获取通关方案数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午8:32:14 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getClearanceNumber(Map<String, Object> map);
}
