package com.jingren.jing.school.front.tuiguang;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.city.bean.City;
import com.jingren.jing.common.city.service.CityService;
import com.jingren.jing.common.yixiang.bean.Intent;
import com.jingren.jing.common.yixiang.service.IntentService;
import com.jingren.jing.school.bean.course.CourseInfo;
import com.jingren.jing.school.service.course.CourseInfoService;
import com.jingren.jing.util.GetIPUtil;
/**
* @Title: TuiGuangController.java 
* @Package com.jingren.jing.school.front.tuiguang 
* @Description: TODO 推广页
* @author 鲁晓飞 MR.Lu   
* @date 2017年1月19日 下午2:53:09 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("tuiguang")
public class TuiGuangController {

	@Resource
	private CourseInfoService courseInfoService;
	@Resource
	private IntentService intentService;
	@Resource
	private CityService cityService;
	
	/**
	* @Title: TuiGuangController.java 
	* @Package com.jingren.jing.school.front.tuiguang 
	* @Description: TODO 获取消防工程师推广页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月19日 下午3:26:53 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_firecontrol.html")
	public String  get_firecontrol(Model model) throws Exception{
		Map<String, Object> map=new HashMap<>();
		map.put("pageNumber", 0);
		map.put("limit", 3);
		map.put("course_id", 30);
		List<CourseInfo> late_update=courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("late_update", late_update);
		map.put("info_type", "考试试题");
		List<CourseInfo> exam_question=courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("exam_question", exam_question);
		map.put("limit", 7);
		map.put("info_type", "考试动态");
		List<CourseInfo> denamic_test=courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("denamic_test", denamic_test);
		map.clear();
		map.put("leveltype", 1);
		List<City> cities = cityService.getCityList(map);
		model.addAttribute("cities", cities);
		return "/tuiguang/xiaofang/xiaofang";
	}
	/**
	* @Title: TuiGuangController.java 
	* @Package com.jingren.jing.school.front.tuiguang 
	* @Description: TODO 保存消防意向用户
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月19日 下午3:27:55 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_yixiang_xiaofang.html")
	public void save_yixiang_xiaofang(Intent intent,
			HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="province",required=false)Integer province){
		Map<String, Object> map=new HashMap<>();
		map.put("id", province);
		City city=cityService.getCity(map);
		intent.setYixiang_time(new Date());
		intent.setYixiang_province(city.getName());
		intent.setCourse_id(30);//消防工程师
		intent.setYixiang_ip(GetIPUtil.getIpAddress(request));
		intentService.saveIntent(intent);
	}
	/**
	* @Title: TuiGuangController.java 
	* @Package com.jingren.jing.school.front.tuiguang 
	* @Description: TODO 人力资源管理推广页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月19日 下午4:30:02 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_humanresources.html")
	public String  get_humanresources(Model model) throws Exception{
		
		return "/tuiguang/renli/renli";
	}
	/**
	* @Title: TuiGuangController.java 
	* @Package com.jingren.jing.school.front.tuiguang 
	* @Description: TODO 心理咨询师推广页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月19日 下午4:35:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_psychology.html")
	public String  get_Psychology(Model model) throws Exception{
		
		return "/tuiguang/xinli/xinli";
	}
	/**
	* @Title: TuiGuangController.java 
	* @Package com.jingren.jing.school.front.tuiguang 
	* @Description: TODO 建造师推广页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月19日 下午4:40:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_construction_division.html")
	public String  get_construction_division(Model model) throws Exception{
		
		return "/tuiguang/jianzaoshi/jianzaoshi";
	}
	/**
	* @Title: TuiGuangController.java 
	* @Package com.jingren.jing.school.front.tuiguang 
	* @Description: TODO 学历提升推广页
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月23日 上午9:57:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_education_promotion.html")
	public String  get_education_promotion(Model model) throws Exception{
		Map<String, Object> map=new HashMap<>();
		map.put("pageNumber", 0);
		map.put("limit", 4);
		map.put("course_id", 19);
		List<CourseInfo> late_update=courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("late_update", late_update);
		map.put("info_type", "考试试题");
		List<CourseInfo> exam_question=courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("exam_question", exam_question);
		map.put("limit", 4);
		map.put("info_type", "考试动态");
		List<CourseInfo> denamic_test=courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("denamic_test", denamic_test);
		map.put("info_type", "考试试题");
		map.put("pageNumber", 1);
		map.put("limit", 5);
		List<CourseInfo> denamic_test2=courseInfoService.getCourseInfoList_erji(map);
		model.addAttribute("denamic_test2", denamic_test2);
		return "/tuiguang/xueli/xueli";
	}
	/**
	* @Title: TuiGuangController.java 
	* @Package com.jingren.jing.school.front.tuiguang 
	* @Description: TODO 活动页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月21日 下午4:44:13 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/jingren_activity.html")
	public String jingren_activity(){
		
		return "/tuiguang/huodong/activity";
	}
}
