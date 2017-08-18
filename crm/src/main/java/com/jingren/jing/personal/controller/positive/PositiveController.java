package com.jingren.jing.personal.controller.positive;

import java.text.ParseException;
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

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.oa.bean.oa_employee.OAEmployee;
import com.jingren.jing.oa.service.oa_employee.OAEmployeeService;
import com.jingren.jing.personal.bean.positive.Positive;
import com.jingren.jing.personal.service.positive.PositiveService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
/**
* @Title: PositiveController.java 
* @Package com.jingren.jing.personal.controller.positive 
* @Description: TODO 转正申请系统
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月5日 上午11:29:34 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("positive")
public class PositiveController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private PositiveService positiveService;
	@Resource
	private OAEmployeeService oaEmployeeService;
	
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 转正日期
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 上午11:36:38 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhuanzheng_page.jr")
	public String get_zhuanzheng_page(Model model,HttpSession session){
		Employee employee =(Employee) session.getAttribute("employee_session");
		model.addAttribute("employee", employee);
		Map<String, Object> map=new HashMap<>();
		map.put("organization_id", employee.getBumen_id());//部门
		Organization organization=organizationService.getOranization(map);
		model.addAttribute("bumen", organization);
		map.clear();
		map.put("organization_id", employee.getOrganization_id());//岗位
		Organization organization2=organizationService.getOranization(map);
		model.addAttribute("gangwei", organization2);
		map.clear();
		map.put("employee_id", employee.getEmployee_id());
		List<OAEmployee> oaEmployees=oaEmployeeService.getOAEmployeeList(map);
		List<Positive> positives=positiveService.getPositiveList(map);
		if(positives.size()>0){
			model.addAttribute("positive", positives.get(0));
		}
		if(oaEmployees.size()>0){
			model.addAttribute("oaEmployee", oaEmployees.get(0));
		}
		map.clear();
		map.put("task_bumen_id", employee.getBumen_id());
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		return "/personal/positive/send_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 提交转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午1:53:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/send_positive_content.jr")
	public void send_positive_content (Positive positive,HttpServletResponse response,
			@RequestParam(value="positive_time_str",required=false)String positive_time_str,
			@RequestParam(value="shenpi_id",required=false)Integer shenpi_id) throws ParseException{
		if(StringUtils.isNotBlank(positive_time_str)){
			positive.setPositive_time(CommentDate.get_String_date(positive_time_str));
		}else{
			positive.setPositive_time(new Date());
		}
		if(shenpi_id!=null){
			positive.setJingli_id(shenpi_id);//经理审批
			positive.setPositive_state(0);
		}
		positive.setShenqing_time(new Date());
		Map<String, Object> map=new HashMap<>();
		map.put("employee_id", positive.getEmployee_id());
		List<Positive> positives=positiveService.getPositiveList(map);
		if(positives.size()>0){
			ResponseUtils.renderText(response, "2");
		}else{
			if(positiveService.savePositive(positive)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 市场部门转正审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午2:19:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_crm_bumen_positive.jr")
	public String get_crm_bumen_positive(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
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
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		if (employee.getRole_id() == 13) {
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
		Integer positive_number = positiveService.getPositiveNumber(map);
		List<Positive> positives = positiveService.getPositiveList(map);
		for (Positive positive : positives) {
			map.clear();
			map.put("employee_id", positive.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			positive.setEmployee(employee2);
			map.put("organization_id", positive.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			positive.setBumen(organization.getOrganization_name());
		}
		Pagers<Positive> pagers = new Pagers<Positive>(positive_number, pageNumber, limit);
		pagers.setList(positives);
		model.addAttribute("positives", pagers);
		return "/crm/positive/bumen_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 市场部查看转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午2:40:23 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_crm_positive.jr")
	public String to_check_crm_positive(Model model,
			@RequestParam(value = "positive_id", required = false) Integer positive_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("positive_id", positive_id);
		Positive positive = positiveService.getPositive(map);
		if(positive!=null){
		map.clear();
		map.put("employee_id", positive.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		positive.setEmployee(employee2);
		map.put("organization_id", positive.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		positive.setBumen(organization.getOrganization_name());
		}
		map.clear();
		map.put("organization_id", 30);
		map.put("employee_state", "在职");
		List<Employee> employees_zongjian=employeeService.getEmployeeList(map);
		model.addAttribute("employees_zongjian", employees_zongjian);
		model.addAttribute("positive", positive);
		return "/crm/positive/check_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 审批市场部转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:00:36 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_crm_positive.jr")
	public void update_crm_desert(Positive  positive,HttpServletResponse response,HttpSession session,
			@RequestParam(value="message",required=false)String message,
			@RequestParam(value="shenpi_id",required=false)Integer shenpi_id,
			@RequestParam(value="radio",required=false)Integer radio){
		Employee employee=(Employee) session.getAttribute("employee_session");
		if(radio==1){//同意
				if(shenpi_id==47){
					positive.setJingli_message(message);
					positive.setZongjian_id(shenpi_id);
					positive.setPositive_state(1);//提交到总监
				}else{//总监审批，提交给人事
					positive.setHr_message(message);
					positive.setHr_id(shenpi_id);
					positive.setPositive_state(2);
				}
		
		}else{//拒绝申请
			if(employee.getEmployee_id()==47){//总监审批直接拒绝
				positive.setZongjian_message(message);
				positive.setPositive_state(4);
			}else{//经理审批直接拒绝
				positive.setJingli_message(message);
				positive.setPositive_state(4);
			}
		}
		if(positiveService.updatePositive(positive)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事部门审批转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:19:10 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_oa_bumen_positive.jr")
	public String get_oa_bumen_positive(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
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
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		if (employee.getEmployee_id()==106) {
			map.put("zongjian_id", employee.getEmployee_id());
			Map<String, Object> map_leave = new HashMap<>();
			map_leave.put("organization_id", employee.getBumen_id());
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
		Integer positive_number = positiveService.getPositiveNumber(map);
		List<Positive> positives = positiveService.getPositiveList(map);
		for (Positive positive : positives) {
			map.clear();
			map.put("employee_id", positive.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			positive.setEmployee(employee2);
			map.put("organization_id", positive.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			positive.setBumen(organization.getOrganization_name());
		}
		Pagers<Positive> pagers = new Pagers<Positive>(positive_number, pageNumber, limit);
		pagers.setList(positives);
		model.addAttribute("positives", pagers);
		return "/oa/positive/bumen_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事查看
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:26:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_oa_positive.jr")
	public String to_check_oa_positive(Model model,
			@RequestParam(value = "positive_id", required = false) Integer positive_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("positive_id", positive_id);
		Positive positive = positiveService.getPositive(map);
		if(positive!=null){
		map.clear();
		map.put("employee_id", positive.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		positive.setEmployee(employee2);
		map.put("organization_id", positive.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		positive.setBumen(organization.getOrganization_name());
		}
		map.clear();
		map.put("organization_id", 30);
		map.put("employee_state", "在职");
		List<Employee> employees_zongjian=employeeService.getEmployeeList(map);
		model.addAttribute("employees_zongjian", employees_zongjian);//人力总监
		model.addAttribute("positive", positive);
		return "/oa/positive/check_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事部门审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:28:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_oa_positive.jr")
	public void update_oa_positive(Positive  positive,HttpServletResponse response,HttpSession session,
			@RequestParam(value="message",required=false)String message,
			@RequestParam(value="shenpi_id",required=false)Integer shenpi_id,
			@RequestParam(value="radio",required=false)Integer radio){
		Employee employee=(Employee) session.getAttribute("employee_session");
		if(radio==1){//同意
				if(shenpi_id==106){
					positive.setJingli_message(message);
					positive.setZongjian_id(shenpi_id);
					positive.setPositive_state(1);//提交到总监
				}else{//总监审批，提交给人事
					positive.setHr_message(message);
					positive.setHr_id(shenpi_id);
					positive.setPositive_state(2);
				}
		
		}else{//拒绝申请
			if(employee.getEmployee_id()==106){//总监审批直接拒绝
				positive.setZongjian_message(message);
				positive.setPositive_state(4);
			}else{//经理审批直接拒绝
				positive.setJingli_message(message);
				positive.setPositive_state(4);
			}
		}
		if(positiveService.updatePositive(positive)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事总监审批转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:41:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_renshi_positive.jr")
	public String get_renshi_positive(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
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
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("hr_id", employee.getEmployee_id());
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_level", 1);
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);
		model.addAttribute("limit", limit);
		Integer positive_number = positiveService.getPositiveNumber(map);
		List<Positive> positives = positiveService.getPositiveList(map);
		for (Positive positive : positives) {
			map.clear();
			map.put("employee_id", positive.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			positive.setEmployee(employee2);
			map.put("organization_id", positive.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			positive.setBumen(organization.getOrganization_name());
		}
		Pagers<Positive> pagers = new Pagers<Positive>(positive_number, pageNumber, limit);
		pagers.setList(positives);
		model.addAttribute("positives", pagers);
		return "/oa/positive/hr_positive";
	}
	
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事总监审批转正
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:42:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_renshi_positive.jr")
	public String to_check_renshi_positive(Model model,
			@RequestParam(value = "positive_id", required = false) Integer positive_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("positive_id", positive_id);
		Positive positive = positiveService.getPositive(map);
		if(positive!=null){
		map.clear();
		map.put("employee_id", positive.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		positive.setEmployee(employee2);
		map.put("organization_id", positive.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		positive.setBumen(organization.getOrganization_name());
		}
		model.addAttribute("positive", positive);
		return "/oa/positive/hr_check_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事审批转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:46:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_renshi_positive.jr")
	public void update_renshi_positive(Positive  positive,HttpServletResponse response,HttpSession session,
			@RequestParam(value="message",required=false)String message,
			@RequestParam(value="shenpi_id",required=false)Integer shenpi_id,
			@RequestParam(value="radio",required=false)Integer radio){
		if(radio==1){//同意
			positive.setHr_message(message);
			positive.setBoss_id(shenpi_id);
			positive.setPositive_state(3);//提交到总经理
		}else{//拒绝申请
			positive.setHr_message(message);
			positive.setPositive_state(4);
		}
		if(positiveService.updatePositive(positive)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 技术部和教务共用列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:55:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_edu_bumen_positive.jr")
	public String get_edu_bumen_desert(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
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
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("jingli_id", employee.getEmployee_id());
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_id", employee.getBumen_id());
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);
		model.addAttribute("limit", limit);
		Integer positive_number = positiveService.getPositiveNumber(map);
		List<Positive> positives = positiveService.getPositiveList(map);
		for (Positive positive : positives) {
			map.clear();
			map.put("employee_id", positive.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			positive.setEmployee(employee2);
			map.put("organization_id", positive.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			positive.setBumen(organization.getOrganization_name());
		}
		Pagers<Positive> pagers = new Pagers<Positive>(positive_number, pageNumber, limit);
		pagers.setList(positives);
		model.addAttribute("positives", pagers);
		return "/educational/positive/bumen_positive";
	}
	
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 技术部和教务部提交转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:54:06 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_edu_positive.jr")
	public String to_check_edu_positive(Model model,
			@RequestParam(value = "positive_id", required = false) Integer positive_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("positive_id", positive_id);
		Positive positive = positiveService.getPositive(map);
		if(positive!=null){
		map.clear();
		map.put("employee_id", positive.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		positive.setEmployee(employee2);
		map.put("organization_id", positive.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		positive.setBumen(organization.getOrganization_name());
		}
		map.clear();
		map.put("organization_id", 30);
		map.put("employee_state", "在职");
		List<Employee> employees_zongjian=employeeService.getEmployeeList(map);
		model.addAttribute("employees_zongjian", employees_zongjian);//人力总监
		model.addAttribute("positive", positive);
		return "/educational/positive/check_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事和技术部审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午3:57:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_edu_positive.jr")
	public void update_edu_positive(Positive  positive,HttpServletResponse response,HttpSession session,
			@RequestParam(value="message",required=false)String message,
			@RequestParam(value="shenpi_id",required=false)Integer shenpi_id,
			@RequestParam(value="radio",required=false)Integer radio){
		if(radio==1){//同意
			positive.setJingli_message(message);
			positive.setHr_id(shenpi_id);
			positive.setPositive_state(2);//提交到人事
		}else{//拒绝申请
			positive.setJingli_message(message);
			positive.setPositive_state(4);
		}
		if(positiveService.updatePositive(positive)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 老板查看转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午4:09:46 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_boss_positive.jr")
	public String get_boss_positive(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
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
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("boss_id", employee.getEmployee_id());
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_level", 1);
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);
		model.addAttribute("limit", limit);
		Integer positive_number = positiveService.getPositiveNumber(map);
		List<Positive> positives = positiveService.getPositiveList(map);
		for (Positive positive : positives) {
			map.clear();
			map.put("employee_id", positive.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			positive.setEmployee(employee2);
			map.put("organization_id", positive.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			positive.setBumen(organization.getOrganization_name());
		}
		Pagers<Positive> pagers = new Pagers<Positive>(positive_number, pageNumber, limit);
		pagers.setList(positives);
		model.addAttribute("positives", pagers);
		return "/oa/positive/boss_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 总经理查看转正申请详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午4:12:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_boss_positive.jr")
	public String to_check_boss_positive(Model model,
			@RequestParam(value = "positive_id", required = false) Integer positive_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("positive_id", positive_id);
		Positive positive = positiveService.getPositive(map);
		if(positive!=null){
		map.clear();
		map.put("employee_id", positive.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		positive.setEmployee(employee2);
		map.put("organization_id", positive.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		positive.setBumen(organization.getOrganization_name());
		}
		model.addAttribute("positive", positive);
		return "/oa/positive/boss_check_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 总经理审批转正申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午4:15:05 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_boss_positive.jr")
	public void update_boss_positive(Positive  positive,HttpServletResponse response,HttpSession session,
			@RequestParam(value="message",required=false)String message,
			@RequestParam(value="radio",required=false)Integer radio){
		if(radio==1){//同意
			positive.setBoss_message(message);
			positive.setPositive_state(4);//提交到人事
		}else{//拒绝申请
			positive.setBoss_message(message);
			positive.setPositive_state(4);
		}
		if(positiveService.updatePositive(positive)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事查看所有转正申请记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午4:20:13 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_all_positive.jr")
	public String get_all_positive(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		if (employee_id != null) {
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (bumen_id != null) {
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_level", 1);
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);
		model.addAttribute("limit", limit);
		Integer positive_number = positiveService.getPositiveNumber(map);
		List<Positive> positives = positiveService.getPositiveList(map);
		for (Positive positive : positives) {
			map.clear();
			map.put("employee_id", positive.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			positive.setEmployee(employee2);
			map.put("organization_id", positive.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			positive.setBumen(organization.getOrganization_name());
		}
		Pagers<Positive> pagers = new Pagers<Positive>(positive_number, pageNumber, limit);
		pagers.setList(positives);
		model.addAttribute("positives", pagers);
		return "/oa/positive/all_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 人事查看所有的记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午4:21:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_all_positive.jr")
	public String to_check_all_positive(Model model,
			@RequestParam(value = "positive_id", required = false) Integer positive_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("positive_id", positive_id);
		Positive positive = positiveService.getPositive(map);
		if(positive!=null){
		map.clear();
		map.put("employee_id", positive.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		positive.setEmployee(employee2);
		map.put("organization_id", positive.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		positive.setBumen(organization.getOrganization_name());
		}
		model.addAttribute("positive", positive);
		return "/oa/positive/all_check_positive";
	}
	/**
	* @Title: PositiveController.java 
	* @Package com.jingren.jing.personal.controller.positive 
	* @Description: TODO 员工查看进度
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午6:07:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_employee_positive.jr")
	public String get_employee_positive(Model model, HttpSession session,
			@RequestParam(value = "limit", required = false, defaultValue = "1") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee.getEmployee_id());
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer positive_number = positiveService.getPositiveNumber(map);
		List<Positive> positives = positiveService.getPositiveList(map);
		for (Positive positive : positives) {
			map.clear();
			map.put("employee_id", positive.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			positive.setEmployee(employee2);
			map.put("organization_id", positive.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			positive.setBumen(organization.getOrganization_name());
			map.clear();
			map.put("employee_id", employee.getEmployee_id());
			List<OAEmployee> oaEmployees=oaEmployeeService.getOAEmployeeList(map);
			positive.setOaEmployee(oaEmployees.get(0));
		}
		Pagers<Positive> pagers = new Pagers<Positive>(positive_number, pageNumber, limit);
		pagers.setList(positives);
		model.addAttribute("positives", pagers);
		return "/personal/project/positive_page";
	}
}
