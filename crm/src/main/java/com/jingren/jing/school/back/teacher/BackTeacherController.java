package com.jingren.jing.school.back.teacher;

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
/**
* @Title: BackTeacherController.java 
* @Package com.jingren.jing.school.back.teacher 
* @Description: TODO 教师管理
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月9日 上午11:22:17 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.teacher.Teacher;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.teacher.TeacherService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
@Controller
@RequestMapping("back_teacher")
public class BackTeacherController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private TeacherService teacherService;
	private static final String UP_FRONT_FILE ="images/school/back/teacher";
	
	/**
	* @Title: BackTeacherController.java 
	* @Package com.jingren.jing.school.back.teacher 
	* @Description: TODO 教师列表页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午11:28:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_teahcer_main.jr")
	public String get_teahcer_main(Model model,
			@RequestParam(value = "teacher_name", required = false) String teacher_name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(teacher_name)){
			map.put("teacher_name", teacher_name);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<Teacher> teachers=teacherService.getTeacherList(map);
		Integer teahcer_number=teacherService.getTeacherNumber(map);
		for (Teacher teacher : teachers) {
			map.clear();
			map.put("course_id", teacher.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			teacher.setCourseMenu(courseMenu);
		}
		Pagers<Teacher> pagers = new Pagers<Teacher>(teahcer_number, pageNumber, limit);
		pagers.setList(teachers);
		model.addAttribute("teachers", pagers);
		return "/teacher/teacher_main";
	}
	/**
	* @Title: BackTeacherController.java 
	* @Package com.jingren.jing.school.back.teacher 
	* @Description: TODO 添加教师窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午11:41:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_teacher.jr")
	public String to_add_teacher(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());//默认第一个课程二级分类
		List<CourseMenu> coursesubMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		return "/teacher/add_teacher";
	}
	/**
	* @Title: BackTeacherController.java 
	* @Package com.jingren.jing.school.back.teacher 
	* @Description: TODO 保存教师信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 下午1:18:10 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_teacher.jr")
	public void save_teacher(HttpServletResponse response,Teacher teacher,HttpServletRequest request,
			@RequestParam(value = "file_upload", required = false) MultipartFile file_upload){
		if(file_upload!=null){
			String path=UploadAddress.getUploadDate(file_upload, request,UP_FRONT_FILE );
			teacher.setTeacher_pic(path);
			teacher.setTeacher_time(new Date());
			if(teacherService.saveTeacher(teacher)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: BackTeacherController.java 
	* @Package com.jingren.jing.school.back.teacher 
	* @Description: TODO 删除教师信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 下午2:02:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_teacher.jr")
	public void delete_teacher(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		for (String string : array) {
			int id=Integer.valueOf(string);
			Map<String, Object> map=new HashMap<>();
			map.put("teacher_id", id);
			Teacher teacher2=teacherService.getTeacher(map);
			if(DeleteFile.findfile(teacher2.getTeacher_pic(), request)){
				DeleteFile.deleteFile1(teacher2.getTeacher_pic(), request);
			}
			teacherService.deleteTeacher(id);
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: BackTeacherController.java 
	* @Package com.jingren.jing.school.back.teacher 
	* @Description: TODO 去修改教师信息页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 下午2:08:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_teacher.jr")
	public String to_update_teacher(Model model,
			@RequestParam(value="teacher_id",required=false)Integer teacher_id){
		Map<String, Object> map=new HashMap<>();
		map.put("teacher_id", teacher_id);
		Teacher teacher=teacherService.getTeacher(map);
		map.clear();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("course_id", teacher.getCourse_id());
		CourseMenu courseMenu_sub=courseMenuService.getCouerseMenu(map);//二级课程
		map.clear();
		map.put("course_id", teacher.getCourse_parent_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);//一级课程
		
		map.clear();
		map.put("course_parent_id", teacher.getCourse_parent_id());
		List<CourseMenu> courseMenus_sub=courseMenuService.getCourserMenuList(map);//二级分类列表
		model.addAttribute("courseMenus_sub", courseMenus_sub);
		model.addAttribute("courseMenu_sub", courseMenu_sub);
		model.addAttribute("courseMenu_dan", courseMenu);
		model.addAttribute("teacher", teacher);
		return "/teacher/update_teacher";
	}
	/**
	* @Title: BackTeacherController.java 
	* @Package com.jingren.jing.school.back.teacher 
	* @Description: TODO 修改教师信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 下午2:23:49 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_teacher.jr")
	public void update_teacher(HttpServletResponse response,Teacher teacher,HttpServletRequest request,
			@RequestParam(value = "file_upload", required = false) MultipartFile file_upload){
		if(file_upload!=null){
			Map<String, Object> map=new HashMap<>();
			map.put("teacher_id", teacher.getTeacher_id());
			Teacher teacher2=teacherService.getTeacher(map);
			if(DeleteFile.findfile(teacher2.getTeacher_pic(), request)){
				DeleteFile.deleteFile1(teacher2.getTeacher_pic(), request);
			}
			String path=UploadAddress.getUploadDate(file_upload, request,UP_FRONT_FILE );
			teacher.setTeacher_pic(path);
		}
		if(teacherService.updateTeacher(teacher)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	

}
