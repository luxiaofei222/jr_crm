package com.jingren.jing.personal.dao.desert;

import java.util.List;
import java.util.Map;

import com.jingren.jing.personal.bean.desert.DesertJob;

public interface DesertJobMapper {

	/**
	* @Title: DesertJobMapper.java 
	* @Package com.jingren.jing.personal.dao.desert 
	* @Description: TODO 提交离职申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午9:27:07 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveDesertJob(DesertJob desertJob);
	/**
	* @Title: DesertJobMapper.java 
	* @Package com.jingren.jing.personal.dao.desert 
	* @Description: TODO 修改离职申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午9:27:32 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateDesertJob(DesertJob desertJob);
	/**
	* @Title: DesertJobMapper.java 
	* @Package com.jingren.jing.personal.dao.desert 
	* @Description: TODO 删除离职申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午9:29:20 
	* @version 网校+CRM系统 V1.0
	 */
	boolean delteDesertJob(Integer desert_id);
	/**
	* @Title: DesertJobMapper.java 
	* @Package com.jingren.jing.personal.dao.desert 
	* @Description: TODO 获取离职申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午9:29:46 
	* @version 网校+CRM系统 V1.0
	 */
	DesertJob getDesertJob(Map<String, Object> map);
	/**
	* @Title: DesertJobMapper.java 
	* @Package com.jingren.jing.personal.dao.desert 
	* @Description: TODO 获取离职申请列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午9:30:12 
	* @version 网校+CRM系统 V1.0
	 */
	List<DesertJob> getDesertJobList(Map<String, Object> map);
	/**
	* @Title: DesertJobMapper.java 
	* @Package com.jingren.jing.personal.dao.desert 
	* @Description: TODO 获取离职申请数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午9:30:52 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getDesertJobNumber(Map<String, Object> map);
	
}
