package com.jingren.jing.school.back.role;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingren.jing.common.tree.bean.HighTree;
import com.jingren.jing.school.bean.menu.Menu;
import com.jingren.jing.school.bean.menu.rolemenu.RoleMenu;
import com.jingren.jing.school.bean.role.Role;
import com.jingren.jing.school.service.menu.MenuService;
import com.jingren.jing.school.service.menu.rolemenu.RoleMenuService;
import com.jingren.jing.school.service.role.RoleService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: RoleController.java 
* @Package com.jingren.jing.school.back.role 
* @Description: TODO 角色管理
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月2日 上午9:49:40 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_role")
public class RoleController {

	@Resource
	private RoleService roleService;
	@Resource
	private MenuService menuService;
	@Resource
	private RoleMenuService roleMenuService;
	/**
	* @Title: RoleController.java 
	* @Package com.jingren.jing.school.back.role 
	* @Description: TODO 角色列表页-角色首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午12:46:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_role_main.jr")
	public String get_role_main(Model model,
			@RequestParam(value="role_name",required=false)String role_name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(role_name)){
			map.put("role_name", role_name);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<Role> roles=roleService.getRoleList(map);
		Integer role_number=roleService.getRoleNumber(map);
		Pagers<Role> pagers = new Pagers<Role>(role_number, pageNumber, limit);
		pagers.setList(roles);
		model.addAttribute("roles", pagers);
		return "/role/role";
	}
	/**
	* @Title: RoleController.java 
	* @Package com.jingren.jing.school.back.role 
	* @Description: TODO 添加角色页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午2:02:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_role.jr")
	public String to_add_role(){
		
		return "/role/add_role";
	}
	/**
	* @Title: RoleController.java 
	* @Package com.jingren.jing.school.back.role 
	* @Description: TODO 检测角色名称是否重复
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午2:44:46 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_role.jr")
	public void check_role(HttpServletResponse response,
			@RequestParam(value="role_name",required=false)String role_name){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(role_name)){
			map.put("role_name", role_name);
			Role role=roleService.getRole(map);
			if(role!=null){//角色名称存在
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "2");
			}
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: RoleController.java 
	* @Package com.jingren.jing.school.back.role 
	* @Description: TODO 保存角色信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午2:45:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_role.jr")
	public void save_role(Role role,HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="role_name",required=false)String role_name){
		role.setRole_time(new Date());
		if(roleService.saveRole(role)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: RoleController.java 
	* @Package com.jingren.jing.school.back.role 
	* @Description: TODO 删除角色信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月2日 下午3:37:16 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_role.jr")
	public void delete_role(HttpServletResponse response,
			@RequestParam(value="role_id",required=false)Integer role_id){
		if(roleService.deleteRole(role_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: RoleController.java 
	* @Package com.jingren.jing.school.back.role 
	* @Description: TODO 获取权限页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月19日 上午8:16:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("get_permission_page.jr")
	public String get_permission_page(Model model,
			@RequestParam(value="role_id",required=false) Integer role_id){
		Map<String, Object> map = new HashMap<>();
		map.put("role_id", role_id);
		Role role=roleService.getRole(map);
		model.addAttribute("role", role);
		return "/permission/permission";
	}
	/**
	* @Title: RoleController.java 
	* @Package com.jingren.jing.school.back.role 
	* @Description: TODO 获取权限菜单
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月19日 上午11:20:01 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_menu_tree.jr")
	public List<HighTree> get_menu_tree(Model model,
			@RequestParam(value="role_id",required=false) Integer role_id,
			@RequestParam(value="menu_id",required=false) Integer menu_id){
		Map<String, Object> map = new HashMap<>();
		List<HighTree> highTrees=new ArrayList<>();
		map.put("role_id", role_id);
		Role role=roleService.getRole(map);
		model.addAttribute("role", role);
		List<RoleMenu> roleMenus=roleMenuService.getRoleMenuList(map);
		
		map.clear();
		if(menu_id!=null){
			map.put("parent_id", menu_id);//一级菜单
		}else{
			map.put("menu_leval", 1);
		}
		List<Menu> menus=menuService.getMenuList(map);
		if(menus.size()>0){
			for (Menu menu : menus) {
				HighTree highTree=new HighTree();
				highTree.setId(String.valueOf(menu.getMenu_id()));
				highTree.setText(menu.getMenu_name());;
				map.clear();
				map.put("parent_id", menu.getMenu_id());
				List<Menu> menussub=menuService.getMenuList(map);
				if(menussub.size()==0){//判断是否有子节点
					highTree.setState("");
				}
				for (RoleMenu roleMenu : roleMenus) {
					if(roleMenu.getMenu_id()==menu.getMenu_id()){
						highTree.setChecked(true);
						break;
					}
				}
				highTrees.add(highTree);
			}
		}else{
			HighTree highTree=new HighTree();
			highTree.setState("");
			highTrees.add(highTree);
		}
		return highTrees;
	}
	/**
	* @Title: RoleController.java 
	* @Package com.jingren.jing.school.back.role 
	* @Description: TODO 设置权限
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月19日 上午11:48:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_role_menu.jr")
	public void save_role_menu(HttpServletResponse response,RoleMenu roleMenu,
			@RequestParam(value = "role_id", required = false) Integer role_id,
			@RequestParam(value = "str", required = false) String str){
			roleMenuService.deleteRoleMenu(role_id);
			if(StringUtils.isNotBlank(str)){
				String[] str_array = str.split(",");
				for (int i = 0; i < str_array.length; i++) {
					int menu_id = Integer.parseInt(str_array[i]);
					roleMenu.setRole_id(role_id);
					roleMenu.setMenu_id(menu_id);
					roleMenuService.saveRoleMenu(roleMenu);
				}
				ResponseUtils.renderText(response, "权限设置成功！");
			}else{
				ResponseUtils.renderText(response, "权限设置成功！");
			}
	}
}
