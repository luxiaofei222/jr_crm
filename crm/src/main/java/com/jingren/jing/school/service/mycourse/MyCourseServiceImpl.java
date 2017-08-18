package com.jingren.jing.school.service.mycourse;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.mycourse.MyCourse;
import com.jingren.jing.school.dao.mycourse.MyCourseMapper;
@Service
public class MyCourseServiceImpl implements MyCourseService{
	@Resource
	private MyCourseMapper myCourseMapper;

	@Override
	public boolean saveMyCourse(MyCourse myCourse) {
		return myCourseMapper.saveMyCourse(myCourse);
	}

	@Override
	public boolean deleteMyCourse(Map<String, Object> map) {
		return myCourseMapper.deleteMyCourse(map);
	}

	@Override
	public MyCourse getMyCourse(Map<String, Object> map) {
		return myCourseMapper.getMyCourse(map);
	}

	@Override
	public List<MyCourse> getMyCourseList(Map<String, Object> map) {
		return myCourseMapper.getMyCourseList(map);
	}

	@Override
	public Integer getMyCourseNumber(Map<String, Object> map) {
		return myCourseMapper.getMyCourseNumber(map);
	}

	@Override
	public boolean updateMyCourse(MyCourse myCourse) {
		return myCourseMapper.updateMyCourse(myCourse);
	}

}
