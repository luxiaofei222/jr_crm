package com.jingren.jing.educational.controller.entrycondition;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: EduEntriConditionController.java 
* @Package com.jingren.jing.educational.controller.entrycondition 
* @Description: TODO 报考条件
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月28日 下午1:15:01 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.teacher.Teacher;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
import com.jingren.jing.school.entrysystem.service.entrycondition.EntryConditionService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("edu_condition")
public class EduEntriConditionController {

	@Resource
	private EntryConditionService entryConditionService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private UniversityService universityService;
	@Resource
	private EntryPlanService entryPlanService;
	@Resource
	private ChengkaoScService chengkaoScService;

	/**
	 * @Title: EduEntriConditionController.java
	 * @Package com.jingren.jing.educational.controller.entrycondition
	 * @Description: TODO 跳转添加条件弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月28日 下午1:33:55
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_condition.jr")
	public String to_add_condition(Model model,
			@RequestParam(value = "entryplan_id", required = false) Integer entryplan_id) {
		Map<String, Object> map = new HashMap<>();
//		map.put("course_leavl", 1);
//		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
//		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
//		map.clear();
//		map.put("course_parent_id", courseMenus.get(0).getCourse_id());// 默认第一个课程二级分类
//		List<CourseMenu> coursesubMenus = courseMenuService.getCourserMenuList(map);
//		model.addAttribute("coursesubMenus", coursesubMenus);
//		map.clear();
		map.clear();
		map.put("entryplan_id", entryplan_id);
		EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
		model.addAttribute("entryPlan", entryPlan);
		map.put("course_id", entryPlan.getCourse_id());
		map.put("no_parent", "hehe");
		List<Dictionary> dictionaries = dictionaryService.getDictionaryList(map);
		if (dictionaries.size() > 0) {
			model.addAttribute("dictionaries", dictionaries);// 默认课程等级
		} else {
			if(entryPlan.getParent_id()==9){
				map.clear();
				map.put("course_id", 1002);
				dictionaries = dictionaryService.getDictionaryList(map);
				model.addAttribute("dictionaries", dictionaries);// 默认课程等级
			}else{
				map.clear();
				map.put("course_id", 1001);
				dictionaries = dictionaryService.getDictionaryList(map);
				model.addAttribute("dictionaries", dictionaries);// 默认课程等级
			}
		}
		map.clear();
		map.put("parent_id", 0);
		List<University> universities = universityService.getUniversityList(map);
		model.addAttribute("universities", universities);
		// map.clear();
		// map.put("parent_id", universities.get(0).getUniversity_id());
		// map.put("university_type", "专升本");
		// List<University>
		// subuniversities=universityService.getUniversityList(map);
		// model.addAttribute("subuniversities", subuniversities);
		model.addAttribute("entryplan_id", entryplan_id);
		/****************************成考专业*******************************/
		if(entryPlan.getCourse_id()==19&&entryPlan.getUniversity_id()!=0){
			map.put("parent_id", entryPlan.getUniversity_id());
			map.put("cengci", "专升本");
			List<ChengkaoSc> chengkaoScs=chengkaoScService.getChengkaoScList(map);
			model.addAttribute("chengkaoScs", chengkaoScs);
		}
		return "/educational/entrycondition/add_condition";
	}

	/**
	* @Title: EduEntriConditionController.java 
	* @Package com.jingren.jing.educational.controller.entrycondition 
	* @Description: TODO 获取成考专业
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 下午4:29:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chengkao_zhuanye.jr")
	public String get_chengkao_zhuanye(Model model,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "cengci", required = false) String cengci) {
		Map<String, Object> map = new HashMap<>();
		if(university_id!=0){
			map.put("parent_id", university_id);
			map.put("cengci", cengci);
			List<ChengkaoSc> chengkaoScs=chengkaoScService.getChengkaoScList(map);
			model.addAttribute("chengkaoScs", chengkaoScs);
		}
		return "/educational/entrycondition/get_chengkao_zhuanye";
	}
	
	/**
	 * @Title: EduEntriConditionController.java
	 * @Package com.jingren.jing.educational.controller.entrycondition
	 * @Description: TODO 保存申报条件
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月28日 下午2:19:00
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_entry_condition.jr")
	public void save_entry_condition(EntryCondition entryCondition, HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str,
			@RequestParam(value = "str2", required = false) String str2) {
		entryCondition.setEntrycondition_time(new Date());
		Map<String, Object> map = new HashMap<>();
		// map.put("parent_id", 0);
		map.put("entryplan_id", entryCondition.getEntryplan_id());
		map.put("dictionary_id", entryCondition.getDictionary_id());
		List<EntryCondition> conditions = entryConditionService.getEntryConditionListByCourseParent(map);
		if (conditions.size() > 0) {
			String[] arraystr = str.split(",");
			String[] arraystr2 = str2.split(",");
			for (int i = 0; i < arraystr.length; i++) {
				EntryCondition entryCondition2 = new EntryCondition();
				if (entryCondition.getCourse_id() == 20) {
					entryCondition2.setUniversity_id(entryCondition.getUniversity_id());
					// entryCondition2.setZhuanye_id(entryCondition.getZhuanye_id());
				}
				if(entryCondition.getReview_id()!=null){
					entryCondition2.setReview_id(entryCondition.getReview_id());
				}
				entryCondition2.setBaokao_cengci(entryCondition.getBaokao_cengci());
				entryCondition2.setKaoshi_pici(entryCondition.getKaoshi_pici());
				entryCondition2.setXiangguang_info(arraystr2[i]);
				entryCondition2.setParent_id(conditions.get(0).getEntrycondition_id());
				entryCondition2.setEntrycondition_time(new Date());
				entryCondition2.setEntryplan_id(entryCondition.getEntryplan_id());
				entryCondition2.setDictionary_id(entryCondition.getDictionary_id());
				entryCondition2.setEntrycondition_content(arraystr[i]);
				entryCondition2.setCourse_id(entryCondition.getCourse_id());
				entryCondition2.setKaoshi_qici(entryCondition.getKaoshi_qici());
				entryCondition2.setCourse_parent_id(entryCondition.getCourse_parent_id());
				entryCondition2.setChengkao_kemu(entryCondition.getChengkao_kemu());
				entryCondition2.setChengkao_xuefei(entryCondition.getChengkao_xuefei());
				entryConditionService.saveEntryCondition(entryCondition2);
			}
			ResponseUtils.renderText(response, "1");
		} else {
			if (entryConditionService.saveEntryCondition(entryCondition)) {
				String[] arraystr = str.split(",");
				String[] arraystr2 = str2.split(",");
				for (int i = 0; i < arraystr.length; i++) {
					EntryCondition entryCondition2 = new EntryCondition();
					if (entryCondition.getCourse_id() == 20) {
						entryCondition2.setUniversity_id(entryCondition.getUniversity_id());
						// entryCondition2.setZhuanye_id(entryCondition.getZhuanye_id());
					}
					if(entryCondition.getReview_id()!=null){
						entryCondition2.setReview_id(entryCondition.getReview_id());
					}
					entryCondition2.setBaokao_cengci(entryCondition.getBaokao_cengci());
					entryCondition2.setKaoshi_pici(entryCondition.getKaoshi_pici());
					entryCondition2.setXiangguang_info(arraystr2[i]);
					entryCondition2.setParent_id(entryCondition.getEntrycondition_id());
					entryCondition2.setEntrycondition_time(new Date());
					entryCondition2.setEntryplan_id(entryCondition.getEntryplan_id());
					entryCondition2.setDictionary_id(entryCondition.getDictionary_id());
					entryCondition2.setEntrycondition_content(arraystr[i]);
					entryCondition2.setCourse_id(entryCondition.getCourse_id());
					entryCondition2.setKaoshi_qici(entryCondition.getKaoshi_qici());
					entryCondition2.setCourse_parent_id(entryCondition.getCourse_parent_id());
					entryCondition2.setChengkao_kemu(entryCondition.getChengkao_kemu());
					entryCondition2.setChengkao_xuefei(entryCondition.getChengkao_xuefei());
					entryConditionService.saveEntryCondition(entryCondition2);
				}
				ResponseUtils.renderText(response, "1");
			} else {
				ResponseUtils.renderText(response, "0");
			}
		}
	}

	/**
	 * @Title: EduEntriConditionController.java
	 * @Package com.jingren.jing.educational.controller.entrycondition
	 * @Description: TODO 查看申报条件
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月28日 下午5:13:14
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_condition.jr")
	public String to_check_condition(Model model,
			@RequestParam(value = "entryplan_id", required = false) Integer entryplan_id) {
		Map<String, Object> map = new HashMap<>();
		// map.put("parent_id", 0);
		map.put("entryplan_id", entryplan_id);
		List<EntryCondition> conditions = entryConditionService.getEntryConditionListByCourseParent(map);
		for (EntryCondition entryCondition : conditions) {
			map.clear();
			map.put("course_id", entryCondition.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			entryCondition.setCourseMenu(courseMenu);
			map.clear();
			map.put("course_id", entryCondition.getCourse_id());
			map.put("entryplan_id", entryplan_id);
			// map.put("parent_id", entryCondition.getEntrycondition_id());
			List<EntryCondition> quchongconditions=null;
			if (entryCondition.getCourse_id() == 20||entryCondition.getCourse_id() == 19) {
				 quchongconditions = entryConditionService.getEntryConditionListByCourseWangjiao(map);
			}else{
				 quchongconditions = entryConditionService.getEntryConditionListByQuchonghoutai(map);
			}
			if (quchongconditions.size() == 0) {
				map.clear();
				map.put("entryplan_id", entryplan_id);
				quchongconditions = entryConditionService.getEntryConditionListByDic(map);
			}
			if (entryCondition.getCourse_id() == 20) {
				for (EntryCondition entryCondition2 : quchongconditions) {
					map.clear();
					map.put("university_id", entryCondition2.getUniversity_id());
					University university=universityService.getUniversity(map);
					entryCondition2.setUniversity(university);
					map.put("parent_id", entryCondition2.getParent_id());
					map.put("baokao_cengci", entryCondition2.getBaokao_cengci());
					List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
					entryCondition2.setSub_conditionlist(subconditions);
					map.clear();
					map.put("entryplan_id", entryplan_id);
					EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
					if(entryPlan.getUniversity_id()!=0){
						map.clear();
						map.put("cengci",  entryCondition2.getBaokao_cengci());
						map.put("university_id", entryPlan.getUniversity_id());
						List<ChengkaoSc> chengkaoScs=chengkaoScService.getChengkaoScList(map);
						entryCondition2.setChengkaoScs(chengkaoScs);
					}
				}
			}else if(entryCondition.getCourse_id() == 19){
				for (EntryCondition entryCondition2 : quchongconditions) {
					map.clear();
					map.put("parent_id", entryCondition2.getParent_id());
					map.put("baokao_cengci", entryCondition2.getBaokao_cengci());
					List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
					entryCondition2.setSub_conditionlist(subconditions);
				}
			}else{
				for (EntryCondition entryCondition2 : quchongconditions) {
					if (entryCondition2.getCourse_id() != 20) {
						if (entryCondition2.getDictionary_id() != null) {
							map.clear();
							map.put("dictionary_id", entryCondition2.getDictionary_id());
							Dictionary dictionary = dictionaryService.getDictionary(map);
							entryCondition2.setDictionary(dictionary);
							map.clear();
							map.put("parent_id", entryCondition2.getParent_id());
							map.put("dictionary_id", entryCondition2.getDictionary_id());
							List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
							entryCondition2.setSub_conditionlist(subconditions);
						}else{
							map.clear();
							map.put("parent_id", entryCondition2.getParent_id());
							List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
							entryCondition2.setSub_conditionlist(subconditions);
						}
					} 
				}
			}
		
			entryCondition.setHoutaisublist(quchongconditions);
		}
		model.addAttribute("conditions", conditions);
		return "/educational/entrycondition/check_condition";
	}

	/**
	 * @Title: EduEntriConditionController.java
	 * @Package com.jingren.jing.educational.controller.entrycondition
	 * @Description: TODO 修改申报条件
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月29日 上午8:06:42
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_condition.jr")
	public void update_condition(EntryCondition entryCondition, HttpServletResponse response) {
		if (entryConditionService.updateEntryCondition(entryCondition)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: EduEntriConditionController.java
	 * @Package com.jingren.jing.educational.controller.entrycondition
	 * @Description: TODO 获取专业
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年1月16日 下午5:25:08
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhuanye.jr")
	public String get_zhuanye(Model model,
			@RequestParam(value = "university_id", required = false) Integer university_id,
			@RequestParam(value = "type_university", required = false) String type_university) {
		Map<String, Object> map = new HashMap<>();
		map.put("parent_id", university_id);
		map.put("university_type", type_university);
		List<University> subuniversities = universityService.getUniversityList(map);
		model.addAttribute("subuniversities", subuniversities);
		return "/educational/entrycondition/get_zhuanye";
	}
	

	/**
	 * @Title: EduEntriConditionController.java
	 * @Package com.jingren.jing.educational.controller.entrycondition
	 * @Description: TODO 获取等级选项
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年5月5日 下午12:00:51
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_dic_list.jr")
	public String get_dic_list(Model model, @RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "course_id", required = false) Integer course_id) {
		Map<String, Object> map = new HashMap<>();
		if (type == 1) {// 切换一级分类
			map.put("course_parent_id", course_id);// 获取二级分类
			List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
			map.clear();
			map.put("course_id", courseMenus.get(0).getCourse_id());
		} else {// 切换二级分类
			map.put("course_id", course_id);
		}
		map.put("no_parent", "hehe");
		List<Dictionary> dictionaries = dictionaryService.getDictionaryList(map);
		if (dictionaries.size() > 0) {
			model.addAttribute("dictionaries", dictionaries);// 默认课程等级
		} else {
			map.clear();
			map.put("course_id", 1001);
			dictionaries = dictionaryService.getDictionaryList(map);
			model.addAttribute("dictionaries", dictionaries);// 默认课程等级
		}
		return "/educational/common/dic_list";
	}
	/**
	* @Title: EduEntriConditionController.java 
	* @Package com.jingren.jing.educational.controller.entrycondition 
	* @Description: TODO 获取专业学费
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月6日 下午3:40:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhuanye_xuefei.jr")
	public String get_zhuanye_xuefei(Model model,
			@RequestParam(value = "university_id", required = false) Integer university_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("university_id", university_id);
		University university = universityService.getUniversity(map);
		model.addAttribute("university", university);
		return "/educational/common/get_zhuanye_xuefei";
	}
	/**
	* @Title: EduEntriConditionController.java 
	* @Package com.jingren.jing.educational.controller.entrycondition 
	* @Description: TODO 成考专业科类
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月13日 下午5:12:22 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhuanye_kelei.jr")
	public void get_zhuanye_kelei(HttpServletResponse response,
			@RequestParam(value = "university_id", required = false) Integer university_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("chengkao_id", university_id);
		ChengkaoSc university = chengkaoScService.getChengkaoSc(map);
		ResponseUtils.renderText(response, university.getKelei());
	}
	/**
	* @Title: EduEntriConditionController.java 
	* @Package com.jingren.jing.educational.controller.entrycondition 
	* @Description: TODO 获取成考学费
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月14日 上午10:49:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_chengkao_xuefei.jr")
	public String get_chengkao_xuefei(Model model,
			@RequestParam(value = "entrycondition_id", required = false) Integer entrycondition_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("entrycondition_id", entrycondition_id);
		EntryCondition entryCondition=entryConditionService.getEntryCondition(map);
		model.addAttribute("entryCondition", entryCondition);
		return "/educational/entrycondition/get_chengkao_xuefei";
	}
}
