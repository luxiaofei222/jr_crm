package com.jingren.jing.school.entrysystem.service.entryplace;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.entrysystem.bean.entryplace.EntryPlace;
import com.jingren.jing.school.entrysystem.dao.entryplace.EntryPlaceMapper;
@Service
public class EntryPlaceServiceImpl implements EntryPlaceService {

	@Resource
	private EntryPlaceMapper entryPlaceMapper;
	@Override
	public List<EntryPlace> getEntryPlaceList(Map<String, Object> map) {
		return entryPlaceMapper.getEntryPlaceList(map);
	}
	@Override
	public EntryPlace getEntryPlace(Map<String, Object> map) {
		return entryPlaceMapper.getEntryPlace(map);
	}

}
