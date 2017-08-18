package com.jingren.jing.school.service.course;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.dao.course.CourseMenuMapper;
@Service
public class CourseMenuServiceImpl implements CourseMenuService{
	@Resource
	private CourseMenuMapper courseMenuMapper;

	@Override
	public boolean updateCourserMuen(CourseMenu courseMenu) {
		return courseMenuMapper.updateCourserMuen(courseMenu);
	}

	@Override
	public CourseMenu getCouerseMenu(Map<String, Object> map) {
		return courseMenuMapper.getCouerseMenu(map);
	}

	@Override
	public List<CourseMenu> getCourserMenuList(Map<String, Object> map) {
		return courseMenuMapper.getCourserMenuList(map);
	}

	@Override
	public Integer getCourseMenuNumber(Map<String, Object> map) {
		return courseMenuMapper.getCourseMenuNumber(map);
	}

}
