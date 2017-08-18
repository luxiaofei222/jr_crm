package com.jingren.jing.school.back.course;

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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.common.files.FilesEnty;
import com.jingren.jing.common.upload.ProgressEntity;
import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.courseware.CourseWare;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.bean.teacher.Teacher;
import com.jingren.jing.school.service.comment.VideoCommentService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.courseware.CourseWareService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.school.service.mycourse.MyCourseService;
/**
* @Title: VideoController.java 
* @Package com.jingren.jing.school.back.course 
* @Description: TODO 课程视频
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月6日 上午9:36:58 
* @version 网校+CRM系统 V1.0
 */
import com.jingren.jing.school.service.teacher.TeacherService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.FilesListUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
@Controller
@RequestMapping("back_video")
public class VideoController {

	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private VideoCommentService videoCommentServise;
	@Resource
	private TeacherService teacherService;
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private CourseWareService courseWareService;
	@Resource
	private VideoCommentService videoCommentService;
	@Resource
	private MyCourseService myCourseService;
	private Log logger=LogFactory.getLog(getClass());
	private static final String uploadfilepath = "images/school/back/course/upload";
	private static final String UP_FRONT_FILE ="images/school/front/course_video/upload";
	private static final String video ="upload_video/video";
	private static final String annexes ="annexes";//课件目录upload_video
	private static final String ready_video ="upload_video";//准备上传的视频
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 课程列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月6日 上午9:56:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_course_video_main.jr")
	public String get_course_video_main(Model model,
			@RequestParam(value = "video_name", required = false) String video_name,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(video_name)){
			map.put("video_name", video_name);
		}
		map.put("video_parent_id", 0);
		map.put("pageNumber", (pageNumber - 1)*limit);
		map.put("limit", limit);
		List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);
		Integer video_number=courseVideoService.getCourseVideoNumber(map);
		for (CourseVideo courseVideo : courseVideos) {//课程分类
			map.clear();
			map.put("course_id", courseVideo.getCourse_id());
			CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
			courseVideo.setCourseMenu(courseMenu);//课程分类
			map.clear();
			map.put("video_parent_id", courseVideo.getVideo_id());
			List<CourseVideo>courseVideos2=courseVideoService.getCourseVideoList(map);
			Integer course_num = 0;
			for (CourseVideo courseVideo2 : courseVideos2) {
				map.clear();
				map.put("video_parent_id", courseVideo2.getVideo_id());
				map.put("video_level", 3);
				course_num+=courseVideoService.getCourseVideoNumber(map);//课时
			}
			courseVideo.setKeshi_number(course_num);
			map.clear();
			map.put("teacher_id", courseVideo.getTeacher_id());
			Teacher teacher=teacherService.getTeacher(map);
			courseVideo.setTeacher(teacher);//授课老师
		}
		Pagers<CourseVideo> pagers = new Pagers<CourseVideo>(video_number, pageNumber, limit);
		pagers.setList(courseVideos);
		model.addAttribute("courseVideos", pagers);
		return "/course_video/video_main";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 添加课程页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月6日 上午10:49:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_course_video.jr")
	public String to_add_course_video(Model model){
		Map<String, Object> map = new HashMap<>();
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);//一级课程分类
		map.clear();
		map.put("course_parent_id", courseMenus.get(0).getCourse_id());//默认第一个课程二级分类
		List<CourseMenu> coursesubMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("coursesubMenus", coursesubMenus);
		map.clear();
		map.put("course_id", coursesubMenus.get(0).getCourse_id());
		List<Teacher> teachers=teacherService.getTeacherList(map);//默认老师
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
		if(teachers.size()>0){
			model.addAttribute("teachers", teachers);
		}else{
			map.clear();//如果该课程没有老师那么默认提供所有的老师，此功能可能会更改
			teachers=teacherService.getTeacherList(map);
			model.addAttribute("teachers", teachers);
		}
		return "/course_video/add_video";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO summernote 本地上传图片
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月6日 上午11:22:12 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/uploadpic.jr")
	public Map<String, Object> uploadpic(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "ajaxTaskFile", required = false) MultipartFile ajaxTaskFile){
		Map<String,Object> map=new HashMap<>();
		String path = UploadAddress.getUploadDate(ajaxTaskFile, request,
				uploadfilepath);
		System.out.println(path);
		map.put("file_path", path);
		return map;
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 切换一，二级分类的时候同时切换等级
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月6日 下午1:31:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_dic_list.jr")
	public String get_dic_list(Model model,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "course_id", required = false) Integer course_id,
			@RequestParam(value = "course_parent_id", required = false,defaultValue="1") Integer course_parent_id){
		Map<String, Object> map=new HashMap<>();
		if(type==1){//切换一级分类
			map.put("course_parent_id", course_id);//获取二级分类
			List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
			map.clear();
			map.put("course_id", courseMenus.get(0).getCourse_id());
		}else{//切换二级分类
			map.put("course_id", course_id);
		}
		List<Teacher> teachers=teacherService.getTeacherList(map);//默认老师
		map.put("no_parent", "hehe");
		List<Dictionary> dictionaries=dictionaryService.getDictionaryList(map);
		if(dictionaries.size()>0){
			model.addAttribute("dictionaries", dictionaries);//默认课程等级
		}else{
			if(course_parent_id==9){
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
		if(teachers.size()>0){
			model.addAttribute("teachers", teachers);
		}else{
			map.clear();//如果该课程没有老师那么默认提供所有的老师，此功能可能会更改
			teachers=teacherService.getTeacherList(map);
			model.addAttribute("teachers", teachers);
		}
		return "/course_video/dic_list";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 切换获取老师列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月21日 下午5:38:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_teacher_list.jr")
	public String get_teacher_list(Model model,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "course_id", required = false) Integer course_id){
		Map<String, Object> map=new HashMap<>();
		if(type==1){//切换一级分类
			map.put("course_parent_id", course_id);//获取二级分类
			List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
			map.clear();
			map.put("course_id", courseMenus.get(0).getCourse_id());
		}else{//切换二级分类
			map.put("course_id", course_id);
		}
		List<Teacher> teachers=teacherService.getTeacherList(map);//默认老师
		if(teachers.size()>0){
			model.addAttribute("teachers", teachers);
		}else{
			map.clear();//如果该课程没有老师那么默认提供所有的老师，此功能可能会更改
			teachers=teacherService.getTeacherList(map);
			model.addAttribute("teachers", teachers);
		}
		return "/course_video/teacher_list";
	}
	
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 保存课程信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月6日 下午1:54:32 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/save_course_video.jr")
	public void save_course_video(CourseVideo courseVideo,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "file_pic", required = false) MultipartFile file_pic,
			@RequestParam(value = "daoqi_time_str", required = false) String daoqi_time_str) throws ParseException{
		if(StringUtils.isNotBlank(daoqi_time_str)){
			courseVideo.setDaoqi_time(CommentDate.get_String_date_lingchen(daoqi_time_str));
		}
		
		if(file_pic!=null){
			String path = UploadAddress.getUploadDate(file_pic, request,
					UP_FRONT_FILE);
			courseVideo.setVideo_pic(path);
			courseVideo.setVideo_time(new Date());
			if(courseVideo.getVideo_money_now()>0){
				courseVideo.setVideo_type("付费");
			}else{
				courseVideo.setVideo_type("免费");
			}
			courseVideo.setVideo_type("下架");//默认是下架状态，只有点击发布后才能显示在网站
			courseVideo.setVideo_level(1);//课程等级 1级课程内容 2级章 3级节
			if(courseVideoService.saveCourseVideo(courseVideo)){
				ResponseUtils.renderText(response, "1");//添加成功
			}else{
				ResponseUtils.renderText(response, "0");//添加失败
			}
		}else{
			logger.info("用户上传的时候没有传封面图片，所以失败！");
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 获取课程章的列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午7:57:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zhang_list.jr")
	public String get_zhang_list(Model model,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		Map<String, Object> map=new HashMap<>();
		map.put("jibie", "ASC");
		map.put("video_parent_id", video_id);
		map.put("video_level", type);//课程等级 1级课程内容 2级章 3级节
		List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);
		model.addAttribute("courseVideos", courseVideos);
		if(type==2){//获取章的内容
			return "/course_video/zhang";
		}else {
			return "/course_video/jie";
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 添加章
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午9:24:32 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_zhang.jr")
	public String to_add_zhang(Model model,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		model.addAttribute("video_id", video_id);
		return "/course_video/add_zhang";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 去修改章节名称
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午9:56:56 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_zhang.jr")
	public String to_update_zhang(Model model,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);
		CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
		model.addAttribute("courseVideo", courseVideo);
		return "/course_video/update_zhang";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 修改节名称窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月8日 下午12:00:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_jie.jr")
	public String to_update_jie(Model model,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);
		CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
		model.addAttribute("courseVideo", courseVideo);
		return "/course_video/update_jie";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 添加章
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午9:43:19 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_zhang.jr")
	public void save_zhang(HttpServletResponse response,
			@RequestParam(value = "video_id", required = false) Integer video_id,
			@RequestParam(value = "video_name", required = false) String video_name){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);//一级课程
		CourseVideo courseVideo_main=courseVideoService.getCourseVideo(map);
		CourseVideo courseVideo=new CourseVideo();
		courseVideo.setCourse_id(courseVideo_main.getCourse_id());
		if(courseVideo_main.getVideo_money_now()>0){
			courseVideo.setVideo_type("付费");
		}else{
			courseVideo.setVideo_type("免费");
		}
		courseVideo.setVideo_time(new Date());
		courseVideo.setVideo_name(video_name);
		courseVideo.setVideo_parent_id(video_id);
		courseVideo.setBanxing(courseVideo_main.getBanxing());
		courseVideo.setCourse_parent_id(courseVideo_main.getCourse_parent_id());
		courseVideo.setVideo_level(2);
		courseVideo.setVideo_section(1);
		if(courseVideoService.saveCourseVideo(courseVideo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 是否推荐到首页
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午9:52:29 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/tuijian.jr")
	public void tuijian(HttpServletResponse response,CourseVideo courseVideo){
		if(courseVideoService.updateCourseVideo(courseVideo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 课程上下架
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 上午11:44:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/shangxiajia.jr")
	public void shangxiajia(HttpServletResponse response,CourseVideo courseVideo){
		if(courseVideo.getVideo_type().equals("上架")){
			Map<String, Object> map=new HashMap<>();
			map.put("video_id", courseVideo.getVideo_id());//一级课程
			CourseVideo courseVideo_main=courseVideoService.getCourseVideo(map);
			if(courseVideo_main.getVideo_money_now()>0){
				courseVideo.setVideo_type("付费");
			}else{
				courseVideo.setVideo_type("免费");
			}
		}
		if(courseVideoService.updateCourseVideo(courseVideo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 修改章节名称
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午9:59:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_zhang.jr")
	public void update_zhang(HttpServletResponse response,CourseVideo courseVideo){
		if(courseVideoService.updateCourseVideo(courseVideo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 弹出修改课程窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午11:18:34 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_video.jr")
	public String to_update_video(Model model,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);//一级课程
		CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
		model.addAttribute("courseVideo", courseVideo);
		map.clear();
		map.put("course_id", courseVideo.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		List<Teacher> teachers=teacherService.getTeacherList(map);
		model.addAttribute("teachers", teachers);
		model.addAttribute("courseMenu", courseMenu);//二级分类
		map.clear();
		map.put("course_id", courseVideo.getCourse_parent_id());
		CourseMenu courseMenumain=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenumain", courseMenumain);//一级分类
		map.clear();
		map.put("dictionary_id", courseVideo.getDictionary_id());
		Dictionary dictionary=dictionaryService.getDictionary(map);
		model.addAttribute("dictionary", dictionary);//等级
		map.clear();
		map.put("teacher_id", courseVideo.getTeacher_id());
		Teacher teacher=teacherService.getTeacher(map);
		model.addAttribute("teacher", teacher);//老师
		map.put("course_leavl", 1);
		List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
		model.addAttribute("courseMenus", courseMenus);//一级课程分类
		return "/course_video/update_video";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 修改课程分类
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午11:01:58 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/update_course_video.jr")
	public void update_course_video(CourseVideo courseVideo,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "file_pic", required = false) MultipartFile file_pic,
			@RequestParam(value = "daoqi_time_str", required = false) String daoqi_time_str) throws ParseException{
		if(StringUtils.isNotBlank(daoqi_time_str)){
			courseVideo.setDaoqi_time(CommentDate.get_String_date_lingchen(daoqi_time_str));
		}
		if(file_pic!=null){
			Map<String, Object> map=new HashMap<>();
			map.put("video_id", courseVideo.getVideo_id());//一级课程
			CourseVideo courseVideo_c=courseVideoService.getCourseVideo(map);
			String path = UploadAddress.getUploadDate(file_pic, request,
					UP_FRONT_FILE);
			courseVideo.setVideo_pic(path);
			if(DeleteFile.findfile(courseVideo_c.getVideo_pic(), request)){
				DeleteFile.deleteFile1(courseVideo_c.getVideo_pic(), request);
			}
		}
		if(courseVideo.getVideo_money_now()>0){
			courseVideo.setVideo_type("付费");
		}else{
			courseVideo.setVideo_type("免费");
		}
		if(courseVideoService.updateCourseVideo(courseVideo)){
			ResponseUtils.renderText(response, "1");//修改成功
		}else{
			ResponseUtils.renderText(response, "0");//修改失败
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 删除课程
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 上午11:23:53 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_video.jr")
	public void delete_video(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);
		CourseVideo courseVideo_san=courseVideoService.getCourseVideo(map);
		if(courseVideo_san.getVideo_level()==3){
			delete_shipin(courseVideo_san, request);
		}
		map.clear();
		map.put("video_parent_id", video_id);
		List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);
		if(courseVideos.size()>0){
			for (CourseVideo courseVideo : courseVideos) {
				map.clear();//三级内容
				map.put("video_parent_id", courseVideo.getVideo_id());
				List<CourseVideo> courseVideosjie=courseVideoService.getCourseVideoList(map);
				if(courseVideosjie.size()>0){
					for (CourseVideo courseVideo2 : courseVideosjie) {
						if(courseVideo2.getVideo_level()==3){
							delete_shipin(courseVideo2, request);
						}
						courseVideoService.deleteCourseVideo(courseVideo2.getVideo_id());
					}
				}
				if(courseVideo.getVideo_level()==3){
					delete_shipin(courseVideo, request);
				}
				courseVideoService.deleteCourseVideo(courseVideo.getVideo_id());//删完三级后再删二级
			}
		}
		if(courseVideoService.deleteCourseVideo(video_id)){
			map.clear();
			map.put("video_id", video_id);
			myCourseService.deleteMyCourse(map);
			ResponseUtils.renderText(response, "1");//删除成功
		}else{
			ResponseUtils.renderText(response, "0");//删除失败
		}
	}
	//删除视频
	public void delete_shipin(CourseVideo courseVideo,HttpServletRequest request){
		if(DeleteFile.findfile(courseVideo.getVideo_file(), request)){
			DeleteFile.deleteFile1(courseVideo.getVideo_file(), request);
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 上传视频弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午6:26:18 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_jie.jr")
	public String to_add_jie(Model model,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		model.addAttribute("video_id", video_id);
		return "/course_video/add_jie";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 上传视频
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月8日 上午10:35:00 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_video_jie.jr")
	public void save_video_jie(HttpSession session,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="video_name",required=false)String video_name,
			@RequestParam(value="video_id",required=false)Integer video_id,
			@RequestParam(value = "file_up", required = false) MultipartFile file_up){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id",video_id);
		CourseVideo courseVideo_par=courseVideoService.getCourseVideo(map);
		if(file_up!=null){
			CourseVideo courseVideo=new CourseVideo();
			courseVideo.setVideo_parent_id(video_id);
			String path=UploadAddress.getUploadDate(file_up, request, video);
			courseVideo.setVideo_file(path);//视频地址
			courseVideo.setCourse_id(courseVideo_par.getCourse_id());//课程ID
			courseVideo.setVideo_type(courseVideo_par.getVideo_type());//收付费
			courseVideo.setVideo_time(new Date());
			courseVideo.setVideo_name(video_name);//小节名称
			courseVideo.setBanxing(courseVideo_par.getBanxing());
			courseVideo.setCourse_parent_id(courseVideo_par.getCourse_parent_id());
			courseVideo.setVideo_level(3);
			courseVideo.setVideo_section(2);
			courseVideo.setIs_jinzhi(1);//禁止视频点击
			if(courseVideoService.saveCourseVideo(courseVideo)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
		
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 上传监听的进度
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月8日 上午10:19:02 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping(value = "/progress.jr", method = RequestMethod.POST )
	@ResponseBody
	public String initCreateInfo(HttpServletRequest request) {
		ProgressEntity status = (ProgressEntity) request.getSession().getAttribute("upload_ps");
		if(status==null){
			return "{}";
		}
		return status.toString();
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 课程详情页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月8日 下午4:14:21 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_video_detail.jr")
	public String  get_video_detail(Model model,
			@RequestParam(value="video_id",required=false)Integer video_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);
		
		List<CourseWare> courseWares=courseWareService.getCourseWareList(map);
		CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
		Integer commentnum=videoCommentService.getCommentNumber(map);
		courseVideo.setComment_number(commentnum);
		map.clear();
		map.put("video_parent_id", video_id);
		List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);
		Integer course_num = 0;
		if(courseVideos.size()>0){
			courseVideo.setCourseVideos_zhang(courseVideos);
			for (CourseVideo courseVideo2 : courseVideos) {
				map.clear();
				map.put("video_parent_id", courseVideo2.getVideo_id());
				List<CourseVideo> courseVideos2=courseVideoService.getCourseVideoList(map);
				map.put("video_level", 3);
				course_num+=courseVideoService.getCourseVideoNumber(map);
				if(courseVideos2.size()>0){
					courseVideo2.setCourseVideos_sanji(courseVideos2);
				}
			}
		}
		courseVideo.setKeshi_number(course_num);//课时数
		map.clear();
		map.put("course_id", courseVideo.getCourse_id());
		CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
		model.addAttribute("courseMenu", courseMenu);
		model.addAttribute("courseWares", courseWares);//课件
		map.clear();
		map.put("teacher_id", courseVideo.getTeacher_id());
		Teacher teacher=teacherService.getTeacher(map);
		courseVideo.setTeacher(teacher);
		model.addAttribute("courseVideo", courseVideo);//课程详情
		return "/course_video/check_video";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 添加课件弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月8日 下午4:15:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_course_ware.jr")
	public String to_add_course_ware(Model model,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		model.addAttribute("video_id", video_id);
		return "/course_video/add_ware";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 上传课件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月8日 下午4:27:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_video_ware.jr")
	public void save_video_ware(HttpSession session,HttpServletRequest request,HttpServletResponse response,
			CourseWare courseWare,
			@RequestParam(value = "file_up", required = false) MultipartFile file_up){
		if(file_up!=null){
			String path=UploadAddress.getUploadDate(file_up, request, annexes);
			courseWare.setCourseware_file(path);//课件路径
			courseWare.setCourseware_time(new Date());
			if(courseWareService.saveCourseWare(courseWare)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 查看课件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月8日 下午4:57:16 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_course_ware.jr")
	public String to_check_course_ware(Model model,
			@RequestParam(value = "video_id", required = false) Integer video_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);
		List<CourseWare> courseWares=courseWareService.getCourseWareList(map);
		model.addAttribute("courseWares", courseWares);
		return "/course_video/check_ware";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 删除课件
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月8日 下午5:14:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_course_ware.jr")
	public void delete_course_ware(HttpSession session,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "courseware_id", required = false) Integer courseware_id){
		Map<String, Object> map=new HashMap<>();
		map.put("courseware_id", courseware_id);
		CourseWare courseWare=courseWareService.getCourseWare(map);
		if(DeleteFile.findfile(courseWare.getCourseware_file(), request)){
			DeleteFile.deleteFile1(courseWare.getCourseware_file(), request);
		}
		if(courseWareService.deleteCourseWare(courseware_id)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 上传的视频列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 下午3:29:49 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_file_list.jr")
	public String  get_file_list(Model model,HttpServletRequest request,
			@RequestParam(value = "type", required = false) Integer type,
			@RequestParam(value = "video_id", required = false) Integer video_id	){
		//String filePath=request.getServletContext().getRealPath("/")+ready_video;
//		File file=new File("/home");
		String filePath=request.getServletContext().getRealPath("/")+ready_video;
		List<FilesEnty> filesEnties =FilesListUtil.get_file_list(filePath);
		model.addAttribute("files", filesEnties);
		model.addAttribute("video_id", video_id);
		model.addAttribute("type", type);//1修改 0添加
		return "/course_video/tianjia_video";
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 移动文件形式的上传视频
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 下午4:01:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_video_jie_yidong.jr")
	public void save_video_jie_yidong(HttpSession session,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="str",required=false)String str,
			@RequestParam(value="video_id",required=false)Integer video_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id",video_id);
		CourseVideo courseVideo_par=courseVideoService.getCourseVideo(map);
		if(StringUtils.isNotBlank(courseVideo_par.getVideo_file())){//修改视频
			String[] array=str.split(",");
			for (String string : array) {
				String path=FilesListUtil.yidongfile(request, ready_video, string, video);//获取移动后的地址
				CourseVideo courseVideo=new CourseVideo();
				courseVideo.setVideo_file(path);//视频地址
				courseVideo.setVideo_id(video_id);
				courseVideoService.updateCourseVideo(courseVideo);
				DeleteFile.deleteFile1(courseVideo_par.getVideo_file(), request);
			}
		}else{
			String[] array=str.split(",");
			for (String string : array) {
				String path=FilesListUtil.yidongfile(request, ready_video, string, video);//获取移动后的地址
				CourseVideo courseVideo=new CourseVideo();
				courseVideo.setVideo_file(path);//视频地址
				courseVideo.setVideo_parent_id(video_id);
				courseVideo.setCourse_id(courseVideo_par.getCourse_id());//课程ID
				courseVideo.setVideo_type(courseVideo_par.getVideo_type());//收付费
				courseVideo.setVideo_time(new Date());
				courseVideo.setVideo_name(FilesListUtil.huoqufilename(string));//小节名称
				courseVideo.setBanxing(courseVideo_par.getBanxing());
				courseVideo.setCourse_parent_id(courseVideo_par.getCourse_parent_id());
				courseVideo.setVideo_level(3);
				courseVideo.setVideo_section(2);
				courseVideo.setIs_jinzhi(1);
				courseVideoService.saveCourseVideo(courseVideo);
			}
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 向上
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月29日 上午10:53:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/xiangshang.jr")
	public void xiangshang(CourseVideo courseVideo,HttpServletResponse response){
		Map<String, Object> map=new HashMap<>();
		map.put("video_id",courseVideo.getVideo_id());
		CourseVideo courseVideo_par=courseVideoService.getCourseVideo(map);
		courseVideo.setTop_paixu(courseVideo_par.getTop_paixu()-1);
		if(courseVideoService.updateCourseVideo(courseVideo)){
			ResponseUtils.renderText(response, String.valueOf(courseVideo_par.getVideo_parent_id()));
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 给课件排序
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月19日 下午2:16:49 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/warexiangshang.jr")
	public void warexiangshang(CourseWare courseWare,HttpServletResponse response){
		Map<String, Object> map=new HashMap<>();
		map.put("courseware_id",courseWare.getCourseware_id());
		CourseWare courseWare_par=courseWareService.getCourseWare(map);
		courseWare_par.setTop_paixu(courseWare_par.getTop_paixu()-1);
		if(courseWareService.updateCourseWare(courseWare_par)){
			ResponseUtils.renderText(response,"1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: VideoController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 一键禁止点击 
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年2月28日 下午6:25:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/jinzhi_dianji.jr")
	public void jinzhi_dianji(CourseVideo courseVideo,HttpServletResponse response){
		if(courseVideoService.updateVideoDianji(courseVideo)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
