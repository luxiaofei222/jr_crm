package com.jingren.jing.question.dao.correction;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.correction.CorrectionQuestion;

public interface CorrectionQuestionMapper {

	/**
	* @Title: CorrectionQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.correction 
	* @Description: TODO 保存纠错提交记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月7日 下午5:48:21 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveCorrectionQuestion(CorrectionQuestion correctionQuestion);
	/**
	* @Title: CorrectionQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.correction 
	* @Description: TODO 修改纠错信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月7日 下午5:48:45 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateCorrectionQuestion(CorrectionQuestion correctionQuestion);
	/**
	* @Title: CorrectionQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.correction 
	* @Description: TODO 删除纠错记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月7日 下午5:49:26 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteCorrectionQuestion(Integer correction_id);
	/**
	* @Title: CorrectionQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.correction 
	* @Description: TODO 查看纠错记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月7日 下午5:49:55 
	* @version 网校+CRM系统 V1.0
	 */
	CorrectionQuestion getCorrectionQuestion(Map<String, Object> map);
	/**
	* @Title: CorrectionQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.correction 
	* @Description: TODO 纠错记录列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月7日 下午5:50:19 
	* @version 网校+CRM系统 V1.0
	 */
	List<CorrectionQuestion> getCorrectionQuestionList(Map<String, Object> map);
	/**
	* @Title: CorrectionQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.correction 
	* @Description: TODO 纠错记录数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月7日 下午5:50:56 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getgetCorrectionQuestionNumber(Map<String, Object> map);
}
