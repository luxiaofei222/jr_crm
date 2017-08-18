package com.jingren.jing.crm.controller.call;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: CrmCallController.java 
* @Package com.jingren.jing.crm.controller.call 
* @Description: TODO crm通话系统
* @author 鲁晓飞 MR.Lu   
* @date 2017年2月17日 上午9:50:02 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.bean.companyrecord.BusinessCallRecord;
import com.jingren.jing.crm.bean.customer.Customer;
import com.jingren.jing.crm.bean.setcalltime.SetCallTime;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.companyrecord.BusinessCallRecordService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.crm.service.setcalltime.SetCallTimeService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.Validation;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("crm_call")
public class CrmCallController {

	@Resource
	private BusinessCallRecordService businessCallRecordService;
	@Resource
	private SetCallTimeService setCallTimeService;
	@Resource
	private CustomerService customerService;
	@Resource
	private CompanyService companyService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrganizationService organizationService;
	 Log log = LogFactory.getLog( this .getClass()); 
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 保存通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月17日 上午9:53:33 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_business_call.jr")
	public void save_business_call(final HttpSession session,final BusinessCallRecord businessCallRecord,final HttpServletResponse response,
			@RequestParam(value="str",required=false) final String str){
		JSONObject json=JSONObject.fromObject(str);
		Employee employee=(Employee) session.getAttribute("employee_session");
		businessCallRecord.setEmployee_id(employee.getEmployee_id());
		Map<String, Object> map=new HashMap<>();
		map.put("organization_id", employee.getOrganization_id());
		Organization organization=organizationService.getOranization(map);
		businessCallRecord.setOrganization_id(organization.getParent_id());//保存部门ID
		businessCallRecord.setCall_time(new Date());
		businessCallRecord.setCalled_phone(json.getString("callb"));//被叫用户
		businessCallRecord.setZuoxi(json.getString("calla"));//坐席
		businessCallRecord.setCrm_recourd_uid(json.getString("uid"));
		businessCallRecord.setCall_state("NO ANSWER");
		if(businessCallRecord.getCustomer_id()!=null){
			map.clear();
			map.put("customer_id", businessCallRecord.getCustomer_id());
			Customer customer=customerService.getCustomer(map);
			if(customer.getCompany_id()!=null){
				businessCallRecord.setCompany_id(customer.getCompany_id());
			}
		}
		if(json.getString("status").equals("callout")){
			businessCallRecord.setCall_type("呼出");
		}else{
			businessCallRecord.setCall_type("呼入");
		}
		businessCallRecord.setRecord_time(0);
		if(businessCallRecordService.saveBusinessCallRecord(businessCallRecord)){
			session.removeAttribute("call_uid");
			session.removeAttribute("call_record_id");
			session.setAttribute("call_uid", businessCallRecord.getCrm_recourd_uid());
			session.setAttribute("call_record_id", businessCallRecord.getRecord_id());
			ResponseUtils.renderText(response, String.valueOf(businessCallRecord.getRecord_id()));
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 获取通话记录ID
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月17日 上午10:36:33 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_uucall_id.jr")
	public Map<String, Object> get_uucall_id( HttpSession session){
			Map<String, Object> map=new HashMap<>();
			String uid=(String) session.getAttribute("call_uid");
			Integer record_id=(Integer) session.getAttribute("call_record_id");
			map.put("uid", uid);
			map.put("record_id", record_id);
			map.put("time", System.currentTimeMillis()/1000);
			return map;
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 手动输入外呼录入联系人
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月27日 下午5:52:03 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_customer_id.jr")
	public Callable<Map<String, Object>> get_customer_id(final HttpSession session,
			@RequestParam(value="phone",required=false) final String phone,
			final Customer customer){
		return new Callable<Map<String,Object>>() {
			@Override
			public Map<String, Object> call() throws Exception {
				Map<String, Object> map=new HashMap<>();
				map.put("company_id", customer.getCompany_id());
				if(Validation.isChinaPhoneLegal(phone)){
					map.put("customer_phone_call", customer.getCompany_id());
					customer.setCustomer_phone(phone);
				}else{
					map.put("customer_officephone_call", customer.getCompany_id());
					customer.setCustomer_officephone(phone);
				}
				List<Customer> customers=customerService.getCustomerList(map);
				if(customers.size()>0){
					map.put("customer_id", customers.get(0).getCustomer_id());
				}else{
					Employee employee=(Employee) session.getAttribute("employee_session");
					customer.setEmployee_id(employee.getEmployee_id());
					customer.setCustomer_time(new Date());
					customer.setIs_my_customer(1);//自己添加的
					if(customerService.saveCustomer(customer)){
						map.clear();
						map.put("customer_id", customer.getCustomer_id());
					}
				}
				return map;
			}
			
		};
		
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 修改通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月17日 上午11:33:00 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/update_business_call.jr")
	public  void update_business_call (final HttpSession session,final BusinessCallRecord businessCallRecord,
			final HttpServletResponse response,
			@RequestParam(value="call_time_str",required=false)String call_time_str) throws ParseException{
		SetCallTime callTime=setCallTimeService.getSetCallTime();
		if(businessCallRecord.getRecord_time()!=null){
		if(businessCallRecord.getRecord_time()<=callTime.getCall_time()){//如果小于等于设置的最小有效通话时间则通话时长为0
			businessCallRecord.setRecord_time(0);
		}
		}else{
			businessCallRecord.setRecord_time(0);
		}
		Integer customer_id=(Integer) session.getAttribute("customer_id");
		if(customer_id!=null){
			businessCallRecord.setCustomer_id(customer_id);
			session.removeAttribute("customer_id");
		}
		if(StringUtils.isNotBlank(businessCallRecord.getSound_file())){
			businessCallRecord.setSound_file(businessCallRecord.getSound_file().substring(6, businessCallRecord.getSound_file().length()));
		}
		if(StringUtils.isNotBlank(call_time_str)){
			businessCallRecord.setCall_time(CommentDate.get_leave_date(call_time_str));
		}
		if(businessCallRecordService.updateBusinessCallRecord(businessCallRecord)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "2");
		}
	}

	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 获取当前坐席的分机号
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月20日 上午8:50:41 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_now_zuoxi.jr")
	public Map<String, Object> get_now_zuoxi(HttpSession session,HttpServletRequest request){
		Employee employee=(Employee) session.getAttribute("employee_session");
		Map<String, Object> map=new HashMap<>();
		String ip=GetIPUtil.getIpAddress(request);
		if(ip.equals("110.249.251.98")){
			map.put("is_ip",  "1");
		}else{
			map.put("is_ip",  "0");
		}
		if(StringUtils.isNotBlank(employee.getZuoxi())){
			map.put("zuoxi", employee.getZuoxi());
		}else{
			map.put("zuoxi", "0");//如果当前坐席没有分机号则为0
		}
		return map;
		
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 来电弹屏页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月20日 上午11:55:36 
	* @version 网校+CRM系统 V1.0
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping("/get_laidian_tanping.jr")
	public Callable<String> get_laidian_tanping(final Model model,final HttpSession session,final BusinessCallRecord businessCallRecord,
			@RequestParam(value="str",required=false) final String str) throws IOException, Exception{
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				JSONObject json=JSONObject.fromObject(DesUtil.decrypt(str, "record_data"));
				Employee employee=(Employee) session.getAttribute("employee_session");
				businessCallRecord.setEmployee_id(employee.getEmployee_id());
				businessCallRecord.setCall_time(new Date());
				businessCallRecord.setCalled_phone(json.getString("callb"));//主叫用户
				businessCallRecord.setZuoxi(json.getString("calla"));//被叫坐席
				businessCallRecord.setCrm_recourd_uid(json.getString("uid"));
				businessCallRecord.setCall_state("NO ANSWER");
				businessCallRecord.setCall_type("呼入");
				Map<String, Object> map=new HashMap<>();
				map.put("organization_id", employee.getOrganization_id());
				Organization organization=organizationService.getOranization(map);
				businessCallRecord.setOrganization_id(organization.getParent_id());//保存部门ID
				map.clear();
				if(Validation.isChinaPhoneLegal(json.getString("calla"))){
					map.put("customer_phone", json.getString("calla"));//手机号
				}else{
					map.put("customer_officephone", json.getString("calla"));//固定电话
				}
				List<Customer> customers=customerService.getCustomerList(map);
				Customer customer=null;
				if(customers.size()>0){
					customer=customers.get(0);//取第一个联系人
					map.clear();
					map.put("company_id", customer.getCompany_id());
					Company company=companyService.getCompany(map);
					model.addAttribute("company", company);
					map.clear();
					map.put("customer_id", customer.getCustomer_id());
					map.put("employee_id", employee.getEmployee_id());
					List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
					model.addAttribute("businessCallRecords", businessCallRecords);
					businessCallRecord.setCustomer_id(customer.getCustomer_id());
				}
				if(businessCallRecordService.saveBusinessCallRecord(businessCallRecord)){
					session.removeAttribute("call_uid");
					session.removeAttribute("call_record_id");
					session.setAttribute("call_uid", businessCallRecord.getCrm_recourd_uid());
					session.setAttribute("call_record_id", businessCallRecord.getRecord_id());
				}
				model.addAttribute("phone", json.getString("calla"));
				model.addAttribute("customer", customer);
				return "/crm/mybusiness/laidian_tanping_call";
			}
		};
		
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 保存企业信息和联系人信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月20日 下午3:53:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_company_customer.jr")
	public void save_company_customer(final Company company,final Customer customer,final HttpServletResponse response,
			final HttpSession session){
		Map<String, Object> map = new HashMap<>();
		Employee employee = (Employee) session.getAttribute("employee_session");
		map.put("company_name_upload", company.getCompany_name());
		List<Company> company1 = companyService.getCompanyList(map);
		if (company1.size()>0) {
			customer.setCompany_id(company1.get(0).getCompany_id());
			customer.setEmployee_id(employee.getEmployee_id());
			customer.setCustomer_time(new Date());
			if(customerService.saveCustomer(customer)){
				session.setAttribute("customer_id", customer.getCustomer_id());
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}else{
			company.setEmployee_id(employee.getEmployee_id());
			company.setCompany_time(new Date());
			if(companyService.saveCompany(company)){
				customer.setCompany_id(company.getCompany_id());
				customer.setEmployee_id(employee.getEmployee_id());
				customer.setCustomer_time(new Date());
				if(customerService.saveCustomer(customer)){
					session.setAttribute("customer_id", customer.getCustomer_id());
					ResponseUtils.renderText(response, "1");
				}else{
					ResponseUtils.renderText(response, "0");
				}
			}
		}
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 管理员查看通话记录列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月21日 上午8:51:23 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_business_call_list.jr")
	public Callable<String> get_business_call_list(final Model model,final HttpServletRequest request,
			@RequestParam(value = "start_time", required = false) final String start_time,
			@RequestParam(value = "end_time", required = false)final String end_time,
			@RequestParam(value = "employee_id", required = false)final Integer employee_id,
			@RequestParam(value = "beijiao", required = false)final String beijiao,
			@RequestParam(value = "zhujiao", required = false)final String zhujiao,
			@RequestParam(value = "record_state", required = false)final String record_state,
			@RequestParam(value = "record_type", required = false)final String record_type,
			@RequestParam(value = "start_shichang", required = false)final Integer start_shichang,
			@RequestParam(value = "end_shichang", required = false)final Integer end_shichang,
			@RequestParam(value = "limit", required = false)final Integer limit,
			@RequestParam(value = "pageNumber", required = false)final Integer pageNumber) throws ParseException{
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				Map<String, Object> map = new HashMap<>();
				if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
					map.put("start_time", CommentDate.get_String_date(start_time));
					long currentTime =  CommentDate.get_String_date(end_time).getTime();
					currentTime +=24*60*60*1000-1000;//加23小时59分59秒
					Date date=new Date(currentTime);
					map.put("end_time", date);
					model.addAttribute("start_time", start_time);
					model.addAttribute("end_time", end_time);
				}else{
					map.put("start_time", CommentDate.getStartTime());
					map.put("end_time", CommentDate.getnowEndTime());
				}
				
				if(employee_id!=null){//坐席
					map.put("employee_id", employee_id);
					model.addAttribute("employee_id", employee_id);
				}
				if(StringUtils.isNotBlank(beijiao)){//被叫
					map.put("beijiao", beijiao);
					model.addAttribute("beijiao", beijiao);
				}
				if(StringUtils.isNotBlank(zhujiao)){//主叫
					map.put("zhujiao", zhujiao);
					model.addAttribute("zhujiao", zhujiao);
				}
				if(StringUtils.isNotBlank(record_state)){//呼叫状态
					map.put("record_state", record_state);
					model.addAttribute("record_state", record_state);
				}
				if(StringUtils.isNotBlank(record_type)){//呼叫类型
					map.put("record_type", record_type);
					model.addAttribute("record_type", record_type);
				}
				if(start_shichang!=null&&end_shichang!=null){//通话时长
					map.put("start_shichang", start_shichang);
					map.put("end_shichang", end_shichang);
					model.addAttribute("start_shichang", start_shichang);
					model.addAttribute("end_shichang", end_shichang);
				}
				map.put("pageNumber", (pageNumber - 1) * limit);
				map.put("limit", limit);
				model.addAttribute("limit", limit);
				Integer record_number=businessCallRecordService.getBusinessCallRecordNumber(map);
				List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
				for (BusinessCallRecord businessCallRecord : businessCallRecords) {
					map.clear();
					if(businessCallRecord.getCustomer_id()!=null){
						if(businessCallRecord.getCustomer()!=null){
							map.put("company_id", businessCallRecord.getCustomer().getCompany_id());
							Company company=companyService.getCompany(map);
							if(company!=null){
								businessCallRecord.setQiye(company.getCompany_name());
							}	
						}
					}
				}
				Pagers<BusinessCallRecord> pagers=new Pagers<BusinessCallRecord>(record_number, pageNumber, limit);
				pagers.setList(businessCallRecords);
				String ip=GetIPUtil.getIpAddress(request);
				if(ip.equals("110.249.251.98")){
					model.addAttribute("is_ip", "1");
				}else{
					model.addAttribute("is_ip", "0");
				}
				map.clear();
				map.put("zuoxi", "zuoxi");
				List<Employee> employees=employeeService.getEmployeeList(map);
				model.addAttribute("employees", employees);
				model.addAttribute("businessCallRecords", pagers);
				return "/crm/callrecord/record";
			}
		};
		
		
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 录音试听页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月22日 上午10:00:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/listen_the_tape.jr")
	public String listen_the_tape(Model model,
			@RequestParam(value="record_id",required=false)Integer record_id,
			@RequestParam(value="uniqueid",required=false)String uniqueid,
			@RequestParam(value="date",required=false)String date){
		Map<String, Object> map = new HashMap<>();
		map.put("record_id", record_id);
		BusinessCallRecord businessCallRecord=businessCallRecordService.getBusinessCallRecord(map);
		model.addAttribute("businessCallRecord", businessCallRecord);
		model.addAttribute("uniqueid", uniqueid);
		model.addAttribute("date", date);
		return "/crm/callrecord/listen_tage";
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 业务员查看自己的通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月22日 上午10:19:50 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_my_business_call_list.jr")
	public Callable<String> get_my_business_call_list(final Model model,final HttpServletRequest request,
			final HttpSession session,
			@RequestParam(value = "start_time", required = false)final String start_time,
			@RequestParam(value = "end_time", required = false)final String end_time,
			@RequestParam(value = "employee_id", required = false)final Integer employee_id,
			@RequestParam(value = "beijiao", required = false)final String beijiao,
			@RequestParam(value = "zhujiao", required = false)final String zhujiao,
			@RequestParam(value = "record_state", required = false)final String record_state,
			@RequestParam(value = "record_type", required = false)final String record_type,
			@RequestParam(value = "start_shichang", required = false)final Integer start_shichang,
			@RequestParam(value = "end_shichang", required = false)final Integer end_shichang,
			@RequestParam(value = "limit", required = false)final Integer limit,
			@RequestParam(value = "pageNumber", required = false)final Integer pageNumber) throws ParseException{
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				Map<String, Object> map = new HashMap<>();
				Employee employee=(Employee) session.getAttribute("employee_session");
				map.put("employee_id", employee.getEmployee_id());
				if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
					map.put("start_time", CommentDate.get_String_date(start_time));
					long currentTime =  CommentDate.get_String_date(end_time).getTime();
					currentTime +=24*60*60*1000-1000;//加23小时59分59秒
					Date date=new Date(currentTime);
					map.put("end_time", date);
					model.addAttribute("start_time", start_time);
					model.addAttribute("end_time", end_time);
				}else{
					map.put("start_time", CommentDate.getStartTime());
					map.put("end_time", CommentDate.getnowEndTime());
				}
				if(StringUtils.isNotBlank(beijiao)){//被叫
					map.put("beijiao", beijiao);
					model.addAttribute("beijiao", beijiao);
				}
				if(StringUtils.isNotBlank(zhujiao)){//主叫
					map.put("zhujiao", zhujiao);
					model.addAttribute("zhujiao", zhujiao);
				}
				if(StringUtils.isNotBlank(record_state)){//呼叫状态
					map.put("record_state", record_state);
					model.addAttribute("record_state", record_state);
				}
				if(StringUtils.isNotBlank(record_type)){//呼叫类型
					map.put("record_type", record_type);
					model.addAttribute("record_type", record_type);
				}
				if(start_shichang!=null&&end_shichang!=null){//通话时长
					map.put("start_shichang", start_shichang);
					map.put("end_shichang", end_shichang);
					model.addAttribute("start_shichang", start_shichang);
					model.addAttribute("end_shichang", end_shichang);
				}
				int zongshichang=0;
				List<BusinessCallRecord> shichang=businessCallRecordService.getBusinessCallRecordList(map);
				for (BusinessCallRecord businessCallRecord : shichang) {
					if(businessCallRecord.getRecord_time()!=null){
						zongshichang+=businessCallRecord.getRecord_time();
					}
				}
				String sec_time_call =CommentDate.secToTime(zongshichang);
				model.addAttribute("zongshichang", sec_time_call);//通话时长
				model.addAttribute("jilushu", shichang.size());
				map.put("pageNumber", (pageNumber - 1) * limit);
				map.put("limit", limit);
				model.addAttribute("limit", limit);
				Integer record_number=businessCallRecordService.getBusinessCallRecordNumber(map);
				List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
				for (BusinessCallRecord businessCallRecord : businessCallRecords) {
					map.clear();
					if(businessCallRecord.getCustomer_id()!=null){
						if(businessCallRecord.getCustomer()!=null){
							map.put("company_id", businessCallRecord.getCustomer().getCompany_id());
							Company company=companyService.getCompany(map);
							if(company!=null){
								businessCallRecord.setQiye(company.getCompany_name());
							}
						}
					}
				}
				Pagers<BusinessCallRecord> pagers=new Pagers<BusinessCallRecord>(record_number, pageNumber, limit);
				pagers.setList(businessCallRecords);
				String ip=GetIPUtil.getIpAddress(request);
				if(ip.equals("110.249.251.98")){
					model.addAttribute("is_ip", "1");
				}else{
					model.addAttribute("is_ip", "0");
				}
				map.clear();
				map.put("zuoxi", "zuoxi");
				List<Employee> employees=employeeService.getEmployeeList(map);
				model.addAttribute("employees", employees);
				model.addAttribute("businessCallRecords", pagers);
				return "/crm/callrecord/my_record";
			}
		};
		
		
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 获取导出通话记录地址
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月22日 上午11:06:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_down_load_record_url.jr")
	public void get_down_load_record_url(HttpServletResponse response,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "employee_id", required = false) String employee_id,
			@RequestParam(value = "beijiao", required = false) String beijiao,
			@RequestParam(value = "zhujiao", required = false) String zhujiao,
			@RequestParam(value = "record_state", required = false) String record_state,
			@RequestParam(value = "record_type", required = false) String record_type,
			@RequestParam(value = "start_shichang", required = false) String start_shichang,
			@RequestParam(value = "end_shichang", required = false) String end_shichang){
		if(StringUtils.isBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
			start_time="";
			end_time="";
		}
		if(StringUtils.isBlank(beijiao)){//被叫
			beijiao="";
		}
		if(StringUtils.isBlank(zhujiao)){//主叫
			zhujiao="";
		}
		if(StringUtils.isBlank(record_state)){//呼叫状态
			record_state="";
		}
		if(StringUtils.isBlank(record_type)){//呼叫类型
			record_type="";
		}
		ResponseUtils.renderText(response, "/donwload/export_call_record_excel.jr?start_time="+start_time+"&end_time="+end_time+"&employee_id="+employee_id+"&beijiao="+
				beijiao+"&zhujiao="+zhujiao+"&record_state="+record_state+"&record_type="+record_type+"&start_shichang="+start_shichang+"&end_shichang="+end_shichang);
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 查看通话记录详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月22日 下午2:18:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_record_detail.jr")
	public String check_record_detail(Model model,
			@RequestParam(value="record_id",required=false) Integer record_id){
		Map<String, Object> map = new HashMap<>();
		map.put("record_id", record_id);
		BusinessCallRecord businessCallRecord=businessCallRecordService.getBusinessCallRecord(map);
		model.addAttribute("businessCallRecord", businessCallRecord);
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return "/crm/callrecord/check_record";
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 导出通话录音弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月24日 下午1:36:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/down_load_tage.jr")
	public String down_load_tage(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("zuoxi", "zuoxi");
		List<Employee> employees=employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		return "/crm/callrecord/daochu_luyin";
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 部门寻通话记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月7日 下午2:44:20 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_bumen_business_call_list.jr")
	public Callable<String> get_bumen_business_call_list(final Model model,final HttpServletRequest request,
			final HttpSession session,
			@RequestParam(value = "start_time", required = false)final String start_time,
			@RequestParam(value = "end_time", required = false)final String end_time,
			@RequestParam(value = "employee_id", required = false)final Integer employee_id,
			@RequestParam(value = "beijiao", required = false)final String beijiao,
			@RequestParam(value = "zhujiao", required = false)final String zhujiao,
			@RequestParam(value = "record_state", required = false)final String record_state,
			@RequestParam(value = "record_type", required = false)final String record_type,
			@RequestParam(value = "start_shichang", required = false)final Integer start_shichang,
			@RequestParam(value = "end_shichang", required = false)final Integer end_shichang,
			@RequestParam(value = "limit", required = false)final Integer limit,
			@RequestParam(value = "pageNumber", required = false)final Integer pageNumber) throws ParseException{
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				Map<String, Object> map = new HashMap<>();
				Employee employee=(Employee) session.getAttribute("employee_session");
				map.put("organization_id", employee.getOrganization_id());
				Organization organization=organizationService.getOranization(map);
				map.clear();
				map.put("organization_id", organization.getParent_id());
		 		if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
					map.put("start_time", CommentDate.get_String_date(start_time));
					long currentTime =  CommentDate.get_String_date(end_time).getTime();
					currentTime +=24*60*60*1000-1000;//加23小时59分59秒
					Date date=new Date(currentTime);
					map.put("end_time", date);
					model.addAttribute("start_time", start_time);
					model.addAttribute("end_time", end_time);
				}else{
					map.put("start_time", CommentDate.getStartTime());
					map.put("end_time", CommentDate.getnowEndTime());
				}
				if(StringUtils.isNotBlank(beijiao)){//被叫
					map.put("beijiao", beijiao);
					model.addAttribute("beijiao", beijiao);
				}
				if(StringUtils.isNotBlank(zhujiao)){//主叫
					map.put("zhujiao", zhujiao);
					model.addAttribute("zhujiao", zhujiao);
				}
				if(StringUtils.isNotBlank(record_state)){//呼叫状态
					map.put("record_state", record_state);
					model.addAttribute("record_state", record_state);
				}
				if(StringUtils.isNotBlank(record_type)){//呼叫类型
					map.put("record_type", record_type);
					model.addAttribute("record_type", record_type);
				}
				if(start_shichang!=null&&end_shichang!=null){//通话时长
					map.put("start_shichang", start_shichang);
					map.put("end_shichang", end_shichang);
					model.addAttribute("start_shichang", start_shichang);
					model.addAttribute("end_shichang", end_shichang);
				}
				map.put("pageNumber", (pageNumber - 1) * limit);
				map.put("limit", limit);
				model.addAttribute("limit", limit);
				Integer record_number=businessCallRecordService.getBusinessCallRecordNumber(map);
				List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
				for (BusinessCallRecord businessCallRecord : businessCallRecords) {
					map.clear();
					if(businessCallRecord.getCustomer_id()!=null){
						if(businessCallRecord.getCustomer()!=null){
							map.put("company_id", businessCallRecord.getCustomer().getCompany_id());
							Company company=companyService.getCompany(map);
							if(company!=null){
								businessCallRecord.setQiye(company.getCompany_name());
							}
						}
					}
				}
				Pagers<BusinessCallRecord> pagers=new Pagers<BusinessCallRecord>(record_number, pageNumber, limit);
				pagers.setList(businessCallRecords);
				String ip=GetIPUtil.getIpAddress(request);
				if(ip.equals("110.249.251.98")){
					model.addAttribute("is_ip", "1");
				}else{
					model.addAttribute("is_ip", "0");
				}
				map.clear();
				map.put("zuoxi", "zuoxi");
				List<Employee> employees=employeeService.getEmployeeList(map);
				model.addAttribute("employees", employees);
				model.addAttribute("businessCallRecords", pagers);
				return "/crm/callrecord/bumen_record";
			}
		};
		
		
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 通话统计
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月8日 下午3:05:05 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_record_tongji.jr")
	public String get_record_tongji(Model model,HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "record_type", required = false) String record_type,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		if(employee_id!=null){//呼叫类型
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if(bumen_id!=null){//呼叫类型
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		model.addAttribute("limit", limit);
		map.put("zuoxi", "zuoxi");
		List<Employee> employees=employeeService.getEmployeeList(map);
		Integer employee_number=employeeService.getEmployeeNumber(map);
		for (Employee employee : employees) {
			map.clear();
			map.put("employee_id", employee.getEmployee_id());
			if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
				map.put("start_time", CommentDate.get_String_date(start_time));
				long currentTime =  CommentDate.get_String_date(end_time).getTime();
				currentTime +=24*60*60*1000-1000;//加23小时59分59秒
				Date date=new Date(currentTime);
				map.put("end_time", date);
				model.addAttribute("start_time", start_time);
				model.addAttribute("end_time", end_time);
			}else{
				map.put("start_time", CommentDate.getStartTime());
				map.put("end_time", CommentDate.getnowEndTime());
			}
			if(StringUtils.isNotBlank(record_type)){//呼叫类型
				map.put("record_type", record_type);
				model.addAttribute("record_type", record_type);
			}
			Integer record_number=businessCallRecordService.getBusinessCallRecordNumber(map);
			employee.setCall_number(record_number);//通话条数
			int zongshichang=0;
			List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
			for (BusinessCallRecord businessCallRecord : businessCallRecords) {
				map.clear();
				if(businessCallRecord.getRecord_time()!=null){
					zongshichang+=businessCallRecord.getRecord_time();
				}
			}
			DecimalFormat  df =new  DecimalFormat("0.0"); 
			employee.setPaixu_shichang(String.valueOf(df.format((double)zongshichang/60)));
			String sec_time_call =String.valueOf(df.format((double)zongshichang/60));   //CommentDate.secToTime(zhoushichang);
			employee.setCall_time_length(sec_time_call);
			/*******************************本周累计时长开始************************************************/
			map.clear();//统计本周的累计时长
			map.put("employee_id", employee.getEmployee_id());
			String zhouyi=CommentDate.getzhouyi();
			String zhouwu=CommentDate.getzhouwu();
			map.put("zhouyi_time", CommentDate.get_String_date(zhouyi));
			long currentTime =  CommentDate.get_String_date(zhouwu).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("zhouwu_time", date);
			int zhoushichang=0;
			List<BusinessCallRecord> businessCallRecordszhou=businessCallRecordService.getBusinessCallRecordList(map);
			for (BusinessCallRecord businessCallRecord : businessCallRecordszhou) {
				map.clear();
				if(businessCallRecord.getRecord_time()!=null){
					zhoushichang+=businessCallRecord.getRecord_time();
				}
			}
			employee.setPaixu_shichang(String.valueOf(df.format((double)zhoushichang/60)));
			String sec_time_call_zhou =String.valueOf(df.format((double)zhoushichang/60));   //CommentDate.secToTime(zhoushichang);
			employee.setZhou_call_time_length(sec_time_call_zhou);
			/*******************************本周累计时长结束************************************************/
		}
		Pagers<Employee> pagers=new Pagers<Employee>(employee_number, pageNumber, limit);
		pagers.setList(employees);
		model.addAttribute("employees", pagers);
		map.clear();
		map.put("zuoxi", "zuoxi");
		List<Employee> employees2=employeeService.getEmployeeList(map);
		model.addAttribute("zuoxis", employees2);
		return "/crm/callrecord/call_record_tongji";
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 部门统计
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月9日 下午4:05:36 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_record_bumen_tongji.jr")
	public String get_record_bumen_tongji(Model model,HttpServletRequest request,
			HttpSession session,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "record_type", required = false) String record_type,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		Employee employeeor=(Employee) session.getAttribute("employee_session");
		map.put("bumen_id", employeeor.getBumen_id());
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		model.addAttribute("limit", limit);
		map.put("zuoxi", "zuoxi");
		List<Employee> employees=employeeService.getEmployeeList(map);
		Integer employee_number=employeeService.getEmployeeNumber(map);
		for (Employee employee : employees) {
			map.clear();
			map.put("employee_id", employee.getEmployee_id());
			if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
				map.put("start_time", CommentDate.get_String_date(start_time));
				long currentTime =  CommentDate.get_String_date(end_time).getTime();
				currentTime +=24*60*60*1000-1000;//加23小时59分59秒
				Date date=new Date(currentTime);
				map.put("end_time", date);
				model.addAttribute("start_time", start_time);
				model.addAttribute("end_time", end_time);
			}else{
				map.put("start_time", CommentDate.getStartTime());
				map.put("end_time", CommentDate.getnowEndTime());
			}
			if(StringUtils.isNotBlank(record_type)){//呼叫类型
				map.put("record_type", record_type);
				model.addAttribute("record_type", record_type);
			}
			Integer record_number=businessCallRecordService.getBusinessCallRecordNumber(map);
			employee.setCall_number(record_number);//通话条数
			int zongshichang=0;
			List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
			for (BusinessCallRecord businessCallRecord : businessCallRecords) {
				map.clear();
				if(businessCallRecord.getRecord_time()!=null){
					zongshichang+=businessCallRecord.getRecord_time();
				}
			}
			DecimalFormat  df =new  DecimalFormat("0.0"); 
			employee.setPaixu_shichang(String.valueOf(df.format((double)zongshichang/60)));
			String sec_time_call =String.valueOf(df.format((double)zongshichang/60));   //CommentDate.secToTime(zhoushichang);
			employee.setCall_time_length(sec_time_call);
			/*******************************本周累计时长开始************************************************/
			map.clear();//统计本周的累计时长
			map.put("employee_id", employee.getEmployee_id());
			String zhouyi=CommentDate.getzhouyi();
			String zhouwu=CommentDate.getzhouwu();
			map.put("zhouyi_time", CommentDate.get_String_date(zhouyi));
			long currentTime =  CommentDate.get_String_date(zhouwu).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("zhouwu_time", date);
			int zhoushichang=0;
			List<BusinessCallRecord> businessCallRecordszhou=businessCallRecordService.getBusinessCallRecordList(map);
			for (BusinessCallRecord businessCallRecord : businessCallRecordszhou) {
				map.clear();
				if(businessCallRecord.getRecord_time()!=null){
					zhoushichang+=businessCallRecord.getRecord_time();
				}
			}
			employee.setPaixu_shichang(String.valueOf(df.format((double)zhoushichang/60)));
			String sec_time_call_zhou =String.valueOf(df.format((double)zhoushichang/60));   //CommentDate.secToTime(zhoushichang);
			employee.setZhou_call_time_length(sec_time_call_zhou);
			/*******************************本周累计时长结束************************************************/
			
		}
		Pagers<Employee> pagers=new Pagers<Employee>(employee_number, pageNumber, limit);
		pagers.setList(employees);
		model.addAttribute("employees", pagers);
		map.clear();
		map.put("zuoxi", "zuoxi");
		List<Employee> employees2=employeeService.getEmployeeList(map);
		model.addAttribute("zuoxis", employees2);
		return "/crm/callrecord/call_record_bumen_tongji";
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 图表页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月9日 上午10:56:31 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_echart.jr")
	public String to_check_echart(Model model,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "record_type", required = false) String record_type,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("limit", limit);
		model.addAttribute("start_time", start_time);
		model.addAttribute("end_time", end_time);
		model.addAttribute("bumen_id", bumen_id);
		model.addAttribute("employee_id", employee_id);
		model.addAttribute("record_type", record_type);
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		if(type!=null){
			return "/crm/callrecord/call_record__bumenechart";
		}else{
			return "/crm/callrecord/call_record_echart";
		}
		
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 统计图表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月9日 上午11:41:42 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_all_call_echart.jr")
	public Map<String, Object> get_all_call_echart(
			HttpSession session,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "record_type", required = false) String record_type) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		map.put("zuoxi", "zuoxi");
		if(bumen_id!=null){
			map.put("bumen_id", bumen_id);
		}
		List<Employee> employees=employeeService.getEmployeeList(map);
		List<Double> double_list_dou=new ArrayList<Double>();
		List<String> double_list=new ArrayList<String>();
		List<String> name_list=new ArrayList<String>();
		for (Employee employee : employees) {
			map.clear();
			map.put("employee_id", employee.getEmployee_id());
			if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
				map.put("start_time", CommentDate.get_String_date(start_time));
				long currentTime =  CommentDate.get_String_date(end_time).getTime();
				currentTime +=24*60*60*1000-1000;//加23小时59分59秒
				Date date=new Date(currentTime);
				map.put("end_time", date);
			}else{
				map.put("start_time", CommentDate.getStartTime());
				map.put("end_time", CommentDate.getnowEndTime());
			}
			if(StringUtils.isNotBlank(record_type)){//呼叫类型
				map.put("record_type", record_type);
			}
			int zongshichang=0;
			List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
			for (BusinessCallRecord businessCallRecord : businessCallRecords) {
				map.clear();
				if(businessCallRecord.getRecord_time()!=null){
					zongshichang+=businessCallRecord.getRecord_time();
				}
			}
			
			if(zongshichang>0){
				DecimalFormat  df =new  DecimalFormat("0.0"); 
				employee.setPaixu_shichang(String.valueOf(df.format((double)zongshichang/60)));
				double_list_dou.add((double)zongshichang/60);
				
			}
		}
		Double[] arr=new Double[double_list_dou.size()];
		double_list_dou.toArray(arr);//将list转为数组
		Arrays.sort(arr);//数组降序
		DecimalFormat  df =new  DecimalFormat("0.0"); 
		for (Double string : arr) {//生成数据
			for (Employee employee : employees) {
				if(StringUtils.isNotBlank(employee.getPaixu_shichang())){
						if(df.format(string).equals(employee.getPaixu_shichang())){
							double_list.add(df.format(string));
							name_list.add(employee.getEmployee_name());
							break;
						}
				}
			}
		}
		Map<String, Object> map_char = new HashMap<>();
		map_char.put("fenzhong", double_list);
		map_char.put("name", name_list);
		map_char.put("length", name_list.size());
		return map_char;
	}
	
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 统计部门时长
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月9日 下午5:16:41 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/get_bumen_call_echart.jr")
	public Map<String, Object> get_bumen_call_echart(
			HttpSession session,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "record_type", required = false) String record_type) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		Employee employeeor=(Employee) session.getAttribute("employee_session");
		map.put("bumen_id", employeeor.getBumen_id());
		map.put("zuoxi", "zuoxi");
		List<Employee> employees=employeeService.getEmployeeList(map);
		List<Double> double_list_dou=new ArrayList<Double>();
		List<String> double_list=new ArrayList<String>();
		List<String> name_list=new ArrayList<String>();
		for (Employee employee : employees) {
			map.clear();
			map.put("employee_id", employee.getEmployee_id());
			if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//开始时间和结束时间
				map.put("start_time", CommentDate.get_String_date(start_time));
				long currentTime =  CommentDate.get_String_date(end_time).getTime();
				currentTime +=24*60*60*1000-1000;//加23小时59分59秒
				Date date=new Date(currentTime);
				map.put("end_time", date);
			}else{
				map.put("start_time", CommentDate.getStartTime());
				map.put("end_time", CommentDate.getnowEndTime());
			}
			if(StringUtils.isNotBlank(record_type)){//呼叫类型
				map.put("record_type", record_type);
			}
			int zongshichang=0;
			List<BusinessCallRecord> businessCallRecords=businessCallRecordService.getBusinessCallRecordList(map);
			for (BusinessCallRecord businessCallRecord : businessCallRecords) {
				map.clear();
				if(businessCallRecord.getRecord_time()!=null){
					zongshichang+=businessCallRecord.getRecord_time();
				}
			}
			
			if(zongshichang>0){
				DecimalFormat  df =new  DecimalFormat("0.0"); 
				employee.setPaixu_shichang(String.valueOf(df.format((double)zongshichang/60)));
				double_list_dou.add((double)zongshichang/60);
				
			}
		}
		Double[] arr=new Double[double_list_dou.size()];
		double_list_dou.toArray(arr);//将list转为数组
		Arrays.sort(arr);//数组降序
		DecimalFormat  df =new  DecimalFormat("0.0"); 
		for (Double string : arr) {//生成数据
			for (Employee employee : employees) {
				if(StringUtils.isNotBlank(employee.getPaixu_shichang())){
						if(df.format(string).equals(employee.getPaixu_shichang())){
							double_list.add(df.format(string));
							name_list.add(employee.getEmployee_name());
							break;
						}
				}
			}
		}
		Map<String, Object> map_char = new HashMap<>();
		map_char.put("fenzhong", double_list);
		map_char.put("name", name_list);
		map_char.put("length", name_list.size());
		return map_char;
	}
	/**
	* @Title: CrmCallController.java 
	* @Package com.jingren.jing.crm.controller.call 
	* @Description: TODO 通话记录备注
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月9日 下午6:48:53 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_record_note.jr")
	public void update_record_note(BusinessCallRecord businessCallRecord,HttpServletResponse response){
		if(businessCallRecordService.updateBusinessCallRecord(businessCallRecord)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
