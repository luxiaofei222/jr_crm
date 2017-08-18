package com.jingren.jing.common.city.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.city.bean.City;
import com.jingren.jing.common.city.dao.CityMapper;
@Service
public class CityServiceImpl implements CityService{
	@Resource
	private CityMapper cityMapper;

	@Override
	public List<City> getCityList(Map<String, Object> map) {
		return cityMapper.getCityList(map);
	}

	@Override
	public City getCity(Map<String, Object> map) {
		return cityMapper.getCity(map);
	}

}
