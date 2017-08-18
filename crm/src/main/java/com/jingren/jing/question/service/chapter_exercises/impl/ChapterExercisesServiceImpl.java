package com.jingren.jing.question.service.chapter_exercises.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.chapter_exercises.ChapterExercises;
import com.jingren.jing.question.dao.chapter_exercises.ChapterExercisesMapper;
import com.jingren.jing.question.service.chapter_exercises.ChapterExercisesService;
@Service
public class ChapterExercisesServiceImpl implements ChapterExercisesService {

	@Resource
	private ChapterExercisesMapper chapterExercisesMapper;
	@Override
	public boolean saveChapterExercises(ChapterExercises chapterExercises) {
		chapterExercises.setChapter_time(new Date());
		return chapterExercisesMapper.saveChapterExercises(chapterExercises);
	}

	@Override
	public boolean updateChapterExercises(ChapterExercises chapterExercises) {
		return chapterExercisesMapper.updateChapterExercises(chapterExercises);
	}

	@Override
	public boolean deleteChapterExercises(Integer exercises_id) {
		return chapterExercisesMapper.deleteChapterExercises(exercises_id);
	}

	@Override
	public ChapterExercises getChapterExercises(Map<String, Object> map) {
		return chapterExercisesMapper.getChapterExercises(map);
	}

	@Override
	public List<ChapterExercises> getChapterExercisesList(Map<String, Object> map) {
		return chapterExercisesMapper.getChapterExercisesList(map);
	}

	@Override
	public Integer getChapterExercisesNumber(Map<String, Object> map) {
		return chapterExercisesMapper.getChapterExercisesNumber(map);
	}

	@Override
	public List<ChapterExercises> getChapterExercisesQuchongList(Map<String, Object> map) {
		return chapterExercisesMapper.getChapterExercisesQuchongList(map);
	}

}
