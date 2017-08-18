package com.jingren.jing.school.entrysystem.service.entryinfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.dao.entryinfo.EntryInfoMapper;
@Service
public class EntryInfoServiceImpl implements EntryInfoService{

	@Resource
	private EntryInfoMapper entryInfoMapper;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean saveEntryInfo(EntryInfo entryInfo) {
		entryInfo.setEntryInfoTime(new Date());
		return entryInfoMapper.saveEntryInfo(entryInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public boolean updateEntryInfo(EntryInfo entryInfo) {
		return entryInfoMapper.updateEntryInfo(entryInfo);
	}

	@Override
	public boolean deleteEntryInfo(Integer info_id) {
		return entryInfoMapper.deleteEntryInfo(info_id);
	}

	@Override
	public EntryInfo getEntryInfo(Map<String, Object> map) {
		return entryInfoMapper.getEntryInfo(map);
	}

	@Override
	public List<EntryInfo> getEntryInfoList(Map<String, Object> map) {
		return entryInfoMapper.getEntryInfoList(map);
	}

	@Override
	public Integer getEntryInfoNumber(Map<String, Object> map) {
		return entryInfoMapper.getEntryInfoNumber(map);
	}

	@Override
	public boolean updateEntryInfoPic(EntryInfo entryInfo) {
		return entryInfoMapper.updateEntryInfoPic(entryInfo);
	}

}
