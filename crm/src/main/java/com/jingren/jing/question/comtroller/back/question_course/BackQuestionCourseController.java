package com.jingren.jing.question.comtroller.back.question_course;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.question.bean.question_course.QuestionCourse;
import com.jingren.jing.question.service.question_course.QuestionCourseService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: BackQuestionCourseController.java 
* @Package com.jingren.jing.question.comtroller.back.question_course 
* @Description: TODO 题库管理-课程分类信息
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月9日 下午2:00:25 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_question_course")
public class BackQuestionCourseController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private QuestionCourseService questionCourseService;
	/**
	* @Title: BackQuestionCourseController.java 
	* @Package com.jingren.jing.question.comtroller.back.question_course 
	* @Description: TODO 题库课程分类信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午2:24:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_question_main.jr")
	public String get_question_main(Model model){
		Map<String, Object> map=new HashMap<>();
//		map.put("is_show", "显示");
		List<QuestionCourse> questionCourses=questionCourseService.getQuestionCourseQuchongList(map);
		List<CourseMenu> courseMenus=new ArrayList<>();;
		for (QuestionCourse questionCourse : questionCourses) {
			map.clear();
			map.put("course_id", questionCourse.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			Integer qusetion_course_number=questionCourseService.getQuestionCourseNumber(map);
			courseMenu.setQuestion_number(qusetion_course_number);
			courseMenus.add(courseMenu);
		}
		model.addAttribute("courseMenus", courseMenus);
		return "/question/questioncourse/question_course_menu";
	}
	/**
	* @Title: BackQuestionCourseController.java 
	* @Package com.jingren.jing.question.comtroller.back.question_course 
	* @Description: TODO 添加分类信息弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午3:08:23 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_question_course.jr")
	public String to_add_question_course(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 1);
		map.put("is_show", "是");
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);//一级课程分类
		map.clear();
		map.put("is_show", "是");
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());//默认第一个课程二级分类
		List<CourseMenu> coursesubMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		return "/question/questioncourse/add_question_course";
	}
	/**
	* @Title: BackQuestionCourseController.java 
	* @Package com.jingren.jing.question.comtroller.back.question_course 
	* @Description: TODO 检查题库分类信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午3:18:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_question_course_name.jr")
	public void check_question_course_name(HttpServletResponse response,
			@RequestParam(value="course_id",required=false) Integer course_id,
			@RequestParam(value="question_course_name",required=false) String question_course_name){
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		map.put("question_course_name", question_course_name);
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		if(questionCourse!=null){
			ResponseUtils.renderText(response, "1");//重复
		}else{
			ResponseUtils.renderText(response, "2");//不重复
		}
	}
	/**
	* @Title: BackQuestionCourseController.java 
	* @Package com.jingren.jing.question.comtroller.back.question_course 
	* @Description: TODO 添加课程分类信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午3:20:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_question_course.jr")
	public  void save_question_course(HttpServletResponse response,QuestionCourse questionCourse){
		if(questionCourseService.saveQuestionCourse(questionCourse)){
			ResponseUtils.renderText(response, "1");//成功
		}else{
			ResponseUtils.renderText(response, "2");//失败
		}
	}
	/**
	* @Title: BackQuestionCourseController.java 
	* @Package com.jingren.jing.question.comtroller.back.question_course 
	* @Description: TODO 单独添加题库课程信息弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 下午3:52:15 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_question_course_single.jr")
	public String to_add_question_course_single(Model model,
			@RequestParam(value="course_id",required=false) Integer course_id,
			@RequestParam(value="course_parent_id",required=false) Integer course_parent_id){
		model.addAttribute("course_id", course_id);
		model.addAttribute("course_parent_id", course_parent_id);
		return "/question/questioncourse/add_question_course_single";
	}
	/**
	* @Title: BackQuestionCourseController.java 
	* @Package com.jingren.jing.question.comtroller.back.question_course 
	* @Description: TODO 获取题库分类名称
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 上午8:51:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_menu_list.jr")
	public String get_sub_menu_list(Model model,
			@RequestParam(value="course_id",required=false)Integer course_id,
			@RequestParam(value="id",required=false)Integer id){
		model.addAttribute("id", id);
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		List<QuestionCourse> questionCourses=questionCourseService.getQuestionCourseList(map);
		model.addAttribute("questionCourses", questionCourses);
		return "/question/questioncourse/course_sub_menu";
	}
	/**
	* @Title: BackQuestionCourseController.java 
	* @Package com.jingren.jing.question.comtroller.back.question_course 
	* @Description: TODO 修改题库信息弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 上午9:19:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("to_update_question_course.jr")
	public String to_update_question_course(Model model,
			@RequestParam(value="question_course_id",required=false)Integer question_course_id){
		Map<String, Object> map = new HashMap<>();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		map.clear();
		map.put("course_leavl", 1);
		map.put("is_show", "是");
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);//一级课程分类
		map.clear();
		map.put("is_show", "是");
		map.put("course_parent_id", questionCourse.getCourse_parent_id());//二级课程分类
		List<CourseMenu> coursesubMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		return "/question/questioncourse/update_question_course";
	}
	/**
	* @Title: BackQuestionCourseController.java 
	* @Package com.jingren.jing.question.comtroller.back.question_course 
	* @Description: TODO 修改题库分类信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 上午9:20:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_question_course.jr")
	public void update_question_course(HttpServletResponse response,QuestionCourse questionCourse){
		if(questionCourseService.updateQuestionCourse(questionCourse)){
			ResponseUtils.renderText(response, "1");//成功
		}else{
			ResponseUtils.renderText(response, "2");//失败
		}
	}
}
