package com.jingren.jing.personal.service.desert;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.personal.bean.desert.DesertJob;
import com.jingren.jing.personal.dao.desert.DesertJobMapper;
@Service
public class DesertJobServiceImpl implements DesertJobService {

	@Resource
	private DesertJobMapper desertJobMapper;
	@Override
	public boolean saveDesertJob(DesertJob desertJob) {
		return desertJobMapper.saveDesertJob(desertJob);
	}

	@Override
	public boolean updateDesertJob(DesertJob desertJob) {
		return desertJobMapper.updateDesertJob(desertJob);
	}

	@Override
	public boolean delteDesertJob(Integer desert_id) {
		return desertJobMapper.delteDesertJob(desert_id);
	}

	@Override
	public DesertJob getDesertJob(Map<String, Object> map) {
		return desertJobMapper.getDesertJob(map);
	}

	@Override
	public List<DesertJob> getDesertJobList(Map<String, Object> map) {
		return desertJobMapper.getDesertJobList(map);
	}

	@Override
	public Integer getDesertJobNumber(Map<String, Object> map) {
		return desertJobMapper.getDesertJobNumber(map);
	}

}
