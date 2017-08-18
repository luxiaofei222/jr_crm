package com.jingren.jing.crm.controller.zuoxi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.zuoxi.Zuoxi;
import com.jingren.jing.crm.bean.companyrecord.BusinessCallRecord;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.companyrecord.BusinessCallRecordService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.crm.service.setcalltime.SetCallTimeService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.ResponseUtils;

import net.sf.json.JSONArray;

/**
* @Title: BackZuoxiController.java 
* @Package com.jingren.jing.crm.controller.zuoxi 
* @Description: TODO 坐席管理
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月24日 上午8:21:20 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_zuoxi")
public class BackZuoxiController {

	@Resource
	private EmployeeService employeeService;
	@Resource
	private BusinessCallRecordService businessCallRecordService;
	@Resource
	private CustomerService customerService;
	@Resource
	private CompanyService companyService;
	/**
	* @Title: BackZuoxiController.java 
	* @Package com.jingren.jing.crm.controller.zuoxi 
	* @Description: TODO  坐席管理首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月24日 上午8:27:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zuoxi_main.jr")
	public String get_zuoxi_main(Model model,HttpServletRequest request){
		String ip=GetIPUtil.getIpAddress(request);
		if(ip.equals("110.249.251.98")){
			model.addAttribute("is_ip", "1");
		}else{
			model.addAttribute("is_ip", "0");
		}
		return "/crm/zuoxi/zuoxi_list";
	}
	
	/**
	* @Title: BackZuoxiController.java 
	* @Package com.jingren.jing.crm.controller.zuoxi 
	* @Description: TODO 坐席列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月24日 上午8:23:05 
	* @version 网校+CRM系统 V1.0
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping("/get_zuoxi_list.jr")
	public String get_zuoxi_list(Model model,HttpServletRequest request,
			@RequestParam(value = "zuoxi_list", required = false) String zuoxi_list) {
		Map<String, Object> map = new HashMap<>();
		JSONArray jsonArray = new JSONArray();
		jsonArray = jsonArray.fromObject(zuoxi_list);
		List<Zuoxi> zuoxis = (List<Zuoxi>)JSONArray.toCollection(jsonArray, Zuoxi.class);//jsaonarray转Java对象
		for (Zuoxi zuoxi : zuoxis) {
			map.clear();
			map.put("zuoxi", zuoxi.getExtension());
			Employee employee2 = employeeService.getEmployee(map);
			zuoxi.setEmployee(employee2);
		}
		model.addAttribute("zuoxi_list", zuoxis);
		return "/crm/zuoxi/zuoxi";
	}
	/**
	* @Title: BackZuoxiController.java 
	* @Package com.jingren.jing.crm.controller.zuoxi 
	* @Description: TODO 弹出添加坐席页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月24日 上午10:20:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_zuoxi.jr")
	public String  to_add_zuoxi(Model model,
			@RequestParam(value="zuoxi",required=false)String zuoxi){
		Map<String, Object> map=new HashMap<>();
		map.put("crm_zuoxi", "zuoxi");//所有未绑定的账号
		map.put("employee_state", "在职");
		map.put("parent_id_zuoxi", 5);
		List<Employee> employees=employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		model.addAttribute("zuoxi", zuoxi);
		return "/crm/zuoxi/add_zuoxi";
	}
	
	@RequestMapping("/huifu_qiye_lianxiren.jr")
	public void huifu_qiye_lianxiren(HttpServletResponse response,
			@RequestParam(value="employee_id",required=false,defaultValue="57")Integer employee_id){
		Map<String, Object> map=new HashMap<>();
		map.put("employee_id", employee_id);
		List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
		int a=0;
		for (BusinessCallRecord businessCallRecord : businessCallRecords) {
			if(StringUtils.isNotBlank(businessCallRecord.getCall_state())){
				if(businessCallRecord.getCall_state().equals("ANSWERED")){
					if(businessCallRecord.getCustomer()==null){
						if(StringUtils.isNotBlank(businessCallRecord.getCalled_phone())){
							map.clear();
							map.put("customer_phone", businessCallRecord.getCalled_phone());
							List<Customer> customers2=customerService.getCustomerList(map);
							if(customers2.size()>0){
								Customer customer=new Customer();
								customer.setCustomer_phone(customers2.get(0).getCustomer_phone());
								customer.setCompany_id(customers2.get(0).getCompany_id());
								customer.setIs_my_customer(1);
								customer.setCustomer_time(new Date());
								customer.setEmployee_id(57);
								customerService.saveCustomer(customer);
								a++;
							}
							map.clear();
							map.put("customer_officephone", businessCallRecord.getCalled_phone());
							List<Customer> customers3=customerService.getCustomerList(map);
							if(customers3.size()>0){
								Customer customer=new Customer();
								customer.setCustomer_phone(customers3.get(0).getCustomer_phone());
								customer.setCompany_id(customers3.get(0).getCompany_id());
								customer.setIs_my_customer(1);
								customer.setCustomer_time(new Date());
								customer.setEmployee_id(57);
								customerService.saveCustomer(customer);
								a++;
							}
						}
					}
				}
			}
		}
		ResponseUtils.renderText(response, String.valueOf(a));
	}
}
