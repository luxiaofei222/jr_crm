package com.jingren.jing.school.back.extend;

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
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
/**
* @Title: CourseExtendController.java 
* @Package com.jingren.jing.school.back.extend 
* @Description: TODO 课程推广
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月13日 下午5:47:57 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("extend")
public class CourseExtendController {
	@Resource
	private CourseMenuService courseMenuService;
	private static final String UP_EXTEND_FILE ="images/school/front/extend";
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
		return "/course_extend/extend";
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
		return "/course_extend/course_sub_menu";
	}
	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 本地上传图片
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午2:57:07 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/uploadpic.jr")
	public Map<String, Object> uploadpic(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "ajaxTaskFile", required = false) MultipartFile ajaxTaskFile){
		Map<String,Object> map=new HashMap<>();
		String path = UploadAddress.getUploadDate(ajaxTaskFile, request,
				UP_EXTEND_FILE);
		System.out.println(path);
		map.put("file_path", path);
		return map;
	}
	/**
	* @Title: CourseExtendController.java 
	* @Package com.jingren.jing.school.back.extend 
	* @Description: TODO 去添加推广信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午6:11:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_extend.jr")
	public String to_add_extend(Model model,
			@RequestParam(value="course_id",required=false)Integer course_id){
		Map<String,Object> map=new HashMap<>();
		map.put("course_id", course_id);
		CourseMenu courseMenu =courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		return "/course_extend/add_extend";
	}
	/**
	* @Title: CourseExtendController.java 
	* @Package com.jingren.jing.school.back.extend 
	* @Description: TODO 给手机添加推广页封面图片
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月10日 下午3:14:52 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_phone_extend.jr")
	public String to_add_phone_extend(Model model,
			@RequestParam(value="course_id",required=false)Integer course_id){
		Map<String,Object> map=new HashMap<>();
		map.put("course_id", course_id);
		CourseMenu courseMenu =courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		return "/course_extend/add_extend_phone";
	}
	/**
	* @Title: CourseExtendController.java 
	* @Package com.jingren.jing.school.back.extend 
	* @Description: TODO 添加推广课程信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 上午8:26:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_extend.jr")
	public void update_extend(HttpServletRequest request,HttpServletResponse response,CourseMenu courseMenu,
			@RequestParam(value = "file_pic", required = false) MultipartFile file_pic){
		if(file_pic!=null){
			String path = UploadAddress.getUploadDate(file_pic, request,
					UP_EXTEND_FILE);
			courseMenu.setCourse_index_pic(path);
			Map<String,Object> map=new HashMap<>();
			map.put("course_id", courseMenu.getCourse_id());
			CourseMenu courseMenu2=courseMenuService.getCouerseMenu(map);
			if(courseMenu2.getCourse_link()==null){
				courseMenu.setCourse_link("/sc_extend/get_tuiguang_sanji.html?course_id="+courseMenu.getCourse_id());
			}
			if(courseMenuService.updateCourserMuen(courseMenu)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: CourseExtendController.java 
	* @Package com.jingren.jing.school.back.extend 
	* @Description: TODO 添加手机站封面图
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月10日 下午3:22:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_extend_phone.jr")
	public void update_extend_phone(HttpServletRequest request,HttpServletResponse response,CourseMenu courseMenu,
			@RequestParam(value = "file_pic", required = false) MultipartFile file_pic){
		if(file_pic!=null){
			String path = UploadAddress.getUploadDate(file_pic, request,
					UP_EXTEND_FILE);
			courseMenu.setPhone_index_pic(path);
			if(courseMenuService.updateCourserMuen(courseMenu)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: CourseExtendController.java 
	* @Package com.jingren.jing.school.back.extend 
	* @Description: TODO 修改meta弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 上午8:45:16 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_meta.jr")
	public String to_update_meta(Model model,
			@RequestParam(value="course_id",required=false)Integer course_id){
		Map<String,Object> map=new HashMap<>();
		map.put("course_id", course_id);
		CourseMenu courseMenu =courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		return "/course_extend/update_meta";
	}
	/**
	* @Title: CourseExtendController.java 
	* @Package com.jingren.jing.school.back.extend 
	* @Description: TODO 修改meta信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 上午8:43:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_meta.jr")
	public void update_meta(HttpServletRequest request,HttpServletResponse response,CourseMenu courseMenu){
			if(courseMenuService.updateCourserMuen(courseMenu)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
	}
}
