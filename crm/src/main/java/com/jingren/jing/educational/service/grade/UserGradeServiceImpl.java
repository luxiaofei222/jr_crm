package com.jingren.jing.educational.service.grade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.educational.bean.grade.UserGrade;
import com.jingren.jing.educational.dao.grade.UserGradeMapper;
@Service
public class UserGradeServiceImpl implements UserGradeService {
	@Resource
	private UserGradeMapper userGradeMapper;

	@Override
	public boolean saveUserGrade(UserGrade userGrade) {
		return userGradeMapper.saveUserGrade(userGrade);
	}

	@Override
	public boolean updateUserGrade(UserGrade userGrade) {
		return userGradeMapper.updateUserGrade(userGrade);
	}

	@Override
	public boolean deleteUserGrade(Map<String, Object> map) {
		return userGradeMapper.deleteUserGrade(map);
	}

	@Override
	public UserGrade getUserGrade(Map<String, Object> map) {
		return userGradeMapper.getUserGrade(map);
	}

	@Override
	public List<UserGrade> getUserGradeList(Map<String, Object> map) {
		return userGradeMapper.getUserGradeList(map);
	}

	@Override
	public Integer getUserGradeNumber(Map<String, Object> map) {
		return userGradeMapper.getUserGradeNumber(map);
	}

}
