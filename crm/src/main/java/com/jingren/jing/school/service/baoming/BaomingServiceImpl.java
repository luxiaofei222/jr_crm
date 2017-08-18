package com.jingren.jing.school.service.baoming;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.baoming.Baoming;
import com.jingren.jing.school.dao.baoming.BaomingMapper;
@Service
public class BaomingServiceImpl implements BaomingService{
	@Resource
	private BaomingMapper baomingMapper;

	@Override
	public Baoming getBaoming(Map<String, Object> map) {
		return baomingMapper.getBaoming(map);
	}

	@Override
	public List<Baoming> getBaomingList(Map<String, Object> map) {
		return baomingMapper.getBaomingList(map);
	}

	@Override
	public Integer getBaomingNumber(Map<String, Object> map) {
		return baomingMapper.getBaomingNumber(map);
	}

	@Override
	public boolean deleteBaoming(Integer baoming_id) {
		return baomingMapper.deleteBaoming(baoming_id);
	}

	@Override
	public boolean saveBaoming(Baoming baoming) {
		return baomingMapper.saveBaoming(baoming);
	}

}
