package com.jingren.jing.conult.controller.conultinfo;

import java.text.ParseException;
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

import com.jingren.jing.common.city.bean.City;
import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.common.university.service.ReviewService;
import com.jingren.jing.conult.bean.conultinfo.NetConult;
import com.jingren.jing.conult.bean.record.NetConultRecord;
import com.jingren.jing.conult.bean.track.ConTrack;
import com.jingren.jing.conult.dao.record.NetConultRecordMapper;
import com.jingren.jing.conult.service.conultinfo.NetConultService;
import com.jingren.jing.conult.service.record.NetConultRecordService;
import com.jingren.jing.conult.service.track.ConTrackService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("net_conult")
public class NetConultController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private ReviewService reviewService;
	@Resource
	private NetConultService netConultService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private CityService cityService;
	@Resource
	private ConTrackService conTrackService;
	@Resource
	private NetConultRecordService NetConultRecordService;

	/**
	 * @Title: NetConultController.java
	 * @Package com.jingren.jing.conult.controller.conultinfo
	 * @Description: TODO 客户咨询信息——网校入口
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月1日 下午5:00:15
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/get_net_conult_main.jr")
	public String get_net_conult_main(Model model,
			@RequestParam(value = "user_sex", required = false) final String user_sex,
			@RequestParam(value = "user_name", required = false) final String user_name,
			@RequestParam(value = "user_phone", required = false) final String user_phone,
			@RequestParam(value = "consult_type", required = false) final String consult_type,
			@RequestParam(value = "search_word", required = false) final String search_word,
			@RequestParam(value = "hope_sc", required = false) final String hope_sc,
			@RequestParam(value = "zhuanye", required = false) final String zhuanye,
			@RequestParam(value = "city", required = false) final String city,
			@RequestParam(value = "course_id", required = false) final Integer course_id,
			@RequestParam(value = "course_parent_id", required = false) final Integer course_parent_id,
			@RequestParam(value = "review_id", required = false) final Integer review_id,
			@RequestParam(value = "employee_id", required = false) final Integer employee_id,
			@RequestParam(value = "bumen_id", required = false) final Integer bumen_id,
			@RequestParam(value = "consult_start_time", required = false) final String consult_start_time,
			@RequestParam(value = "consult_end_time", required = false) final String consult_end_time,
			@RequestParam(value = "zixun_start_time", required = false) final String zixun_start_time,
			@RequestParam(value = "zixun_end_time", required = false) final String zixun_end_time,
			@RequestParam(value = "limit", required = false, defaultValue = "20") final Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") final Integer pageNumber)
					throws ParseException {
		Map<String, Object> map = new HashMap<>();
		/****************** 这么多特么的筛选条件我也是醉了，不过这是我写的，就不吐槽了 *******************/
		if (StringUtils.isNotBlank(user_sex)) {// 用户性别
			map.put("user_sex", user_sex);
			model.addAttribute("user_sex", user_sex);
		}

		if (StringUtils.isNotBlank(user_name)) {// 用户姓名
			map.put("user_name", user_name);
			model.addAttribute("user_name", user_name);
		}

		if (StringUtils.isNotBlank(user_phone)) {// 用户手机号
			map.put("user_phone", user_phone);
			model.addAttribute("user_phone", user_phone);
		}

		if (StringUtils.isNotBlank(consult_type)) {// 咨询方式
			map.put("consult_type", consult_type);
			model.addAttribute("consult_type", consult_type);
		}

		if (StringUtils.isNotBlank(search_word)) {// 搜索关键词
			map.put("search_word", search_word);
			model.addAttribute("search_word", search_word);
		}

		if (StringUtils.isNotBlank(hope_sc)) {// 意向学校
			map.put("hope_sc", hope_sc);
			model.addAttribute("hope_sc", hope_sc);
		}

		if (StringUtils.isNotBlank(zhuanye)) {// 意向专业
			map.put("zhuanye", zhuanye);
			model.addAttribute("zhuanye", zhuanye);
		}
		if (StringUtils.isNotBlank(city)) {// 用户所在城市
			map.put("city", city);
			model.addAttribute("city", city);
		}

		if (course_id != null) {// 类别
			map.put("course_id", course_id);
			model.addAttribute("course_id", course_id);
		}

		if (course_parent_id != null) {// 类别-父类
			map.put("course_parent_id", course_parent_id);
			model.addAttribute("course_parent_id", course_parent_id);
		}
		if (review_id != null) {// 职称类别
			map.put("review_id", review_id);
			model.addAttribute("review_id", review_id);
		}
		if (employee_id != null) {// 员工ID
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (bumen_id != null) {// 所在部门
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}

		if (StringUtils.isNotBlank(consult_start_time) && StringUtils.isNotBlank(consult_end_time)) {// 录入的开始时间和结束时间
			map.put("consult_start_time", CommentDate.get_String_date(consult_start_time));
			long currentTime = CommentDate.get_String_date(consult_end_time).getTime();
			currentTime += 24 * 60 * 60 * 1000 - 1000;// 加23小时59分59秒
			Date date = new Date(currentTime);
			map.put("consult_end_time", date);
			model.addAttribute("consult_start_time", consult_start_time);
			model.addAttribute("consult_end_time", consult_end_time);
		}

		if (StringUtils.isNotBlank(zixun_start_time) && StringUtils.isNotBlank(zixun_end_time)) {// 咨询的开始时间和结束时间
			map.put("zixun_start_time", CommentDate.get_String_date(zixun_start_time));
			long currentTime = CommentDate.get_String_date(zixun_end_time).getTime();
			currentTime += 24 * 60 * 60 * 1000 - 1000;// 加23小时59分59秒
			Date date = new Date(currentTime);
			map.put("zixun_end_time", date);
			model.addAttribute("zixun_start_time", zixun_start_time);
			model.addAttribute("zixun_end_time", zixun_end_time);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		model.addAttribute("limit", limit);

		Integer shai_conult_number = netConultService.getNetConultNumber(map);
		List<NetConult> netConults = netConultService.getNetConultList(map);
		for (NetConult netConult : netConults) {
			if (netConult.getCourse_id() != null) {
				map.clear();// 分类名称
				map.put("course_id", netConult.getCourse_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				netConult.setCourse_name(courseMenu.getCourse_name());
			}

			if (netConult.getCourse_parent_id() != null) {
				map.clear();// 父类名称
				map.put("course_id", netConult.getCourse_parent_id());
				CourseMenu courseMenu2 = courseMenuService.getCouerseMenu(map);
				netConult.setCourse_parent_name(courseMenu2.getCourse_name());
			}

			if (netConult.getDictionary_id() != null) {
				map.clear();// 等级名称
				map.put("dictionary_id", netConult.getDictionary_id());
				Dictionary dictionary = dictionaryService.getDictionary(map);
				netConult.setDictionary_name(dictionary.getDictionary_name());
			}

		}
		Pagers<NetConult> pagers = new Pagers<NetConult>(shai_conult_number, pageNumber, limit);
		pagers.setList(netConults);
		model.addAttribute("netConults", pagers);
		model.addAttribute("shai_conult_number", shai_conult_number);// 当前条件的客户信息数量
		map.clear();
		Integer all_conult_number = netConultService.getNetConultNumber(map);
		model.addAttribute("all_conult_number", all_conult_number);// 所有客户信息数量
		/******************************** 条件 ************************************/
		map.clear();
		map.put("zuoxi", "zuoxi");
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		map.clear();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
		return "/conult/net_conult/net_conult";
	}

	/**
	 * @Title: NetConultController.java
	 * @Package com.jingren.jing.conult.controller.conultinfo
	 * @Description: TODO 去添加客户信息页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月2日 上午8:25:21
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_save_conult.jr")
	public String to_save_conult(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.clear();
		map.put("zuoxi", "zuoxi");
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		for (Employee employee : employees) {
			map.clear();
			map.put("employee_id", employee.getEmployee_id());
			Integer info_number = netConultService.getNetConultNumber(map);
			employee.setCall_number(info_number);
		}
		model.addAttribute("employees", employees);
		map.clear();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
//		map.clear();// 省份
//		map.put("leveltype", 1);
//		List<City> cities = cityService.getCityList(map);
//		model.addAttribute("cities", cities);
		return "/conult/net_conult/add_conult";
	}

	/**
	 * @Title: NetConultController.java
	 * @Package com.jingren.jing.conult.controller.conultinfo
	 * @Description: TODO 保存客户信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月2日 上午11:27:42
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/save_conult.jr")
	public void save_conult(NetConult netConult, HttpServletResponse response,
			@RequestParam(value = "zixun_time_str", required = false) final String zixun_time_str)
					throws ParseException {
		netConult.setConsult_time(new Date());
		Map<String, Object> map = new HashMap<>();
		map.clear();
		if (netConult.getEmployee_id() != null) {
			map.put("employee_id", netConult.getEmployee_id());
			Employee employee = employeeService.getEmployee(map);
			netConult.setBumen_id(employee.getBumen_id());
			netConult.setEmployee_name(employee.getEmployee_name());
		}
		if (StringUtils.isNotBlank(zixun_time_str)) {
			netConult.setZixun_time(CommentDate.get_String_date(zixun_time_str));
		}
		if (netConultService.saveNetConult(netConult)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 删除客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月2日 下午12:01:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_conult.jr")
	public void  delete_conult(HttpServletResponse response,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		for (String string : array) {
			int id=Integer.valueOf(string);
			netConultService.deleteNetConult(id);
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 查看客户信息详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月4日 上午8:37:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_net_conult.jr")
	public String  to_check_net_conult(Model model,
			@RequestParam(value="consult_id",required=false)Integer consult_id){
		Map<String, Object> map=new HashMap<>();
		map.put("consult_id", consult_id);
		NetConult netConult=netConultService.getNetConult(map);
		map.clear();
		map.put("course_id", netConult.getCourse_id());
		if(netConult.getCourse_id()!=null){
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			if(netConult.getCourse_id()==9){//职称
				map.clear();
				map.put("review_id", netConult.getReview_id());
				Review review=reviewService.getReview(map);
				netConult.setCourse_name(courseMenu.getCourse_name()+"-"+review.getReview_name());
			}else{
				netConult.setCourse_name(courseMenu.getCourse_name());
			}
			if(netConult.getDictionary_id()!=null){
				map.clear();
				map.put("dictionary_id", netConult.getDictionary_id());
				Dictionary dictionary=dictionaryService.getDictionary(map);
				netConult.setDictionary_name(dictionary.getDictionary_name());
			}
		}
		model.addAttribute("netConult", netConult);
		return "/conult/net_conult/check_conult";
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 检测手机号
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月4日 下午2:19:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_UserPhone.jr")
	public void check_UserPhone(HttpServletResponse response,
			@RequestParam(value = "user_phone", required = false) String user_phone){
		Map<String, Object> map=new HashMap<>();
		map.put("user_phone", user_phone);
		NetConult netConult=netConultService.getNetConult(map);
		if(netConult!=null){
			ResponseUtils.renderText(response, "0");
		}else{
			ResponseUtils.renderText(response, "1");
		}
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 去修改客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月4日 下午4:37:50 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_conult.jr")
	public String  to_update_conult(Model model,
			@RequestParam(value="consult_id",required=false)Integer consult_id){
		Map<String, Object> map=new HashMap<>();
		map.put("consult_id", consult_id);
		NetConult netConult=netConultService.getNetConult(map);
		if(netConult.getCourse_parent_id()!=null){
			map.clear();
			map.put("course_id", netConult.getCourse_parent_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			model.addAttribute("course_parent_name", courseMenu.getCourse_name());
		}
		if( netConult.getCourse_id()!=null){
			map.clear();
			map.put("course_id", netConult.getCourse_id());
			CourseMenu courseMenu2=courseMenuService.getCouerseMenu(map);
			model.addAttribute("course_name", courseMenu2.getCourse_name());
			model.addAttribute("netConult", netConult);
		}
		if(netConult.getReview_id()!=null){
			map.clear();
			map.put("review_id", netConult.getReview_id());
			Review review=reviewService.getReview(map);
			model.addAttribute("review_name", review.getReview_name());
		}
		
		if(netConult.getDictionary_id()!=null){
			map.clear();
			map.put("dictionary_id", netConult.getDictionary_id());
			Dictionary dictionary=dictionaryService.getDictionary(map);
			model.addAttribute("dictionary_name", dictionary.getDictionary_name());
		}
		
		if(netConult.getEmployee_id()!=null){
			map.clear();
			map.put("employee_id", netConult.getEmployee_id());
			Employee employee=employeeService.getEmployee(map);
			model.addAttribute("employee_name", employee.getEmployee_name());
		}
		
		model.addAttribute("netConult", netConult);
		map.clear();
		map.put("zuoxi", "zuoxi");
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		for (Employee employee : employees) {
			map.clear();
			map.put("employee_id", employee.getEmployee_id());
			Integer info_number = netConultService.getNetConultNumber(map);
			employee.setCall_number(info_number);
		}
		model.addAttribute("employees", employees);
		map.clear();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
//		map.clear();// 省份
//		map.put("leveltype", 1);
//		List<City> cities = cityService.getCityList(map);
//		model.addAttribute("cities", cities);
		return "/conult/net_conult/update_conult";
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 修改客户信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月4日 下午4:38:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_conult.jr")
	public void update_conult(NetConult netConult, HttpServletResponse response,
			@RequestParam(value = "zixun_time_str", required = false) final String zixun_time_str)
					throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.clear();
		if (netConult.getEmployee_id() != null) {
			map.put("employee_id", netConult.getEmployee_id());
			Employee employee = employeeService.getEmployee(map);
			netConult.setBumen_id(employee.getBumen_id());
			netConult.setEmployee_name(employee.getEmployee_name());
		}
		if (StringUtils.isNotBlank(zixun_time_str)) {
			netConult.setZixun_time(CommentDate.get_String_date(zixun_time_str));
		}
		if (netConultService.updateNetConult(netConult)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 客户信息追踪记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月7日 下午3:21:50 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_net_conult_track.jr")
	public String to_net_conult_track(Model model,
			@RequestParam(value="consult_id",required=false)Integer consult_id){
		Map<String, Object> map=new HashMap<>();
		map.put("consult_id", consult_id);
		List<ConTrack> conTracks=conTrackService.getConTrackList(map);
		for (ConTrack conTrack : conTracks) {
			map.clear();
			map.put("employee_id", conTrack.getEmployee_id());
			Employee employee=employeeService.getEmployee(map);
			conTrack.setEmployee(employee);
		}
		model.addAttribute("conTracks", conTracks);
		return "/conult/net_conult/conult_track_list";
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 查看通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月7日 下午3:25:29 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_net_conult_call_record.jr")
	public String to_net_conult_call_record(Model model,HttpServletRequest request,
			@RequestParam(value="consult_id",required=false)Integer consult_id){
		Map<String, Object> map=new HashMap<>();
		map.put("consult_id", consult_id);
		List<NetConultRecord> netConultRecords=NetConultRecordService.getNetConultRecordList(map);
		for (NetConultRecord netConultRecord : netConultRecords) {
			map.clear();
			map.put("employee_id", netConultRecord.getEmployee_id());
			Employee employee=employeeService.getEmployee(map);
			netConultRecord.setEmployee(employee);
		}
		model.addAttribute("records", netConultRecords);
		String ip=GetIPUtil.getIpAddress(request);
		if(ip.equals("110.249.251.98")||ip.equals("127.0.0.1")){
			model.addAttribute("is_ip", "1");
		}else{
			model.addAttribute("is_ip", "0");
		}
		return "/conult/net_conult/check_conult_call_record";
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 专业咨询顾问页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月8日 上午9:19:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_employee_conult.jr")
	public String to_update_employee_conult(Model model,HttpServletRequest request,
			@RequestParam(value="consult_id",required=false)Integer consult_id){
		model.addAttribute("consult_id", consult_id);
		Map<String, Object> map=new HashMap<>();
		map.put("zuoxi", "zuoxi");
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		return "/conult/net_conult/update_employee";
	}
	/**
	* @Title: NetConultController.java 
	* @Package com.jingren.jing.conult.controller.conultinfo 
	* @Description: TODO 转移业务咨询顾问
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月8日 上午9:23:24 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_net_conult_employee.jr")
	public void update_net_conult_employee(NetConult netConult,HttpServletResponse response){
		Map<String, Object> map = new HashMap<>();
		map.clear();
		if (netConult.getEmployee_id() != null) {
			map.put("employee_id", netConult.getEmployee_id());
			Employee employee = employeeService.getEmployee(map);
			netConult.setBumen_id(employee.getBumen_id());
			netConult.setEmployee_name(employee.getEmployee_name());
			netConult.setConult_state(0);
		}
		if(netConultService.updateNetConult(netConult)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
