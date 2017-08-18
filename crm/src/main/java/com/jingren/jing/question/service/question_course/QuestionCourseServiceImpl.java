package com.jingren.jing.question.service.question_course;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.question_course.QuestionCourse;
import com.jingren.jing.question.dao.question_course.QuestionCourseMapper;
@Service
public class QuestionCourseServiceImpl implements QuestionCourseService {

	@Resource
	private QuestionCourseMapper questionCourseMapper;
	@Override
	public boolean saveQuestionCourse(QuestionCourse questionCourse) {
		questionCourse.setQuestion_course_time(new Date());
		return questionCourseMapper.saveQuestionCourse(questionCourse);
	}

	@Override
	public boolean updateQuestionCourse(QuestionCourse questionCourse) {
		return questionCourseMapper.updateQuestionCourse(questionCourse);
	}

	@Override
	public boolean deleteQuestionCourse(Integer question_id) {
		return questionCourseMapper.deleteQuestionCourse(question_id);
	}

	@Override
	public QuestionCourse getQuestionCourse(Map<String, Object> map) {
		return questionCourseMapper.getQuestionCourse(map);
	}

	@Override
	public List<QuestionCourse> getQuestionCourseList(Map<String, Object> map) {
		return questionCourseMapper.getQuestionCourseList(map);
	}

	@Override
	public Integer getQuestionCourseNumber(Map<String, Object> map) {
		return questionCourseMapper.getQuestionCourseNumber(map);
	}

	@Override
	public List<QuestionCourse> getQuestionCourseQuchongList(Map<String, Object> map) {
		return questionCourseMapper.getQuestionCourseQuchongList(map);
	}

}
