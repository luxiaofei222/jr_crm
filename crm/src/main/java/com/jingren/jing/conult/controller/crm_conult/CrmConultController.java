package com.jingren.jing.conult.controller.crm_conult;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.common.university.service.ReviewService;
import com.jingren.jing.conult.bean.conultinfo.NetConult;
import com.jingren.jing.conult.bean.record.NetConultRecord;
import com.jingren.jing.conult.bean.track.ConTrack;
import com.jingren.jing.conult.service.conultinfo.NetConultService;
import com.jingren.jing.conult.service.record.NetConultRecordService;
import com.jingren.jing.conult.service.track.ConTrackService;
import com.jingren.jing.crm.bean.companyrecord.BusinessCallRecord;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.bean.setcalltime.SetCallTime;
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

import net.sf.json.JSONObject;

/**
 * @Title: CrmConultController.java
 * @Package com.jingren.jing.conult.controller.crm_conult
 * @Description: TODO crm获取网询客户信息
 * @author 鲁晓飞 MR.Lu
 * @date 2017年8月4日 下午2:38:06
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("crm_conult")
public class CrmConultController {
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
	private ConTrackService conTrackService;
	@Resource
	private NetConultRecordService netConultRecordService;

	/**
	 * @Title: CrmConultController.java
	 * @Package com.jingren.jing.conult.controller.crm_conult
	 * @Description: TODO 业务员查看客户信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月4日 下午5:06:36
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_crm_net_conult_main.jr")
	public String get_crm_net_conult_main(Model model, HttpSession session, HttpServletRequest request,
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
		Employee employee = (Employee) session.getAttribute("employee_session");
		map.put("employee_id", employee.getEmployee_id());
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
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
		String ip = GetIPUtil.getIpAddress(request);
		if (ip.equals("110.249.251.98") || ip.equals("127.0.0.1")) {
			model.addAttribute("is_ip", "1");
		} else {
			model.addAttribute("is_ip", "0");
		}
		return "/conult/crm_conult/crm_net_conult";
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
	public String to_check_net_conult(Model model, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "consult_id", required = false) Integer consult_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("consult_id", consult_id);
		NetConult netConult = netConultService.getNetConult(map);
		map.clear();
		map.put("course_id", netConult.getCourse_id());
		CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
		if (netConult.getCourse_id() == 9) {// 职称
			map.clear();
			map.put("review_id", netConult.getReview_id());
			Review review = reviewService.getReview(map);
			netConult.setCourse_name(courseMenu.getCourse_name() + "-" + review.getReview_name());
		} else {
			netConult.setCourse_name(courseMenu.getCourse_name());
		}
		if (netConult.getDictionary_id() != null) {
			map.clear();
			map.put("dictionary_id", netConult.getDictionary_id());
			Dictionary dictionary = dictionaryService.getDictionary(map);
			netConult.setDictionary_name(dictionary.getDictionary_name());
		}
		model.addAttribute("netConult", netConult);
		map.clear();
		map.put("consult_id", consult_id);
		List<NetConultRecord> records = netConultRecordService.getNetConultRecordList(map);
		for (NetConultRecord netConultRecord : records) {
			map.clear();
			map.put("employee_id", netConultRecord.getEmployee_id());
			Employee employee=employeeService.getEmployee(map);
			netConultRecord.setEmployee(employee);
		}
		model.addAttribute("records", records);
		String ip=GetIPUtil.getIpAddress(request);
		if(ip.equals("110.249.251.98")||ip.equals("127.0.0.1")){
			model.addAttribute("is_ip", "1");
		}else{
			model.addAttribute("is_ip", "0");
		}
		return "/conult/crm_conult/check_conult";
	}

	/**
	 * @Title: CrmConultController.java
	 * @Package com.jingren.jing.conult.controller.crm_conult
	 * @Description: TODO 添加追踪记录页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月4日 下午6:16:47
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_track.jr")
	public String to_add_track(Model model,HttpSession session,
			@RequestParam(value = "consult_id", required = false) Integer consult_id) {
		//Employee employee=(Employee) session.getAttribute("employee_session");
		Map<String, Object> map=new HashMap<>();
		map.put("consult_id", consult_id);
		NetConult netConult = netConultService.getNetConult(map);
		//map.put("employee_id", employee.getEmployee_id());
		List<ConTrack> conTracks=conTrackService.getConTrackList(map);
		for (ConTrack conTrack : conTracks) {
			map.clear();
			map.put("employee_id", conTrack.getEmployee_id());
			Employee employee=employeeService.getEmployee(map);
			conTrack.setEmployee(employee);
		}
		model.addAttribute("conTracks", conTracks);
		model.addAttribute("netConult", netConult);
		model.addAttribute("consult_id", consult_id);
		return "/conult/crm_conult/add_conult_track";
	}

	/**
	 * @Title: CrmConultController.java
	 * @Package com.jingren.jing.conult.controller.crm_conult
	 * @Description: TODO 添加追踪记录
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月4日 下午6:25:33
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_conult_track.jr")
	public void save_conult_track(HttpServletResponse response, ConTrack conTrack, HttpSession session,
			@RequestParam(value = "conult_state", required = false) Integer conult_state) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		conTrack.setTrack_time(new Date());
		conTrack.setEmployee_id(employee.getEmployee_id());
		if (conTrackService.saveConTrack(conTrack)) {
			NetConult netConult = new NetConult();
			netConult.setConult_state(conult_state);
			netConult.setConsult_id(conTrack.getConsult_id());
			if (netConultService.updateNetConult(netConult)) {
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "0");
			}
		}
	}

	/**
	 * @Title: CrmConultController.java
	 * @Package com.jingren.jing.conult.controller.crm_conult
	 * @Description: TODO 保存通话记录
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月7日 上午8:17:04
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_net_conult_call.jr")
	public void save_net_conult_call(final HttpSession session, final NetConultRecord netConultRecord,
			final HttpServletResponse response, @RequestParam(value = "str", required = false) final String str) {
		JSONObject json = JSONObject.fromObject(str);
		Employee employee = (Employee) session.getAttribute("employee_session");
		netConultRecord.setEmployee_id(employee.getEmployee_id());
		Map<String, Object> map = new HashMap<>();
		netConultRecord.setBumen_id(employee.getBumen_id());// 保存部门ID
		netConultRecord.setCall_time(new Date());
		netConultRecord.setCalled_phone(json.getString("callb"));// 被叫用户
		netConultRecord.setZuoxi(json.getString("calla"));// 坐席
		netConultRecord.setCrm_recourd_uid(json.getString("uid"));
		netConultRecord.setCall_state("NO ANSWER");
		if (netConultRecord.getConsult_id() != null) {
			map.clear();
			map.put("consult_id", netConultRecord.getConsult_id());
			NetConult netConult = netConultService.getNetConult(map);
			if (netConult != null) {
				netConultRecord.setUser_name(netConult.getUser_name());
			}
		}
		if (json.getString("status").equals("callout")) {
			netConultRecord.setCall_type("呼出");
		} else {
			netConultRecord.setCall_type("呼入");
		}
		netConultRecord.setRecord_time(0);
		if (netConultRecordService.saveNetConultRecord(netConultRecord)) {
			session.removeAttribute("call_uid");
			session.removeAttribute("call_record_id");
			session.setAttribute("call_uid", netConultRecord.getCrm_recourd_uid());
			session.setAttribute("call_record_id", netConultRecord.getRecord_id());
			ResponseUtils.renderText(response, String.valueOf(netConultRecord.getRecord_id()));
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: CrmConultController.java
	 * @Package com.jingren.jing.conult.controller.crm_conult
	 * @Description: TODO 通话弹屏
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月7日 上午8:18:08
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_call_tanping.jr")
	public Callable<String> to_call_tanping(final Model model, final HttpSession session,
			@RequestParam(value = "limit", required = false) final Integer limit,
			@RequestParam(value = "pageNumber", required = false) final Integer pageNumber,
			@RequestParam(value = "consult_id", required = false) final Integer consult_id,
			@RequestParam(value = "record_id", required = false) final Integer record_id) {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				Map<String, Object> map = new HashMap<>();
				//Employee employee = (Employee) session.getAttribute("employee_session");
				map.put("consult_id", consult_id);
				NetConult netConult = netConultService.getNetConult(map);
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
				model.addAttribute("netConult", netConult);
				// map.put("pageNumber", (pageNumber - 1) * limit);
				// map.put("limit", limit);
				//map.put("employee_id", employee.getEmployee_id());
				List<NetConultRecord> records = netConultRecordService.getNetConultRecordList(map);
				for (NetConultRecord netConultRecord : records) {
					map.clear();
					map.put("employee_id", netConultRecord.getEmployee_id());
					Employee employee=employeeService.getEmployee(map);
					netConultRecord.setEmployee(employee);
				}
				model.addAttribute("records", records);
				map.clear();
				if (netConult.getCourse_id() != null) {
					map.put("course_id", netConult.getCourse_id());
					CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
					model.addAttribute("courseMenu", courseMenu);
				}
				model.addAttribute("record_id", record_id);
				return "/conult/crm_conult/tanping_call";
			}
		};

	}
	/**
	* @Title: CrmConultController.java 
	* @Package com.jingren.jing.conult.controller.crm_conult 
	* @Description: TODO 添加通话记录备注
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月7日 上午10:58:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/add_note_conult_call.jr")
	public void add_note_business_call(HttpServletResponse response,
			@RequestParam(value = "record_id", required = false) Integer record_id,
			@RequestParam(value = "record_note", required = false) String record_note,
			@RequestParam(value = "genjin_state", required = false) Integer genjin_state) {
		NetConultRecord netConultRecord = new NetConultRecord();
		netConultRecord.setRecord_id(record_id);
		netConultRecord.setRecord_note(record_note);
		netConultRecord.setGenjin_state(genjin_state);
		if (netConultRecordService.updateNetConultRecord(netConultRecord)) {
			Map<String, Object> map =new HashMap<>();
			map.put("record_id", record_id);
			NetConultRecord conultRecord=netConultRecordService.getNetConultRecord(map);
			NetConult netConult=new NetConult();
			netConult.setConsult_id(conultRecord.getConsult_id());
			netConult.setConult_state(netConultRecord.getGenjin_state());
			netConultService.updateNetConult(netConult);
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "2");
		}
	}
	
	/**
	* @Title: CrmConultController.java 
	* @Package com.jingren.jing.conult.controller.crm_conult 
	* @Description: TODO 修改通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月7日 上午11:12:36 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/update_conult_call.jr")
	public  void update_conult_call ( HttpSession session, NetConultRecord netConultRecord,
			HttpServletResponse response,
			@RequestParam(value="call_time_str",required=false)String call_time_str) throws ParseException{
		if(StringUtils.isNotBlank(netConultRecord.getSound_file())){
			netConultRecord.setSound_file(netConultRecord.getSound_file().substring(6, netConultRecord.getSound_file().length()));
		}
		if(StringUtils.isNotBlank(call_time_str)){
			netConultRecord.setCall_time(CommentDate.get_leave_date(call_time_str));
		}
		if(netConultRecordService.updateNetConultRecord(netConultRecord)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "2");
		}
	}
}
