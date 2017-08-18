package com.jingren.jing.common.university.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.dao.ChengkaoScMapper;
@Service
public class ChengkaoScServiceImpl implements ChengkaoScService {
	@Resource
	private ChengkaoScMapper chengkaoScMapper;

	@Override
	public ChengkaoSc getChengkaoSc(Map<String, Object> map) {
		return chengkaoScMapper.getChengkaoSc(map);
	}

	@Override
	public List<ChengkaoSc> getChengkaoScList(Map<String, Object> map) {
		return chengkaoScMapper.getChengkaoScList(map);
	}

}
