package com.jingren.jing.school.service.clearance;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.clearance.Clearance;
import com.jingren.jing.school.dao.clearance.ClearanceMapper;
@Service
public class ClearanceServiceImpl implements ClearanceService {

	@Resource
	private ClearanceMapper clearanceMapper;
	@Override
	public boolean saveClearance(Clearance clearance) {
		return clearanceMapper.saveClearance(clearance);
	}

	@Override
	public boolean updateClearance(Clearance clearance) {
		return clearanceMapper.updateClearance(clearance);
	}

	@Override
	public boolean deleteClearance(Integer clearance_id) {
		return clearanceMapper.deleteClearance(clearance_id);
	}

	@Override
	public Clearance getClearance(Map<String, Object> map) {
		return clearanceMapper.getClearance(map);
	}

	@Override
	public List<Clearance> getClearanceList(Map<String, Object> map) {
		return clearanceMapper.getClearanceList(map);
	}

	@Override
	public Integer getClearanceNumber(Map<String, Object> map) {
		return clearanceMapper.getClearanceNumber(map);
	}

}
