package com.jingren.jing.crm.controller.mybusiness;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.bean.customerplate.CustomerPlate;
import com.jingren.jing.crm.bean.mycompany.MyCompany;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.crm.service.customerplate.CustomerPlateService;
import com.jingren.jing.crm.service.mycompany.MyCompanyService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
 * @Title: BackMyBusinessController.java
 * @Package com.jingren.jing.crm.controller.mybusiness
 * @Description: TODO 业务人员企业选择以及加入企业
 * @author 鲁晓飞 MR.Lu
 * @date 2017年1月16日 上午9:14:40
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_mybusiness")
public class BackMyBusinessController {

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
	private MyCompanyService myCompanyService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private EntryInfoService entryInfoService;

	/**
	 * @Title: BackMyBusinessController.java
	 * @Package com.jingren.jing.crm.controller.mybusiness
	 * @Description: TODO 企业库备选区
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月16日 上午9:53:15
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_select_business.jr")
	public String get_select_business(final Model model, final HttpSession session,
			@RequestParam(value = "company_province", required = false)final String company_province,
			@RequestParam(value = "company_name", required = false)final String company_name,
			@RequestParam(value = "company_city", required = false)final String company_city,
			@RequestParam(value = "suoshuhangye", required = false)final String suoshuhangye,
			@RequestParam(value = "employee_name", required = false)final String employee_name,
			@RequestParam(value = "limit", required = false)final Integer limit,
			@RequestParam(value = "pageNumber", required = false)final Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		Employee employee_session = (Employee) session.getAttribute("employee_session");
		if (StringUtils.isNotBlank(company_name)) {
			map.put("company_name", company_name);
			model.addAttribute("company_name", company_name);
		}
		if (StringUtils.isNotBlank(employee_name)) {
			map.put("employee_name_crm", employee_name);
			List<Employee> employee = employeeService.getEmployeeList(map);
			map.clear();
			model.addAttribute("employee_name", employee_name);
			if (employee.size() > 0) {
				map.put("employee_id", employee.get(0).getEmployee_id());
			} else {
				map.put("employee_id", 0);
			}

		}
		if (StringUtils.isNotBlank(company_province)) {
			map.put("company_province", company_province);
			model.addAttribute("company_province", company_province);
		}
		if (StringUtils.isNotBlank(company_city)) {
			map.put("company_city", company_city);
			model.addAttribute("company_city", company_city);
		}
		if (StringUtils.isNotBlank(suoshuhangye)) {
			map.put("suoshuhangye", suoshuhangye);
			model.addAttribute("suoshuhangye", suoshuhangye);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		model.addAttribute("limit", limit);
		Integer company_number = companyService.getCompanyNumber(map);
		List<Company> companys = companyService.getCompanyList(map);
		for (int i = 0; i < companys.size(); i++) {
			try {
			map.clear();
			map.put("organization_id", employee_session.getOrganization_id());
			Organization organization = organizationService.getOranization(map);
			map.clear();
			map.put("company_id", companys.get(i).getCompany_id());
			map.put("bumen_id", organization.getParent_id());
			int companiesnumber = myCompanyService.getMycompanyNumber(map);
			map.put("employee_id", employee_session.getEmployee_id());
			if (companiesnumber > 0) {
				companys.get(i).setIs_jiaru_mybusiness("是");
			} else {
					MyCompany myCompany = myCompanyService.getMyCompany(map);
					if (myCompany != null) {
						companys.get(i).setIs_jiaru_mybusiness("是");
					} else {
						companys.get(i).setIs_jiaru_mybusiness("否");
					}
			}
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
		Pagers<Company> pagers = new Pagers<Company>(company_number, pageNumber, limit);
		pagers.setList(companys);
		model.addAttribute("companies", pagers);
		map.clear();
		map.put("employee_id", employee_session.getEmployee_id());
		Integer my_company_number = myCompanyService.getMycompanyNumber(map);
		model.addAttribute("my_company_number", my_company_number);
		return "/crm/mybusiness/mybusiness";
	}

	/**
	 * @Title: BackMyBusinessController.java
	 * @Package com.jingren.jing.crm.controller.mybusiness
	 * @Description: TODO 加入企业信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月16日 上午10:07:09
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_my_company.jr")
	public void save_my_company(MyCompany mycompany, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "str", required = false) String str) {
		Employee employee_session = (Employee) session.getAttribute("employee_session");
		mycompany.setEmployee_id(employee_session.getEmployee_id());
		mycompany.setOrganization_id(employee_session.getOrganization_id());
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", employee_session.getEmployee_id());
		// Integer my_company_number=myCompanyService.getMycompanyNumber(map);
		map.clear();
		map.put("organization_id", employee_session.getOrganization_id());
		Organization organization = organizationService.getOranization(map);
		mycompany.setBumen_id(organization.getParent_id());
		mycompany.setMy_company_time(new Date());
		// if(my_company_number>50){
		// ResponseUtils.renderText(response, "2");//超过限额
		// }else{
		if (StringUtils.isNotBlank(str)) {
			String[] arraystr = str.split(",");
			for (String string : arraystr) {
				Integer companyid = Integer.valueOf(string);
				map.clear();
				map.put("company_id", companyid);
				Company company = companyService.getCompany(map);
				if (company != null) {
					mycompany.setCompany_name(company.getCompany_name().replace(" ", ""));
					mycompany.setCompany_province(company.getCompany_province());
					mycompany.setSuoshuhangye(company.getSuoshuhangye());
					mycompany.setCompany_city(company.getCompany_city());
					mycompany.setCompany_id(companyid);
					myCompanyService.saveMyCompany(mycompany);
				}
			}
			ResponseUtils.renderText(response, "1");
		} else {
			map.clear();
			map.put("company_id", mycompany.getCompany_id());
			Company company = companyService.getCompany(map);
			if (company != null) {
				mycompany.setCompany_name(company.getCompany_name());
				mycompany.setCompany_province(company.getCompany_province());
				mycompany.setSuoshuhangye(company.getSuoshuhangye());
				mycompany.setCompany_city(company.getCompany_city());
				if (myCompanyService.saveMyCompany(mycompany)) {
					ResponseUtils.renderText(response, "1");
				} else {
					ResponseUtils.renderText(response, "0");
				}
			}
		}
		// }
	}

	/**
	 * @Title: BackMyBusinessController.java
	 * @Package com.jingren.jing.crm.controller.mybusiness
	 * @Description: TODO 获取我的企业信息列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月16日 上午11:09:14
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_my_business_list.jr")
	public Callable<String> get_my_business_list(final Model model, final HttpSession session,
			@RequestParam(value = "qiye_chaxun", required = false,defaultValue="kong") final String qiye_chaxun,
			@RequestParam(value = "company_province", required = false) final String company_province,
			@RequestParam(value = "suoshuhangye", required = false)final String suoshuhangye,
			@RequestParam(value = "company_city", required = false) final String company_city,
			@RequestParam(value = "company_name", required = false) final String company_name,
			@RequestParam(value = "limit", required = false) final Integer limit,
			@RequestParam(value = "pageNumber", required = false)final Integer pageNumber) {
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, Object> map = new HashMap<>();
				Employee employee_session = (Employee) session.getAttribute("employee_session");
				model.addAttribute("limit", limit);
				map.clear();
				model.addAttribute("qiye_chaxun", qiye_chaxun);
				if(qiye_chaxun.equals("jingque")){
					if(StringUtils.isNotBlank(company_name)){
						if (StringUtils.isNotBlank(company_name)) {
							// map.put("company_name", company_name);
							// Company company=companyService.getCompany(map);
							map.put("company_name_upload", company_name);
							List<Company> company1 = companyService.getCompanyList(map);
							map.clear();
							if (company1.size() > 0) {
								map.put("company_id", company1.get(0).getCompany_id());
							} else {
								map.put("company_id", 0);
							}
							model.addAttribute("company_name", company_name);
						}
					}else{
						if (StringUtils.isNotBlank(company_province)) {
							map.put("company_province", company_province);
							model.addAttribute("company_province", company_province);
						}
						if (StringUtils.isNotBlank(company_city)) {
							map.put("company_city", company_city);
							model.addAttribute("company_city", company_city);
						}
						if (StringUtils.isNotBlank(suoshuhangye)) {
							map.put("suoshuhangye", suoshuhangye);
							model.addAttribute("suoshuhangye", suoshuhangye);
						}
					}
					
				}else{
					if (StringUtils.isNotBlank(company_province)) {
						map.put("company_province", company_province);
						model.addAttribute("company_province", company_province);
					}
					if (StringUtils.isNotBlank(company_name)) {
						map.put("is_flag", 1);
						map.put("company_name", company_name);
						model.addAttribute("company_name", company_name);
					}
					if (StringUtils.isNotBlank(company_city)) {
						map.put("company_city", company_city);
						model.addAttribute("company_city", company_city);
					}
					if (StringUtils.isNotBlank(suoshuhangye)) {
						map.put("suoshuhangye", suoshuhangye);
						model.addAttribute("suoshuhangye", suoshuhangye);
					}
				}
				map.put("pageNumber", (pageNumber - 1) * limit);
				map.put("limit", limit);
				map.put("employee_id", employee_session.getEmployee_id());
				List<MyCompany> myCompanies = myCompanyService.getMyCompanyList(map);
				Integer my_company_number = myCompanyService.getMycompanyNumber(map);
				if (myCompanies.size() > 0) {
					for (MyCompany mycompany : myCompanies) {
						map.clear();
						if (mycompany.getCompany() != null) {
							map.put("employee_id", employee_session.getEmployee_id());
							map.put("company_id", mycompany.getCompany().getCompany_id());
							Integer number = customerService.getCustomerNumber(map);
							Integer baokaonum = entryInfoService.getEntryInfoNumber(map);
							mycompany.getCompany().setLianxirennumber(number);
							mycompany.getCompany().setBaokaonumber(baokaonum);
						} else {
							myCompanyService.deleteCompany(mycompany.getMy_company_id());
							// myCompanies.remove(mycompany);
						}
					}
				}
				Pagers<MyCompany> pagers = new Pagers<MyCompany>(my_company_number, pageNumber, limit);
				pagers.setList(myCompanies);
				model.addAttribute("companies", pagers);
				model.addAttribute("my_company_number", my_company_number);
				try {
					finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return "/crm/mybusiness/my_business_list";
			}
		};
		
	}

	/**
	 * @Title: BackMyBusinessController.java
	 * @Package com.jingren.jing.crm.controller.mybusiness
	 * @Description: TODO 移除企业信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月16日 上午11:30:15
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_my_business_list.jr")
	public void delete_my_business_list(MyCompany mycompany, HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "my_company_id", required = false) Integer my_company_id) {
		if (StringUtils.isNotBlank(str)) {
			String[] arraystr = str.split(",");
			for (String string : arraystr) {
				Integer companyid = Integer.valueOf(string);
				myCompanyService.deleteCompany(companyid);
			}
			ResponseUtils.renderText(response, "1");
		} else {
			if (myCompanyService.deleteCompany(my_company_id)) {
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "0");
			}
		}
	}

	/**
	 * @Title: BackMyBusinessController.java
	 * @Package com.jingren.jing.crm.controller.mybusiness
	 * @Description: TODO 查看企业联系人
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月16日 上午11:59:02
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_business_detail.jr")
	public String check_business_detail(Model model, HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "company_province", required = false) final String company_province,
			@RequestParam(value = "suoshuhangye", required = false)final String suoshuhangye,
			@RequestParam(value = "company_city", required = false) final String company_city,
			@RequestParam(value = "company_name", required = false) final String company_name,
			@RequestParam(value = "company_id", required = false) Integer company_id,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "company_limit", required = false) Integer company_limit,
			@RequestParam(value = "company_page", required = false) Integer company_page) {
		model.addAttribute("company_province", company_province);
		model.addAttribute("suoshuhangye", suoshuhangye);
		model.addAttribute("company_city", company_city);
		model.addAttribute("company_name", company_name);
		Map<String, Object> map = new HashMap<>();
		map.put("company_id", company_id);
		Company company = companyService.getCompany(map);
		List<CustomerPlate> customerPlates = customerPlateService.getCustomerPlateList(map);
		model.addAttribute("customerPlates", customerPlates);
		model.addAttribute("company", company);
		model.addAttribute("company_limit", company_limit);
		model.addAttribute("company_page", company_page);
		if (pageNumber != null && limit != null) {
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
		} else {
			map.put("pageNumber", 0);
			map.put("limit", 20);
		}
		Employee employee_session = (Employee) session.getAttribute("employee_session");
		map.put("employee_id", employee_session.getEmployee_id());
		List<Customer> customers = customerService.getCustomerList(map);
		Integer customers_number = customerService.getCustomerNumber(map);
		if (pageNumber != null && limit != null) {
			Pagers<Customer> pagers = new Pagers<Customer>(customers_number, pageNumber, limit);
			pagers.setList(customers);
			model.addAttribute("customers", pagers);
		} else {
			Pagers<Customer> pagers = new Pagers<Customer>(customers_number, 1, 20);
			pagers.setList(customers);
			model.addAttribute("customers", pagers);
		}
		String ip = GetIPUtil.getIpAddress(request);
		if (ip.equals("110.249.251.98")||ip.equals("127.0.0.1")) {
			model.addAttribute("is_ip", "1");
		} else {
			model.addAttribute("is_ip", "0");
		}
		return "/crm/mybusiness/check_business";
	}
	/**
	* @Title: BackMyBusinessController.java 
	* @Package com.jingren.jing.crm.controller.mybusiness 
	* @Description: TODO 标记我的企业信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月22日 下午3:06:25 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/flag_company.jr")
	public void flag_company(MyCompany myCompany,HttpServletResponse response){
		if(myCompanyService.updateMyCompany(myCompany)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
