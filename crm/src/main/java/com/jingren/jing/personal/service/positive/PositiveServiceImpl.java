package com.jingren.jing.personal.service.positive;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.personal.bean.positive.Positive;
import com.jingren.jing.personal.dao.positive.PositiveMapper;
@Service
public class PositiveServiceImpl implements PositiveService{

	@Resource
	private PositiveMapper positiveMapper;
	
	@Override
	public boolean savePositive(Positive positive) {
		return positiveMapper.savePositive(positive);
	}

	@Override
	public boolean updatePositive(Positive positive) {
		return positiveMapper.updatePositive(positive);
	}

	@Override
	public boolean deltePositive(Integer positive_id) {
		return positiveMapper.deltePositive(positive_id);
	}

	@Override
	public Positive getPositive(Map<String, Object> map) {
		return positiveMapper.getPositive(map);
	}

	@Override
	public List<Positive> getPositiveList(Map<String, Object> map) {
		return positiveMapper.getPositiveList(map);
	}

	@Override
	public Integer getPositiveNumber(Map<String, Object> map) {
		return positiveMapper.getPositiveNumber(map);
	}

}
