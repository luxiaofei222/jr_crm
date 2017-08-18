package com.jingren.jing.crm.controller.xueyuanshenhe;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
import com.jingren.jing.school.entrysystem.bean.repair.RepairMoney;
import com.jingren.jing.school.entrysystem.service.entrycondition.EntryConditionService;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.entrysystem.service.repair.RepairMoneyService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;

/**
* @Title: EntryInfoExamine.java 
* @Package com.jingren.jing.crm.controller.xueyuanshenhe 
* @Description: TODO CRM上级审核
* @author 鲁晓飞 MR.Lu   
* @date 2017年5月23日 上午8:56:29 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("crm_examine")
public class EntryInfoExamine {
	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private UserService userService;
	@Resource
	private EntryConditionService entryConditionService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private  UniversityService universityService;
	@Resource
	private EntryPlanService entryPlanService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private RepairMoneyService repairMoneyService;
	@Resource
	private ChengkaoScService chengkaoScService;
	/**
	* @Title: EntryInfoExamine.java 
	* @Package com.jingren.jing.crm.controller.xueyuanshenhe 
	* @Description: TODO 部门经理审核
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 上午8:58:16 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_bumenjingli_entryinfo.jr")
	public String get_entry_plan_main(Model model,HttpSession session,
			@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "entryUserPosition", required = false) String entryUserPosition,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "zhuanye_id", required = false) Integer zhuanye_id,
			@RequestParam(value = "mianshoubanxing", required = false) String mianshoubanxing,
			@RequestParam(value = "isPay", required = false) Integer isPay,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException{
		Employee employee_session=(Employee) session.getAttribute("employee_session");
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(entryUserName)){//学员姓名
			map.put("entryUserName", entryUserName);
			model.addAttribute("entryUserName", entryUserName);
		}
		if(StringUtils.isNotBlank(documentNumber)){//身份证号
			map.put("documentNumber", documentNumber);
			model.addAttribute("documentNumber", documentNumber);
		}
		if(StringUtils.isNotBlank(entryUserPosition)){//所在部门
			map.put("entryUserPosition", entryUserPosition);
			model.addAttribute("entryUserPosition", entryUserPosition);
		}
		
		if(employee_id!=null){//业务员
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//报名开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime =  CommentDate.get_String_date(end_time).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		}
		if(entryplanId!=null){//报名计划
			map.put("entryplanId", entryplanId);
			model.addAttribute("entryplanId", entryplanId);
		}
		if(university_id!=null){//学校ID
			map.put("university_id", university_id);
			model.addAttribute("university_id", university_id);
		}
		if(zhuanye_id!=null){//专业ID
			map.put("zhuanye_id", zhuanye_id);
			model.addAttribute("zhuanye_id", zhuanye_id);
		}
		if(StringUtils.isNotBlank(mianshoubanxing)){//面授班型
			map.put("mianshoubanxing", mianshoubanxing);
			model.addAttribute("mianshoubanxing", mianshoubanxing);
		}
		if(isPay!=null){//支付状态
			map.put("isPay", isPay);
			model.addAttribute("isPay", isPay);
		}
		if(StringUtils.isNotBlank(payType)){//支付方式
			map.put("payType", payType);
			model.addAttribute("payType", payType);
		}
		if(StringUtils.isNotBlank(price)){//通话时长
			map.put("price", price);
			model.addAttribute("price", price);
		}
		
		if(StringUtils.isNotBlank(orderNumber)){
			map.put("orderNumber", orderNumber);
		}
		//map.put("baokao_qudao", "网校");
		map.put("bumen_id", employee_session.getBumen_id());
		map.put("entry_info_state", 7);//部门经理审核
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<EntryInfo> entryInfos=entryInfoService.getEntryInfoList(map);
		Integer entryInfos_number=entryInfoService.getEntryInfoNumber(map);
		for (EntryInfo entryInfo : entryInfos) {
			Map<String, Object> map_info=new HashMap<>();
			map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
			EntryCondition condition=entryConditionService.getEntryCondition(map_info);
			map_info.clear();
			map_info.put("course_id", condition.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map_info);
			entryInfo.setCourseMenu(courseMenu);
			if (condition.getCourse_id() == 20) {
				map.clear();// 学校
				map.put("university_id", condition.getUniversity_id());
				University university = universityService.getUniversity(map);
				entryInfo.setXuexiao(university.getUniversity_name());

				map.clear();// 专业
				map.put("university_id", entryInfo.getZhuanye_id());
				University university2 = universityService.getUniversity(map);
				entryInfo.setZhuanye(university2.getUniversity_zhuanye());
			} else if(condition.getCourse_id()==19){
				map.clear();
				map.put("entryplan_id", entryInfo.getEntryplanId());
				EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
				entryInfo.setXuexiao(entryPlan.getEntryplan_content());
				if(entryInfo.getZhuanye_id()!=null){
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
					entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
				}
				entryInfo.setBaokaocengci(condition.getBaokao_cengci());
				entryInfo.setBaokaokemu(condition.getChengkao_kemu());
			}else {
				if(condition.getDictionary_id()!=null){
				map_info.clear();
				map_info.put("dictionary_id", condition.getDictionary_id());
				Dictionary dictionary = dictionaryService.getDictionary(map_info);
				entryInfo.setDictionary(dictionary);
				}
			}
			if(entryInfo.getEmployee_id()!=null){
				map_info.clear();
				map_info.put("employee_id", entryInfo.getEmployee_id());
				Employee employee=employeeService.getEmployee(map_info);
				map_info.clear();
				map_info.put("organization_id", employee.getBumen_id());
				Organization organization=organizationService.getOranization(map_info);
				employee.setOrganization(organization);
				entryInfo.setEmployee(employee);
			}
			if(Integer.valueOf(entryInfo.getYing_pay())==0){
				int yingpay=Integer.valueOf(entryInfo.getJiaocaofei())+Integer.valueOf(entryInfo.getKaoshimoey())+Integer.valueOf(entryInfo.getPeixunfei());
				EntryInfo entryInfo2=new EntryInfo();
				entryInfo2.setEntryInfoId(entryInfo.getEntryInfoId());
				entryInfo2.setYing_pay(String.valueOf(yingpay));//设置应支付的费用
				entryInfoService.updateEntryInfo(entryInfo2);
			}
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
			entryInfo.setEntryPlan(entryPlan);
		}
		//业务人员
		map.clear();
		map.put("parent_id", 5);
		map.put("employee_state", "在职");
		List<Employee> employees=employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		//报名计划
		map.clear();
		map.put("is_show", "显示");
		List<EntryPlan> entryPlans=entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		//报考学校
		map.clear();
		map.put("parent_id", 0);
		List<University> universities =universityService.getUniversityList(map);
		model.addAttribute("universities", universities);
		Pagers<EntryInfo> pagers = new Pagers<EntryInfo>(entryInfos_number,
				pageNumber, limit);
		pagers.setList(entryInfos);
		model.addAttribute("entryInfos", pagers);
		model.addAttribute("limit", limit);
		return "/finance/jinglishenhe/bumenentry_info";
	}
	
	/**
	* @Title: EntryInfoExamine.java 
	* @Package com.jingren.jing.crm.controller.xueyuanshenhe 
	* @Description: TODO 部门经理审核
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 上午9:43:13 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_examine_page.jr")
	public String to_examine_page(Model model,
			@RequestParam(value="entryinfo_id",required=false)Integer entryinfo_id){
		Map<String, Object> map=new HashMap<>();
		map.put("entryInfoId", entryinfo_id);
		EntryInfo entryInfo=entryInfoService.getEntryInfo(map);
		Map<String, Object> map_info=new HashMap<>();
		map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition condition=entryConditionService.getEntryCondition(map_info);
		model.addAttribute("condition", condition);
		map_info.clear();
		map_info.put("course_id", condition.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map_info);
		entryInfo.setCourseMenu(courseMenu);
		if(entryInfo.getEmployee_id()!=null){
			map_info.clear();
			map_info.put("employee_id", entryInfo.getEmployee_id());
			Employee employee=employeeService.getEmployee(map_info);
			entryInfo.setEmployee(employee);
		}
		if(condition.getCourse_id()==20){
			map.clear();//学校
			map.put("university_id", condition.getUniversity_id());
			University university=universityService.getUniversity(map);
			entryInfo.setXuexiao(university.getUniversity_name());
			
			map.clear();//专业
			map.put("university_id", entryInfo.getZhuanye_id());
			University university2=universityService.getUniversity(map);
			entryInfo.setZhuanye(university2.getUniversity_zhuanye());
		}else if(condition.getCourse_id()==19){
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
			entryInfo.setXuexiao(entryPlan.getEntryplan_content());
			if(entryInfo.getZhuanye_id()!=null){
				map.clear();
				map.put("chengkao_id", entryInfo.getZhuanye_id());
				ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
				entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
			}
			entryInfo.setBaokaocengci(condition.getBaokao_cengci());
			entryInfo.setBaokaokemu(condition.getChengkao_kemu());
		}else{
			if(condition.getDictionary_id()!=null){
			map_info.clear();
			map_info.put("dictionary_id", condition.getDictionary_id());
			Dictionary dictionary=dictionaryService.getDictionary(map_info);
			entryInfo.setDictionary(dictionary);
			}
		}
		model.addAttribute("entryInfo", entryInfo);
		/********************************补费信息**********************************/
		map.clear();
		map.put("entry_info_id", entryInfo.getEntryInfoId());
		List<RepairMoney> repairMoneys=repairMoneyService.getRepairMoneyList(map);
		model.addAttribute("repairMoneys", repairMoneys);
		return "/finance/jinglishenhe/bumenexamine";
	}
	
	/**
	* @Title: EntryInfoExamine.java 
	* @Package com.jingren.jing.crm.controller.xueyuanshenhe 
	* @Description: TODO 市场总监审核
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 上午10:55:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_shichangzongjian_entryinfo.jr")
	public String get_shichangzongjian_entryinfo(Model model,HttpSession session,
			@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "entryUserPosition", required = false) String entryUserPosition,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "zhuanye_id", required = false) Integer zhuanye_id,
			@RequestParam(value = "mianshoubanxing", required = false) String mianshoubanxing,
			@RequestParam(value = "isPay", required = false) Integer isPay,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException{
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(entryUserName)){//学员姓名
			map.put("entryUserName", entryUserName);
			model.addAttribute("entryUserName", entryUserName);
		}
		if(StringUtils.isNotBlank(documentNumber)){//身份证号
			map.put("documentNumber", documentNumber);
			model.addAttribute("documentNumber", documentNumber);
		}
		if(StringUtils.isNotBlank(entryUserPosition)){//所在部门
			map.put("entryUserPosition", entryUserPosition);
			model.addAttribute("entryUserPosition", entryUserPosition);
		}
		
		if(employee_id!=null){//业务员
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//报名开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime =  CommentDate.get_String_date(end_time).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		}
		if(entryplanId!=null){//报名计划
			map.put("entryplanId", entryplanId);
			model.addAttribute("entryplanId", entryplanId);
		}
		if(university_id!=null){//学校ID
			map.put("university_id", university_id);
			model.addAttribute("university_id", university_id);
		}
		if(zhuanye_id!=null){//专业ID
			map.put("zhuanye_id", zhuanye_id);
			model.addAttribute("zhuanye_id", zhuanye_id);
		}
		if(StringUtils.isNotBlank(mianshoubanxing)){//面授班型
			map.put("mianshoubanxing", mianshoubanxing);
			model.addAttribute("mianshoubanxing", mianshoubanxing);
		}
		if(isPay!=null){//支付状态
			map.put("isPay", isPay);
			model.addAttribute("isPay", isPay);
		}
		if(StringUtils.isNotBlank(payType)){//支付方式
			map.put("payType", payType);
			model.addAttribute("payType", payType);
		}
		if(StringUtils.isNotBlank(price)){//通话时长
			map.put("price", price);
			model.addAttribute("price", price);
		}
		
		if(StringUtils.isNotBlank(orderNumber)){
			map.put("orderNumber", orderNumber);
		}
		//map.put("baokao_qudao", "网校");
		map.put("entry_info_state", 8);//部门经理审核
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<EntryInfo> entryInfos=entryInfoService.getEntryInfoList(map);
		Integer entryInfos_number=entryInfoService.getEntryInfoNumber(map);
		for (EntryInfo entryInfo : entryInfos) {
			Map<String, Object> map_info=new HashMap<>();
			map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
			EntryCondition condition=entryConditionService.getEntryCondition(map_info);
			map_info.clear();
			map_info.put("course_id", condition.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map_info);
			entryInfo.setCourseMenu(courseMenu);
			if (condition.getCourse_id() == 20) {
				map.clear();// 学校
				map.put("university_id", condition.getUniversity_id());
				University university = universityService.getUniversity(map);
				entryInfo.setXuexiao(university.getUniversity_name());

				map.clear();// 专业
				map.put("university_id", entryInfo.getZhuanye_id());
				University university2 = universityService.getUniversity(map);
				entryInfo.setZhuanye(university2.getUniversity_zhuanye());
			} else if(condition.getCourse_id()==19){
				map.clear();
				map.put("entryplan_id", entryInfo.getEntryplanId());
				EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
				entryInfo.setXuexiao(entryPlan.getEntryplan_content());
				if(entryInfo.getZhuanye_id()!=null){
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
					entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
				}
				entryInfo.setBaokaocengci(condition.getBaokao_cengci());
				entryInfo.setBaokaokemu(condition.getChengkao_kemu());
			}else {
				if(condition.getDictionary_id()!=null){
				map_info.clear();
				map_info.put("dictionary_id", condition.getDictionary_id());
				Dictionary dictionary = dictionaryService.getDictionary(map_info);
				entryInfo.setDictionary(dictionary);
				}
			}
			if(entryInfo.getEmployee_id()!=null){
				map_info.clear();
				map_info.put("employee_id", entryInfo.getEmployee_id());
				Employee employee=employeeService.getEmployee(map_info);
				map_info.clear();
				map_info.put("organization_id", employee.getBumen_id());
				Organization organization=organizationService.getOranization(map_info);
				employee.setOrganization(organization);
				entryInfo.setEmployee(employee);
			}
			if(Integer.valueOf(entryInfo.getYing_pay())==0){
				int yingpay=Integer.valueOf(entryInfo.getJiaocaofei())+Integer.valueOf(entryInfo.getKaoshimoey())+Integer.valueOf(entryInfo.getPeixunfei());
				EntryInfo entryInfo2=new EntryInfo();
				entryInfo2.setEntryInfoId(entryInfo.getEntryInfoId());
				entryInfo2.setYing_pay(String.valueOf(yingpay));//设置应支付的费用
				entryInfoService.updateEntryInfo(entryInfo2);
			}
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
			entryInfo.setEntryPlan(entryPlan);
		}
		//业务人员
		map.clear();
		map.put("parent_id", 5);
		map.put("employee_state", "在职");
		List<Employee> employees=employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		//报名计划
		map.clear();
		map.put("is_show", "显示");
		List<EntryPlan> entryPlans=entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		//报考学校
		map.clear();
		map.put("parent_id", 0);
		List<University> universities =universityService.getUniversityList(map);
		model.addAttribute("universities", universities);
		Pagers<EntryInfo> pagers = new Pagers<EntryInfo>(entryInfos_number,
				pageNumber, limit);
		pagers.setList(entryInfos);
		model.addAttribute("entryInfos", pagers);
		model.addAttribute("limit", limit);
		return "/finance/jinglishenhe/zongjianentry_info";
	}
	/**
	* @Title: EntryInfoExamine.java 
	* @Package com.jingren.jing.crm.controller.xueyuanshenhe 
	* @Description: TODO 总监审核
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 上午11:04:22 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/zongjian_to_examine_page.jr")
	public String zongjian_to_examine_page(Model model,
			@RequestParam(value="entryinfo_id",required=false)Integer entryinfo_id){
		Map<String, Object> map=new HashMap<>();
		map.put("entryInfoId", entryinfo_id);
		EntryInfo entryInfo=entryInfoService.getEntryInfo(map);
		Map<String, Object> map_info=new HashMap<>();
		map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition condition=entryConditionService.getEntryCondition(map_info);
		model.addAttribute("condition", condition);
		map_info.clear();
		map_info.put("course_id", condition.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map_info);
		entryInfo.setCourseMenu(courseMenu);
		if(entryInfo.getEmployee_id()!=null){
			map_info.clear();
			map_info.put("employee_id", entryInfo.getEmployee_id());
			Employee employee=employeeService.getEmployee(map_info);
			entryInfo.setEmployee(employee);
		}
		if(condition.getCourse_id()==20){
			map.clear();//学校
			map.put("university_id", condition.getUniversity_id());
			University university=universityService.getUniversity(map);
			entryInfo.setXuexiao(university.getUniversity_name());
			
			map.clear();//专业
			map.put("university_id", entryInfo.getZhuanye_id());
			University university2=universityService.getUniversity(map);
			entryInfo.setZhuanye(university2.getUniversity_zhuanye());
		}else if(condition.getCourse_id()==19){
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
			entryInfo.setXuexiao(entryPlan.getEntryplan_content());
			if(entryInfo.getZhuanye_id()!=null){
				map.clear();
				map.put("chengkao_id", entryInfo.getZhuanye_id());
				ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
				entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
			}
			entryInfo.setBaokaocengci(condition.getBaokao_cengci());
			entryInfo.setBaokaokemu(condition.getChengkao_kemu());
		}else{
			if(condition.getDictionary_id()!=null){
			map_info.clear();
			map_info.put("dictionary_id", condition.getDictionary_id());
			Dictionary dictionary=dictionaryService.getDictionary(map_info);
			entryInfo.setDictionary(dictionary);
			}
		}
		model.addAttribute("entryInfo", entryInfo);
		/********************************补费信息**********************************/
		map.clear();
		map.put("entry_info_id", entryInfo.getEntryInfoId());
		List<RepairMoney> repairMoneys=repairMoneyService.getRepairMoneyList(map);
		model.addAttribute("repairMoneys", repairMoneys);
		return "/finance/jinglishenhe/zongjian_examine";
	}
	/**
	* @Title: EntryInfoExamine.java 
	* @Package com.jingren.jing.crm.controller.xueyuanshenhe 
	* @Description: TODO 总经理审核
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 上午11:27:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_boss_entryinfo.jr")
	public String get_boss_entryinfo(Model model,HttpSession session,
			@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "entryUserPosition", required = false) String entryUserPosition,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "zhuanye_id", required = false) Integer zhuanye_id,
			@RequestParam(value = "mianshoubanxing", required = false) String mianshoubanxing,
			@RequestParam(value = "isPay", required = false) Integer isPay,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "price", required = false) String price,
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException{
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(entryUserName)){//学员姓名
			map.put("entryUserName", entryUserName);
			model.addAttribute("entryUserName", entryUserName);
		}
		if(StringUtils.isNotBlank(documentNumber)){//身份证号
			map.put("documentNumber", documentNumber);
			model.addAttribute("documentNumber", documentNumber);
		}
		if(StringUtils.isNotBlank(entryUserPosition)){//所在部门
			map.put("entryUserPosition", entryUserPosition);
			model.addAttribute("entryUserPosition", entryUserPosition);
		}
		
		if(employee_id!=null){//业务员
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//报名开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime =  CommentDate.get_String_date(end_time).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		}
		if(entryplanId!=null){//报名计划
			map.put("entryplanId", entryplanId);
			model.addAttribute("entryplanId", entryplanId);
		}
		if(university_id!=null){//学校ID
			map.put("university_id", university_id);
			model.addAttribute("university_id", university_id);
		}
		if(zhuanye_id!=null){//专业ID
			map.put("zhuanye_id", zhuanye_id);
			model.addAttribute("zhuanye_id", zhuanye_id);
		}
		if(StringUtils.isNotBlank(mianshoubanxing)){//面授班型
			map.put("mianshoubanxing", mianshoubanxing);
			model.addAttribute("mianshoubanxing", mianshoubanxing);
		}
		if(isPay!=null){//支付状态
			map.put("isPay", isPay);
			model.addAttribute("isPay", isPay);
		}
		if(StringUtils.isNotBlank(payType)){//支付方式
			map.put("payType", payType);
			model.addAttribute("payType", payType);
		}
		if(StringUtils.isNotBlank(price)){//通话时长
			map.put("price", price);
			model.addAttribute("price", price);
		}
		
		if(StringUtils.isNotBlank(orderNumber)){
			map.put("orderNumber", orderNumber);
		}
		//map.put("baokao_qudao", "网校");
		map.put("entry_info_state", 9);//总经理审核
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<EntryInfo> entryInfos=entryInfoService.getEntryInfoList(map);
		Integer entryInfos_number=entryInfoService.getEntryInfoNumber(map);
		for (EntryInfo entryInfo : entryInfos) {
			Map<String, Object> map_info=new HashMap<>();
			map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
			EntryCondition condition=entryConditionService.getEntryCondition(map_info);
			map_info.clear();
			map_info.put("course_id", condition.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map_info);
			entryInfo.setCourseMenu(courseMenu);
			if (condition.getCourse_id() == 20) {
				map.clear();// 学校
				map.put("university_id", condition.getUniversity_id());
				University university = universityService.getUniversity(map);
				entryInfo.setXuexiao(university.getUniversity_name());

				map.clear();// 专业
				map.put("university_id", entryInfo.getZhuanye_id());
				University university2 = universityService.getUniversity(map);
				entryInfo.setZhuanye(university2.getUniversity_zhuanye());
			} else if(condition.getCourse_id()==19){
				map.clear();
				map.put("entryplan_id", entryInfo.getEntryplanId());
				EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
				entryInfo.setXuexiao(entryPlan.getEntryplan_content());
				if(entryInfo.getZhuanye_id()!=null){
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
					entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
				}
				entryInfo.setBaokaocengci(condition.getBaokao_cengci());
				entryInfo.setBaokaokemu(condition.getChengkao_kemu());
			}else {
				if(condition.getDictionary_id()!=null){
				map_info.clear();
				map_info.put("dictionary_id", condition.getDictionary_id());
				Dictionary dictionary = dictionaryService.getDictionary(map_info);
				entryInfo.setDictionary(dictionary);
				}
			}
			if(entryInfo.getEmployee_id()!=null){
				map_info.clear();
				map_info.put("employee_id", entryInfo.getEmployee_id());
				Employee employee=employeeService.getEmployee(map_info);
				map_info.clear();
				map_info.put("organization_id", employee.getBumen_id());
				Organization organization=organizationService.getOranization(map_info);
				employee.setOrganization(organization);
				entryInfo.setEmployee(employee);
			}
			if(Integer.valueOf(entryInfo.getYing_pay())==0){
				int yingpay=Integer.valueOf(entryInfo.getJiaocaofei())+Integer.valueOf(entryInfo.getKaoshimoey())+Integer.valueOf(entryInfo.getPeixunfei());
				EntryInfo entryInfo2=new EntryInfo();
				entryInfo2.setEntryInfoId(entryInfo.getEntryInfoId());
				entryInfo2.setYing_pay(String.valueOf(yingpay));//设置应支付的费用
				entryInfoService.updateEntryInfo(entryInfo2);
			}
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
			entryInfo.setEntryPlan(entryPlan);
		}
		//业务人员
		map.clear();
		map.put("parent_id", 5);
		map.put("employee_state", "在职");
		List<Employee> employees=employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		//报名计划
		map.clear();
		map.put("is_show", "显示");
		List<EntryPlan> entryPlans=entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		//报考学校
		map.clear();
		map.put("parent_id", 0);
		List<University> universities =universityService.getUniversityList(map);
		model.addAttribute("universities", universities);
		Pagers<EntryInfo> pagers = new Pagers<EntryInfo>(entryInfos_number,
				pageNumber, limit);
		pagers.setList(entryInfos);
		model.addAttribute("entryInfos", pagers);
		model.addAttribute("limit", limit);
		return "/finance/jinglishenhe/boss_entry_info";
	}
	/**
	* @Title: EntryInfoExamine.java 
	* @Package com.jingren.jing.crm.controller.xueyuanshenhe 
	* @Description: TODO 总经理审核页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月23日 上午11:29:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/boss_to_examine_page.jr")
	public String boss_to_examine_page(Model model,
			@RequestParam(value="entryinfo_id",required=false)Integer entryinfo_id){
		Map<String, Object> map=new HashMap<>();
		map.put("entryInfoId", entryinfo_id);
		EntryInfo entryInfo=entryInfoService.getEntryInfo(map);
		Map<String, Object> map_info=new HashMap<>();
		map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition condition=entryConditionService.getEntryCondition(map_info);
		model.addAttribute("condition", condition);
		map_info.clear();
		map_info.put("course_id", condition.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map_info);
		entryInfo.setCourseMenu(courseMenu);
		if(entryInfo.getEmployee_id()!=null){
			map_info.clear();
			map_info.put("employee_id", entryInfo.getEmployee_id());
			Employee employee=employeeService.getEmployee(map_info);
			entryInfo.setEmployee(employee);
		}
		if(condition.getCourse_id()==20){
			map.clear();//学校
			map.put("university_id", condition.getUniversity_id());
			University university=universityService.getUniversity(map);
			entryInfo.setXuexiao(university.getUniversity_name());
			
			map.clear();//专业
			map.put("university_id", entryInfo.getZhuanye_id());
			University university2=universityService.getUniversity(map);
			entryInfo.setZhuanye(university2.getUniversity_zhuanye());
		}else if(condition.getCourse_id()==19){
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
			entryInfo.setXuexiao(entryPlan.getEntryplan_content());
			if(entryInfo.getZhuanye_id()!=null){
				map.clear();
				map.put("chengkao_id", entryInfo.getZhuanye_id());
				ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
				entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
			}
			entryInfo.setBaokaocengci(condition.getBaokao_cengci());
			entryInfo.setBaokaokemu(condition.getChengkao_kemu());
		}else{
			if(condition.getDictionary_id()!=null){
			map_info.clear();
			map_info.put("dictionary_id", condition.getDictionary_id());
			Dictionary dictionary=dictionaryService.getDictionary(map_info);
			entryInfo.setDictionary(dictionary);
			}
		}
		model.addAttribute("entryInfo", entryInfo);
		/********************************补费信息**********************************/
		map.clear();
		map.put("entry_info_id", entryInfo.getEntryInfoId());
		List<RepairMoney> repairMoneys=repairMoneyService.getRepairMoneyList(map);
		model.addAttribute("repairMoneys", repairMoneys);
		return "/finance/jinglishenhe/boss_examine";
	}
}
