package com.jingren.jing.oa.service.oa_work;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.oa.bean.oa_work.OAWork;
import com.jingren.jing.oa.dao.oa_work.OAWorkMapper;
@Service
public class OAWorkServiceImpl implements OAWorkService{

	@Resource
	private OAWorkMapper oAWorkMapper;
	@Override
	public boolean saveOAWork(OAWork oAWork) {
		return oAWorkMapper.saveOAWork(oAWork);
	}

	@Override
	public boolean updateOAWork(OAWork oAWork) {
		return oAWorkMapper.updateOAWork(oAWork);
	}

	@Override
	public List<OAWork> getOAWorkList(Map<String, Object> map) {
		return oAWorkMapper.getOAWorkList(map);
	}

}
