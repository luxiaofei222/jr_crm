package com.jingren.jing.question.service.chapter_exercises.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.question.dao.chapter_exercises.ChapterQuestionMapper;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuestionService;
@Service
public class ChapterQuestionServiceImpl implements ChapterQuestionService {

	@Resource
	private ChapterQuestionMapper chapterQuestionMapper;
	@Override
	public boolean saveChapterQuestion(ChapterQuestion ChapterQuestion) {
		ChapterQuestion.setChapter_question_time(new Date());
		return chapterQuestionMapper.saveChapterQuestion(ChapterQuestion);
	}

	@Override
	public boolean updateChapterQuestion(ChapterQuestion ChapterQuestion) {
		return chapterQuestionMapper.updateChapterQuestion(ChapterQuestion);
	}

	@Override
	public boolean deleteChapterQuestion(Integer exercises_id) {
		return chapterQuestionMapper.deleteChapterQuestion(exercises_id);
	}

	@Override
	public ChapterQuestion getChapterQuestion(Map<String, Object> map) {
		return chapterQuestionMapper.getChapterQuestion(map);
	}

	@Override
	public List<ChapterQuestion> getChapterQuestionList(Map<String, Object> map) {
		return chapterQuestionMapper.getChapterQuestionList(map);
	}

	@Override
	public Integer getChapterQuestionNumber(Map<String, Object> map) {
		return chapterQuestionMapper.getChapterQuestionNumber(map);
	}

	@Override
	public boolean deleteChapterQuestionByzhang_id(Integer zhang_id) {
		return chapterQuestionMapper.deleteChapterQuestionByzhang_id(zhang_id);
	}

	@Override
	public boolean deleteChapterQuestionByjie_id(Integer jie_id) {
		return chapterQuestionMapper.deleteChapterQuestionByjie_id(jie_id);
	}

	@Override
	public boolean deleteChapterQuestionByquestion_id(Integer question_id) {
		return chapterQuestionMapper.deleteChapterQuestionByquestion_id(question_id);
	}

}
