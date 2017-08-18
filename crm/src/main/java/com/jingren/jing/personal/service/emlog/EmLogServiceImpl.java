package com.jingren.jing.personal.service.emlog;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.personal.bean.emlog.EmLog;
import com.jingren.jing.personal.dao.emlog.EmLogMapper;
@Service
public class EmLogServiceImpl implements EmLogService {

	@Resource
	private EmLogMapper emLogMapper;
	@Override
	public boolean saveEmLog(EmLog emLog) {
		return emLogMapper.saveEmLog(emLog);
	}

	@Override
	public boolean updateEmLog(EmLog emLog) {
		return emLogMapper.updateEmLog(emLog);
	}

	@Override
	public boolean deleteEmLog(Integer emlog_id) {
		return emLogMapper.deleteEmLog(emlog_id);
	}

	@Override
	public EmLog getEmLog(Map<String, Object> map) {
		return emLogMapper.getEmLog(map);
	}

	@Override
	public List<EmLog> getEmLogList(Map<String, Object> map) {
		return emLogMapper.getEmLogList(map);
	}

	@Override
	public Integer getEmLogNumber(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return emLogMapper.getEmLogNumber(map);
	}

}
