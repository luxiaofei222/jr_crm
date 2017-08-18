package com.jingren.jing.personal.service.leave;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.personal.bean.leave.BackLeave;
import com.jingren.jing.personal.dao.leave.BackLeaveMapper;
@Service
public class BackLeaveServiceImpl implements BackLeaveService {
	@Resource
	private BackLeaveMapper backLeaveMapper;

	@Override
	public boolean saveBackLeave(BackLeave backLeave) {
		return backLeaveMapper.saveBackLeave(backLeave);
	}

	@Override
	public boolean updateBackLeave(BackLeave backLeave) {
		return backLeaveMapper.updateBackLeave(backLeave);
	}

	@Override
	public boolean delteBackLeave(Integer leave_id) {
		return backLeaveMapper.delteBackLeave(leave_id);
	}

	@Override
	public BackLeave getBackLeave(Map<String, Object> map) {
		return backLeaveMapper.getBackLeave(map);
	}

	@Override
	public List<BackLeave> getBackLeaveList(Map<String, Object> map) {
		return backLeaveMapper.getBackLeaveList(map);
	}

	@Override
	public Integer getBackLeaveNumber(Map<String, Object> map) {
		return backLeaveMapper.getBackLeaveNumber(map);
	}

}
