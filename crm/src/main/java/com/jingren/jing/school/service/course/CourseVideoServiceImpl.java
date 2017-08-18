package com.jingren.jing.school.service.course;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.dao.course.CourseVideoMapper;
@Service
public class CourseVideoServiceImpl implements CourseVideoService{
	@Resource
	private CourseVideoMapper courseVideoMapper;

	@Override
	public boolean saveCourseVideo(CourseVideo courseVideo) {
		return courseVideoMapper.saveCourseVideo(courseVideo);
	}

	@Override
	public boolean updateCourseVideo(CourseVideo courseVideo) {
		return courseVideoMapper.updateCourseVideo(courseVideo);
	}

	@Override
	public boolean deleteCourseVideo(Integer coursevideo_id) {
		return courseVideoMapper.deleteCourseVideo(coursevideo_id);
	}

	@Override
	public CourseVideo getCourseVideo(Map<String, Object> map) {
		return courseVideoMapper.getCourseVideo(map);
	}

	@Override
	public List<CourseVideo> getCourseVideoList(Map<String, Object> map) {
		return courseVideoMapper.getCourseVideoList(map);
	}

	@Override
	public Integer getCourseVideoNumber(Map<String, Object> map) {
		return courseVideoMapper.getCourseVideoNumber(map);
	}

	@Override
	public List<CourseVideo> getCourseVideoTuijianList(Map<String, Object> map) {
		return courseVideoMapper.getCourseVideoTuijianList(map);
	}

	@Override
	public boolean updateVideoDianji(CourseVideo courseVideo) {
		return courseVideoMapper.updateVideoDianji(courseVideo);
	}

}
