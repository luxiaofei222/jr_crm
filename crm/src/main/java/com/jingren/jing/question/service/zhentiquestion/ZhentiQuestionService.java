package com.jingren.jing.question.service.zhentiquestion;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.zhentiquestion.ZhentiQuestion;

public interface ZhentiQuestionService {
	/**
	* @Title: ZhentiQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiquestion 
	* @Description: TODO 保存历年真题-题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午2:22:29 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveZhentiQuestion(ZhentiQuestion zhentiQuestion);
	/**
	* @Title: ZhentiQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiquestion 
	* @Description: TODO 修改题目-历年真题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午2:31:17 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateZhentiQuestion(ZhentiQuestion zhentiQuestion);
	/**
	* @Title: ZhentiQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiquestion 
	* @Description: TODO 删除历年真题题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午2:32:31 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteZhentiQuestion(Integer zhenti_question_id);
	/**
	* @Title: ZhentiQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiquestion 
	* @Description: TODO 历年真题-题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午2:43:24 
	* @version 网校+CRM系统 V1.0
	 */
	ZhentiQuestion getZhentiQuestion(Map<String, Object> map);
	/**
	* @Title: ZhentiQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiquestion 
	* @Description: TODO 历年真题列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午2:43:57 
	* @version 网校+CRM系统 V1.0
	 */
	List<ZhentiQuestion> getZhentiQuestionList(Map<String, Object> map);
	/**
	* @Title: ZhentiQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentiquestion 
	* @Description: TODO 历年真题数量
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年2月9日 下午2:44:25 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getZhentiQuestionNumber(Map<String, Object> map);
}
