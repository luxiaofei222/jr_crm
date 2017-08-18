package com.jingren.jing.question.service.question_course;

import java.util.List;
import java.util.Map;

import com.jingren.jing.question.bean.question_course.QuestionCourse;

public interface QuestionCourseService {
	/**
	* @Title: QuestionCourseMapper.java 
	* @Package com.jingren.jing.question.dao.question_course 
	* @Description: TODO 保存题库课程名称分类
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午1:17:58 
	* @version 网校+CRM系统 V1.0
	 */
	boolean saveQuestionCourse(QuestionCourse questionCourse);
	/**
	* @Title: QuestionCourseMapper.java 
	* @Package com.jingren.jing.question.dao.question_course 
	* @Description: TODO 修改题库课程名称
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午1:18:36 
	* @version 网校+CRM系统 V1.0
	 */
	boolean updateQuestionCourse(QuestionCourse questionCourse);
	/**
	* @Title: QuestionCourseMapper.java 
	* @Package com.jingren.jing.question.dao.question_course 
	* @Description: TODO 删除课程名称分类
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午1:19:24 
	* @version 网校+CRM系统 V1.0
	 */
	boolean deleteQuestionCourse(Integer question_id);
	/**
	* @Title: QuestionCourseMapper.java 
	* @Package com.jingren.jing.question.dao.question_course 
	* @Description: TODO 题库课程分类信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午1:19:54 
	* @version 网校+CRM系统 V1.0
	 */
	QuestionCourse getQuestionCourse(Map<String, Object> map);
	/**
	* @Title: QuestionCourseMapper.java 
	* @Package com.jingren.jing.question.dao.question_course 
	* @Description: TODO 题库分类列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午1:20:27 
	* @version 网校+CRM系统 V1.0
	 */
	List<QuestionCourse> getQuestionCourseList(Map<String, Object> map);
	/**
	* @Title: QuestionCourseMapper.java 
	* @Package com.jingren.jing.question.dao.question_course 
	* @Description: TODO 题库分类数量
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午1:21:04 
	* @version 网校+CRM系统 V1.0
	 */
	Integer getQuestionCourseNumber(Map<String, Object> map);
	/**
	* @Title: QuestionCourseMapper.java 
	* @Package com.jingren.jing.question.dao.question_course 
	* @Description: TODO 题库分类列表去重课程分类查询
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午1:20:27 
	* @version 网校+CRM系统 V1.0
	 */
	List<QuestionCourse> getQuestionCourseQuchongList(Map<String, Object> map);
}
