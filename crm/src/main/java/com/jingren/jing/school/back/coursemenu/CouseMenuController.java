package com.jingren.jing.school.back.coursemenu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: CouseMenuController.java 
* @Package com.jingren.jing.school.back.coursemenu 
* @Description: TODO 课程目录管理
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月13日 上午9:39:32 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_course_menu")
public class CouseMenuController {

	@Resource
	private CourseMenuService courseMenuService;
	/**
	* @Title: CouseMenuController.java 
	* @Package com.jingren.jing.school.back.coursemenu 
	* @Description: TODO 获取项目目录
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午11:02:04 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_course_menu.jr")
	public String get_course_menu(Model model,
			@RequestParam(value = "course_name", required = false) String course_name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(course_name)){
			map.put("course_name", course_name);
		}
		map.put("course_leavl", 1);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		Integer course_number=courseMenuService.getCourseMenuNumber(map);
		Pagers<CourseMenu> pagers = new Pagers<CourseMenu>(course_number, pageNumber, limit);
		pagers.setList(courseMenus);
		model.addAttribute("courseMenus", pagers);
		return "/course_menu/course_menu";
	}
	/**
	* @Title: CouseMenuController.java 
	* @Package com.jingren.jing.school.back.coursemenu 
	* @Description: TODO 获取二级课程目录
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午11:41:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_menu_list.jr")
	public String get_course_menu(Model model,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "id", required = false) Integer id){
		Map<String, Object> map = new HashMap<>();
		map.put("course_parent_id", course_id);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", courseMenus);
		model.addAttribute("id", id);
		return "/course_menu/course_sub_menu";
	}
	/**
	* @Title: CouseMenuController.java 
	* @Package com.jingren.jing.school.back.coursemenu 
	* @Description: TODO设置菜单是否显示
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 上午11:43:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_isshow.jr")
	public void update_isshow(HttpServletResponse response,CourseMenu courseMenu){
		if(courseMenuService.updateCourserMuen(courseMenu)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: CouseMenuController.java 
	* @Package com.jingren.jing.school.back.coursemenu 
	* @Description: TODO 设置考试时间弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 上午10:19:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_set_time.jr")
	public String to_set_time(Model model,
			@RequestParam(value = "course_id", required = false) Integer course_id){
		model.addAttribute("course_id", course_id);
		return "/course_menu/set_exam_time";
	}
	/**
	* @Title: CouseMenuController.java 
	* @Package com.jingren.jing.school.back.coursemenu 
	* @Description: TODO 设置考试时间
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 上午10:30:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/set_examtime.jr")
	public void set_examtime(HttpServletResponse response,CourseMenu courseMenu){
		courseMenu.setExam_time(courseMenu.getExam_time().replaceAll("-", "/"));
		if(courseMenuService.updateCourserMuen(courseMenu)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
