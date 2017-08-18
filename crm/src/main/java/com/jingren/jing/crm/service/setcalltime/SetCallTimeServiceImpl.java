package com.jingren.jing.crm.service.setcalltime;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.crm.bean.setcalltime.SetCallTime;
import com.jingren.jing.crm.dao.setcalltime.SetCallTimeMapper;
@Service
public class SetCallTimeServiceImpl implements SetCallTimeService {
	@Resource
	private SetCallTimeMapper setCallTimeMapper;

	@Override
	public boolean updateSetCallTime(SetCallTime setCallTime) {
		return setCallTimeMapper.updateSetCallTime(setCallTime);
	}

	@Override
	public SetCallTime getSetCallTime() {
		return setCallTimeMapper.getSetCallTime();
	}

}
