package com.jingren.jing.school.back.clearance;

import java.text.ParseException;
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
/**
* @Title: BackClearanceController.java 
* @Package com.jingren.jing.school.back.clearance 
* @Description: TODO 通关方案
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月14日 下午2:20:12 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.clearance.Clearance;
import com.jingren.jing.school.bean.clearance.ClearanceVideo;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.service.clearance.ClearanceService;
import com.jingren.jing.school.service.clearance.ClearanceVideoService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("back_clearance")
public class BackClearanceController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private ClearanceService clearanceService;
	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private ClearanceVideoService clearanceVideoService;

	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 获取
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 下午2:28:06
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_claeraance.jr")
	public String get_claeraance(Model model,
			@RequestParam(value = "clearance_name", required = false) String clearance_name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(clearance_name)) {
			map.put("clearance_name", clearance_name);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<Clearance> clearances = clearanceService.getClearanceList(map);
		Integer clearance_number = clearanceService.getClearanceNumber(map);
		for (Clearance clearance : clearances) {
			map.clear();
			map.put("course_id", clearance.getCourse_id());
			CourseMenu courseMenu = courseMenuService.getCouerseMenu(map);
			clearance.setCourseMenu(courseMenu);
		}
		Pagers<Clearance> pagers = new Pagers<Clearance>(clearance_number, pageNumber, limit);
		pagers.setList(clearances);
		model.addAttribute("clearances", pagers);
		return "/claeraance/clearance_main";
	}

	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 添加通关方案弹窗
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 下午2:50:40
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_clearance.jr")
	public String to_add_clearance(Model model) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());// 默认第一个课程二级分类
		List<CourseMenu> coursesubMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		return "/claeraance/add_clearace";
	}

	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 保存通关方案
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 下午3:11:55
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/save_clearance.jr")
	public void save_clearance(HttpServletResponse response, Clearance clearance,
			@RequestParam(value = "daoqi_time_str", required = false) String daoqi_time_str) throws ParseException {
		clearance.setClearrance_time(new Date());
		if (StringUtils.isNotBlank(daoqi_time_str)) {
			clearance.setDaoqi_time(CommentDate.get_String_date_lingchen(daoqi_time_str));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("course_id", clearance.getCourse_id());
		//int clearances = clearanceService.getClearanceNumber(map);
		if (clearanceService.saveClearance(clearance)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 删除通关方案
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 下午3:19:07
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_clearance.jr")
	public void delete_clearance(HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str) {
		String[] array = str.split(",");
		for (String string : array) {
			int id = Integer.valueOf(string);
			clearanceService.deleteClearance(id);
		}
		ResponseUtils.renderText(response, "1");
	}

	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 修改通关方案的弹窗页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 下午3:24:01
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_clearance.jr")
	public String to_update_clearance(Model model,
			@RequestParam(value = "clearance_id", required = false) Integer clearance_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());// 默认第一个课程二级分类
		List<CourseMenu> coursesubMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		map.clear();
		map.put("clearance_id", clearance_id);
		Clearance clearance = clearanceService.getClearance(map);
		model.addAttribute("clearance", clearance);
		return "/claeraance/update_clearace";
	}

	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 修改通关方案
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 下午3:28:26
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/update_clearance.jr")
	public void update_clearance(HttpServletResponse response, Clearance clearance,
			@RequestParam(value = "daoqi_time_str", required = false) String daoqi_time_str) throws ParseException {
		if (StringUtils.isNotBlank(daoqi_time_str)) {
			clearance.setDaoqi_time(CommentDate.get_String_date_lingchen(daoqi_time_str));
		}
		if (clearanceService.updateClearance(clearance)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 查看通关方案
	 * @author 鲁晓飞 MR.Lu
	 * @date 2016年12月14日 下午3:53:33
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_clearance.jr")
	public String to_check_clearance(Model model,
			@RequestParam(value = "clearance_id", required = false) Integer clearance_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("clearance_id", clearance_id);
		Clearance clearance = clearanceService.getClearance(map);
		model.addAttribute("clearance", clearance);
		List<ClearanceVideo> clearanceVideos =clearanceVideoService.getClearanceVideoList(map);
		model.addAttribute("clearanceVideos", clearanceVideos);
		return "/claeraance/check_clearance";
	}

	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 添加课程页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月2日 下午5:04:13
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_course_video.jr")
	public String to_add_course_video(Model model,
			@RequestParam(value = "clearance_id", required = false) Integer clearance_id,
			@RequestParam(value = "course_id", required = false) Integer course_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus = courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		model.addAttribute("clearance_id", clearance_id);
		map.clear();
		map.put("course_id", course_id);
		map.put("video_level", 1);
		map.put("video_type", "付费");
		List<CourseVideo> courseVideos = courseVideoService.getCourseVideoList(map);
		if (courseVideos.size() > 0) {
			for (CourseVideo courseVideo : courseVideos) {// 判断课程是否已加入用户
				map.clear();
				map.put("clearance_id", clearance_id);
				List<ClearanceVideo> clearanceVideos = clearanceVideoService.getClearanceVideoList(map);
				for (ClearanceVideo clearanceVideo : clearanceVideos) {
					if (clearanceVideo.getVideo_id().equals(courseVideo.getVideo_id())) {
						courseVideo.setIs_jiaru_course(true);
						break;
					} else {
						courseVideo.setIs_jiaru_course(false);
					}
				}
			}
		}
		model.addAttribute("courseVideos", courseVideos);
		return "/claeraance/add_course_video";
	}


	/**
	 * @Title: BackClearanceController.java
	 * @Package com.jingren.jing.school.back.clearance
	 * @Description: TODO 保存套餐课程
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年8月2日 下午5:21:02
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_clearance_video.jr")
	public void save_clearance_video(ClearanceVideo clearanceVideo, HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str) {
		if (StringUtils.isNotBlank(str)) {
			String[] str_array = str.split(",");
			if (str_array.length > 0) {
				for (int i = 0; i < str_array.length; i++) {
					Map<String, Object> map = new HashMap<>();
					if (StringUtils.isNotBlank(str_array[i])) {
						Integer id = Integer.valueOf(str_array[i]);
						map.put("video_id", id);
						CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
						clearanceVideo.setVideo_id(id);
						clearanceVideo.setVideo_price(String.valueOf(courseVideo.getVideo_money_now()));
						clearanceVideo.setVideo_name(courseVideo.getVideo_name());
						if (clearanceVideoService.saveClearanceVideo(clearanceVideo)) {
							ResponseUtils.renderText(response, "1");// 成功
						} else {
							ResponseUtils.renderText(response, "0");
						}
					}
				}
			} else {
				ResponseUtils.renderText(response, "2");
			}
		}else{
			ResponseUtils.renderText(response, "2");
		}
	}
	/**
	* @Title: BackClearanceController.java 
	* @Package com.jingren.jing.school.back.clearance 
	* @Description: TODO 解除课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月2日 下午6:16:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_clearance_video.jr")
	public  void delete_clearance_video(HttpServletResponse response,
			@RequestParam(value = "clear_video_id", required = false) Integer clear_video_id){
		if(clearanceVideoService.deleteClearanceVideo(clear_video_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
