package com.jingren.jing.question.dao.chapter_exercises;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;

public interface ChapterQuestionMapper {
	/**
	* @Title: ChapterQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 保存章节练习-章节题目信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:17:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveChapterQuestion(ChapterQuestion ChapterQuestion);
	/**
	* @Title: ChapterQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 修改章节练习-章节题目信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:18:22 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateChapterQuestion(ChapterQuestion ChapterQuestion);
	/**
	* @Title: ChapterQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 删除章节练习-章节题目信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:19:33 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteChapterQuestion(Integer exercises_id);
	/**
	* @Title: ChapterQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 获取章节练习-章节题目信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:20:18 
	* @version 网校+CRM系统 V1.0
	 */
	ChapterQuestion getChapterQuestion(Map<String, Object> map);
	/**
	* @Title: ChapterQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO获取章节练习-章节题目信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:20:49 
	* @version 网校+CRM系统 V1.0
	 */
	List<ChapterQuestion> getChapterQuestionList(Map<String, Object> map);
	/**
	* @Title: ChapterQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 获取章节练习-章节题目信息数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:21:18 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getChapterQuestionNumber(Map<String, Object> map);
	/**
	* @Title: ChapterQuestionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 根据章、节、课删除题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月12日 上午10:33:30 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteChapterQuestionByzhang_id(Integer zhang_id);
	boolean deleteChapterQuestionByjie_id(Integer jie_id);
	boolean deleteChapterQuestionByquestion_id(Integer question_id);
}
