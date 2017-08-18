package com.jingren.jing.oa.controller.task;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: OATaskController.java 
* @Package com.jingren.jing.oa.controller.task 
* @Description: TODO 任务发布系统
* @author 鲁晓飞 MR.Lu   
* @date 2017年5月27日 上午8:00:08 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.oa.bean.task.OaTask;
import com.jingren.jing.oa.service.task.OaTaskService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
@Controller
@RequestMapping("oa_task")
public class OATaskController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private OaTaskService oaTaskService;
	@Resource
	private OrganizationService organizationService;
	private static final String UP_ANNEX_FILE ="annexes";
	
	/**
	* @Title: OATaskController.java 
	* @Package com.jingren.jing.oa.controller.task 
	* @Description: TODO 公司任务列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月27日 上午8:19:08 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_manager_task.jr")
	public String get_manager_task(Model model,HttpSession session,
			@RequestParam(value = "task_employee_id", required = false) Integer task_employee_id,
			@RequestParam(value = "task_type", required = false) String task_type,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "zhongyao_state", required = false) Integer zhongyao_state,
			@RequestParam(value = "task_finish_time_start_str", required = false) String task_finish_time_start_str,
			@RequestParam(value = "task_finish_time_end_str", required = false) String task_finish_time_end_str,
			@RequestParam(value = "limit", required = false,defaultValue="20") Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1") Integer pageNumber) throws ParseException{
		Employee employee=(Employee) session.getAttribute("employee_session");
		Map<String, Object> map=new HashMap<>();
		model.addAttribute("limit", limit);
		if(task_employee_id!=null){
			map.put("task_employee_id", task_employee_id);
			model.addAttribute("task_employee_id", task_employee_id);
		}
		if(bumen_id!=null){
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		if(StringUtils.isNotBlank(task_type)){
			map.put("task_type", task_type);
			model.addAttribute("task_type", task_type);
		}
		if(zhongyao_state!=null){
			map.put("zhongyao_state", zhongyao_state);
			model.addAttribute("zhongyao_state", zhongyao_state);
		}
		if(StringUtils.isNotBlank(task_finish_time_start_str)&&StringUtils.isNotBlank(task_finish_time_end_str)){
			map.put("finish_time_start", CommentDate.get_String_date(task_finish_time_start_str));
			long currentTime =  CommentDate.get_String_date(task_finish_time_end_str).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("finish_time_end", date);
			model.addAttribute("finish_time_start", task_finish_time_start_str);
			model.addAttribute("finish_time_end", task_finish_time_end_str);
		}
		map.put("fabu_employee_id", employee.getEmployee_id());
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("task_jibie", "公司");
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
		/**********************************任务人列表***************************************/
		map.clear();
		map.put("fabu_employee_id", employee.getEmployee_id());
		List<OaTask> oaTasks_employee=oaTaskService.getOataskJieEmployee(map);
		for (OaTask oaTask : oaTasks_employee) {
			map.clear();
			map.put("employee_id", oaTask.getTask_employee_id());
			Employee employee2=employeeService.getEmployee(map);
			oaTask.setJieshouem(employee2);
		}
		model.addAttribute("oaTasks_employee", oaTasks_employee);
		/************************************所在部门**********************************/
		map.clear();
		map.put("organization_level", 1);// 获取所有部门
		List<Organization> organizations = organizationService.getOranizationList(map);
		model.addAttribute("organizations", organizations);
		return "/oa/task/manager_task";
	}
	/**
	* @Title: OATaskController.java 
	* @Package com.jingren.jing.oa.controller.task 
	* @Description: TODO 发布任务页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月27日 上午9:15:43 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_send_task_page.jr")
	public String get_send_task_page(Model model){
		Map<String, Object> map=new HashMap<>();
		map.clear();
		map.put("organization_level", 1);// 获取所有部门
		List<Organization> organizations = organizationService.getOranizationList(map);
		model.addAttribute("organizations", organizations);
		map.clear();
		map.put("task_bumen_id", organizations.get(0).getOrganization_id());
		map.put("employee_state", "在职");
		List<Employee> employees =employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		return "/oa/task/send_task";
	}
	/**
	* @Title: OATaskController.java 
	* @Package com.jingren.jing.oa.controller.task 
	* @Description: TODO 获取部门员工列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月27日 上午10:25:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_bumen_employee_list.jr")
	public String get_bumen_employee_list(Model model,
			@RequestParam(value="bumen_id",required=false)Integer bumen_id){
		Map<String, Object> map=new HashMap<>();
		map.put("task_bumen_id", bumen_id);
		map.put("employee_state", "在职");
		List<Employee> employees =employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		return "/oa/task/bumen_employee_list";
	}
	/**
	* @Title: OATaskController.java 
	* @Package com.jingren.jing.oa.controller.task 
	* @Description: TODO 发布任务
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月27日 下午1:42:10 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/send_task.jr")
	public void send_task(OaTask oaTask,HttpServletResponse response,HttpSession session,HttpServletRequest request,
			@RequestParam(value="task_finish_time_str",required=false) String task_finish_time_str,
			@RequestParam(value="file_name",required=false) MultipartFile file_name) throws ParseException{
		Employee employee=(Employee) session.getAttribute("employee_session");
		if(file_name!=null){
			String path=UploadAddress.getUploadDate(file_name, request, UP_ANNEX_FILE);
			oaTask.setFujian(path);
		}
		long currentTime =  CommentDate.get_String_date(task_finish_time_str).getTime();
		currentTime +=24*60*60*1000-1000;//加23小时59分59秒
		Date date=new Date(currentTime);
		oaTask.setFabu_employee_id(employee.getEmployee_id());
		oaTask.setTask_finish_time(date);//完成时间
		oaTask.setTask_time(new Date());
		Map<String, Object> map=new HashMap<>();
		map.put("employee_id", oaTask.getTask_employee_id());
		Employee jieshou=employeeService.getEmployee(map);
		map.clear();
		map.put("organization_id", jieshou.getOrganization_id());
		Organization organization=organizationService.getOranization(map);
		oaTask.setGangwei(organization.getOrganization_name());
		if(oaTaskService.saveOaTask(oaTask)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: OATaskController.java 
	* @Package com.jingren.jing.oa.controller.task 
	* @Description: TODO 删除任务
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月27日 下午2:07:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_oa_task.jr")
	public void delete_oa_task(HttpServletResponse response,
			@RequestParam(value="task_id",required=false) Integer task_id){
		if(oaTaskService.deleteOaTask(task_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: OATaskController.java 
	* @Package com.jingren.jing.oa.controller.task 
	* @Description: TODO 查看任务详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月27日 下午2:45:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_task.jr")
	public String to_check_task(Model model,
			@RequestParam(value="task_id",required=false) Integer task_id){
		Map<String, Object> map=new HashMap<>();
		map.put("task_id", task_id);
		OaTask oaTask=oaTaskService.getOaTask(map);
		map.clear();//发布人
		map.put("employee_id", oaTask.getFabu_employee_id());
		Employee faburen=employeeService.getEmployee(map);
		model.addAttribute("faburen", faburen);
		
		map.clear();//接收人
		map.put("employee_id", oaTask.getTask_employee_id());
		Employee jieshou=employeeService.getEmployee(map);
		model.addAttribute("jieshou", jieshou);
		map.clear();//所在部门
		map.put("organization_id", jieshou.getBumen_id());
		Organization organization=organizationService.getOranization(map);
		model.addAttribute("organization", organization);
		model.addAttribute("oaTask", oaTask);
		return "/oa/task/check_task";
	}
	/**
	* @Title: OATaskController.java 
	* @Package com.jingren.jing.oa.controller.task 
	* @Description: TODO 任务评分
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月27日 下午4:44:09 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/pingfen_task.jr")
	public void pingfen_task(HttpServletResponse response,OaTask oaTask){
		if(oaTaskService.updateOaTask(oaTask)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
