package com.jingren.jing.question.service.chapter_exercises.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuerstionOption;
import com.jingren.jing.question.dao.chapter_exercises.ChapterQuerstionOptionMapper;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuerstionOptionService;
@Service
public class ChapterQuerstionOptionServiceImpl implements ChapterQuerstionOptionService {

	@Resource
	private ChapterQuerstionOptionMapper chapterQuerstionOptionMapper;
	@Override
	public boolean saveChapterQuerstionOption(ChapterQuerstionOption chapterQuerstionOption) {
		chapterQuerstionOption.setOption_time(new Date());
		return chapterQuerstionOptionMapper.saveChapterQuerstionOption(chapterQuerstionOption);
	}

	@Override
	public boolean updateChapterQuerstionOption(ChapterQuerstionOption chapterQuerstionOption) {
		return chapterQuerstionOptionMapper.updateChapterQuerstionOption(chapterQuerstionOption);
	}

	@Override
	public boolean deleteChapterQuerstionOption(Integer exercises_id) {
		return chapterQuerstionOptionMapper.deleteChapterQuerstionOption(exercises_id);
	}

	@Override
	public ChapterQuerstionOption getChapterQuerstionOption(Map<String, Object> map) {
		return chapterQuerstionOptionMapper.getChapterQuerstionOption(map);
	}

	@Override
	public List<ChapterQuerstionOption> getChapterQuerstionOptionList(Map<String, Object> map) {
		return chapterQuerstionOptionMapper.getChapterQuerstionOptionList(map);
	}

	@Override
	public Integer getChapterQuerstionOptionNumber(Map<String, Object> map) {
		return chapterQuerstionOptionMapper.getChapterQuerstionOptionNumber(map);
	}

}
