package com.jingren.jing.school.back.employee;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: EmployeeCenterController.java 
* @Package com.jingren.jing.school.back.employee 
* @Description: TODO 系统个人中心
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月27日 上午9:04:13 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.goeasy.GoEasyPush;
import com.jingren.jing.oa.bean.oa_file.OaFile;
import com.jingren.jing.oa.bean.oa_notice.Notice;
import com.jingren.jing.oa.service.oa_file.OaFileService;
import com.jingren.jing.oa.service.oa_notice.NoticeService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.message.PrivateMessage;
import com.jingren.jing.school.bean.say.EmployeeSay;
import com.jingren.jing.school.bean.say.EmployeeZan;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.school.service.message.PrivateMessageService;
import com.jingren.jing.school.service.say.EmployeeSayService;
import com.jingren.jing.school.service.say.EmployeeZanService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;

@Controller
@RequestMapping("center")
public class EmployeeCenterController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private EmployeeSayService employeeSayService;
	@Resource
	private EmployeeZanService employeeZanService;
	@Resource
	private PrivateMessageService privateMessageService;

	@Resource
	private NoticeService noticeService;
	@Resource
	private OaFileService oaFileService;
	private static final String UP_FRONT_FILE = "images/saysay";

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 个人中心首页
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月27日 上午9:06:16
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/get_employee_center.jr")
	public Callable<String> get_employee_center(final HttpSession session, final Model model,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") final Integer pageNumber)
					throws ParseException {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Employee employee = (Employee) session.getAttribute("employee_session");
				int jifen = employee.getJifen();
				int dengji = 0;
				if (jifen >= 90) {
					dengji = Integer.valueOf((int) Math.floor(Math.sqrt(jifen / 10) + 7));
				} else if (20 >= jifen && jifen >= 0) {
					dengji = jifen / 5;
				} else if (jifen > 20 && jifen < 75) {
					dengji=Integer.valueOf((int) Math.floor(jifen / 10))+2;
				}else if(jifen >= 75 && jifen < 90){
					dengji=9;
				}
				if (dengji < 16) {
					model.addAttribute("flower", Integer.valueOf((int) Math.floor(dengji / 4)));
					model.addAttribute("ye", dengji % 4);
				} else if (dengji >= 16) {
					model.addAttribute("apple", Integer.valueOf((int) Math.floor(dengji / 16)));
					model.addAttribute("flower", dengji % 16 / 4);
					model.addAttribute("ye", dengji % 16 % 4);
				}
				model.addAttribute("employee", employee);
				Map<String, Object> map = new HashMap<>();
				map.put("pageNumber", (pageNumber - 1) * 14);
				map.put("limit", 14);
				map.put("employee_state_cen", "在职");
				List<Employee> employees = employeeService.getEmployeeList(map);
				Integer employee_number = employeeService.getEmployeeNumber(map);
				for (Employee employee2 : employees) {
					map.put("organization_id", employee2.getOrganization_id());
					Organization organization = organizationService.getOranization(map);
					employee2.setOrganization(organization);
				}
				Pagers<Employee> pagers = new Pagers<Employee>(employee_number, pageNumber, 14);
				pagers.setList(employees);
				model.addAttribute("employees", pagers);
				map.clear();
				map.put("employee_id", employee.getEmployee_id());
				map.put("say_level", 0);
				int zan = 0;
				List<EmployeeSay> employeeSays = employeeSayService.getEmployeeSayList(map);
				for (EmployeeSay employeeSay : employeeSays) {
					zan += employeeSay.getZan_times();
				}
				Integer donttai = employeeSayService.getEmployeeSayNumber(map);
				model.addAttribute("donttai", donttai);
				model.addAttribute("zan", zan);
				model.addAttribute("new_time", CommentDate.get_employee_string(new Date()));
				model.addAttribute("zhouji", CommentDate.getWeekOfDate(new Date()));
				/*********************** 判断签到 ***********************/
				map.clear();
				map.put("employee_id", employee.getEmployee_id());
				map.put("content_type", "签到");
				map.put("start_time", CommentDate.getStartTime());
				map.put("end_time", CommentDate.getnowEndTime());
				List<EmployeeSay> employeeSays2 = employeeSayService.getEmployeeSayList(map);
				if (employeeSays2.size() > 0) {
					model.addAttribute("is_qiandao", 1);// 已签到
				} else {
					model.addAttribute("is_qiandao", 0);// 未签到
				}
				/************************ 公告 *****************************/
				List<Notice> notice = noticeService.getNotice();
				model.addAttribute("notice", notice);
				/*************************私信*****************************/
				map.clear();
				map.put("get_employee_id", employee.getEmployee_id());
				int message_number_all=privateMessageService.getPrivateMessageNumber(map);
				map.put("is_read", 0);
				int message_number=privateMessageService.getPrivateMessageNumber(map);
				model.addAttribute("message_number", message_number);
				model.addAttribute("message_number_all", message_number_all);
				return "/employee_center/employee_center";
			}
		};
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 获取公司文件列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月31日 下午6:07:55
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_file_list.jr")
	public String get_file_list(Model model,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * 20);
		map.put("limit", 10);
		Integer file_number = oaFileService.getOaFileNumber(map);
		List<OaFile> oaFiles = oaFileService.getOaFileList(map);
		for (OaFile oaFile : oaFiles) {
			String prefix = oaFile.getFile_addr().substring(oaFile.getFile_addr().lastIndexOf(".") + 1);
			oaFile.setHouzhui(prefix);
		}
		Pagers<OaFile> pagers = new Pagers<OaFile>(file_number, pageNumber, 10);
		pagers.setList(oaFiles);
		model.addAttribute("oaFiles", pagers);
		return "/employee_center/file_list";
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 系统好友分页
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月27日 上午10:53:20
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_employee_list.jr")
	public String get_employee_list(HttpSession session, Model model,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * 14);
		map.put("limit", 14);
		map.put("employee_state_cen", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		Integer employee_number = employeeService.getEmployeeNumber(map);
		for (Employee employee2 : employees) {
			map.put("organization_id", employee2.getOrganization_id());
			Organization organization = organizationService.getOranization(map);
			employee2.setOrganization(organization);
		}
		Pagers<Employee> pagers = new Pagers<Employee>(employee_number, pageNumber, 14);
		pagers.setList(employees);
		model.addAttribute("employees", pagers);
		return "/employee_center/employee_list";
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 发表说说
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月28日 上午9:32:49
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_saysay.jr")
	public void save_saysay(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			EmployeeSay employeeSay, @RequestParam(value = "file_upload", required = false) MultipartFile file_upload) {
		if (file_upload != null) {
			String path = UploadAddress.getUploadDate(file_upload, request, UP_FRONT_FILE);
			employeeSay.setSay_pic(path);
		}
		Employee employee = (Employee) session.getAttribute("employee_session");
		if (employee != null) {
			if (StringUtils.isNotBlank(employeeSay.getSay_content())) {
				employeeSay.setEmployee_id(employee.getEmployee_id());
				employeeSay.setSay_time(new Date());
				if (employeeSayService.saveEmployeeSay(employeeSay)) {
					Map<String, Object> map = new HashMap<>();
					map.put("employee_id", employee.getEmployee_id());
					Employee employee2 = employeeService.getEmployee(map);
					employee2.setJifen(employee2.getJifen() + 5);
					employeeService.updateEmployee(employee2);// 发表说说+5分
					ResponseUtils.renderText(response, "1");
				} else {
					ResponseUtils.renderText(response, "0");
				}
			} else {
				ResponseUtils.renderText(response, "4");
			}
		} else {
			ResponseUtils.renderText(response, "3");// 用户登录超时
		}
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 获取说说列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月29日 上午9:30:29
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_say_list.jr")
	public String get_say_list(Model model, HttpSession session,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Employee employee_session = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("content_type", "说说");
		map.put("pageNumber", (pageNumber - 1) * 20);
		map.put("limit", 20);
		map.put("parent_id", 0);
		map.put("huifuleve", 0);
		int employeesay_number = employeeSayService.getEmployeeSayNumber(map);
		List<EmployeeSay> employeeSays = employeeSayService.getEmployeeSayList(map);
		List<EmployeeSay> say_id=new ArrayList<>();
		for (EmployeeSay employeeSay : employeeSays) {
			map.clear();
			map.put("say_id", employeeSay.getSay_id());
			map.put("employee_id", employee_session.getEmployee_id());
			List<EmployeeZan> employeeZans = employeeZanService.getemployeeZans(map);
			if (employeeZans.size() > 0) {
				employeeSay.setIs_zan(1);
			} else {
				employeeSay.setIs_zan(0);
			}
//			map.clear();
//			map.put("employee_id", employeeSay.getEmployee_id());
//			Employee employee = employeeService.getEmployee(map);
//			employeeSay.setEmployee(employee);
			say_id.add(employeeSay);
		}
		Pagers<EmployeeSay> pagers = new Pagers<EmployeeSay>(employeesay_number, pageNumber, 20);
		pagers.setList(employeeSays);
		model.addAttribute("employeeSays", pagers);
		return "/employee_center/say_list";

	}

	/**
	* @Title: EmployeeCenterController.java 
	* @Package com.jingren.jing.school.back.employee 
	* @Description: TODO 获取二级说说
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月15日 上午10:30:49 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_say_list.jr")
	public String get_sub_say_list(Model model, HttpSession session,
			@RequestParam(value = "say_id", required = false) Integer say_id) {
		Map<String, Object> map = new HashMap<>();
		/************************ 一级评论回复 **********************************/
		map.put("parent_id", say_id);
		map.put("say_level", 1);
		map.put("huifuleve", 1);
		List<EmployeeSay> subemployeesays = employeeSayService.getEmployeeSayList(map);
		for (EmployeeSay employeeSay2 : subemployeesays) {
//			map.clear();
//			map.put("employee_id", employeeSay2.getEmployee_id());
//			Employee employee_sub = employeeService.getEmployee(map);
//			employeeSay2.setEmployee(employee_sub);
			/********************************* 二级评论回复 ****************************************/
			map.clear();
			map.put("parent_id", employeeSay2.getSay_id());
			map.put("say_level", 2);
			map.put("huifuleve", 1);
			List<EmployeeSay> sanemployeesays = employeeSayService.getEmployeeSayList(map);
			for (EmployeeSay employeeSay3 : sanemployeesays) {
//				map.clear();
//				map.put("employee_id", employeeSay3.getEmployee_id());
//				Employee employee_san = employeeService.getEmployee(map);
//				employeeSay3.setEmployee(employee_san);
				map.clear();
				map.put("employee_id", employeeSay3.getParent_employee_id());
				Employee employee_par = employeeService.getEmployee(map);
				employeeSay3.setParent_employee(employee_par);
			}
			employeeSay2.setSub_employeeSays(sanemployeesays);
		}
		model.addAttribute("subemployeesays", subemployeesays);
		return "/employee_center/sub_say_list";

	}
	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 一级回复评论
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月29日 下午3:33:39
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_leveone_saysay.jr")
	public void save_leveone_saysay(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			EmployeeSay employeeSay) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		if (employee != null) {
			if (StringUtils.isNotBlank(employeeSay.getSay_content())) {
				employeeSay.setEmployee_id(employee.getEmployee_id());
				employeeSay.setSay_time(new Date());
				if (employeeSayService.saveEmployeeSay(employeeSay)) {
					if(StringUtils.isNotBlank(employeeSay.getContent_type())){
						if (employeeSay.getContent_type().equals("签到")) {
							Map<String, Object> map = new HashMap<>();
							map.put("employee_id", employee.getEmployee_id());
							Employee employee2 = employeeService.getEmployee(map);
							employee2.setJifen(employee2.getJifen() + 3);
							employeeService.updateEmployee(employee2);// 签到一次+3分
						}
					}
					if(employeeSay.getSay_level()!=null){
						if(employeeSay.getSay_level()==1){
							Map<String, Object> map = new HashMap<>();
							map.put("say_id", employeeSay.getParent_id());
							EmployeeSay employeeSay2=employeeSayService.getEmployeeSay(map);
							map.clear();
							map.put("employee_id", employeeSay2.getEmployee_id());
							//Employee employee3 = employeeService.getEmployee(map);
							//GoEasyPush.push_message(employee3.getLogin_name(), employee.getEmployee_name()+"评论了您的说说，快去查看吧！");
						}else{
							Map<String, Object> map = new HashMap<>();
							map.put("employee_id", employeeSay.getParent_employee_id());
							//Employee employee3 = employeeService.getEmployee(map);
							//GoEasyPush.push_message(employee3.getLogin_name(), employee.getEmployee_name()+"评论了您的说说，快去查看吧！");
						}
					}
					ResponseUtils.renderText(response, "1");
				} else {
					ResponseUtils.renderText(response, "0");
				}
			} else {
				ResponseUtils.renderText(response, "4");
			}
		} else {
			ResponseUtils.renderText(response, "3");// 用户登录超时
		}
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 点赞
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月30日 上午9:29:55
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/dianzan.jr")
	public void dianzan(HttpServletResponse response, HttpSession session, EmployeeZan employeeZan) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("say_id", employeeZan.getSay_id());
		map.put("employee_id", employee.getEmployee_id());
		List<EmployeeZan> employeeZans = employeeZanService.getemployeeZans(map);
		if (employeeZans.size() > 0) {
			ResponseUtils.renderText(response, "2");// 已经点过赞了
		} else {
			employeeZan.setEmployee_id(employee.getEmployee_id());
			if (employeeZanService.saveEmployeeZan(employeeZan)) {
				map.clear();
				map.put("say_id", employeeZan.getSay_id());
				EmployeeSay employeeSay = employeeSayService.getEmployeeSay(map);
				employeeSay.setZan_times(employeeSay.getZan_times() + 1);
				employeeSayService.updateEmployeeSay(employeeSay);
				map.clear();
				map.put("employee_id", employee.getEmployee_id());
				Employee employee2 = employeeService.getEmployee(map);
				employee2.setJifen(employee2.getJifen() + 2);
				employeeService.updateEmployee(employee2);// 点赞一次+2分
				map.clear();
				map.put("employee_id", employeeSay.getEmployee_id());
//				Employee employee3 = employeeService.getEmployee(map);
//				GoEasyPush.push_message(employee3.getLogin_name(), employee.getEmployee_name()+"赞了您的说说，快去查看吧！");
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "0");
			}
		}
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 获取个人资料
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月30日 上午10:49:18
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_get_employee.jr")
	public String to_get_employee(Model model,
			@RequestParam(value = "employee_id", required = false) Integer employee_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee_id);
		Employee employee = employeeService.getEmployee(map);
		model.addAttribute("employee", employee);
		return "/employee_center/update_info";
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 修改个人资料
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月30日 下午1:13:08
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_employee.jr")
	public void update_employee(Employee employee, HttpServletResponse response) {
		if (employee.getLogin_password() != null) {
			employee.setLogin_password(DigestUtils.shaHex(employee.getLogin_password()));
		}
		if (employeeService.updateEmployee(employee)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 检查旧密码
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月30日 下午1:24:36
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_old_password.jr")
	public void check_old_password(HttpServletResponse response,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "pass", required = false) String pass) {
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee_id);
		Employee employee = employeeService.getEmployee(map);
		if (employee.getLogin_password().equals(DigestUtils.shaHex(pass))) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 上传头像
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月30日 下午1:46:48
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_employee_pic.jr")
	public void update_person_pic(Employee employee, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "headpic", required = false) String headpic) {
		if (StringUtils.isNotBlank(headpic)) {
			employee.setEmployee_pic(headpic);
			if (employeeService.updateEmployee(employee)) {
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "0");
			}
		} else {
			ResponseUtils.renderText(response, "2");// 没有剪切
		}
	}

	/**
	 * @Title: EmployeeCenterController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 获取名片
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月30日 下午5:26:40
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_employee_card.jr")
	public String get_employee_card(Model model,
			@RequestParam(value = "employee_id", required = false) Integer employee_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee_id);
		Employee employee = employeeService.getEmployee(map);
		map.clear();
		map.put("organization_id", employee.getOrganization_id());
		Organization gangwei = organizationService.getOranization(map);
		map.clear();
		map.put("organization_id", employee.getBumen_id());
		Organization bumen = organizationService.getOranization(map);
		model.addAttribute("employee", employee);
		model.addAttribute("gangwei", gangwei);
		model.addAttribute("bumen", bumen);
		/********************等级积分**********************/
		int jifen = employee.getJifen();
		int dengji = 0;
		if (jifen >= 90) {
			dengji = Integer.valueOf((int) Math.floor(Math.sqrt(jifen / 10) + 7));
		} else if (20 >= jifen && jifen >= 0) {
			dengji = jifen / 5;
		} else if (jifen > 20 && jifen < 75) {
			dengji=Integer.valueOf((int) Math.floor(jifen / 10))+2;
		}else if(jifen >= 75 && jifen < 90){
			dengji=9;
		}
		if (dengji < 16) {
			model.addAttribute("flower", Integer.valueOf((int) Math.floor(dengji / 4)));
			model.addAttribute("ye", dengji % 4);
		} else if (dengji >= 16) {
			model.addAttribute("apple", Integer.valueOf((int) Math.floor(dengji / 16)));
			model.addAttribute("flower", dengji % 16 / 4);
			model.addAttribute("ye", dengji % 16 % 4);
		}
		return "/employee_center/employee_card";
	}
	/**
	* @Title: EmployeeCenterController.java 
	* @Package com.jingren.jing.school.back.employee 
	* @Description: TODO 获取我的评论
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 上午11:42:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_my_say_list.jr")
	public String get_my_say_list(Model model, HttpSession session,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Employee employee_session = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("content_type", "说说");
		map.put("employee_id", employee_session.getEmployee_id());
		map.put("pageNumber", (pageNumber - 1) * 20);
		map.put("limit", 20);
		map.put("parent_id", 0);
		map.put("huifuleve", 0);
		int employeesay_number = employeeSayService.getEmployeeSayNumber(map);
		List<EmployeeSay> employeeSays = employeeSayService.getEmployeeSayList(map);
		for (EmployeeSay employeeSay : employeeSays) {
			map.clear();
			map.put("say_id", employeeSay.getSay_id());
			map.put("employee_id", employee_session.getEmployee_id());
			List<EmployeeZan> employeeZans = employeeZanService.getemployeeZans(map);
			if (employeeZans.size() > 0) {
				employeeSay.setIs_zan(1);
			} else {
				employeeSay.setIs_zan(0);
			}
//			map.clear();
//			map.put("employee_id", employeeSay.getEmployee_id());
//			Employee employee = employeeService.getEmployee(map);
//			employeeSay.setEmployee(employee);
			/************************ 一级评论回复 **********************************/
			map.clear();
			map.put("parent_id", employeeSay.getSay_id());
			map.put("say_level", 1);
			map.put("huifuleve", 1);
			List<EmployeeSay> subemployeesays = employeeSayService.getEmployeeSayList(map);
			for (EmployeeSay employeeSay2 : subemployeesays) {
//				map.clear();
//				map.put("employee_id", employeeSay2.getEmployee_id());
//				Employee employee_sub = employeeService.getEmployee(map);
//				employeeSay2.setEmployee(employee_sub);
				/********************************* 二级评论回复 ****************************************/
				map.clear();
				map.put("parent_id", employeeSay2.getSay_id());
				map.put("say_level", 2);
				map.put("huifuleve", 1);
				List<EmployeeSay> sanemployeesays = employeeSayService.getEmployeeSayList(map);
				for (EmployeeSay employeeSay3 : sanemployeesays) {
//					map.clear();
//					map.put("employee_id", employeeSay3.getEmployee_id());
//					Employee employee_san = employeeService.getEmployee(map);
//					employeeSay3.setEmployee(employee_san);
					map.clear();
					map.put("employee_id", employeeSay3.getParent_employee_id());
					Employee employee_par = employeeService.getEmployee(map);
					employeeSay3.setParent_employee(employee_par);
				}
				employeeSay2.setSub_employeeSays(sanemployeesays);
			}
			employeeSay.setSub_employeeSays(subemployeesays);
		}
		Pagers<EmployeeSay> pagers = new Pagers<EmployeeSay>(employeesay_number, pageNumber, 20);
		pagers.setList(employeeSays);
		model.addAttribute("employeeSays", pagers);
		return "/employee_center/my_say_list";

	}
	/**
	* @Title: EmployeeCenterController.java 
	* @Package com.jingren.jing.school.back.employee 
	* @Description: TODO 删除说说
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月25日 下午5:12:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_say.jr")
	public void delete_say(HttpServletResponse response,
			@RequestParam(value = "say_id", required = false) Integer say_id){
		if(employeeSayService.deleteEmployeeSay(say_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
