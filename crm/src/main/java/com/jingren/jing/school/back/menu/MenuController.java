package com.jingren.jing.school.back.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.menu.Menu;
import com.jingren.jing.school.service.menu.MenuService;

/**
* @Title: MenuController.java 
* @Package com.jingren.jing.school.back.menu 
* @Description: TODO 菜单
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月2日 上午10:44:46 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_menu")
public class MenuController {

	@Resource
	private MenuService menuService;
	
	/**
	 * 网校左侧菜单列表
	* @Title: MenuController.java 
	* @Package com.jingren.jing.school.back.menu 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 上午10:53:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/menu_list.jr")
	public String get_menu_list(Model model,HttpSession session){
		Employee employee=(Employee) session.getAttribute("employee_session");
		Integer menu_id=(Integer) session.getAttribute("menu_id");
		Map<String, Object> map=new HashMap<>();
		map.put("parent_id", menu_id);
		map.put("employee_id", employee.getEmployee_id());
		List<Menu> menus=menuService.getMenuListPermission(map);
		for (Menu menu : menus) {
			map.clear();
			map.put("parent_id", menu.getMenu_id());
			map.put("employee_id", employee.getEmployee_id());
			List<Menu> menus_sub=menuService.getMenuListPermission(map);
			menu.setMenus_sub(menus_sub);
		}
		model.addAttribute("menus", menus);
		return "/common/menu_first";
	}
}
