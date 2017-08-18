package com.jingren.jing.question.comtroller.back.zhentiyears;

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
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuerstionOption;
import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.question.bean.question_course.QuestionCourse;
import com.jingren.jing.question.bean.zhentioption.ZhentiQuestionOption;
import com.jingren.jing.question.bean.zhentiquestion.ZhentiQuestion;
import com.jingren.jing.question.bean.zhentitypeintro.ZhentiTypeIntroduce;
import com.jingren.jing.question.bean.zhentiyear.ZhentiYears;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuerstionOptionService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuestionService;
import com.jingren.jing.question.service.question_course.QuestionCourseService;
import com.jingren.jing.question.service.zhentioption.ZhentiQuestionOptionService;
import com.jingren.jing.question.service.zhentiquestion.ZhentiQuestionService;
import com.jingren.jing.question.service.zhentirecord.ZhentiRecordService;
import com.jingren.jing.question.service.zhentitypeintro.ZhentiTypeIntroduceService;
import com.jingren.jing.question.service.zhentiyear.ZhentiYearsService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
/**
* @Title: BackZhentiController.java 
* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
* @Description: TODO 历年真题后台管理
* @author 鲁晓飞 MR.Lu    
* @date 2017年2月10日 上午8:42:02 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_zhenti")
public class BackZhentiController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private QuestionCourseService questionCourseService;
	@Resource
	private ZhentiQuestionService zhentiQuestionService;
	@Resource
	private ZhentiTypeIntroduceService zhentiTypeIntroduceService;
	@Resource
	private ZhentiQuestionOptionService zhentiQuestionOptionService;
	@Resource
	private ZhentiYearsService zhentiYearsService;
	@Resource
	private ZhentiRecordService zhentiRecordService;
	@Resource
	private ChapterQuestionService chapterQuestionService;
	@Resource
	private  ChapterQuerstionOptionService chapterQuerstionOptionService;
	private static final String UP_FRONT_FILE = "images/question/zhenti";
	
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 历年真题课程页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月10日 上午8:49:29 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_question_list.jr")
	public String get_question_list(Model model) {
		Map<String, Object> map = new HashMap<>();
		List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseQuchongList(map);
		List<CourseMenu> courseMenus = new ArrayList<>();
		for (QuestionCourse questionCourse : questionCourses) {
			map.clear();
			map.put("course_id", questionCourse.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			Integer qusetion_course_number = questionCourseService.getQuestionCourseNumber(map);
			courseMenu.setQuestion_number(qusetion_course_number);
			courseMenus.add(courseMenu);
		}
		model.addAttribute("courseMenus", courseMenus);
		return "/question/zhenti/question_course_menu";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 获取二级分类课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月10日 上午8:50:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_menu_list.jr")
	public String get_sub_menu_list(Model model, @RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "id", required = false) Integer id) {
		model.addAttribute("id", id);
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		map.put("is_show", "显示");
		List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseList(map);
		for (QuestionCourse questionCourse : questionCourses) {
			map.clear();
			map.put("question_course_id", questionCourse.getQuestion_course_id());
			Integer questionnumber = zhentiYearsService.getZhentiYearsNumber(map);
			questionCourse.setZhenti_number(questionnumber);;
		}
		model.addAttribute("questionCourses", questionCourses);
		return "/question/zhenti/course_sub_menu";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 获取真题列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月10日 上午9:22:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhenti_years.jr")
	public String get_zhenti_years(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("question_course_id", question_course_id);
		Integer zhentiyear_number=zhentiYearsService.getZhentiYearsNumber(map);
		List<ZhentiYears> zhentiYears=zhentiYearsService.getZhentiYearsList(map);
		for (ZhentiYears zhentiYears2 : zhentiYears) {
			map.clear();
			map.put("question_course_id", zhentiYears2.getQuestion_course_id());
			QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
			zhentiYears2.setQuestionCourse(questionCourse);
			map.clear();
			map.put("course_id", questionCourse.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			zhentiYears2.setCourseMenu(courseMenu);
			map.clear();
			map.put("zhenti_id", zhentiYears2.getZhenti_id());
			List<ZhentiTypeIntroduce> zhentiTypeIntroduces=zhentiTypeIntroduceService.getZhentiTypeIntroduceList(map);
			zhentiYears2.setZhentiTypeIntroduce(zhentiTypeIntroduces);
		}
		Pagers<ZhentiYears> pagers = new Pagers<ZhentiYears>(zhentiyear_number,
				pageNumber, limit);
		pagers.setList(zhentiYears);
		model.addAttribute("zhentiYears", pagers);
		model.addAttribute("question_course_id", question_course_id);
		return "/question/zhenti/zhenti_year_main";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 添加真题弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月10日 上午10:23:02 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_zhenti_year.jr")
	public String to_add_zhenti_year(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id){
		Map<String, Object> map=new HashMap<>();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		return "/question/zhenti/add_zhentiyear";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 保存真题试卷信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月10日 上午10:43:22 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_zhenti_year.jr")
	public void save_zhenti_year(ZhentiYears zhentiYears,HttpServletResponse response){
		if(zhentiYears.getZhenti_new_price()>0){
			zhentiYears.setZhneti_shoufei_type("收费");
		}else{
			zhentiYears.setZhneti_shoufei_type("免费");
		}
		zhentiYears.setZhenti_time(new Date());
		if(zhentiYearsService.saveZhentiYears(zhentiYears)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 设置显示隐藏状态
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月10日 上午10:55:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_zhenti_year_show.jr")
	public void update_zhenti_year_show(ZhentiYears zhentiYears,HttpServletResponse response){
		if(zhentiYearsService.updateZhentiYears(zhentiYears)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 添加真题题目类型
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月10日 下午4:25:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_zhenti_type.jr")
	public String to_add_zhenti_type(Model model,
			@RequestParam(value="zhenti_id",required=false)Integer zhenti_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		map.clear();
		map.put("question_course_id", zhentiYears.getQuestion_course_id());
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("zhentiYears", zhentiYears);
		return "/question/zhenti/add_zhentiintroduce";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 添加真题题目题型
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月10日 下午4:29:10 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_zhenti_year_type.jr")
	public void save_zhenti_year_type(ZhentiTypeIntroduce zhentiTypeIntroduce,HttpServletResponse response){
		Map<String, Object> map=new HashMap<>();
		map.put("zhenti_id", zhentiTypeIntroduce.getZhenti_id());
		map.put("question_type", zhentiTypeIntroduce.getQuestion_type());
		ZhentiTypeIntroduce zhentiTypeIntroduce2=zhentiTypeIntroduceService.getZhentiTypeIntroduce(map);
		if(zhentiTypeIntroduce2!=null){
			ResponseUtils.renderText(response, "2");//该题型已经添加过
		}else{
			if(zhentiTypeIntroduceService.saveZhentiTypeIntroduce(zhentiTypeIntroduce)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 添加真题-单选题弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月13日 上午9:53:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_save_danxuanti_option.jr")
	public String to_save_danxuanti_option(Model model,
			@RequestParam(value="question_type",required=false)String question_type,
			@RequestParam(value="zhenti_id",required=false)Integer zhenti_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		map.clear();
		map.put("question_course_id", zhentiYears.getQuestion_course_id());
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("zhentiYears", zhentiYears);
		model.addAttribute("question_type", question_type);
		return "/question/zhenti/add_danxuan_option";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 添加多选题弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月13日 上午11:21:46 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_save_duoxuanti_option.jr")
	public String to_save_duoxuanti_option(Model model,
			@RequestParam(value="zhenti_id",required=false)Integer zhenti_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		map.clear();
		map.put("question_course_id", zhentiYears.getQuestion_course_id());
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("zhentiYears", zhentiYears);
		return "/question/zhenti/add_duoxuan_option";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 添加案例题弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月13日 下午12:52:46 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_save_anli_option.jr")
	public String to_save_anli_option(Model model,
			@RequestParam(value="question_type",required=false)String question_type,
			@RequestParam(value="zhenti_id",required=false)Integer zhenti_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		map.clear();
		map.put("question_course_id", zhentiYears.getQuestion_course_id());
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("zhentiYears", zhentiYears);
		model.addAttribute("question_type", question_type);
		return "/question/zhenti/add_anli_option";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 添加真题单选题/多选题题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月13日 上午10:51:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_zhenti_question_option.jr")
	public void save_zhenti_question_option(HttpServletResponse response, HttpServletRequest request,
			ZhentiQuestion zhentiQuestion,
			@RequestParam(value = "zhenti_id", required = false) Integer zhenti_id,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "str_xuanxiang", required = false) String str_xuanxiang,
			@RequestParam(value = "question_pic", required = false) MultipartFile question_pic,
			@RequestParam(value = "option_a", required = false) MultipartFile option_a,
			@RequestParam(value = "option_b", required = false) MultipartFile option_b,
			@RequestParam(value = "option_c", required = false) MultipartFile option_c,
			@RequestParam(value = "option_d", required = false) MultipartFile option_d,
			@RequestParam(value = "option_e", required = false) MultipartFile option_e,
			@RequestParam(value = "option_f", required = false) MultipartFile option_f,
			@RequestParam(value = "option_g", required = false) MultipartFile option_g,
			@RequestParam(value = "option_h", required = false) MultipartFile option_h) {
		Map<String, Object> map = new HashMap<>();
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears = zhentiYearsService.getZhentiYears(map);
		zhentiQuestion.setQuestion_course_id(zhentiYears.getQuestion_course_id());
		if (question_pic != null) {
			String path = UploadAddress.getUploadDate(question_pic, request, UP_FRONT_FILE);
			zhentiQuestion.setQuestion_name_pic(path);
		}
		zhentiQuestion.setQuestion_time(new Date());
		if(zhentiQuestion.getQuestion_type().equals("案例简答题")||zhentiQuestion.getQuestion_type().equals("简答题")){
			zhentiQuestion.setAnswer("无");
		}
		map.clear();
		if (zhentiQuestionService.saveZhentiQuestion(zhentiQuestion)) {
			if(StringUtils.isNotBlank(str)){
			String[] arraystr = str.split(",");// 选项题目
			String[] optionarray = str_xuanxiang.split(",");// 选项序号
			for (int i = 0; i < arraystr.length; i++) {
				ZhentiQuestionOption zhentiQuestionOption = new ZhentiQuestionOption();
				zhentiQuestionOption.setOption_time(new Date());
				zhentiQuestionOption.setZhenti_question_id(zhentiQuestion.getZhenti_question_id());
				zhentiQuestionOption.setOption_content(arraystr[i].trim());
				zhentiQuestionOption.setOption_number(optionarray[i]);
				switch (i) {
				case 0:
					if (option_a != null) {
						String path = UploadAddress.getUploadDate(option_a, request, UP_FRONT_FILE);
						zhentiQuestionOption.setOptioan_pic(path);
					}
					break;
				case 1:
					if (option_b != null) {
						String path = UploadAddress.getUploadDate(option_b, request, UP_FRONT_FILE);
						zhentiQuestionOption.setOptioan_pic(path);
					}
					break;
				case 2:
					if (option_c != null) {
						String path = UploadAddress.getUploadDate(option_c, request, UP_FRONT_FILE);
						zhentiQuestionOption.setOptioan_pic(path);
					}
					break;
				case 3:
					if (option_d != null) {
						String path = UploadAddress.getUploadDate(option_d, request, UP_FRONT_FILE);
						zhentiQuestionOption.setOptioan_pic(path);
					}
					break;
				case 4:
					if (option_e != null) {
						String path = UploadAddress.getUploadDate(option_e, request, UP_FRONT_FILE);
						zhentiQuestionOption.setOptioan_pic(path);
					}
					break;
				case 5:
					if (option_f != null) {
						String path = UploadAddress.getUploadDate(option_f, request, UP_FRONT_FILE);
						zhentiQuestionOption.setOptioan_pic(path);
					}
					break;
				case 6:
					if (option_g != null) {
						String path = UploadAddress.getUploadDate(option_g, request, UP_FRONT_FILE);
						zhentiQuestionOption.setOptioan_pic(path);
					}
					break;
				case 7:
					if (option_h != null) {
						String path = UploadAddress.getUploadDate(option_h, request, UP_FRONT_FILE);
						zhentiQuestionOption.setOptioan_pic(path);
					}
					break;
				default:
					System.out.println("没有上传图片！");
					break;
				}
				zhentiQuestionOptionService.saveZhentiQuestionOption(zhentiQuestionOption);
			}
			}
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 真题题目查看详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月13日 下午4:31:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_zhenti_years.jr")
	public String check_zhenti_years(Model model,
			@RequestParam(value="zhenti_id",required=false) Integer zhenti_id,
			@RequestParam(value="zhentitype",required=false) String zhentitype){
		Map<String, Object> map=new HashMap<>();
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		map.clear();
		map.put("question_course_id", zhentiYears.getQuestion_course_id());
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("zhentiYears", zhentiYears);
		map.clear();
		map.put("zhenti_id", zhenti_id);
		List<ZhentiTypeIntroduce> zhentiTypeIntroduces=zhentiTypeIntroduceService.getZhentiTypeIntroduceList(map);
		for (ZhentiTypeIntroduce zhentiTypeIntroduce : zhentiTypeIntroduces) {
			map.clear();
			map.put("zhenti_id", zhenti_id);
			map.put("question_type", zhentiTypeIntroduce.getQuestion_type());
			List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);//题目列表按照类型分类
			for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
				map.clear();
				map.put("zhenti_question_id", zhentiQuestion.getZhenti_question_id());
				List<ZhentiQuestionOption> zhentiQuestionOptions=zhentiQuestionOptionService.getZhentiQuestionOptionList(map);//题目选项
				zhentiQuestion.setZhentiQuestionOptions(zhentiQuestionOptions);
				if(zhentiQuestion.getQuestion_type().equals("技能选择题")||zhentiQuestion.getQuestion_type().equals("案例简答题")){
					map.clear();
					map.put("zhenti_question_id", zhentiQuestion.getZhenti_question_id());
					List<ChapterQuestion> chapterQuestions=chapterQuestionService.getChapterQuestionList(map);
					for (ChapterQuestion chapterQuestion : chapterQuestions) {
						map.clear();
						map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
						List<ChapterQuerstionOption> chapterQuerstionOptions=chapterQuerstionOptionService.getChapterQuerstionOptionList(map);
						chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
					}
					zhentiQuestion.setChapterQuestions(chapterQuestions);
				}
			}
			zhentiTypeIntroduce.setZhentiQuestions(zhentiQuestions);
		}
		model.addAttribute("zhentiTypeIntroduces", zhentiTypeIntroduces);
		return "/question/zhenti/check_zhenti";
		
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 修改题目的弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月14日 上午9:21:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_zhenti_question.jr")
	public String to_update_zhenti_question(Model model,
			@RequestParam(value="zhenti_question_id",required=false) Integer zhenti_question_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhenti_question_id", zhenti_question_id);
		ZhentiQuestion zhentiQuestion=zhentiQuestionService.getZhentiQuestion(map);
		if(zhentiQuestion.getQuestion_type().equals("案例题")){
			model.addAttribute("zhentiQuestion", zhentiQuestion);
			return "/question/zhenti/update_anli_question";
		}else{
			map.clear();
			map.put("zhenti_question_id", zhenti_question_id);
			List<ZhentiQuestionOption> zhentiQuestionOptions=zhentiQuestionOptionService.getZhentiQuestionOptionList(map);//题目选项
			zhentiQuestion.setZhentiQuestionOptions(zhentiQuestionOptions);
			model.addAttribute("zhentiQuestion", zhentiQuestion);
			return "/question/zhenti/update_xuanzeti_question";
		}
		
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 修改真题题目以及解析
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月14日 上午9:30:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_zhenti_question.jr")
	public void update_zhenti_question(HttpServletResponse response,HttpServletRequest request,
			ZhentiQuestion zhentiQuestion,
			@RequestParam(value = "file_upload", required = false) MultipartFile file_upload){
		if(file_upload!=null){
			String path = UploadAddress.getUploadDate(file_upload, request, UP_FRONT_FILE);
			zhentiQuestion.setQuestion_name_pic(path);
		}
		if(zhentiQuestionService.updateZhentiQuestion(zhentiQuestion)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 修改真题选项
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月14日 上午9:31:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_zhenti_question_option.jr")
	public void update_zhenti_question_option(HttpServletResponse response,
			ZhentiQuestionOption zhentiQuestionOption){
		if(zhentiQuestionOptionService.updateZhentiQuestionOption(zhentiQuestionOption)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 删除历年真题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月14日 上午11:21:38 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_zhenti_question.jr")
	public void delete_zhenti_question(HttpServletResponse response,
			@RequestParam(value="zhenti_question_id",required=false)Integer zhenti_question_id){
		if(zhentiQuestionService.deleteZhentiQuestion(zhenti_question_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 删除试卷
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月3日 上午11:28:09 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_zhenti.jr")
	public void delete_zhenti(HttpServletResponse response,
			@RequestParam(value="zhenti_id",required=false)Integer zhenti_id){
		if(zhentiYearsService.deleteZhentiYears(zhenti_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
