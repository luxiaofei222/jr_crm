package com.jingren.jing.school.back.privatemessage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.message.PrivateMessage;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.school.service.message.PrivateMessageService;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: PrivateMessageController.java 
* @Package com.jingren.jing.school.back.privatemessage 
* @Description: TODO 私信
* @author 鲁晓飞 MR.Lu   
* @date 2017年5月26日 上午9:42:02 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("private_message")
public class PrivateMessageController {

	@Resource
	private PrivateMessageService privateMessageService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrganizationService organizationService;
	
	/**
	* @Title: PrivateMessageController.java 
	* @Package com.jingren.jing.school.back.privatemessage 
	* @Description: TODO 获取发送私信的页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 上午9:45:05 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_send_message_page.jr")
	public String get_send_message_page(Model model,
			@RequestParam(value="employee_id",required=false)Integer employee_id){
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
			return "/employee_center/send_message";
	}
	/**
	* @Title: PrivateMessageController.java 
	* @Package com.jingren.jing.school.back.privatemessage 
	* @Description: TODO 发送私信
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 上午10:35:08 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/send_private_message.jr")
	public void send_private_message(PrivateMessage privateMessage,
			HttpServletResponse response,HttpSession session){
		Employee employee=(Employee) session.getAttribute("employee_session");
		if(employee!=null){
			privateMessage.setPrivate_message_time(new Date());
			privateMessage.setSend_employee_id(employee.getEmployee_id());
			privateMessage.setSend_employee_name(employee.getEmployee_name());
			if(privateMessageService.savePrivateMessage(privateMessage)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: PrivateMessageController.java 
	* @Package com.jingren.jing.school.back.privatemessage 
	* @Description: TODO 进入私信聊天页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 上午11:00:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_private_message.jr")
	public String get_private_message(Model model,HttpSession session,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1")  Integer pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "20")  Integer limit){
		Employee employee=(Employee) session.getAttribute("employee_session");
		model.addAttribute("employee", employee);
		Map<String, Object> map=new HashMap<>();
		map.put("get_employee_id", employee.getEmployee_id());
		List<PrivateMessage> messages_employee=privateMessageService.getPrivateMessageLianxiList(map);
		map.clear();
		map.put("employee_id", messages_employee.get(0).getSend_employee_id());
		Employee employee_duihua=employeeService.getEmployee(map);
		model.addAttribute("employee_duihua", employee_duihua);
		map.clear();
		map.put("employee_id", messages_employee.get(0).getGet_employee_id());
		map.clear();
		map.put("send_employee_id", messages_employee.get(0).getSend_employee_id());
		map.put("is_read", 0);
		List<PrivateMessage> messages_weidu=privateMessageService.getPrivateMessageList(map);
		for (PrivateMessage privateMessage : messages_weidu) {
			privateMessage.setIs_read(1);
			privateMessageService.updatePrivateMessage(privateMessage);
		}
		map.clear();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("all_xiangguan_id", messages_employee.get(0).getSend_employee_id());
		map.put("all_other_id", employee.getEmployee_id());
		List<PrivateMessage> messages_all=privateMessageService.getPrivateMessageList(map);
		for (PrivateMessage privateMessage : messages_all) {
			map.clear();
			map.put("employee_id", privateMessage.getSend_employee_id());
			Employee employee2=employeeService.getEmployee(map);
			privateMessage.setEmployee(employee2);
		}
		model.addAttribute("messages_all", messages_all);//所有接收到的信息-第一个联系人
		for (PrivateMessage privateMessage : messages_employee) {
			map.clear();
			map.put("employee_id", privateMessage.getSend_employee_id());
			Employee employee2=employeeService.getEmployee(map);
			privateMessage.setEmployee(employee2);
			map.clear();
			map.put("send_employee_id", privateMessage.getSend_employee_id());
			map.put("is_read", 0);
			int weidu=privateMessageService.getPrivateMessageNumber(map);
			privateMessage.setWeidu(weidu);
		}
		model.addAttribute("messages_employee", messages_employee);
		return "/employee_center/private_message";
	}
	/**
	* @Title: PrivateMessageController.java 
	* @Package com.jingren.jing.school.back.privatemessage 
	* @Description: TODO 获取当前对话
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 下午2:41:53 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_now_dangqian_duihua.jr")
	public String get_now_dangqian_duihua(Model model,HttpSession session,
			@RequestParam(value = "now_employee_id", required = false, defaultValue = "1")  Integer now_employee_id,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1")  Integer pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "20")  Integer limit){
		Employee employee=(Employee) session.getAttribute("employee_session");
		Map<String, Object> map=new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("all_xiangguan_id", now_employee_id);
		map.put("all_other_id", employee.getEmployee_id());
		List<PrivateMessage> messages_all=privateMessageService.getPrivateMessageList(map);
		for (PrivateMessage privateMessage : messages_all) {
			map.clear();
			map.put("employee_id", privateMessage.getSend_employee_id());
			Employee employee2=employeeService.getEmployee(map);
			privateMessage.setEmployee(employee2);
			if(privateMessage.getSend_employee_id()==now_employee_id){
				privateMessage.setIs_read(1);
				privateMessageService.updatePrivateMessage(privateMessage);
			}
		}
		model.addAttribute("messages_all", messages_all);//所有接收到的信息-第一个联系人
		map.clear();
		map.put("employee_id", now_employee_id);
		Employee employee_duihua=employeeService.getEmployee(map);
		model.addAttribute("employee_duihua", employee_duihua);
		return "/employee_center/now_duihua";
	}
	/**
	* @Title: PrivateMessageController.java 
	* @Package com.jingren.jing.school.back.privatemessage 
	* @Description: TODO 全部设置为已读
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月26日 下午4:00:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/set_all_yidu.jr")
	public void set_all_yidu(HttpSession session,HttpServletResponse response){
		Employee employee=(Employee) session.getAttribute("employee_session");
		Map<String, Object> map=new HashMap<>();
		map.put("get_employee_id", employee.getEmployee_id());
		map.put("is_read", 0);
		List<PrivateMessage> messages_weidu=privateMessageService.getPrivateMessageList(map);
		for (PrivateMessage privateMessage : messages_weidu) {
			privateMessage.setIs_read(1);
			privateMessageService.updatePrivateMessage(privateMessage);
		}
		ResponseUtils.renderText(response, "1");
	}
}
