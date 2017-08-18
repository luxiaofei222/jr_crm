package com.jingren.jing.common.university.dao;

import java.util.List;
import java.util.Map;

import com.jingren.jing.common.university.bean.Review;



public interface ReviewMapper {
	/**
	* @Title: ReviewMapper.java 
	* @Package com.jingren.jing.common.university.dao 
	* @Description: TODO 大学列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 下午4:53:48 
	* @version 网校+CRM系统 V1.0
	 */
	List<Review> getReviewList(Map<String, Object> map);
	/**
	* @Title: ReviewMapper.java 
	* @Package com.jingren.jing.common.university.dao 
	* @Description: TODO 大学信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月16日 下午4:54:09 
	* @version 网校+CRM系统 V1.0
	 */
	Review getReview(Map<String, Object> map);
}
