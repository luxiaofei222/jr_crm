package com.jingren.jing.school.service.advertising;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.advertising.Advertising;
import com.jingren.jing.school.dao.advertising.AdvertisingMapper;
@Service
public class AdvertisingServiceImpl implements AdvertisingService{
	@Resource
	private AdvertisingMapper advertisingMapper;

	@Override
	public boolean saveAdvertising(Advertising advertising) {
		return advertisingMapper.saveAdvertising(advertising);
	}

	@Override
	public boolean deleteAdvertising(Integer adver_id) {
		return advertisingMapper.deleteAdvertising(adver_id);
	}

	@Override
	public List<Advertising> getAdvertisingList(Map<String, Object> map) {
		return advertisingMapper.getAdvertisingList(map);
	}

	@Override
	public Integer getAdvertisingNumber(Map<String, Object> map) {
		return advertisingMapper.getAdvertisingNumber(map);
	}

	@Override
	public boolean updateAdvertising(Advertising advertising) {
		return advertisingMapper.updateAdvertising(advertising);
	}

	@Override
	public Advertising getAdvertising(Map<String, Object> map) {
		return advertisingMapper.getAdvertising(map);
	}

}
