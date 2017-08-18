package com.jingren.jing.school.back.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.goeasy.GoEasyPush;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.ResponseUtils;
import com.uwantsoft.goeasy.client.goeasyclient.GoEasy;

@Controller
@RequestMapping()
public class BackLoginController {

	@Resource
	private EmployeeService employeeService;
	/**
	* @Title: BackLoginController.java 
	* @Package com.jingren.jing.school.back.login 
	* @Description: TODO 跳转到登录页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 下午4:08:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("login.jr")
	public String get_sc_back(HttpSession session){
		Employee employee=(Employee) session.getAttribute("employee_session");
		if(employee!=null){
			return "redirect:/center/get_employee_center.jr";
		}else{
			return "/login/login";
		}
		
	}
	/**
	* @Title: BackLoginController.java 
	* @Package com.jingren.jing.school.back.login 
	* @Description: TODO 登录系统
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 下午4:29:58 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/login_employee.jr")
	public String login_employee(Employee employee,Model model,HttpSession session){
		Map<String, Object> map=new HashMap<String, Object>();
		if(StringUtils.isNotBlank(employee.getLogin_name())){
			map.put("login_name", employee.getLogin_name());
			Employee employee2=employeeService.getEmployee(map);
			if(employee2!=null){
				if(employee2.getLogin_password().equals(DigestUtils.shaHex(employee.getLogin_password()))){
					if(employee2.getEmployee_state().equals("在职")){
						session.removeAttribute("employee_session");
						session.setAttribute("employee_session", employee2);
						employee2.setJifen(employee2.getJifen()+1);
						employeeService.updateEmployee(employee2);//登录一次积分+1
						return "redirect:/center/get_employee_center.jr";
					}else{
						model.addAttribute("error", "您已经离职，无法登录了！");
						return "redirect:/login.jr";
					}
				}else{
					model.addAttribute("error", "您输入的密码错误！");
					return "redirect:/login.jr";
				}
			}else{
				model.addAttribute("error", "您输入的用户名错误！");
				return "redirect:/login.jr";
			}
		}else{
			model.addAttribute("error", "请输入用户名！");
			return "redirect:/login.jr";
		}
	}
	/**
	* @Title: BackLoginController.java 
	* @Package com.jingren.jing.school.back.login 
	* @Description: TODO  退出系统
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月14日 下午4:30:10 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/logout.jr")
	public void logout_user(HttpSession session,HttpServletResponse response){
		session.removeAttribute("employee_session");
		ResponseUtils.renderText(response, "1");
	}
}
