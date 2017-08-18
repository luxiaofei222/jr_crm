package com.jingren.jing.school.back.employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.common.zuoxi.Zuoxi;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.crm.service.mycompany.MyCompanyService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.role.Role;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.school.service.role.RoleService;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

import net.sf.json.JSONArray;

/**
 * @Title: EmployeeController.java
 * @Package com.jingren.jing.school.back.employee
 * @Description: TODO 员工管理
 * @author 鲁晓飞 MR.Lu
 * @date 2016年12月5日 上午8:39:24
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_employee")
public class EmployeeController {

	@Resource
	private RoleService roleService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private MyCompanyService myCompanyService;
	@Resource
	private CustomerService customerService;

	// private final static String
	// pic_employee="/images/school/front/index/user_moren.png";//默认头像
	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 员工管理
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 上午8:58:33
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_employee_main.jr")
	public String get_employee_main(Model model,HttpServletRequest request,
			@RequestParam(value = "employee_state", required = false) String employee_state,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "role_id", required = false) Integer role_id,
			@RequestParam(value = "employee_sex", required = false) String employee_sex,
			@RequestParam(value = "employee_name", required = false) String employee_name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(employee_name)) {
			map.put("employee_name", employee_name);
			model.addAttribute("employee_name", employee_name);
		}
		if (StringUtils.isNotBlank(employee_sex)) {
			map.put("employee_sex", employee_sex);
			model.addAttribute("employee_sex", employee_sex);
		}
		if (StringUtils.isNotBlank(employee_state)) {
			map.put("employee_state", employee_state);
			model.addAttribute("employee_state", employee_state);
		}
		if (role_id != null) {
			map.put("role_id", role_id);
			model.addAttribute("role_id", role_id);
		}
		if (bumen_id != null) {
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<Employee> employees = employeeService.getEmployeeList(map);
		Integer em_number = employeeService.getEmployeeNumber(map);
		for (Employee employee : employees) {
			map.clear();
			map.put("role_id", employee.getRole_id());
			Role role = roleService.getRole(map);
			employee.setRole(role);// 获取所属角色
			map.clear();
			map.put("organization_id", employee.getOrganization_id());
			Organization organization = organizationService.getOranization(map);
			employee.setOrganization(organization);// 获取所属部门
			if (employee.getZuoxi().equals("0")) {
				employee.setZuoxi("暂未设置");
			}
		}
		Pagers<Employee> pagers = new Pagers<Employee>(em_number, pageNumber, limit);
		pagers.setList(employees);
		model.addAttribute("employees", pagers);
		// 一下是获取搜索条件
		map.clear();
		List<Role> roles = roleService.getRoleList(map);
		model.addAttribute("roles", roles);
		map.put("organization_level", 1);// 获取所有部门
		List<Organization> organizations = organizationService.getOranizationList(map);
		model.addAttribute("organizations", organizations);
		String ip=GetIPUtil.getIpAddress(request);
		model.addAttribute("limit", limit);
		if(ip.equals("110.249.251.98")){
			model.addAttribute("is_ip", "1");
		}else{
			model.addAttribute("is_ip", "0");
		}
		map.clear();
		Integer all_number = employeeService.getEmployeeNumber(map);//所有员工数量
		map.put("employee_state_cen", "在职");
		Integer zaizhi_number=employeeService.getEmployeeNumber(map);
		model.addAttribute("em_number", all_number);
		model.addAttribute("zaizhi_number", zaizhi_number);
		return "/employee/employee_main";
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 获取员工工号
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月13日 下午3:54:17
	 * @version 网校+CRM系统 V1.0
	 */
	public String get_job_number() {
		String number = employeeService.getEmployeeJobnumber();// 获取最大的工号
		if (StringUtils.isBlank(number)) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String dateString = formatter.format(new Date());
			number = dateString + "10001";
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			String qian = number.substring(0, 8);
			Integer hou = Integer.valueOf(number.substring(8, number.length()));
			String dateString = formatter.format(new Date());
			if (dateString.equals(qian)) {
				String a = String.valueOf(hou + 1);
				number = dateString + a;
			} else {
				String a = "10001";
				number = dateString + a;
			}
		}
		return number;
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 去添加员工账号
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 上午9:06:24
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_employee.jr")
	public String to_add_employee(Model model) {
		Map<String, Object> map = new HashMap<>();
		List<Role> roles = roleService.getRoleList(map);
		model.addAttribute("roles", roles);
		map.put("organization_level", 1);// 获取所有部门
		List<Organization> organizations = organizationService.getOranizationList(map);
		model.addAttribute("organizations", organizations);
		return "/employee/add_employee";
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 获取岗位
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 上午9:36:03
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sun_oragn.jr")
	public String get_sun_oragn(Model model, @RequestParam(value = "organ_id", required = false) Integer organ_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", organ_id);// 获取岗位sub_level
		map.put("sub_level", 2);
		List<Organization> organizations = organizationService.getOranizationList(map);
		for (Organization organization : organizations) {
			map.clear();
			map.put("parent_id", organization.getOrganization_id());
			map.put("sub_level", 2);
			List<Organization> organizations_sub = organizationService.getOranizationList(map);
			if (organizations_sub.size() > 0) {
				organization.setOrganizations_sub(organizations_sub);
			}
		}
		model.addAttribute("organizations", organizations);
		return "/employee/sub_organization";
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 检查账号是否存在
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 上午10:03:12
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_employee.jr")
	public void check_employee(HttpServletResponse response,
			@RequestParam(value = "login_name", required = false) String login_name) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(login_name)) {
			map.put("login_name", login_name);
			Employee employee = employeeService.getEmployee(map);
			if (employee != null) {// 账号存在
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "2");
			}
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 保存员工信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 上午10:16:54
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_employee.jr")
	public void save_employee(Employee employee, HttpServletResponse response) {
		employee.setEmployee_time(new Date());
		employee.setLogin_password(DigestUtils.shaHex(employee.getLogin_password()));
		// employee.setEmployee_pic(pic_employee);
		employee.setEmployee_name(employee.getEmployee_name().replace(" ", ""));
		employee.setJob_number(get_job_number());// 添加工号
		employee.setZuoxi("0");
		Map<String, Object> map=new HashMap<>();
		map.put("organization_id", employee.getOrganization_id());
		Organization organization=organizationService.getOranization(map);
		employee.setParent_id(organization.getBumen_id());
		employee.setBumen_id(organization.getParent_id());
		if (employeeService.saveEmployee(employee)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 删除员工信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 上午11:49:56
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_employee.jr")
	public void delete_employee(HttpServletResponse response,
			@RequestParam(value = "employee_id", required = false) Integer employee_id) {
		if (employeeService.deleteEmployee(employee_id)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 禁用账号
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 上午11:57:41
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/jinyong_employee.jr")
	public void jinyong_employee(HttpServletResponse response,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "employee_id", required = false) Integer employee_id) {
		Employee employee = new Employee();
		employee.setEmployee_state(type);
		employee.setEmployee_id(employee_id);
		if (employeeService.updateEmployee(employee)) {
			myCompanyService.deleteCompanyByEmployee_id(employee_id);
			Map<String, Object> map=new HashMap<>();
			map.put("employee_id_lizhi", employee_id);
			map.put("is_my_customer", 1);
			List<Customer> customers=customerService.getCustomerList(map);
			for (Customer customer : customers) {//员工离职后去除重复的电话联系人
				if(StringUtils.isNotBlank(customer.getCustomer_phone())){
					map.clear();
					map.put("employee_id_lizhi", employee_id);
					map.put("customer_phone_call", customer.getCustomer_phone());
					List<Customer> customers2=customerService.getCustomerList(map);
					if(customers2.size()>1){
						for (int i = 1; i < customers2.size(); i++) {
							if(StringUtils.isBlank(customers2.get(i).getCustomer_note())){
								customerService.deleteCustomer(customers2.get(i).getCustomer_id());
							}
						}
					}
				}else if(StringUtils.isNotBlank(customer.getCustomer_officephone())){
					map.clear();
					map.put("employee_id_lizhi", employee_id);
					map.put("customer_officephone_call", customer.getCustomer_officephone());
					List<Customer> customers2=customerService.getCustomerList(map);
					if(customers2.size()>1){
						for (int i = 1; i < customers2.size(); i++) {
							if(StringUtils.isBlank(customers2.get(i).getCustomer_note())){
								customerService.deleteCustomer(customers2.get(i).getCustomer_id());
							}
						}
					}
				}
			}
			customerService.updateCustomerLizhi(employee_id);//该用户添加的联系人充公
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 去修改员工
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 下午1:11:37
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_employee.jr")
	public String to_update_employee(Model model,
			@RequestParam(value = "employee_id", required = false) Integer employee_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee_id);
		Employee employee = employeeService.getEmployee(map);
		map.clear();
		map.put("role_id", employee.getRole_id());
		Role role = roleService.getRole(map);
		map.clear();
		map.put("organization_id", employee.getOrganization_id());
		Organization organization = organizationService.getOranization(map);
		map.clear();
		map.put("bumen_id", organization.getBumen_id());
		Organization bumen = organizationService.getOranization(map);
		employee.setRole(role);
		employee.setOrganization(organization);
		model.addAttribute("employee", employee);// 获取要修改的员工
		model.addAttribute("bumen", bumen);
		map.clear();
		List<Role> roles = roleService.getRoleList(map);
		model.addAttribute("roles", roles);
		map.put("organization_level", 1);// 获取所有部门
		List<Organization> organizations = organizationService.getOranizationList(map);
		model.addAttribute("organizations", organizations);
		return "/employee/update_employee";
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 修改员工信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月5日 下午1:18:30
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_employee.jr")
	public void update_employee(Employee employee, HttpServletResponse response) {
		Map<String, Object> map=new HashMap<>();
		if(employee.getOrganization_id()!=null){
			map.put("organization_id", employee.getOrganization_id());
			Organization organization=organizationService.getOranization(map);
			employee.setParent_id(organization.getBumen_id());
			employee.setBumen_id(organization.getParent_id());
		}
		if (employeeService.updateEmployee(employee)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 获取用户组织结构
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月26日 下午6:31:04
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_organzition.jr")
	public String get_organzition(Model model, HttpSession session) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("organization_id", employee.getOrganization_id());
		Organization organization = organizationService.getOranization(map);
		model.addAttribute("organization", organization);
		map.clear();
		map.put("organization_id", organization.getParent_id());
		Organization organization_pa = organizationService.getOranization(map);
		model.addAttribute("organization_pa", organization_pa);
		return "/common/organ";

	}

	/**
	 * @Title: EmployeeController.java
	 * @Package com.jingren.jing.school.back.employee
	 * @Description: TODO 设置坐席号弹出页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年2月16日 上午11:26:36
	 * @version 网校+CRM系统 V1.0
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping("/get_zuoxi_list.jr")
	public String get_zuoxi_list(Model model,HttpServletRequest request,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "zuoxi_list", required = false) String zuoxi_list) {
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee_id);
		Employee employee = employeeService.getEmployee(map);
		model.addAttribute("employee", employee);
		JSONArray jsonArray = new JSONArray();
		jsonArray = jsonArray.fromObject(zuoxi_list);
		List<Zuoxi> zuoxis = (List<Zuoxi>)JSONArray.toCollection(jsonArray, Zuoxi.class);//jsaonarray转Java对象
		for (Zuoxi zuoxi : zuoxis) {
			map.clear();
			map.put("zuoxi", zuoxi.getExtension());
			Employee employee2 = employeeService.getEmployee(map);
			if(employee2!=null){
				//zuoxi.setState(1);//已经被绑定
				zuoxi.setEmployee(employee2);
			}
		}
		model.addAttribute("zuoxi_list", zuoxis);
		return "/employee/set_zuoxi";
	}
}
