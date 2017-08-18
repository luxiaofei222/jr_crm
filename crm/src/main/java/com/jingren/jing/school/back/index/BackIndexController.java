package com.jingren.jing.school.back.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.menu.Menu;
import com.jingren.jing.school.bean.menu.rolemenu.RoleMenu;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.school.service.menu.MenuService;
import com.jingren.jing.school.service.menu.rolemenu.RoleMenuService;
/**
* @Title: BackIndexController.java 
* @Package com.jingren.jing.school.back.index 
* @Description: TODO 后台网校首页
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月2日 上午11:15:31 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("")
public class BackIndexController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private RoleMenuService roleMenuService;
	@Resource
	private MenuService menuService;
	/**
	* @Title: BackIndexController.java 
	* @Package com.jingren.jing.school.back.index 
	* @Description: TODO 系统首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 上午11:51:31 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("admin.jr")
	public String get_sc_back(HttpSession session,Model model){
		Employee employee=(Employee) session.getAttribute("employee_session");
		Map<String, Object> map=new HashMap<>();
		map.put("role_id", employee.getRole_id());
		List<RoleMenu> roleMenus=roleMenuService.getRoleMenuList(map);
		map.clear();
		map.put("menu_leval", 1);//一级菜单
		List<Menu> menus=menuService.getMenuList(map);
		for (Menu menu : menus) {
			for (RoleMenu roleMenu : roleMenus) {
				if(roleMenu.getMenu_id()==menu.getMenu_id()){
					menu.setIs_permission(true);
				}
			}
		}
		model.addAttribute("menus", menus);
		return "/index";
	}
	/**
	* @Title: BackIndexController.java 
	* @Package com.jingren.jing.school.back.index 
	* @Description: TODO 进入系统
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月19日 下午5:43:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("main.jr")
	public String get_main(HttpSession session,Model model,
			@RequestParam(value="menu_id",required=false)Integer menu_id){
		Map<String, Object> map=new HashMap<>();
		if(menu_id!=null){
			map.put("menu_id", menu_id);
			Menu menu=menuService.getMenu(map);
			session.removeAttribute("menu_id");
			session.setAttribute("menu_id", menu_id);
			if(menu.getMenu_name().equals("网校")){
				return "/scmain";
			}else if(menu.getMenu_name().equals("人员")){
				return "/scmain";
			}else if(menu.getMenu_name().equals("CRM")){
				return "/crmmain";
			}else if(menu.getMenu_name().equals("教务")){
				return "/educationalmain";
			}else if(menu.getMenu_name().equals("财务")){
				return "/caiwumain";
			}else if(menu.getMenu_name().equals("OA")){
				return "/oamain";
			}
		}
		return null;
	}
}
