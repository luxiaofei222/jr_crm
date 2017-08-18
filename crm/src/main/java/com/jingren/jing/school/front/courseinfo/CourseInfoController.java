package com.jingren.jing.school.front.courseinfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: CourseInfoController.java 
* @Package com.jingren.jing.school.front.courseinfo 
* @Description: TODO 课程资讯
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月12日 上午9:20:23 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.classtype.ClassType;
import com.jingren.jing.school.bean.clearance.Clearance;
import com.jingren.jing.school.bean.clearance.ClearanceVideo;
import com.jingren.jing.school.bean.course.CourseInfo;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.mycourse.MyCourse;
import com.jingren.jing.school.bean.teacher.Teacher;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.classtype.ClassTypeService;
import com.jingren.jing.school.service.clearance.ClearanceService;
import com.jingren.jing.school.service.clearance.ClearanceVideoService;
import com.jingren.jing.school.service.course.CourseInfoService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.mycourse.MyCourseService;
import com.jingren.jing.school.service.teacher.TeacherService;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.Pagers;

@Controller
@RequestMapping("front_info")
public class CourseInfoController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private CourseInfoService courseInfoService;
	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private MyCourseService myCourseService;
	@Resource
	private ClassTypeService classTypeService;
	@Resource
	private ClearanceService clearanceService;
	@Resource
	private ClearanceVideoService clearanceVideoService;

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 课程资讯二级页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月12日 上午9:22:02
	 * @version 网校+CRM系统 V1.0
	 * @throws Exception
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	@RequestMapping("/get_course_info.html")
	public Callable<String> get_course_info(final Model model, final HttpSession session,
			@RequestParam(value = "courseid", required = false) final String courseid) throws Exception {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				if (DesUtil.decrypt(courseid, "courseinfo") != null) {
					Integer course_id = Integer.valueOf(DesUtil.decrypt(courseid, "courseinfo"));
					Map<String, Object> map = new HashMap<>();
					map.put("course_id", course_id);
					map.put("moren", "是");
					map.put("is_info", "是");
					List<CourseVideo> courseVideos = courseVideoService.getCourseVideoList(map);
					model.addAttribute("courseVideos", courseVideos);
					CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
					session.removeAttribute("menu_name");
					session.setAttribute("menu_name", courseMenu.getCourse_name());
					model.addAttribute("courseMenu", courseMenu);// 课程信息
					// 拆分年月日
					if (StringUtils.isNotBlank(courseMenu.getExam_time())) {
						String[] array = courseMenu.getExam_time().split("/");
						String year = array[0];
						model.addAttribute("year", year);
						String month = array[1];
						model.addAttribute("month", month);
						String day = array[2];
						model.addAttribute("day", day);
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
						Date date = new Date();
						String tim = sdf.format(date);
						model.addAttribute("tim", tim);
						String[] array = tim.split("/");
						String year = array[0];
						model.addAttribute("year", year);
						String month = array[1];
						model.addAttribute("month", month);
						String day = array[2];
						model.addAttribute("day", day);// 考试时间默认当日
					}
					map.put("pageNumber", 0);
					map.put("limit", 11);
					List<CourseInfo> late_update = courseInfoService.getCourseInfoList_erji(map);
					map.put("info_type", "考试动态");
					List<CourseInfo> denamic_test = courseInfoService.getCourseInfoList_erji(map);
					map.put("info_type", "考试试题");
					List<CourseInfo> exam_question = courseInfoService.getCourseInfoList_erji(map);
					map.put("info_type", "热点专题");
					List<CourseInfo> hot_topics = courseInfoService.getCourseInfoList_erji(map);
					model.addAttribute("late_update", late_update);// 最新更新列表
					model.addAttribute("denamic_test", denamic_test);// 考试动态
					model.addAttribute("exam_question", exam_question);// 考试试题
					model.addAttribute("hot_topics", hot_topics);// 热点专题
					/************************* 头部块 *********************************************/
					map.put("limit", 8);
					map.put("info_type", "考试试题");
					List<CourseInfo> exam_question_top = courseInfoService.getCourseInfoList_erji(map);
					model.addAttribute("exam_question_top", exam_question_top);
					/***************************** 底部资讯16条记录 ****************************************************/
					map.put("limit", 16);
					map.put("info_type", "考试动态");
					List<CourseInfo> denamic_test_foot = courseInfoService.getCourseInfoList_erji(map);// 考试动态
					map.put("is_hot", "是");
					List<CourseInfo> denamic_test_foot_hot = courseInfoService.getCourseInfoList_erji(map);// 考试动态
					model.addAttribute("denamic_test_foot", denamic_test_foot);// 考试动态
					model.addAttribute("denamic_test_foot_hot", denamic_test_foot_hot);// 考试动态热点推荐
					/*********************************
					 * 底部课程等级资讯-包含热点推荐
					 ***********************************************/
					map.clear();
					map.put("course_id", course_id);
					map.put("no_parent", "hehe");
					List<Dictionary> dictionaries = dictionaryService.getDictionaryList(map);
					if (dictionaries.size() == 0) {
						map.clear();
						map.put("course_id", 1001);
						dictionaries = dictionaryService.getDictionaryList(map);
						model.addAttribute("dictionaries", dictionaries);// 默认课程等级
					}
					for (Dictionary dictionary : dictionaries) {
						map.clear();
						map.put("course_id", course_id);
						map.put("dictionary_id", dictionary.getDictionary_id());
						map.put("is_dic", "是");
						map.put("pageNumber", 0);
						map.put("limit", 16);// 显示最新的16条记录
						List<CourseInfo> courseInfos_course = courseInfoService.getCourseInfoList_erji(map);
						dictionary.setCourseInfos(courseInfos_course);
						map.put("is_hot", "是");
						List<CourseInfo> courseInfos_course_hot = courseInfoService.getCourseInfoList_erji(map);
						dictionary.setHot_courseInfos(courseInfos_course_hot);// 热点推荐
					}
					model.addAttribute("dictionaries", dictionaries);
					return "/school/course_info/course_info_list";
				} else {
					return "/404";
				}
			}
		};

	}

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 课程中心
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月12日 上午11:59:07
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_course_video.html")
	public Callable<String> get_course_video(final Model model, final HttpSession session,
			@RequestParam(value = "course_id", required = false) final Integer course_id) {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				User user = (User) session.getAttribute("user_session");

				Map<String, Object> map = new HashMap<>();
				map.put("course_id", course_id);
				if(course_id!=null){
					CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
					model.addAttribute("courseMenu", courseMenu);// 课程信息
					map.put("pageNumber", 0);
					map.put("limit", 4);
					map.put("moren", "是");
					map.put("is_info", "是");
					List<CourseVideo> courseVideos = courseVideoService.getCourseVideoList(map);
					for (CourseVideo courseVideo : courseVideos) {// 课程分类
						map.clear();
						map.put("video_parent_id", courseVideo.getVideo_id());
						List<CourseVideo> courseVideos2 = courseVideoService.getCourseVideoList(map);
						courseVideo.setCourseVideos_zhang(courseVideos2);// 章
						Integer course_num = 0;
						for (CourseVideo courseVideo2 : courseVideos2) {
							map.clear();
							map.put("video_parent_id", courseVideo2.getVideo_id());
							List<CourseVideo> courseVideos3 = courseVideoService.getCourseVideoList(map);
							courseVideo2.setCourseVideos_sanji(courseVideos3);// 节
							map.put("video_level", 3);
							course_num += courseVideoService.getCourseVideoNumber(map);// 课时
						}
						courseVideo.setKeshi_number(course_num);
						map.clear();
						map.put("teacher_id", courseVideo.getTeacher_id());
						Teacher teacher = teacherService.getTeacher(map);
						courseVideo.setTeacher(teacher);// 授课老师
						map.clear();
						map.put("video_id", courseVideo.getVideo_id());
						Integer paynumber = myCourseService.getMyCourseNumber(map);
						courseVideo.setPay_number(paynumber);// 购买成功的数量
					}
					model.addAttribute("courseVideos", courseVideos);
					if (courseVideos.size() > 0) {
						model.addAttribute("video", courseVideos.get(0));
						if (user != null) {
							map.clear();
							map.put("video_id", courseVideos.get(0).getVideo_id());
							map.put("user_id", user.getUser_id());
							MyCourse myCourse = myCourseService.getMyCourse(map);
							model.addAttribute("myCourse", myCourse);
						}
					}
					return "/school/course_info/course_video";
				}else{
					return "/404";
				}
			}
		};

	}

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 获取其他的视频
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月12日 下午2:57:56
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_course_other_video.html")
	public Callable<String> get_course_other_video(final Model model, final HttpSession session,
			@RequestParam(value = "video_id", required = false) final Integer video_id) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, Object> map = new HashMap<>();
				User user = (User) session.getAttribute("user_session");
				map.put("video_id", video_id);
				if (user != null) {
					map.put("user_id", user.getUser_id());
					MyCourse myCourse = myCourseService.getMyCourse(map);
					model.addAttribute("myCourse", myCourse);
				}
				CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
				map.clear();
				map.put("video_parent_id", courseVideo.getVideo_id());
				List<CourseVideo> courseVideos2 = courseVideoService.getCourseVideoList(map);
				courseVideo.setCourseVideos_zhang(courseVideos2);// 章
				Integer course_num = 0;
				for (CourseVideo courseVideo2 : courseVideos2) {
					map.clear();
					map.put("video_parent_id", courseVideo2.getVideo_id());
					List<CourseVideo> courseVideos3 = courseVideoService.getCourseVideoList(map);
					courseVideo2.setCourseVideos_sanji(courseVideos3);// 节
					map.put("video_level", 3);
					course_num = courseVideoService.getCourseVideoNumber(map);// 课时
				}
				courseVideo.setKeshi_number(course_num);
				map.clear();
				map.put("teacher_id", courseVideo.getTeacher_id());
				Teacher teacher = teacherService.getTeacher(map);
				courseVideo.setTeacher(teacher);// 授课老师
				map.clear();
				map.put("video_id", courseVideo.getVideo_id());
				Integer paynumber = myCourseService.getMyCourseNumber(map);
				courseVideo.setPay_number(paynumber);// 购买成功的数量
				model.addAttribute("video", courseVideo);
				return "/school/course_info/other_video";
			}
		};
	}

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 资讯页班型介绍
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月12日 下午4:32:45
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_banxing_list.html")
	public String get_banxing_list(Model model,
			@RequestParam(value = "course_id", required = false) Integer course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);// 课程信息
		map.put("pageNumber", 0);
		map.put("limit", 7);
		map.put("moren", "是");
		map.put("is_info", "是");
		map.put("gr_banxing", "gr_banxing");
		List<CourseVideo> courseVideos = courseVideoService.getCourseVideoList(map);
		if (courseVideos.size() > 0) {
			map.put("banxing", courseVideos.get(0).getBanxing());
			List<CourseVideo> courseVideos_banxing = courseVideoService.getCourseVideoList(map);
			for (CourseVideo courseVideo : courseVideos_banxing) {
				map.clear();
				map.put("video_parent_id", courseVideo.getVideo_id());
				List<CourseVideo> courseVideoszhang = courseVideoService.getCourseVideoList(map);
				courseVideo.setCourseVideos_zhang(courseVideoszhang);// 章
				Integer course_num = 0;
				for (CourseVideo courseVideo2 : courseVideoszhang) {
					map.clear();
					map.put("video_parent_id", courseVideo2.getVideo_id());
					List<CourseVideo> courseVideos3 = courseVideoService.getCourseVideoList(map);
					courseVideo2.setCourseVideos_sanji(courseVideos3);// 节
					map.put("video_level", 3);
					course_num = courseVideoService.getCourseVideoNumber(map);// 课时
				}
				courseVideo.setKeshi_number(course_num);
				map.clear();
				map.put("teacher_id", courseVideo.getTeacher_id());
				Teacher teacher = teacherService.getTeacher(map);
				courseVideo.setTeacher(teacher);// 授课老师
			}
			model.addAttribute("courseVideos_banxing", courseVideos_banxing);// 班型默认列表
		}
		model.addAttribute("courseVideos", courseVideos);// 去重后的班型
		return "/school/course_info/banxing_list";
	}

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 其他班型列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月12日 下午4:49:55
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_banxing_sub_list.html")
	public String get_banxing_sub_list(Model model,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "banxing", required = false) String banxing) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		map.put("banxing", banxing);
		map.put("pageNumber", 0);
		map.put("limit", 7);
		map.put("moren", "是");
		map.put("is_info", "是");
		List<CourseVideo> courseVideos_banxing = courseVideoService.getCourseVideoList(map);
		for (CourseVideo courseVideo : courseVideos_banxing) {
			map.clear();
			map.put("video_parent_id", courseVideo.getVideo_id());
			List<CourseVideo> courseVideoszhang = courseVideoService.getCourseVideoList(map);
			courseVideo.setCourseVideos_zhang(courseVideoszhang);// 章
			Integer course_num = 0;
			for (CourseVideo courseVideo2 : courseVideoszhang) {
				map.clear();
				map.put("video_parent_id", courseVideo2.getVideo_id());
				List<CourseVideo> courseVideos3 = courseVideoService.getCourseVideoList(map);
				courseVideo2.setCourseVideos_sanji(courseVideos3);// 节
				map.put("video_level", 3);
				course_num = courseVideoService.getCourseVideoNumber(map);// 课时
			}
			courseVideo.setKeshi_number(course_num);
			map.clear();
			map.put("teacher_id", courseVideo.getTeacher_id());
			Teacher teacher = teacherService.getTeacher(map);
			courseVideo.setTeacher(teacher);// 授课老师
		}
		model.addAttribute("courseVideos_banxing", courseVideos_banxing);// 班型默认列表
		return "/school/course_info/banxing_table";
	}

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 班级介绍
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月13日 上午9:03:09
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_banji_list.html")
	public String get_banji_list(Model model, @RequestParam(value = "course_id", required = false) Integer course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		List<ClassType> classTypes = classTypeService.getClassTypeList(map);
		model.addAttribute("classTypes", classTypes);
		return "/school/course_info/banji_list";
	}

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 通关方案列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 上午10:12:00
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_clearance_list.html")
	public String get_clearance_list(Model model,
			@RequestParam(value = "dengji", required = false) String dengji,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "limit", required = false,defaultValue="2") Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1") Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		map.put("dengji_gro", "group");
		List<Clearance> clearances_group = clearanceService.getClearanceList(map);
		model.addAttribute("clearances_group", clearances_group);//等级
		map.clear();
		map.put("course_id", course_id);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		if(StringUtils.isNotBlank(dengji)){
			map.put("dengji", dengji);
			model.addAttribute("dengji", dengji);
		}else{
			map.put("dengji", clearances_group.get(0).getDengji());
			model.addAttribute("dengji", clearances_group.get(0).getDengji());
		}
		Integer clearance_number=clearanceService.getClearanceNumber(map);
		List<Clearance> clearances = clearanceService.getClearanceList(map);
		for (Clearance clearance : clearances) {
			map.clear();
			map.put("clearance_id", clearance.getClearance_id());
			List<ClearanceVideo> clearanceVideos=clearanceVideoService.getClearanceVideoList(map);
			clearance.setClearanceVideos(clearanceVideos);
		}
		Pagers<Clearance> pagers = new Pagers<Clearance>(clearance_number,
				pageNumber, limit);
		pagers.setList(clearances);
		model.addAttribute("clearances", pagers);
		model.addAttribute("course_id", course_id);
		return "/school/course_info/clearance_list";
	}
	/**
	* @Title: CourseInfoController.java 
	* @Package com.jingren.jing.school.front.courseinfo 
	* @Description: TODO 通关方案表格
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月4日 上午11:06:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_clearance_table.html")
	public String get_clearance_table(Model model,
			@RequestParam(value = "dengji", required = false) String dengji,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "limit", required = false,defaultValue="2") Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1") Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("dengji", dengji);
		model.addAttribute("dengji", dengji);
		Integer clearance_number=clearanceService.getClearanceNumber(map);
		List<Clearance> clearances = clearanceService.getClearanceList(map);
		for (Clearance clearance : clearances) {
			map.clear();
			map.put("clearance_id", clearance.getClearance_id());
			List<ClearanceVideo> clearanceVideos=clearanceVideoService.getClearanceVideoList(map);
			clearance.setClearanceVideos(clearanceVideos);
		}
		Pagers<Clearance> pagers = new Pagers<Clearance>(clearance_number,
				pageNumber, limit);
		pagers.setList(clearances);
		model.addAttribute("clearances", pagers);
		model.addAttribute("course_id", course_id);
		return "/school/course_info/clearance_table";
	}

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 课程资讯三级页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 上午9:25:56
	 * @version 网校+CRM系统 V1.0
	 * @throws Exception
	 */
	@RequestMapping("/get_course_info_detail.html")
	public String get_course_info_detail(Model model,
			@RequestParam(value = "info_id", required = false) Integer info_id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("info_id", info_id);
		if(info_id!=null){
			CourseInfo courseInfo = courseInfoService.getCourseInfo(map);
			model.addAttribute("courseInfo", courseInfo);
			map.clear();
			map.put("course_id", courseInfo.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			String course_id_sub = DesUtil.encrypt(String.valueOf(courseInfo.getCourse_id()), "courseinfo");// des加密
			courseMenu.setSub_course_id(course_id_sub.replaceAll("\\+", "%2B"));
			model.addAttribute("courseMenu", courseMenu);
			map.put("pageNumber", 0);
			map.put("limit", 4);
			List<CourseInfo> late_update = courseInfoService.getCourseInfoList_erji(map);
			model.addAttribute("late_update", late_update);
			map.put("limit", 10);
			List<CourseInfo> late_update_hot = courseInfoService.getCourseInfoList_erji(map);
			model.addAttribute("late_update_hot", late_update_hot);
			return "/school/course_info/check_course_info";
		}else{
			return "/404";
		}
	}

	/**
	 * @Title: CourseInfoController.java
	 * @Package com.jingren.jing.school.front.courseinfo
	 * @Description: TODO 首页顶部资讯列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月16日 下午1:42:20
	 * @version 网校+CRM系统 V1.0
	 * @throws Exception
	 */
	@RequestMapping("/get_course_info_top.html")
	public String get_course_info_top(Model model) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 2);
		map.put("is_show", "是");
		List<CourseMenu> course_sub = courseMenuService.getCourserMenuList(map);
		for (CourseMenu courseMenu : course_sub) {
			String course_id_sub = DesUtil.encrypt(String.valueOf(courseMenu.getCourse_id()), "courseinfo");// des加密
			courseMenu.setSub_course_id(course_id_sub.replaceAll("\\+", "%2B"));
		}
		model.addAttribute("course_sub", course_sub);
		return "/school/index/course/course_info_top_list";
	}
	/**
	* @Title: CourseInfoController.java 
	* @Package com.jingren.jing.school.front.courseinfo 
	* @Description: TODO 资讯二级页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月25日 下午4:19:33 
	* @version 网校+CRM系统 V1.0
	 * @throws Exception 
	 */
	@RequestMapping("/get_course_info_erji_list.html")
	public String get_course_info_erji_list(Model model,
			@RequestParam(value="info_type",required=false)String info_type,
			@RequestParam(value="course_id",required=false)Integer course_id,
			@RequestParam(value = "limit", required = false,defaultValue="30") Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1") Integer pageNumber) throws Exception{
		Map<String, Object> map=new HashMap<>();
		map.put("info_type", info_type);
		map.put("course_id", course_id);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer info_number=courseInfoService.getCourseInfoNumber(map);
		List<CourseInfo> courseInfos=courseInfoService.getCourseInfoList(map);
		Pagers<CourseInfo> pagers=new Pagers<CourseInfo>(info_number, pageNumber, limit);
		pagers.setList(courseInfos);
		model.addAttribute("courseInfos", pagers);
		model.addAttribute("info_type", info_type);
		model.addAttribute("course_id", course_id);
		///////////////////////////////////////////////////////////////////////////////
		map.clear();
		map.put("course_id", course_id);
		CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
		String course_id_sub = DesUtil.encrypt(String.valueOf(course_id), "courseinfo");// des加密
		courseMenu.setSub_course_id(course_id_sub.replaceAll("\\+", "%2B"));
		model.addAttribute("courseMenu", courseMenu);
		map.put("pageNumber", 0);
		map.put("limit", 4);
		List<CourseInfo> late_update = courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("late_update", late_update);
		map.put("limit", 10);
		List<CourseInfo> late_update_hot = courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("late_update_hot", late_update_hot);
		return "/school/course_info/erji_list/course_info_erji_list";
	}
	
	/**
	* @Title: CourseInfoController.java 
	* @Package com.jingren.jing.school.front.courseinfo 
	* @Description: TODO 二级页面列表跳转
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月25日 下午6:18:08 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/jump_course_info_erji_list.html")
	public String jump_course_info_erji_list(Model model,
			@RequestParam(value="info_type",required=false)String info_type,
			@RequestParam(value="course_id",required=false)Integer course_id,
			@RequestParam(value = "limit", required = false,defaultValue="30") Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1") Integer pageNumber) throws Exception{
		Map<String, Object> map=new HashMap<>();
		map.put("info_type", info_type);
		map.put("course_id", course_id);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer info_number=courseInfoService.getCourseInfoNumber(map);
		List<CourseInfo> courseInfos=courseInfoService.getCourseInfoList(map);
		Pagers<CourseInfo> pagers=new Pagers<CourseInfo>(info_number, pageNumber, limit);
		pagers.setList(courseInfos);
		model.addAttribute("courseInfos", pagers);
		model.addAttribute("info_type", info_type);
		model.addAttribute("course_id", course_id);
		return "/school/course_info/erji_list/course_info_erji_list_page";
	}
}
