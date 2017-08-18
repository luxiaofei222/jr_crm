package com.jingren.jing.school.service.cooperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.cooperation.Cooperation;
import com.jingren.jing.school.dao.cooperation.CooperationMapper;
@Service
public class CooperationServiceImpl implements CooperationService{
	@Resource
	private CooperationMapper cooperationMapper;
	

	@Override
	public boolean saveCooperation(Cooperation cooperation) {
		return cooperationMapper.saveCooperation(cooperation);
	}

	@Override
	public boolean updateCooperation(Cooperation cooperation) {
		return cooperationMapper.updateCooperation(cooperation);
	}

	@Override
	public boolean deleteCooperation(Integer cooperation_id) {
		return cooperationMapper.deleteCooperation(cooperation_id);
	}

	@Override
	public Cooperation getCooperation(Map<String, Object> map) {
		return cooperationMapper.getCooperation(map);
	}

	@Override
	public List<Cooperation> getCooperationList(Map<String, Object> map) {
		return cooperationMapper.getCooperationList(map);
	}

	@Override
	public int getCooperationNumber(Map<String, Object> map) {
		return cooperationMapper.getCooperationNumber(map);
	}

}
