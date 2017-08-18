package com.jingren.jing.school.service.courseware;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.courseware.CourseWare;
import com.jingren.jing.school.dao.courseware.CourseWareMapper;
@Service
public class CourseWareServiceImpl implements CourseWareService{
	@Resource
	private CourseWareMapper courseWareMapper;

	@Override
	public boolean saveCourseWare(CourseWare courseWare) {
		return courseWareMapper.saveCourseWare(courseWare);
	}

	@Override
	public boolean updateCourseWare(CourseWare courseWare) {
		return courseWareMapper.updateCourseWare(courseWare);
	}

	@Override
	public boolean deleteCourseWare(Integer ware_id) {
		return courseWareMapper.deleteCourseWare(ware_id);
	}

	@Override
	public CourseWare getCourseWare(Map<String, Object> map) {
		return courseWareMapper.getCourseWare(map);
	}

	@Override
	public List<CourseWare> getCourseWareList(Map<String, Object> map) {
		return courseWareMapper.getCourseWareList(map);
	}

	@Override
	public Integer getCourseWareNumber(Map<String, Object> map) {
		return courseWareMapper.getCourseWareNumber(map);
	}

}
