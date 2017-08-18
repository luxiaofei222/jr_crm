package com.jingren.jing.school.entrysystem.service.entryplan;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
import com.jingren.jing.school.entrysystem.dao.entryplan.EntryPlanMapper;
@Service
public class EntryPlanServiceImpl implements EntryPlanService{

	@Resource
	private EntryPlanMapper entryPlanMapper;
	@Override
	public boolean saveEntryPlan(EntryPlan entryPlan) {
		entryPlan.setEntryplan_time(new Date());
		return entryPlanMapper.saveEntryPlan(entryPlan);
	}

	@Override
	public boolean updateEntryPlan(EntryPlan entryPlan) {
		return entryPlanMapper.updateEntryPlan(entryPlan);
	}

	@Override
	public boolean deleteEntryPlan(Integer plan_id) {
		return entryPlanMapper.deleteEntryPlan(plan_id);
	}

	@Override
	public EntryPlan getEntryPlan(Map<String, Object> map) {
		
		return entryPlanMapper.getEntryPlan(map);
	}

	@Override
	public List<EntryPlan> getEntryPlanList(Map<String, Object> map) {
		return entryPlanMapper.getEntryPlanList(map);
	}

	@Override
	public Integer getEntryPlanNumber(Map<String, Object> map) {
		return entryPlanMapper.getEntryPlanNumber(map);
	}

	@Override
	public List<EntryPlan> getEntryQuChongPlanList(Map<String, Object> map) {
		return entryPlanMapper.getEntryQuChongPlanList(map);
	}

}
