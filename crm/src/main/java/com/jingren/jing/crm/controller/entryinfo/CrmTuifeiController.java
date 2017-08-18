package com.jingren.jing.crm.controller.entryinfo;

import java.text.ParseException;
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
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("crm_tuifei")
public class CrmTuifeiController {
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
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 业务申请退费页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午2:32:22 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_shenqing_tuifei_page.jr")
	public String to_shenqing_tuifei_page(Model model,
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
		return "/crm/tuifei/tuifei_shenqing";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 退费申请到业务经理
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午2:44:16 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/tuifei_shenqing.jr")
	public void tuifei_shenqing(EntryInfo entryInfo,HttpServletResponse response,
			@RequestParam(value="type",required=false,defaultValue="0")Integer type){
		if(type==0){
			entryInfo.setShenqing_tuifei(new Date());
		}else{
			entryInfo.setTuifei_time(new Date());
		}
		if(entryInfoService.updateEntryInfo(entryInfo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 部门经理审核
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午2:52:04 
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
		if(employee_session.getRole_id()==13){
			map.put("tuifei_state", 2);//市场总监审核
		}else{
			map.put("bumen_id", employee_session.getBumen_id());
			map.put("tuifei_state", 1);//部门经理审核
		}
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
		return "/crm/tuifei/crm_tuifei_info";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO经理，总监审核退费
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午3:06:24 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_shenhe_tuifei_page.jr")
	public String to_shenhe_tuifei_page(Model model,
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
		return "/crm/tuifei/crm_shenhe";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 总经理审核退费
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午3:28:34 
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
		//Employee employee_session=(Employee) session.getAttribute("employee_session");
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
		map.put("tuifei_state", 3);//总经理审核
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
		return "/crm/tuifei/boss_tuifei_info";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 总经理审核退费页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午3:30:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/boss_shenhe_tuifei_page.jr")
	public String boss_shenhe_tuifei_page(Model model,
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
		return "/crm/tuifei/boss_shenhe";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 退费信息财务待审核
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午3:49:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_caiwu_entryinfo.jr")
	public String get_caiwu_entryinfo(Model model,HttpSession session,
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
		//Employee employee_session=(Employee) session.getAttribute("employee_session");
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
		map.put("tuifei_state", 4);//财务待审核
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
		return "/crm/tuifei/caiwu_tuifei_info";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 财务审核页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午3:57:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/caiwu_shenhe_tuifei_page.jr")
	public String caiwu_shenhe_tuifei_page(Model model,
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
		return "/crm/tuifei/caiwu_shenhe";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 退费审核成功
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午4:02:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_success_entryinfo.jr")
	public String get_success_entryinfo(Model model,HttpSession session,
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
		//Employee employee_session=(Employee) session.getAttribute("employee_session");
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
		map.put("tuifei_state", 5);//审核成功
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
		return "/crm/tuifei/success_tuifei_info";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 查看退费信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月3日 下午4:04:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_tuifei_page.jr")
	public String check_tuifei_page(Model model,
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
		return "/crm/tuifei/check_tuifei";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO导出退费信息窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月19日 下午3:12:29 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_daochu_entry_info.jr")
	public String to_daochu_entry_info(Model model){
		Map<String, Object> map=new HashMap<>();
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
		return "/crm/tuifei/downloadentryinfo";
	}
	/**
	* @Title: CrmTuifeiController.java 
	* @Package com.jingren.jing.crm.controller.entryinfo 
	* @Description: TODO 获取下载的路径
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月19日 下午3:15:43 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_down_load_record_url.jr")
	public void get_down_load_record_url(HttpServletResponse response,@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "entryUserPosition", required = false) String entryUserPosition,
			@RequestParam(value = "employee_id", required = false) String employee_id,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "bao_start_time", required = false) String bao_start_time,
			@RequestParam(value = "bao_end_time", required = false) String bao_end_time,
			@RequestParam(value = "entryplanId", required = false) String entryplanId,
			@RequestParam(value = "university_id", required = false) String university_id,
			@RequestParam(value = "zhuanye_id", required = false) String zhuanye_id,
			@RequestParam(value = "mianshoubanxing", required = false) String mianshoubanxing,
			@RequestParam(value = "tuifei_state", required = false) String tuifei_state,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "price", required = false) String price){
		if(StringUtils.isBlank(entryUserName)){//学员姓名
			entryUserName="";
		}
		if(StringUtils.isBlank(documentNumber)){//身份证号
			documentNumber="";
		}
		if(StringUtils.isBlank(entryUserPosition)){//所在部门
			entryUserPosition="";
		}
		if(StringUtils.isBlank(start_time)&&StringUtils.isNotBlank(end_time)){//报名开始时间和结束时间
			start_time="";
			end_time="";
		}
		if(StringUtils.isBlank(bao_start_time)&&StringUtils.isNotBlank(bao_end_time)){//报名开始时间和结束时间
			bao_start_time="";
			bao_end_time="";
		}
		if(StringUtils.isBlank(mianshoubanxing)){//面授班型
			mianshoubanxing="";
		}
		if(StringUtils.isBlank(payType)){//支付方式
			payType="";
		}
		if(StringUtils.isBlank(price)){//通话时长
			price="";
		}
		ResponseUtils.renderText(response, "/finance_download/export_tuifei_info_excel.jr?entryUserName="+entryUserName+"&documentNumber="+documentNumber+"&entryUserPosition="+entryUserPosition+"&employee_id="+
				employee_id+"&start_time="+start_time+"&end_time="+end_time+"&entryplanId="+entryplanId+"&university_id="+university_id+"&zhuanye_id="+zhuanye_id+"&mianshoubanxing="+mianshoubanxing+"&tuifei_state="+tuifei_state
				+"&payType="+payType+"&price="+price+"&bao_start_time="+bao_start_time+"&bao_end_time="+bao_end_time);
	}
}
