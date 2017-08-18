package com.jingren.jing.personal.controller.notelog;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.personal.bean.emlog.EmLog;
import com.jingren.jing.personal.service.emlog.EmLogService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
/**
* @Title: LogNoteContrller.java 
* @Package com.jingren.jing.personal.controller.notelog 
* @Description: TODO 日报系统
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月6日 下午6:01:41 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("em_log")
public class LogNoteContrller {

	@Resource
	private EmLogService emLogService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrganizationService organizationService;
	

	/**
	* @Title: LogNoteContrller.java 
	* @Package com.jingren.jing.personal.controller.notelog 
	* @Description: TODO 日报页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午6:28:27 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_back_log.jr")
	public String get_back_log(Model model,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber
			) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		model.addAttribute("now_nianyue", sdf.format(c.getTime()));//获取当年时间的年月
		model.addAttribute("time_list", CommentDate.get_nianyuelist());
		Map<String, Object> map = new HashMap<>();
		map.put("nianyue", sdf.format(c.getTime()));
		map.put("employee_id", employee_id);
		Employee employee = employeeService.getEmployee(map);
		model.addAttribute("employee", employee);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("parent_id", 0);
		Integer log_number=emLogService.getEmLogNumber(map);
		List<EmLog> emLogs=emLogService.getEmLogList(map);
		if(emLogs.size()>0){
			for (EmLog emLog : emLogs) {
				map.clear();
				map.put("employee_id", emLog.getEmployee_id());
				Employee employee2=employeeService.getEmployee(map);
				emLog.setEmployee(employee2);
				map.clear();
				map.put("parent_id", emLog.getLog_id());
				List<EmLog> emLogs_sub=emLogService.getEmLogList(map);
				if(emLogs_sub.size()>0){
					for (EmLog emLog2 : emLogs_sub) {
						map.clear();
						map.put("employee_id", emLog2.getPinglun_id());
						Employee employee_sub=employeeService.getEmployee(map);
						emLog2.setEmployee(employee_sub);
					}
					emLog.setEmLogs(emLogs_sub);
				}
			}
		}
		Pagers<EmLog> pagers = new Pagers<EmLog>(log_number, pageNumber, limit);
		pagers.setList(emLogs);
		model.addAttribute("em_logs", pagers);
		//判断今天有没有提交日报
		map.clear();
		map.put("employee_id", employee_id);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		map.put("nianyueri", sdf2.format(new Date()));
		List<EmLog> emLogs2=emLogService.getEmLogList(map);
		if(emLogs2.size()>0){
			model.addAttribute("is_tijiao", "1");
		}else{
			model.addAttribute("is_tijiao", "0");
		}
		return "/personal/emlog/em_log";
	}
	/**
	* @Title: LogNoteContrller.java 
	* @Package com.jingren.jing.personal.controller.notelog 
	* @Description: TODO 日志列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月7日 上午9:58:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_back_log_list.jr")
	public String get_back_log_list(Model model,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "now_nianyue", required = false) String now_nianyue,
			@RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber
			) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("nianyue", now_nianyue);
		map.put("employee_id", employee_id);
		model.addAttribute("employee_id", employee_id);
		model.addAttribute("now_nianyue", now_nianyue);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("parent_id", 0);
		Integer log_number=emLogService.getEmLogNumber(map);
		List<EmLog> emLogs=emLogService.getEmLogList(map);
		if(emLogs.size()>0){
			for (EmLog emLog : emLogs) {
				map.clear();
				map.put("employee_id", emLog.getEmployee_id());
				Employee employee2=employeeService.getEmployee(map);
				emLog.setEmployee(employee2);
				map.clear();
				map.put("parent_id", emLog.getLog_id());
				List<EmLog> emLogs_sub=emLogService.getEmLogList(map);
				if(emLogs_sub.size()>0){
					for (EmLog emLog2 : emLogs_sub) {
						map.clear();
						map.put("employee_id", emLog2.getPinglun_id());
						Employee employee_sub=employeeService.getEmployee(map);
						emLog2.setEmployee(employee_sub);
					}
					emLog.setEmLogs(emLogs_sub);
				}
			}
		}
		Pagers<EmLog> pagers = new Pagers<EmLog>(log_number, pageNumber, limit);
		pagers.setList(emLogs);
		model.addAttribute("em_logs", pagers);
		return "/personal/emlog/em_log_list";
	}
	
	/**
	* @Title: LogNoteContrller.java 
	* @Package com.jingren.jing.personal.controller.notelog 
	* @Description: TODO 发送日报
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午6:54:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/send_em_log.jr")
	public void send_em_log(HttpServletResponse response,EmLog emLog){
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", emLog.getEmployee_id());
		Employee employee = employeeService.getEmployee(map);
		emLog.setBumen_id(employee.getBumen_id());
		emLog.setShang_bumen_id(employee.getParent_id());
		emLog.setTijiao_time(new Date());
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		emLog.setNianyue(sdf.format(c.getTime()));
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		emLog.setNianyueri(sdf2.format(new Date()));
		if(emLogService.saveEmLog(emLog)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: LogNoteContrller.java 
	* @Package com.jingren.jing.personal.controller.notelog 
	* @Description: TODO 部门查看日志
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月7日 上午8:49:17 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_bumen_emlog.jr")
	public String get_bumen_emlog(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "start_str", required = false) String start_str,
			@RequestParam(value = "end_str", required = false) String end_str,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) throws ParseException {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		if(employee.getEmployee_id()==47){
			if(bumen_id!=null){
				map.put("bumen_id", bumen_id);
				model.addAttribute("bumen_id", bumen_id);
			}
			Map<String, Object> map_leave = new HashMap<>();
			map_leave.put("parent_id", 35);
			List<Organization> organizations = organizationService.getOranizationList(map_leave);
			model.addAttribute("organizations", organizations);
		}else{
			map.put("bumen_id", employee.getBumen_id());
			map.put("employee_state", "在职");
			List<Employee> employees =employeeService.getEmployeeList(map);
			model.addAttribute("employees", employees);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("parent_id", 0);
		map.put("limit", limit);
		model.addAttribute("limit", limit);
		if(employee_id!=null){
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if(StringUtils.isNotBlank(start_str)&&StringUtils.isNotBlank(end_str)){//开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_str));
			long currentTime =  CommentDate.get_String_date(end_str).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_str", start_str);
			model.addAttribute("end_str", end_str);
		}
		Integer log_number = emLogService.getEmLogNumber(map);
		List<EmLog> emLogs = emLogService.getEmLogList(map);
		for (EmLog emLog : emLogs) {
			map.clear();
			map.put("employee_id", emLog.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			emLog.setEmployee(employee2);
			map.put("organization_id", emLog.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			emLog.setBumen(organization.getOrganization_name());
		}
		Pagers<EmLog> pagers = new Pagers<EmLog>(log_number, pageNumber, limit);
		pagers.setList(emLogs);
		model.addAttribute("emLogs", pagers);
		return "/crm/emlog/bumen_log";
	}
	/**
	* @Title: LogNoteContrller.java 
	* @Package com.jingren.jing.personal.controller.notelog 
	* @Description: TODO 查看所有员工日志
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月7日 下午1:05:34 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_all_emlog.jr")
	public String get_all_emlog(Model model, HttpSession session,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "start_str", required = false) String start_str,
			@RequestParam(value = "end_str", required = false) String end_str,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("parent_id", 0);
		map.put("limit", limit);
		model.addAttribute("limit", limit);
		if(employee_id!=null){
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if(bumen_id!=null){
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		if(StringUtils.isNotBlank(start_str)&&StringUtils.isNotBlank(end_str)){//开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_str));
			long currentTime =  CommentDate.get_String_date(end_str).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_str", start_str);
			model.addAttribute("end_str", end_str);
		}
		Integer log_number = emLogService.getEmLogNumber(map);
		List<EmLog> emLogs = emLogService.getEmLogList(map);
		for (EmLog emLog : emLogs) {
			map.clear();
			map.put("employee_id", emLog.getEmployee_id());
			Employee employee2 = employeeService.getEmployee(map);
			emLog.setEmployee(employee2);
			map.put("organization_id", emLog.getBumen_id());
			Organization organization = organizationService.getOranization(map);
			emLog.setBumen(organization.getOrganization_name());
		}
		Pagers<EmLog> pagers = new Pagers<EmLog>(log_number, pageNumber, limit);
		pagers.setList(emLogs);
		model.addAttribute("emLogs", pagers);
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_level", 1);
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);
		return "/crm/emlog/all_log";
	}
	/**
	* @Title: LogNoteContrller.java 
	* @Package com.jingren.jing.personal.controller.notelog 
	* @Description: TODO 查看日志详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月7日 上午9:00:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_log.jr")
	public String to_check_log(Model model,
			@RequestParam(value="log_id",required=false)Integer log_id){
		Map<String, Object> map = new HashMap<>();
		map.put("log_id", log_id);
		EmLog emLog=emLogService.getEmLog(map);
		map.clear();
		map.put("employee_id", emLog.getEmployee_id());
		Employee employee2 = employeeService.getEmployee(map);
		emLog.setEmployee(employee2);
		map.put("organization_id", emLog.getBumen_id());
		Organization organization = organizationService.getOranization(map);
		emLog.setBumen(organization.getOrganization_name());
		map.clear();
		map.put("parent_id", emLog.getLog_id());
		List<EmLog> emLogs_sub=emLogService.getEmLogList(map);
		if(emLogs_sub.size()>0){
			for (EmLog emLog2 : emLogs_sub) {
				map.clear();
				map.put("employee_id", emLog2.getPinglun_id());
				Employee employee_sub=employeeService.getEmployee(map);
				emLog2.setEmployee(employee_sub);
			}
		}
		emLog.setEmLogs(emLogs_sub);
		model.addAttribute("emLog", emLog);
		return "/crm/emlog/check_log";
	}
	/**
	* @Title: LogNoteContrller.java 
	* @Package com.jingren.jing.personal.controller.notelog 
	* @Description: TODO 日志评价
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月7日 上午9:12:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/pingfen_log.jr")
	public void pingfen_log(EmLog emLog,HttpServletResponse response,HttpSession session){
		Employee employee = (Employee) session.getAttribute("employee_session");
		emLog.setPinglun_id(employee.getEmployee_id());
		emLog.setTijiao_time(new Date());
		if(emLogService.saveEmLog(emLog)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
