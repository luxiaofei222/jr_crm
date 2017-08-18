package com.jingren.jing.common.university.service;

import java.util.List;
import java.util.Map;

import com.jingren.jing.common.university.bean.ChengkaoSc;

public interface ChengkaoScService {
	/**
	* @Title: ChengkaoScMapper.java 
	* @Package com.jingren.jing.common.university.dao 
	* @Description: TODO 成考学校详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月9日 上午10:42:00 
	* @version 网校+CRM系统 V1.0
	 */
	ChengkaoSc getChengkaoSc(Map<String, Object> map);
	/**
	* @Title: ChengkaoScMapper.java 
	* @Package com.jingren.jing.common.university.dao 
	* @Description: TODO 成考学校列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月9日 上午10:42:27 
	* @version 网校+CRM系统 V1.0
	 */
	List<ChengkaoSc> getChengkaoScList(Map<String, Object> map);
}
