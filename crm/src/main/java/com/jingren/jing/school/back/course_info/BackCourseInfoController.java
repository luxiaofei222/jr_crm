package com.jingren.jing.school.back.course_info;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: BackCourseInfoController.java 
* @Package com.jingren.jing.school.back.course_info 
* @Description: TODO 课程资讯管理
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月9日 上午9:01:18 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.school.bean.course.CourseInfo;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.service.course.CourseInfoService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
@Controller
@RequestMapping("back_info")
public class BackCourseInfoController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private CourseInfoService courseInfoService;
	@Resource
	private DictionaryService dictionaryService;
	private static final String UP_FRONT_FILE ="images/school/front/courseinfo";
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 添加课程资讯页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午9:50:56 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_course_info.jr")
	public String to_add_course_info(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());//默认第一个课程二级分类
		List<CourseMenu> coursesubMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		map.clear();
		map.put("course_id", coursesubMenus.get(0).getCourse_id());
		map.put("no_parent", "hehe");
		List<Dictionary> dictionaries=dictionaryService.getDictionaryList(map);
		if(dictionaries.size()>0){
			model.addAttribute("dictionaries", dictionaries);//默认课程等级
		}else{
			map.clear();
			map.put("course_id", 1001);
			dictionaries=dictionaryService.getDictionaryList(map);
			model.addAttribute("dictionaries", dictionaries);//默认课程等级
		}
		return "/course_info/add_course_info";
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 本地上传
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午9:49:50 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/uploadpic.jr")
	public Map<String, Object> uploadpic(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "ajaxTaskFile", required = false) MultipartFile ajaxTaskFile){
		Map<String,Object> map=new HashMap<>();
		String path = UploadAddress.getUploadDate(ajaxTaskFile, request,
				UP_FRONT_FILE);
//		System.out.println(path);
		map.put("file_path", path);
		return map;
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 保存课程资讯
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午10:11:04 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_corse_info.jr")
	public void save_corse_info(CourseInfo courseInfo,HttpServletResponse response){
		courseInfo.setInfo_time(new Date());
		if(StringUtils.isBlank(courseInfo.getInfo_laiyuan())){
			courseInfo.setInfo_laiyuan("京人网校");
		}
		if(courseInfoService.saveCourseInfo(courseInfo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 课程咨询列表页
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午10:48:49 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_info_main.jr")
	public String get_info_main(Model model,
			@RequestParam(value = "parent_course_id", required = false) Integer parent_course_id,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "info_type", required = false) String info_type,
			@RequestParam(value = "info_title", required = false) String info_title,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(info_title)){
			map.put("info_title", info_title);
			model.addAttribute("info_title", info_title);
		}
		if(StringUtils.isNotBlank(info_type)){
			map.put("info_type", info_type);
			model.addAttribute("info_type", info_type);
		}
		if(parent_course_id!=null){
			map.put("parent_course_id", parent_course_id);
			model.addAttribute("parent_course_id", parent_course_id);
		}
		if(course_id!=null){
			map.put("course_id", course_id);
			model.addAttribute("course_id", course_id);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<CourseInfo> courseInfos=courseInfoService.getCourseInfoList(map);
		Integer info_number=courseInfoService.getCourseInfoNumber(map);
		Pagers<CourseInfo> pagers = new Pagers<CourseInfo>(info_number, pageNumber, limit);
		pagers.setList(courseInfos);
		model.addAttribute("courseInfos", pagers);
		map.clear();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);// 一级课程分类
		return "/course_info/course_info";
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 去修改页面推广信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午10:51:29 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_meta.jr")
	public String to_update_meta(Model model,
			@RequestParam(value="info_id",required=false)Integer info_id){
		Map<String, Object> map=new HashMap<>();
		map.put("info_id", info_id);
		CourseInfo courseInfo=courseInfoService.getCourseInfo(map);
		model.addAttribute("courseInfo", courseInfo);
		return "/course_info/update_meta";
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 修改页面推广信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午10:51:18 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_info_meta.jr")
	public void update_info_meta(CourseInfo courseInfo,HttpServletResponse response){
		String key=courseInfo.getMeta_key().replaceAll("，", ",");
		courseInfo.setMeta_key(key);
		if(courseInfoService.updateCourseInfo(courseInfo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 删除课程咨询
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 上午10:52:35 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_course_info.jr")
	public void delete_course_info(HttpServletResponse response,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		for (String string : array) {
			int id=Integer.valueOf(string);
			courseInfoService.deleteCourseInfo(id);
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 设置热点推荐
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午6:05:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_info_ishot.jr")
	public void update_info_ishot(CourseInfo courseInfo,HttpServletResponse response){
		if(courseInfoService.updateCourseInfo(courseInfo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 查看资讯详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月10日 下午6:32:48 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_course_info.jr")
	public String to_check_course_info(Model model,
			@RequestParam(value="info_id",required=false) Integer info_id){
		Map<String, Object> map=new HashMap<>();
		map.put("info_id", info_id);
		CourseInfo courseInfo=courseInfoService.getCourseInfo(map);
		model.addAttribute("courseInfo", courseInfo);
		return "/course_info/check_course_info";
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 修改课程资讯弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月11日 下午2:00:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_course_info.jr")
	public String to_update_course_info(Model model,
			@RequestParam(value="info_id",required=false) Integer info_id){
		Map<String, Object> map=new HashMap<>();
		map.put("info_id", info_id);
		CourseInfo courseInfo=courseInfoService.getCourseInfo(map);
		model.addAttribute("courseInfo", courseInfo);
		return "/course_info/update_course_info";
	}
	/**
	* @Title: BackCourseInfoController.java 
	* @Package com.jingren.jing.school.back.course_info 
	* @Description: TODO 修改课程资讯
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年1月11日 下午2:07:15 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_corse_info.jr")
	public void update_corse_info(CourseInfo courseInfo,HttpServletResponse response){
		if(courseInfoService.updateCourseInfo(courseInfo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
