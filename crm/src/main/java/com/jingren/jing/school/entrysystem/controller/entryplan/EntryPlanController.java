package com.jingren.jing.school.entrysystem.controller.entryplan;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.school.bean.course.CourseMenu;
/**
* @Title: EntryPlanController.java 
* @Package com.jingren.jing.school.entrysystem.controller.entryplan 
* @Description: TODO 报名计划
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月23日 下午1:17:24 
* @version 网校+CRM系统 V1.0
 */
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.DeleteFile;
@Controller
@RequestMapping("entry_plan")
public class EntryPlanController {
	@Resource
	private EntryPlanService entryPlanService;
	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private CourseMenuService courseMenuService;
	/**
	* @Title: EntryPlanController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryplan 
	* @Description: TODO 报名中心第一步-报名计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月23日 下午1:20:50 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_plan.html")
	public String get_entry_plan(Model model,HttpSession session,HttpServletRequest request){
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
		Map<String, Object> map=new HashMap<>();
		map.put("user_id", user.getUser_id());
		List<EntryInfo> entryInfos=entryInfoService.getEntryInfoList(map);
		for (EntryInfo entryInfo : entryInfos) {//如果有没有登记用户信息的 全部删除
			if(StringUtils.isBlank(entryInfo.getDocumentNumber())){
				map.clear();
				map.put("entryInfoId", entryInfo.getEntryInfoId());
				EntryInfo entryInfo2=entryInfoService.getEntryInfo(map);
				entryInfoService.deleteEntryInfo(entryInfo.getEntryInfoId());
				if(entryInfo2.getCertificatePic()!=null){
					DeleteFile.deleteFile1(entryInfo2.getCertificatePic(), request);
				}
				if(entryInfo2.getDocument_photo()!=null){
					DeleteFile.deleteFile1(entryInfo2.getDocument_photo(), request);
				}
				if(entryInfo2.getUserCardOpposite()!=null){
					DeleteFile.deleteFile1(entryInfo2.getUserCardOpposite(), request);
				}
				if(entryInfo2.getUserCardPositive()!=null){
					DeleteFile.deleteFile1(entryInfo2.getUserCardPositive(), request);
				}
				if(entryInfo2.getEntryUserPhoto()!=null){
					DeleteFile.deleteFile1(entryInfo2.getEntryUserPhoto(), request);
				}
			}
		}
		map.clear();
		map.put("course_id", "course_id");
		map.put("is_show", "显示");
		map.put("now", new Date());
		List<EntryPlan> course_list=entryPlanService.getEntryQuChongPlanList(map);//去重后的分类
		for (EntryPlan entryPlan : course_list) {
			map.clear();
			map.put("course_id", entryPlan.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			entryPlan.setCoursename(courseMenu.getCourse_name());
			map.put("is_show", "显示");
			map.put("now", new Date());
			List<EntryPlan> entryPlans=entryPlanService.getEntryPlanList(map);
			entryPlan.setEntryPlans(entryPlans);
		}
		
		model.addAttribute("entryPlans", course_list);
		return "/course_sign/entry_first";
		}else {
			session.removeAttribute("url");
			session.setAttribute("url", "/entry_plan/get_entry_plan.html");
			return "/school/login/login";
		}
	}
}
