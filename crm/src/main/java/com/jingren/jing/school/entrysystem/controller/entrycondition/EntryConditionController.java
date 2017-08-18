package com.jingren.jing.school.entrysystem.controller.entrycondition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.university.bean.University;
import com.jingren.jing.common.university.service.UniversityService;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.entrysystem.bean.entrycondition.EntryCondition;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.service.entrycondition.EntryConditionService;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: EntryConditionController.java 
* @Package com.jingren.jing.school.entrysystem.controller.entrycondition 
* @Description: TODO 报名条件
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月23日 下午7:08:26 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("condition")
public class EntryConditionController {

	@Resource
	private EntryConditionService entryConditionService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private UniversityService universityService;
	
	/**
	* @Title: EntryConditionController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entrycondition 
	* @Description: TODO 获取等级条件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月23日 下午7:29:05 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_dition.html")
	public  String get_sub_dition(Model model,
			@RequestParam(value="entrycondition_id",required=false)Integer entrycondition_id,
			@RequestParam(value="course_id",required=false)Integer course_id){
		Map<String, Object> map=new HashMap<>();
		map.put("entryplan_id", entrycondition_id);
		map.put("course_id", course_id);
		//map.put("parent_id", entryCondition.getEntrycondition_id());
		List<EntryCondition> quchongconditions=null;
		if (course_id == 20||course_id == 19) {
			 quchongconditions = entryConditionService.getEntryConditionListByCourseWangjiao(map);
		}else{
			 quchongconditions = entryConditionService.getEntryConditionListByQuchonghoutai(map);
		}
		if (quchongconditions.size() == 0) {
			map.clear();
			map.put("entryplan_id", entrycondition_id);
			quchongconditions = entryConditionService.getEntryConditionListByDic(map);
		}
		if (course_id == 20) {
			for (EntryCondition entryCondition2 : quchongconditions) {
				map.clear();
				map.put("university_id", entryCondition2.getUniversity_id());
				University university=universityService.getUniversity(map);
				entryCondition2.setUniversity(university);
				map.put("parent_id", entryCondition2.getParent_id());
				map.put("baokao_cengci", entryCondition2.getBaokao_cengci());
				List<EntryCondition> subconditions = entryConditionService.getEntryConditionList(map);
				entryCondition2.setSub_conditionlist(subconditions);
			}
		}else if(course_id== 19){
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
		model.addAttribute("quchongconditions", quchongconditions);
		return "/course_sign/sub_condition";
	}
	
	/**
	* @Title: EntryInfoController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryinfo 
	* @Description: TODO 检测是否报名过该条件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月26日 下午5:11:36 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_entry_dition.html")
	public void check_entry_dition(HttpSession session,HttpServletResponse response,
			@RequestParam(value="entrycondition_id",required=false)Integer entrycondition_id){
		User user = (User) session.getAttribute("user_session");
		Map<String, Object> map=new HashMap<>();
		map.put("userId", user.getUser_id());
		map.put("entrycondition_id", entrycondition_id);
		EntryInfo entryInfo=entryInfoService.getEntryInfo(map);
		if(entryInfo!=null){
			if(entryInfo.getDocumentNumber()!=null){
				ResponseUtils.renderText(response, "0");//不可用
			}else{
				ResponseUtils.renderText(response, "1");//可用
			}
		}else{
			ResponseUtils.renderText(response, "1");//可用
		}
	}
}
