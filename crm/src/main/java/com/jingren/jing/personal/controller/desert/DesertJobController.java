package com.jingren.jing.personal.controller.desert;

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
/**
* @Title: DesertJobController.java 
* @Package com.jingren.jing.personal.controller.desert 
* @Description: TODO 离职申请
* @author 鲁晓飞 MR.Lu    
* @date 2017年6月2日 上午10:11:29 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.oa.bean.oa_employee.OAEmployee;
import com.jingren.jing.oa.service.oa_employee.OAEmployeeService;
import com.jingren.jing.personal.bean.desert.DesertJob;
import com.jingren.jing.personal.service.desert.DesertJobService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
@Controller
@RequestMapping("desert")
public class DesertJobController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private DesertJobService desertJobService;
	@Resource
	private OAEmployeeService oaEmployeeService;
	
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 离职申请页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 上午10:17:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_lizhi_page.jr")
	public String get_lizhi_page(Model model,HttpSession session){
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
		List<DesertJob> desertJobs=desertJobService.getDesertJobList(map);
		if(desertJobs.size()>0){
			model.addAttribute("desertJob", desertJobs.get(0));
		}
		if(oaEmployees.size()>0){
			model.addAttribute("oaEmployee", oaEmployees.get(0));
		}
		map.clear();
		map.put("task_bumen_id", employee.getBumen_id());
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		return "/personal/desert/send_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 提交离职申请
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午12:57:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/send_desert_content.jr")
	public void send_desert_content (DesertJob desertJob,HttpServletResponse response,
			@RequestParam(value="lizhi_time_str",required=false)String lizhi_time_str,
			@RequestParam(value="shenpi_id",required=false)Integer shenpi_id) throws ParseException{
		if(StringUtils.isNotBlank(lizhi_time_str)){
			desertJob.setDesert_time(CommentDate.get_String_date(lizhi_time_str));
		}else{
			desertJob.setDesert_time(new Date());
		}
		if(shenpi_id!=null){
			if(shenpi_id!=47&&shenpi_id!=106){
				desertJob.setJingli_id(shenpi_id);
				desertJob.setDesert_state(0);
			}else{
				desertJob.setZongjian_id(shenpi_id);
				desertJob.setDesert_state(1);
			}
		}else{
			desertJob.setBoss_id(101);
			desertJob.setDesert_state(2);
		}
		desertJob.setTijiao_time(new Date());
		Map<String, Object> map=new HashMap<>();
		map.put("employee_id", desertJob.getEmployee_id());
		List<DesertJob> desertJobs=desertJobService.getDesertJobList(map);
		if(desertJobs.size()>0){
			ResponseUtils.renderText(response, "2");
		}else{
			if(desertJobService.saveDesertJob(desertJob)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 市场部门离职审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午1:51:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_crm_bumen_desert.jr")
	public String get_crm_bumen_desert(Model model, HttpSession session,
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
		Integer desert_number = desertJobService.getDesertJobNumber(map);
		List<DesertJob> backdeserts = desertJobService.getDesertJobList(map);
		for (DesertJob desertJob : backdeserts) {
			map.clear();
			map.put("employee_id", desertJob.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			desertJob.setEmployee(employee2);
			map.put("organization_id", desertJob.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			desertJob.setBumen(organization.getOrganization_name());
		}
		Pagers<DesertJob> pagers = new Pagers<DesertJob>(desert_number, pageNumber, limit);
		pagers.setList(backdeserts);
		model.addAttribute("backdeserts", pagers);
		return "/crm/desert/bumen_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 查看crm部门离职审批详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午2:00:15 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/to_check_crm_desert.jr")
	public String to_check_crm_desert(Model model,
			@RequestParam(value = "desert_id", required = false) Integer desert_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("desert_id", desert_id);
		DesertJob desertJob = desertJobService.getDesertJob(map);
		if(desertJob!=null){
		map.clear();
		map.put("employee_id", desertJob.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		desertJob.setEmployee(employee2);
		map.put("organization_id", desertJob.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		desertJob.setBumen(organization.getOrganization_name());
		}
		if(desertJob.getDesert_state()!=3&&desertJob.getDesert_state()!=4){
			int shicha=CommentDate.daysBetween(new Date(), desertJob.getDesert_time());
			model.addAttribute("shicha", shicha);
		}
		model.addAttribute("desertJob", desertJob);
		return "/crm/desert/check_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 经理/总监审批
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年6月2日 下午2:41:20 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_crm_desert.jr")
	public void update_crm_desert(DesertJob desertJob,HttpServletResponse response,HttpSession session,
			@RequestParam(value="message",required=false)String message,
			@RequestParam(value="shenpi_id",required=false)Integer shenpi_id,
			@RequestParam(value="radio",required=false)Integer radio){
		Employee employee=(Employee) session.getAttribute("employee_session");
		if(radio==1){//同意
			if(shenpi_id!=null){//经理审批，提交给总监
				if(shenpi_id==47||shenpi_id==106){
					desertJob.setJingli_message(message);
					desertJob.setZongjian_id(shenpi_id);
					desertJob.setDesert_state(1);
				}else{//总监审批，提交给总经理
					if(employee.getEmployee_id()==47||employee.getEmployee_id()==106){//总监审批
						desertJob.setZongjian_message(message);
						desertJob.setBoss_id(shenpi_id);
						desertJob.setDesert_state(2);
					}else{
						desertJob.setJingli_message(message);
						desertJob.setBoss_id(shenpi_id);
						desertJob.setDesert_state(2);
					}
				}
			}else{
				if(employee.getEmployee_id()==47||employee.getEmployee_id()==106){//总监审批
					desertJob.setZongjian_message(message);
					desertJob.setDesert_state(4);
				}else{
					desertJob.setJingli_message(message);
					desertJob.setDesert_state(4);
				}
			}
		}else{//拒绝申请
			if(employee.getEmployee_id()==47||employee.getEmployee_id()==106){//总监审批直接拒绝
				desertJob.setZongjian_message(message);
				desertJob.setDesert_state(3);
			}else{//经理审批直接拒绝
				desertJob.setJingli_message(message);
				desertJob.setDesert_state(3);
			}
		}
		if(desertJobService.updateDesertJob(desertJob)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 人力离职审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午2:57:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_oa_bumen_desert.jr")
	public String get_oa_bumen_desert(Model model, HttpSession session,
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
		if (employee.getEmployee_id() == 106) {
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
		Integer desert_number = desertJobService.getDesertJobNumber(map);
		List<DesertJob> backdeserts = desertJobService.getDesertJobList(map);
		for (DesertJob desertJob : backdeserts) {
			map.clear();
			map.put("employee_id", desertJob.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			desertJob.setEmployee(employee2);
			map.put("organization_id", desertJob.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			desertJob.setBumen(organization.getOrganization_name());
		}
		Pagers<DesertJob> pagers = new Pagers<DesertJob>(desert_number, pageNumber, limit);
		pagers.setList(backdeserts);
		model.addAttribute("backdeserts", pagers);
		return "/oa/desert/bumen_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 人力查看审批详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午2:58:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_oa_desert.jr")
	public String to_check_oa_desert(Model model,
			@RequestParam(value = "desert_id", required = false) Integer desert_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("desert_id", desert_id);
		DesertJob desertJob = desertJobService.getDesertJob(map);
		if(desertJob!=null){
		map.clear();
		map.put("employee_id", desertJob.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		desertJob.setEmployee(employee2);
		map.put("organization_id", desertJob.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		desertJob.setBumen(organization.getOrganization_name());
		}
		if(desertJob.getDesert_state()!=3&&desertJob.getDesert_state()!=4){
			int shicha=CommentDate.daysBetween(new Date(), desertJob.getDesert_time());
			model.addAttribute("shicha", shicha);
		}
		map.clear();
		map.put("organization_id", 30);
		map.put("employee_state", "在职");
		List<Employee> employees_zongjian=employeeService.getEmployeeList(map);
		model.addAttribute("employees_zongjian", employees_zongjian);//人力总监
		model.addAttribute("desertJob", desertJob);
		return "/oa/desert/check_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 教务离职审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午3:07:02 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_edu_bumen_desert.jr")
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
		Integer desert_number = desertJobService.getDesertJobNumber(map);
		List<DesertJob> backdeserts = desertJobService.getDesertJobList(map);
		for (DesertJob desertJob : backdeserts) {
			map.clear();
			map.put("employee_id", desertJob.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			desertJob.setEmployee(employee2);
			map.put("organization_id", desertJob.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			desertJob.setBumen(organization.getOrganization_name());
		}
		Pagers<DesertJob> pagers = new Pagers<DesertJob>(desert_number, pageNumber, limit);
		pagers.setList(backdeserts);
		model.addAttribute("backdeserts", pagers);
		return "/educational/desert/bumen_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 教务查看离职详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午3:10:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_edu_desert.jr")
	public String to_check_edu_desert(Model model,
			@RequestParam(value = "desert_id", required = false) Integer desert_id) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("desert_id", desert_id);
		DesertJob desertJob = desertJobService.getDesertJob(map);
		if(desertJob!=null){
		map.clear();
		map.put("employee_id", desertJob.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		desertJob.setEmployee(employee2);
		map.put("organization_id", desertJob.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		desertJob.setBumen(organization.getOrganization_name());
		}
		if(desertJob.getDesert_state()!=3&&desertJob.getDesert_state()!=4){
			int shicha=CommentDate.daysBetween(new Date(), desertJob.getDesert_time());
			model.addAttribute("shicha", shicha);
		}
		model.addAttribute("desertJob", desertJob);
		return "/educational/desert/check_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 总经理审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午3:52:20 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_boss_bumen_desert.jr")
	public String get_boss_bumen_desert(Model model, HttpSession session,
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
		Integer desert_number = desertJobService.getDesertJobNumber(map);
		List<DesertJob> backdeserts = desertJobService.getDesertJobList(map);
		for (DesertJob desertJob : backdeserts) {
			map.clear();
			map.put("employee_id", desertJob.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			desertJob.setEmployee(employee2);
			map.put("organization_id", desertJob.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			desertJob.setBumen(organization.getOrganization_name());
		}
		Pagers<DesertJob> pagers = new Pagers<DesertJob>(desert_number, pageNumber, limit);
		pagers.setList(backdeserts);
		model.addAttribute("backdeserts", pagers);
		return "/personal/desert/bumen_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 总经理查看
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午3:52:52 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_boss_desert.jr")
	public String to_check_boss_desert(Model model,
			@RequestParam(value = "desert_id", required = false) Integer desert_id,
			@RequestParam(value = "type", required = false) Integer type) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("desert_id", desert_id);
		DesertJob desertJob = desertJobService.getDesertJob(map);
		if(desertJob!=null){
		map.clear();
		map.put("employee_id", desertJob.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		desertJob.setEmployee(employee2);
		map.put("organization_id", desertJob.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		desertJob.setBumen(organization.getOrganization_name());
		}
		if(desertJob.getDesert_state()!=3&&desertJob.getDesert_state()!=4){
			int shicha=CommentDate.daysBetween(new Date(), desertJob.getDesert_time());
			model.addAttribute("shicha", shicha);
		}
		model.addAttribute("desertJob", desertJob);
		if(type!=null){
			return "/personal/desert/check_all_desert";
		}else{
			return "/personal/desert/check_desert";
		}
		
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 总经理审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午3:59:38 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_boss_desert.jr")
	public void update_boss_desert(DesertJob desertJob,HttpServletResponse response,HttpSession session,
			@RequestParam(value="message",required=false)String message,
			@RequestParam(value="radio",required=false)Integer radio){
		if(radio==1){//同意
			desertJob.setBoss_message(message);
			desertJob.setDesert_state(4);
		}else{//拒绝申请
			desertJob.setBoss_message(message);
			desertJob.setDesert_state(3);
		}
		if(desertJobService.updateDesertJob(desertJob)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 人事查看所有离职记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月2日 下午4:07:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_ao_all_desert.jr")
	public String get_ao_all_desert(Model model, HttpSession session,
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
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_id", employee.getBumen_id());
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);
		model.addAttribute("limit", limit);
		Integer desert_number = desertJobService.getDesertJobNumber(map);
		List<DesertJob> backdeserts = desertJobService.getDesertJobList(map);
		for (DesertJob desertJob : backdeserts) {
			map.clear();
			map.put("employee_id", desertJob.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			desertJob.setEmployee(employee2);
			map.put("organization_id", desertJob.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			desertJob.setBumen(organization.getOrganization_name());
		}
		Pagers<DesertJob> pagers = new Pagers<DesertJob>(desert_number, pageNumber, limit);
		pagers.setList(backdeserts);
		model.addAttribute("backdeserts", pagers);
		return "/personal/desert/all_desert";
	}
	/**
	* @Title: DesertJobController.java 
	* @Package com.jingren.jing.personal.controller.desert 
	* @Description: TODO 离职申请进度
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月5日 下午5:15:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_employee_desert.jr")
	public String get_crm_bumen_desert(Model model, HttpSession session,
			@RequestParam(value = "limit", required = false, defaultValue = "1") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("employee_id", employee.getEmployee_id());
		model.addAttribute("limit", limit);
		Integer desert_number = desertJobService.getDesertJobNumber(map);
		List<DesertJob> backdeserts = desertJobService.getDesertJobList(map);
		for (DesertJob desertJob : backdeserts) {
			map.clear();
			map.put("employee_id", desertJob.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			desertJob.setEmployee(employee2);
			map.clear();
			map.put("organization_id", desertJob.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			desertJob.setBumen(organization.getOrganization_name());
			map.clear();
			map.put("employee_id", employee.getEmployee_id());
			List<OAEmployee> oaEmployees=oaEmployeeService.getOAEmployeeList(map);
			desertJob.setOaEmployee(oaEmployees.get(0));
		}
		Pagers<DesertJob> pagers = new Pagers<DesertJob>(desert_number, pageNumber, limit);
		pagers.setList(backdeserts);
		model.addAttribute("backdeserts", pagers);
		return "/personal/project/desert_page";
	}
}
