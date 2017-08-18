package com.jingren.jing.crm.controller.entryinfo;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.druid.stat.TableStat.Mode;
import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.nation.service.TbNationService;
import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.educational.service.family.EntryFamilyService;
import com.jingren.jing.educational.service.viate.EntryViateService;
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
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.employee.EmployeeService;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
 * @Title: CrmEntryInfoController.java
 * @Package com.jingren.jing.crm.controller.entryinfo
 * @Description: TODO crm 系统专用学员信息
 * @author 鲁晓飞 MR.Lu
 * @date 2017年1月22日 下午4:39:07
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("crm_entryinfo")
public class CrmEntryInfoController {
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
	private EntryPlanService entryPlanService;
	@Resource
	private TbNationService tbNationService;
	@Resource
	private CityService cityService;
	@Resource
	private EntryPlaceService entryPlaceService;
	@Resource
	private CompanyService companyService;
	@Resource
	private EmployeeService employeeService;
	@Resource
	private UniversityService universityService;
	@Resource
	private EntryViateService entryViateService;
	@Resource
	private EntryFamilyService entryFamilyService;
	@Resource
	private ChengkaoScService chengkaoScService;
	/**
	 * @Title: CrmEntryInfoController.java
	 * @Package com.jingren.jing.crm.controller.entryinfo
	 * @Description: TODO 业务员相关的报考学员信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月23日 上午7:19:12
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/get_my_entry_info.jr")
	public String get_company_entry_info(Model model, HttpSession session,
			@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "entryUserPosition", required = false) String entryUserPosition,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "entryplanId", required = false) Integer entryplanId,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "zhuanye_id", required = false) Integer zhuanye_id,
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(entryUserName)) {// 学员姓名
			map.put("entryUserName", entryUserName);
			model.addAttribute("entryUserName", entryUserName);
		}
		if (StringUtils.isNotBlank(documentNumber)) {// 身份证号
			map.put("documentNumber", documentNumber);
			model.addAttribute("documentNumber", documentNumber);
		}
		if (StringUtils.isNotBlank(entryUserPosition)) {// 所在部门
			map.put("entryUserPosition", entryUserPosition);
			model.addAttribute("entryUserPosition", entryUserPosition);
		}
		if (StringUtils.isNotBlank(start_time) && StringUtils.isNotBlank(end_time)) {// 报名开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime = CommentDate.get_String_date(end_time).getTime();
			currentTime += 24 * 60 * 60 * 1000 - 1000;// 加23小时59分59秒
			Date date = new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		}
		if (entryplanId != null) {// 报名计划
			map.put("entryplanId", entryplanId);
			model.addAttribute("entryplanId", entryplanId);
		}
		if (university_id != null) {// 学校ID
			map.put("university_id", university_id);
			model.addAttribute("university_id", university_id);
		}
		if (zhuanye_id != null) {// 专业ID
			map.put("zhuanye_id", zhuanye_id);
			model.addAttribute("zhuanye_id", zhuanye_id);
		}
		// if (StringUtils.isNotBlank(orderNumber)) {
		// map.put("orderNumber", orderNumber);
		// }
		Employee employee = (Employee) session.getAttribute("employee_session");
		map.put("employee_id", employee.getEmployee_id());
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
		Integer entryInfos_number = entryInfoService.getEntryInfoNumber(map);
		for (EntryInfo entryInfo : entryInfos) {
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
			int shichang=CommentDate.daysBetween(entryInfo.getEntryInfoTime(), new Date());
			if (shichang < 30) {
				if (entryPlan.getCourse_id() != 20 && entryPlan.getCourse_id() != 19) {// 不是远程和成考的
					entryInfo.setKaoshimoey(entryPlan.getBaomingfei());// 考试费
					if(!entryInfo.getJiaocaofei().equals("0")){
						entryInfo.setJiaocaofei(entryPlan.getJiaocaifei());
					}
					entryInfo.setPeixunfei(entryPlan.getJingjiangbanfei());
					int zonge = Integer.valueOf(entryPlan.getBaomingfei()) + Integer.valueOf(entryInfo.getJiaocaofei())
							+ Integer.valueOf(entryPlan.getJingjiangbanfei())
							+ Integer.valueOf(entryInfo.getCailiaofei());
					//entryInfo.setYing_pay(String.valueOf(zonge));
					entryInfo.setQianfei(String.valueOf(zonge - Integer.valueOf(entryInfo.getPayMoney())
							- Integer.valueOf(entryInfo.getFan_fei())));
					entryInfoService.updateEntryInfo(entryInfo);
				} else if (entryPlan.getCourse_id() == 19||entryPlan.getCourse_id() == 20) {
					if(!entryInfo.getJiaocaofei().equals("0")){
						entryInfo.setJiaocaofei(entryPlan.getJiaocaifei());
					}
					int zonge = Integer.valueOf(entryPlan.getBaomingfei()) + Integer.valueOf(entryInfo.getJiaocaofei())
							+ Integer.valueOf(entryInfo.getCailiaofei()) + Integer.valueOf(entryInfo.getXuefei());
					//entryInfo.setYing_pay(String.valueOf(zonge));
					entryInfo.setQianfei(String.valueOf(zonge - Integer.valueOf(entryInfo.getPayMoney())
							- Integer.valueOf(entryInfo.getFan_fei())));
					entryInfoService.updateEntryInfo(entryInfo);
				}
			}
			Map<String, Object> map_info = new HashMap<>();
			map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
			EntryCondition condition = entryConditionService.getEntryCondition(map_info);
			map_info.clear();
			map_info.put("course_id", condition.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map_info);
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
			} else if (condition.getCourse_id() == 19) {
			
				entryInfo.setXuexiao(entryPlan.getEntryplan_content());
				if(entryInfo.getZhuanye_id()!=null){
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc=chengkaoScService.getChengkaoSc(map);
					entryInfo.setZhuanye(chengkaoSc.getChengkao_name());
				}
				entryInfo.setBaokaocengci(condition.getBaokao_cengci());
				entryInfo.setBaokaokemu(condition.getChengkao_kemu());
			} else {
				if (condition.getDictionary_id() != null) {
					map_info.clear();
					map_info.put("dictionary_id", condition.getDictionary_id());
					Dictionary dictionary = dictionaryService.getDictionary(map_info);
					entryInfo.setDictionary(dictionary);
				}
			}
			entryInfo.setEmployee(employee);
		}

		// 报名计划
		map.clear();
		map.put("is_show", "显示");
		List<EntryPlan> entryPlans = entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		// 报考学校
		map.clear();
		map.put("parent_id", 0);
		List<University> universities = universityService.getUniversityList(map);
		model.addAttribute("universities", universities);
		Pagers<EntryInfo> pagers = new Pagers<EntryInfo>(entryInfos_number, pageNumber, limit);
		pagers.setList(entryInfos);
		model.addAttribute("entryInfos", pagers);
		model.addAttribute("limit", limit);
		return "/crm/myentry_info/my_entry_info";
	}

	/**
	 * @Title: CrmEntryInfoController.java
	 * @Package com.jingren.jing.crm.controller.entryinfo
	 * @Description: TODO 去添加组织费
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月10日 下午5:26:08
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_zuzhifei.jr")
	public String to_add_zuzhifei(Model model, @RequestParam(value = "info_id", required = false) Integer info_id) {
		model.addAttribute("info_id", info_id);
		Map<String, Object> map = new HashMap<>();
		map.put("entryInfoId", info_id);
		EntryInfo entryInfo = entryInfoService.getEntryInfo(map);
		map.clear();
		map.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition condition = entryConditionService.getEntryCondition(map);
		map.clear();
		map.put("entryplan_id", entryInfo.getEntryplanId());
		EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
		if (entryPlan.getParent_id() == 1) {
			entryInfo.setYing_pay(entryPlan.getBaomingfei());
		}
		model.addAttribute("entryPlan", entryPlan);
		model.addAttribute("entryInfo", entryInfo);
		model.addAttribute("condition", condition);
		return "/crm/myentry_info/add_zuzhifei";
	}

	/**
	 * @Title: CrmEntryInfoController.java
	 * @Package com.jingren.jing.crm.controller.entryinfo
	 * @Description: TODO 查看所有审核的学员信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年5月25日 下午2:53:27
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_all_entry_info.jr")
	public String get_all_entry_info(Model model,
			@RequestParam(value = "entryUserName", required = false) String entryUserName,
			@RequestParam(value = "documentNumber", required = false) String documentNumber,
			@RequestParam(value = "bumen_id", required = false) Integer bumen_id,
			@RequestParam(value = "course_id", required = false, defaultValue = "0") Integer course_id,
			@RequestParam(value = "review_id", required = false, defaultValue = "0") Integer review_id,
			@RequestParam(value = "start_time", required = false) String start_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "entryplanId", required = false, defaultValue = "0") Integer entryplanId,
			@RequestParam(value = "employee_id", required = false) Integer employee_id,
			@RequestParam(value = "parent_id", required = false, defaultValue = "0") Integer parent_id,
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(entryUserName)) {// 学员姓名
			map.put("entryUserName", entryUserName);
			model.addAttribute("entryUserName", entryUserName);
		}
		if (StringUtils.isNotBlank(documentNumber)) {// 身份证号
			map.put("documentNumber", documentNumber);
			model.addAttribute("documentNumber", documentNumber);
		}
		if (bumen_id != null) {// 所在部门
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		if (StringUtils.isNotBlank(start_time) && StringUtils.isNotBlank(end_time)) {// 报名开始时间和结束时间
			map.put("start_time", CommentDate.get_String_date(start_time));
			long currentTime = CommentDate.get_String_date(end_time).getTime();
			currentTime += 24 * 60 * 60 * 1000 - 1000;// 加23小时59分59秒
			Date date = new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_time", start_time);
			model.addAttribute("end_time", end_time);
		}
		if (entryplanId != 0) {// 报名计划
			map.put("entryplanId", entryplanId);
			model.addAttribute("entryplanId", entryplanId);
		}
		if (employee_id != null) {// 业务员ID
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (parent_id != 0) {// 一级分类ID
			map.put("parent_id", parent_id);
			model.addAttribute("parent_id", parent_id);
		}
		if (review_id != 0) {// 三级分类ID
			map.put("review_id", review_id);
			model.addAttribute("review_id", review_id);
		}
		if (course_id != 0) {// 二级分类ID
			map.put("course_id", course_id);
			model.addAttribute("course_id", course_id);
		}
		// if (StringUtils.isNotBlank(orderNumber)) {
		// map.put("orderNumber", orderNumber);
		// }
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<EntryInfo> entryInfos = entryInfoService.getEntryInfoList(map);
		Integer entryInfos_number = entryInfoService.getEntryInfoNumber(map);
		for (EntryInfo entryInfo : entryInfos) {
			Map<String, Object> map_info = new HashMap<>();
			map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
			EntryCondition condition = entryConditionService.getEntryCondition(map_info);
			map_info.clear();
			map_info.put("course_id", condition.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map_info);
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
			} else if (condition.getCourse_id() == 19) {
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
			} else {
				if (condition.getDictionary_id() != null) {
					map_info.clear();
					map_info.put("dictionary_id", condition.getDictionary_id());
					Dictionary dictionary = dictionaryService.getDictionary(map_info);
					entryInfo.setDictionary(dictionary);
				}
			}
			if (entryInfo.getEmployee_id() != null) {
				map.clear();
				map.put("employee_id", entryInfo.getEmployee_id());
				Employee employee = employeeService.getEmployee(map);
				entryInfo.setEmployee(employee);
			}
			if (entryInfo.getParent_id() == null || entryInfo.getCourse_id() == null) {
				map_info.clear();
				map_info.put("entryplan_id", entryInfo.getEntryplanId());
				EntryPlan entryPlan = entryPlanService.getEntryPlan(map_info);
				entryInfo.setParent_id(entryPlan.getParent_id());
				if (entryPlan.getParent_id() == 9) {
					entryInfo.setReview_id(entryPlan.getReview_id());
				}
				entryInfo.setCourse_id(entryPlan.getCourse_id());
				entryInfoService.updateEntryInfo(entryInfo);
			}
			map.clear();
			map.put("entryplan_id", entryInfo.getEntryplanId());
			EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
			int shichang=CommentDate.daysBetween(entryInfo.getEntryInfoTime(), new Date());
			if (shichang < 30) {
				if (entryPlan.getCourse_id() != 20 && entryPlan.getCourse_id() != 19) {// 不是远程和成考的
					entryInfo.setKaoshimoey(entryPlan.getBaomingfei());// 考试费
					if(!entryInfo.getJiaocaofei().equals("0")){
						entryInfo.setJiaocaofei(entryPlan.getJiaocaifei());
					}
					entryInfo.setPeixunfei(entryPlan.getJingjiangbanfei());
					int zonge = Integer.valueOf(entryPlan.getBaomingfei()) + Integer.valueOf(entryInfo.getJiaocaofei())
							+ Integer.valueOf(entryPlan.getJingjiangbanfei())
							+ Integer.valueOf(entryInfo.getCailiaofei());
					//entryInfo.setYing_pay(String.valueOf(zonge));
					entryInfo.setQianfei(String.valueOf(zonge - Integer.valueOf(entryInfo.getPayMoney())
							- Integer.valueOf(entryInfo.getFan_fei())));
					entryInfoService.updateEntryInfo(entryInfo);
				} else if (entryPlan.getCourse_id() == 19||entryPlan.getCourse_id() == 20) {
					if(!entryInfo.getJiaocaofei().equals("0")){
						entryInfo.setJiaocaofei(entryPlan.getJiaocaifei());
					}
					int zonge = Integer.valueOf(entryPlan.getBaomingfei()) + Integer.valueOf(entryInfo.getJiaocaofei())
							+ Integer.valueOf(entryInfo.getCailiaofei()) + Integer.valueOf(entryInfo.getXuefei());
					//entryInfo.setYing_pay(String.valueOf(zonge));
					entryInfo.setQianfei(String.valueOf(zonge - Integer.valueOf(entryInfo.getPayMoney())
							- Integer.valueOf(entryInfo.getFan_fei())));
					entryInfoService.updateEntryInfo(entryInfo);
				}
			}
		}
		// 一级分类
		map.clear();
		map.put("is_show", "显示");
		map.put("parent_id", 0);
		List<EntryPlan> course_list = entryPlanService.getEntryQuChongPlanList(map);// 去重后的分类
		for (EntryPlan entryPlan : course_list) {
			map.clear();
			map.put("course_id", entryPlan.getParent_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			entryPlan.setCoursename(courseMenu.getCourse_name());
		}
		model.addAttribute("course_list", course_list);
		// 报考学校
		// map.clear();
		// map.put("parent_id", 0);
		// List<University> universities =
		// universityService.getUniversityList(map);
		// model.addAttribute("universities", universities);
		Pagers<EntryInfo> pagers = new Pagers<EntryInfo>(entryInfos_number, pageNumber, limit);
		pagers.setList(entryInfos);
		model.addAttribute("entryInfos", pagers);
		model.addAttribute("limit", limit);
		return "/crm/myentry_info/all_entry_info";
	}

	/**
	 * @Title: CrmEntryInfoController.java
	 * @Package com.jingren.jing.crm.controller.entryinfo
	 * @Description: TODO 职称论文
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月14日 下午3:34:03
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_lunwen.jr")
	public String to_update_lunwen(Model model,
			@RequestParam(value = "entryInfoId", required = false) Integer entryInfoId) {
		Map<String, Object> map = new HashMap<>();
		map.put("entryInfoId", entryInfoId);
		EntryInfo entryInfo = entryInfoService.getEntryInfo(map);
		model.addAttribute("entryInfo", entryInfo);
		return "/crm/myentry_info/update_lunwen";
	}

	/**
	 * @Title: CrmEntryInfoController.java
	 * @Package com.jingren.jing.crm.controller.entryinfo
	 * @Description: TODO 添加论文
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月14日 下午4:00:00
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("update_lunwen.jr")
	public void update_lunwen(EntryInfo entryInfo, HttpServletResponse response) {
		if (entryInfoService.updateEntryInfo(entryInfo)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: CrmEntryInfoController.java
	 * @Package com.jingren.jing.crm.controller.entryinfo
	 * @Description: TODO 删除学员信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月14日 下午7:09:16
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("delete_entry_info.jr")
	public void delete_entry_info(@RequestParam(value = "entryInfoId", required = false) Integer entryInfoId,
			HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		map.clear();
		map.put("entryInfoId", entryInfoId);
		EntryInfo entryInfo2 = entryInfoService.getEntryInfo(map);
		if (entryInfoService.deleteEntryInfo(entryInfoId)) {
			if (entryInfo2.getCertificatePic() != null) {
				DeleteFile.deleteFile1(entryInfo2.getCertificatePic(), request);
			}
			if (entryInfo2.getDocument_photo() != null) {
				DeleteFile.deleteFile1(entryInfo2.getDocument_photo(), request);
			}
			if (entryInfo2.getUserCardOpposite() != null) {
				DeleteFile.deleteFile1(entryInfo2.getUserCardOpposite(), request);
			}
			if (entryInfo2.getUserCardPositive() != null) {
				DeleteFile.deleteFile1(entryInfo2.getUserCardPositive(), request);
			}
			if (entryInfo2.getEntryUserPhoto() != null) {
				DeleteFile.deleteFile1(entryInfo2.getEntryUserPhoto(), request);
			}
			if (entryInfo2.getXuexin_beian() != null) {
				DeleteFile.deleteFile1(entryInfo2.getXuexin_beian(), request);
			}
			if (entryInfo2.getZhengshu_dabao() != null) {
				DeleteFile.deleteFile1(entryInfo2.getZhengshu_dabao(), request);
			}
			if (entryInfo2.getZhichenglunwen() != null) {
				DeleteFile.deleteFile1(entryInfo2.getZhichenglunwen(), request);
			}
			if (entryInfo2.getErcunzhao() != null) {
				DeleteFile.deleteFile1(entryInfo2.getErcunzhao(), request);
			}
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
}
