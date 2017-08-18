package com.jingren.jing.question.comtroller.front.zhentiyear;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.question.bean.chapter_exercises.ChapterQuerstionOption;
import com.jingren.jing.question.bean.chapter_exercises.ChapterQuestion;
import com.jingren.jing.question.bean.chapterrecord.ChapterRecord;
import com.jingren.jing.question.bean.question_course.QuestionCourse;
import com.jingren.jing.question.bean.zhentioption.ZhentiQuestionOption;
import com.jingren.jing.question.bean.zhentiquestion.ZhentiQuestion;
import com.jingren.jing.question.bean.zhentirecord.ZhentiRecord;
import com.jingren.jing.question.bean.zhentitypeintro.ZhentiTypeIntroduce;
import com.jingren.jing.question.bean.zhentiyear.ZhentiYears;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuerstionOptionService;
import com.jingren.jing.question.service.chapter_exercises.ChapterQuestionService;
import com.jingren.jing.question.service.chapterrecord.ChapterRecordService;
import com.jingren.jing.question.service.question_course.QuestionCourseService;
import com.jingren.jing.question.service.zhentioption.ZhentiQuestionOptionService;
import com.jingren.jing.question.service.zhentiquestion.ZhentiQuestionService;
import com.jingren.jing.question.service.zhentirecord.ZhentiRecordService;
import com.jingren.jing.question.service.zhentitypeintro.ZhentiTypeIntroduceService;
import com.jingren.jing.question.service.zhentiyear.ZhentiYearsService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.ResponseUtils;

import net.sf.json.JSONObject;

/**
* @Title: FrontZhentiYearController.java 
* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
* @Description: TODO 题库历年真题
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月14日 下午1:58:07 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("front_zhenti")
public class FrontZhentiYearController {

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
	private ChapterRecordService chapterRecordService;
	@Resource
	private ChapterQuestionService chapterQuestionService;
	@Resource
	private  ChapterQuerstionOptionService chapterQuerstionOptionService;
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 获取历年真题首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月14日 下午2:02:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhenti_year_main.html")
	public String get_zhenti_year_main(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id){
		Map<String, Object> map = new HashMap<>();
		User user=(User) session.getAttribute("user_session");
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
		map.put("question_course_id", question_course_id);
		map.put("is_show", "显示");
		map.put("zhentitype", "历年真题");
		List<ZhentiYears> zhentiYears=zhentiYearsService.getZhentiYearsList(map);//历年真题列表
		for (ZhentiYears zhentiYears2 : zhentiYears) {
			map.clear();
			map.put("zhenti_id", zhentiYears2.getZhenti_id());
			int tishu=zhentiQuestionService.getZhentiQuestionNumber(map);
			zhentiYears2.setZhenti_zongtishu(tishu);
			List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);
			int zongfenzhi=0;
			for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
				zongfenzhi+=zhentiQuestion.getFenzhi();
			}
			zhentiYears2.setZhenti_zongfen(zongfenzhi);
			map.put("user_id", user.getUser_id());
			map.put("parent_id", 0);
			List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
			if(zhentiRecords.size()>0){
				if(zhentiRecords.get(0).getRecourd_state()==1){
					zhentiYears2.setIs_jixu(1);//再做一次
					zhentiYears2.setZhenti_record_id(zhentiRecords.get(0).getZhenti_record_id());
				}else{
					zhentiYears2.setIs_jixu(2);//继续答题
					zhentiYears2.setZhenti_record_id(zhentiRecords.get(0).getZhenti_record_id());
				}
			}else{
				zhentiYears2.setIs_jixu(0);
			}
		}
		model.addAttribute("zhentiYears", zhentiYears);
		return "/question/zhenti_years/zhenti_years_main";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 历年真题-题型介绍
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月14日 下午2:44:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhenti_year_introduce.html")
	public String get_zhenti_year_introduce(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "zhenti_id", required = false) Integer zhenti_id){
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
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		int tishu=zhentiQuestionService.getZhentiQuestionNumber(map);
		zhentiYears.setZhenti_zongtishu(tishu);
//		List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);
//		int zongfenzhi=0;
//		for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
//			zongfenzhi+=zhentiQuestion.getFenzhi();
//		}
//		zhentiYears.setZhenti_zongfen(zongfenzhi);
		model.addAttribute("zhentiYears", zhentiYears);
		List<ZhentiTypeIntroduce> zhentiTypeIntroduces=zhentiTypeIntroduceService.getZhentiTypeIntroduceList(map);
		model.addAttribute("zhentiTypeIntroduces", zhentiTypeIntroduces);
		return "/question/zhenti_years/zhenti_years_intro";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 真题做题页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月14日 下午3:13:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhenti_question_now.html")
	public String get_zhenti_question_now(Model model,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "zhenti_id", required = false) Integer zhenti_id){
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
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		int tishu=zhentiQuestionService.getZhentiQuestionNumber(map);
		zhentiYears.setZhenti_zongtishu(tishu);
		model.addAttribute("zhentiYears", zhentiYears);
		List<ZhentiTypeIntroduce> zhentiTypeIntroduces=zhentiTypeIntroduceService.getZhentiTypeIntroduceList(map);
		int xuhao=0;
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
				xuhao++;
				zhentiQuestion.setXuhao(xuhao);
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
		return "/question/zhenti_years/zhenti_question_now";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 保存真题-答题进度
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月15日 下午1:14:06 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_zhenti_jindu.html")
	public void save_zhenti_jindu(ZhentiRecord zhentiRecord,ChapterRecord reChapterRecord,HttpServletResponse response,HttpSession session,
			@RequestParam(value="str",required=false)String str,
			@RequestParam(value="str_duo",required=false)String str_duo,
			@RequestParam(value="jineng_op",required=false)String jineng_op,
			@RequestParam(value="jineng_id",required=false)String jineng_id){
		User user=(User) session.getAttribute("user_session");
		Map<String, Object> map_zhenti=new HashMap<>();
		map_zhenti.put("zhenti_id", zhentiRecord.getZhenti_id());
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map_zhenti);
		zhentiRecord.setZhenti_type(zhentiYears.getZhentitype());
		zhentiRecord.setUser_id(user.getUser_id());
		zhentiRecord.setRecourd_time(new Date());
//		zhentiRecord.setRecourd_state(2);
		String order_nbumber=DesUtil.getUUID();
		//技能选择题
		zhentiRecord.setQuestion_order_number(order_nbumber);
		reChapterRecord.setQuestion_order_number(order_nbumber);
		reChapterRecord.setUser_id(user.getUser_id());//用户ID
		reChapterRecord.setRecourd_time(new Date());
		String[] strarrjineng=jineng_id.split(",");
		String[] str_duo_option_jineng=jineng_op.split(",");//多选选项
		Map<String, Object> map=new HashMap<>();
		if(zhentiRecordService.saveZhentiRecord(zhentiRecord)){
			ZhentiRecord zhentiRecord2=new ZhentiRecord();
			if(StringUtils.isNotBlank(str)){
				String[] strarr=str.split(",");
				String[] str_duo_option=str_duo.split(",");//选项
				map.clear();
				for (int i=0;i<strarr.length;i++) {
					if(StringUtils.isNotBlank(strarr[i])){
					zhentiRecord2.setZhenti_question_id(Integer.valueOf(strarr[i]));//不分章节的题目ID
					map.put("zhenti_question_id", Integer.valueOf(strarr[i]));
					ZhentiQuestion zhentiQuestion=zhentiQuestionService.getZhentiQuestion(map);
					zhentiRecord2.setQuestion_type(zhentiQuestion.getQuestion_type());//保存题型
					if(zhentiQuestion.getQuestion_type().equals("技能选择题")||zhentiQuestion.getQuestion_type().equals("案例简答题")){
						zhentiRecord2.setZhenti_question_id(Integer.valueOf(strarr[i]));
						if(str_duo_option_jineng.length>=i){
							zhentiRecord2.setUser_answer(str_duo_option_jineng[i]);//答案
						}
					}else{
						zhentiRecord2.setUser_answer(str_duo_option[i]);//答案
						if(zhentiQuestion.getQuestion_type().equals("案例题")||zhentiQuestion.getQuestion_type().equals("案例简答题")){
							if(StringUtils.isNotBlank(zhentiRecord2.getUser_answer())){
								zhentiRecord2.setIs_right("正确");
							}else{
								zhentiRecord2.setIs_right("错误");
							}
						}else{
							if(zhentiQuestion.getQuestion_type().equals("案例简答题")||zhentiQuestion.getQuestion_type().equals("简答题")){
								if(StringUtils.isNotBlank(zhentiRecord2.getUser_answer())){
									zhentiRecord2.setIs_right("正确");
								}else{
									zhentiRecord2.setIs_right("错误");
								}
							}else{
								if(zhentiQuestion.getAnswer().equals(zhentiRecord2.getUser_answer())){
									zhentiRecord2.setIs_right("正确");
								}else{
									zhentiRecord2.setIs_right("错误");
								}
							}
						}
					}
					zhentiRecord2.setQuestion_course_id(zhentiRecord.getQuestion_course_id());
					zhentiRecord2.setUser_id(user.getUser_id());
					zhentiRecord2.setZhenti_type(zhentiYears.getZhentitype());
					zhentiRecord2.setParent_id(zhentiRecord.getZhenti_record_id());
					zhentiRecordService.saveZhentiRecord(zhentiRecord2);//保存答题记录
					}
				}
			}
			
			if(StringUtils.isNotBlank(jineng_id)){
				for (int i=0;i<strarrjineng.length;i++) {
					if(StringUtils.isNotBlank(strarrjineng[i])){
					reChapterRecord.setzhenti_record_id(zhentiRecord.getZhenti_record_id());
					reChapterRecord.setChapter_question_id(Integer.valueOf(strarrjineng[i]));//不分章节的题目ID
					reChapterRecord.setUser_answer(str_duo_option_jineng[i]);//答案
					map.put("chapter_question_id", Integer.valueOf(strarrjineng[i]));
					ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
					reChapterRecord.setQuestion_type(chapterQuestion.getQuestion_type());//保存题型
					if(chapterQuestion.getAnswer().equals(reChapterRecord.getUser_answer())){
						reChapterRecord.setIs_right("正确");
					}else{
						reChapterRecord.setIs_right("错误");
					}
					chapterRecordService.saveChapterRecord(reChapterRecord);//保存答题记录
					}
				}
				}
			if(zhentiYears.getZhentitype().equals("历年真题")){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "2");
			}
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 继续答题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月27日 上午9:22:10 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhenti_question_jixu.html")
	public String get_zhenti_question_jixu(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "zhenti_id", required = false) Integer zhenti_id,
			@RequestParam(value = "zhenti_record_id", required = false) Integer zhenti_record_id){
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
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		int tishu=zhentiQuestionService.getZhentiQuestionNumber(map);
		zhentiYears.setZhenti_zongtishu(tishu);
		model.addAttribute("zhentiYears", zhentiYears);
		List<ZhentiTypeIntroduce> zhentiTypeIntroduces=zhentiTypeIntroduceService.getZhentiTypeIntroduceList(map);
		int xuhao=0;
		map.clear();
		map.put("zhenti_record_id", zhenti_record_id);
		ZhentiRecord record=zhentiRecordService.getZhentiRecord(map);
		String[] s=record.getZhuanhuan_time().split(":");
		List<Integer> integers=new ArrayList<>();
		for (String string : s) {
			integers.add(Integer.valueOf(string));
		}
		record.setShifenmiao(integers);
		model.addAttribute("record", record);
		map.clear();
		map.put("parent_id_jixu", record.getZhenti_record_id());
		List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
		int zuole=0;//做过的题数
		int anli=1;//做过的案例简答题的数量
		int xuanze=1;//做过的技能选择题的数量
		for (ZhentiTypeIntroduce zhentiTypeIntroduce : zhentiTypeIntroduces) {
			map.put("zhenti_id", zhenti_id);
			map.put("question_type", zhentiTypeIntroduce.getQuestion_type());
			List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);//题目列表按照类型分类
			for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
				for (ZhentiRecord zhentiRecord : zhentiRecords) {
					if(zhentiRecord.getZhenti_question_id()==zhentiQuestion.getZhenti_question_id()){
						if(StringUtils.isNotBlank(zhentiRecord.getUser_answer())){
							if(!zhentiRecord.getUser_answer().equals("无")){
								if(zhentiRecord.getQuestion_type().equals("技能选择题")){
									xuanze++;
								}
								if(zhentiRecord.getQuestion_type().equals("案例简答题")){
									anli++;
								}
								zuole++;
								zhentiQuestion.setUser_answer(zhentiRecord.getUser_answer());
								if(zhentiQuestion.getQuestion_type().equals("多选题")){
									String[] user_answer_arr=zhentiQuestion.getUser_answer().replace(".", ",").split(",");
									List<String> user_answer_list=new ArrayList<>();
									user_answer_list=Arrays.asList(user_answer_arr);//String[]转成list
									zhentiQuestion.setDuoxuanlisti(user_answer_list);
								}
							}
						}
					}
				}
				map.clear();
				map.put("zhenti_question_id", zhentiQuestion.getZhenti_question_id());
				List<ZhentiQuestionOption> zhentiQuestionOptions=zhentiQuestionOptionService.getZhentiQuestionOptionList(map);//题目选项
				if(zhentiQuestion.getQuestion_type().equals("多选题")){
					for (ZhentiQuestionOption zhentiQuestionOption : zhentiQuestionOptions) {
						if(StringUtils.isNotBlank(zhentiQuestion.getUser_answer())){
							for (String string2 : zhentiQuestion.getDuoxuanlisti()) {
								if(zhentiQuestionOption.getOption_number().equals(string2)){
									zhentiQuestionOption.setIs_dati(1);//做过了
									break;
								}else{
									zhentiQuestionOption.setIs_dati(0);//没做
									continue;
								}
							}
						}
					}
				
				}
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
				zhentiQuestion.setZhentiQuestionOptions(zhentiQuestionOptions);
				xuhao++;
				zhentiQuestion.setXuhao(xuhao);
			}
			model.addAttribute("zuole", zuole-(xuanze+anli-2));
			zhentiTypeIntroduce.setZhentiQuestions(zhentiQuestions);
			
		}
		
		model.addAttribute("zhentiTypeIntroduces", zhentiTypeIntroduces);
		return "/question/zhenti_years/zhenti_question_jixu";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 真题解析
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年2月27日 上午11:25:35 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhenti_question_jiexi.html")
	public String get_zhenti_question_jiexi(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "zhenti_id", required = false) Integer zhenti_id,
			@RequestParam(value = "zhenti_record_id", required = false) Integer zhenti_record_id){
		User user=(User) session.getAttribute("user_session");
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
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		int tishu=zhentiQuestionService.getZhentiQuestionNumber(map);
		zhentiYears.setZhenti_zongtishu(tishu);
		model.addAttribute("zhentiYears", zhentiYears);
		List<ZhentiTypeIntroduce> zhentiTypeIntroduces=zhentiTypeIntroduceService.getZhentiTypeIntroduceList(map);
		int xuhao=0;
		map.clear();
		map.put("zhenti_record_id", zhenti_record_id);
		ZhentiRecord record=zhentiRecordService.getZhentiRecord(map);
		model.addAttribute("record", record);
		map.clear();
		map.put("parent_id_jixu", record.getZhenti_record_id());
		List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
		int zuole=0;//做过的题数
		int fenzhi=0;//得分
		for (ZhentiTypeIntroduce zhentiTypeIntroduce : zhentiTypeIntroduces) {
			map.put("zhenti_id", zhenti_id);
			map.put("question_type", zhentiTypeIntroduce.getQuestion_type());
			List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);//题目列表按照类型分类
			for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
				map.clear();
				map.put("user_id", user.getUser_id());
				map.put("zhenti_question_id", zhentiQuestion.getZhenti_question_id());
				map.put("user_answer_wu", "无");
				Integer zuoguo=zhentiRecordService.getZhentiRecordNumber(map);
				map.put("is_right", "正确");
				Integer zhengque=zhentiRecordService.getZhentiRecordNumber(map);
				zhentiQuestion.setZuoguo_number(zuoguo);
				zhentiQuestion.setZuodui_number(zhengque);
				for (ZhentiRecord zhentiRecord : zhentiRecords) {
					zhentiQuestion.setIs_right(zhentiRecord.getIs_right());
					continue;
				}
				for (ZhentiRecord zhentiRecord : zhentiRecords) {
					int record_id=zhentiRecord.getZhenti_question_id();
					int zhenti_que_id=zhentiQuestion.getZhenti_question_id();
					if(record_id==zhenti_que_id){
						if(!zhentiRecord.getUser_answer().equals("无")){
							zuole++;
							zhentiQuestion.setUser_answer(zhentiRecord.getUser_answer());
							if(zhentiQuestion.getQuestion_type().equals("多选题")){
								String[] user_answer_arr=zhentiQuestion.getUser_answer().replace(".", ",").split(",");
								List<String> user_answer_list=new ArrayList<>();
								user_answer_list=Arrays.asList(user_answer_arr);//String[]转成list
								zhentiQuestion.setDuoxuanlisti(user_answer_list);
							}
						}else{
							if(zhentiQuestion.getQuestion_type().equals("多选题")){
								String[] user_answer_arr=zhentiQuestion.getAnswer().replace(".", ",").split(",");
								List<String> user_answer_list=new ArrayList<>();
								user_answer_list=Arrays.asList(user_answer_arr);//String[]转成list
								zhentiQuestion.setDuoxuanlisti(user_answer_list);
							}
						}
					}
					if(StringUtils.isNotBlank(zhentiRecord.getIs_right())){
						if(zhentiRecord.getIs_right().equals("正确")){//作对的分值
							fenzhi+=zhentiQuestion.getFenzhi();
						}
					}
				}
				map.clear();
				map.put("zhenti_question_id", zhentiQuestion.getZhenti_question_id());
				List<ZhentiQuestionOption> zhentiQuestionOptions=zhentiQuestionOptionService.getZhentiQuestionOptionList(map);//题目选项
				if(zhentiQuestion.getQuestion_type().equals("多选题")){
					for (ZhentiQuestionOption zhentiQuestionOption : zhentiQuestionOptions) {
						if(StringUtils.isNotBlank(zhentiQuestion.getUser_answer())){
							for (String string2 : zhentiQuestion.getDuoxuanlisti()) {
								if(zhentiQuestionOption.getOption_number().equals(string2)){
									zhentiQuestionOption.setIs_dati(1);//做过了
									break;
								}else{
									zhentiQuestionOption.setIs_dati(0);//没做
									continue;
								}
							}
						}else{
							zhentiQuestionOption.setIs_dati(0);
						}
					}
				
				}
				//技能选择题
				if(zhentiQuestion.getQuestion_type().equals("技能选择题")||zhentiQuestion.getQuestion_type().equals("案例简答题")){
					map.clear();
					map.put("zhenti_question_id", zhentiQuestion.getZhenti_question_id());
					List<ChapterQuestion> chapterQuestions=chapterQuestionService.getChapterQuestionList(map);
					for (ChapterQuestion chapterQuestion : chapterQuestions) {
						map.clear();
						map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
						List<ChapterQuerstionOption> chapterQuerstionOptions=chapterQuerstionOptionService.getChapterQuerstionOptionList(map);
						chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
						map.put("user_id", user.getUser_id());
						map.put("zhenti_record_id", zhenti_record_id);
						ChapterRecord chapterRecord=chapterRecordService.getChapterRecord(map);
						if(chapterRecord!=null){
							if(chapterRecord.getIs_right().equals("正确")){
								fenzhi+=chapterQuestion.getFenzhi();
							}
						}
						if(chapterQuestion.getQuestion_type().equals("多选题")){
							String[] user_answer_arr=chapterQuestion.getAnswer().replace(".", ",").split(",");
							List<String> user_answer_list=new ArrayList<>();
							user_answer_list=Arrays.asList(user_answer_arr);//String[]转成list
							chapterQuestion.setAnswer_list(user_answer_list);
						}
						if(chapterQuestion.getQuestion_type().equals("多选题")){
						for (ChapterQuerstionOption chapterQuerstionOption : chapterQuerstionOptions) {
							for (String string2 : chapterQuestion.getAnswer_list()) {
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
						chapterQuestion.setChapterRecord(chapterRecord);
					}
					zhentiQuestion.setChapterQuestions(chapterQuestions);
				}
				zhentiQuestion.setZhentiQuestionOptions(zhentiQuestionOptions);
				xuhao++;
				zhentiQuestion.setXuhao(xuhao);
			}
			model.addAttribute("zuole", zuole);
			model.addAttribute("fenzhi", fenzhi);//得分
			zhentiTypeIntroduce.setZhentiQuestions(zhentiQuestions);
			
		}
		model.addAttribute("zhentiTypeIntroduces", zhentiTypeIntroduces);
		return "/question/zhenti_years/zhenti_question_jiexi";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 历年真题-试卷分析
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月27日 下午1:39:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhenti_fenxi.html")
	public String get_zhenti_fenxi(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id,
			@RequestParam(value = "zhenti_id", required = false) Integer zhenti_id,
			@RequestParam(value = "zhenti_record_id", required = false) Integer zhenti_record_id){
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
		map.put("zhenti_id", zhenti_id);
		ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
		map.clear();
		map.put("zhenti_record_id", zhenti_record_id);
		ZhentiRecord record=zhentiRecordService.getZhentiRecord(map);
		model.addAttribute("record", record);
		map.clear();
		map.put("zhenti_id", zhenti_id);
		List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);
		int zongfenzhi=0;
		map.clear();
		map.put("parent_id_jixu", zhenti_record_id);
		List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
		int fenzhi=0;//得分
		int dadui=0;
		for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
			zongfenzhi+=zhentiQuestion.getFenzhi();
			for (ZhentiRecord zhentiRecord : zhentiRecords) {
				if(zhentiRecord.getIs_right().equals("正确")){//作对的分值
					fenzhi+=zhentiQuestion.getFenzhi();
					dadui++;
				}
			}
		}
	
		zhentiYears.setZhenti_zongfen(zongfenzhi);
		model.addAttribute("zhentiYears", zhentiYears);
		model.addAttribute("fenzhi", fenzhi);
		model.addAttribute("dadui", dadui);
		model.addAttribute("zongtishu", zhentiRecords.size());
		map.clear();
		map.put("zhenti_id", zhenti_id);
		int xuhao=0;
		List<ZhentiTypeIntroduce> zhentiTypeIntroduces=zhentiTypeIntroduceService.getZhentiTypeIntroduceList(map);
		for (ZhentiTypeIntroduce zhentiTypeIntroduce : zhentiTypeIntroduces) {
			map.clear();
			map.put("parent_id_jixu", zhenti_record_id);
			map.put("question_type", zhentiTypeIntroduce.getQuestion_type());
			List<ZhentiRecord> zhentiRecords2=zhentiRecordService.getZhentiRecordList(map);
			for (ZhentiRecord zhentiRecord : zhentiRecords2) {
				xuhao++;
				zhentiRecord.setXuhao(xuhao);
			}
			zhentiTypeIntroduce.setZhentiRecords(zhentiRecords2);
		}
		model.addAttribute("zhentiTypeIntroduces", zhentiTypeIntroduces);
		return "/question/zhenti_years/zhenti_question_fenxi";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO获取历年真题答题记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月27日 下午2:51:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhenti_record_list.html")
	public String get_zhenti_record_list(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id){
		Map<String, Object> map = new HashMap<>();
		User user=(User) session.getAttribute("user_session");
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
		map.put("question_course_id", question_course_id);
		map.put("user_id", user.getUser_id());
		map.put("zhentitype", "历年真题");
		map.put("parent_id", 0);
		List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
		int fenzhi=0;//得分
		for (ZhentiRecord zhentiRecord : zhentiRecords) {
			map.clear();
			map.put("zhenti_id", zhentiRecord.getZhenti_id());
			ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
			map.clear();
			map.put("zhenti_id", zhentiYears.getZhenti_id());
			int tishu=zhentiQuestionService.getZhentiQuestionNumber(map);
			zhentiYears.setZhenti_zongtishu(tishu);
			List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);
			int zongfenzhi=0;
			for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
				zongfenzhi+=zhentiQuestion.getFenzhi();
			}
			zhentiYears.setZhenti_zongfen(zongfenzhi);
			zhentiRecord.setZhentiYears(zhentiYears);
			map.clear();
			map.put("parent_id_jixu", zhentiRecord.getZhenti_record_id());
			List<ZhentiRecord> zhentiRecords2=zhentiRecordService.getZhentiRecordList(map);
			for (ZhentiRecord zhentiRecord2 : zhentiRecords2) {
				if(zhentiRecord2.getQuestion_type().equals("技能选择题")||zhentiRecord2.getQuestion_type().equals("案例简答题")){
					//技能选择题
					map.clear();
					map.put("zhenti_question_id",  zhentiRecord2.getZhenti_question_id());
					ZhentiQuestion zhentiQuestion_jineng=zhentiQuestionService.getZhentiQuestion(map);
					map.clear();
					map.put("zhenti_question_id", zhentiQuestion_jineng.getZhenti_question_id());
					List<ChapterQuestion> chapterQuestions=chapterQuestionService.getChapterQuestionList(map);
					for (ChapterQuestion chapterQuestion : chapterQuestions) {
						map.clear();
						map.put("chapter_question_id", chapterQuestion.getChapter_question_id());
						List<ChapterQuerstionOption> chapterQuerstionOptions=chapterQuerstionOptionService.getChapterQuerstionOptionList(map);
						chapterQuestion.setChapterQuerstionOptions(chapterQuerstionOptions);
						map.put("user_id", user.getUser_id());
						map.put("zhenti_record_id", zhentiRecord2.getZhenti_record_id());
						ChapterRecord chapterRecord=chapterRecordService.getChapterRecord(map);
						if(chapterRecord!=null){
							if(chapterRecord.getIs_right().equals("正确")){
								fenzhi+=chapterQuestion.getFenzhi();
							}
						}
						break;
					}
				}else{
					if(zhentiRecord2.getIs_right().equals("正确")){//作对的分值
						map.clear();
						map.put("zhenti_question_id",  zhentiRecord2.getZhenti_question_id());
						ZhentiQuestion zhentiQuestion=zhentiQuestionService.getZhentiQuestion(map);
						
						fenzhi+=zhentiQuestion.getFenzhi();
					}
				}
			}
			zhentiRecord.setFenzhi(fenzhi);
		}
		model.addAttribute("zhentiRecords", zhentiRecords);
		return "/question/zhenti_years/zhenti_record_list";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 模拟考试首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月28日 上午8:51:43 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_moni_kaoshi_main.html")
	public String get_moni_kaoshi_main(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id){
		Map<String, Object> map = new HashMap<>();
		User user=(User) session.getAttribute("user_session");
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
		map.put("question_course_id", question_course_id);
		map.put("is_show", "显示");
		map.put("zhentitype", "模拟考试");
		List<ZhentiYears> zhentiYears=zhentiYearsService.getZhentiYearsList(map);//历年真题列表
		for (ZhentiYears zhentiYears2 : zhentiYears) {
			map.clear();
			map.put("zhenti_id", zhentiYears2.getZhenti_id());
			int tishu=zhentiQuestionService.getZhentiQuestionNumber(map);
			zhentiYears2.setZhenti_zongtishu(tishu);
			List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);
			int zongfenzhi=0;
			for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
				zongfenzhi+=zhentiQuestion.getFenzhi();
			}
			zhentiYears2.setZhenti_zongfen(zongfenzhi);
			map.put("user_id", user.getUser_id());
			map.put("parent_id", 0);
			List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
			if(zhentiRecords.size()>0){
				if(zhentiRecords.get(0).getRecourd_state()==1){
					zhentiYears2.setIs_jixu(1);//再做一次
					zhentiYears2.setZhenti_record_id(zhentiRecords.get(0).getZhenti_record_id());
				}else{
					zhentiYears2.setIs_jixu(2);//继续答题
					zhentiYears2.setZhenti_record_id(zhentiRecords.get(0).getZhenti_record_id());
				}
			}else{
				zhentiYears2.setIs_jixu(0);
			}
		}
		model.addAttribute("zhentiYears", zhentiYears);
		return "/question/monikaoshi/monikaoshi_main";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO 模拟考试记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月28日 上午9:48:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_monikaoshi_record_list.html")
	public String get_monikaoshi_record_list(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id){
		Map<String, Object> map = new HashMap<>();
		User user=(User) session.getAttribute("user_session");
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
		map.put("question_course_id", question_course_id);
		map.put("user_id", user.getUser_id());
		map.put("zhenti_type", "模拟考试");
		map.put("parent_id", 0);
		List<ZhentiRecord> zhentiRecords=zhentiRecordService.getZhentiRecordList(map);
		int fenzhi=0;//得分
		for (ZhentiRecord zhentiRecord : zhentiRecords) {
			map.clear();
			map.put("zhenti_id", zhentiRecord.getZhenti_id());
			ZhentiYears zhentiYears=zhentiYearsService.getZhentiYears(map);
			map.clear();
			map.put("zhenti_id", zhentiYears.getZhenti_id());
			int tishu=zhentiQuestionService.getZhentiQuestionNumber(map);
			zhentiYears.setZhenti_zongtishu(tishu);
			List<ZhentiQuestion> zhentiQuestions=zhentiQuestionService.getZhentiQuestionList(map);
			int zongfenzhi=0;
			for (ZhentiQuestion zhentiQuestion : zhentiQuestions) {
				zongfenzhi+=zhentiQuestion.getFenzhi();
			}
			zhentiYears.setZhenti_zongfen(zongfenzhi);
			zhentiRecord.setZhentiYears(zhentiYears);
			map.clear();
			map.put("parent_id_jixu", zhentiRecord.getZhenti_record_id());
			List<ZhentiRecord> zhentiRecords2=zhentiRecordService.getZhentiRecordList(map);
			for (ZhentiRecord zhentiRecord2 : zhentiRecords2) {
				if(zhentiRecord2.getIs_right().equals("正确")){//作对的分值
					map.clear();
					map.put("zhenti_question_id", zhentiRecord2.getZhenti_question_id());
					ZhentiQuestion zhentiQuestion=zhentiQuestionService.getZhentiQuestion(map);
					fenzhi+=zhentiQuestion.getFenzhi();
				}
			}
			zhentiRecord.setFenzhi(fenzhi);
		}
		model.addAttribute("zhentiRecords", zhentiRecords);
		return "/question/zhenti_years/zhenti_record_list";
	}
	/**
	* @Title: FrontZhentiYearController.java 
	* @Package com.jingren.jing.question.comtroller.front.zhentiyear 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月28日 下午1:31:55 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_nengli_main.html")
	public String get_nengli_main(Model model,HttpSession session,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id){
		Map<String, Object> map = new HashMap<>();
		User user=(User) session.getAttribute("user_session");
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
		map.put("cuoti_user_id", user.getUser_id());
		map.put("question_course_id", question_course_id);
		Integer chapter_record_number=chapterRecordService.getChapterRecordNumberNormal(map);
		model.addAttribute("chapter_record_number", chapter_record_number);	//章节练习做的题数
		map.put("is_right", "正确");
		Integer chapter_record__zhengque_number=chapterRecordService.getChapterRecordNumberNormal(map);
		double zhangjie_lv=0;
		if(chapter_record_number!=0){
			zhangjie_lv=(double)chapter_record__zhengque_number/chapter_record_number;//章节练习的正确率
		}
		map.clear();
		map.put("zhenti_type", "模拟考试");
		map.put("user_id", user.getUser_id());
		map.put("parent_id", 0);
		map.put("question_course_id", question_course_id);
		Integer moni_record_number=zhentiRecordService.getZhentiRecordNumber(map);
		model.addAttribute("moni_record_number", moni_record_number);	//模拟考试的次数
		List<ZhentiRecord> zhentiRecords =zhentiRecordService.getZhentiRecordList(map);
		if(zhentiRecords.size()>0){
			double moni_lv=0;
			for (ZhentiRecord zhentiRecord : zhentiRecords) {
				map.clear();
				map.put("zhenti_id", zhentiRecord.getZhenti_id());
				Integer zhentinumber=zhentiQuestionService.getZhentiQuestionNumber(map);//总的真题题数
				map.clear();
				map.put("parent_id_nengli", zhentiRecord.getZhenti_record_id());
				map.put("is_right", "正确");
				Integer zhenti_zhengque_number=zhentiRecordService.getZhentiRecordNumber(map);
				moni_lv+=(double)zhenti_zhengque_number/zhentinumber;
			}
			double pingjun_moni_lv=(double)moni_lv/zhentiRecords.size();
			double zhengquelv=(double)(zhangjie_lv+pingjun_moni_lv)/2;
			model.addAttribute("zhengquelv", zhengquelv);
		}else{
			double zhengquelv=zhangjie_lv/2;
			model.addAttribute("zhengquelv", zhengquelv);
		}
		return "/question/select_topic/nenglipinggu";
	}
	/**
	* @Title: BackZhentiController.java 
	* @Package com.jingren.jing.question.comtroller.back.zhentiyears 
	* @Description: TODO 能力评估曲线
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月28日 下午2:52:56 
	* @version 网校+CRM系统 V1.0
	 * @throws IOException 
	 */
	@RequestMapping("/quxiantu_limit.html")
	public void quxiantu_limit(HttpSession session,HttpServletResponse response,
			@RequestParam(value = "question_course_id", required = false) Integer question_course_id) throws IOException{
		Map<String, Object> map=new HashMap<>();
		User user=(User) session.getAttribute("user_session");
		map.put("pageNumber", 0);
		map.put("limit", 15);
		map.put("zhenti_type", "模拟考试");
		map.put("user_id", user.getUser_id());
		map.put("parent_id", 0);
		map.put("question_course_id", question_course_id);
		List<Integer> defen_list=new ArrayList<>();
		List<String> cishu_list=new ArrayList<>();
		List<ZhentiRecord> zhentiRecords =zhentiRecordService.getZhentiRecordList(map);
		Integer cishu=0;
		for (ZhentiRecord zhentiRecord : zhentiRecords) {
			Integer defen=0;
			map.clear();
			map.put("parent_id_jixu", zhentiRecord.getZhenti_record_id());
			List<ZhentiRecord> zhentiRecords_sub=zhentiRecordService.getZhentiRecordList(map);//题目记录
			for (ZhentiRecord zhentiRecord2 : zhentiRecords_sub) {
				if(!zhentiRecord2.getQuestion_type().equals("技能选择题")&&!zhentiRecord2.getQuestion_type().equals("案例简答题")){
					if(zhentiRecord2.getIs_right().equals("正确")){
						map.clear();
						map.put("zhenti_question_id", zhentiRecord2.getZhenti_question_id());
						ZhentiQuestion zhentiQuestion=zhentiQuestionService.getZhentiQuestion(map);
						defen+=zhentiQuestion.getFenzhi();
					}
				}
				
			}
			map.clear();
			map.put("zhenti_record_id", zhentiRecord.getZhenti_record_id());
			List<ChapterRecord> chapterRecords=chapterRecordService.getChapterRecordList(map);
			for (ChapterRecord chapterRecord : chapterRecords) {
				if(chapterRecord.getIs_right().equals("正确")){
					map.clear();
					map.put("chapter_question_id", chapterRecord.getChapter_question_id());
					ChapterQuestion chapterQuestion=chapterQuestionService.getChapterQuestion(map);
					defen+=chapterQuestion.getFenzhi();
				}
			}
			defen_list.add(defen);
			cishu++;
			cishu_list.add("第"+cishu+"次");
		}
		 /* 设置格式为text/json    */
        response.setContentType("text/json"); 
        /*设置字符集为'UTF-8'*/
        response.setCharacterEncoding("UTF-8"); 
        PrintWriter out = response.getWriter(); 
        /* 转变成JSON格式 */
        JSONObject json= new JSONObject();
        json.put("defen",defen_list);
        json.put("cishu",cishu_list);
        out.print(json);
        out.flush();
	}
}
