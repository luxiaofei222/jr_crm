package com.jingren.jing.common.nation.dao;

import java.util.List;

import com.jingren.jing.common.nation.bean.TbNation;

public interface TbNationMapper {

	/**
	* @Title: TbNationMapper.java 
	* @Package com.jingren.jing.common.nation.dao 
	* @Description: TODO 民族列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月26日 上午11:21:23 
	* @version 网校+CRM系统 V1.0
	 */
	List<TbNation> getTbNationList();
}
