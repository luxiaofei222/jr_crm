package com.jingren.jing.school.front.course.extend;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.service.course.CourseMenuService;
/**
* @Title: FrondExtendController.java 
* @Package com.jingren.jing.school.front.course.extend 
* @Description: TODO 课程推广也
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月14日 上午8:52:55 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("sc_extend")
public class FrondExtendController {

	@Resource
	private CourseMenuService courseMenuService;
	
	/**
	* @Title: FrondExtendController.java 
	* @Package com.jingren.jing.school.front.course.extend 
	* @Description: TODO 获取推广页
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 上午8:53:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_tuiguang_sanji.html")
	public String get_tuiguang_sanji(Model model,
			@RequestParam(value="course_id",required=false)Integer course_id){
		Map<String,Object> map=new HashMap<>();
		map.put("course_id", course_id);
		CourseMenu courseMenu =courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		return "/school/course_extend/course_extend";
	}
}
