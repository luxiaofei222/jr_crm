package com.jingren.jing.crm.controller.business;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.city.bean.City;
import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.bean.companyrecord.BusinessCallRecord;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.bean.customerplate.CustomerPlate;
import com.jingren.jing.crm.bean.mycompany.MyCompany;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.companyrecord.BusinessCallRecordService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.crm.service.customerplate.CustomerPlateService;
import com.jingren.jing.crm.service.mycompany.MyCompanyService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.role.Role;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.school.service.role.RoleService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

import net.sf.json.JSONArray;

/**
 * @Title: CrmBusinessController.java
 * @Package com.jingren.jing.crm.controller.business
 * @Description: TODO 企业库
 * @author 鲁晓飞 MR.Lu
 * @date 2016年12月21日 下午2:13:00
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("crm_business")
public class CrmBusinessController {
	@Resource
	private CompanyService companyService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private CityService cityService;
	@Resource
	private CustomerPlateService customerPlateService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private CustomerService customerService;
	@Resource
	private BusinessCallRecordService businessCallRecordService;
	@Resource
	private RoleService roleService;
	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private MyCompanyService myCompanyService;

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 获取企业库列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月21日 下午2:30:16
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_business.jr")
	public Callable<String> get_role_main(final Model model, final HttpSession session,
			@RequestParam(value = "employee_name", required = false) final String employee_name,
			@RequestParam(value = "company_name", required = false) final String company_name,
			@RequestParam(value = "limit", required = false) final Integer limit,
			@RequestParam(value = "pageNumber", required = false) final Integer pageNumber) {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				model.addAttribute("employee_name", employee_name);
				model.addAttribute("company_name", company_name);
				Map<String, Object> map = new HashMap<>();
				Employee employee_session = (Employee) session.getAttribute("employee_session");
				map.put("role_id", employee_session.getRole_id());
				Role role = roleService.getRole(map);
				model.addAttribute("role", role);
				model.addAttribute("limit", limit);
				map.clear();
				if (role.getRole_name().equals("市场专员")) {
					if (StringUtils.isNotBlank(company_name)) {
						map.put("company_name_seacher", company_name);
					}
				} else {
					if (StringUtils.isNotBlank(company_name)) {
						map.put("company_name", company_name);
					}
				}
				if (StringUtils.isNotBlank(employee_name)) {
					map.put("employee_name", employee_name);
					List<Employee> employees = employeeService.getEmployeeList(map);
					if (employees.size() > 0) {
						List<Company> companies = new ArrayList<>();
						map.clear();
						map.put("employee_id", employees.get(0).getEmployee_id());
						map.put("pageNumber", (pageNumber - 1) * limit);
						map.put("limit", limit);
						List<Company> companys = companyService.getCompanyList(map);
						Integer company_number = companyService.getCompanyNumber(map);
						for (Company company : companys) {
							map.clear();
							map.put("company_id", company.getCompany_id());
							Integer number = customerService.getCustomerNumber(map);
							Integer baokaonum = entryInfoService.getEntryInfoNumber(map);
							company.setLianxirennumber(number);
							company.setBaokaonumber(baokaonum);
						}
						companies.addAll(companys);
						Pagers<Company> pagers = new Pagers<Company>(company_number, pageNumber, limit);
						pagers.setList(companies);
						model.addAttribute("companies", pagers);
						model.addAttribute("employee_name", employee_name);
						model.addAttribute("employee_number_search", company_number);
					}

				} else {
					map.put("pageNumber", (pageNumber - 1) * limit);
					map.put("limit", limit);
					if (role.getRole_name().equals("市场专员")) {
						map.put("employee_id", employee_session.getEmployee_id());
					}
					List<Company> companys = companyService.getCompanyList(map);
					Integer company_number = companyService.getCompanyNumber(map);
					for (Company company : companys) {
						map.clear();
						map.put("company_id", company.getCompany_id());
						Integer number = customerService.getCustomerNumber(map);
						Integer baokaonum = entryInfoService.getEntryInfoNumber(map);
						company.setLianxirennumber(number);
						company.setBaokaonumber(baokaonum);
					}
					Pagers<Company> pagers = new Pagers<Company>(company_number, pageNumber, limit);
					pagers.setList(companys);
					model.addAttribute("companies", pagers);
				}
				map.clear();
				Integer total_number = companyService.getCompanyNumber(map);
				model.addAttribute("total_number", total_number);// 数据库总记录
				map.put("employee_id", employee_session.getEmployee_id());
				Integer employee_number = companyService.getCompanyNumber(map);
				model.addAttribute("employee_number", employee_number);// 该用户录入了多少
				return "/crm/business/business";
			}
		};
		
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 添加企业信息弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月21日 下午5:08:32
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_business.jr")
	public String to_add_business(Model model,
			@RequestParam(value="type",required=false)String type) {
		Map<String, Object> map = new HashMap<>();
		map.put("leveltype", 1);
		List<City> cities = cityService.getCityList(map);
		model.addAttribute("cities", cities);
		model.addAttribute("type", type);
		return "/crm/business/add_business";
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 获取城市列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月21日 下午5:08:22
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_city_erji.jr")
	public void get_city_erji(HttpServletResponse response, @RequestParam(value = "id", required = false) Integer id) {
		try {
			response.setCharacterEncoding("UTF-8");
			Map<String, Object> map = new HashMap<>();
			map.put("parentid", id);
			List<City> cities = cityService.getCityList(map);
			JSONArray json = JSONArray.fromObject(cities);
			String jsonStr = json.toString();
			response.getWriter().print(jsonStr);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 检测企业名称是否重复
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 上午8:44:56
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("check_company_name.jr")
	public void check_company_name(HttpServletResponse response,
			@RequestParam(value = "company_name", required = false) String company_name) {
		Map<String, Object> map = new HashMap<>();
		map.put("company_name_upload", company_name);
		List<Company> company1 = companyService.getCompanyList(map);
		if (company1.size()>0) {
			ResponseUtils.renderText(response, "2");// 不可用
		}else {
			ResponseUtils.renderText(response, "1");// 可用
		}
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 保存企业信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 上午9:12:46
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_company.jr")
	public void save_company(Company company, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "company_province_id", required = false) Integer company_province_id,
			@RequestParam(value = "str1", required = false) String str1) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		company.setEmployee_id(employee.getEmployee_id());
		company.setCompany_time(new Date());
		Map<String, Object> map = new HashMap<>();
		if (company_province_id != 0) {
			map.put("id", company_province_id);
			City city = cityService.getCity(map);
			company.setCompany_province(city.getName());// 省份名称
		} else {
			company.setCompany_province("0");
		}
		if (companyService.saveCompany(company)) {
			if(StringUtils.isNotBlank(type)){
				Employee employee_session = (Employee) session.getAttribute("employee_session");
				MyCompany mycompany=new MyCompany();
				mycompany.setEmployee_id(employee_session.getEmployee_id());
				mycompany.setOrganization_id(employee_session.getOrganization_id());
				map.clear();
				map.put("employee_id", employee_session.getEmployee_id());
//				Integer my_company_number=myCompanyService.getMycompanyNumber(map);
				map.clear();
				map.put("organization_id", employee_session.getOrganization_id());
				Organization organization=organizationService.getOranization(map);
				mycompany.setBumen_id(organization.getParent_id());
				mycompany.setMy_company_time(new Date());
				mycompany.setCompany_province(company.getCompany_province());
				mycompany.setSuoshuhangye(company.getSuoshuhangye());
				mycompany.setCompany_city(company.getCompany_city());
				mycompany.setCompany_id(company.getCompany_id());
				myCompanyService.saveMyCompany(mycompany);
			}
			if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(str1)) {
				CustomerPlate customerPlate = new CustomerPlate();
				customerPlate.setPlate_time(new Date());
				customerPlate.setCompany_id(company.getCompany_id());
				String[] array = str.split(",");
				String[] array1 = str1.split(",");
				for (int i = 0; i < array.length; i++) {
					if (StringUtils.isNotBlank(array[i]) && StringUtils.isNotBlank(array1[i])) {
						customerPlate.setPalte_title(array[i]);
						customerPlate.setPlate_content(array1[i]);
						customerPlateService.saveCustomerPlate(customerPlate);
					}
				}
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "1");
			}
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 删除企业信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 上午9:26:17
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_company.jr")
	public void delete_company(HttpServletResponse response,
			@RequestParam(value = "company_id", required = false) Integer company_id) {
		if (companyService.deleteCompany(company_id)) {
			customerPlateService.deleteCustomerPlate(company_id);
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 修改企业信息弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 上午9:54:58
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_business.jr")
	public String to_update_business(Model model,
			@RequestParam(value = "company_id", required = false) Integer company_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("company_id", company_id);
		Company company = companyService.getCompany(map);
		List<CustomerPlate> customerPlates = customerPlateService.getCustomerPlateList(map);
		model.addAttribute("customerPlates", customerPlates);
		model.addAttribute("company", company);
		map.clear();
		map.put("leveltype", 1);
		List<City> cities = cityService.getCityList(map);
		model.addAttribute("cities", cities);
		map.clear();
		map.put("name", company.getCompany_province());
		City city = cityService.getCity(map);
		if (city != null) {
			map.clear();
			map.put("parentid", city.getId());
			List<City> citiessub = cityService.getCityList(map);
			model.addAttribute("citiessub", citiessub);
		} else {
			model.addAttribute("citiessub", null);
		}

		return "/crm/business/update_business";
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 修改企业信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 上午10:34:01
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("update_company.jr")
	public void update_company(Company company, HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "company_province_id", required = false) Integer company_province_id,
			@RequestParam(value = "str1", required = false) String str1) {
		Map<String, Object> map = new HashMap<>();
		if (company_province_id != 0) {
			map.put("id", company_province_id);
			City city = cityService.getCity(map);
			company.setCompany_province(city.getName());// 省份名称
		} else {
			company.setCompany_province("0");
		}
		if (companyService.updateCompany(company)) {
			customerPlateService.deleteCustomerPlate(company.getCompany_id());
			if (StringUtils.isNotBlank(str) && StringUtils.isNotBlank(str1)) {
				CustomerPlate customerPlate = new CustomerPlate();
				customerPlate.setPlate_time(new Date());
				customerPlate.setCompany_id(company.getCompany_id());
				String[] array = str.split(",");
				String[] array1 = str1.split(",");
				for (int i = 0; i < array.length; i++) {
					if (StringUtils.isNotBlank(array[i]) && StringUtils.isNotBlank(array1[i])) {
						customerPlate.setPalte_title(array[i]);
						customerPlate.setPlate_content(array1[i]);
						customerPlateService.saveCustomerPlate(customerPlate);
					}
				}
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "1");
			}
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 添加联系人弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 上午10:42:02
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_lianxiren.jr")
	public String to_add_lianxiren(Model model,
			@RequestParam(value = "company_id", required = false) Integer company_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());// 默认第一个课程二级分类
		List<CourseMenu> coursesubMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		model.addAttribute("company_id", company_id);
		return "/crm/business/add_lianxiren";
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 保存联系人信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 上午10:54:46
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_lianxiren.jr")
	public void save_lianxiren(HttpServletResponse response, Customer customer, HttpSession session) {
		Employee employee = (Employee) session.getAttribute("employee_session");
		customer.setEmployee_id(employee.getEmployee_id());
		customer.setCustomer_time(new Date());
		if (customerService.saveCustomer(customer)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 查看企业详细信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 下午1:13:19
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_business_detail.jr")
	public Callable<String> check_business_detail(final Model model,
			@RequestParam(value = "company_id", required = false) final Integer company_id,
			@RequestParam(value = "limit", required = false) final Integer limit,
			@RequestParam(value = "pageNumber", required = false) final Integer pageNumber) {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				Map<String, Object> map = new HashMap<>();
				map.put("company_id", company_id);
				Company company = companyService.getCompany(map);
				List<CustomerPlate> customerPlates = customerPlateService.getCustomerPlateList(map);
				model.addAttribute("customerPlates", customerPlates);
				model.addAttribute("company", company);
				map.put("pageNumber", (pageNumber - 1) * limit);
				map.put("limit", limit);
				List<Customer> customers = customerService.getCustomerList(map);
				Integer customers_number = customerService.getCustomerNumber(map);
				Pagers<Customer> pagers = new Pagers<Customer>(customers_number, pageNumber, limit);
				pagers.setList(customers);
				model.addAttribute("customers", pagers);
				return "/crm/business/check_business";
			}
		};
		
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 修改联系人弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 下午1:40:07
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_customer.jr")
	public String to_update_customer(Model model,
			@RequestParam(value = "customer_id", required = false) Integer customer_id,
			@RequestParam(value = "type", required = false) Integer type) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("customer_id", customer_id);
		Customer customer = customerService.getCustomer(map);
		model.addAttribute("customer", customer);
		model.addAttribute("type", type);
		map.clear();
		if(customer!=null){
			if (customer.getCourse_id() != null) {
				map.put("course_id", customer.getCourse_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				model.addAttribute("courseMenu", courseMenu);
			}
		}
		return "/crm/business/update_lianxiren";
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 修改联系人信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 下午1:40:59
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_lianxiren.jr")
	public void update_lianxiren(Customer customer, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "calltype", required = false) Integer calltype) {
		if (calltype != null) {
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
			Employee employee = (Employee) session.getAttribute("employee_session");
			Map<String, Object> map = new HashMap<>();
			map.put("customer_id", customer.getCustomer_id());
			Customer customer2 = customerService.getCustomer(map);
			if(StringUtils.isNotBlank(customer2.getCustomer_note())){
				customer.setCustomer_note(customer2.getCustomer_note() + "|" + employee.getEmployee_name()
				+ "("+employee.getZuoxi()+")" + "于" + dateString + "追加备注：" + customer.getCustomer_note());
			}else{
				customer.setCustomer_note(employee.getEmployee_name()
				+ "("+employee.getZuoxi()+")"+ "于" + dateString + "追加备注：" + customer.getCustomer_note());
			}
			
		}
		if (customerService.updateCustomer(customer)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 查看联系人信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 下午2:14:33
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_lianxiren.jr")
	public String to_check_lianxiren(Model model,HttpSession session, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "customer_id", required = false) Integer customer_id) {
		Employee employee=(Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("customer_id", customer_id);
		Customer customer = customerService.getCustomer(map);
		model.addAttribute("customer", customer);
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("employee_id", employee.getEmployee_id());
		List<BusinessCallRecord> records = businessCallRecordService.getBusinessCallRecordList(map);
		Integer records_number = businessCallRecordService.getBusinessCallRecordNumber(map);
		Pagers<BusinessCallRecord> pagers = new Pagers<BusinessCallRecord>(records_number, pageNumber, limit);
		pagers.setList(records);
		model.addAttribute("records", pagers);
		map.clear();
		if (customer.getCourse_id() != null) {
			map.put("course_id", customer.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			model.addAttribute("courseMenu", courseMenu);
		}
		return "/crm/business/check_lianxiren";
	}
	

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 通话记录
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月16日 下午1:13:19
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_record_list.jr")
	public Callable<String> get_record_list(final Model model,final HttpSession session, @RequestParam(value = "limit", required = false) final Integer limit,
			@RequestParam(value = "pageNumber", required = false) final Integer pageNumber,
			@RequestParam(value = "customer_id", required = false) final Integer customer_id) {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				Employee employee=(Employee) session.getAttribute("employee_session");
				Map<String, Object> map = new HashMap<>();
				map.put("customer_id", customer_id);
				map.put("pageNumber", (pageNumber - 1) * limit);
				map.put("limit", limit);
				map.put("employee_id", employee.getEmployee_id());
				List<BusinessCallRecord> records = businessCallRecordService.getBusinessCallRecordList(map);
				Integer records_number = businessCallRecordService.getBusinessCallRecordNumber(map);
				Pagers<BusinessCallRecord> pagers = new Pagers<BusinessCallRecord>(records_number, pageNumber, limit);
				pagers.setList(records);
				model.addAttribute("records", pagers);
				return "/crm/business/record_list";
			}
		};
		
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 删除联系人信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月22日 下午4:24:36
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_customer.jr")
	public void delete_customer(HttpServletResponse response,
			@RequestParam(value = "customer_id", required = false) Integer customer_id) {
		if (customerService.deleteCustomer(customer_id)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 导入企业信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月23日 上午9:06:36
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_upload_business.jr")
	public String to_upload_business() {

		return "/crm/business/import_business";
	}

	/**
	 * @Title: CrmBusinessController.java
	 * @Package com.jingren.jing.crm.controller.business
	 * @Description: TODO 点击通话弹屏
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年2月16日 上午9:26:14
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_call_tanping.jr")
	public Callable<String> to_call_tanping(final Model model,final HttpSession session, @RequestParam(value = "limit", required = false) final Integer limit,
			@RequestParam(value = "pageNumber", required = false) final Integer pageNumber,
			@RequestParam(value = "customer_id", required = false) final Integer customer_id,
			@RequestParam(value = "record_id", required = false) final Integer record_id) {
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				Map<String, Object> map = new HashMap<>();
				Employee employee=(Employee) session.getAttribute("employee_session");
				map.put("customer_id", customer_id);
				Customer customer = customerService.getCustomer(map);
				model.addAttribute("customer", customer);
				map.put("pageNumber", (pageNumber - 1) * limit);
				map.put("limit", limit);
				map.put("employee_id", employee.getEmployee_id());
				List<BusinessCallRecord> records = businessCallRecordService.getBusinessCallRecordList(map);
				Integer records_number = businessCallRecordService.getBusinessCallRecordNumber(map);
				Pagers<BusinessCallRecord> pagers = new Pagers<BusinessCallRecord>(records_number, pageNumber, limit);
				pagers.setList(records);
				model.addAttribute("records", pagers);
				map.clear();
				if(customer!=null){
					if (customer.getCourse_id() != null) {
						map.put("course_id", customer.getCourse_id());
						CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
						model.addAttribute("courseMenu", courseMenu);
					}
				}
				model.addAttribute("record_id", record_id);
				return "/crm/mybusiness/tanping_call";
			}
		};
		
	}

	/**
	 * @Title: CrmCallController.java
	 * @Package com.jingren.jing.crm.controller.call
	 * @Description: TODO 添加跟进内容
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年2月17日 下午2:16:13
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/add_note_business_call.jr")
	public void add_note_business_call(HttpServletResponse response,
			@RequestParam(value = "record_id", required = false) Integer record_id,
			@RequestParam(value = "record_note", required = false) String record_note,
			@RequestParam(value = "genjin_state", required = false) String genjin_state) {
		BusinessCallRecord businessCallRecord = new BusinessCallRecord();
		businessCallRecord.setRecord_id(record_id);
		businessCallRecord.setRecord_note(record_note);
		businessCallRecord.setGenjin_state(genjin_state);
		if (businessCallRecordService.updateBusinessCallRecord(businessCallRecord)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "2");
		}
	}
}
