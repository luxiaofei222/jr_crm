package com.jingren.jing.school.entrysystem.service.entrycondition;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.dao.entrycondition.EntryConditionMapper;
@Service
public class EntryConditionServiceImpl implements EntryConditionService {

	@Resource
	private EntryConditionMapper entryConditionMapper;
	@Override
	public boolean saveEntryCondition(EntryCondition entryCondition) {
		return entryConditionMapper.saveEntryCondition(entryCondition);
	}

	@Override
	public boolean updateEntryCondition(EntryCondition entryCondition) {
		return entryConditionMapper.updateEntryCondition(entryCondition);
	}

	@Override
	public boolean delteEntryCondition(Integer condi_id) {
		return entryConditionMapper.delteEntryCondition(condi_id);
	}

	@Override
	public EntryCondition getEntryCondition(Map<String, Object> map) {
		return entryConditionMapper.getEntryCondition(map);
	}

	@Override
	public List<EntryCondition> getEntryConditionList(Map<String, Object> map) {
		return entryConditionMapper.getEntryConditionList(map);
	}

	@Override
	public Integer getEntryConditionNumber(Map<String, Object> map) {
		return entryConditionMapper.getEntryConditionNumber(map);
	}

	@Override
	public List<EntryCondition> getEntryConditionListByQuchong(Map<String, Object> map) {
		return entryConditionMapper.getEntryConditionListByQuchong(map);
	}

	@Override
	public List<EntryCondition> getEntryConditionListByCourseParent(Map<String, Object> map) {
		return entryConditionMapper.getEntryConditionListByCourseParent(map);
	}

	@Override
	public List<EntryCondition> getEntryConditionListByDic(Map<String, Object> map) {
		return entryConditionMapper.getEntryConditionListByDic(map);
	}

	@Override
	public List<EntryCondition> getEntryConditionListByQuchonghoutai(Map<String, Object> map) {
		return entryConditionMapper.getEntryConditionListByQuchonghoutai(map);
	}

	@Override
	public List<EntryCondition> getEntryConditionListByCourseWangjiao(Map<String, Object> map) {
		return entryConditionMapper.getEntryConditionListByCourseWangjiao(map);
	}

}
