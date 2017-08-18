package com.jingren.jing.educational.controller.entryinfo;

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
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.common.city.bean.City;
import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.nation.bean.TbNation;
import com.jingren.jing.common.nation.service.TbNationService;
import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.common.university.service.ReviewService;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.crm.bean.company.Company;
import com.jingren.jing.crm.service.company.CompanyService;
import com.jingren.jing.educational.bean.family.EntryFamily;
import com.jingren.jing.educational.bean.viate.EntryViate;
import com.jingren.jing.educational.service.family.EntryFamilyService;
import com.jingren.jing.educational.service.viate.EntryViateService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.bean.entryplace.EntryPlace;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
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
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;

/**
 * @Title: EduEntryInfoController.java
 * @Package com.jingren.jing.educational.controller.entryinfo
 * @Description: TODO 教务审核报考信息
 * @author 鲁晓飞 MR.Lu
 * @date 2016年12月30日 上午9:38:22
 * @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("edu_entry")
public class EduEntryInfoController {
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
	private ReviewService reviewService;
	@Resource
	private ChengkaoScService chengkaoScService;
	private static final String UP_FRONT_FILE = "images/entry";
	private static final String UP_ENTRY_FILE = "file/entryinfo";// 学员文件资料

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 教务学员审核页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月30日 上午9:42:27
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/get_entry_info_main.jr")
	public String get_entry_info_main(Model model,
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
		// map.put("baokao_qudao", "网校");
		map.put("entry_info_state", 2);// 财务审核完毕，提交给教务审核
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
				EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
				entryInfo.setXuexiao(entryPlan.getEntryplan_content());
				if (entryInfo.getZhuanye_id() != null) {
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
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
				map_info.clear();
				map_info.put("employee_id", entryInfo.getEmployee_id());
				Employee employee = employeeService.getEmployee(map_info);
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
		Pagers<EntryInfo> pagers = new Pagers<EntryInfo>(entryInfos_number, pageNumber, limit);
		pagers.setList(entryInfos);
		model.addAttribute("entryInfos", pagers);
		model.addAttribute("limit", limit);
		return "/educational/entryinfo/entry_info";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 企业报考学员
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月30日 上午10:56:30
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/get_company_entry_info.jr")
	public String get_company_entry_info(Model model,
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
		map.put("jaiowu_info_state", "jiaowu");
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
				EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
				entryInfo.setXuexiao(entryPlan.getEntryplan_content());
				if (entryInfo.getZhuanye_id() != null) {
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
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
				map_info.clear();
				map_info.put("employee_id", entryInfo.getEmployee_id());
				Employee employee = employeeService.getEmployee(map_info);
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
		Pagers<EntryInfo> pagers = new Pagers<EntryInfo>(entryInfos_number, pageNumber, limit);
		pagers.setList(entryInfos);
		model.addAttribute("entryInfos", pagers);
		model.addAttribute("limit", limit);
		return "/educational/entryinfo/company_entry_info";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 跳转审核报考学员信息弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月3日 上午9:45:54
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_examine_page.jr")
	private String to_examine_page(Model model,
			@RequestParam(value = "entryinfo_id", required = false) Integer entryinfo_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("entryInfoId", entryinfo_id);
		EntryInfo entryInfo = entryInfoService.getEntryInfo(map);
		Map<String, Object> map_info = new HashMap<>();
		map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition condition = entryConditionService.getEntryCondition(map_info);
		entryInfo.setCondition(condition);
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
			EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
			entryInfo.setXuexiao(entryPlan.getEntryplan_content());
			if (entryInfo.getZhuanye_id() != null) {
				map.clear();
				map.put("chengkao_id", entryInfo.getZhuanye_id());
				ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
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
		map_info.clear();
		map_info.put("entryplan_id", entryInfo.getEntryplanId());
		EntryPlan entryPlan = entryPlanService.getEntryPlan(map_info);
		entryInfo.setEntryPlan(entryPlan);
		if (entryPlan.getParent_id() == 9) {// 职称
			map.clear();
			map.put("review_id", entryPlan.getReview_id());
			Review review = reviewService.getReview(map);
			model.addAttribute("review", review);

			String xuexin_houzhui = entryInfo.getXuexin_beian()
					.substring(entryInfo.getXuexin_beian().lastIndexOf(".") + 1);
			model.addAttribute("xuexin_houzhui", xuexin_houzhui);
			model.addAttribute("entryInfo", entryInfo);
			return "/educational/common/examine_zhicheng_info";
		} else if (entryPlan.getCourse_id() == 19) {// 成考
			if (entryPlan.getUniversity_id() != 0) {
				map.clear();
				map.put("chengkao_id", entryPlan.getUniversity_id());
				ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
				model.addAttribute("chengkaoSc", chengkaoSc);
			}
			if (entryInfo.getZhuanye_id() != null) {// 专业
				map.clear();
				map.put("chengkao_id", entryInfo.getZhuanye_id());
				ChengkaoSc zhuanye = chengkaoScService.getChengkaoSc(map);
				model.addAttribute("zhuanye", zhuanye);
			}
			model.addAttribute("entryInfo", entryInfo);
			return "/educational/common/examine_chengkao_info";
		} else if (entryPlan.getCourse_id() == 20) {// 网教
			if (entryInfo.getUniversity_id() != null) {
				map.clear();
				map.put("university_id", entryInfo.getUniversity_id());
				University university = universityService.getUniversity(map);
				model.addAttribute("xuexiao", university.getUniversity_name());
			}
			if (entryInfo.getZhuanye_id() != null) {// 专业
				map.clear();
				map.put("university_id", entryInfo.getZhuanye_id());
				University university = universityService.getUniversity(map);
				model.addAttribute("zhuanye", university.getUniversity_zhuanye());
			}
			model.addAttribute("entryInfo", entryInfo);
			return "/educational/common/examine_wangjiao_info";
		} else {// 普通
			model.addAttribute("entryInfo", entryInfo);
			return "/educational/entryinfo/examine";
		}

	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 教务审核
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月3日 上午10:18:55
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/jiaowushenhe.jr")
	public void jiaowushenhe(EntryInfo entryInfo, HttpServletResponse response) {
		if (entryInfoService.updateEntryInfo(entryInfo)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO网校审核通过页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月3日 上午10:40:51
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_info_wangxiao_main.jr")
	public String get_entry_info_wangxiao_main(Model model,
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(orderNumber)) {
			map.put("orderNumber", orderNumber);
		}
		map.put("baokao_qudao", "网校");
		map.put("entry_info_state", 3);// 1.报名成功，但是没有审核 2，财务审核成功，提交给教务 3.教务审核成功
										// 4财务审核失败 5教务审核失败
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
				EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
				entryInfo.setXuexiao(entryPlan.getEntryplan_content());
				if (entryInfo.getZhuanye_id() != null) {
					map.clear();
					map.put("chengkao_id", entryInfo.getZhuanye_id());
					ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
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
				map_info.clear();
				map_info.put("employee_id", entryInfo.getEmployee_id());
				Employee employee = employeeService.getEmployee(map_info);
				entryInfo.setEmployee(employee);
			}
		}
		Pagers<EntryInfo> pagers = new Pagers<EntryInfo>(entryInfos_number, pageNumber, limit);
		pagers.setList(entryInfos);
		model.addAttribute("entryInfos", pagers);
		model.addAttribute("limit", limit);
		return "/educational/entryinfo/school_entry_info";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 教务查看报考信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月3日 上午10:48:28
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_page.jr")
	private String to_check_page(Model model,
			@RequestParam(value = "entryinfo_id", required = false) Integer entryinfo_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("entryInfoId", entryinfo_id);
		EntryInfo entryInfo = entryInfoService.getEntryInfo(map);
		Map<String, Object> map_info = new HashMap<>();
		map_info.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition condition = entryConditionService.getEntryCondition(map_info);
		entryInfo.setCondition(condition);
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
			EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
			entryInfo.setXuexiao(entryPlan.getEntryplan_content());
			if (entryInfo.getZhuanye_id() != null) {
				map.clear();
				map.put("chengkao_id", entryInfo.getZhuanye_id());
				ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
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
		map_info.clear();
		map_info.put("entryplan_id", entryInfo.getEntryplanId());
		EntryPlan entryPlan = entryPlanService.getEntryPlan(map_info);
		entryInfo.setEntryPlan(entryPlan);
		if (entryPlan.getParent_id() == 9) {// 职称
			map.clear();
			map.put("review_id", entryPlan.getReview_id());
			Review review = reviewService.getReview(map);
			model.addAttribute("review", review);

			String xuexin_houzhui = entryInfo.getXuexin_beian()
					.substring(entryInfo.getXuexin_beian().lastIndexOf(".") + 1);
			model.addAttribute("xuexin_houzhui", xuexin_houzhui);
			model.addAttribute("entryInfo", entryInfo);
			return "/educational/common/check_zhicheng_info";
		} else if (entryPlan.getCourse_id() == 19) {// 成考
			if (entryPlan.getUniversity_id() != 0) {
				map.clear();
				map.put("chengkao_id", entryPlan.getUniversity_id());
				ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
				model.addAttribute("chengkaoSc", chengkaoSc);
			}
			if (entryInfo.getZhuanye_id() != null) {// 专业
				map.clear();
				map.put("chengkao_id", entryInfo.getZhuanye_id());
				ChengkaoSc zhuanye = chengkaoScService.getChengkaoSc(map);
				model.addAttribute("zhuanye", zhuanye);
			}
			model.addAttribute("entryInfo", entryInfo);
			return "/educational/common/check_chengkao_info";
		} else if (entryPlan.getCourse_id() == 20) {// 网教
			if (entryPlan.getUniversity_id() != null) {
				map.clear();
				map.put("university_id", condition.getUniversity_id());
				University university = universityService.getUniversity(map);
				model.addAttribute("xuexiao", university.getUniversity_name());
			}
			if (entryInfo.getZhuanye_id() != null) {// 专业
				map.clear();
				map.put("university_id", entryInfo.getZhuanye_id());
				University university = universityService.getUniversity(map);
				model.addAttribute("zhuanye", university.getUniversity_zhuanye());
			}
			model.addAttribute("entryInfo", entryInfo);
			return "/educational/common/check_wangjiao_info";
		} else {// 普通
			model.addAttribute("entryInfo", entryInfo);
			return "/educational/entryinfo/checkentryinfo";
		}
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取分类的计划列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年5月22日 上午11:54:12
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_plan.jr")
	public String get_sub_plan(Model model,
			@RequestParam(value = "course_id_plan", required = false) Integer course_id_plan) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", course_id_plan);
		map.put("is_show", "显示");
		List<EntryPlan> entryPlans = entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		return "/educational/entryinfo/sub_plan";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取职称评审计划
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月8日 下午3:49:12
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_review_plan.jr")
	public String get_sub_review_plan(Model model,
			@RequestParam(value = "review_id", required = false) Integer review_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("review_id", review_id);
		map.put("is_show", "显示");
		List<EntryPlan> entryPlans = entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		return "/educational/entryinfo/sub_plan";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 添加学员信息弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月3日 下午4:47:20
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_entryinfo.jr")
	public String to_add_entryinfo(Model model,
			@RequestParam(value = "entrycondition_id", required = false) Integer entrycondition_id) {
		Map<String, Object> map = new HashMap<>();
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
		map.clear();
		List<TbNation> nations = tbNationService.getTbNationList();// 民族列表
		model.addAttribute("nations", nations);
		map.clear();
		map.put("leveltype", 1);
		List<City> cities = cityService.getCityList(map);
		model.addAttribute("cities", cities);
		map.clear();
		map.put("parent_id", 0);
		List<EntryPlace> entryPlaces = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces", entryPlaces);// 报名地点一级列表
		map.clear();
		map.put("parent_id", entryPlaces.get(0).getEntryplace_id());
		List<EntryPlace> entryPlaces_sub = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces_sub", entryPlaces_sub);
		map.clear();
		map.put("parent_id", entryPlaces_sub.get(0).getEntryplace_id());
		List<EntryPlace> entryPlaces_sub_1 = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces_sub_sub", entryPlaces_sub_1);
		map.clear();
		//map.put("parent_id", 5);
		map.put("employee_state", "在职");
		List<Employee> employees = employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);
		if (entrycondition_id != null) {
			map.clear();
			map.put("entrycondition_id", entrycondition_id);
			EntryCondition entryCondition = entryConditionService.getEntryCondition(map);
			if (entryCondition.getCourse_id() == 20) {// 切换网校模板
				map.clear();
				map.put("university_id", entryCondition.getUniversity_id());
				University university = universityService.getUniversity(map);
				model.addAttribute("university", university);
				map.clear();
				map.put("parent_id", entryCondition.getUniversity_id());
				map.put("university_type", entryCondition.getBaokao_cengci());
				List<University> universities = universityService.getUniversityList(map);
				model.addAttribute("universities", universities);
				return "/educational/common/addtable_xuexiao";
			} else if (entryCondition.getCourse_id() == 19) {// 成考模板
				return "/educational/common/add_chengkao_xuexiao";
			} else if (entryCondition.getCourse_parent_id() == 9) {// 职称评审模板

				return "/educational/common/addtable_zhicheng";
			} else {// 普通模板
					// model.addAttribute("entryCondition", entryCondition);
				return "/educational/common/addtable_public";
			}
		} else {
			return "/educational/entryinfo/addentryinfo";
		}
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取申报条件列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月3日 下午5:01:00
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_condition_list.jr")
	public String get_condition_list(Model model,
			@RequestParam(value = "entryplan_id", required = false) Integer entryplan_id) {
		Map<String, Object> map = new HashMap<>();
		// map.put("parent_id", 0);
		map.put("entryplan_id", entryplan_id);
		EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
		model.addAttribute("entryPlan", entryPlan);
		List<EntryCondition> conditions = entryConditionService.getEntryConditionListByCourseParent(map);
		for (EntryCondition entryCondition : conditions) {
			map.clear();
			map.put("course_id", entryCondition.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			entryCondition.setCourseMenu(courseMenu);
		}
		model.addAttribute("conditions", conditions);
		return "/educational/common/entry_condition";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取考试培训费等内容
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月22日 上午8:21:32
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_feiyong_info.jr")
	public String get_feiyong_info(Model model,
			@RequestParam(value = "entryplan_id", required = false) Integer entryplan_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("entryplan_id", entryplan_id);
		EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
		model.addAttribute("entryPlan", entryPlan);
		return "/educational/common/feiyong_info";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取条件单选框
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月3日 下午5:10:02
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_tiaojianlist.jr")
	public String get_tiaojianlist(Model model,
			@RequestParam(value = "entrycondition_id", required = false) Integer entrycondition_id) {
		Map<String, Object> map = new HashMap<>();

		map.put("entrycondition_id", entrycondition_id);
		EntryCondition entryCondition = entryConditionService.getEntryCondition(map);
		map.clear();
		map.put("course_id", entryCondition.getCourse_id());
		map.put("entryplan_id", entryCondition.getEntryplan_id());
		List<EntryCondition> quchongconditions = null;
		if (entryCondition.getCourse_id() == 19) {
			quchongconditions = entryConditionService.getEntryConditionListByCourseWangjiao(map);
		} else {
			quchongconditions = entryConditionService.getEntryConditionListByQuchonghoutai(map);
		}

		if (quchongconditions.size() == 0) {
			map.clear();
			map.put("entryplan_id", entryCondition.getEntryplan_id());
			quchongconditions = entryConditionService.getEntryConditionListByDic(map);
		}
		for (EntryCondition entryCondition2 : quchongconditions) {
			if (entryCondition2.getCourse_id() == 20) {
				map.clear();// 学校
				map.put("university_id", entryCondition2.getUniversity_id());
				University university = universityService.getUniversity(map);
				entryCondition2.setXuexiao(university.getUniversity_name());
			} else if (entryCondition2.getCourse_id() == 19) {
				map.clear();
				map.put("entryplan_id", entryCondition.getEntryplan_id());
				EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
				entryCondition2.setUniversity_id(entryPlan.getUniversity_id());
			} else {
				map.clear();
				map.put("dictionary_id", entryCondition2.getDictionary_id());
				Dictionary dictionary = dictionaryService.getDictionary(map);
				entryCondition2.setDictionary(dictionary);
			}

		}
		model.addAttribute("course_id", entryCondition.getCourse_id());
		model.addAttribute("quchongconditions", quchongconditions);
		return "/educational/common/entry_condition_dic";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取申报条件单选框
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月3日 下午5:44:47
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_tiaojian_radio.jr")
	public String get_tiaojian_radio(Model model,
			@RequestParam(value = "entrycondition_id", required = false) Integer entrycondition_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("entrycondition_id", entrycondition_id);
		EntryCondition entryCondition = entryConditionService.getEntryCondition(map);
		model.addAttribute("entryCondition", entryCondition);
		if (entryCondition.getCourse_id() != 20 && entryCondition.getCourse_id() != 19) {
			map.clear();
			map.put("parent_id", entryCondition.getParent_id());
			map.put("dictionary_id", entryCondition.getDictionary_id());
			List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
			model.addAttribute("subconditions", subconditions);
		} else if (entryCondition.getCourse_id() == 19) {
			map.clear();
			map.put("baokao_cengci", entryCondition.getBaokao_cengci());
			map.put("parent_id", entryCondition.getParent_id());
			List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
			for (EntryCondition entryCondition2 : subconditions) {
				map.clear();
				map.put("entryplan_id", entryCondition2.getEntryplan_id());
				EntryPlan entryPlan = entryPlanService.getEntryPlan(map);
				entryCondition2.setUniversity_id(entryPlan.getUniversity_id());
			}
			model.addAttribute("subconditions", subconditions);
		} else if (entryCondition.getCourse_id() == 20) {
			map.clear();
			map.put("university_id", entryCondition.getUniversity_id());
			University university = universityService.getUniversity(map);// 获取学校名称
			entryCondition.setXuexiao(university.getUniversity_name());
			map.clear();
			map.put("university_id", entryCondition.getUniversity_id());
			map.put("parent_id", entryCondition.getParent_id());
			List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
			model.addAttribute("subconditions", subconditions);
		}

		return "/educational/common/tiaojianlist";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 保存学员报名信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月4日 上午9:13:30
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/save_entry_info.jr")
	public void save_entry_info(EntryInfo entryInfo, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "pay_time_str", required = false) String pay_time_str,
			@RequestParam(value = "star_time", required = false) String star_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "suozaidanwei", required = false) String suozaidanwei,
			@RequestParam(value = "zhengmingren", required = false) String zhengmingren,
			@RequestParam(value = "xingming", required = false) String xingming,
			@RequestParam(value = "guanxi", required = false) String guanxi,
			@RequestParam(value = "nianling", required = false) String nianling,
			@RequestParam(value = "family_danwei", required = false) String family_danwei, HttpSession session,
			@RequestParam(value = "entry_city_id", required = false) Integer entry_city_id,
			@RequestParam(value = "entry_province_id", required = false) Integer entry_province_id,
			@RequestParam(value = "entry_school_id", required = false) Integer entry_school_id,
			@RequestParam(value = "zhengjian_pic", required = false) MultipartFile zhengjian_pic,
			@RequestParam(value = "zhengmian_pic", required = false) MultipartFile zhengmian_pic,
			@RequestParam(value = "fanmian_pic", required = false) MultipartFile fanmian_pic,
			@RequestParam(value = "xueli_pic", required = false) MultipartFile xueli_pic,
			@RequestParam(value = "user_pic", required = false) MultipartFile user_pic,
			@RequestParam(value = "xuexin_beian_str", required = false) MultipartFile xuexin_beian_str,
			@RequestParam(value = "ercunzhao_str", required = false) MultipartFile ercunzhao_str,
			@RequestParam(value = "zhengshu_dabao_str", required = false) MultipartFile zhengshu_dabao_str,
			@RequestParam(value = "zhichenglunwen_str", required = false) MultipartFile zhichenglunwen_str,
			@RequestParam(value = "province", required = false) Integer province,
			@RequestParam(value = "user_city", required = false) Integer user_city,
			@RequestParam(value = "province_jiguang", required = false) Integer province_jiguang,
			@RequestParam(value = "jiguan_city", required = false) Integer jiguan_city) throws ParseException {
		// Employee employee = (Employee)
		// session.getAttribute("employee_session");
		// entryInfo.setEmployee_id(employee.getEmployee_id());

		entryInfo.setEntryInfoTime(new Date());
		if (Integer.valueOf(entryInfo.getFan_fei()) > 0 || Integer.valueOf(entryInfo.getZuzhifei()) > 0) {
			entryInfo.setEntryInfoState(7);// 组织费或者返费都要提交部门经理审核
		} else {
			entryInfo.setEntryInfoState(1);// 报名成功，需要财务审核
		}
		entryInfo.setIsPay(0);// 默认未付款，由财务审核是否付款
		entryInfo.setOrderNumber(DesUtil.get_baoming_number());// 订单号
		if (StringUtils.isNotBlank(pay_time_str)) {
			entryInfo.setPay_time(CommentDate.get_String_date(pay_time_str));
		} else {
			entryInfo.setPay_time(new Date());
		}
		entryInfo.setBaokao_qudao("企业");
		if (StringUtils.isBlank(entryInfo.getPayMoney())) {
			entryInfo.setPayMoney("0");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("employee_id", entryInfo.getEmployee_id());
		Employee employee = employeeService.getEmployee(map);
		entryInfo.setBumen_id(employee.getBumen_id());
		map.clear();
		map.put("entryplace_id", entry_city_id);
		EntryPlace entryPlace = entryPlaceService.getEntryPlace(map);
		map.clear();
		map.put("entryplace_id", entry_province_id);
		EntryPlace entryPlace_province = entryPlaceService.getEntryPlace(map);
		map.clear();
		map.put("entryplace_id", entry_school_id);
		EntryPlace entryPlace_school = entryPlaceService.getEntryPlace(map);
		entryInfo.setEntryCity(entryPlace.getEntryplace_name());// 省份
		entryInfo.setEntryProvince(entryPlace_province.getEntryplace_name());// 城市
		entryInfo.setEntrySchool(entryPlace_school.getEntryplace_name());// 报名校区
		if (zhengjian_pic != null) {// 暂时无用
			String zhengjianpath = UploadAddress.getUploadDate(zhengjian_pic, request, UP_FRONT_FILE);
			entryInfo.setDocument_photo(zhengjianpath);
		}
		if (zhengmian_pic != null) {
			String zhengmianpath = UploadAddress.getUploadDate(zhengmian_pic, request, UP_FRONT_FILE);
			entryInfo.setUserCardPositive(zhengmianpath);// 身份证正面
		}
		if (fanmian_pic != null) {
			String fanmianpath = UploadAddress.getUploadDate(fanmian_pic, request, UP_FRONT_FILE);
			entryInfo.setUserCardOpposite(fanmianpath);// 身份证反面
		}
		if (xueli_pic != null) {
			String xueli_picpath = UploadAddress.getUploadDate(xueli_pic, request, UP_FRONT_FILE);
			entryInfo.setCertificatePic(xueli_picpath);// 学历证书
		}
		if (user_pic != null) {
			String user_picpath = UploadAddress.getUploadDate(user_pic, request, UP_FRONT_FILE);
			entryInfo.setEntryUserPhoto(user_picpath);// 个人免冠照片
		}
		if (zhengshu_dabao_str != null) {
			String zhengshu_picpath = UploadAddress.getUploadDate(zhengshu_dabao_str, request, UP_ENTRY_FILE);
			entryInfo.setZhengshu_dabao(zhengshu_picpath);// 证书资料打包
		}
		if (xuexin_beian_str != null) {
			String xuexinpath = UploadAddress.getUploadDate(xuexin_beian_str, request, UP_ENTRY_FILE);
			entryInfo.setXuexin_beian(xuexinpath);// 学信网备案表
		}
		if (ercunzhao_str != null) {
			String ercunpath = UploadAddress.getUploadDate(ercunzhao_str, request, UP_ENTRY_FILE);
			entryInfo.setErcunzhao(ercunpath);// 两寸照片
		}
		if (zhichenglunwen_str != null) {
			String lunwen_path = UploadAddress.getUploadDate(zhichenglunwen_str, request, UP_ENTRY_FILE);
			entryInfo.setZhichenglunwen(lunwen_path);// 论文文件资料
		}
		if (province != null && province != 0) {
			map.clear();
			map.put("id", province);
			City city = cityService.getCity(map);
			entryInfo.setEntryUserProvince(city.getName());
		}
		if (user_city != null && user_city != 0) {
			map.clear();
			map.put("id", user_city);
			City city = cityService.getCity(map);
			entryInfo.setEntryUserCity(city.getName());
		}
		if(StringUtils.isBlank(entryInfo.getCailiaofei().trim())){
			entryInfo.setCailiaofei("0");
		}
		Map<String, Object> mapinfo = new HashMap<>();
		if (entryInfo.getEntryUserUnit() != null) {
			mapinfo.put("company_name", entryInfo.getEntryUserUnit());
			Company company = companyService.getCompany(mapinfo);
			if (company != null) {
				entryInfo.setCompany_id(company.getCompany_id());
			}
		}

		if (province_jiguang != null && province_jiguang != 0 && jiguan_city != null && jiguan_city != 0) {// 籍贯
			map.clear();
			map.put("id", province_jiguang);
			City city = cityService.getCity(map);
			map.clear();
			map.put("id", jiguan_city);
			City city2 = cityService.getCity(map);
			entryInfo.setJiguan(city.getName() + city2.getName());
		}
		if (entryInfoService.saveEntryInfo(entryInfo)) {
			// 个人简历
			if (StringUtils.isNotBlank(star_time)) {
				String[] star_time_arry = star_time.split(",");
				String[] end_time_arry = end_time.split(",");
				String[] suozaidanwei_arry = suozaidanwei.split(",");
				String[] zhengmingren_arry = zhengmingren.split(",");
				EntryViate entryViate = new EntryViate();
				entryViate.setEntry_info_id(entryInfo.getEntryInfoId());
				for (int i = 0; i < star_time_arry.length; i++) {
					entryViate.setTime_qujian(star_time_arry[i] + "至" + end_time_arry[i]);
					entryViate.setDanwei_xuexiao(suozaidanwei_arry[i]);
					entryViate.setZhengmingren(zhengmingren_arry[i]);
					if (!suozaidanwei_arry[i].equals("暂无") || !zhengmingren_arry[i].equals("暂无")) {
						entryViateService.saveEntryViate(entryViate);
					}
				}
			}
			// 家庭成员
			if (StringUtils.isNotBlank(xingming)) {
				String[] xingming_arry = xingming.split(",");
				String[] guanxi_arry = guanxi.split(",");
				String[] nianling_arry = nianling.split(",");
				String[] family_danwei_arry = family_danwei.split(",");
				EntryFamily entryFamily = new EntryFamily();
				entryFamily.setEntry_info_id(entryInfo.getEntryInfoId());
				for (int i = 0; i < xingming_arry.length; i++) {
					entryFamily.setFamily_name(xingming_arry[i]);
					entryFamily.setGuanxi(guanxi_arry[i]);
					entryFamily.setNianling(nianling_arry[i]);
					entryFamily.setGongzuodanwei(family_danwei_arry[i]);
					if (!xingming_arry[i].equals("暂无") || !guanxi_arry[i].equals("暂无") || !nianling_arry[i].equals("暂无")
							|| !family_danwei_arry[i].equals("暂无")) {
						entryFamilyService.saveEntryFamily(entryFamily);
					}
				}
			}
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}

	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 修改页面弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月4日 下午1:07:20
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_entryinfo.jr")
	public String to_update_entryinfo(Model model,
			@RequestParam(value = "entryInfoId", required = false) Integer entryInfoId) {
		Map<String, Object> map = new HashMap<>();
		map.put("is_show", "显示");
		List<EntryPlan> entryPlans = entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		map.clear();
		List<TbNation> nations = tbNationService.getTbNationList();// 民族列表
		model.addAttribute("nations", nations);
		map.clear();
		map.put("leveltype", 1);
		List<City> cities = cityService.getCityList(map);
		model.addAttribute("cities", cities);
		map.clear();
		map.put("parent_id", 0);
		List<EntryPlace> entryPlaces = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces", entryPlaces);// 报名地点一级列表
		map.clear();
		map.put("parent_id", entryPlaces.get(0).getEntryplace_id());
		List<EntryPlace> entryPlaces_sub = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces_sub", entryPlaces_sub);
		map.clear();
		map.put("parent_id", entryPlaces_sub.get(0).getEntryplace_id());
		List<EntryPlace> entryPlaces_sub_1 = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces_sub_sub", entryPlaces_sub_1);
		map.clear();
		map.put("zuoxi", "zuoxi");
		List<Employee> employees = employeeService.getEmployeeList(map);
		model.addAttribute("employees", employees);// 相关业务员
		map.clear();
		map.put("entryInfoId", entryInfoId);
		EntryInfo entryInfo = entryInfoService.getEntryInfo(map);
		model.addAttribute("entryInfo", entryInfo);
		map.clear();
		map.put("entryplan_id", entryInfo.getEntryplanId());
		List<EntryCondition> conditions = entryConditionService.getEntryConditionListByCourseParent(map);
		for (EntryCondition entryCondition : conditions) {
			map.clear();
			map.put("course_id", entryCondition.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			entryCondition.setCourseMenu(courseMenu);
		}
		model.addAttribute("conditions", conditions);
		map.clear();
		map.put("entrycondition_id", entryInfo.getEntrycondition_id());
		EntryCondition entryCondition2 = entryConditionService.getEntryCondition(map);
		map.clear();
		map.put("course_id", entryCondition2.getCourse_id());
		map.put("entryplan_id", entryCondition2.getEntryplan_id());
		// map.put("parent_id", entryCondition.getEntrycondition_id());
		List<EntryCondition> quchongconditions = entryConditionService.getEntryConditionListByQuchonghoutai(map);
		if (quchongconditions.size() == 0) {
			map.clear();
			map.put("entryplan_id", entryCondition2.getEntryplan_id());
			quchongconditions = entryConditionService.getEntryConditionListByDic(map);
		}
		for (EntryCondition entryCondition3 : quchongconditions) {
			if (entryCondition3.getCourse_id() != 20 && entryCondition3.getCourse_id() != 19) {
				map.clear();
				map.put("dictionary_id", entryCondition3.getDictionary_id());
				Dictionary dictionary = dictionaryService.getDictionary(map);
				entryCondition3.setDictionary(dictionary);
			} else if (entryCondition3.getCourse_id() == 19) {

			} else if (entryCondition3.getCourse_id() == 20) {
				map.clear();
				map.put("university_id", entryCondition3.getUniversity_id());
				University university = universityService.getUniversity(map);// 获取学校名称
				entryCondition3.setXuexiao(university.getUniversity_name());

			}

		}
		model.addAttribute("quchongconditions", quchongconditions);
		map.clear();
		map.put("parent_id", entryCondition2.getParent_id());
		map.put("dictionary_id", entryCondition2.getDictionary_id());
		List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
		model.addAttribute("subconditions", subconditions);
		model.addAttribute("entryCondition2", entryCondition2);
		if (entryCondition2.getCourse_id() == 20) {
			map.clear();
			map.put("parent_id", entryCondition2.getUniversity_id());
			map.put("university_type", entryCondition2.getBaokao_cengci());
			List<University> universities = universityService.getUniversityList(map);
			model.addAttribute("universities", universities);

			List<EntryFamily> entryFamilies = entryFamilyService.getEntryFamilyList(entryInfoId);// 家庭成员
			List<EntryViate> entryViates = entryViateService.getEntryViateList(entryInfoId);// 紧急联系人
			model.addAttribute("entryFamilies", entryFamilies);
			model.addAttribute("entryViates", entryViates);
			return "/educational/entryinfo/updateentryinfo_xuexiao";
		} else if (entryCondition2.getCourse_id() == 19) {// 成考修改资料
			if (entryInfo.getZhuanye_id() != null) {
				map.clear();
				map.put("chengkao_id", entryInfo.getZhuanye_id());
				ChengkaoSc chengkaoSc = chengkaoScService.getChengkaoSc(map);
				model.addAttribute("chengkaoSc", chengkaoSc);
			}

			return "/educational/entryinfo/update_chengkao_info";
		} else if (entryCondition2.getCourse_parent_id() == 9) {// 修改职称评审

			return "/educational/entryinfo/update_zhicheng_info";
		} else {
			return "/educational/entryinfo/updateentryinfo";
		}
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取企业名称
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月4日 下午1:49:38
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_company_json.jr")
	public String get_company_json(Model model,
			@RequestParam(value = "company_name", required = false) String company_name) {
		Map<String, Object> map = new HashMap<>();
		map.put("company_name", company_name);
		map.put("pageNumber", 0);
		map.put("limit", 15);
		List<Company> companies = companyService.getCompanyList(map);
		model.addAttribute("companies", companies);
		return "/educational/entryinfo/tip_company";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 修改学员信息
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月5日 上午8:42:29
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_entry_info.jr")
	public void update_entry_info(EntryInfo entryInfo, HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "entry_city_id", required = false) Integer entry_city_id,
			@RequestParam(value = "entry_province_id", required = false) Integer entry_province_id,
			@RequestParam(value = "entry_school_id", required = false) Integer entry_school_id,
			@RequestParam(value = "zhengjian_pic", required = false) MultipartFile zhengjian_pic,
			@RequestParam(value = "zhengmian_pic", required = false) MultipartFile zhengmian_pic,
			@RequestParam(value = "fanmian_pic", required = false) MultipartFile fanmian_pic,
			@RequestParam(value = "xueli_pic", required = false) MultipartFile xueli_pic,
			@RequestParam(value = "user_pic", required = false) MultipartFile user_pic,
			@RequestParam(value = "province", required = false) Integer province,
			@RequestParam(value = "user_city", required = false) Integer user_city,
			@RequestParam(value = "star_time", required = false) String star_time,
			@RequestParam(value = "end_time", required = false) String end_time,
			@RequestParam(value = "suozaidanwei", required = false) String suozaidanwei,
			@RequestParam(value = "zhengmingren", required = false) String zhengmingren,
			@RequestParam(value = "xingming", required = false) String xingming,
			@RequestParam(value = "guanxi", required = false) String guanxi,
			@RequestParam(value = "nianling", required = false) String nianling,
			@RequestParam(value = "family_danwei", required = false) String family_danwei) {
		Map<String, Object> map = new HashMap<>();
		map.put("entryInfoId", entryInfo.getEntryInfoId());
		EntryInfo entryInfo2 = entryInfoService.getEntryInfo(map);
		if (entryInfo2.getEntryInfoState() == 0 || entryInfo2.getEntryInfoState() == 4
				|| entryInfo2.getEntryInfoState() == 5) {
			if (Integer.valueOf(entryInfo2.getFan_fei()) > 0 || Integer.valueOf(entryInfo2.getZuzhifei()) > 0) {
				entryInfo.setBumen_yijian("0");
				entryInfo.setZongjian_yijian("0");
				entryInfo.setBoss_yijian("0");
				// entryInfo.setCaiwushenhe_message("0");
				entryInfo.setJiaowushenhe_message("0");
				entryInfo.setEntryInfoState(7);// 组织费或者返费都要提交部门经理审核
			} else {
				entryInfo.setBumen_yijian("0");
				entryInfo.setZongjian_yijian("0");
				entryInfo.setBoss_yijian("0");
				// entryInfo.setCaiwushenhe_message("0");
				entryInfo.setJiaowushenhe_message("0");
				entryInfo.setEntryInfoState(1);// 报名成功，需要财务审核
			}
		}
		// map.clear();
		// map.put("entryplace_id", entry_city_id);
		// EntryPlace entryPlace = entryPlaceService.getEntryPlace(map);
		// map.clear();
		// map.put("entryplace_id", entry_province_id);
		// EntryPlace entryPlace_province =
		// entryPlaceService.getEntryPlace(map);
		// map.clear();
		// map.put("entryplace_id", entry_school_id);
		// EntryPlace entryPlace_school = entryPlaceService.getEntryPlace(map);
		// entryInfo.setEntryCity(entryPlace.getEntryplace_name());// 省份
		// entryInfo.setEntryProvince(entryPlace_province.getEntryplace_name());//
		// 城市
		// entryInfo.setEntrySchool(entryPlace_school.getEntryplace_name());//
		// 报名校区
		if (zhengjian_pic != null) {
			String zhengjianpath = UploadAddress.getUploadDate(zhengjian_pic, request, UP_FRONT_FILE);
			entryInfo.setDocument_photo(zhengjianpath);
			DeleteFile.deleteFile1(entryInfo2.getDocument_photo(), request);
		}
		if (zhengmian_pic != null) {
			String zhengmianpath = UploadAddress.getUploadDate(zhengmian_pic, request, UP_FRONT_FILE);
			entryInfo.setUserCardPositive(zhengmianpath);// 身份证正面
			DeleteFile.deleteFile1(entryInfo2.getUserCardPositive(), request);
		}
		if (fanmian_pic != null) {
			String fanmianpath = UploadAddress.getUploadDate(fanmian_pic, request, UP_FRONT_FILE);
			entryInfo.setUserCardOpposite(fanmianpath);// 身份证反面
			DeleteFile.deleteFile1(entryInfo2.getUserCardOpposite(), request);
		}
		if (xueli_pic != null) {
			String xueli_picpath = UploadAddress.getUploadDate(xueli_pic, request, UP_FRONT_FILE);
			entryInfo.setCertificatePic(xueli_picpath);// 学历证书
			DeleteFile.deleteFile1(entryInfo2.getCertificatePic(), request);
		}
		if (user_pic != null) {
			String user_picpath = UploadAddress.getUploadDate(user_pic, request, UP_FRONT_FILE);
			entryInfo.setEntryUserPhoto(user_picpath);// 个人免冠照片
			DeleteFile.deleteFile1(entryInfo2.getEntryUserPhoto(), request);
		}
		if (province != null && province != 0) {
			map.clear();
			map.put("id", province);
			City city = cityService.getCity(map);
			entryInfo.setEntryUserProvince(city.getName());
		}
		if (user_city != null && user_city != 0) {
			map.clear();
			map.put("id", user_city);
			City city = cityService.getCity(map);
			entryInfo.setEntryUserCity(city.getName());
		}
		Map<String, Object> mapinfo = new HashMap<>();
		if (StringUtils.isNotBlank(entryInfo.getEntryUserUnit())) {
			mapinfo.put("company_name", entryInfo.getEntryUserUnit());
			Company company = companyService.getCompany(mapinfo);
			if (company != null) {
				entryInfo.setCompany_id(company.getCompany_id());
			}
		}
		if (entryInfoService.updateEntryInfo(entryInfo)) {
			// 个人简历
			if (StringUtils.isNotBlank(star_time)) {
				String[] star_time_arry = star_time.split(",");
				String[] end_time_arry = end_time.split(",");
				String[] suozaidanwei_arry = suozaidanwei.split(",");
				String[] zhengmingren_arry = zhengmingren.split(",");
				EntryViate entryViate = new EntryViate();
				entryViate.setEntry_info_id(entryInfo.getEntryInfoId());
				for (int i = 0; i < star_time_arry.length; i++) {
					entryViate.setTime_qujian(star_time_arry[i] + "至" + end_time_arry[i]);
					entryViate.setDanwei_xuexiao(suozaidanwei_arry[i]);
					entryViate.setZhengmingren(zhengmingren_arry[i]);
					if (!suozaidanwei_arry[i].equals("暂无") || !zhengmingren_arry[i].equals("暂无")) {
						entryViateService.saveEntryViate(entryViate);
					}
				}
			}
			// 家庭成员
			if (StringUtils.isNotBlank(xingming)) {
				String[] xingming_arry = xingming.split(",");
				String[] guanxi_arry = guanxi.split(",");
				String[] nianling_arry = nianling.split(",");
				String[] family_danwei_arry = family_danwei.split(",");
				EntryFamily entryFamily = new EntryFamily();
				entryFamily.setEntry_info_id(entryInfo.getEntryInfoId());
				for (int i = 0; i < xingming_arry.length; i++) {
					entryFamily.setFamily_name(xingming_arry[i]);
					entryFamily.setGuanxi(guanxi_arry[i]);
					entryFamily.setNianling(nianling_arry[i]);
					entryFamily.setGongzuodanwei(family_danwei_arry[i]);
					if (!xingming_arry[i].equals("暂无") || !guanxi_arry[i].equals("暂无") || !nianling_arry[i].equals("暂无")
							|| !family_danwei_arry[i].equals("暂无")) {
						entryFamilyService.saveEntryFamily(entryFamily);
					}
				}
			}
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 添加组织费
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年3月10日 下午5:27:22
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/update_zuzhifei.jr")
	public void update_zuzhifei(EntryInfo entryInfo, HttpServletResponse response,
			@RequestParam(value = "pay_time_str", required = false) String pay_time_str) throws ParseException {
		Map<String, Object> map = new HashMap<>();
		map.put("entryInfoId", entryInfo.getEntryInfoId());
		EntryInfo entryInfo2 = entryInfoService.getEntryInfo(map);
		// entryInfo.setQianfei(String.valueOf(Integer.valueOf(entryInfo2.getYing_pay())-Integer.valueOf(entryInfo.getPayMoney())-Integer.valueOf(entryInfo.getFan_fei())));
		if (entryInfo2.getEntryInfoState() == 0 || entryInfo2.getEntryInfoState() == 4
				|| entryInfo2.getEntryInfoState() == 5) {
			if (Integer.valueOf(entryInfo2.getFan_fei()) > 0 || Integer.valueOf(entryInfo2.getZuzhifei()) > 0) {
				entryInfo.setBumen_yijian("0");
				entryInfo.setZongjian_yijian("0");
				entryInfo.setBoss_yijian("0");
				// entryInfo.setCaiwushenhe_message("0");
				entryInfo.setJiaowushenhe_message("0");
				entryInfo.setEntryInfoState(7);// 组织费或者返费都要提交部门经理审核
			} else {
				entryInfo.setBumen_yijian("0");
				entryInfo.setZongjian_yijian("0");
				entryInfo.setBoss_yijian("0");
				// entryInfo.setCaiwushenhe_message("0");
				entryInfo.setJiaowushenhe_message("0");
				entryInfo.setEntryInfoState(1);// 报名成功，需要财务审核
			}
		}
		if (StringUtils.isNotBlank(pay_time_str)) {
			entryInfo.setPay_time(CommentDate.get_String_date(pay_time_str));
		}
		if (entryInfoService.updateEntryInfo(entryInfo)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 导入学员信息弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月5日 上午8:37:25
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_upload_entry_info.jr")
	public String to_upload_entry_info(Model model) {
		Map<String, Object> map = new HashMap<>();
		List<EntryPlan> entryPlans = entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		map.clear();
		map.put("parent_id", 0);
		List<EntryPlace> entryPlaces = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces", entryPlaces);// 报名地点一级列表
		map.clear();
		map.put("parent_id", entryPlaces.get(0).getEntryplace_id());
		List<EntryPlace> entryPlaces_sub = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces_sub", entryPlaces_sub);
		map.clear();
		map.put("parent_id", entryPlaces_sub.get(0).getEntryplace_id());
		List<EntryPlace> entryPlaces_sub_1 = entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces_sub_sub", entryPlaces_sub_1);
		return "/educational/upload/uploadentryinfo";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 导出学员信息弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月5日 上午10:51:32
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_daochu_entry_info.jr")
	public String to_daochu_entry_info(Model model, @RequestParam(value = "type", required = false) String type) {
		Map<String, Object> map = new HashMap<>();
		// List<EntryPlan> entryPlans = entryPlanService.getEntryPlanList(map);
		// model.addAttribute("entryPlans", entryPlans);
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
		model.addAttribute("type", type);
		return "/educational/upload/downloadentryinfo";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 进入导出图片页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月18日 下午1:48:25
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_daochu_entry_info_pic.jr")
	public String to_daochu_entry_info_pic(Model model) {
		Map<String, Object> map = new HashMap<>();
		List<EntryPlan> entryPlans = entryPlanService.getEntryPlanList(map);
		model.addAttribute("entryPlans", entryPlans);
		return "/educational/upload/downloadentryinfopic";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 给学员发送通知页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月20日 上午8:07:47
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_send_message.jr")
	public String to_send_message(Model model, @RequestParam(value = "user_id", required = false) String user_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("user_id", user_id);
		User user = userService.getUser(map);
		model.addAttribute("user", user);
		return "/educational/common/send_message";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取二级分类
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月8日 上午11:47:24
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_couse.jr")
	public String get_sub_couse(Model model, @RequestParam(value = "parent_id", required = false) Integer parent_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("is_show", "显示");
		map.put("course_id", 0);
		map.put("course_parent_id", parent_id);
		List<EntryPlan> course_list = entryPlanService.getEntryQuChongPlanList(map);// 去重后的分类
		for (EntryPlan entryPlan : course_list) {
			map.clear();
			map.put("course_id", entryPlan.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			entryPlan.setCoursename(courseMenu.getCourse_name());
		}
		model.addAttribute("course_list", course_list);
		return "/educational/entryinfo/sub_course";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 获取职称评审三级分类
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月8日 下午3:49:29
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_review.jr")
	public String get_sub_review(Model model, @RequestParam(value = "course_id", required = false) Integer course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("is_show", "显示");
		map.put("review_id", 0);
		map.put("course_review_id", course_id);
		List<EntryPlan> course_list = entryPlanService.getEntryQuChongPlanList(map);// 去重后的分类
		for (EntryPlan entryPlan : course_list) {
			map.clear();
			map.put("review_id", entryPlan.getReview_id());
			Review review = reviewService.getReview(map);
			entryPlan.setReview(review);
		}
		model.addAttribute("review_list", course_list);
		return "/educational/entryinfo/sub_review";
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 修改个人简历
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月13日 上午9:14:38
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_entry_viat.jr")
	public void update_entry_viat(EntryViate entryViate, HttpServletResponse response) {
		if (entryViateService.updateEntryViate(entryViate)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EduEntryInfoController.java
	 * @Package com.jingren.jing.educational.controller.entryinfo
	 * @Description: TODO 修改家庭关系
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年6月13日 上午9:19:06
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_entry_family.jr")
	public void update_entry_family(EntryFamily entryFamily, HttpServletResponse response) {
		if (entryFamilyService.updateEntryFamily(entryFamily)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
}
