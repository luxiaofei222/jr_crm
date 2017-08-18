package com.jingren.jing.educational.controller.entryplan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.university.bean.ChengkaoSc;
import com.jingren.jing.common.university.bean.Review;
import com.jingren.jing.common.university.service.ChengkaoScService;
import com.jingren.jing.common.university.service.ReviewService;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.entrysystem.bean.entryplan.EntryPlan;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.entryplan.EntryPlanService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: EduEntryPlanController.java 
* @Package com.jingren.jing.educational.controller.entryplan 
* @Description: TODO 报考计划
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月27日 下午2:03:22 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("edu_entry_plan")
public class EduEntryPlanController {

	@Resource
	private EntryPlanService entryPlanService;
	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private ReviewService reviewService;
	@Resource
	private ChengkaoScService chengkaoScService;
	
	/**
	* @Title: EduEntryPlanController.java 
	* @Package com.jingren.jing.educational.controller.entryplan 
	* @Description: TODO 报考计划首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月27日 下午2:15:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_entry_plan_main.jr")
	public String get_entry_plan_main(Model model,
			@RequestParam(value = "parent_id", required = false) Integer parent_id,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "entryplan_content", required = false) String entryplan_content,
			@RequestParam(value = "entryplan_explain", required = false) String entryplan_explain,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(entryplan_content)){
			map.put("entryplan_content", entryplan_content);
			model.addAttribute("entryplan_content", entryplan_content);
		}
		if(parent_id!=null){
			map.put("parent_id", parent_id);
			model.addAttribute("parent_id", parent_id);
		}
		if(parent_id!=null){
			map.put("course_id", course_id);
			model.addAttribute("course_id", course_id);
		}
		if(StringUtils.isNotBlank(entryplan_explain)){
			map.put("entryplan_explain", entryplan_explain);
			model.addAttribute("entryplan_explain", entryplan_explain);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<EntryPlan> entryPlans=entryPlanService.getEntryPlanList(map);
		Integer entryPlans_number=entryPlanService.getEntryPlanNumber(map);
		for (EntryPlan entryPlan : entryPlans) {
			if(entryPlan.getValidity_time().getTime()>System.currentTimeMillis()){//截至日期到期
				entryPlan.setTime_type("未过期");
			}else{
				entryPlan.setTime_type("已过期");
			}
			map.clear();
			map.put("entryplanId", entryPlan.getEntryplan_id());
			Integer baomingnumber=entryInfoService.getEntryInfoNumber(map);
			entryPlan.setBaomingnumber(baomingnumber);
		}
		Pagers<EntryPlan> pagers = new Pagers<EntryPlan>(entryPlans_number,
				pageNumber, limit);
		pagers.setList(entryPlans);
		model.addAttribute("entryPlans", pagers);
		map.clear();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
		
		return "/educational/entryplan/plan";
	}
	/**
	* @Title: EduEntryPlanController.java 
	* @Package com.jingren.jing.educational.controller.entryplan 
	* @Description: TODO 获取添加计划窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月27日 下午2:57:00 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_entry_plan.jr")
	public String to_entry_plan(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());// 默认第一个课程二级分类
		List<CourseMenu> coursesubMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		map.clear();
		map.put("parent_id", 0);
		List<ChengkaoSc> chengkaoScs=chengkaoScService.getChengkaoScList(map);
		model.addAttribute("chengkaoScs", chengkaoScs);
		return "/educational/entryplan/add_entryplan";
	}
	/**
	* @Title: EduEntryPlanController.java 
	* @Package com.jingren.jing.educational.controller.entryplan 
	* @Description: TODO 保存报考计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月27日 下午3:02:08 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_entry_plan.jr")
	public void save_entry_plan(EntryPlan entryPlan,HttpServletResponse response,
			@RequestParam(value="jiezhiriqi",required=false)String jiezhiriqi){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(jiezhiriqi);
			entryPlan.setEntryplan_time(new Date());
			long currentTime = date.getTime();
			currentTime +=24*60*60*1000-1000;//加23小时59分59秒
			Date date2=new Date(currentTime);
			entryPlan.setValidity_time(date2);
			if(entryPlanService.saveEntryPlan(entryPlan)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* @Title: EntryPlanController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entryplan 
	* @Description: TODO 设置计划是否隐藏
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月28日 下午2:55:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_ishow.jr")
	public void update_ishow(EntryPlan entryPlan,HttpServletResponse response
			){
		if(entryPlanService.updateEntryPlan(entryPlan)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: EduEntryPlanController.java 
	* @Package com.jingren.jing.educational.controller.entryplan 
	* @Description: TODO 修改弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月28日 下午3:20:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_plan.jr")
	public String to_update_plan(Model model,
			@RequestParam(value="entryplan_id",required=false)Integer entryplan_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
		map.clear();
		map.put("entryplan_id", entryplan_id);
		EntryPlan entryPlan=entryPlanService.getEntryPlan(map);
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		  String dateString = formatter.format(entryPlan.getValidity_time());
		model.addAttribute("entryPlan", entryPlan);
		model.addAttribute("dateString", dateString);
		map.clear();
		map.put("course_parent_id", entryPlan.getParent_id());// 默认第一个课程二级分类
		List<CourseMenu> coursesubMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		if(entryPlan.getParent_id()==9){
			map.clear();
			map.put("course_id", entryPlan.getCourse_id());
			List<Review> reviews=reviewService.getReviewList(map);
			model.addAttribute("reviews", reviews);
		}
		//成考学校
		map.clear();
		map.put("parent_id", 0);
		List<ChengkaoSc> chengkaoScs=chengkaoScService.getChengkaoScList(map);
		model.addAttribute("chengkaoScs", chengkaoScs);
		return "/educational/entryplan/update_entryplan";
	}
	/**
	* @Title: EduEntryPlanController.java 
	* @Package com.jingren.jing.educational.controller.entryplan 
	* @Description: TODO 修改计划
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月28日 下午3:23:07 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/update_entry_plan.jr")
	public void update_entry_plan(EntryPlan entryPlan,HttpServletResponse response,
			@RequestParam(value="jiezhiriqi",required=false)String jiezhiriqi) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(jiezhiriqi);
		long currentTime = date.getTime();
		currentTime +=24*60*60*1000-1000;//加23小时59分59秒
		Date date2=new Date(currentTime);
		entryPlan.setValidity_time(date2);
		if(entryPlanService.updateEntryPlan(entryPlan)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: EduEntryPlanController.java 
	* @Package com.jingren.jing.educational.controller.entryplan 
	* @Description: TODO 职称评审三级
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月8日 下午2:25:50 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_review_list.jr")
	public String get_review_list(Model model,
			@RequestParam(value="course_id",required=false)Integer course_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_id", course_id);
		List<Review> reviews=reviewService.getReviewList(map);
		model.addAttribute("reviews", reviews);
		return "/educational/entryplan/sub_course";
	}
	/**
	* @Title: EduEntryPlanController.java 
	* @Package com.jingren.jing.educational.controller.entryplan 
	* @Description: TODO 获取二级分类
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月13日 下午6:17:04 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_course.jr")
	public String get_sub_course(Model model,
			@RequestParam(value = "course_id", required = false) Integer course_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_parent_id", course_id);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		return "/educational/common/sub_course";
	}
}
