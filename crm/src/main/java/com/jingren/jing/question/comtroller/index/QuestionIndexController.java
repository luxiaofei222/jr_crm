package com.jingren.jing.question.comtroller.index;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;
import com.jingren.jing.question.bean.question_course.QuestionCourse;
import com.jingren.jing.question.bean.zhentirecord.ZhentiRecord;
import com.jingren.jing.question.bean.zhentiyear.ZhentiYears;
import com.jingren.jing.question.service.chapter_exercises.ChapterExercisesService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuerstionOptionService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuestionService;
import com.jingren.jing.question.service.chapterrecord.ChapterRecordService;
import com.jingren.jing.question.service.question_collection.QuestionCollectionService;
import com.jingren.jing.question.service.question_course.QuestionCourseService;
import com.jingren.jing.question.service.zhentirecord.ZhentiRecordService;
import com.jingren.jing.question.service.zhentiyear.ZhentiYearsService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.course.CourseMenuService;
/**
* @Title: QuestionIndexController.java 
* @Package com.jingren.jing.question.comtroller.index 
* @Description: TODO 题库首页
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月9日 上午9:33:13 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("questionindex")
public class QuestionIndexController {
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private QuestionCourseService questionCourseService;
	@Resource
	private ChapterQuestionService chapterQuestionService;
	@Resource
	private ChapterExercisesService ChapterExercisesService;
	@Resource
	private ChapterQuerstionOptionService chapterQuerstionOptionService;
	@Resource
	private ChapterRecordService chapterRecordService;
	@Resource
	private QuestionCollectionService questionCollectionService;
	@Resource
	private ZhentiYearsService zhentiYearsService;
	@Resource
	private ZhentiRecordService zhentiRecordService;
	/**
	* @Title: QuestionIndexController.java 
	* @Package com.jingren.jing.question.comtroller.index 
	* @Description: TODO 进入题库首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月9日 上午9:34:18 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_question_index.html")
	public String get_question_index(HttpServletRequest request,Model model,HttpSession session){
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map=new HashMap<>();
			map.put("course_parent_id", 0);
			map.put("is_show", "是");
			List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
			for (CourseMenu courseMenu : courseMenus) {
				map.clear();
				map.put("course_parent_id", courseMenu.getCourse_id());
				map.put("is_show", "是");
				List<CourseMenu> course_sub=courseMenuService.getCourserMenuList(map);
				for (CourseMenu courseMenu2 : course_sub) {
					map.clear();
					map.put("course_id", courseMenu2.getCourse_id());
					map.put("is_show", "显示");
					Integer questionnumber=questionCourseService.getQuestionCourseNumber(map);
					courseMenu2.setQuestion_number(questionnumber);
					Integer optionnumber=0;
					Integer shujuannumber=0;
					List<QuestionCourse> questionCourses=questionCourseService.getQuestionCourseList(map);
					for (QuestionCourse questionCourse : questionCourses) {
						map.clear();
						map.put("question_course_id", questionCourse.getQuestion_course_id());
						optionnumber+=chapterQuestionService.getChapterQuestionNumber(map);
						shujuannumber+=zhentiYearsService.getZhentiYearsNumber(map);
					}
					courseMenu2.setChapter_option_number(optionnumber);
					courseMenu2.setShijuan_number(shujuannumber);//试卷数量
				}
				courseMenu.setSub_list(course_sub);
			}
			model.addAttribute("courseMenus", courseMenus);
			return "/question/index";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			return "/school/login/login";
		}
	}

	/**
	* @Title: QuestionIndexController.java 
	* @Package com.jingren.jing.question.comtroller.index 
	* @Description: TODO 进入题库分类二级页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 上午10:34:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_selest_question_index.html")
	public String  get_selest_question_index(Model model,HttpSession session,
			@RequestParam(value="course_id",required=false)Integer course_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_id", course_id);
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		map.put("is_show", "显示");
		List<QuestionCourse> questionCourses=questionCourseService.getQuestionCourseList(map);
		model.addAttribute("questionCourses", questionCourses);
		model.addAttribute("question_course_name", questionCourses.get(0).getQuestion_course_name());
		model.addAttribute("question_course_id", questionCourses.get(0).getQuestion_course_id());
		User user=(User) session.getAttribute("user_session");
		map.clear();
		map.put("user_id_cuowu", user.getUser_id());
		map.put("question_course_id", questionCourses.get(0).getQuestion_course_id());
		map.put("group_order", "group_order");
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		map.put("is_right", "错误");
		Integer cuowu_number=chapterRecordService.getChapterRecordNumber(map);
		model.addAttribute("cuowu_number", cuowu_number);
		model.addAttribute("chapter_number", chapterRecords.size());
		map.clear();
		map.put("question_course_id", questionCourses.get(0).getQuestion_course_id());
		map.put("user_id", user.getUser_id());
		Integer collection_number=questionCollectionService.getQuestionCollectionNumber(map);
		model.addAttribute("collection_number", collection_number);
		//试卷做题记录
		map.clear();
		map.put("recourd_state", 2);
		map.put("question_course_id", questionCourses.get(0).getQuestion_course_id());
		map.put("user_id", user.getUser_id());
		map.put("parent_id", 0);
		List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
		if(zhentiRecords.size()>0){
			map.clear();
			map.put("zhenti_id", zhentiRecords.get(0).getZhenti_id());
			ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
			model.addAttribute("zhentiYears", zhentiYears);
		}else{
			model.addAttribute("zhentiYears", null);
		}
		return "/question/select_topic/select_question";
	}
	/**
	* @Title: QuestionIndexController.java 
	* @Package com.jingren.jing.question.comtroller.index 
	* @Description: TODO 切换题库课程分类名称
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 上午11:49:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_selest_question_qiehuan.html")
	public String  get_selest_question_qiehuan(Model model,HttpSession session,
			@RequestParam(value="question_course_id",required=false)Integer question_course_id){
		Map<String, Object> map=new HashMap<>();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		map.put("is_show", "显示");
		List<QuestionCourse> questionCourses=questionCourseService.getQuestionCourseList(map);
		model.addAttribute("questionCourses", questionCourses);
		model.addAttribute("question_course_name", questionCourse.getQuestion_course_name());
		model.addAttribute("question_course_id",question_course_id);
		User user=(User) session.getAttribute("user_session");
		map.clear();
		map.put("user_id_cuowu", user.getUser_id());
		map.put("question_course_id", questionCourses.get(0).getQuestion_course_id());
		map.put("group_order", "group_order");
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		map.put("is_right", "错误");
		Integer cuowu_number=chapterRecordService.getChapterRecordNumber(map);
		model.addAttribute("cuowu_number", cuowu_number);
		model.addAttribute("chapter_number", chapterRecords.size());
		map.clear();
		map.put("question_course_id", questionCourses.get(0).getQuestion_course_id());
		map.put("user_id", user.getUser_id());
		Integer collection_number=questionCollectionService.getQuestionCollectionNumber(map);
		model.addAttribute("collection_number", collection_number);
		//试卷做题记录
		map.clear();
		map.put("recourd_state", 2);
		map.put("question_course_id", questionCourses.get(0).getQuestion_course_id());
		map.put("user_id", user.getUser_id());
		map.put("parent_id", 0);
		List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
		if(zhentiRecords.size()>0){
			map.clear();
			map.put("zhenti_id", zhentiRecords.get(0).getZhenti_id());
			ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
			model.addAttribute("zhentiYears", zhentiYears);
		}else{
			model.addAttribute("zhentiYears", null);
		}
		return "/question/select_topic/select_question";
	}
}
