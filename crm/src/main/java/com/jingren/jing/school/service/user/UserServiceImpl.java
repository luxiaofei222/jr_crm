package com.jingren.jing.school.service.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.dao.user.UserMapper;


@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapper userMapper;

	@Override
	public boolean saveUser(User user) {
		return userMapper.saveUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public boolean deleteUser(Integer userid) {
		return userMapper.deleteUser(userid);
	}

	@Override
	public User getUser(Map<String, Object> map) {
		return userMapper.getUser(map);
	}

	@Override
	public List<User> getUserlist(Map<String, Object> map) {
		return userMapper.getUserlist(map);
	}

	@Override
	public Integer getUserNumber(Map<String, Object> map) {
		return userMapper.getUserNumber(map);
	}

	@Override
	public boolean updateUserMailAndPhone(User user) {
		return userMapper.updateUserMailAndPhone(user);
	}

}
