package com.jingren.jing.finance.controller.donwload;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
import com.jingren.jing.school.entrysystem.bean.repair.RepairMoney;
import com.jingren.jing.school.entrysystem.service.entrycondition.EntryConditionService;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.entryplace.EntryPlaceService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.entrysystem.service.repair.RepairMoneyService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.ExcelUtil;
import com.jingren.jing.util.ExcelUtil.ExcelExportData;

/**
 * @Title: EntryInfoDownLoadController.java
 * @Package com.jingren.jing.educational.controller.uploadexcel
 * @Description: TODO 导出学员信息
 * @author 鲁晓飞 MR.Lu
 * @date 2017年1月5日 上午10:55:18
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("finance_download")
public class FinanceEntryInfoDownLoadController {
	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private EntryConditionService entryConditionService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private EntryPlanService entryPlanService;
	@Resource
	private CityService cityService;
	@Resource
	private EntryPlaceService entryPlaceService;
	@Resource
	private CompanyService companyService;
	@Resource
	private CustomerService customerService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private UniversityService universityService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private ChengkaoScService chengkaoScService;
	@Resource
	private RepairMoneyService repairMoneyService;
	/**
	 * @Title: EntryInfoDownLoadController.java
	 * @Package com.jingren.jing.educational.controller.uploadexcel
	 * @Description: TODO 导出学员信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月5日 上午11:53:56
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/export_entry_info_excel.jr")
	public String export_entry_info_excel(HttpServletResponse response,
			@RequestParam(value = "info_state", required = false) Integer info_state,
			@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "entryUserPosition", required = false) String entryUserPosition,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "bao_start_time", required = false) String bao_start_time,
			@RequestParam(value = "bao_end_time", required = false) String bao_end_time,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "zhuanye_id", required = false) Integer zhuanye_id,
			@RequestParam(value = "mianshoubanxing", required = false) String mianshoubanxing,
			@RequestParam(value = "isPay", required = false) Integer isPay,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "price", required = false) String price) throws Exception {
		// 准备设置excel工作表的标题
//		String[] title = { "姓名", "性别", "民族", "身份证号", "文化程度", "专业", "联系方式", "报名条件", "工种（学校）", "等级", "工龄", "报名班型",
//				"业务员部门", "业务员", "是否补考", "标准收费", "折扣", "应收费用", "实收费用", "欠款", "打款日期", "打款银行", "返费" };
		String[] title = { "业务员", "所属部门", "学员姓名", "身份证号", "报考类别", "报考专业", "应收金额","材料费","降费金额","欠费金额","缴费金额", "学费",
				"报名费","教材费"/*,"提成金额"*/,"学位英语","公共英语三级","计算机一级","收款总额", "返费金额","交费时间", "支付方式","审核状态", "录入时间", "备注" };
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(entryUserName)){//学员姓名
			map.put("entryUserName", entryUserName);
		}
		if(StringUtils.isNotBlank(documentNumber)){//身份证号
			map.put("documentNumber", documentNumber);
		}
		if(StringUtils.isNotBlank(entryUserPosition)){//所在部门
			map.put("entryUserPosition", entryUserPosition);
		}
		
		if(employee_id!=null){//业务员
			map.put("employee_id", employee_id);
		}
		if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//报名开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime =  CommentDate.get_String_date(end_time).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("end_time", date);
		}
		
		if(StringUtils.isNotBlank(bao_start_time)&&StringUtils.isNotBlank(bao_end_time)){//交费开始时间和结束时间
			map.put("bao_start_time", CommentDate.get_String_date(bao_start_time));
			long currentTime =  CommentDate.get_String_date(bao_end_time).getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date=new Date(currentTime);
			map.put("bao_end_time", date);
		}
		if(entryplanId!=null){//报名计划
			map.put("entryplanId", entryplanId);
		}
		if(university_id!=null){//学校ID
			map.put("university_id", university_id);
		}
		if(zhuanye_id!=null){//专业ID
			map.put("zhuanye_id", zhuanye_id);
		}
		if(StringUtils.isNotBlank(mianshoubanxing)){//面授班型
			map.put("mianshoubanxing", mianshoubanxing);
		}
		if(isPay!=null){//支付状态
			if(isPay==1){//审核成功
				map.put("isPay", isPay);
			}else{//审核失败
				map.put("entry_info_state", 0);
			}
		}
		if(StringUtils.isNotBlank(payType)){//支付方式
			map.put("payType", payType);
		}
		if(StringUtils.isNotBlank(price)){//通话时长
			map.put("price", price);
		}
//		if(info_state!=null){
//			map.put("entry_info_state", info_state);//判断审核状态
//		}else{
//			map.put("entryinfo_state_zhangwu", 1);
//		}
		map.put("entryinfo_state_zhangwu", 1);
		List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
		for (EntryInfo entryInfo : entryInfos) {
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
			entryInfo.setBaomingjihua(entryPlan.getEntryplan_content());
			map.clear();
			map.put("entrycondition_id", entryInfo.getEntrycondition_id());
			EntryCondition condition = entryConditionService.getEntryCondition(map);
			entryInfo.setShenbaotiaojian(condition.getEntrycondition_content());
			entryInfo.setBaomingdian(
					entryInfo.getEntryProvince() + entryInfo.getEntryCity() + entryInfo.getEntrySchool());
			if (entryInfo.getEmployee_id() != null) {
				map.clear();
				map.put("employee_id", entryInfo.getEmployee_id());
				Employee employee = employeeService.getEmployee(map);
				entryInfo.setEmployee_name(employee.getEmployee_name());
				map.clear();
				map.put("organization_id", employee.getOrganization_id());
				Organization organization = organizationService.getOranization(map);
				entryInfo.setEmployee_bumen(organization.getOrganization_name());
			}
			if (entryInfo.getPay_time() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(entryInfo.getPay_time());
				entryInfo.setPay_time_str(dateString);
			}
			if (condition.getCourse_id() == 20) {
				map.clear();// 学校
				map.put("university_id", condition.getUniversity_id());
				University university = universityService.getUniversity(map);
				map.clear();//类别
				map.put("course_id", condition.getCourse_parent_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				entryInfo.setBaomingcourse(courseMenu.getCourse_name()+"-"+university.getUniversity_name());
				map.clear();// 专业
				map.put("university_id", entryInfo.getZhuanye_id());
				University university2 = universityService.getUniversity(map);
				entryInfo.setZhuanye(university2.getUniversity_zhuanye()+"-"+university2.getUniversity_type());
				entryInfo.setDengji(university2.getUniversity_type());
			} else if (condition.getCourse_id() == 19) {
				map.clear();//类别
				map.put("course_id", condition.getCourse_parent_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				entryInfo.setBaomingcourse(courseMenu.getCourse_name()+"-"+entryPlan.getEntryplan_content());
				if(entryInfo.getZhuanye_id()!=null){
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
					entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
				}
				entryInfo.setBaokaocengci(condition.getBaokao_cengci());
				entryInfo.setBaokaokemu(condition.getChengkao_kemu());
			}else {
				map.clear();
				map.put("course_id", condition.getCourse_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				map.clear();//类别
				map.put("course_id", condition.getCourse_parent_id());
				CourseMenu courseMenup = courseMenuService.getCouerseMenu(map);
				entryInfo.setBaomingcourse(courseMenup.getCourse_name()+"-"+courseMenu.getCourse_name());
				entryInfo.setHukousuozaidi(
						entryInfo.getEntryUserProvince() + entryInfo.getEntryUserCity() + entryInfo.getEntryUserArea());
				map.clear();
				map.put("dictionary_id", condition.getDictionary_id());
				Dictionary dictionary = dictionaryService.getDictionary(map);
				entryInfo.setZhuanye(dictionary.getDictionary_name());
			}
			
			if(entryInfo.getIsPay()==0){
				entryInfo.setShenhe_zhuangtai("审核失败");
			}else{
				entryInfo.setShenhe_zhuangtai("审核成功");
			}
			entryInfo.setJiaofei_shijian(CommentDate.get_date_string(entryInfo.getPay_time()));
			entryInfo.setLuru_shijian(CommentDate.get_date_string(entryInfo.getEntryInfoTime()));
			int xueweiyingyu=0;//学位英语费用
			if(StringUtils.isNotBlank(entryInfo.getXueweiyingyu())){
				xueweiyingyu=Integer.valueOf(entryInfo.getXueweiyingyu());
			}else{
				entryInfo.setXueweiyingyu("0");
			}
			int gonggongyingyu=0;//公共英语三级费用
			if(StringUtils.isNotBlank(entryInfo.getYingyusanji())){
				gonggongyingyu=Integer.valueOf(entryInfo.getYingyusanji());
			}else{
				entryInfo.setYingyusanji("0");
			}
			int jisuanjiyiji=0;//计算机一级费用
			if(StringUtils.isNotBlank(entryInfo.getJisuanjiyiji())){
				jisuanjiyiji=Integer.valueOf(entryInfo.getJisuanjiyiji());
			}else{
				entryInfo.setJisuanjiyiji("0");
			}
			int zonge=xueweiyingyu+gonggongyingyu+jisuanjiyiji+Integer.valueOf(entryInfo.getPayMoney());
			entryInfo.setZonge(String.valueOf(zonge));
//			if(condition.getCourse_id() != 19&&condition.getCourse_id() !=20){
//				int ticheng=Integer.valueOf(entryInfo.getPayMoney())-Integer.valueOf(entryInfo.getJiaocaofei());
//				entryInfo.setTicheng_money(String.valueOf(ticheng));//非学历考试 提成金额=已支付金额-教材费
//			}else{
//				int ticheng=Integer.valueOf(entryInfo.getPayMoney())-Integer.valueOf(entryInfo.getKaoshimoey())-Integer.valueOf(entryInfo.getJiaocaofei());
//				entryInfo.setTicheng_money(String.valueOf(ticheng));//学历考试 提成金额=已支付金额-报名费-教材费
//			}
		}
		List<String[]> columNames = new ArrayList<String[]>();
		columNames.add(title);
		List<String[]> fieldNames = new ArrayList<String[]>();
//		fieldNames.add(new String[] { "entryUserName", "entryUserSex", "entryUserNation", "documentNumber",
//				"entryUserEducation", "zhuanye", "entryUserPhone", "shenbaotiaojian", "baomingcourse", "dengji",
//				"workYears", "mianshoubanxing", "employee_bumen", "employee_name", "isbukao", "ying_pay", "zhekou",
//				"ying_pay", "payMoney", "qianfei", "pay_time_str", "payType", "zuzhifei" });
		fieldNames.add(new String[] {"employee_name","employee_bumen","entryUserName", "documentNumber", "baomingcourse", "zhuanye",
				"ying_pay","cailiaofei","fan_fei", "qianfei", "payMoney", "xuefei", "kaoshimoey", "jiaocaofei"/*, "ticheng_money"*/,"xueweiyingyu","yingyusanji","jisuanjiyiji","zonge", "zuzhifei", "jiaofei_shijian", "payType",
				"shenhe_zhuangtai", "luru_shijian", "note"});
		LinkedHashMap<String, List<?>> map1 = new LinkedHashMap<String, List<?>>();
		map1.put("财务-学员信息表", entryInfos);
		ExcelExportData setInfo = new ExcelExportData();
		setInfo.setDataMap(map1);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "财务-学员信息表" });
		setInfo.setColumnNames(columNames);
		ExcelUtil.directExpor(setInfo, response, "财务-学员信息表");
		return null;
	}
	/**
	* @Title: FinanceEntryInfoDownLoadController.java 
	* @Package com.jingren.jing.finance.controller.donwload 
	* @Description: TODO 导出补费信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月13日 下午5:08:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/export_bufei_info_excel.jr")
	public String export_bufei_info_excel(HttpServletResponse response,
			@RequestParam(value = "info_state", required = false) Integer info_state,
			@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "entryUserPosition", required = false) String entryUserPosition,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "bao_start_time", required = false) String bao_start_time,
			@RequestParam(value = "bao_end_time", required = false) String bao_end_time,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "zhuanye_id", required = false) Integer zhuanye_id,
			@RequestParam(value = "mianshoubanxing", required = false) String mianshoubanxing,
			@RequestParam(value = "isPay", required = false) Integer isPay,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "price", required = false) String price) throws Exception {
		// 准备设置excel工作表的标题
		String[] title = { "业务员", "所属部门", "学员姓名", "身份证号", "报考类别", "报考专业", "补费项目","补缴金额","初始欠费金额","交费时间", "支付方式", "录入时间", "备注" };
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(entryUserName)){//学员姓名
			map.put("entryUserName", entryUserName);
		}
		if(StringUtils.isNotBlank(documentNumber)){//身份证号
			map.put("documentNumber", documentNumber);
		}
		if(StringUtils.isNotBlank(entryUserPosition)){//所在部门
			map.put("entryUserPosition", entryUserPosition);
		}
		
		if(employee_id!=null){//业务员
			map.put("employee_id", employee_id);
		}
		
		if(entryplanId!=null){//报名计划
			map.put("entryplanId", entryplanId);
		}
		if(university_id!=null){//学校ID
			map.put("university_id", university_id);
		}
		if(zhuanye_id!=null){//专业ID
			map.put("zhuanye_id", zhuanye_id);
		}
		if(StringUtils.isNotBlank(mianshoubanxing)){//面授班型
			map.put("mianshoubanxing", mianshoubanxing);
		}
		if(isPay!=null){//支付状态
			if(isPay==1){//审核成功
				map.put("isPay", isPay);
			}else{//审核失败
				map.put("entry_info_state", 0);
			}
		}
		if(StringUtils.isNotBlank(payType)){//支付方式
			map.put("payType", payType);
		}
		if(StringUtils.isNotBlank(price)){//通话时长
			map.put("price", price);
		}
		map.put("entryinfo_state_zhangwu", 1);
		List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
		List<RepairMoney> linklist=new LinkedList<RepairMoney>();//补费信息
		for (EntryInfo entryInfo : entryInfos) {
			map.clear();
			map.put("entry_info_id", entryInfo.getEntryInfoId());
			if(StringUtils.isNotBlank(start_time)&&StringUtils.isNotBlank(end_time)){//报名开始时间和结束时间
				map.put("start_time", CommentDate.get_String_date(start_time));
				long currentTime =  CommentDate.get_String_date(end_time).getTime();
				currentTime +=24*60*60*1000-1000;//加23小时59分59秒
				Date date=new Date(currentTime);
				map.put("end_time", date);
			}
			
			if(StringUtils.isNotBlank(bao_start_time)&&StringUtils.isNotBlank(bao_end_time)){//交费开始时间和结束时间
				map.put("bao_start_time", CommentDate.get_String_date(bao_start_time));
				long currentTime =  CommentDate.get_String_date(bao_end_time).getTime();
				currentTime +=24*60*60*1000-1000;//加23小时59分59秒
				Date date=new Date(currentTime);
				map.put("bao_end_time", date);
			}
			List<RepairMoney> repairMoneys=repairMoneyService.getRepairMoneyList(map);
			for (RepairMoney repairMoney : repairMoneys) {
				if (entryInfo.getEmployee_id() != null) {
					map.clear();
					map.put("employee_id", entryInfo.getEmployee_id());
					Employee employee = employeeService.getEmployee(map);
					repairMoney.setEmployee_name(employee.getEmployee_name());//业务员
					map.clear();
					map.put("organization_id", employee.getOrganization_id());
					Organization organization = organizationService.getOranization(map);
					repairMoney.setBumen(organization.getOrganization_name());//部门
				}
				repairMoney.setUser_name(entryInfo.getEntryUserName());//学员姓名
				repairMoney.setCard_number(entryInfo.getDocumentNumber());//学员身份证号
				repairMoney.setPay_time_str(CommentDate.get_date_string(repairMoney.getPay_time()));//支付时间
				repairMoney.setRepair_time_str(CommentDate.get_date_string(repairMoney.getRepair_time()));//录入时间
				map.clear();
				map.put("entryplan_id", entryInfo.getEntryplanId());
				EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
				map.clear();
				map.put("entrycondition_id", entryInfo.getEntrycondition_id());
				EntryCondition condition = entryConditionService.getEntryCondition(map);
				if (condition.getCourse_id() == 20) {
					map.clear();// 学校
					map.put("university_id", condition.getUniversity_id());
					University university = universityService.getUniversity(map);
					map.clear();//类别
					map.put("course_id", condition.getCourse_parent_id());
					CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
					repairMoney.setLeibie(courseMenu.getCourse_name()+"-"+university.getUniversity_name());
					map.clear();// 专业
					map.put("university_id", entryInfo.getZhuanye_id());
					University university2 = universityService.getUniversity(map);
					repairMoney.setZhuanye(university2.getUniversity_zhuanye()+"-"+university2.getUniversity_type());
				} else if (condition.getCourse_id() == 19) {
					map.clear();//类别
					map.put("course_id", condition.getCourse_parent_id());
					CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
					repairMoney.setLeibie(courseMenu.getCourse_name()+"-"+entryPlan.getEntryplan_content());
					if(entryInfo.getZhuanye_id()!=null){
						map.clear();
						map.put("chengkao_id", entryInfo.getZhuanye_id());
						ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
						repairMoney.setZhuanye(chengkaoSc.getChengkao_name());
					}
				}else {
					map.clear();
					map.put("course_id", condition.getCourse_id());
					CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
					map.clear();//类别
					map.put("course_id", condition.getCourse_parent_id());
					CourseMenu courseMenup = courseMenuService.getCouerseMenu(map);
					repairMoney.setLeibie(courseMenup.getCourse_name()+"-"+courseMenu.getCourse_name());
					map.clear();
					map.put("dictionary_id", condition.getDictionary_id());
					Dictionary dictionary = dictionaryService.getDictionary(map);
					repairMoney.setZhuanye(dictionary.getDictionary_name());
				}
				repairMoney.setQianfei(entryInfo.getQianfei());//欠费
				linklist.add(repairMoney);
			}
		}
		List<String[]> columNames = new ArrayList<String[]>();
		columNames.add(title);
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] {"employee_name","bumen","user_name", "card_number", "leibie", "zhuanye", "repair_name", "pay_money", "qianfei",
				"pay_time_str", "paytype","repair_time_str", "note"});
		LinkedHashMap<String, List<?>> map1 = new LinkedHashMap<String, List<?>>();
		map1.put("财务-学员补费信息表", linklist);
		ExcelExportData setInfo = new ExcelExportData();
		setInfo.setDataMap(map1);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "财务-学员补费信息表" });
		setInfo.setColumnNames(columNames);
		ExcelUtil.directExpor(setInfo, response, "财务-学员补费信息表");
		return null;
	}
	/**
	* @Title: FinanceEntryInfoDownLoadController.java 
	* @Package com.jingren.jing.finance.controller.donwload 
	* @Description: TODO 导出退费信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月19日 下午3:16:52 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/export_tuifei_info_excel.jr")
	public String export_tuifei_info_excel(HttpServletResponse response,
			@RequestParam(value = "info_state", required = false) Integer info_state,
			@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "entryUserPosition", required = false) String entryUserPosition,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "bao_start_time", required = false) String bao_start_time,
			@RequestParam(value = "bao_end_time", required = false) String bao_end_time,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "zhuanye_id", required = false) Integer zhuanye_id,
			@RequestParam(value = "mianshoubanxing", required = false) String mianshoubanxing,
			@RequestParam(value = "tuifei_state", required = false) Integer tuifei_state,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "price", required = false) String price) throws Exception {
		// 准备设置excel工作表的标题
		String[] title = { "业务员", "所属部门", "学员姓名", "身份证号", "报考类别", "报考专业", "退费金额","退费说明","交费时间", "支付方式", "退费申请时间","退费状态", "备注" };
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(entryUserName)){//学员姓名
			map.put("entryUserName", entryUserName);
		}
		if(StringUtils.isNotBlank(documentNumber)){//身份证号
			map.put("documentNumber", documentNumber);
		}
		if(StringUtils.isNotBlank(entryUserPosition)){//所在部门
			map.put("entryUserPosition", entryUserPosition);
		}
		
		if(employee_id!=null){//业务员
			map.put("employee_id", employee_id);
		}
		
		if(entryplanId!=null){//报名计划
			map.put("entryplanId", entryplanId);
		}
		if(university_id!=null){//学校ID
			map.put("university_id", university_id);
		}
		if(zhuanye_id!=null){//专业ID
			map.put("zhuanye_id", zhuanye_id);
		}
		if(StringUtils.isNotBlank(mianshoubanxing)){//面授班型
			map.put("mianshoubanxing", mianshoubanxing);
		}
		if(tuifei_state!=null){//支付状态
			if(tuifei_state==1){//审核成功
				map.put("tuifei_state", 5);
			}else{//审核失败
				map.put("tuifei_state", 6);
			}
		}
		if(StringUtils.isNotBlank(payType)){//支付方式
			map.put("payType", payType);
		}
		if(StringUtils.isNotBlank(price)){//通话时长
			map.put("price", price);
		}
		map.put("no_tuifei_state", 0);
		List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
		for (EntryInfo entryInfo : entryInfos) {
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
			entryInfo.setBaomingjihua(entryPlan.getEntryplan_content());
			map.clear();
			map.put("entrycondition_id", entryInfo.getEntrycondition_id());
			EntryCondition condition = entryConditionService.getEntryCondition(map);
			entryInfo.setShenbaotiaojian(condition.getEntrycondition_content());
			entryInfo.setBaomingdian(
					entryInfo.getEntryProvince() + entryInfo.getEntryCity() + entryInfo.getEntrySchool());
			if (entryInfo.getEmployee_id() != null) {
				map.clear();
				map.put("employee_id", entryInfo.getEmployee_id());
				Employee employee = employeeService.getEmployee(map);
				entryInfo.setEmployee_name(employee.getEmployee_name());
				map.clear();
				map.put("organization_id", employee.getOrganization_id());
				Organization organization = organizationService.getOranization(map);
				entryInfo.setEmployee_bumen(organization.getOrganization_name());
			}
			if (entryInfo.getPay_time() != null) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(entryInfo.getPay_time());
				entryInfo.setPay_time_str(dateString);
			}
			if (condition.getCourse_id() == 20) {
				map.clear();// 学校
				map.put("university_id", condition.getUniversity_id());
				University university = universityService.getUniversity(map);
				map.clear();//类别
				map.put("course_id", condition.getCourse_parent_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				entryInfo.setBaomingcourse(courseMenu.getCourse_name()+"-"+university.getUniversity_name());
				map.clear();// 专业
				map.put("university_id", entryInfo.getZhuanye_id());
				University university2 = universityService.getUniversity(map);
				entryInfo.setZhuanye(university2.getUniversity_zhuanye()+"-"+university2.getUniversity_type());
				entryInfo.setDengji(university2.getUniversity_type());
			} else if (condition.getCourse_id() == 19) {
				map.clear();//类别
				map.put("course_id", condition.getCourse_parent_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				entryInfo.setBaomingcourse(courseMenu.getCourse_name()+"-"+entryPlan.getEntryplan_content());
				if(entryInfo.getZhuanye_id()!=null){
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
					entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
				}
				entryInfo.setBaokaocengci(condition.getBaokao_cengci());
				entryInfo.setBaokaokemu(condition.getChengkao_kemu());
			}else {
				map.clear();
				map.put("course_id", condition.getCourse_id());
				CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
				map.clear();//类别
				map.put("course_id", condition.getCourse_parent_id());
				CourseMenu courseMenup = courseMenuService.getCouerseMenu(map);
				entryInfo.setBaomingcourse(courseMenup.getCourse_name()+"-"+courseMenu.getCourse_name());
				entryInfo.setHukousuozaidi(
						entryInfo.getEntryUserProvince() + entryInfo.getEntryUserCity() + entryInfo.getEntryUserArea());
				map.clear();
				map.put("dictionary_id", condition.getDictionary_id());
				Dictionary dictionary = dictionaryService.getDictionary(map);
				entryInfo.setZhuanye(dictionary.getDictionary_name());
			}
			
			if(entryInfo.getIsPay()==0){
				entryInfo.setShenhe_zhuangtai("审核失败");
			}else{
				entryInfo.setShenhe_zhuangtai("审核成功");
			}
			entryInfo.setJiaofei_shijian(CommentDate.get_date_string(entryInfo.getPay_time()));
			entryInfo.setLuru_shijian(CommentDate.get_date_string(entryInfo.getEntryInfoTime()));
			if(entryInfo.getTuifei_time()!=null){
				entryInfo.setTuifei_time_str(CommentDate.get_date_string(entryInfo.getTuifei_time()));
			}
			//0 未退费 1业务经理审核 2总监审核 3总经理审核 4财务审核 5 退费成功 6被拒绝
			switch (entryInfo.getTuifei_state()) {
			case 1:
				entryInfo.setTuifei_state_str("业务经理审核中");
				break;
			case 2:
				entryInfo.setTuifei_state_str("市场总监审核中");
				break;
			case 3:
				entryInfo.setTuifei_state_str("总经理审核中");
				break;
			case 4:
				entryInfo.setTuifei_state_str("财务审核中");
				break;
			case 5:
				entryInfo.setTuifei_state_str("退费成功");
				break;
			case 6:
				entryInfo.setTuifei_state_str("审核失败");
				break;
			}
		}
		List<String[]> columNames = new ArrayList<String[]>();
		columNames.add(title);
		List<String[]> fieldNames = new ArrayList<String[]>();
		fieldNames.add(new String[] {"employee_name","employee_bumen","entryUserName", "documentNumber", "baomingcourse", "zhuanye", "tuifei", "tuifei_shuoming",
				"pay_time_str", "payType","tuifei_time_str","tuifei_state_str", "note"});
		LinkedHashMap<String, List<?>> map1 = new LinkedHashMap<String, List<?>>();
		map1.put("财务-学员退费信息表", entryInfos);
		ExcelExportData setInfo = new ExcelExportData();
		setInfo.setDataMap(map1);
		setInfo.setFieldNames(fieldNames);
		setInfo.setTitles(new String[] { "财务-学员退费信息表" });
		setInfo.setColumnNames(columNames);
		ExcelUtil.directExpor(setInfo, response, "财务-学员退费信息表");
		return null;
	}
}
