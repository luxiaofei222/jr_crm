package com.jingren.jing.school.service.menu;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jingren.jing.school.bean.menu.Menu;
import com.jingren.jing.school.dao.menu.MenuMapper;

@Service
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuMapper menuMapper;

	@Override
	public List<Menu> getMenuList(Map<String, Object> map) {
		return menuMapper.getMenuList(map);
	}

	@Override
	public Integer getMenuNumber(Map<String, Object> map) {
		return menuMapper.getMenuNumber(map);
	}

	@Override
	public Menu getMenu(Map<String, Object> map) {
		return menuMapper.getMenu(map);
	}

	@Override
	public List<Menu> getMenuListPermission(Map<String, Object> map) {
		return menuMapper.getMenuListPermission(map);
	}

}
