package com.jingren.jing.question.service.chapterrecord;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;
import com.jingren.jing.question.dao.chapterrecord.ChapterRecordMapper;
@Service
public class ChapterRecordServiceImpl implements ChapterRecordService {

	@Resource
	private ChapterRecordMapper chapterRecordMapper;
	@Override
	public boolean saveChapterRecord(ChapterRecord chapterRecord) {
		return chapterRecordMapper.saveChapterRecord(chapterRecord);
	}

	@Override
	public boolean deleteChapterRecord(ChapterRecord chapterRecord) {
		return chapterRecordMapper.deleteChapterRecord(chapterRecord);
	}

	@Override
	public List<ChapterRecord> getChapterRecordList(Map<String, Object> map) {
		return chapterRecordMapper.getChapterRecordList(map);
	}

	@Override
	public Integer getChapterRecordNumber(Map<String, Object> map) {
		return chapterRecordMapper.getChapterRecordNumber(map);
	}

	@Override
	public Integer getChapterRecordNumberNormal(Map<String, Object> map) {
		return chapterRecordMapper.getChapterRecordNumberNormal(map);
	}

	@Override
	public ChapterRecord getChapterRecord(Map<String, Object> map) {
		return chapterRecordMapper.getChapterRecord(map);
	}

}
