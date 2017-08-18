package com.jingren.jing.oa.service.task;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.oa.bean.task.OaTask;
import com.jingren.jing.oa.dao.task.OaTaskMapper;
@Service
public class OaTaskServiceImpl implements OaTaskService {
	@Resource
	private OaTaskMapper oaTaskMapper;

	@Override
	public boolean saveOaTask(OaTask oaTask) {
		return oaTaskMapper.saveOaTask(oaTask);
	}

	@Override
	public boolean updateOaTask(OaTask oaTask) {
		return oaTaskMapper.updateOaTask(oaTask);
	}

	@Override
	public boolean deleteOaTask(Integer oatask_id) {
		return oaTaskMapper.deleteOaTask(oatask_id);
	}

	@Override
	public OaTask getOaTask(Map<String, Object> map) {
		return oaTaskMapper.getOaTask(map);
	}

	@Override
	public List<OaTask> getOaTaskList(Map<String, Object> map) {
		return oaTaskMapper.getOaTaskList(map);
	}

	@Override
	public Integer getOaTaskNumber(Map<String, Object> map) {
		return oaTaskMapper.getOaTaskNumber(map);
	}

	@Override
	public List<OaTask> getOataskJieEmployee(Map<String, Object> map) {
		return oaTaskMapper.getOataskJieEmployee(map);
	}

}
