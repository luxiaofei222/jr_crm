package com.jingren.jing.personal.service.positive;

import java.util.List;
import java.util.Map;

import com.jingren.jing.personal.bean.positive.Positive;

public interface PositiveService {
	/**
	* @Title: PositiveMapper.java 
	* @Package com.jingren.jing.personal.dao.positive 
	* @Description: TODO 保存装证申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 上午10:16:43 
	* @version 网校+CRM系统 V1.0
	 */
	boolean savePositive (Positive positive);
	/**
	* @Title: PositiveMapper.java 
	* @Package com.jingren.jing.personal.dao.positive 
	* @Description: TODO 修改转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 上午10:17:41 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updatePositive(Positive positive);
	/**
	* @Title: PositiveMapper.java 
	* @Package com.jingren.jing.personal.dao.positive 
	* @Description: TODO 删除转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 上午10:18:16 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deltePositive(Integer positive_id);
	/**
	* @Title: PositiveMapper.java 
	* @Package com.jingren.jing.personal.dao.positive 
	* @Description: TODO 获取转正申请信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 上午10:18:47 
	* @version 网校+CRM系统 V1.0
	 */
	Positive getPositive(Map<String, Object> map);
	/**
	* @Title: PositiveMapper.java 
	* @Package com.jingren.jing.personal.dao.positive 
	* @Description: TODO 获取转正申请列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 上午10:19:31 
	* @version 网校+CRM系统 V1.0
	 */
	List<Positive> getPositiveList(Map<String, Object> map);
	/**
	* @Title: PositiveMapper.java 
	* @Package com.jingren.jing.personal.dao.positive 
	* @Description: TODO 获取转正申请数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 上午10:20:26 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getPositiveNumber(Map<String, Object> map);
}
