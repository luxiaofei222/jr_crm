package com.jingren.jing.personal.controller.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: BackTaskController.java 
* @Package com.jingren.jing.personal.controller.task 
* @Description: TODO 任务系统
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月6日 上午8:28:35 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.oa.bean.task.OaTask;
import com.jingren.jing.oa.service.task.OaTaskService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
@Controller
@RequestMapping("back_task")
public class BackTaskController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private OaTaskService oaTaskService;
	@Resource
	private OrganizationService organizationService;
	
	/**
	* @Title: BackTaskController.java 
	* @Package com.jingren.jing.personal.controller.task 
	* @Description: TODO 任务系统首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 上午9:55:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_back_task.jr")
	public String get_back_task(Model model,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "limit", required = false, defaultValue = "4") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber
			) {
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee_id);
		Employee employee = employeeService.getEmployee(map);
		model.addAttribute("employee", employee);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer task_number=oaTaskService.getOaTaskNumber(map);
		List<OaTask> oaTasks=oaTaskService.getOaTaskList(map);
		if(oaTasks.size()>0){
			for (OaTask oaTask : oaTasks) {
				if(oaTask.getTask_type().equals("进行中")){
					if(oaTask.getTask_finish_time().getTime()<new Date().getTime()){
						oaTask.setTask_type("未完成");
						oaTaskService.updateOaTask(oaTask);
					}
				}
				map.clear();
				map.put("employee_id", oaTask.getFabu_employee_id());
				Employee employee2=employeeService.getEmployee(map);
				oaTask.setFabuem(employee2);
				map.clear();
				map.put("employee_id", oaTask.getTask_employee_id());
				Employee employee3=employeeService.getEmployee(map);
				oaTask.setJieshouem(employee3);
				map.clear();
				map.put("organization_id", oaTask.getBumen_id());
				Organization organization=organizationService.getOranization(map);
				oaTask.setBumen(organization.getOrganization_name());
			}
		}
		Pagers<OaTask> pagers = new Pagers<OaTask>(task_number, pageNumber, limit);
		pagers.setList(oaTasks);
		model.addAttribute("oaTasks", pagers);
		return "/personal/task/task_index";
	}
	/**
	* @Title: BackTaskController.java 
	* @Package com.jingren.jing.personal.controller.task 
	* @Description: TODO 进入任务反馈页面
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年6月6日 上午10:04:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_fankui_task.jr")
	public String to_fankui_task(Model model,
			@RequestParam(value = "task_id", required = false) Integer task_id){
		Map<String, Object> map = new HashMap<>();
		map.put("task_id", task_id);
		OaTask oaTask=oaTaskService.getOaTask(map);
		map.clear();
		map.put("employee_id", oaTask.getFabu_employee_id());
		Employee employee2=employeeService.getEmployee(map);
		oaTask.setFabuem(employee2);
		model.addAttribute("oaTask", oaTask);
		map.clear();
		map.put("organization_id", employee2.getOrganization_id());// 获取所有部门
		Organization Organization = organizationService.getOranization(map);
		model.addAttribute("Organization", Organization);
		return "/personal/task/fankui_task";
	}
	/**
	* @Title: BackTaskController.java 
	* @Package com.jingren.jing.personal.controller.task 
	* @Description: TODO 任务反馈
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 上午10:12:13 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/fankui.jr")
	public void fankui(OaTask oaTask,HttpServletResponse response){
		if(StringUtils.isNotBlank(oaTask.getTask_type())){
			if(oaTask.getTask_type().equals("已完成")){
				oaTask.setFinish_time(new Date());
			}
		}
		if(oaTaskService.updateOaTask(oaTask)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackTaskController.java 
	* @Package com.jingren.jing.personal.controller.task 
	* @Description: TODO 获取任务列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 上午11:00:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_back_task_list.jr")
	public String get_back_task_list(Model model,
			@RequestParam(value = "task_type", required = false) String task_type,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "limit", required = false, defaultValue = "3") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber
			) {
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(task_type)){
			map.put("task_type", task_type);
			model.addAttribute("task_type", task_type);
		}
		map.put("employee_id", employee_id);
		model.addAttribute("employee_id", employee_id);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer task_number=oaTaskService.getOaTaskNumber(map);
		List<OaTask> oaTasks=oaTaskService.getOaTaskList(map);
		if(oaTasks.size()>0){
			for (OaTask oaTask : oaTasks) {
				if(oaTask.getTask_type().equals("进行中")){
					if(oaTask.getTask_finish_time().getTime()<new Date().getTime()){
						oaTask.setTask_type("未完成");
						oaTaskService.updateOaTask(oaTask);
					}
				}
				map.clear();
				map.put("employee_id", oaTask.getFabu_employee_id());
				Employee employee2=employeeService.getEmployee(map);
				oaTask.setFabuem(employee2);
				map.clear();
				map.put("employee_id", oaTask.getTask_employee_id());
				Employee employee3=employeeService.getEmployee(map);
				oaTask.setJieshouem(employee3);
				map.clear();
				map.put("organization_id", oaTask.getBumen_id());
				Organization organization=organizationService.getOranization(map);
				oaTask.setBumen(organization.getOrganization_name());
			}
		}
		Pagers<OaTask> pagers = new Pagers<OaTask>(task_number, pageNumber, limit);
		pagers.setList(oaTasks);
		model.addAttribute("oaTasks", pagers);
		return "/personal/task/task_list";
	}
}
