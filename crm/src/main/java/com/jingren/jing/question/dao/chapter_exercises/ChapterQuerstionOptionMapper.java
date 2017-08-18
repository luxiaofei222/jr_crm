package com.jingren.jing.question.dao.chapter_exercises;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuerstionOption;

public interface ChapterQuerstionOptionMapper {
	/**
	* @Title: ChapterQuerstionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 保存章节练习-章节信息题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:17:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveChapterQuerstionOption(ChapterQuerstionOption chapterQuerstionOption);
	/**
	* @Title: ChapterQuerstionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 修改章节练习-章节信息题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:18:22 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateChapterQuerstionOption(ChapterQuerstionOption chapterQuerstionOption);
	/**
	* @Title: ChapterQuerstionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 删除章节练习-章节信息题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:19:33 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteChapterQuerstionOption(Integer exercises_id);
	/**
	* @Title: ChapterQuerstionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 获取章节练习-章节信息题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:20:18 
	* @version 网校+CRM系统 V1.0
	 */
	ChapterQuerstionOption getChapterQuerstionOption(Map<String, Object> map);
	/**
	* @Title: ChapterQuerstionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO获取章节练习-章节信息题目列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:20:49 
	* @version 网校+CRM系统 V1.0
	 */
	List<ChapterQuerstionOption> getChapterQuerstionOptionList(Map<String, Object> map);
	/**
	* @Title: ChapterQuerstionOptionMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 获取章节练习-章节信息题目数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:21:18 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getChapterQuerstionOptionNumber(Map<String, Object> map);
}
