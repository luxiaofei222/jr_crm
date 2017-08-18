package com.jingren.jing.school.service.courseware;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.courseware.MyCourseWare;
import com.jingren.jing.school.dao.courseware.MyCourseWareMapper;
@Service
public class MyCourseWareServiceImpl implements MyCourseWareService{
	@Resource
	private MyCourseWareMapper myCourseWareMapper;

	@Override
	public boolean saveMyCourseWare(MyCourseWare myCourseWare) {
		return myCourseWareMapper.saveMyCourseWare(myCourseWare);
	}

	@Override
	public boolean deleteMyCourseWare(MyCourseWare myCourseWare) {
		return myCourseWareMapper.deleteMyCourseWare(myCourseWare);
	}

	@Override
	public MyCourseWare getMyCourseWare(Map<String, Object> map) {
		return myCourseWareMapper.getMyCourseWare(map);
	}

	@Override
	public List<MyCourseWare> getMyCourseWareList(Map<String, Object> map) {
		return myCourseWareMapper.getMyCourseWareList(map);
	}

	@Override
	public Integer getMyCourseWareNumber(Map<String, Object> map) {
		return myCourseWareMapper.getMyCourseWareNumber(map);
	}

}
