package com.jingren.jing.school.service.menu.rolemenu;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.menu.rolemenu.RoleMenu;
import com.jingren.jing.school.dao.menu.rolemenu.RoleMenuMapper;
@Service
public class RoleMenuServiceImpl implements RoleMenuService{
	@Resource
	private RoleMenuMapper roleMenuMapper;

	@Override
	public List<RoleMenu> getRoleMenuList(Map<String, Object> map) {
		return roleMenuMapper.getRoleMenuList(map);
	}

	@Override
	public boolean saveRoleMenu(RoleMenu roleMenu) {
		return roleMenuMapper.saveRoleMenu(roleMenu);
	}

	@Override
	public boolean deleteRoleMenu(Integer role_id) {
		return roleMenuMapper.deleteRoleMenu(role_id);
	}

}
