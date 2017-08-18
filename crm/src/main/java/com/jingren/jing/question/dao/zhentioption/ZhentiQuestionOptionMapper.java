package com.jingren.jing.question.dao.zhentioption;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.zhentioption.ZhentiQuestionOption;

public interface ZhentiQuestionOptionMapper {

	/**
	* @Title: ZhentiQuestionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentioption 
	* @Description: TODO 保存历年真题选项
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午3:14:50 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveZhentiQuestionOption(ZhentiQuestionOption zhentiQuestionOption);
	/**
	* @Title: ZhentiQuestionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentioption 
	* @Description: TODO 修改历年真题选项
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午3:16:45 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateZhentiQuestionOption(ZhentiQuestionOption zhentiQuestionOption);
	/**
	* @Title: ZhentiQuestionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentioption 
	* @Description: TODO 删除历年真题题目选项
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午3:19:27 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteZhentiQuestionOption(Integer zhenti_question_id);
	/**
	* @Title: ZhentiQuestionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.zhentioption 
	* @Description: TODO 历年真题选项列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月9日 下午3:21:56 
	* @version 网校+CRM系统 V1.0
	 */
	List<ZhentiQuestionOption> getZhentiQuestionOptionList(Map<String, Object> map);
}
