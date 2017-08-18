package com.jingren.jing.school.service.teacher;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.teacher.Teacher;
import com.jingren.jing.school.dao.teacher.TeacherMapper;
@Service
public class TeacherServiceImpl implements TeacherService{
	@Resource
	private TeacherMapper teacherMapper;

	@Override
	public boolean saveTeacher(Teacher teacher) {
		return teacherMapper.saveTeacher(teacher);
	}

	@Override
	public boolean updateTeacher(Teacher teacher) {
		return teacherMapper.updateTeacher(teacher);
	}

	@Override
	public boolean deleteTeacher(Integer teacher_id) {
		return teacherMapper.deleteTeacher(teacher_id);
	}

	@Override
	public Teacher getTeacher(Map<String, Object> map) {
		return teacherMapper.getTeacher(map);
	}

	@Override
	public List<Teacher> getTeacherList(Map<String, Object> map) {
		return teacherMapper.getTeacherList(map);
	}

	@Override
	public Integer getTeacherNumber(Map<String, Object> map) {
		return teacherMapper.getTeacherNumber(map);
	}

}
