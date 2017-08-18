package com.jingren.jing.personal.service.punch;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.personal.bean.punch.Punch;
import com.jingren.jing.personal.dao.punch.PunchMapper;
@Service
public class PunchServiceImpl implements PunchService {

	@Resource
	private PunchMapper punchMapper;
	@Override
	public boolean savePunch(Punch punch) {
		return punchMapper.savePunch(punch);
	}

	@Override
	public boolean updatePunch(Punch punch) {
		return punchMapper.updatePunch(punch);
	}

	@Override
	public boolean deletePunch(Integer punch_id) {
		return punchMapper.deletePunch(punch_id);
	}

	@Override
	public Punch getPunch(Map<String, Object> map) {
		return punchMapper.getPunch(map);
	}

	@Override
	public List<Punch> getPunchList(Map<String, Object> map) {
		return punchMapper.getPunchList(map);
	}

	@Override
	public Integer getPunchNumber(Map<String, Object> map) {
		return punchMapper.getPunchNumber(map);
	}

}
