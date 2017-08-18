package com.jingren.jing.school.front.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.course.CourseInfo;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.service.comment.VideoCommentService;
import com.jingren.jing.school.service.course.CourseInfoService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.util.DesUtil;

/**
 * 课程分类
* @Title: CourseClassController.java 
* @Package com.jingren.jing.school.front.course 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月8日 上午9:28:04 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("sc_course_class")
public class CourseClassController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private VideoCommentService videoCommentService;
	@Resource
	private CourseInfoService courseInfoService;
	/**
	 * 获取课程列表
	* @Title: CourseClassController.java 
	* @Package com.jingren.jing.school.front.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 上午9:53:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_course_first_menu.html")
	public Callable<String> get_course_first_menu(final Model model,
			@RequestParam(value="course_parent_id",required=false) final Integer course_parent_id){
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, Object> map=new HashMap<>();
				map.put("course_parent_id", course_parent_id);
				map.put("is_show", "是");
				List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
				for (CourseMenu courseMenu : courseMenus) {
					map.clear();
					map.put("course_parent_id", courseMenu.getCourse_id());
					map.put("is_show", "是");
					List<CourseMenu> course_sub=courseMenuService.getCourserMenuList(map);
					courseMenu.setSub_list(course_sub);
				}
				model.addAttribute("courseMenus", courseMenus);
				return "/school/index/classify";
			}
		};
		
	}
	/**
	 * 获取左侧菜单列表
	* @Title: CourseClassController.java 
	* @Package com.jingren.jing.school.front.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午4:42:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/left_course_menu.html")
	public String left_course_menu(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("course_parent_id", 0);
		map.put("is_show", "是");
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);
		return "/school/index/left_menu/menu";
	}
	/**
	 * 首页课程内容
	* @Title: CourseClassController.java 
	* @Package com.jingren.jing.school.front.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午6:03:30 
	* @version 网校+CRM系统 V1.0
	 * @throws Exception 
	 */
	@RequestMapping("/get_course_content_menu.html")
	public Callable<String> get_course_content_menu(final Model model) throws Exception{
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, Object> map=new HashMap<>();
				map.put("course_parent_id", 0);
				map.put("is_show", "是");
				List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
				for (CourseMenu courseMenu : courseMenus) {
					map.clear();
					map.put("course_parent_id", courseMenu.getCourse_id());
					map.put("is_show", "是");
					List<CourseMenu> course_sub=courseMenuService.getCourserMenuList(map);
					courseMenu.setSub_info_name(course_sub.get(0).getCourse_name());
					courseMenu.setSub_list(course_sub);
					String course_id_sub=DesUtil.encrypt(String.valueOf(course_sub.get(0).getCourse_id()), "courseinfo");//des加密
					courseMenu.setSub_course_id(course_id_sub.replaceAll("\\+", "%2B"));
					//默认显示课程资讯
					map.clear();
					map.put("course_id", course_sub.get(0).getCourse_id());
					map.put("pageNumber", 0);
					map.put("limit", 11);
					List<CourseInfo> courseInfos=courseInfoService.getCourseInfoList(map);
					courseMenu.setCourseInfos(courseInfos);
					courseInfos=null;
					//获取默认显示的第一个课程内容
					map.clear();
					map.put("pageNumber", 0);
					map.put("limit", 6);
					map.put("moren", "下架");
					map.put("video_level", 1);
					map.put("course_id", course_sub.get(0).getCourse_id());//默认的展示内容
					List<CourseVideo> courseVideos=courseVideoService.getCourseVideoTuijianList(map);
					for (CourseVideo courseVideo : courseVideos) {
						map.clear();
						map.put("video_parent_id", courseVideo.getVideo_id());
						List<CourseVideo>courseVideos2=courseVideoService.getCourseVideoList(map);
						Integer course_num = 0;
						for (CourseVideo courseVideo2 : courseVideos2) {
							map.clear();
							map.put("video_parent_id", courseVideo2.getVideo_id());
							map.put("video_level", 3);
							 course_num +=courseVideoService.getCourseVideoNumber(map);
							 //course_num +=course_num;
						}
						courseVideo.setKeshi_number(course_num);
						map.clear();
						map.put("video_id", courseVideo.getVideo_id());
						Integer commentnum=videoCommentService.getCommentNumber(map);
						courseVideo.setComment_number(commentnum);
						courseVideos2=null;
					}
					course_sub.get(0).setCourseVideos(courseVideos);
					courseVideos=null;
					courseMenu.setCourseMenus_video(course_sub.get(0));
					course_sub=null;
				}
				//获取默认显示的第一个课程内容
				model.addAttribute("courseMenus", courseMenus);//所有课程分类-首页中部
				try {
					finalize();
				} catch (Throwable e) {
					e.printStackTrace();
				}
				return "/school/index/course/course_info";
			}
		};
	}
	/**
	 * 获取课程二级分类内容
	* @Title: CourseClassController.java 
	* @Package com.jingren.jing.school.front.course 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午7:38:59 
	* @version 网校+CRM系统 V1.0
	 * @throws Exception 
	 */
	@RequestMapping("/get_sub_course_video.html")
	public Callable<String> get_sub_course_video(final Model model,
			@RequestParam(value="course_id",required=false) final Integer course_id) throws Exception{
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, Object> map=new HashMap<>();	
				map.put("course_id", course_id);
				CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
				map.clear();
				map.put("course_id", course_id);
				map.put("pageNumber", 0);
				map.put("limit", 11);
				List<CourseInfo> courseInfos=courseInfoService.getCourseInfoList(map);
				courseMenu.setCourseInfos(courseInfos);//获取资讯内容
				map.clear();
				map.put("pageNumber", 0);
				map.put("limit", 6);
				map.put("moren", "下架");
				map.put("video_level", 1);
				map.put("course_id", course_id);//获取课程内容
				List<CourseVideo> courseVideos=courseVideoService.getCourseVideoTuijianList(map);
				for (CourseVideo courseVideo : courseVideos) {
					map.clear();
					map.put("video_parent_id", courseVideo.getVideo_id());
					List<CourseVideo>courseVideos2=courseVideoService.getCourseVideoList(map);
					Integer course_num = 0;
					for (CourseVideo courseVideo2 : courseVideos2) {
						map.clear();
						map.put("video_parent_id", courseVideo2.getVideo_id());
						map.put("video_level", 3);
						 course_num+=courseVideoService.getCourseVideoNumber(map);
						 //course_num +=course_num;
					}
					courseVideo.setKeshi_number(course_num);
					map.clear();
					map.put("video_id", courseVideo.getVideo_id());
					Integer commentnum=videoCommentService.getCommentNumber(map);
					courseVideo.setComment_number(commentnum);
				}
				String course_id_sub=DesUtil.encrypt(String.valueOf(course_id), "courseinfo");//des加密
				courseMenu.setSub_course_id(course_id_sub.replaceAll("\\+", "%2B"));
				courseMenu.setCourseVideos(courseVideos);
				model.addAttribute("cour_menu", courseMenu);
				return "/school/index/course/course_sub_info";
			}
		};
		
	}
	
}
