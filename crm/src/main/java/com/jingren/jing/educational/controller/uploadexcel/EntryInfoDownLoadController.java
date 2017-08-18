package com.jingren.jing.educational.controller.uploadexcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.ReviewService;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.crm.service.customer.CustomerService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
import com.jingren.jing.school.entrysystem.service.entrycondition.EntryConditionService;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.entryplace.EntryPlaceService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.employee.EmployeeService;
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
@RequestMapping("entry_info_download")
public class EntryInfoDownLoadController {
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
	private ReviewService reviewService;
	/**
	* @Title: EntryInfoDownLoadController.java 
	* @Package com.jingren.jing.educational.controller.uploadexcel 
	* @Description: TODO 导出学员信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月5日 上午11:53:56 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/export_entry_info_excel.jr")
	public String  export_entry_info_excel(HttpServletResponse response,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "parent_id", required = false) Integer parent_id,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "course_id_review", required = false) Integer course_id_review,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "start_time", required = false) Date start_time,
			@RequestParam(value = "end_time", required = false) Date end_time) throws Exception {
		// 准备设置excel工作表的标题
		// "培训费", "考试费", "教材费", "总费用", "支付方式",
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(entryplanId!=null){
			map.put("entryplanId", entryplanId);
//			map.put("entryinfo_state", 3);
		}
		if(parent_id!=null){
			map.put("parent_id", parent_id);
		}
		if(course_id!=null){
			map.put("course_id", course_id);
		}
		if(course_id_review!=null){
			map.put("review_id", course_id_review);
		}
		List<EntryCondition> conditions=entryConditionService.getEntryConditionList(map);
		if(parent_id==18){//网校
//			String[] title = { "报名计划", "报考类别", "申报条件", "报名地点", "报考层次", "报考专业","姓名", "性别", "身份证号码", "出生日期", "民族", "政治面貌",
//					"户口所在地","籍贯", "手机号", "电子邮箱", "联系QQ",  "邮编", "面授班型"};
			String[] title = { "姓名", "性别", "民族", "身份证号", "户籍", "报考层次","专业", "报考学校", "学习形式", "学制", "科类", "费用",
					"报考老师","联系电话", "邮箱", "邮编", "备注"};
			map.put("jaiowu_info_state", "jiaowu");//审核成功和提交至教育中心的才能导出
			//map.put("baokao_qudao", type);
			List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
			for (EntryInfo entryInfo : entryInfos) {
				EntryInfo entryInfo2=new EntryInfo();
				entryInfo2.setEntryInfoId(entryInfo.getEntryInfoId());
				entryInfo2.setIs_daochu("是");
				entryInfoService.updateEntryInfo(entryInfo2);
				map.clear();
				map.put("entryplan_id", entryInfo.getEntryplanId());
				EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
				entryInfo.setBaomingjihua(entryPlan.getEntryplan_content());
				map.clear();
				map.put("entrycondition_id", entryInfo.getEntrycondition_id());
				EntryCondition condition=entryConditionService.getEntryCondition(map);
				entryInfo.setShenbaotiaojian(condition.getEntrycondition_content());
				entryInfo.setBaomingdian(entryInfo.getEntryProvince()+entryInfo.getEntryCity()+entryInfo.getEntrySchool());
				entryInfo.setBaokaocengci(condition.getBaokao_cengci());
				map.clear();
				map.put("course_id", condition.getCourse_id());
				CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
				entryInfo.setBaomingcourse(courseMenu.getCourse_name());
				entryInfo.setHukousuozaidi(entryInfo.getEntryUserProvince()+entryInfo.getEntryUserCity()+entryInfo.getEntryUserArea());
				map.clear();
				map.put("university_id", entryInfo.getZhuanye_id());
				University university=universityService.getUniversity(map);
				entryInfo.setZhuanye(university.getUniversity_zhuanye());
				entryInfo.setXuexiao(university.getUniversity_name());
				entryInfo.setKelei(university.getUniversity_kinds());
				entryInfo.setXuezhi(university.getXuezhi());
			}
			List<String[]> columNames = new ArrayList<String[]>();
	        columNames.add(title);
	        List<String[]> fieldNames = new ArrayList<String[]>();//
//	        fieldNames.add(new String[] { "baomingjihua", "baomingcourse","shenbaotiaojian","baomingdian","baokaocengci","zhuanye","entryUserName","entryUserSex","documentNumber"
//	        		,"entryUserBirthday","entryUserNation","entryPolitical","hukousuozaidi","jiguan","entryUserPhone","entryUserMail","user_qq","zipCode","mianshoubanxing"});
	        fieldNames.add(new String[] { "entryUserName", "entryUserSex","entryUserNation","documentNumber","jiguan","baokaocengci","zhuanye","xuexiao","studry_type"
	        		,"xuezhi","kelei","payMoney","baokaolaoshi","entryUserPhone","entryUserMail","zipCode","note"});
	        LinkedHashMap<String, List<?>> map1 = new LinkedHashMap<String, List<?>>();
	        map1.put("学员信息表", entryInfos);
	        ExcelExportData setInfo = new ExcelExportData();
	        setInfo.setDataMap(map1);
	        setInfo.setFieldNames(fieldNames);
	        setInfo.setTitles(new String[] { "学员信息表"});
	        setInfo.setColumnNames(columNames);
	        ExcelUtil.directExpor(setInfo, response,"学员信息表");
		}else{//普通
//			String[] title = { "报名计划", "报考课程", "申报条件", "报名地点", "报考类别", "姓名", "性别", "证件类型", "证件号码", "出生日期", "民族", "政治面貌",
//					"户口所在地", "学员电话", "电子邮箱", "所在单位", "职位", "现住址", "邮编", "文化程度", "参加工作时间","连续工龄", "面授班型","紧急联系人", "紧急联系人电话"};
			List<String[]> columNames = new ArrayList<String[]>();
			map.put("jaiowu_info_state", "jiaowu");//审核成功和提交至教育中心的才能导出
			//map.put("baokao_qudao", type);
			List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
			for (EntryInfo entryInfo : entryInfos) {
				EntryInfo entryInfo2=new EntryInfo();
				entryInfo2.setEntryInfoId(entryInfo.getEntryInfoId());
				entryInfo2.setIs_daochu("是");
				entryInfoService.updateEntryInfo(entryInfo2);
				map.clear();
				map.put("entryplan_id", entryInfo.getEntryplanId());
				EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
				entryInfo.setBaomingjihua(entryPlan.getEntryplan_content());
				map.clear();
				map.put("entrycondition_id", entryInfo.getEntrycondition_id());
				EntryCondition condition=entryConditionService.getEntryCondition(map);
				entryInfo.setShenbaotiaojian(condition.getEntrycondition_content());
				entryInfo.setBaomingdian(entryInfo.getEntryProvince()+entryInfo.getEntryCity()+entryInfo.getEntrySchool());
				if(condition.getCourse_parent_id()==9){
					map.clear();
					map.put("course_id", condition.getCourse_id());
					CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
					map.clear();
					map.put("review_id", entryPlan.getReview_id());
					Review review=reviewService.getReview(map);
					entryInfo.setBaomingcourse(courseMenu.getCourse_name()+"-"+review.getReview_name());
				}else{
					map.clear();
					map.put("course_id", condition.getCourse_id());
					CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
					entryInfo.setBaomingcourse(courseMenu.getCourse_name());
				}
				
				entryInfo.setHukousuozaidi(entryInfo.getEntryUserProvince()+entryInfo.getEntryUserCity()+entryInfo.getEntryUserArea());
				String birteh=entryInfo.getEntryUserBirthday().replace("-", "");
				entryInfo.setEntryUserBirthday(birteh.substring(0,birteh.length()-2));
				if(StringUtils.isNotBlank(entryInfo.getBiye_time())){
					String diyi_biyetime=entryInfo.getBiye_time().replace("-", "");
					entryInfo.setBiye_time(diyi_biyetime.substring(0,diyi_biyetime.length()-2));
				}
				if(StringUtils.isNotBlank(entryInfo.getZuigao_biye_time())){
					String zuigao_biyetime=entryInfo.getBiye_time().replace("-", "");
					entryInfo.setZuigao_biye_time(zuigao_biyetime.substring(0,zuigao_biyetime.length()-2));
				}
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
			}
			if(parent_id==9){
				String[] title = { "姓名", "性别", "出生年月", "民族", "身份证号", "第一学历", "毕业院校(第一学历)", "所学专业(第一学历)", "学制(第一学历)", "学位(第一学历)","学习形式(第一学历)", "毕业证书编号(第一学历)","毕业时间(最高学历)", "最高学历",
						"毕业院校(最高学历)", "所学专业(最高学历)", "学制(最高学历)", "学位(最高学历)","学习形式(最高学历)", "毕业证书编号(最高学历)",
						"毕业时间(最高学历)", "申报专业", "入职时间", "论文题目", "论文期刊", "独著/合著", "论文时间", "摘要", "所在部门", "代理", "联系电话"};
				columNames.add(title);
			}else{
				String[] title = { "姓名", "性别", "民族", "身份证号", "文化程度", "专业", "联系方式", "报名条件", "鉴定工种", "鉴定等级", "工龄", "报名情况（VIP通关、精讲班、代报名、VIP后期）",
						"提交资料", "所缺资料", "报名机构（代理）", "是否补考", "验原件是否齐全", "备注", "所在单位", "联系电话"};
		        columNames.add(title);
			}
	        List<String[]> fieldNames = new ArrayList<String[]>();
	        if(parent_id==9){
	        	  fieldNames.add(new String[] { "entryUserName", "entryUserSex","entryUserBirthday","entryUserNation","documentNumber","diyi_xueli","biye_sc","suoxue_zhuanye","xuezhi"
	  	        		,"xuewei","studry_type","zhengshu_num","biye_time","zuigao_xueli","zuigao_xuexiao","zuigao_zhuanye","zuigaoxuezhi","zuigao_xuewei","zuigao_xuexi_type","zuigao_bianhao"
	  	        		,"zuigao_biye_time","baomingcourse","inWorkTime","lunwen_timu","lunwen_qikan","lunwen_duzhu","lunwen_time","lunwen_zhaiyao"
	  	        		,"employee_bumen","employee_name","entryUserPhone"});
	        }else{
	        	  fieldNames.add(new String[] { "entryUserName", "entryUserSex","entryUserNation","documentNumber","entryUserEducation","baomingcourse","entryUserPhone","shenbaotiaojian","baomingcourse"
	  	        		,"dengji","workYears","mianshoubanxing","tijiaoziliao","suoqueziliao","baomingjigou","isbukao","yuanjian_isqiquan","note","entryUserUnit"
	  	        		,"danwei_phone"});
	        }
	        LinkedHashMap<String, List<?>> map1 = new LinkedHashMap<String, List<?>>();
	        map1.put("学员信息表", entryInfos);
	        ExcelExportData setInfo = new ExcelExportData();
	        setInfo.setDataMap(map1);
	        setInfo.setFieldNames(fieldNames);
	        setInfo.setTitles(new String[] { "学员信息表"});
	        setInfo.setColumnNames(columNames);
	        ExcelUtil.directExpor(setInfo, response,"学员信息表");
		}
		
		return null;
	}
	
}
