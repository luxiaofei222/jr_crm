package com.jingren.jing.question.dao.chapter_exercises;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.chapter_exercises.ChapterExercises;

public interface ChapterExercisesMapper {

	/**
	* @Title: ChapterExercisesMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 保存章节练习-章节信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:17:56 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveChapterExercises(ChapterExercises chapterExercises);
	/**
	* @Title: ChapterExercisesMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 修改章节练习-章节信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:18:22 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateChapterExercises(ChapterExercises chapterExercises);
	/**
	* @Title: ChapterExercisesMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 删除章节练习-章节信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:19:33 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteChapterExercises(Integer exercises_id);
	/**
	* @Title: ChapterExercisesMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 获取章节练习-章节信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:20:18 
	* @version 网校+CRM系统 V1.0
	 */
	ChapterExercises getChapterExercises(Map<String, Object> map);
	/**
	* @Title: ChapterExercisesMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO获取章节练习-章节信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:20:49 
	* @version 网校+CRM系统 V1.0
	 */
	List<ChapterExercises> getChapterExercisesList(Map<String, Object> map);
	/**
	* @Title: ChapterExercisesMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 获取章节练习-章节信息数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午2:21:18 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getChapterExercisesNumber(Map<String, Object> map);
	/**
	* @Title: ChapterExercisesMapper.java 
	* @Package com.jingren.jing.question.dao.chapter_exercises 
	* @Description: TODO 去重
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月11日 下午1:47:12 
	* @version 网校+CRM系统 V1.0
	 */
	List<ChapterExercises> getChapterExercisesQuchongList(Map<String, Object> map);
}
