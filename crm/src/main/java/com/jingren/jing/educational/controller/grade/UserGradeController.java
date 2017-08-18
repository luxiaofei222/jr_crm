package com.jingren.jing.educational.controller.grade;

import java.util.Date;
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

import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.common.university.service.ReviewService;
import com.jingren.jing.educational.bean.grade.UserGrade;
import com.jingren.jing.educational.service.grade.UserGradeService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("user_grade")
public class UserGradeController {

	@Resource
	private UserGradeService userGradeService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private ReviewService reviewService;
	
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 学员成绩信息页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午5:00:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_user_grade.jr")
	public String  get_user_grade(Model model,
			@RequestParam(value = "course_parent_id", required = false) Integer course_parent_id,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "user_name", required = false) String user_name,
			@RequestParam(value = "user_phone", required = false) String user_phone,
			@RequestParam(value = "user_card", required = false) String user_card,
			@RequestParam(value = "user_zhunkao", required = false) String user_zhunkao,
			@RequestParam(value = "limit", required = false,defaultValue="20") Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1") Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		
		if(course_parent_id!=null){
			map.put("course_parent_id", course_parent_id);
			model.addAttribute("course_parent_id", course_parent_id);
		}
		if(course_id!=null){
			map.put("course_id", course_id);
			model.addAttribute("course_id", course_id);
		}
		
		if(StringUtils.isNotBlank(user_name)){
			map.put("user_name", user_name);
			model.addAttribute("user_name", user_name);
		}
		if(StringUtils.isNotBlank(user_phone)){
			map.put("user_phone", user_phone);
			model.addAttribute("user_phone", user_phone);
		}
		if(StringUtils.isNotBlank(user_card)){
			map.put("user_card", user_card);
			model.addAttribute("user_card", user_card);
		}
		if(StringUtils.isNotBlank(user_zhunkao)){
			map.put("user_zhunkao", user_zhunkao);
			model.addAttribute("user_zhunkao", user_zhunkao);
		}
		
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("parent_id", 0);
		Integer grede_number=userGradeService.getUserGradeNumber(map);
		List<UserGrade> userGrades=userGradeService.getUserGradeList(map);
		for (UserGrade userGrade : userGrades) {
			if(userGrade.getCourse_id()!=null){
				map.clear();
				map.put("course_id", userGrade.getCourse_id());
				CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
				userGrade.setCourseMenu(courseMenu);
			}
			
			if(userGrade.getDictionary_id()!=null){
				map.clear();
				map.put("dictionary_id", userGrade.getDictionary_id());
				Dictionary dictionary=dictionaryService.getDictionary(map);
				userGrade.setDictionary(dictionary);
			}
		}
		Pagers<UserGrade> pagers = new Pagers<UserGrade>(grede_number,
				pageNumber, limit);
		pagers.setList(userGrades);
		model.addAttribute("userGrades", pagers);
		map.clear();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
		return "/educational/grade/grade";
	}
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 添加学员成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午6:18:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_user_grade.jr")
	public String to_add_user_grade(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
		return "/educational/grade/add_grade";
	}
	
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 获取职称
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午6:41:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_review.jr")
	public String get_sub_review(Model model,
			@RequestParam(value = "course_id", required = false) Integer course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		List<Review> reviews = reviewService.getReviewList(map);
		model.addAttribute("reviews", reviews);
		return "/educational/grade/sub_review";
	}
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 职称等级
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午6:54:24 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_review_diclist.jr")
	public String get_review_diclist(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", 1002);
		List<Dictionary> dictionaries = dictionaryService.getDictionaryList(map);
		model.addAttribute("dictionaries", dictionaries);
		return "/educational/common/dic_list";
	}
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 保存学员成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月17日 下午7:27:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_user_grade.jr")
	public void save_user_grade(UserGrade userGrade,HttpServletResponse response,
			@RequestParam(value="str",required=false)String str,
			@RequestParam(value="str2",required=false)String str2){
		userGrade.setGrade_time(new Date());
		if(userGradeService.saveUserGrade(userGrade)){
			String[] arraystr = str.split(",");
			String[] arraystr2 = str2.split(",");
			for (int i = 0; i < arraystr.length; i++) {
				UserGrade userGrade2 = new UserGrade();
				userGrade2.setKemu(arraystr[i]);
				userGrade2.setGrade(arraystr2[i]);
				userGrade2.setParent_id(userGrade.getGrade_id());
				userGradeService.saveUserGrade(userGrade2);
			}
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 删除学员成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月18日 上午8:13:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_user_grade.jr")
	public void delete_user_grade(HttpServletResponse response,
			@RequestParam(value="grade_id",required=false)Integer grade_id){
		Map<String, Object> map=new HashMap<>();
		map.put("grade_id", grade_id);
		if(userGradeService.deleteUserGrade(map)){
			map.clear();
			map.put("parent_id", grade_id);
			userGradeService.deleteUserGrade(map);
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 查看用户成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月18日 上午8:30:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_user_grade.jr")
	public String to_check_user_grade(Model model,
			@RequestParam(value="grade_id",required=false)Integer grade_id){
		Map<String, Object> map=new HashMap<>();
		map.put("grade_id", grade_id);
		UserGrade userGrade=userGradeService.getUserGrade(map);
		if(userGrade.getCourse_id()!=null){
			map.clear();
			map.put("course_id", userGrade.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			userGrade.setCourseMenu(courseMenu);
		}
		
		if(userGrade.getDictionary_id()!=null){
			map.clear();
			map.put("dictionary_id", userGrade.getDictionary_id());
			Dictionary dictionary=dictionaryService.getDictionary(map);
			userGrade.setDictionary(dictionary);
		}
		
		if(userGrade.getReview_id()!=null){
			map.clear();
			map.put("review_id", userGrade.getReview_id());
			Review review=reviewService.getReview(map);
			userGrade.setReview(review);
		}
		model.addAttribute("userGrade", userGrade);
		map.clear();
		map.put("parent_id", grade_id);
		List<UserGrade> userGrades=userGradeService.getUserGradeList(map);
		model.addAttribute("userGrades", userGrades);
		return "/educational/grade/check_user_grade";
	}
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 修改学员成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月18日 上午10:05:08 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_user_grade.jr")
	public void update_user_grade(UserGrade userGrade,HttpServletResponse response){
		if(userGradeService.updateUserGrade(userGrade)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 打开添加成绩页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月18日 上午10:35:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_chengji.jr")
	public String to_add_chengji(Model model,
			@RequestParam(value="grade_id",required=false)Integer grade_id){
		model.addAttribute("grade_id", grade_id);
		
		return "/educational/grade/add_chengji";
	}
	/**
	* @Title: UserGradeController.java 
	* @Package com.jingren.jing.educational.controller.grade 
	* @Description: TODO 添加成绩
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月18日 上午10:37:50 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/add_chengji.jr")
	public void add_chengji(UserGrade userGrade,HttpServletResponse response){
		if(userGradeService.saveUserGrade(userGrade)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
