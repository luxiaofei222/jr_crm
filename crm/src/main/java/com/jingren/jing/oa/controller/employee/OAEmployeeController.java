package com.jingren.jing.oa.controller.employee;

import java.text.ParseException;
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
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.common.nation.bean.TbNation;
import com.jingren.jing.common.nation.service.TbNationService;
import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.oa.bean.oa_edu.OAEdu;
import com.jingren.jing.oa.bean.oa_employee.OAEmployee;
import com.jingren.jing.oa.bean.oa_lianxiren.OALianxiren;
import com.jingren.jing.oa.bean.oa_work.OAWork;
import com.jingren.jing.oa.service.oa_edu.OAEduService;
import com.jingren.jing.oa.service.oa_employee.OAEmployeeService;
import com.jingren.jing.oa.service.oa_lianxiren.OALianxirenService;
import com.jingren.jing.oa.service.oa_work.OAWorkService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
/**
* @Title: OAEmployeeController.java 
* @Package com.jingren.jing.oa.controller.employee 
* @Description: TODO 员工管理
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月1日 下午4:45:30 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("oa_employee")
public class OAEmployeeController {

	@Resource
	private OrganizationService organizationService;
	@Resource
	private TbNationService tbNationService;
	@Resource
	private OAEmployeeService oaEmployeeService;
	@Resource
	private OAEduService oaEduService;
	@Resource
	private OALianxirenService oaLianxirenService;
	@Resource
	private OAWorkService oaWorkService;
	@Resource
	private EmployeeService employeeService;
	private static final String UP_FRONT_FILE = "images/oa";
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 进入入职登记表页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月1日 下午4:46:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_add_employee.jr")
	public String get_add_employee(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("organization_level", 1);// 获取所有部门
		List<Organization> organizations = organizationService.getOranizationList(map);
		model.addAttribute("organizations", organizations);
		map.clear();
		map.put("sub_level", 2);
		map.put("parent_id", organizations.get(0).getOrganization_id());
		List<Organization> organizations_sub = organizationService.getOranizationList(map);
		model.addAttribute("organizations_sub", organizations_sub);
		List<TbNation> nations=tbNationService.getTbNationList();//民族列表
		model.addAttribute("nations", nations);
		return "/oa/employee/add_employee";
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 入职登记表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月2日 上午9:48:53 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_oa_employee.jr")
	public void save_oa_employee(HttpServletResponse response,HttpServletRequest request,OAEmployee oaEmployee,
			@RequestParam(value="employee_pic_str",required=false) MultipartFile employee_pic_str,//用户免冠照片
			@RequestParam(value="zhuanzheng_time_str",required=false)String zhuanzheng_time_str,
			@RequestParam(value="ruzhi_time_str",required=false)String ruzhi_time_str,
			@RequestParam(value="school_start_time",required=false)String school_start_time,
			@RequestParam(value="school_end_time",required=false)String school_end_time,
			@RequestParam(value="school_name_str",required=false)String school_name_str,
			@RequestParam(value="zhuanye_str_school",required=false)String zhuanye_str_school,
			@RequestParam(value="xueli_str_school",required=false)String xueli_str_school,
			@RequestParam(value="work_start_time",required=false)String work_start_time,
			@RequestParam(value="work_end_time",required=false)String work_end_time,
			@RequestParam(value="danwei_name_str",required=false)String danwei_name_str,
			@RequestParam(value="gangwei_str",required=false)String gangwei_str,
			@RequestParam(value="gongzuo_neirong_str",required=false)String gongzuo_neirong_str,
			@RequestParam(value="guanxi_jiating_str",required=false)String guanxi_jiating_str,
			@RequestParam(value="name_jiating_str",required=false)String name_jiating_str,
			@RequestParam(value="age_jiating_str",required=false)String age_jiating_str,
			@RequestParam(value="danwei_jiating_str",required=false)String danwei_jiating_str,
			@RequestParam(value="zhiwu_jiating_str",required=false)String zhiwu_jiating_str,
			@RequestParam(value="phone_jiating_str",required=false)String phone_jiating_str,
			@RequestParam(value="guanxi_jinji_str",required=false)String guanxi_jinji_str,
			@RequestParam(value="name_jinji_str",required=false)String name_jinji_str,
			@RequestParam(value="age_jinji_str",required=false)String age_jinji_str,
			@RequestParam(value="danwei_jinji_str",required=false)String danwei_jinji_str,
			@RequestParam(value="zhiwu_jinji_str",required=false)String zhiwu_jinji_str,
			@RequestParam(value="phone_jinji_str",required=false)String phone_jinji_str) throws ParseException{
		if(employee_pic_str!=null){
			String filepath=UploadAddress.getUploadDate(employee_pic_str, request, UP_FRONT_FILE);
			oaEmployee.setEmployee_pic(filepath);
		}
		oaEmployee.setOa_employee_time(new Date());
		if(StringUtils.isNotBlank(zhuanzheng_time_str)){//转正时间
			oaEmployee.setZhuanzheng_time(CommentDate.get_String_date(zhuanzheng_time_str));
		}
		if(StringUtils.isNotBlank(ruzhi_time_str)){//入职时间
			oaEmployee.setRuzhi_time(CommentDate.get_String_date(ruzhi_time_str));
		}
		if(oaEmployeeService.saveOAEmployee(oaEmployee)){
			//保存教育经历
			if(StringUtils.isNotBlank(school_start_time)){
				String[] school_start_time_arr=school_start_time.split(",");
				String[] school_end_time_arr=school_end_time.split(",");
				String[] school_name_str_arr=school_name_str.split(",");
				String[] zhuanye_str_school_arr=zhuanye_str_school.split(",");
				String[] xueli_str_school_arr=xueli_str_school.split(",");
				for (int i = 0; i < school_start_time_arr.length; i++) {
					OAEdu oaEdu=new OAEdu();
					oaEdu.setOa_employee_id(oaEmployee.getOa_employee_id());
					oaEdu.setStart_time(school_start_time_arr[i]);
					oaEdu.setEnd_time(school_end_time_arr[i]);
					oaEdu.setSchool_name(school_name_str_arr[i]);
					oaEdu.setZhuanye(zhuanye_str_school_arr[i]);
					oaEdu.setXueli(xueli_str_school_arr[i]);
					oaEduService.saveOAEdu(oaEdu);
				}
			}
			//保存工作经历
			if(StringUtils.isNotBlank(work_start_time)){
				String[] work_start_time_arr=work_start_time.split(",");
				String[] work_end_time_arr=work_end_time.split(",");
				String[] danwei_name_str_arr=danwei_name_str.split(",");
				String[] gangwei_str_arr=gangwei_str.split(",");
				String[] gongzuo_neirong_str_arr=gongzuo_neirong_str.split(",");
				for (int i = 0; i < work_start_time_arr.length; i++) {
					OAWork oaWork=new OAWork();
					oaWork.setOa_employee_id(oaEmployee.getOa_employee_id());
					oaWork.setStart_time(work_start_time_arr[i]);
					oaWork.setEnd_time(work_end_time_arr[i]);
					oaWork.setDanwei_name(danwei_name_str_arr[i]);
					oaWork.setGangwei(gangwei_str_arr[i]);
					oaWork.setGongzuo_neirong(gongzuo_neirong_str_arr[i]);
					oaWorkService.saveOAWork(oaWork);
				}
			}
			//保存家庭成员
			if(StringUtils.isNotBlank(name_jiating_str)){
				String[] name_jiating_str_arr=name_jiating_str.split(",");
				String[] guanxi_jiating_str_arr=guanxi_jiating_str.split(",");
				String[] age_jiating_str_arr=age_jiating_str.split(",");
				String[] danwei_jiating_str_arr=danwei_jiating_str.split(",");
				String[] zhiwu_jiating_str_arr=zhiwu_jiating_str.split(",");
				String[] phone_jiating_str_arr=phone_jiating_str.split(",");
				for (int i = 0; i < name_jiating_str_arr.length; i++) {
					OALianxiren lianxiren=new OALianxiren();
					lianxiren.setOa_employee_id(oaEmployee.getOa_employee_id());
					lianxiren.setType("家庭");
					lianxiren.setName(name_jiating_str_arr[i]);
					lianxiren.setGuanxi(guanxi_jiating_str_arr[i]);
					lianxiren.setAge(Integer.valueOf(age_jiating_str_arr[i]));
					lianxiren.setDanwei(danwei_jiating_str_arr[i]);
					lianxiren.setZhiwu(zhiwu_jiating_str_arr[i]);
					lianxiren.setPhone(phone_jiating_str_arr[i]);
					oaLianxirenService.saveOALianxiren(lianxiren);
				}
			}
			//保存紧急联系人
			if(StringUtils.isNotBlank(name_jinji_str)){
				String[] name_jinji_str_arr=name_jinji_str.split(",");
				String[] guanxi_jinji_str_arr=guanxi_jinji_str.split(",");
				String[] age_jinji_str_arr=age_jinji_str.split(",");
				String[] danwei_jinji_str_arr=danwei_jinji_str.split(",");
				String[] zhiwu_jinji_str_arr=zhiwu_jinji_str.split(",");
				String[] phone_jinji_str_arr=phone_jinji_str.split(",");
				for (int i = 0; i < name_jinji_str_arr.length; i++) {
					OALianxiren lianxiren=new OALianxiren();
					lianxiren.setOa_employee_id(oaEmployee.getOa_employee_id());
					lianxiren.setType("紧急");
					lianxiren.setName(name_jinji_str_arr[i]);
					lianxiren.setGuanxi(guanxi_jinji_str_arr[i]);
					lianxiren.setAge(Integer.valueOf(age_jinji_str_arr[i]));
					lianxiren.setDanwei(danwei_jinji_str_arr[i]);
					lianxiren.setZhiwu(zhiwu_jinji_str_arr[i]);
					lianxiren.setPhone(phone_jinji_str_arr[i]);
					oaLianxirenService.saveOALianxiren(lianxiren);
				}
			}
			ResponseUtils.renderText(response, "1");
		}
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 员工信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月2日 上午10:40:35 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_oa_employee_list.jr")
	public String get_oa_employee_list(Model model,
			@RequestParam(value = "gangwei_state", required = false) String gangwei_state,
			@RequestParam(value = "card_number", required = false) String card_number,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "xingbie", required = false) String xingbie,
			@RequestParam(value = "ruzhi_time_start_str", required = false) String ruzhi_time_start_str,
			@RequestParam(value = "ruzhi_time_end_str", required = false) String ruzhi_time_end_str,
			@RequestParam(value = "birthday", required = false) String birthday,
			@RequestParam(value = "xingming", required = false) String xingming,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(card_number)){
			map.put("card_number", card_number);
			model.addAttribute("card_number", card_number);
		}
		if(bumen_id!=null){
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		if(StringUtils.isNotBlank(xingbie)){
			map.put("xingbie", xingbie);
			model.addAttribute("xingbie", xingbie);
		}
		
		if(StringUtils.isNotBlank(gangwei_state)){
			map.put("gangwei_state", gangwei_state);
			model.addAttribute("gangwei_state", gangwei_state);
		}
		if(StringUtils.isNotBlank(ruzhi_time_start_str)&&StringUtils.isNotBlank(ruzhi_time_end_str)){
			map.put("ruzhi_time_start", CommentDate.get_String_date(ruzhi_time_start_str));
			long currentTime =  CommentDate.get_String_date(ruzhi_time_end_str).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("ruzhi_time_end", date);
			model.addAttribute("ruzhi_time_start_str", ruzhi_time_start_str);
			model.addAttribute("ruzhi_time_end_str", ruzhi_time_end_str);
		}
		if(StringUtils.isNotBlank(birthday)){
			map.put("birthday", birthday);
			model.addAttribute("birthday", birthday);
		}
		if(StringUtils.isNotBlank(xingming)){
			map.put("xingming", xingming);
			model.addAttribute("xingming", xingming);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer employee_number=oaEmployeeService.getOAEmployeeNumber(map);
		List<OAEmployee> oaEmployees=oaEmployeeService.getOAEmployeeList(map);
		for (OAEmployee oaEmployee : oaEmployees) {
			map.clear();
			map.put("organization_id", oaEmployee.getGangwei_id());
			Organization organization=organizationService.getOranization(map);
			oaEmployee.setOrganization(organization);
		}
		Pagers<OAEmployee> pagers = new Pagers<OAEmployee>(employee_number, pageNumber, limit);
		pagers.setList(oaEmployees);
		model.addAttribute("oaEmployees", pagers);
		model.addAttribute("limit", limit);
		map.clear();
		map.put("organization_level", 1);// 获取所有部门
		List<Organization> organizations = organizationService.getOranizationList(map);
		model.addAttribute("organizations", organizations);
		return "/oa/employee/employee_list";
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 跳转修改查看页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月2日 下午2:22:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_employee.jr")
	public String to_update_employee(Model model,
			@RequestParam(value = "oa_employee_id", required = false) Integer oa_employee_id,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		map.put("organization_level", 1);// 获取所有部门
		List<Organization> organizations = organizationService.getOranizationList(map);
		model.addAttribute("organizations", organizations);
		
		List<TbNation> nations=tbNationService.getTbNationList();//民族列表
		model.addAttribute("nations", nations);
		map.clear();
		map.put("oa_employee_id", oa_employee_id);
		OAEmployee oaEmployee=oaEmployeeService.getOAEmployee(map);
		map.clear();
		map.put("sub_level", 2);
		map.put("parent_id", oaEmployee.getBumen_id());
		List<Organization> organizations_sub = organizationService.getOranizationList(map);
		model.addAttribute("organizations_sub", organizations_sub);
		map.clear();
		map.put("oa_employee_id", oa_employee_id);
		List<OAEdu> oaEdus=oaEduService.getOAEduList(map);
		List<OAWork> oaWorks=oaWorkService.getOAWorkList(map);
		map.put("type", "家庭");
		List<OALianxiren> lianxirens_jiating=oaLianxirenService.getOALianxirenList(map);
		model.addAttribute("lianxirens_jiating", lianxirens_jiating);
		map.put("type", "紧急");
		List<OALianxiren> lianxirens_jinji=oaLianxirenService.getOALianxirenList(map);
		model.addAttribute("lianxirens_jinji", lianxirens_jinji);
		model.addAttribute("oaEdus", oaEdus);
		model.addAttribute("oaWorks", oaWorks);
		model.addAttribute("oaEmployee", oaEmployee);
		model.addAttribute("limit", limit);
		model.addAttribute("pageNumber", pageNumber);
		return "/oa/employee/update_employee";
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 修改员工信息个人资料
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月2日 下午2:24:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_oa_employee.jr")
	public void update_oa_employee(HttpServletResponse response,HttpServletRequest request,
			OAEmployee oaEmployee,
			@RequestParam(value="employee_pic_str",required=false) MultipartFile employee_pic_str,//用户免冠照片
			@RequestParam(value="zhuanzheng_time_str",required=false)String zhuanzheng_time_str,
			@RequestParam(value="ruzhi_time_str",required=false)String ruzhi_time_str) throws ParseException{
		if(employee_pic_str!=null){
			String filepath=UploadAddress.getUploadDate(employee_pic_str, request, UP_FRONT_FILE);
			oaEmployee.setEmployee_pic(filepath);
		}
		oaEmployee.setOa_employee_time(new Date());
		if(StringUtils.isNotBlank(zhuanzheng_time_str)){//转正时间
			oaEmployee.setZhuanzheng_time(CommentDate.get_String_date(zhuanzheng_time_str));
		}
		if(StringUtils.isNotBlank(ruzhi_time_str)){//入职时间
			oaEmployee.setRuzhi_time(CommentDate.get_String_date(ruzhi_time_str));
		}
		if(oaEmployeeService.updateOAEmployee(oaEmployee)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 修改员工信息-岗位状态
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月2日 下午3:28:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_oa_employee_gangwei.jr")
	public void update_oa_employee_gangwei(HttpServletResponse response,HttpServletRequest request,
			OAEmployee oaEmployee) throws ParseException{
		if(oaEmployeeService.updateOAEmployee(oaEmployee)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 绑定账号页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月2日 下午3:57:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_bangding_zhanghao.jr")
	public String to_bangding_zhanghao(Model model,
			@RequestParam(value="oa_employee_id",required=false)Integer oa_employee_id,
			@RequestParam(value="xingming",required=false)String xingming){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(xingming)){
			map.put("employee_name", xingming);
			List<Employee> employees=employeeService.getEmployeeList(map);
			if(employees.size()>0){
				model.addAttribute("employees", employees);
			}else{
				map.clear();
				List<Employee> employeesall=employeeService.getEmployeeList(map);
				model.addAttribute("employees", employeesall);
			}
		}else{
			map.clear();
			List<Employee> employeesall=employeeService.getEmployeeList(map);
			model.addAttribute("employees", employeesall);
		}
		model.addAttribute("oa_employee_id", oa_employee_id);
		return "/oa/employee/add_zhanghao";
		
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 添加离职日期
	* @author 鲁晓飞 MR.Lu    
	* @date 2017年6月5日 上午8:52:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_lizhitime.jr")
	public String to_add_lizhitime(Model model,
			@RequestParam(value="oa_employee_id",required=false)Integer oa_employee_id){
	
		model.addAttribute("oa_employee_id", oa_employee_id);
		return "/oa/employee/add_lizhi_time";
		
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 修改员工教育经历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 下午5:46:06 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/uodate_employee_edu.jr")
	public void uodate_employee_edu(OAEdu edu,HttpServletResponse response){
		if(oaEduService.updateOAEdu(edu)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 修改工作经历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 下午6:05:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/uodate_employee_work.jr")
	public void uodate_employee_work(OAWork oaWork,HttpServletResponse response){
		if(oaWorkService.updateOAWork(oaWork)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 修改家庭联系人
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 下午6:13:56 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/uodate_employee_jiating.jr")
	public void uodate_employee_jiating(OALianxiren oaLianxiren,HttpServletResponse response){
		if(oaLianxirenService.updateOALianxiren(oaLianxiren)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 删除员工信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 下午6:29:25 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_employee.jr")
	public void delete_employee(HttpServletResponse response,
			@RequestParam(value="oa_employee_id",required=false) Integer oa_employee_id){
		if(oaEmployeeService.deleteOAEmployee(oa_employee_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 添加个人经历页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月24日 上午8:55:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_employee_jingli.jr")
	public String to_add_employee_jingli(Model model,
			@RequestParam(value="oa_employee_id",required=false) Integer oa_employee_id){
		model.addAttribute("oa_employee_id", oa_employee_id);
		return "/oa/employee/add_jingli";
	}
	/**
	* @Title: OAEmployeeController.java 
	* @Package com.jingren.jing.oa.controller.employee 
	* @Description: TODO 添加员工经历
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月24日 上午10:27:50 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_oa_employee_jingli.jr")
	public void save_oa_employee_jingli(HttpServletResponse response,
			@RequestParam(value="zhuanzheng_time_str",required=false)String zhuanzheng_time_str,
			@RequestParam(value="ruzhi_time_str",required=false)String ruzhi_time_str,
			@RequestParam(value="school_start_time",required=false)String school_start_time,
			@RequestParam(value="school_end_time",required=false)String school_end_time,
			@RequestParam(value="school_name_str",required=false)String school_name_str,
			@RequestParam(value="zhuanye_str_school",required=false)String zhuanye_str_school,
			@RequestParam(value="xueli_str_school",required=false)String xueli_str_school,
			@RequestParam(value="work_start_time",required=false)String work_start_time,
			@RequestParam(value="work_end_time",required=false)String work_end_time,
			@RequestParam(value="danwei_name_str",required=false)String danwei_name_str,
			@RequestParam(value="gangwei_str",required=false)String gangwei_str,
			@RequestParam(value="gongzuo_neirong_str",required=false)String gongzuo_neirong_str,
			@RequestParam(value="guanxi_jiating_str",required=false)String guanxi_jiating_str,
			@RequestParam(value="name_jiating_str",required=false)String name_jiating_str,
			@RequestParam(value="age_jiating_str",required=false)String age_jiating_str,
			@RequestParam(value="danwei_jiating_str",required=false)String danwei_jiating_str,
			@RequestParam(value="zhiwu_jiating_str",required=false)String zhiwu_jiating_str,
			@RequestParam(value="phone_jiating_str",required=false)String phone_jiating_str,
			@RequestParam(value="guanxi_jinji_str",required=false)String guanxi_jinji_str,
			@RequestParam(value="name_jinji_str",required=false)String name_jinji_str,
			@RequestParam(value="age_jinji_str",required=false)String age_jinji_str,
			@RequestParam(value="danwei_jinji_str",required=false)String danwei_jinji_str,
			@RequestParam(value="zhiwu_jinji_str",required=false)String zhiwu_jinji_str,
			@RequestParam(value="phone_jinji_str",required=false)String phone_jinji_str,
			@RequestParam(value="oa_employee_id",required=false)Integer oa_employee_id) throws ParseException{
		//保存教育经历
		if(StringUtils.isNotBlank(school_start_time)){
			String[] school_start_time_arr=school_start_time.split(",");
			String[] school_end_time_arr=school_end_time.split(",");
			String[] school_name_str_arr=school_name_str.split(",");
			String[] zhuanye_str_school_arr=zhuanye_str_school.split(",");
			String[] xueli_str_school_arr=xueli_str_school.split(",");
			for (int i = 0; i < school_start_time_arr.length; i++) {
				OAEdu oaEdu=new OAEdu();
				oaEdu.setOa_employee_id(oa_employee_id);
				oaEdu.setStart_time(school_start_time_arr[i]);
				oaEdu.setEnd_time(school_end_time_arr[i]);
				oaEdu.setSchool_name(school_name_str_arr[i]);
				oaEdu.setZhuanye(zhuanye_str_school_arr[i]);
				oaEdu.setXueli(xueli_str_school_arr[i]);
				oaEduService.saveOAEdu(oaEdu);
			}
		}
		//保存工作经历
		if(StringUtils.isNotBlank(work_start_time)){
			String[] work_start_time_arr=work_start_time.split(",");
			String[] work_end_time_arr=work_end_time.split(",");
			String[] danwei_name_str_arr=danwei_name_str.split(",");
			String[] gangwei_str_arr=gangwei_str.split(",");
			String[] gongzuo_neirong_str_arr=gongzuo_neirong_str.split(",");
			for (int i = 0; i < work_start_time_arr.length; i++) {
				OAWork oaWork=new OAWork();
				oaWork.setOa_employee_id(oa_employee_id);
				oaWork.setStart_time(work_start_time_arr[i]);
				oaWork.setEnd_time(work_end_time_arr[i]);
				oaWork.setDanwei_name(danwei_name_str_arr[i]);
				oaWork.setGangwei(gangwei_str_arr[i]);
				oaWork.setGongzuo_neirong(gongzuo_neirong_str_arr[i]);
				oaWorkService.saveOAWork(oaWork);
			}
		}
		//保存家庭成员
		if(StringUtils.isNotBlank(name_jiating_str)){
			String[] name_jiating_str_arr=name_jiating_str.split(",");
			String[] guanxi_jiating_str_arr=guanxi_jiating_str.split(",");
			String[] age_jiating_str_arr=age_jiating_str.split(",");
			String[] danwei_jiating_str_arr=danwei_jiating_str.split(",");
			String[] zhiwu_jiating_str_arr=zhiwu_jiating_str.split(",");
			String[] phone_jiating_str_arr=phone_jiating_str.split(",");
			for (int i = 0; i < name_jiating_str_arr.length; i++) {
				OALianxiren lianxiren=new OALianxiren();
				lianxiren.setOa_employee_id(oa_employee_id);
				lianxiren.setType("家庭");
				lianxiren.setName(name_jiating_str_arr[i]);
				lianxiren.setGuanxi(guanxi_jiating_str_arr[i]);
				lianxiren.setAge(Integer.valueOf(age_jiating_str_arr[i]));
				lianxiren.setDanwei(danwei_jiating_str_arr[i]);
				lianxiren.setZhiwu(zhiwu_jiating_str_arr[i]);
				lianxiren.setPhone(phone_jiating_str_arr[i]);
				oaLianxirenService.saveOALianxiren(lianxiren);
			}
		}
		//保存紧急联系人
		if(StringUtils.isNotBlank(name_jinji_str)){
			String[] name_jinji_str_arr=name_jinji_str.split(",");
			String[] guanxi_jinji_str_arr=guanxi_jinji_str.split(",");
			String[] age_jinji_str_arr=age_jinji_str.split(",");
			String[] danwei_jinji_str_arr=danwei_jinji_str.split(",");
			String[] zhiwu_jinji_str_arr=zhiwu_jinji_str.split(",");
			String[] phone_jinji_str_arr=phone_jinji_str.split(",");
			for (int i = 0; i < name_jinji_str_arr.length; i++) {
				OALianxiren lianxiren=new OALianxiren();
				lianxiren.setOa_employee_id(oa_employee_id);
				lianxiren.setType("紧急");
				lianxiren.setName(name_jinji_str_arr[i]);
				lianxiren.setGuanxi(guanxi_jinji_str_arr[i]);
				lianxiren.setAge(Integer.valueOf(age_jinji_str_arr[i]));
				lianxiren.setDanwei(danwei_jinji_str_arr[i]);
				lianxiren.setZhiwu(zhiwu_jinji_str_arr[i]);
				lianxiren.setPhone(phone_jinji_str_arr[i]);
				oaLianxirenService.saveOALianxiren(lianxiren);
			}
		}
		ResponseUtils.renderText(response, "1");
	}
}
