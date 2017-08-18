package com.jingren.jing.question.comtroller.back.chapter_exercises;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.question.bean.chapter_exercises.ChapterExercises;
import com.jingren.jing.question.bean.chapter_exercises.ChapterQuerstionOption;
import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.question.bean.question_course.QuestionCourse;
import com.jingren.jing.question.service.chapter_exercises.ChapterExercisesService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuerstionOptionService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuestionService;
import com.jingren.jing.question.service.question_course.QuestionCourseService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.NumUtil;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;

/**
 * @Title: BackChapterExerciseController.java
 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
 * @Description: TODO 章节练习后台管理
 * @author 鲁晓飞 MR.Lu
 * @date 2017年1月10日 下午4:20:53
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_chapter")
public class BackChapterExerciseController {
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
	private static final String UP_FRONT_FILE = "images/chapter_option";

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 获取章节练习课程分类
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月10日 下午4:30:27
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
		return "/question/chapter_exericse/question_course_menu";
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 获取课程分类二级菜单
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月10日 下午4:38:20
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
			Integer questionnumber = chapterQuestionService.getChapterQuestionNumber(map);
			questionCourse.setQuestion_chapter_number(questionnumber);
		}
		model.addAttribute("questionCourses", questionCourses);
		return "/question/chapter_exericse/course_sub_menu";
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 跳转到章节练习列表页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月11日 上午11:00:09
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_chapter_question.jr")
	public String to_add_chapter_question(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourses", questionCourse);
		map.put("parent_id", 0);
		List<ChapterExercises> chapterExercises = ChapterExercisesService.getChapterExercisesList(map);
		for (ChapterExercises chapterExercises2 : chapterExercises) {
			map.clear();
			map.put("zhang_id", chapterExercises2.getChapter_exercises_id());
			Integer questionnumber = chapterQuestionService.getChapterQuestionNumber(map);
			chapterExercises2.setQuestion_number(questionnumber);
			chapterExercises2.setChapter_number_str(
					NumUtil.get_str_number(String.valueOf(chapterExercises2.getChapter_number())));// 转换序号
			map.clear();
			map.put("parent_id", chapterExercises2.getChapter_exercises_id());
			List<ChapterExercises> chapterExercises_jie = ChapterExercisesService.getChapterExercisesList(map);
			chapterExercises2.setChapterExercises_jie(chapterExercises_jie);
			for (ChapterExercises chapterExercises3 : chapterExercises_jie) {
				map.clear();
				map.put("jie_id", chapterExercises3.getChapter_exercises_id());
				Integer questionnumber2 = chapterQuestionService.getChapterQuestionNumber(map);
				chapterExercises3.setQuestion_number(questionnumber2);
				chapterExercises3.setChapter_number_str(
						NumUtil.get_str_number(String.valueOf(chapterExercises3.getChapter_number())));// 转换序号
				map.clear();
				map.put("parent_id", chapterExercises3.getChapter_exercises_id());
				List<ChapterExercises> chapterExercises_question = ChapterExercisesService.getChapterExercisesList(map);
				for (ChapterExercises chapterExercises4 : chapterExercises_question) {
					map.clear();
					map.put("question_id", chapterExercises4.getChapter_exercises_id());
					Integer questionnumber3 = chapterQuestionService.getChapterQuestionNumber(map);
					chapterExercises4.setQuestion_number(questionnumber3);
				}
				chapterExercises3.setChapterExercises_question(chapterExercises_question);
			}
		}
		map.clear();
		map.put("question_course_id", question_course_id);
		List<ChapterExercises> chapterExercisesquchong = ChapterExercisesService.getChapterExercisesQuchongList(map);
		if (chapterExercisesquchong.size() > 0) {
			model.addAttribute("version", chapterExercisesquchong.get(0).getTextbook_version());
		} else {
			model.addAttribute("version", "未知版本");
		}
		model.addAttribute("chapterExercises", chapterExercises);
		return "/question/chapter_exericse/zhangjielist";
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 添加章弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月11日 上午11:46:59
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_chapter_zhang.jr")
	public String to_add_chapter_zhang(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		model.addAttribute("question_course_id", question_course_id);
		return "/question/chapter_exericse/add_zhang";
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 添加小节弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月11日 下午4:40:08
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_chapter_jie.jr")
	public String to_add_chapter_jie(Model model,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		model.addAttribute("chapter_exercises_id", chapter_exercises_id);
		return "/question/chapter_exericse/add_jie";
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 添加课目弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月11日 下午5:09:55
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_chapter_questionmenu.jr")
	public String to_add_chapter_questionmenu(Model model,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		model.addAttribute("chapter_exercises_id", chapter_exercises_id);
		return "/question/chapter_exericse/add_question";
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 添加章节练习题目
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月11日 下午6:26:02
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_question_option.jr")
	public String to_add_question_option(Model model,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		model.addAttribute("chapter_exercises_id", chapter_exercises_id);
		return "/question/chapter_exericse/add_option";
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 去添加技能选题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月6日 上午9:54:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_jineng_question_option.jr")
	public String to_add_jineng_question_option(Model model,
			@RequestParam(value = "zhenti_question_id", required = false) Integer zhenti_question_id) {
		model.addAttribute("zhenti_question_id", zhenti_question_id);
		return "/question/chapter_exericse/zhenti/add_option";
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 添加章名称
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月11日 下午1:23:10
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_chapter_zhang.jr")
	public void save_chapter_zhang(HttpServletResponse response, ChapterExercises chapterExercises,
			@RequestParam(value = "str", required = false) String str) {
		// chapterExercises.setTextbook_version("2007版");
		chapterExercises.setChapter_level(1);// 章
		String[] arraystr = str.split(",");
		for (String string : arraystr) {
			Map<String, Object> map = new HashMap<>();
			map.put("parent_id", 0);
			map.put("question_course_id", chapterExercises.getQuestion_course_id());
			map.put("paixu", "降序");
			List<ChapterExercises> chapterExerciseslist = ChapterExercisesService.getChapterExercisesList(map);
			if (chapterExerciseslist.size() > 0) {
				chapterExercises.setTextbook_version(chapterExerciseslist.get(0).getTextbook_version());
				chapterExercises.setChapter_number(chapterExerciseslist.get(0).getChapter_number() + 1);
			} else {
				chapterExercises.setChapter_number(1);
			}
			chapterExercises.setChapter_name(string);
			ChapterExercisesService.saveChapterExercises(chapterExercises);
		}
		ResponseUtils.renderText(response, "1");
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 保存小节
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月11日 下午4:42:07
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_chapter_jie.jr")
	public void save_chapter_jie(HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("chapter_exercises_id", chapter_exercises_id);
		ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
		ChapterExercises chapterExercises2 = new ChapterExercises();
		chapterExercises2.setChapter_level(2);
		chapterExercises2.setQuestion_course_id(chapterExercises.getQuestion_course_id());
		chapterExercises2.setTextbook_version(chapterExercises.getTextbook_version());
		chapterExercises2.setParent_id(chapter_exercises_id);
		String[] arraystr = str.split(",");
		for (String string : arraystr) {
			map.clear();
			map.put("parent_id", chapter_exercises_id);
			map.put("question_course_id", chapterExercises.getQuestion_course_id());
			map.put("paixu", "降序");
			List<ChapterExercises> chapterExerciseslist = ChapterExercisesService.getChapterExercisesList(map);
			if (chapterExerciseslist.size() > 0) {
				chapterExercises2.setChapter_number(chapterExerciseslist.get(0).getChapter_number() + 1);
			} else {
				chapterExercises2.setChapter_number(1);
			}
			chapterExercises2.setChapter_name(string);
			ChapterExercisesService.saveChapterExercises(chapterExercises2);
		}
		ResponseUtils.renderText(response, "1");
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月11日 下午5:12:24
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_chapter_question_menu.jr")
	public void save_chapter_question_menu(HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("chapter_exercises_id", chapter_exercises_id);
		ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
		ChapterExercises chapterExercises2 = new ChapterExercises();
		chapterExercises2.setChapter_level(3);
		chapterExercises2.setQuestion_course_id(chapterExercises.getQuestion_course_id());
		chapterExercises2.setTextbook_version(chapterExercises.getTextbook_version());
		chapterExercises2.setParent_id(chapter_exercises_id);
		String[] arraystr = str.split(",");
		for (String string : arraystr) {
			chapterExercises2.setChapter_name(string);
			ChapterExercisesService.saveChapterExercises(chapterExercises2);
		}
		ResponseUtils.renderText(response, "1");
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 上传题目
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月12日 上午8:39:32
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_question_option.jr")
	public void save_question_option(HttpServletResponse response, HttpServletRequest request,
			ChapterQuestion chapterQuestion,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id,
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
		if(chapter_exercises_id!=null){
			map.put("chapter_exercises_id", chapter_exercises_id);
			ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
			chapterQuestion.setQuestion_course_id(chapterExercises.getQuestion_course_id());
			if (question_pic != null) {
				String path = UploadAddress.getUploadDate(question_pic, request, UP_FRONT_FILE);
				chapterQuestion.setQuestion_name_pic(path);
			}
			chapterQuestion.setJie_id(chapterExercises.getParent_id());
			chapterQuestion.setQuestion_id(chapter_exercises_id);
			map.clear();
			map.put("chapter_exercises_id", chapterExercises.getParent_id());// 小节ID
			ChapterExercises chapterExercises2 = ChapterExercisesService.getChapterExercises(map);// 课目
			chapterQuestion.setZhang_id(chapterExercises2.getParent_id());// 章id
		}
		if (chapterQuestionService.saveChapterQuestion(chapterQuestion)) {
			String[] arraystr = str.split(",");// 选项题目
			String[] optionarray = str_xuanxiang.split(",");// 选项序号
			for (int i = 0; i < arraystr.length; i++) {
				ChapterQuerstionOption chapterQuerstionOption = new ChapterQuerstionOption();
				chapterQuerstionOption.setChapter_question_id(chapterQuestion.getChapter_question_id());
				chapterQuerstionOption.setOption_content(arraystr[i].trim());
				chapterQuerstionOption.setOption_number(optionarray[i]);
				switch (i) {
				case 0:
					if (option_a != null) {
						String path = UploadAddress.getUploadDate(option_a, request, UP_FRONT_FILE);
						chapterQuerstionOption.setOptioan_pic(path);
					}
					break;
				case 1:
					if (option_b != null) {
						String path = UploadAddress.getUploadDate(option_b, request, UP_FRONT_FILE);
						chapterQuerstionOption.setOptioan_pic(path);
					}
					break;
				case 2:
					if (option_c != null) {
						String path = UploadAddress.getUploadDate(option_c, request, UP_FRONT_FILE);
						chapterQuerstionOption.setOptioan_pic(path);
					}
					break;
				case 3:
					if (option_d != null) {
						String path = UploadAddress.getUploadDate(option_d, request, UP_FRONT_FILE);
						chapterQuerstionOption.setOptioan_pic(path);
					}
					break;
				case 4:
					if (option_e != null) {
						String path = UploadAddress.getUploadDate(option_e, request, UP_FRONT_FILE);
						chapterQuerstionOption.setOptioan_pic(path);
					}
					break;
				case 5:
					if (option_f != null) {
						String path = UploadAddress.getUploadDate(option_f, request, UP_FRONT_FILE);
						chapterQuerstionOption.setOptioan_pic(path);
					}
					break;
				case 6:
					if (option_g != null) {
						String path = UploadAddress.getUploadDate(option_g, request, UP_FRONT_FILE);
						chapterQuerstionOption.setOptioan_pic(path);
					}
					break;
				case 7:
					if (option_h != null) {
						String path = UploadAddress.getUploadDate(option_h, request, UP_FRONT_FILE);
						chapterQuerstionOption.setOptioan_pic(path);
					}
					break;
				default:
					System.out.println("没有上传图片！");
					break;
				}
				chapterQuerstionOptionService.saveChapterQuerstionOption(chapterQuerstionOption);
			}
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 修改教材版本弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月12日 上午9:59:56
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_version.jr")
	public String to_update_version(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		model.addAttribute("question_course_id", question_course_id);
		return "/question/chapter_exericse/update_version";
	}
/**
* @Title: BackChapterExerciseController.java 
* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
* @Description: TODO 
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月12日 上午11:30:37 
* @version 网校+CRM系统 V1.0
 */
	@RequestMapping("/to_insert_zhang.jr")
	public String to_insert_zhang(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		model.addAttribute("question_course_id", question_course_id);
		return "/question/chapter_exericse/insert_zhang";
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 修改章节名称弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月12日 上午11:33:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_name.jr")
	public String to_update_name(Model model,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("chapter_exercises_id", chapter_exercises_id);
		ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
		model.addAttribute("chapterExercises", chapterExercises);
		return "/question/chapter_exericse/update_name";
	}
	
	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 修改教材版本
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月12日 上午10:01:56
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_version.jr")
	public void update_version(HttpServletResponse response, ChapterExercises chapterExercises) {
		if (ChapterExercisesService.updateChapterExercises(chapterExercises)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 删除章
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月12日 上午10:24:20
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_zhang.jr")
	public void delete_zhang(HttpServletResponse response,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", chapter_exercises_id);
		List<ChapterExercises> chapterExercises = ChapterExercisesService.getChapterExercisesList(map);
		if (chapterExercises.size() > 0) {
			for (ChapterExercises chapterExercises2 : chapterExercises) {
				ChapterExercisesService.deleteChapterExercises(chapterExercises2.getChapter_exercises_id());
				map.put("parent_id", chapterExercises2.getChapter_exercises_id());
				List<ChapterExercises> chapterExerciseslist = ChapterExercisesService.getChapterExercisesList(map);
				if (chapterExerciseslist.size() > 0) {
					for (ChapterExercises chapterExercises3 : chapterExerciseslist) {
						ChapterExercisesService.deleteChapterExercises(chapterExercises3.getChapter_exercises_id());
					}
				}

			}
		}
		if (ChapterExercisesService.deleteChapterExercises(chapter_exercises_id)
	|| chapterQuestionService.deleteChapterQuestionByzhang_id(chapter_exercises_id)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 删除节
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月12日 上午10:38:18
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_jie.jr")
	public void delete_jie(HttpServletResponse response,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", chapter_exercises_id);
		List<ChapterExercises> chapterExercises = ChapterExercisesService.getChapterExercisesList(map);
		if (chapterExercises.size() > 0) {
			for (ChapterExercises chapterExercises2 : chapterExercises) {
				ChapterExercisesService.deleteChapterExercises(chapterExercises2.getChapter_exercises_id());
			}
		}
		if (ChapterExercisesService.deleteChapterExercises(chapter_exercises_id)
				|| chapterQuestionService.deleteChapterQuestionByjie_id(chapter_exercises_id)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: BackChapterExerciseController.java
	 * @Package com.jingren.jing.question.comtroller.back.chapter_exercises
	 * @Description: TODO 删除课
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月12日 上午10:40:02
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_question.jr")
	public void delete_question(HttpServletResponse response,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		if (ChapterExercisesService.deleteChapterExercises(chapter_exercises_id)
				|| chapterQuestionService.deleteChapterQuestionByjie_id(chapter_exercises_id)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 插入章标题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月12日 上午11:11:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/insert_chapter_zhang.jr")
	public void insert_chapter_zhang(HttpServletResponse response, ChapterExercises chapterExercises) {
		// chapterExercises.setTextbook_version("2007版");
		chapterExercises.setChapter_level(1);// 章
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", 0);
		map.put("question_course_id", chapterExercises.getQuestion_course_id());
		map.put("paixu", "降序");
		List<ChapterExercises> chapterExerciseslist = ChapterExercisesService.getChapterExercisesList(map);
		if (chapterExerciseslist.size() > 0) {
			chapterExercises.setTextbook_version(chapterExerciseslist.get(0).getTextbook_version());
		}else{
			chapterExercises.setTextbook_version("2007版");
		}
		map.put("chapter_level", 1);
		map.put("chapter_number", chapterExercises.getChapter_number());
		ChapterExercises chapterExercises2=ChapterExercisesService.getChapterExercises(map);
		if(chapterExercises2!=null){
			ResponseUtils.renderText(response, "2");//章节序号有重复的
		}else{
			if(	ChapterExercisesService.saveChapterExercises(chapterExercises)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 查看所有题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月12日 下午1:20:35 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_option.jr")
	public String to_check_option(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("question_course_id", question_course_id);
		List<ChapterQuestion> chapterQuestions=chapterQuestionService.getChapterQuestionList(map);
		for (ChapterQuestion chapterQuestion : chapterQuestions) {
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions=chapterQuerstionOptionService.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
		}
		model.addAttribute("chapterQuestions", chapterQuestions);
		return "/question/chapter_exericse/check_option";
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 以章查看
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月12日 下午3:04:23 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_zhang_option.jr")
	public String to_check_zhang_option(Model model,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("zhang_id", chapter_exercises_id);
		List<ChapterQuestion> chapterQuestions=chapterQuestionService.getChapterQuestionList(map);
		for (ChapterQuestion chapterQuestion : chapterQuestions) {
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions=chapterQuerstionOptionService.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
		}
		model.addAttribute("chapterQuestions", chapterQuestions);
		return "/question/chapter_exericse/check_option";
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 以节查看
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月12日 下午3:05:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_jie_option.jr")
	public String to_check_jie_option(Model model,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("jie_id", chapter_exercises_id);
		List<ChapterQuestion> chapterQuestions=chapterQuestionService.getChapterQuestionList(map);
		for (ChapterQuestion chapterQuestion : chapterQuestions) {
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions=chapterQuerstionOptionService.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
		}
		model.addAttribute("chapterQuestions", chapterQuestions);
		return "/question/chapter_exericse/check_option";
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 以课目查看
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月12日 下午3:06:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_question_option.jr")
	public String to_check_question_option(Model model,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("question_id", chapter_exercises_id);
		List<ChapterQuestion> chapterQuestions=chapterQuestionService.getChapterQuestionList(map);
		for (ChapterQuestion chapterQuestion : chapterQuestions) {
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions=chapterQuerstionOptionService.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
		}
		model.addAttribute("chapterQuestions", chapterQuestions);
		return "/question/chapter_exericse/check_option";
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 删除题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月12日 下午2:24:23 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_question_option.jr")
	public void delete_question_option(HttpServletResponse response,
			@RequestParam(value = "chapter_question_id", required = false) Integer chapter_question_id) {
		if (chapterQuestionService.deleteChapterQuestion(chapter_question_id)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 题目解析-编辑器添加图片上传
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年2月13日 上午9:10:03 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/uploadpic.jr")
	public Map<String, Object> uploadpic(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "ajaxTaskFile", required = false) MultipartFile ajaxTaskFile){
		Map<String,Object> map=new HashMap<>();
		String path = UploadAddress.getUploadDate(ajaxTaskFile, request,
				UP_FRONT_FILE);
//		System.out.println(path);
		map.put("file_path", path);
		return map;
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 修改题目弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月22日 下午4:06:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_question.jr")
	public String to_update_question(Model model,
			@RequestParam(value="chapter_question_id",required=false)Integer chapter_question_id){
		Map<String, Object> map=new HashMap<>();
		map.put("chapter_question_id", chapter_question_id);
		ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
		map.clear();
		map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
		List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
				.getChapterQuerstionOptionList(map);
		chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
		model.addAttribute("chapterQuestion", chapterQuestion);
		return "/question/chapter_exericse/update_question";
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 添加案例简答题页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月7日 上午8:44:52 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_anli_jianda_question_option.jr")
	public String to_add_anli_jianda_question_option(Model model,
			@RequestParam(value="zhenti_question_id",required=false)Integer zhenti_question_id){
		model.addAttribute("zhenti_question_id", zhenti_question_id);
		return "/question/zhenti/add_anli__jianda_option";
	}
	/**
	* @Title: BackChapterExerciseController.java 
	* @Package com.jingren.jing.question.comtroller.back.chapter_exercises 
	* @Description: TODO 添加案例简答题题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月7日 上午8:51:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_anli_jianda.jr")
	public void save_anli_jianda(ChapterQuestion chapterQuestion,HttpServletResponse response){
		chapterQuestion.setAnswer("无");
		chapterQuestion.setChapter_question_time(new Date());
		if(chapterQuestionService.saveChapterQuestion(chapterQuestion)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "1");
		}
	}
}
