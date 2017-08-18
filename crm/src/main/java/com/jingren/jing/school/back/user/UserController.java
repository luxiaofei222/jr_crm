package com.jingren.jing.school.back.user;

import java.text.DecimalFormat;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.university.service.ReviewService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.mycourse.MyCourse;
import com.jingren.jing.school.bean.order.CourseOrder;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.bean.video.VideoRecord;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.school.service.mycourse.MyCourseService;
import com.jingren.jing.school.service.order.CourseOrderService;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.school.service.video.VideoRecordService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: UserController.java 
* @Package com.jingren.jing.school.back.user 
* @Description: TODO 网站用户管理
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月5日 下午1:59:51 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_user")
public class UserController {
	@Resource
	private UserService userService;
	@Resource
	private CourseOrderService courseOrderService;
	@Resource
	private MyCourseService myCourseService;
	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private ReviewService reviewService;
	@Resource
	private VideoRecordService videoRecordService;
	@Resource
	private EmployeeService employeeService;
	private final static String pic_user="/images/school/front/index/user_moren.png";
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 网站用户
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午2:13:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_user_main.jr")
	public String get_role_main(Model model,
			@RequestParam(value="user_sex",required=false)String user_sex,
			@RequestParam(value="user_mail",required=false)String user_mail,
			@RequestParam(value="user_phone",required=false)String user_phone,
			@RequestParam(value="user_nickname",required=false)String user_nickname,
			@RequestParam(value="user_state",required=false)String user_state,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		Integer all_user_number=userService.getUserNumber(map);
		model.addAttribute("all_user_number", all_user_number);//总用户数量
		if(StringUtils.isNotBlank(user_phone)){
			map.put("user_phone", user_phone);
			model.addAttribute("user_phone", user_phone);
		}
		if(StringUtils.isNotBlank(user_sex)){
			map.put("user_sex", user_sex);
			model.addAttribute("user_sex", user_sex);
		}
		if(StringUtils.isNotBlank(user_mail)){
			map.put("user_mail", user_mail);
			model.addAttribute("user_mail", user_mail);
		}
		if(StringUtils.isNotBlank(user_nickname)){
			map.put("user_nickname", user_nickname);
			model.addAttribute("user_nickname", user_nickname);
		}
		if(StringUtils.isNotBlank(user_state)){
			map.put("user_state", user_state);
			model.addAttribute("user_state", user_state);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<User> users=userService.getUserlist(map);
		Integer user_number=userService.getUserNumber(map);
		model.addAttribute("user_number", user_number);//当前条件用户数量
		for (User user : users) {
			map.clear();
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
				user.setIs_study(1);
			}else{
				user.setIs_study(0);
			}
		}
		Pagers<User> pagers = new Pagers<User>(user_number, pageNumber, limit);
		pagers.setList(users);
		model.addAttribute("users", pagers);
		return "/user/user_main";
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 快速添加用户的课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月9日 下午6:07:13 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/change_mycourse.jr")
	public void change_mycourse(){
		Map<String, Object> map = new HashMap<>();
		map.put("video_id", 682);
//		map.put("not_video_id", 684);
//		List<MyCourse> myCourses=myCourseService.getMyCourseList(map);
//		for (MyCourse myCourse : myCourses) {
//			MyCourse myCourse2=new MyCourse();
//			myCourse2.setUser_id(myCourse.getUser_id());
//			myCourse2.setVideo_id(684);
//			myCourse2.setMy_course_time(new Date());
//			myCourseService.saveMyCourse(myCourse2);
//			for (int i = 0; i < 2; i++) {
//				if(i==0){
//					MyCourse myCourse2=new MyCourse();
//					myCourse2.setUser_id(myCourse.getUser_id());
//					myCourse2.setVideo_id(684);
//					myCourse2.setMy_course_time(new Date());
//					myCourseService.saveMyCourse(myCourse2);
//				}else if(i==1){
//					MyCourse myCourse2=new MyCourse();
//					myCourse2.setUser_id(myCourse.getUser_id());
//					myCourse2.setVideo_id(683);
//					myCourse2.setMy_course_time(new Date());
//					myCourseService.saveMyCourse(myCourse2);
//				}
//			}
//		}
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 查看网站用户详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午2:52:13 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_user_detail.jr")
	public String get_user_detail(Model model,
			@RequestParam(value = "user_id", required = false) Integer user_id) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		User user=userService.getUser(map);
		model.addAttribute("user", user);
		map.clear();
		map.put("user_id", user_id);
		List<CourseOrder> courseOrders=courseOrderService.getCourseOrderList(map);
		double zongjin=0;
		for (CourseOrder courseOrder : courseOrders) {
			if(StringUtils.isNotBlank(courseOrder.getPay_money())){
				double moey=Double.valueOf(courseOrder.getPay_money());
				zongjin+=moey;	
			}
		}
		DecimalFormat    df   = new DecimalFormat("0.00");
		model.addAttribute("zongjin", df.format(zongjin));//消费金额
		List<MyCourse> courses=myCourseService.getMyCourseList(map);
		Iterator<MyCourse> it=courses.iterator();
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
			CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
			myCourse.setCourseVideo(courseVideo);
		}
		model.addAttribute("courses",courses);//我的课程
		return "/user/check_user";
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 打开添加我的课程窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午5:01:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_my_course.jr")
	public String get_my_course(Model model,
			@RequestParam(value = "user_id", required = false) Integer user_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("video_parent_id", 0);
		map.put("video_type", "付费");
		List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);
		if(courseVideos.size()>0){
			for (CourseVideo courseVideo : courseVideos) {//判断课程是否已加入用户
				map.clear();
				map.put("user_id", user_id);
				List<MyCourse> myCourses=myCourseService.getMyCourseList(map);
				for (MyCourse myCourse : myCourses) {
					if(myCourse.getVideo_id().equals(courseVideo.getVideo_id())){
						courseVideo.setIs_jiaru_course(true);
						break;
					}else{
						courseVideo.setIs_jiaru_course(false);
					}
				}
			}
		}
		model.addAttribute("courseVideos", courseVideos);
		model.addAttribute("user_id", user_id);
		return "/user/add_mycourse";
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 获取二级课程目录
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午5:36:25 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_course.jr")
	public String get_sub_course(Model model,
			@RequestParam(value = "course_id", required = false) Integer course_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_parent_id", course_id);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		
//		map.clear();
//		if(course_id==9){
//			map.put("course_id", courseMenus.get(0).getCourse_id());
//			List<Review> reviews=reviewService.getReviewList(map);
//			model.addAttribute("reviews", reviews);
//		}
		return "/user/sub_course";
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 获取视频列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午5:51:58 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_course_video.jr")
	public String get_course_video(Model model,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "user_id", required = false) Integer user_id,
			@RequestParam(value = "course_id", required = false) Integer course_id){
		Map<String, Object> map=new HashMap<>();
		List<CourseVideo> courseVideos=null;
		if(type==1){
			map.put("course_parent_id", course_id);
			map.put("video_level", 1);
			map.put("video_type", "付费");
			 courseVideos=courseVideoService.getCourseVideoList(map);
			model.addAttribute("courseVideos", courseVideos);
		}else if(type==2){
			map.put("course_id", course_id);
			map.put("video_level", 1);
			map.put("video_type", "付费");
			 courseVideos=courseVideoService.getCourseVideoList(map);
			model.addAttribute("courseVideos", courseVideos);
		}else{
			map.put("video_parent_id", 0);
			map.put("video_type", "付费");
			courseVideos=courseVideoService.getCourseVideoList(map);
			model.addAttribute("courseVideos", courseVideos);
		}
		if(courseVideos.size()>0){
			for (CourseVideo courseVideo : courseVideos) {//判断课程是否已加入用户
				map.clear();
				map.put("user_id", user_id);
				List<MyCourse> myCourses=myCourseService.getMyCourseList(map);
				for (MyCourse myCourse : myCourses) {
					if(myCourse.getVideo_id().equals(courseVideo.getVideo_id())){
						courseVideo.setIs_jiaru_course(true);
						break;
					}else{
						courseVideo.setIs_jiaru_course(false);
					}
				}
			}
		}
		return "/user/video_list";
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 保存我的课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月5日 下午6:35:05 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/save_my_course.jr")
	public void save_course_order(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="str",required=false)String str,
			@RequestParam(value="user_id",required=false)Integer user_id,
			CourseOrder courseOrder,HttpSession session,MyCourse myCourse) throws ParseException{
			Employee employee=(Employee) session.getAttribute("employee_session");
			String[] str_array = str.split(",");
			courseOrder.setPay_type("报名处");
			courseOrder.setUser_id(user_id);
			courseOrder.setOrder_state("已付款");
			if(str_array.length>0){
			for (int i = 0; i < str_array.length; i++) {
				Map<String, Object> map=new HashMap<>();
				if(StringUtils.isNotBlank(str_array[i])){
					Integer id=Integer.valueOf(str_array[i]);
					/*保存支付价格，根据课程的单价保存的，需求可能会变为支付价格与单价不符的情况………start………*/
					map.put("video_id", id);
					CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
					DecimalFormat    df   = new DecimalFormat("0.00");
					courseOrder.setPay_money(df.format(courseVideo.getVideo_money_now()));
					courseOrder.setPay_time(new Date());
					/*保存支付价格，根据课程的单价保存的，需求可能会变为支付价格与单价不符的情况………end………*/
					courseOrder.setVideo_id(id);//课程ID
					myCourse.setVideo_id(id);
					myCourse.setCourse_id(courseVideo.getCourse_id());
					myCourse.setCourse_parent_id(courseVideo.getCourse_parent_id());
					myCourse.setMy_course_time(new Date());
					if(courseVideo.getDaoqi_time()!=null){
						myCourse.setDaoqi_time(courseVideo.getDaoqi_time());
					}
					myCourse.setEmployee_id(employee.getEmployee_id());
					myCourse.setEmployee_name(employee.getEmployee_name());
					myCourse.setNote(employee.getEmployee_name()+"在"+CommentDate.get_date_string(new Date())+"添加了课程");
					if(courseOrderService.saveCourseOrder(courseOrder)&&myCourseService.saveMyCourse(myCourse)){
						if(str_array.length-1==i){
							ResponseUtils.renderText(response, "1");//成功
						}
					}else{
						ResponseUtils.renderText(response, "0");
					}
				}
			}
			}else{
				ResponseUtils.renderText(response, "2");
			}
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 修改用户权限
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月6日 上午8:43:43 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_user_permision.jr")
	public void update_user_permision(User user,HttpServletResponse response,
			@RequestParam(value="type",required=false)String type){
		user.setUser_state(type);
		if(userService.updateUser(user)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 解除加入课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月29日 下午1:18:06 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_mycourse.jr")
	public void delete_mycourse(HttpServletResponse response,
			@RequestParam(value="video_id",required=false)Integer video_id,
			@RequestParam(value="user_id",required=false)Integer user_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);
		map.put("user_id", user_id);
		MyCourse myCourse=myCourseService.getMyCourse(map);
		CourseOrder courseOrder=courseOrderService.getCourseOrder(map);
		courseOrderService.deleteCourseOrder(courseOrder.getOrder_id());
		map.clear();
		map.put("my_course_id", myCourse.getMy_course_id());
		myCourseService.deleteMyCourse(map);
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 检查手机号
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午12:39:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_phone.jr")
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
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 保存网站用户
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午12:41:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_user.jr")
	public void save_user(User user,HttpSession session,HttpServletResponse response,HttpServletRequest request){
		user.setUser_password(DigestUtils.shaHex(user.getUser_password()));
		user.setUser_time(new Date());
		user.setUser_ip(GetIPUtil.getIpAddress(request));
		user.setUser_pic(pic_user);
		if(StringUtils.isBlank(user.getUser_nickname())){
			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
	    	String num=format.format(new Date());
			user.setUser_nickname("JR"+num);
		}
		if(StringUtils.isNotBlank(user.getUser_phone())){
			Map<String, Object> map=new HashMap<>();
			map.put("user_phone", user.getUser_phone());
			User user2=userService.getUser(map);
			if(user2==null){
				if(userService.saveUser(user)){
					ResponseUtils.renderText(response, "1");//注册成功
				}else{
					ResponseUtils.renderText(response, "0");//注册失败
				}
			}else{
				ResponseUtils.renderText(response, "3");
			}
		}
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 添加用户
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午12:58:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_save_user.jr")
	public String to_save_user(){
		
		return "/user/add_user";
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 设置到期时间页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月14日 上午11:51:20 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_set_daoqi_time.jr")
	public String to_set_daoqi_time(Model model,
			@RequestParam(value = "my_course_id", required = false) Integer my_course_id){
				
		model.addAttribute("my_course_id", my_course_id);
		return "/user/daoqi/add_daoqi_time";
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 设置到期时间
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月14日 下午12:02:34 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_my_course_daoqi.jr")
	public void update_my_course_daoqi(MyCourse myCourse,HttpServletResponse response,HttpSession session,
			@RequestParam(value = "daoqi_time_str", required = false) String daoqi_time_str) throws ParseException{
		Map<String, Object> map=new HashMap<>();
		map.put("my_course_id", myCourse.getMy_course_id());
		MyCourse myCourse2=myCourseService.getMyCourse(map);
		if(daoqi_time_str!=null){
			Employee employee=(Employee) session.getAttribute("employee_session");
			long currentTime =  CommentDate.get_String_date(daoqi_time_str).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			myCourse.setDaoqi_time(date);
			if(myCourse2!=null){
				myCourse.setNote(myCourse2.getNote()+"/"+employee.getEmployee_name()+"在"+CommentDate.get_date_string(new Date())+"修改了到期日期。");
			}else{
				myCourse.setNote(employee.getEmployee_name()+"在"+CommentDate.get_date_string(new Date())+"修改了到期日期。");
			}
			if(myCourseService.updateMyCourse(myCourse)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}else{
			ResponseUtils.renderText(response, "2");
		}
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 绑定账号页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月26日 下午2:32:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_bangding_zhanghao.jr")
	public String to_bangding_zhanghao(Model model,
			@RequestParam(value="user_id",required=false)Integer user_id){
		Map<String, Object> map = new HashMap<>();
		map.put("employee_state", "在职");
		List<Employee> employees=employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		model.addAttribute("user_id", user_id);
		return "/user/add_zhanghao";
	}
	/**
	* @Title: UserController.java 
	* @Package com.jingren.jing.school.back.user 
	* @Description: TODO 绑定账号
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月26日 下午2:37:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_oa_employee_gangwei.jr")
	public void update_oa_employee_gangwei(User user,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", user.getEmployee_id());
		Employee employee=employeeService.getEmployee(map);
		user.setEmployee_name(employee.getEmployee_name());
		if(userService.updateUser(user)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
