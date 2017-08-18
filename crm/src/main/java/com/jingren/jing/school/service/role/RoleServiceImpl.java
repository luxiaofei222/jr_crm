package com.jingren.jing.school.service.role;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.role.Role;
import com.jingren.jing.school.dao.role.RoleMapper;
@Service
public class RoleServiceImpl implements RoleService{
	@Resource
	private RoleMapper roleMapper;

	@Override
	public boolean saveRole(Role role) {
		return roleMapper.saveRole(role);
	}

	@Override
	public boolean deleteRole(Integer role_id) {
		return roleMapper.deleteRole(role_id);
	}

	@Override
	public Role getRole(Map<String, Object> map) {
		return roleMapper.getRole(map);
	}

	@Override
	public List<Role> getRoleList(Map<String, Object> map) {
		return roleMapper.getRoleList(map);
	}

	@Override
	public Integer getRoleNumber(Map<String, Object> map) {
		return roleMapper.getRoleNumber(map);
	}

}
