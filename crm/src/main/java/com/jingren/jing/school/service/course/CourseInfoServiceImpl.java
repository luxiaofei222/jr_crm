package com.jingren.jing.school.service.course;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.course.CourseInfo;
import com.jingren.jing.school.dao.course.CourseInfoMapper;
@Service
public class CourseInfoServiceImpl implements CourseInfoService{
	@Resource
	private CourseInfoMapper courseInfoMapper;

	@Override
	public boolean saveCourseInfo(CourseInfo courseInfo) {
		return courseInfoMapper.saveCourseInfo(courseInfo);
	}

	@Override
	public boolean updateCourseInfo(CourseInfo courseInfo) {
		return courseInfoMapper.updateCourseInfo(courseInfo);
	}

	@Override
	public boolean deleteCourseInfo(Integer courseinfo_id) {
		return courseInfoMapper.deleteCourseInfo(courseinfo_id);
	}

	@Override
	public CourseInfo getCourseInfo(Map<String, Object> map) {
		return courseInfoMapper.getCourseInfo(map);
	}

	@Override
	public List<CourseInfo> getCourseInfoList(Map<String, Object> map) {
		return courseInfoMapper.getCourseInfoList(map);
	}

	@Override
	public Integer getCourseInfoNumber(Map<String, Object> map) {
		return courseInfoMapper.getCourseInfoNumber(map);
	}

	@Override
	public List<CourseInfo> getCourseInfoListByCourseId(Integer course_id) {
		return courseInfoMapper.getCourseInfoListByCourseId(course_id);
	}

	@Override
	public List<CourseInfo> getCourseInfoList_erji(Map<String, Object> map) {
		return courseInfoMapper.getCourseInfoList_erji(map);
	}

}
