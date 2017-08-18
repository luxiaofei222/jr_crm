package com.jingren.jing.question.dao.question_collection;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.question_collection.QuestionCollection;

public interface QuestionCollectionMapper {

	/**
	* @Title: QuestionCollectionMapper.java 
	* @Package com.jingren.jing.question.dao.question_collection 
	* @Description: TODO 保存我的题目收藏
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 下午3:30:58 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveQuestionCollection(QuestionCollection questionCollection);
	/**
	* @Title: QuestionCollectionMapper.java 
	* @Package com.jingren.jing.question.dao.question_collection 
	* @Description: TODO 取消收藏题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 下午4:04:14 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteQuestionCollection(Integer question_collection_id);
	/**
	* @Title: QuestionCollectionMapper.java 
	* @Package com.jingren.jing.question.dao.question_collection 
	* @Description: TODO 获取我的收藏
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 下午4:00:48 
	* @version 网校+CRM系统 V1.0
	 */
	QuestionCollection getQuestionCollection(Map<String, Object> map);
	/**
	* @Title: QuestionCollectionMapper.java 
	* @Package com.jingren.jing.question.dao.question_collection 
	* @Description: TODO 我的收藏题目列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 下午4:01:24 
	* @version 网校+CRM系统 V1.0
	 */
	List<QuestionCollection> getQuestionCollectionList(Map<String, Object> map);
	/**
	* @Title: QuestionCollectionMapper.java 
	* @Package com.jingren.jing.question.dao.question_collection 
	* @Description: TODO 我的收藏数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 下午4:02:08 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getQuestionCollectionNumber(Map<String, Object> map);
}
