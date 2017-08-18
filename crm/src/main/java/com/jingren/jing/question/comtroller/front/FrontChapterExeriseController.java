package com.jingren.jing.question.comtroller.front;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.question.bean.chapter_exercises.ChapterExercises;
import com.jingren.jing.question.bean.chapter_exercises.ChapterQuerstionOption;
import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;
import com.jingren.jing.question.bean.question_collection.QuestionCollection;
import com.jingren.jing.question.bean.question_course.QuestionCourse;
import com.jingren.jing.question.service.chapter_exercises.ChapterExercisesService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuerstionOptionService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuestionService;
import com.jingren.jing.question.service.chapterrecord.ChapterRecordService;
import com.jingren.jing.question.service.question_collection.QuestionCollectionService;
import com.jingren.jing.question.service.question_course.QuestionCourseService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.NumUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
 * @Title: FrontChapterExeriseController.java
 * @Package com.jingren.jing.question.comtroller.front
 * @Description: TODO 章节练习-前台
 * @author 鲁晓飞 MR.Lu
 * @date 2017年1月12日 下午3:41:00
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("front_chapter")
public class FrontChapterExeriseController {
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

	/**
	 * @Title: FrontChapterExeriseController.java
	 * @Package com.jingren.jing.question.comtroller.front
	 * @Description: TODO 章节练习列表页
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月12日 下午3:49:39
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chapter_list.html")
	public String get_chapter_list(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
		map.put("is_show", "显示");
		List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseList(map);
		model.addAttribute("questionCourses", questionCourses);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("questionCourse", questionCourse);
		return "/question/chapterexerise/chapter_list";
	}

	/**
	 * @Title: FrontChapterExeriseController.java
	 * @Package com.jingren.jing.question.comtroller.front
	 * @Description: TODO 题目列表页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月12日 下午4:05:52
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chapter_option_list.html")
	public String get_chapter_option_list(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		User user =(User) session.getAttribute("user_session");
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		map.put("parent_id", 0);
		List<ChapterExercises> chapterExercises = ChapterExercisesService.getChapterExercisesList(map);
		for (ChapterExercises chapterExercises2 : chapterExercises) {
			map.clear();
			map.put("zhang_id", chapterExercises2.getChapter_exercises_id());
			map.put("user_id", user.getUser_id());
			Integer finish_question=chapterRecordService.getChapterRecordNumber(map);
			chapterExercises2.setYizuo_chapter(finish_question);//已经做过的练习题数量-章
			Integer questionnumber = chapterQuestionService.getChapterQuestionNumber(map);
			map.put("is_right", "错误");
			Integer cuowu_question=chapterRecordService.getChapterRecordNumber(map);
			chapterExercises2.setCuowu_chapter(cuowu_question);
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
				map.put("user_id", user.getUser_id());
				Integer finish_question2=chapterRecordService.getChapterRecordNumber(map);
				chapterExercises3.setYizuo_chapter(finish_question2);//已经做过的练习题数量-小节
				Integer questionnumber2 = chapterQuestionService.getChapterQuestionNumber(map);
				map.put("is_right", "错误");
				Integer cuowu_question2=chapterRecordService.getChapterRecordNumber(map);
				chapterExercises3.setCuowu_chapter(cuowu_question2);
				chapterExercises3.setQuestion_number(questionnumber2);
				chapterExercises3.setChapter_number_str(
						NumUtil.get_str_number(String.valueOf(chapterExercises3.getChapter_number())));// 转换序号
				map.clear();
				map.put("parent_id", chapterExercises3.getChapter_exercises_id());
				List<ChapterExercises> chapterExercises_question = ChapterExercisesService.getChapterExercisesList(map);
				for (ChapterExercises chapterExercises4 : chapterExercises_question) {//题目列表
					map.clear();
					map.put("question_id", chapterExercises4.getChapter_exercises_id());
					map.put("user_id", user.getUser_id());
					Integer finish_question3=chapterRecordService.getChapterRecordNumber(map);
					chapterExercises4.setYizuo_chapter(finish_question3);//已经做过的练习题数量-题目
					Integer questionnumber3 = chapterQuestionService.getChapterQuestionNumber(map);
					chapterExercises4.setQuestion_number(questionnumber3);
					map.put("is_right", "错误");
					Integer cuowu_question3=chapterRecordService.getChapterRecordNumber(map);
					chapterExercises4.setCuowu_chapter(cuowu_question3);
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
		return "/question/chapterexerise/chapter_sub_list";
	}

	/**
	 * @Title: FrontChapterExeriseController.java
	 * @Package com.jingren.jing.question.comtroller.front
	 * @Description: TODO选题页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月13日 上午9:52:35
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_xuanti_list.html")
	public String get_xuanti_list(Model model, @RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("chapter_exercises_id", chapter_exercises_id);
		ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
		chapterExercises
				.setChapter_number_str(NumUtil.get_str_number(String.valueOf(chapterExercises.getChapter_number())));// 转换序号
		model.addAttribute("chapterExercises", chapterExercises);
		model.addAttribute("question_course_name", name);
		model.addAttribute("type", type);
		return "/question/chapterexerise/chapter_xuanti";
	}

	/**
	 * @Title: FrontChapterExeriseController.java
	 * @Package com.jingren.jing.question.comtroller.front
	 * @Description: TODO 进入题目页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月13日 下午12:57:12
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_chapter_question.html")
	public String to_chapter_question(Model model,
			@RequestParam(value = "question_type", required = false) String question_type,
			@RequestParam(value = "question_number", required = false) Integer question_number,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		model.addAttribute("chapter_exercises_id", chapter_exercises_id);
		Map<String, Object> map = new HashMap<>();
		map.put("chapter_exercises_id", chapter_exercises_id);
		ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
		chapterExercises
				.setChapter_number_str(NumUtil.get_str_number(String.valueOf(chapterExercises.getChapter_number())));// 转换序号
		model.addAttribute("chapterExercises", chapterExercises);
		map.clear();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		// 查看题目
		map.clear();
		switch (chapterExercises.getChapter_level()) {
		case 1:
			map.put("zhang_id", chapter_exercises_id);
			break;
		case 2:
			map.put("jie_id", chapter_exercises_id);
			break;
		case 3:
			map.put("question_id", chapter_exercises_id);
			break;
		default:
			System.out.println("不存在");
			break;
		}
		List<ChapterQuestion> chapterQuestions = chapterQuestionService.getChapterQuestionList(map);
		List<ChapterQuestion> chapterQuestions2 = new ArrayList<>();
		if (question_number < chapterQuestions.size()) {//题数小于所查询的题目数量
			Random rand = new Random();
			Set<Integer> set = new TreeSet<>();
			for (int i = 0; i < question_number; i++) {
				int a = rand.nextInt(chapterQuestions.size());
				set.add(a);//不重复的随机数
			}
			model.addAttribute("question_number", set.size());
			for (Integer integer : set) {
				chapterQuestions2.add(chapterQuestions.get(integer));
			}
		} else {//题数大于等于所查询的题目数量
			
			Random rand = new Random();
			Set<Integer> set = new TreeSet<>();
			for (int i = 0; i < chapterQuestions.size(); i++) {
				int a = rand.nextInt(chapterQuestions.size());
				set.add(a);//不重复的随机数
			}
			model.addAttribute("question_number", set.size());
			for (Integer integer : set) {
				chapterQuestions2.add(chapterQuestions.get(integer));
			}
		}

		for (ChapterQuestion chapterQuestion : chapterQuestions2) {
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
					.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
		}
		model.addAttribute("chapterQuestions", chapterQuestions2);
		return "/question/chapterexerise/chapter_question_now";
	}
	
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 保存练习记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月3日 上午9:12:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_chapter_user.html")
	public void save_chapter_user(ChapterRecord reChapterRecord,HttpSession session,HttpServletResponse response,
			@RequestParam(value="str",required=false)String str,
			@RequestParam(value="str_zhang",required=false)String str_zhang,
			@RequestParam(value="str_jie",required=false)String str_jie,
			@RequestParam(value="str_question",required=false)String str_question,
			@RequestParam(value="str_duo",required=false)String str_duo){
		User user=(User) session.getAttribute("user_session");
		String order_nbumber=DesUtil.getUUID();
		reChapterRecord.setQuestion_order_number(order_nbumber);
		reChapterRecord.setUser_id(user.getUser_id());//用户ID
		reChapterRecord.setRecourd_time(new Date());
		String[] strarr=str.split(",");
		String[] str_duo_option=str_duo.split(",");//多选选项
		String[] str_zhang_arr=str_zhang.split(",");//章id
		String[] str_jie_arr=str_jie.split(",");//节ID
		String[] str_question_arr=str_question.split(",");//题目ID
		Map<String, Object> map=new HashMap<>();
		for (int i=0;i<strarr.length;i++) {
			reChapterRecord.setChapter_question_id(Integer.valueOf(strarr[i]));//不分章节的题目ID
			reChapterRecord.setZhang_id(Integer.valueOf(str_zhang_arr[i]));//章ID
			reChapterRecord.setJie_id(Integer.valueOf(str_jie_arr[i]));//节ID
			reChapterRecord.setQuestion_id(Integer.valueOf(str_question_arr[i]));//题目ID
			reChapterRecord.setUser_answer(str_duo_option[i]);//答案
			map.put("chapter_question_id", Integer.valueOf(strarr[i]));
			ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
			reChapterRecord.setQuestion_type(chapterQuestion.getQuestion_type());//保存题型
			if(chapterQuestion.getAnswer().equals(str_duo_option[i])){
				reChapterRecord.setIs_right("正确");
			}else{
				reChapterRecord.setIs_right("错误");
			}
			chapterRecordService.saveChapterRecord(reChapterRecord);//保存答题记录
		}
		ResponseUtils.renderText(response, order_nbumber);
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 练习记录首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月3日 下午1:12:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chapter_record_main.html")
	public String get_chapter_record_main(Model model,HttpSession session,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
		map.put("is_show", "显示");
		List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseList(map);
		model.addAttribute("questionCourses", questionCourses);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("questionCourse", questionCourse);
		User user=(User) session.getAttribute("user_session");
		map.clear();
		map.put("user_id", user.getUser_id());
		map.put("group_order", "group_order");
		List<ChapterRecord> chapterRecords2=chapterRecordService.getChapterRecordList(map);
		if(pageNumber!=null&&limit!=null){
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
		}else{
			map.put("pageNumber", 0);
			map.put("limit", 10);
		}
		map.put("chapter_exercises_id_lianxi", "lianxi");
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		for (ChapterRecord chapterRecord : chapterRecords) {
			if(chapterRecord.getChapter_exercises_id()!=null){
			map.clear();
			map.put("user_id", user.getUser_id());
			map.put("chapter_recourd_id", chapterRecord.getChapter_recourd_id());
			QuestionCollection questionCollection=questionCollectionService.getQuestionCollection(map);
			if(questionCollection!=null){
				chapterRecord.setIs_collection(1);
			}
			map.clear();
			if(chapterRecord.getChapter_exercises_id()!=null){
				map.put("chapter_exercises_id", chapterRecord.getChapter_exercises_id());
				ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
				chapterExercises.setChapter_number_str(
						NumUtil.get_str_number(String.valueOf(chapterExercises.getChapter_number())));// 转换序号
				chapterRecord.setChapterExercises(chapterExercises);
			}
			map.clear();
			map.put("question_order_number", chapterRecord.getQuestion_order_number());
			Integer lianxinumber=chapterRecordService.getChapterRecordNumber(map);
			chapterRecord.setLianxi_number(lianxinumber);//练习的数量
			map.put("is_right", "正确");
			Integer right_number=chapterRecordService.getChapterRecordNumber(map);
			chapterRecord.setLianxi_right_number(right_number);//练习做对的数量
			}
		}
		if(pageNumber!=null&&limit!=null){
			Pagers<ChapterRecord> pagers = new Pagers<ChapterRecord>(chapterRecords2.size(),
					pageNumber, limit);
			pagers.setList(chapterRecords);
			model.addAttribute("chapterRecords", pagers);
		}else{
			Pagers<ChapterRecord> pagers = new Pagers<ChapterRecord>(chapterRecords2.size(),
					0, 10);
			pagers.setList(chapterRecords);
			model.addAttribute("chapterRecords", pagers);
		}
		return "/question/chapterrecord/chapter_record_main";
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 练习解析记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月4日 上午8:33:06 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chapter_jiexi_jilu.html")
	public String get_chapter_jiexi_jilu(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id,
			@RequestParam(value = "question_order_number", required = false) String question_order_number) {
		User user=(User) session.getAttribute("user_session");
		model.addAttribute("question_order_number", question_order_number);
		Map<String, Object> map = new HashMap<>();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
		map.clear();
		map.put("course_id", questionCourse.getCourse_id());
		CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
		map.put("is_show", "显示");
		List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseList(map);
		model.addAttribute("questionCourses", questionCourses);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("questionCourse", questionCourse);
		map.clear();
		map.put("chapter_exercises_id", chapter_exercises_id);
		ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
		chapterExercises.setChapter_number_str(
				NumUtil.get_str_number(String.valueOf(chapterExercises.getChapter_number())));// 转换序号
		model.addAttribute("chapterExercises", chapterExercises);//章节题目名称
		map.clear();
		map.put("question_order_number", question_order_number);
		Integer total_number=chapterRecordService.getChapterRecordNumberNormal(map);//本次练习的题目数量
		model.addAttribute("total_number", total_number);//章节题目名称
		map.put("user_answer_no", "无");
		Integer finish_number=chapterRecordService.getChapterRecordNumberNormal(map);//本次练习的已做的题目数量
		model.addAttribute("finish_number", finish_number);
		map.clear();
		map.put("question_order_number", question_order_number);//题目列表
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		model.addAttribute("answer_time",CommentDate.secToTime(chapterRecords.get(0).getAnswer_length()));//用户答题时长
		for (ChapterRecord chapterRecord : chapterRecords) {
			map.clear();
			map.put("user_id", user.getUser_id());
			map.put("chapter_recourd_id", chapterRecord.getChapter_recourd_id());
			QuestionCollection questionCollection=questionCollectionService.getQuestionCollection(map);
			if(questionCollection!=null){
				chapterRecord.setIs_collection(1);
			}
			map.clear();
			map.put("chapter_question_id", chapterRecord.getChapter_question_id());
			ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
			map.put("user_id", user.getUser_id());
			map.put("user_answer_no", "无");
			Integer useranswernumber=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterRecord.setUser_answer_number(useranswernumber);
			map.put("is_right", "正确");
			Integer useranswerrightnumber=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterRecord.setUser_answer_right_number(useranswerrightnumber);
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
					.getChapterQuerstionOptionList(map);
			if(chapterQuestion.getQuestion_type().equals("多选题")){//如果是多选题则拆分答案
				String[] user_answer_arr=chapterRecord.getUser_answer().replace(".", ",").split(",");
				List<String> user_answer_list=new ArrayList<>();
				user_answer_list=Arrays.asList(user_answer_arr);//String[]转成list
//				chapterRecord.setUser_answer_list(user_answer_list);
				String[] answer_arr=chapterQuestion.getAnswer().replace(".", ",").split(",");
				List<String> answer_list=new ArrayList<>();
				answer_list=Arrays.asList(answer_arr);//String[]转成list
//				chapterQuestion.setAnswer_list(answer_list);
				if(chapterRecord.getIs_right().equals("正确")){//正确状态的判断
					for (ChapterQuerstionOption chapterQuerstionOption : chapterQuerstionOptions) {
						for (String string2 : user_answer_list) {
							if(chapterQuerstionOption.getOption_number().equals(string2)){
								chapterQuerstionOption.setDuoxuan_type_jiexi(1);//正确
								break;
							}else{
								chapterQuerstionOption.setDuoxuan_type_jiexi(0);//错误
								continue;
							}
						}
					}
				}else{//错误状态下判断
					if(chapterRecord.getUser_answer().equals("无")){//没有作答的状态下判断
						for (ChapterQuerstionOption chapterQuerstionOption : chapterQuerstionOptions) {
							for (String string2 : answer_list) {
								if(chapterQuerstionOption.getOption_number().equals(string2)){
									chapterQuerstionOption.setDuoxuan_type_jiexi(1);//正确
									break;
								}else{
									chapterQuerstionOption.setDuoxuan_type_jiexi(0);//错误
									continue;
								}
							}
						}
					}else{//已作答的状态下
						for (ChapterQuerstionOption chapterQuerstionOption : chapterQuerstionOptions) {
							for (String string2 : answer_list) {
								if(chapterQuerstionOption.getOption_number().equals(string2)){
									chapterQuerstionOption.setDuoxuan_type_jiexi(1);//正确
									break;
								}
							}
							for (String string2 : user_answer_list) {
								if(chapterQuerstionOption.getDuoxuan_type_jiexi()!=null){
									if(chapterQuerstionOption.getOption_number().equals(string2)&&chapterQuerstionOption.getDuoxuan_type_jiexi()==1){
										chapterQuerstionOption.setDuoxuan_type_jiexi(2);//本选项作对了
										break;
									}
								}else if(chapterQuerstionOption.getOption_number().equals(string2)){
									chapterQuerstionOption.setDuoxuan_type_jiexi(0);//错误
									continue;
								}
							}
						}
					}
				}
			
			}
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
			chapterRecord.setChapterQuestion(chapterQuestion);
		}
		model.addAttribute("chapterRecords", chapterRecords);
		return "/question/chapterrecord/chapter_jiexi";
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 章节练习-再做一次
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月4日 下午2:33:05 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_reset_chapter_question.html")
	public String to_reset_chapter_question(Model model,HttpSession session,
			@RequestParam(value = "question_order_number", required = false) String question_order_number,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "chapter_exercises_id", required = false) Integer chapter_exercises_id) {
		Map<String, Object> map = new HashMap<>();
		User user=(User) session.getAttribute("user_session");
		map.put("question_order_number", question_order_number);
		map.put("re_user_id", user.getUser_id());
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);//获取习题记录
		
		map.put("chapter_exercises_id", chapter_exercises_id);
		ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
		chapterExercises
				.setChapter_number_str(NumUtil.get_str_number(String.valueOf(chapterExercises.getChapter_number())));// 转换序号
		model.addAttribute("chapterExercises", chapterExercises);
		map.clear();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
		model.addAttribute("questionCourse", questionCourse);
		
		model.addAttribute("question_number", chapterRecords.size());//题目数量
		for (ChapterRecord chapterRecord : chapterRecords) {
			map.clear();
			map.put("user_id", user.getUser_id());
			map.put("chapter_recourd_id", chapterRecord.getChapter_recourd_id());
			QuestionCollection questionCollection=questionCollectionService.getQuestionCollection(map);
			if(questionCollection!=null){
				chapterRecord.setIs_collection(1);
			}
			map.clear();
			map.put("chapter_question_id", chapterRecord.getChapter_question_id());
			ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
					.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
			chapterRecord.setChapterQuestion(chapterQuestion);
		}
		model.addAttribute("chapterRecords", chapterRecords);
		return "/question/chapterrecord/chapter_question_reset";
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 章节练习分页列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月4日 下午4:27:53 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chapter_record_list.html")
	public String get_chapter_record_list(Model model,HttpSession session,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		User user=(User) session.getAttribute("user_session");
		map.put("user_id", user.getUser_id());
		map.put("group_order", "group_order");
		List<ChapterRecord> chapterRecords2=chapterRecordService.getChapterRecordList(map);
		if(pageNumber!=null&&limit!=null){
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
		}else{
			map.put("pageNumber", 0);
			map.put("limit", 10);
		}
		map.put("chapter_exercises_id_lianxi", "lianxi");
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		for (ChapterRecord chapterRecord : chapterRecords) {
			if(chapterRecord.getChapter_exercises_id()!=null){
			map.clear();
			map.put("user_id", user.getUser_id());
			map.put("chapter_recourd_id", chapterRecord.getChapter_recourd_id());
			QuestionCollection questionCollection=questionCollectionService.getQuestionCollection(map);
			if(questionCollection!=null){
				chapterRecord.setIs_collection(1);
			}
			map.clear();
			map.put("chapter_exercises_id", chapterRecord.getChapter_exercises_id());
			
				ChapterExercises chapterExercises = ChapterExercisesService.getChapterExercises(map);
				chapterExercises.setChapter_number_str(
						NumUtil.get_str_number(String.valueOf(chapterExercises.getChapter_number())));// 转换序号
				chapterRecord.setChapterExercises(chapterExercises);
			map.clear();
			map.put("question_order_number", chapterRecord.getQuestion_order_number());
			Integer lianxinumber=chapterRecordService.getChapterRecordNumber(map);
			chapterRecord.setLianxi_number(lianxinumber);//练习的数量
			map.put("is_right", "正确");
			Integer right_number=chapterRecordService.getChapterRecordNumber(map);
			chapterRecord.setLianxi_right_number(right_number);//练习做对的数量
			}
		}
		if(pageNumber!=null&&limit!=null){
			Pagers<ChapterRecord> pagers = new Pagers<ChapterRecord>(chapterRecords2.size(),
					pageNumber, limit);
			pagers.setList(chapterRecords);
			model.addAttribute("chapterRecords", pagers);
		}else{
			Pagers<ChapterRecord> pagers = new Pagers<ChapterRecord>(chapterRecords2.size(),
					0, 10);
			pagers.setList(chapterRecords);
			model.addAttribute("chapterRecords", pagers);
		}
		return "/question/chapterrecord/chapter_record_list";
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 错题记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 上午8:40:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_cuoti_record.html")
	public String get_cuoti_record(HttpSession session,Model model,
			@RequestParam(value="question_course_id",required=false)Integer question_course_id){
		Map<String, Object> map = new HashMap<>();
		User user=(User) session.getAttribute("user_session");
		map.put("user_id", user.getUser_id());
		map.put("is_right", "错误");
		Integer danxuan_cuowu=0;
		Integer danxuan_weizuo=0;
		Integer duoxuan_cuowu=0;
		Integer duoxuan_weizuo=0;
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		map.put("group_exercise", "group_exercise");
		List<ChapterRecord> chapterRecords_zhangjie=chapterRecordService.getChapterRecordList(map);//章节形式的错题记录
		for (ChapterRecord chapterRecord : chapterRecords) {
			if(chapterRecord.getQuestion_type().equals("单选题")){
				if(chapterRecord.getUser_answer().equals("无")){
					danxuan_weizuo++;//单选题未做的数量
				}
				danxuan_cuowu++;//单选题错误数量
			}else{
				if(chapterRecord.getUser_answer().equals("无")){
					duoxuan_weizuo++;//多选题选题未做的数量
				}
				duoxuan_cuowu++;//多选题错误数量
			}
 		}
		map.clear();
		map.put("question_course_id", question_course_id);
		QuestionCourse questionCourse=questionCourseService.getQuestionCourse(map);
		List<ChapterExercises> chapterExercises=ChapterExercisesService.getChapterExercisesQuchongList(map);
		model.addAttribute("version", chapterExercises.get(0).getTextbook_version());
		model.addAttribute("questionCourse", questionCourse);//题目所属课程
		model.addAttribute("danxuan_cuowu", danxuan_cuowu);
		model.addAttribute("danxuan_yizuo", danxuan_cuowu-danxuan_weizuo);
		model.addAttribute("duoxuan_cuowu", duoxuan_cuowu);
		model.addAttribute("duoxuan_yizuo", duoxuan_cuowu-duoxuan_weizuo);
		//以章节的形式转换
		List<ChapterExercises> exercises=new ArrayList<>();
		for (ChapterRecord chapterRecord : chapterRecords_zhangjie) {
			map.clear();
			map.put("chapter_exercises_id", chapterRecord.getChapter_exercises_id());
			ChapterExercises chapterExercises2=ChapterExercisesService.getChapterExercises(map);
			exercises.add(chapterExercises2);
		}
		for (ChapterExercises chapterExercises2 : exercises) {
			map.clear();
			map.put("zhang_id", chapterExercises2.getChapter_exercises_id());
			map.put("user_id", user.getUser_id());
			Integer finish_question=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterExercises2.setYizuo_chapter(finish_question);//已经做过的练习题数量-章
//			Integer questionnumber = chapterQuestionService.getChapterQuestionNumber(map);
			map.put("is_right", "错误");
			Integer cuowu_question=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterExercises2.setCuowu_chapter(cuowu_question);
//			chapterExercises2.setQuestion_number(questionnumber);
			chapterExercises2.setChapter_number_str(
					NumUtil.get_str_number(String.valueOf(chapterExercises2.getChapter_number())));// 转换序号
			map.clear();
			map.put("parent_id", chapterExercises2.getChapter_exercises_id());
			List<ChapterExercises> chapterExercises_jie = ChapterExercisesService.getChapterExercisesList(map);
			chapterExercises2.setChapterExercises_jie(chapterExercises_jie);
			for (ChapterExercises chapterExercises3 : chapterExercises_jie) {
				map.clear();
				map.put("jie_id", chapterExercises3.getChapter_exercises_id());
				map.put("user_id", user.getUser_id());
				Integer finish_question2=chapterRecordService.getChapterRecordNumberNormal(map);
				chapterExercises3.setYizuo_chapter(finish_question2);//已经做过的练习题数量-小节
//				Integer questionnumber2 = chapterQuestionService.getChapterQuestionNumber(map);
				map.put("is_right", "错误");
				Integer cuowu_question2=chapterRecordService.getChapterRecordNumberNormal(map);
				chapterExercises3.setCuowu_chapter(cuowu_question2);
//				chapterExercises3.setQuestion_number(questionnumber2);
				chapterExercises3.setChapter_number_str(
						NumUtil.get_str_number(String.valueOf(chapterExercises3.getChapter_number())));// 转换序号
				map.clear();
				map.put("parent_id", chapterExercises3.getChapter_exercises_id());
				List<ChapterExercises> chapterExercises_question = ChapterExercisesService.getChapterExercisesList(map);
				for (ChapterExercises chapterExercises4 : chapterExercises_question) {//题目列表
					map.clear();
					map.put("question_id", chapterExercises4.getChapter_exercises_id());
					map.put("user_id", user.getUser_id());
					Integer finish_question3=chapterRecordService.getChapterRecordNumberNormal(map);
					chapterExercises4.setYizuo_chapter(finish_question3);//已经做过的练习题数量-题目
//					Integer questionnumber3 = chapterQuestionService.getChapterQuestionNumber(map);
//					chapterExercises4.setQuestion_number(questionnumber3);
					map.put("is_right", "错误");
					Integer cuowu_question3=chapterRecordService.getChapterRecordNumberNormal(map);
					chapterExercises4.setCuowu_chapter(cuowu_question3);
				}
				chapterExercises3.setChapterExercises_question(chapterExercises_question);
			}
		}
		model.addAttribute("exercises", exercises);
		return "/question/chapterrecord/cuoti_record";
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 查看错题记录详情-单选题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 上午10:03:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chapter_record_danxuan_detail.html")
	public String get_chapter_record_danxuan_detail(Model model,HttpSession session,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		if(type==null){
			map.put("question_course_id", question_course_id);
			QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
			map.clear();
			map.put("course_id", questionCourse.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			map.put("is_show", "显示");
			List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseList(map);
			model.addAttribute("questionCourses", questionCourses);
			model.addAttribute("courseMenu", courseMenu);
			model.addAttribute("questionCourse", questionCourse);
		}
		if(pageNumber==null){
			pageNumber=1;
		}
		if(limit==null){
			limit=10;
		}
		User user=(User) session.getAttribute("user_session");
		map.clear();
		map.put("cuoti_user_id", user.getUser_id());
		map.put("question_type", "单选题");
		map.put("is_right", "错误");
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		for (ChapterRecord chapterRecord : chapterRecords) {
			map.clear();
			map.put("user_id", user.getUser_id());
			map.put("chapter_recourd_id", chapterRecord.getChapter_recourd_id());
			QuestionCollection questionCollection=questionCollectionService.getQuestionCollection(map);
			if(questionCollection!=null){
				chapterRecord.setIs_collection(1);
			}
			map.clear();
			map.put("chapter_question_id", chapterRecord.getChapter_question_id());
			ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
					.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
			chapterRecord.setChapterQuestion(chapterQuestion);
			//最对的数量以及做过的次数
			map.put("user_id", user.getUser_id());
			map.put("user_answer_no", "无");
			Integer useranswernumber=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterRecord.setUser_answer_number(useranswernumber);
			map.put("is_right", "正确");
			Integer useranswerrightnumber=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterRecord.setUser_answer_right_number(useranswerrightnumber);
		}
		Integer chapter_number=chapterRecordService.getChapterRecordNumberNormal(map);
		Pagers<ChapterRecord> pagers = new Pagers<ChapterRecord>(chapter_number,
				pageNumber, limit);
		pagers.setList(chapterRecords);
		model.addAttribute("chapterRecords", pagers);
		if(type!=null){
			return "/question/chapterrecord/cuoti_xiangqing/chapter_cuoti_danxuan_detail_list";
		}else{
			return "/question/chapterrecord/cuoti_xiangqing/chapter_cuoti_danxuan_detail";
		}
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 查看错题记录详情——多选题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 下午1:26:24 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chapter_record_duoxuan_detail.html")
	public String get_chapter_record_duoxuan_detail(Model model,HttpSession session,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		if(type==null){
			map.put("question_course_id", question_course_id);
			QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
			map.clear();
			map.put("course_id", questionCourse.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			map.put("is_show", "显示");
			List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseList(map);
			model.addAttribute("questionCourses", questionCourses);
			model.addAttribute("courseMenu", courseMenu);
			model.addAttribute("questionCourse", questionCourse);
		}
		if(pageNumber==null){
			pageNumber=1;
		}
		if(limit==null){
			limit=10;
		}
		User user=(User) session.getAttribute("user_session");
		map.clear();
		map.put("cuoti_user_id", user.getUser_id());
		map.put("question_type", "多选题");
		map.put("is_right", "错误");
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		for (ChapterRecord chapterRecord : chapterRecords) {
			map.clear();
			map.put("user_id", user.getUser_id());
			map.put("chapter_recourd_id", chapterRecord.getChapter_recourd_id());
			QuestionCollection questionCollection=questionCollectionService.getQuestionCollection(map);
			if(questionCollection!=null){
				chapterRecord.setIs_collection(1);
			}
			map.clear();
			map.put("chapter_question_id", chapterRecord.getChapter_question_id());
			ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
					.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
			chapterRecord.setChapterQuestion(chapterQuestion);
			//最对的数量以及做过的次数
			map.put("user_id", user.getUser_id());
			map.put("user_answer_no", "无");
			Integer useranswernumber=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterRecord.setUser_answer_number(useranswernumber);
			map.put("is_right", "正确");
			Integer useranswerrightnumber=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterRecord.setUser_answer_right_number(useranswerrightnumber);
			String[] answer_arr=chapterQuestion.getAnswer().replace(".", ",").split(",");
			List<String> answer_list=new ArrayList<>();
			answer_list=Arrays.asList(answer_arr);//String[]转成list
			for (ChapterQuerstionOption chapterQuerstionOption : chapterQuerstionOptions) {
				for (String string2 : answer_list) {
					if(chapterQuerstionOption.getOption_number().equals(string2)){
						chapterQuerstionOption.setDuoxuan_type_jiexi(1);//正确
						break;
					}else{
						chapterQuerstionOption.setDuoxuan_type_jiexi(0);//错误
						continue;
					}
				}
			}
		}
		Integer chapter_number=chapterRecordService.getChapterRecordNumberNormal(map);
		Pagers<ChapterRecord> pagers = new Pagers<ChapterRecord>(chapter_number,
				pageNumber, limit);
		pagers.setList(chapterRecords);
		model.addAttribute("chapterRecords", pagers);
		if(type!=null){
			return "/question/chapterrecord/cuoti_xiangqing/chapter_cuoti_duoxuan_detail_list";
		}else{
			return "/question/chapterrecord/cuoti_xiangqing/chapter_cuoti_duoxuan_detail";
		}
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 用户题目收藏
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月6日 下午4:47:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_user_collection_question.html")
	public String get_user_collection_question(Model model,HttpSession session,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		if(type==null){
			map.put("question_course_id", question_course_id);
			QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
			map.clear();
			map.put("course_id", questionCourse.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			map.put("is_show", "显示");
			List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseList(map);
			model.addAttribute("questionCourses", questionCourses);
			model.addAttribute("courseMenu", courseMenu);
			model.addAttribute("questionCourse", questionCourse);
		}
		if(pageNumber==null){
			pageNumber=1;
		}
		if(limit==null){
			limit=10;
		}
		User user=(User) session.getAttribute("user_session");
		map.clear();
		map.put("user_id", user.getUser_id());
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<QuestionCollection> questionCollections=questionCollectionService.getQuestionCollectionList(map);
		if(questionCollections.size()>0){
			for (QuestionCollection questionCollection : questionCollections) {
				map.clear();
				map.put("chapter_recourd_id", questionCollection.getChapter_recourd_id());
				ChapterRecord chapterRecord=chapterRecordService.getChapterRecord(map);
				questionCollection.setChapterRecord(chapterRecord);//答题记录
				map.clear();
				map.put("chapter_question_id", questionCollection.getQuestion_id());
				ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
				map.clear();
				map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
				List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
						.getChapterQuerstionOptionList(map);
				chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
				questionCollection.setChapterQuestion(chapterQuestion);
				//最对的数量以及做过的次数
				map.put("user_id", user.getUser_id());
				map.put("user_answer_no", "无");
				Integer useranswernumber=chapterRecordService.getChapterRecordNumberNormal(map);
				questionCollection.setUser_answer_number(useranswernumber);
				map.put("is_right", "正确");
				Integer useranswerrightnumber=chapterRecordService.getChapterRecordNumberNormal(map);
				questionCollection.setUser_answer_right_number(useranswerrightnumber);
				if(chapterQuestion.getQuestion_type().equals("多选题")){//如果是多选题的话判断答案
					String[] answer_arr=chapterQuestion.getAnswer().replace(".", ",").split(",");
					List<String> answer_list=new ArrayList<>();
					answer_list=Arrays.asList(answer_arr);//String[]转成list
					for (ChapterQuerstionOption chapterQuerstionOption : chapterQuerstionOptions) {
						for (String string2 : answer_list) {
							if(chapterQuerstionOption.getOption_number().equals(string2)){
								chapterQuerstionOption.setDuoxuan_type_jiexi(1);//正确
								break;
							}else{
								chapterQuerstionOption.setDuoxuan_type_jiexi(0);//错误
								continue;
							}
						}
					}
				}
			}
		}
		Integer questionCollections_number=questionCollectionService.getQuestionCollectionNumber(map);
		Pagers<QuestionCollection> pagers = new Pagers<QuestionCollection>(questionCollections_number,
				pageNumber, limit);
		pagers.setList(questionCollections);
		model.addAttribute("questionCollections", pagers);
		if(type==null){
			return "/question/user_collection/user_collection";
		}else{
			return "/question/user_collection/user_collection_list";
		}
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 添加我的收藏题目
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月7日 上午9:28:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/collection_question.html")
	public void collection_question(HttpServletResponse response,HttpSession session,
			@RequestParam(value="type",required=false)String type,
			@RequestParam(value="chapter_recourd_id",required=false)Integer chapter_recourd_id){
		Map<String, Object> map=new HashMap<>();
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			QuestionCollection questionCollection=new QuestionCollection();
			map.put("chapter_recourd_id", chapter_recourd_id);
			ChapterRecord chapterRecord=chapterRecordService.getChapterRecord(map);
			questionCollection.setUser_id(user.getUser_id());
			questionCollection.setQuestion_type(type);//收藏的题目类型
			questionCollection.setQuestion_course_id(chapterRecord.getQuestion_course_id());
			questionCollection.setQuestion_id(chapterRecord.getChapter_question_id());
			questionCollection.setUser_answer(chapterRecord.getUser_answer());
			questionCollection.setCollection_time(new Date());
			questionCollection.setChapter_recourd_id(chapter_recourd_id);
			if(questionCollectionService.saveQuestionCollection(questionCollection)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 取消题目收藏
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年2月7日 上午10:28:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delte_collection_question.html")
	public void delte_collection_question(HttpServletResponse response,HttpSession session,
			@RequestParam(value="chapter_recourd_id",required=false)Integer chapter_recourd_id){
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			Map<String, Object> map=new HashMap<>();
			map.put("chapter_recourd_id", chapter_recourd_id);
			map.put("user_id", user.getUser_id());
			QuestionCollection questionCollection=questionCollectionService.getQuestionCollection(map);
			if(questionCollection!=null){
				if(questionCollectionService.deleteQuestionCollection(questionCollection.getQuestion_collection_id())){
					ResponseUtils.renderText(response, "1");
				}else{
					ResponseUtils.renderText(response, "0");
				}
			}
		}
	}
	/**
	* @Title: FrontChapterExeriseController.java 
	* @Package com.jingren.jing.question.comtroller.front 
	* @Description: TODO 获取章节错题记录详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月7日 下午2:14:09 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhangjie_cuoti.html")
	public String get_zhangjie_cuoti(Model model,HttpSession session,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "zhang_id",defaultValue="0", required = false) Integer zhang_id,
			@RequestParam(value = "jie_id",defaultValue="0", required = false) Integer jie_id,
			@RequestParam(value = "question_id",defaultValue="0", required = false) Integer question_id,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) {
		Map<String, Object> map = new HashMap<>();
		if(type==null){
			map.put("question_course_id", question_course_id);
			QuestionCourse questionCourse = questionCourseService.getQuestionCourse(map);
			map.clear();
			map.put("course_id", questionCourse.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			map.put("is_show", "显示");
			List<QuestionCourse> questionCourses = questionCourseService.getQuestionCourseList(map);
			model.addAttribute("questionCourses", questionCourses);
			model.addAttribute("courseMenu", courseMenu);
			model.addAttribute("questionCourse", questionCourse);
		}else{
			model.addAttribute("question_course_id", question_course_id);
		}
		if(pageNumber==null){
			pageNumber=1;
		}
		if(limit==null){
			limit=10;
		}
		User user=(User) session.getAttribute("user_session");
		map.clear();
		if(zhang_id!=null&&zhang_id!=0){
			map.put("zhang_id", zhang_id);
		}
		model.addAttribute("zhang_id", zhang_id);
		if(jie_id!=null&&jie_id!=0){
			map.put("jie_id", jie_id);
		}
		model.addAttribute("jie_id", jie_id);
		if(question_id!=null&&question_id!=0){
			map.put("question_id", question_id);
		}
		model.addAttribute("question_id", question_id);
		map.put("zhangjie_user_id", user.getUser_id());
		map.put("is_right","错误");
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
		for (ChapterRecord chapterRecord : chapterRecords) {
			map.clear();
			map.put("user_id", user.getUser_id());
			map.put("chapter_recourd_id", chapterRecord.getChapter_recourd_id());
			QuestionCollection questionCollection=questionCollectionService.getQuestionCollection(map);
			if(questionCollection!=null){
				chapterRecord.setIs_collection(1);
			}
			map.clear();
			map.put("chapter_question_id", chapterRecord.getChapter_question_id());
			ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
			map.clear();
			map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
			List<ChapterQuerstionOption> chapterQuerstionOptions = chapterQuerstionOptionService
					.getChapterQuerstionOptionList(map);
			chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
			chapterRecord.setChapterQuestion(chapterQuestion);
			//最对的数量以及做过的次数
			map.put("user_id", user.getUser_id());
			map.put("user_answer_no", "无");
			Integer useranswernumber=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterRecord.setUser_answer_number(useranswernumber);
			map.put("is_right", "正确");
			Integer useranswerrightnumber=chapterRecordService.getChapterRecordNumberNormal(map);
			chapterRecord.setUser_answer_right_number(useranswerrightnumber);
			if(chapterRecord.getQuestion_type().equals("多选题")){//如果是多选题的话判断答案
				String[] answer_arr=chapterQuestion.getAnswer().replace(".", ",").split(",");
				List<String> answer_list=new ArrayList<>();
				answer_list=Arrays.asList(answer_arr);//String[]转成list
				for (ChapterQuerstionOption chapterQuerstionOption : chapterQuerstionOptions) {
					for (String string2 : answer_list) {
						if(chapterQuerstionOption.getOption_number().equals(string2)){
							chapterQuerstionOption.setDuoxuan_type_jiexi(1);//正确
							break;
						}else{
							chapterQuerstionOption.setDuoxuan_type_jiexi(0);//错误
							continue;
						}
					}
				}
			}
		}
		Integer chapter_number=chapterRecordService.getChapterRecordNumberNormal(map);
		Pagers<ChapterRecord> pagers = new Pagers<ChapterRecord>(chapter_number,
				pageNumber, limit);
		pagers.setList(chapterRecords);
		model.addAttribute("chapterRecords", pagers);
		if(type==null){
			return "/question/chapterrecord/zhangjie_cuoti_detail/zhangjie_cuoti";
		}else{
			return "/question/chapterrecord/zhangjie_cuoti_detail/zhangjie_cuoti_list";
		}
	}
}
