package com.jingren.jing.personal.controller.leave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
/**
* @Title: BackLeaveController.java 
* @Package com.jingren.jing.personal.controller.leave 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2017年5月31日 下午2:44:41 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.personal.bean.leave.BackLeave;
import com.jingren.jing.personal.service.leave.BackLeaveService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.GetWorkDay;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("back_leave")
public class BackLeaveController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private BackLeaveService backLeaveService;
	@Resource
	private OrganizationService organizationService;

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO 进入申请页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年5月31日 下午2:54:32
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_back_leave.jr")
	public String get_back_leave(Model model,
			@RequestParam(value = "employee_id", required = false) Integer employee_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee_id);
		Employee employee = employeeService.getEmployee(map);
		model.addAttribute("employee", employee);
		map.clear();
		map.put("task_bumen_id", employee.getBumen_id());
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		return "/personal/shenqing";
	}

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO 提交请假申请
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年5月31日 下午4:21:42
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/save_leave.jr")
	public void save_leave(HttpServletResponse response, BackLeave backLeave,
			@RequestParam(value = "start_time_str", required = false) String start_time_str,
			@RequestParam(value = "end_time_str", required = false) String end_time_str) throws ParseException {
		GetWorkDay o = new GetWorkDay();
		Long b = o.getWorkdayTimeInMillisExcWeekendHolidays(
				start_time_str, end_time_str,
				"yyyy-MM-dd HH:mm:ss");
		String tianshu=o.formatDuring(b);
		backLeave.setLeave_shichang(tianshu);
		backLeave.setLeave_start_time(CommentDate.get_leave_date(start_time_str));
		backLeave.setLeave_end_time(CommentDate.get_leave_date(end_time_str));
		backLeave.setLeave_time(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		backLeave.setNianyue(sdf.format(backLeave.getLeave_start_time()));
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		backLeave.setNianyueri(sdf2.format(backLeave.getLeave_start_time()));
		if (backLeaveService.saveBackLeave(backLeave)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO crm部门请假审批
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月1日 上午7:55:11
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_crm_bumen_leave.jr")
	public String get_crm_bumen_leave(Model model, HttpSession session,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "leave_type", required = false) Integer leave_type,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		if (employee_id != null) {
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (leave_type != null) {
			map.put("leave_type", leave_type);
			model.addAttribute("leave_type", leave_type);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		if (employee.getEmployee_id() == 47) {
			map.put("zongjian_id", employee.getEmployee_id());
			Map<String, Object> map_leave = new HashMap<>();
			map_leave.put("organization_level", 1);
			map_leave.put("bumen_id", 5);
			List<Organization> organizations = organizationService.getOranizationList(map_leave);
			model.addAttribute("organizations", organizations);
		} else {
			map.put("jingli_id", employee.getEmployee_id());
			Map<String, Object> map_leave = new HashMap<>();
			map_leave.put("organization_id", employee.getBumen_id());
			List<Organization> organizations = organizationService.getOranizationList(map_leave);
			model.addAttribute("organizations", organizations);
		}
		model.addAttribute("limit", limit);
		Integer leave_number = backLeaveService.getBackLeaveNumber(map);
		List<BackLeave> backLeaves = backLeaveService.getBackLeaveList(map);
		Date date=new Date();
		for (BackLeave backLeave : backLeaves) {
			if(backLeave.getLeave_start_time().getTime()>date.getTime()){
				backLeave.setQingjiazhong(1);//还没开始休息
			}else if(backLeave.getLeave_start_time().getTime()<=date.getTime()&&date.getTime()<=backLeave.getLeave_end_time().getTime()){
				backLeave.setQingjiazhong(2);//正在休息
			}else{
				backLeave.setQingjiazhong(3);//未销假，提示销假
			}
			map.clear();
			map.put("employee_id", backLeave.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			backLeave.setEmployee(employee2);
			map.put("organization_id", backLeave.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			backLeave.setBumen(organization.getOrganization_name());
		}
		Pagers<BackLeave> pagers = new Pagers<BackLeave>(leave_number, pageNumber, limit);
		pagers.setList(backLeaves);
		model.addAttribute("backLeaves", pagers);
		return "/crm/leave/bumen_leave";
	}

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO 获取所属部门员工
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月1日 上午8:35:07
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_bumen_employee_list.jr")
	public String get_bumen_employee_list(Model model,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("task_bumen_id", bumen_id);
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		return "/crm/leave/bumen_employee_list";
	}

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO 查看请假申请
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月1日 上午9:01:05
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_leave.jr")
	public String to_check_leave(Model model, @RequestParam(value = "leave_id", required = false) Integer leave_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("leave_id", leave_id);
		BackLeave backLeave = backLeaveService.getBackLeave(map);
		if(backLeave!=null){
		map.clear();
		map.put("employee_id", backLeave.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		backLeave.setEmployee(employee2);
		map.put("organization_id", backLeave.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		backLeave.setBumen(organization.getOrganization_name());
		switch (backLeave.getLeave_type()) {
		case 1:
			model.addAttribute("leixing", "事假");
			break;
		case 2:
			model.addAttribute("leixing", "病假");
			break;
		case 3:
			model.addAttribute("leixing", "婚假");
			break;
		case 4:
			model.addAttribute("leixing", "产假");
			break;
		case 5:
			model.addAttribute("leixing", "丧假");
			break;
		case 6:
			model.addAttribute("leixing", "其他");
			break;
		case 7:
			model.addAttribute("leixing", "倒休");
			break;
		case 8:
			model.addAttribute("leixing", "年假");
			break;
		}
		}
		model.addAttribute("backLeave", backLeave);
		return "/crm/leave/check_leave";
	}

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO 市场部请假流程
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月1日 下午1:45:36
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_crm_leave.jr")
	public void update_crm_leave(BackLeave backLeave, HttpServletResponse response,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "shenpi_id", required = false) Integer shenpi_id,
			@RequestParam(value = "radio", required = false) Integer radio) {
		Map<String, Object> map = new HashMap<>();
		map.put("leave_id", backLeave.getLeave_id());
		BackLeave backLeave2 = backLeaveService.getBackLeave(map);
		if (radio == 1) {// 通过
			if (backLeave2.getLeave_state() == 0) {
				backLeave.setZongjian_id(shenpi_id);
				backLeave.setJingli_message(message);
				backLeave.setLeave_state(1);
			} else if (backLeave2.getLeave_state() == 1) {
				if (shenpi_id != null) {// 提交至总经理
					backLeave.setBoss_id(shenpi_id);
					backLeave.setZongjian_message(message);
					backLeave.setLeave_state(2);
				} else {// 总监同意
					backLeave.setZongjian_message(message);
					backLeave.setLeave_state(3);
				}
			}
		} else {// 拒绝
			if (backLeave2.getLeave_state() == 0) {// 经理拒绝
				backLeave.setJingli_message(message);
				backLeave.setLeave_state(5);
			} else if (backLeave2.getLeave_state() == 1) {// 总监拒绝
				backLeave.setZongjian_message(message);
				backLeave.setLeave_state(5);
			}
		}
		if (backLeaveService.updateBackLeave(backLeave)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO OA系统部门审批请假
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月1日 下午2:50:01
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_oa_bumen_leave.jr")
	public String get_oa_bumen_leave(Model model, HttpSession session,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "leave_type", required = false) Integer leave_type,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		if (employee_id != null) {
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (leave_type != null) {
			map.put("leave_type", leave_type);
			model.addAttribute("leave_type", leave_type);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		if (employee.getEmployee_id() == 106) {
			map.put("zongjian_id", employee.getEmployee_id());
			Map<String, Object> map_leave = new HashMap<>();
			map_leave.put("organization_level", 1);
			map_leave.put("bumen_id", 29);
			List<Organization> organizations = organizationService.getOranizationList(map_leave);
			model.addAttribute("organizations", organizations);
		} else {
			map.put("jingli_id", employee.getEmployee_id());
			Map<String, Object> map_leave = new HashMap<>();
			map_leave.put("organization_id", employee.getBumen_id());
			List<Organization> organizations = organizationService.getOranizationList(map_leave);
			model.addAttribute("organizations", organizations);
		}
		model.addAttribute("limit", limit);
		Integer leave_number = backLeaveService.getBackLeaveNumber(map);
		List<BackLeave> backLeaves = backLeaveService.getBackLeaveList(map);
		Date date=new Date();
		for (BackLeave backLeave : backLeaves) {
			if(backLeave.getLeave_start_time().getTime()>date.getTime()){
				backLeave.setQingjiazhong(1);//还没开始休息
			}else if(backLeave.getLeave_start_time().getTime()<=date.getTime()&&date.getTime()<=backLeave.getLeave_end_time().getTime()){
				backLeave.setQingjiazhong(2);//正在休息
			}else{
				backLeave.setQingjiazhong(3);//未销假，提示销假
			}
			map.clear();
			map.put("employee_id", backLeave.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			backLeave.setEmployee(employee2);
			map.put("organization_id", backLeave.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			backLeave.setBumen(organization.getOrganization_name());
		}
		Pagers<BackLeave> pagers = new Pagers<BackLeave>(leave_number, pageNumber, limit);
		pagers.setList(backLeaves);
		model.addAttribute("backLeaves", pagers);
		return "/oa/leave/bumen_leave";
	}

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO OA查看请假信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月1日 下午2:57:03
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_oa_leave.jr")
	public String to_check_oa_leave(Model model, @RequestParam(value = "leave_id", required = false) Integer leave_id,
			@RequestParam(value = "type", required = false) Integer type) {
		Map<String, Object> map = new HashMap<>();
		map.put("leave_id", leave_id);
		BackLeave backLeave = backLeaveService.getBackLeave(map);
		if(backLeave!=null){
			map.clear();
			map.put("employee_id", backLeave.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			backLeave.setEmployee(employee2);
			map.put("organization_id", backLeave.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			backLeave.setBumen(organization.getOrganization_name());
			switch (backLeave.getLeave_type()) {
			case 1:
				model.addAttribute("leixing", "事假");
				break;
			case 2:
				model.addAttribute("leixing", "病假");
				break;
			case 3:
				model.addAttribute("leixing", "婚假");
				break;
			case 4:
				model.addAttribute("leixing", "产假");
				break;
			case 5:
				model.addAttribute("leixing", "丧假");
				break;
			case 6:
				model.addAttribute("leixing", "其他");
				break;
			case 7:
				model.addAttribute("leixing", "倒休");
				break;
			case 8:
				model.addAttribute("leixing", "年假");
				break;
			}
			model.addAttribute("backLeave", backLeave);
		}
		map.clear();
		map.put("organization_id", 30);
		map.put("employee_state", "在职");
		List<Employee> employees_zongjian=employeeService.getEmployeeList(map);
		model.addAttribute("employees_zongjian", employees_zongjian);//人力总监
		if(type!=null){
			return "/oa/leave/oa_check_leave";
		}else{
			return "/oa/leave/check_leave";
		}
	}

	/**
	 * @Title: BackLeaveController.java
	 * @Package com.jingren.jing.personal.controller.leave
	 * @Description: TODO 通用部门审批
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月1日 下午3:07:13
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_edu_bumen_leave.jr")
	public String get_edu_bumen_leave(Model model, HttpSession session,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "leave_type", required = false) Integer leave_type,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		if (employee_id != null) {
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (leave_type != null) {
			map.put("leave_type", leave_type);
			model.addAttribute("leave_type", leave_type);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("jingli_id", employee.getEmployee_id());

		Map<String, Object> map_leave = new HashMap<>();// 该部门的所有在职员工
		map_leave.put("task_bumen_id", employee.getBumen_id());
		map_leave.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map_leave);
		model.addAttribute("employees", employees);

		model.addAttribute("limit", limit);
		Integer leave_number = backLeaveService.getBackLeaveNumber(map);
		List<BackLeave> backLeaves = backLeaveService.getBackLeaveList(map);
		Date date=new Date();
		for (BackLeave backLeave : backLeaves) {
			if(backLeave.getLeave_start_time().getTime()>date.getTime()){
				backLeave.setQingjiazhong(1);//还没开始休息
			}else if(backLeave.getLeave_start_time().getTime()<=date.getTime()&&date.getTime()<=backLeave.getLeave_end_time().getTime()){
				backLeave.setQingjiazhong(2);//正在休息
			}else{
				backLeave.setQingjiazhong(3);//未销假，提示销假
			}
			map.clear();
			map.put("employee_id", backLeave.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			backLeave.setEmployee(employee2);
			map.put("organization_id", backLeave.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			backLeave.setBumen(organization.getOrganization_name());
		}
		Pagers<BackLeave> pagers = new Pagers<BackLeave>(leave_number, pageNumber, limit);
		pagers.setList(backLeaves);
		model.addAttribute("backLeaves", pagers);
		return "/educational/leave/bumen_leave";
	}
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO 公共查看请假信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月1日 下午3:10:55 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_edu_leave.jr")
	public String to_check_edu_leave(Model model, @RequestParam(value = "leave_id", required = false) Integer leave_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("leave_id", leave_id);
		BackLeave backLeave = backLeaveService.getBackLeave(map);
		if(backLeave!=null){
		map.clear();
		map.put("employee_id", backLeave.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		backLeave.setEmployee(employee2);
		map.put("organization_id", backLeave.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		backLeave.setBumen(organization.getOrganization_name());
		switch (backLeave.getLeave_type()) {
		case 1:
			model.addAttribute("leixing", "事假");
			break;
		case 2:
			model.addAttribute("leixing", "病假");
			break;
		case 3:
			model.addAttribute("leixing", "婚假");
			break;
		case 4:
			model.addAttribute("leixing", "产假");
			break;
		case 5:
			model.addAttribute("leixing", "丧假");
			break;
		case 6:
			model.addAttribute("leixing", "其他");
			break;
		case 7:
			model.addAttribute("leixing", "倒休");
			break;
		case 8:
			model.addAttribute("leixing", "年假");
			break;
		}
		}
		model.addAttribute("backLeave", backLeave);
		return "/educational/leave/check_leave";
	}
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO公共的请假审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月1日 下午3:15:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_edu_leave.jr")
	public void update_edu_leave(BackLeave backLeave, HttpServletResponse response,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "shenpi_id", required = false) Integer shenpi_id,
			@RequestParam(value = "radio", required = false) Integer radio) {
		Map<String, Object> map = new HashMap<>();
		map.put("leave_id", backLeave.getLeave_id());
		BackLeave backLeave2 = backLeaveService.getBackLeave(map);
		if (radio == 1) {// 通过
			if (backLeave2.getLeave_state() == 0) {
				if (shenpi_id != null) {// 提交至总经理
					backLeave.setBoss_id(shenpi_id);
					backLeave.setJingli_message(message);
					backLeave.setLeave_state(2);
				} else {// 经理同意
					backLeave.setJingli_message(message);
					backLeave.setLeave_state(3);
				}
			} 
		} else {// 拒绝
				backLeave.setJingli_message(message);
				backLeave.setLeave_state(5);
		}
		if (backLeaveService.updateBackLeave(backLeave)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO 人力查看所有员工请假记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月1日 下午6:36:29 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_all_bumen_leave.jr")
	public String get_all_bumen_leave(Model model,
			@RequestParam(value = "nianyue", required = false) String nianyue,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "leave_type", required = false) Integer leave_type,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		if (employee_id != null) {
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (bumen_id != null) {
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		if (leave_type != null) {
			map.put("leave_type", leave_type);
			model.addAttribute("leave_type", leave_type);
		}
		model.addAttribute("time_list", CommentDate.get_nianyuelist());
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		if(StringUtils.isNotBlank(nianyue)){
			map.put("nianyue", nianyue);
			model.addAttribute("nianyue", nianyue);
		}else{
			map.put("nianyue", sdf.format(c.getTime()));
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_level", 1);
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);//所有部门
		model.addAttribute("limit", limit);
		Integer leave_number = backLeaveService.getBackLeaveNumber(map);
		List<BackLeave> backLeaves = backLeaveService.getBackLeaveList(map);
		Date date=new Date();
		for (BackLeave backLeave : backLeaves) {
			if(backLeave.getLeave_start_time().getTime()>date.getTime()){
				backLeave.setQingjiazhong(1);//还没开始休息
			}else if(backLeave.getLeave_start_time().getTime()<=date.getTime()&&date.getTime()<=backLeave.getLeave_end_time().getTime()){
				backLeave.setQingjiazhong(2);//正在休息
			}else{
				backLeave.setQingjiazhong(3);//未销假，提示销假
			}
			map.clear();
			map.put("employee_id", backLeave.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			backLeave.setEmployee(employee2);
			map.put("organization_id", backLeave.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			backLeave.setBumen(organization.getOrganization_name());
		}
		Pagers<BackLeave> pagers = new Pagers<BackLeave>(leave_number, pageNumber, limit);
		pagers.setList(backLeaves);
		model.addAttribute("backLeaves", pagers);
		return "/oa/leave/all_leave";
	}
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO 总经理审批请假
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午7:46:52 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_boss_leave.jr")
	public String get_boss_leave(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "leave_type", required = false) Integer leave_type,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		if (employee_id != null) {
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (bumen_id != null) {
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		if (leave_type != null) {
			map.put("leave_type", leave_type);
			model.addAttribute("leave_type", leave_type);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("boss_id", employee.getEmployee_id());
		model.addAttribute("limit", limit);
		Integer leave_number = backLeaveService.getBackLeaveNumber(map);
		List<BackLeave> backLeaves = backLeaveService.getBackLeaveList(map);
		Date date=new Date();
		for (BackLeave backLeave : backLeaves) {
			if(backLeave.getLeave_start_time().getTime()>date.getTime()){
				backLeave.setQingjiazhong(1);//还没开始休息
			}else if(backLeave.getLeave_start_time().getTime()<=date.getTime()&&date.getTime()<=backLeave.getLeave_end_time().getTime()){
				backLeave.setQingjiazhong(2);//正在休息
			}else{
				backLeave.setQingjiazhong(3);//未销假，提示销假
			}
			map.clear();
			map.put("employee_id", backLeave.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			backLeave.setEmployee(employee2);
			map.put("organization_id", backLeave.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			backLeave.setBumen(organization.getOrganization_name());
		}
		Pagers<BackLeave> pagers = new Pagers<BackLeave>(leave_number, pageNumber, limit);
		pagers.setList(backLeaves);
		model.addAttribute("backLeaves", pagers);
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_level", 1);
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);//所有部门
		return "/educational/leave/boss_leave";
	}
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO 总经理查看任务详情
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年6月2日 上午8:01:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_boss_leave.jr")
	public String to_check_boss_leave(Model model, @RequestParam(value = "leave_id", required = false) Integer leave_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("leave_id", leave_id);
		BackLeave backLeave = backLeaveService.getBackLeave(map);
		if(backLeave!=null){
		map.clear();
		map.put("employee_id", backLeave.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		backLeave.setEmployee(employee2);
		map.put("organization_id", backLeave.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		backLeave.setBumen(organization.getOrganization_name());
		switch (backLeave.getLeave_type()) {
		case 1:
			model.addAttribute("leixing", "事假");
			break;
		case 2:
			model.addAttribute("leixing", "病假");
			break;
		case 3:
			model.addAttribute("leixing", "婚假");
			break;
		case 4:
			model.addAttribute("leixing", "产假");
			break;
		case 5:
			model.addAttribute("leixing", "丧假");
			break;
		case 6:
			model.addAttribute("leixing", "其他");
			break;
		case 7:
			model.addAttribute("leixing", "倒休");
			break;
		case 8:
			model.addAttribute("leixing", "年假");
			break;
		}
		}
		model.addAttribute("backLeave", backLeave);
		return "/educational/leave/boss_check_leave";
	}
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO 总经理审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午8:03:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_boss_leave.jr")
	public void update_boss_leave(BackLeave backLeave, HttpServletResponse response,
			@RequestParam(value = "message", required = false) String message,
			@RequestParam(value = "radio", required = false) Integer radio) {
		if (radio == 1) {// 通过
			backLeave.setBoss_message(message);
			backLeave.setLeave_state(3);
		} else {// 拒绝
				backLeave.setBoss_message(message);
				backLeave.setLeave_state(5);
		}
		if (backLeaveService.updateBackLeave(backLeave)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO 获取个人请假记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午4:49:31 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_employee_leave.jr")
	public String get_employee_leave(Model model, HttpSession session,
			@RequestParam(value = "limit", required = false, defaultValue = "3") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee.getEmployee_id());
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer leave_number = backLeaveService.getBackLeaveNumber(map);
		List<BackLeave> backLeaves = backLeaveService.getBackLeaveList(map);
		Date date=new Date();
		for (BackLeave backLeave : backLeaves) {
			if(backLeave.getLeave_start_time().getTime()>date.getTime()){
				backLeave.setQingjiazhong(1);//还没开始休息
			}else if(backLeave.getLeave_start_time().getTime()<=date.getTime()&&date.getTime()<=backLeave.getLeave_end_time().getTime()){
				backLeave.setQingjiazhong(2);//正在休息
			}else{
				backLeave.setQingjiazhong(3);//未销假，提示销假
			}
		}
		Pagers<BackLeave> pagers = new Pagers<BackLeave>(leave_number, pageNumber, limit);
		pagers.setList(backLeaves);
		model.addAttribute("backLeaves", pagers);
		return "/personal/project/leave_page";
	}
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO 获取销假页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月8日 上午8:07:04 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_xiaojia_page.jr")
	public String get_xiaojia_page(Model model,
			@RequestParam(value="leave_id",required=false)Integer leave_id){
		Map<String, Object> map=new HashMap<>();
		map.put("leave_id", leave_id);
		BackLeave backLeave=backLeaveService.getBackLeave(map);
		map.put("employee_id", backLeave.getEmployee_id());
		Employee employee=employeeService.getEmployee(map);
		model.addAttribute("backLeave", backLeave);
		model.addAttribute("employee", employee);
		map.put("organization_id", backLeave.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		model.addAttribute("organization", organization);
		return "/personal/project/fankui_xiaojia";
	}
	
	/**
	* @Title: BackLeaveController.java 
	* @Package com.jingren.jing.personal.controller.leave 
	* @Description: TODO 销假
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月8日 上午8:11:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_xiaojia_leave.jr")
	public void update_xiaojia_leave(BackLeave backLeave, HttpServletResponse response) {
		backLeave.setXiaojia_time(new Date());
		backLeave.setLeave_state(4);
		if (backLeaveService.updateBackLeave(backLeave)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
}
