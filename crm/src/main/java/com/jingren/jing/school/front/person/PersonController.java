package com.jingren.jing.school.front.person;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.city.bean.City;
import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.school.bean.course.CourseInfo;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.courseware.CourseWare;
import com.jingren.jing.school.bean.courseware.MyCourseWare;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.mycourse.MyCourse;
import com.jingren.jing.school.bean.order.CourseOrder;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.bean.video.VideoRecord;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.service.entrycondition.EntryConditionService;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.entryplace.EntryPlaceService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.service.course.CourseInfoService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.courseware.CourseWareService;
import com.jingren.jing.school.service.courseware.MyCourseWareService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.mycourse.MyCourseService;
import com.jingren.jing.school.service.order.CourseOrderService;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.school.service.video.VideoRecordService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Mobilecode;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import net.sf.json.JSONArray;

/**
 * 个人中心
 * 
 * @Title: PersonController.java
 * @Package com.jingren.jing.school.front.person
 * @Description: TODO
 * @author 鲁晓飞 MR.Lu
 * @date 2016年11月25日 上午11:30:00
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("person")
public class PersonController {

	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private MyCourseService myCourseService;
	@Resource
	private CourseOrderService courseOrderService;
	@Resource
	private UserService userService;
	@Resource
	private CityService cityService;
	@Resource
	private CourseWareService courseWareService;
	@Resource
	private MyCourseWareService myCourseWareService;
	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private EntryConditionService entryConditionService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private EntryPlaceService entryPlaceService;
	@Resource
	private EntryPlanService entryPlanService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private CourseInfoService courseInfoService;
	@Resource
	private UniversityService universityService;
	@Resource
	private VideoRecordService videoRecordService;
	private Log logger = LogFactory.getLog(getClass());

	/**
	 * 个人中心首页
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月25日 下午12:59:39
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_person_index.html")
	public String get_person_index(Model model, HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", user.getUser_id());
			User user2 = userService.getUser(map);
			model.addAttribute("user", user2);
			return "/school/person/person_index";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			return "/school/login/login";
		}
	}

	/**
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 获取我的课程列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月25日 下午1:25:46
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_mycourse.html")
	public String get_mycourse(Model model, HttpSession session,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", user.getUser_id());
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
			List<MyCourse> myCourses = myCourseService.getMyCourseList(map);
			Integer my_course_number = myCourseService.getMyCourseNumber(map);
			if (myCourses.size() > 0) {
				Iterator<MyCourse> it=myCourses.iterator();
				while (it.hasNext()) {
					MyCourse myCourse=it.next();
					if(myCourse.getDaoqi_time()!=null){
						int tianshu=CommentDate.daysBetween(new Date(), myCourse.getDaoqi_time());
						myCourse.setTianshu(tianshu);
						if(tianshu<0){
							map.clear();
							map.put("my_course_id", myCourse.getMy_course_id());
							myCourseService.deleteMyCourse(map);
							it.remove();
						}
					}
					map.clear();
					map.put("video_id", myCourse.getVideo_id());
					CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
					map.clear();
					map.put("video_parent_id", myCourse.getVideo_id());
					List<CourseVideo> courseVideos2 = courseVideoService.getCourseVideoList(map);
					Integer course_num = 0;
					for (CourseVideo courseVideo2 : courseVideos2) {
						map.clear();
						map.put("video_parent_id", courseVideo2.getVideo_id());
						map.put("video_level", 3);
						course_num += courseVideoService.getCourseVideoNumber(map);
					}
					courseVideo.setKeshi_number(course_num);// 课时数
					myCourse.setCourseVideo(courseVideo);
					map.clear();
					map.put("video_parent", myCourse.getVideo_id());
					map.put("user_id", user.getUser_id());
					List<VideoRecord> videoRecords=videoRecordService.getVideoRecordList(map);
					if(videoRecords.size()>0){
						for (VideoRecord videoRecord : videoRecords) {
							int shichang=Integer.valueOf(videoRecord.getZong_shichang())-Integer.valueOf(videoRecord.getRecord_time());
							if(shichang<8){
								videoRecord.setRecord_time(videoRecord.getZong_shichang());
								videoRecordService.updateVideoRecord(videoRecord);
							}
						}
						map.clear();
						map.put("video_id", videoRecords.get(0).getVideo_id());
						CourseVideo courseVideore=courseVideoService.getCourseVideo(map);
						videoRecords.get(0).setCourseVideo(courseVideore);
						map.clear();
						map.put("video_id", videoRecords.get(0).getVideo_parent_id());
						CourseVideo courseVideopa=courseVideoService.getCourseVideo(map);
						videoRecords.get(0).setParent_video(courseVideopa);
						myCourse.setVideoRecord(videoRecords.get(0));
					}
					
				}
			}
			Pagers<MyCourse> pagers = new Pagers<MyCourse>(my_course_number, pageNumber, limit);
			pagers.setList(myCourses);
			model.addAttribute("myCourses", pagers);
			map.clear();
			map.put("pageNumber", 0);
			map.put("limit", 20);
			List<CourseInfo> courseInfos = courseInfoService.getCourseInfoList(map);
			model.addAttribute("courseInfos", courseInfos);
			return "/school/person/mycourse";
		} else {
			return "/404";
		}
	}

	/**
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 获取我的订单
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月25日 下午3:04:16
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_mycourse_order.html")
	public String get_mycourse_order(Model model, HttpSession session,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", user.getUser_id());
			map.put("is_show", "显示");
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
			List<CourseOrder> courseOrders_check = courseOrderService.getCourseOrderList(map);
			for (CourseOrder courseOrder : courseOrders_check) {
				if (courseOrder.getOrder_state().equals("待付款")) {
					courseOrderService.deleteCourseOrder(courseOrder.getOrder_id());
//					long time = System.currentTimeMillis() - courseOrder.getOrder_time().getTime();
//					if (time > 48 * 60 * 60 * 1000) {// 超过48小时，删除订单
//						courseOrder.setIs_show("删除");
//						courseOrderService.updateCourseOrder(courseOrder);
//					}
				}
			}
			List<CourseOrder> courseOrders = courseOrderService.getCourseOrderList(map);
			Integer order_number = courseOrderService.getCouseOrderNumber(map);
			for (CourseOrder courseOrder : courseOrders) {
				map.clear();
				map.put("video_id", courseOrder.getVideo_id());
				CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
				map.clear();
				map.put("video_parent_id", courseOrder.getVideo_id());
				List<CourseVideo> courseVideos2 = courseVideoService.getCourseVideoList(map);

				int course_num = 0;
				if(courseVideos2.size()>0){
					for (CourseVideo courseVideo2 : courseVideos2) {
						map.clear();
						map.put("video_parent_id", courseVideo2.getVideo_id());
						map.put("video_level", 3);
						course_num += courseVideoService.getCourseVideoNumber(map);
					}
				}
				courseVideo.setKeshi_number(course_num);// 课时数
				courseOrder.setCourseVideo(courseVideo);
			}
			Pagers<CourseOrder> pagers = new Pagers<CourseOrder>(order_number, pageNumber, limit);
			pagers.setList(courseOrders);
			model.addAttribute("courseOrders", pagers);
			return "/school/person/mycourseorder";
		} else {
			return "/404";
		}
	}

	/**
	 * 获取修改用户信息页面
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月25日 下午4:48:54
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_update_person.html")
	public String get_update_person(Model model, @RequestParam(value = "user_id", required = false) Integer user_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		User user = userService.getUser(map);
		map.clear();
		map.put("leveltype", 1);
		List<City> cities = cityService.getCityList(map);
		if (user != null) {
			if(user.getUser_birthday()!=null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				model.addAttribute("biethday", sdf.format(user.getUser_birthday()));
			}
			model.addAttribute("user", user);
			model.addAttribute("cities", cities);
			return "/school/person/update_person";
		} else {
			return "/404";
		}
	}

	/**
	 * 获取城市
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月25日 下午5:22:59
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_city_erji.html")
	public void get_city_erji(HttpServletResponse response, @RequestParam(value = "id", required = false) Integer id) {
		try {
			response.setCharacterEncoding("UTF-8");
			Map<String, Object> map = new HashMap<>();
			map.put("parentid", id);
			List<City> cities = cityService.getCityList(map);
			JSONArray json = JSONArray.fromObject(cities);
			String jsonStr = json.toString();
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	* @Title: PersonController.java 
	* @Package com.jingren.jing.school.front.person 
	* @Description: TODO 通过那么查询
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月8日 下午5:53:49 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_city_erji_byanme.html")
	public void get_city_erji_byanme(HttpServletResponse response, 
			@RequestParam(value = "id", required = false) String id) {
		try {
			response.setCharacterEncoding("UTF-8");
			Map<String, Object> map = new HashMap<>();
			map.put("name", id);
			City city=cityService.getCity(map);
			map.clear();
			map.put("parentid", city.getId());
			List<City> cities = cityService.getCityList(map);
			JSONArray json = JSONArray.fromObject(cities);
			String jsonStr = json.toString();
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改用户
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月25日 下午6:01:04
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/update_person.html")
	public void update_person(User user, HttpServletResponse response,
			@RequestParam(value = "birthday", required = false) String birthday,
			@RequestParam(value = "id", required = false) Integer id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		if (id != 0 && id != 1) {
			map.put("id", id);
			City city = cityService.getCity(map);
			user.setUser_province(city.getName());
		} else if (id == 1) {
			System.out.println("地区不变");
		} else {
			user.setUser_city(null);
		}
		if (StringUtils.isNotBlank(birthday)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(birthday);
			user.setUser_birthday(date);
		}
		if (userService.updateUser(user)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * 修改绑定邮箱或者手机号
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月28日 上午9:02:03
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_bangding.html")
	public void update_bangding(User user, HttpServletResponse response) {
		if (userService.updateUserMailAndPhone(user)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * 我的课件
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月28日 上午10:27:22
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_my_courseware.html")
	public String get_my_courseware(Model model, HttpSession session,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", user.getUser_id());
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
			List<MyCourseWare> myCourseWares = myCourseWareService.getMyCourseWareList(map);
			Integer mycourseWareNumber = myCourseWareService.getMyCourseWareNumber(map);
			if (myCourseWares.size() > 0) {
				for (MyCourseWare myCourseWare : myCourseWares) {
					map.clear();
					map.put("courseware_id", myCourseWare.getCourseware_id());
					CourseWare courseWare = courseWareService.getCourseWare(map);
					myCourseWare.setCourseWare(courseWare);
				}
			}
			Pagers<MyCourseWare> pagers = new Pagers<MyCourseWare>(mycourseWareNumber, pageNumber, limit);
			pagers.setList(myCourseWares);
			model.addAttribute("myCourseWares", pagers);
			return "/school/person/mycourseware";
		} else {
			return "/404";
		}
	}

	/**
	 * 获取发送消息页面
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月28日 上午11:40:51
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_message_main.html")
	public String get_message_main() {

		return "/school/person/message";
	}

	/**
	 * 检测手机号
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月28日 下午3:43:05
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_phone.html")
	public void chech_phone(HttpServletResponse response,
			@RequestParam(value = "user_phone", required = false) String user_phone) {
		if (StringUtils.isNotBlank(user_phone)) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_phone", user_phone);
			User user = userService.getUser(map);
			if (user != null) {
				// 手机号已经被注册
				ResponseUtils.renderText(response, "2");
			} else {
				ResponseUtils.renderText(response, "1");
			}
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * 发送验证码
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月28日 下午3:46:32
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/send_phone.html")
	public void send_phone(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "user_phone", required = false) String user_phone) {
		try {
			String phone_code = Mobilecode.phone_code(user_phone);
			System.out.println(phone_code);
			session.removeAttribute("phone_code");
			session.setAttribute("phone_code", phone_code);
			ResponseUtils.renderText(response, "1");
		} catch (IOException e) {
			ResponseUtils.renderText(response, "0");
			e.printStackTrace();
		}
	}

	/**
	 * 验证手机验证码
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月28日 下午3:51:25
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_phone_code.html")
	public void check_phone_code(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "phone_code", required = false) String phone_code) {
		if (phone_code != null) {
			String code = (String) session.getAttribute("phone_code");
			if (code != null && code.equals(phone_code)) {
				ResponseUtils.renderText(response, "1");// 验证通过
			} else {
				ResponseUtils.renderText(response, "2");// 验证码失效
			}
		} else {
			ResponseUtils.renderText(response, "0");// 验证码空
		}
	}

	/**
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 头部跳转到我的课程
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 上午8:43:23
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_head_mycourse.html")
	public String get_hean_mycourse(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			return "/school/person/person_mycourse";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			return "/school/login/login";
		}
	}

	/**
	 *
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 头部跳转到我的订单
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 上午9:01:13
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_head_myorder.html")
	public String get_hean_myorder(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			return "/school/person/person_myorder";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			return "/school/login/login";
		}
	}

	/**
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 密码与安全第一步页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 上午11:12:00
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_safe_first.html")
	public String get_safe_first(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			model.addAttribute("user", user);
			logger.info(user.getUser_nickname() + "进入安全与密码页面！///" + new Date());
			return "/school/person/safe/safe_first";
		} else {
			logger.info("用户登录超时或者退出" + new Date());
			return "/404";
		}
	}

	/**
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 密码与安全第二步页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 上午10:24:05
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_safe_second.html")
	public String get_safe_second(HttpSession session, Model model,
			@RequestParam(value = "type", required = false) String type) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			model.addAttribute("user", user);
			model.addAttribute("type", type);
			logger.info(user.getUser_nickname() + "进入安全与密码第二步页面！///" + new Date());
			return "/school/person/safe/safe_second";
		} else {
			logger.info("用户登录超时或者退出" + new Date());
			return "/404";
		}
	}

	/**
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 验证安全与密码验证码
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 上午11:17:26
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_safe_code.html")
	public void check_safe_code(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "code", required = false) String code) {
		String phone_code = (String) session.getAttribute("phone_code");
		Integer mail_code = (Integer) session.getAttribute("mailcode");
		if (StringUtils.isNotBlank(code)) {
			if (phone_code != null) {
				if (code.equals(phone_code)) {
					ResponseUtils.renderText(response, "1");
				} else {
					ResponseUtils.renderText(response, "2");
				}
			} else {
				if (code.equals(String.valueOf(mail_code))) {
					ResponseUtils.renderText(response, "1");
				} else {
					ResponseUtils.renderText(response, "2");
				}
			}
		} else {
			ResponseUtils.renderText(response, "0");// 验证码为空
		}
	}

	/**
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 获取第三步安全与密码的修改密码页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 上午11:26:45
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_safe_third.html")
	public String get_safe_third(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			model.addAttribute("user", user);
			session.removeAttribute("mailcode");
			session.removeAttribute("phone_code");
			logger.info(user.getUser_nickname() + "进入安全与密码修改密码页面！///" + new Date());
			return "/school/person/safe/safe_third";
		} else {
			logger.info("用户登录超时或者退出" + new Date());
			return "/404";
		}
	}

	/**
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 安全与密码修改密码
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 上午11:47:14
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_safe_pass.html")
	public void update_safe_pass(HttpServletResponse response, User user,
			@RequestParam(value = "new_password", required = false) String new_password) {
		user.setUser_password(DigestUtils.shaHex(new_password));
		if (userService.updateUser(user)) {
			ResponseUtils.renderText(response, "1");// 修改成功
		} else {
			ResponseUtils.renderText(response, "0");// 修改失败
		}
	}

	/**
	 * 
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 修改密码成功页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 上午11:48:38
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_safe_success.html")
	public String get_safe_success() {
		logger.info("用户修改密码成功！");
		return "/school/person/safe/safe_succ";
	}

	/**
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 跳转安全与密码页面-头部
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年11月29日 下午12:56:38
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_person_safe.html")
	public String get_person_safe(HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			return "/school/person/safe/person_safe";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			return "/school/login/login";
		}
	}

	/**
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 我的报名信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月27日 下午1:07:49
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_info.html")
	public String get_entry_info(Model model, HttpSession session, HttpServletRequest request) {
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("userId", user.getUser_id());
			map.put("baokao_qudao", "网校");
			List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
			for (EntryInfo entryInfo : entryInfos) {
				if (StringUtils.isBlank(entryInfo.getDocumentNumber())) {
					entryInfoService.deleteEntryInfo(entryInfo.getEntryInfoId());
				}
				map.clear();
				map.put("entryInfoId", entryInfo.getEntryInfoId());
				map.clear();
				map.put("entrycondition_id", entryInfo.getEntrycondition_id());
				EntryCondition condition = entryConditionService.getEntryCondition(map);
				entryInfo.setCondition(condition);
				map.clear();
				map.put("course_id", condition.getCourse_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				entryInfo.setCourseMenu(courseMenu);
				if (condition.getDictionary_id() != null) {
					map.clear();
					map.put("dictionary_id", condition.getDictionary_id());
					Dictionary dictionary = dictionaryService.getDictionary(map);
					entryInfo.setDictionary(dictionary);
				}
				if (condition.getCourse_id() == 20) {
					map.clear();// 学校
					map.put("university_id", condition.getUniversity_id());
					University university = universityService.getUniversity(map);
					entryInfo.setXuexiao(university.getUniversity_name());

					map.clear();// 专业
					map.put("university_id", entryInfo.getZhuanye_id());
					University university2 = universityService.getUniversity(map);
					entryInfo.setZhuanye(university2.getUniversity_zhuanye());

				}
			}
			model.addAttribute("entryInfos", entryInfos);
			return "/school/person/myentryinfo";
		} else {
			String url = request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			return "/school/login/login";
		}
	}

	/**
	 * @Title: PersonController.java
	 * @Package com.jingren.jing.school.front.person
	 * @Description: TODO 切换头像弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月3日 上午8:59:20
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_change_user_pic.html")
	public String to_change_user_pic(Model model, @RequestParam(value = "user_id", required = false) Integer user_id) {
		model.addAttribute("user_id", user_id);
		return "/school/person/change_user_pic/user_pic";
	}

	/**
	* @Title: PersonController.java 
	* @Package com.jingren.jing.school.front.person 
	* @Description: TODO 修改用户头像
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月3日 上午9:15:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_person_pic.html")
	public void update_person_pic(User user,HttpServletResponse response,HttpServletRequest request,
			//@RequestParam(value="upload_file",required=false) MultipartFile upload_file,
			@RequestParam(value="headpic",required=false) String headpic){
		if(StringUtils.isNotBlank(headpic)){
			user.setUser_pic(headpic);
			if(userService.updateUser(user)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}else{
			ResponseUtils.renderText(response, "2");//没有剪切
		}
	}
	/**
	* @Title: PersonController.java 
	* @Package com.jingren.jing.school.front.person 
	* @Description: TODO删除订单
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月16日 下午3:13:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_order.html")
	public void delete_order(HttpServletResponse response,CourseOrder courseOrder,
			@RequestParam(value="order_id",required=false) String order_id){
		courseOrder.setIs_show("删除");
		if(courseOrderService.updateCourseOrder(courseOrder)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
}
}
