package com.jingren.jing.common.university.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.dao.UniversityMapper;
@Service
public class UniversityServiceImpl implements UniversityService {
	@Resource
	private UniversityMapper universityMapper;

	@Override
	public List<University> getUniversityList(Map<String, Object> map) {
		return universityMapper.getUniversityList(map);
	}

	@Override
	public University getUniversity(Map<String, Object> map) {
		return universityMapper.getUniversity(map);
	}

}
