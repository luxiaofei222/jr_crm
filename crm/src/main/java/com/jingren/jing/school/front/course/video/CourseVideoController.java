package com.jingren.jing.school.front.course.video;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.downerror.bean.DownError;
import com.jingren.jing.common.downerror.service.DownErrorService;
import com.jingren.jing.common.videokey.bean.VideoKey;
import com.jingren.jing.common.videokey.service.VideoKeyService;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.courseware.CourseWare;
import com.jingren.jing.school.bean.mycourse.MyCourse;
import com.jingren.jing.school.bean.myshoping.MyShoping;
import com.jingren.jing.school.bean.teacher.Teacher;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.comment.VideoCommentService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.courseware.CourseWareService;
import com.jingren.jing.school.service.mycourse.MyCourseService;
import com.jingren.jing.school.service.myshoping.MyShopingService;
import com.jingren.jing.school.service.teacher.TeacherService;
import com.jingren.jing.school.service.video.VideoRecordService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.ResponseUtils;
@Controller
@RequestMapping("sc_coursevideo")
public class CourseVideoController {

	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private VideoCommentService videoCommentService;
	@Resource
	private MyCourseService myCourseService;
	@Resource
	private MyShopingService myShopingService;
	@Resource
	private CourseWareService courseWareService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private VideoKeyService VideoKeyService;
	@Resource
	private DownErrorService downErrorService;
	@Resource
	private VideoRecordService videoRecordService;
	/**
	 * 推荐课程
	* @Title: CourseVideoController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月15日 下午5:32:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_recommend_course_video.html")
	public String get_recommend_course_video(Model model,
			@RequestParam(value="yangshi",required=false) Integer yangshi,
			@RequestParam(value="type",required=false) Integer type,
			@RequestParam(value="video_type",required=false) String video_type){
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotEmpty(video_type)){
			map.put("video_type", video_type);
		}else{
			map.put("moren", "下架");
		}
		map.put("pageNumber", 0);
		map.put("limit", 4);
		map.put("is_tuijian", "是");
		map.put("video_parent_id", 0);
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
		}
		model.addAttribute("courseVideos", courseVideos);
		model.addAttribute("yangshi", yangshi);
		if(type==0){
			return "/school/index/course/course_recommend";
		}else{
			return "/school/course_video/course_recommend_video";
		}
	
	}
	/**
	 * 视频三级页面
	* @Title: CourseVideoController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 上午9:38:35 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("get_course_video_player.html")
	public Callable<String> get_course_video_player(final Model model,final HttpSession session,
			@RequestParam(value="video_id",required=false) final Integer video_id,
			@RequestParam(value="bofang_id",required=false,defaultValue="0") final Integer bofang_id){
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				model.addAttribute("bofang_id", bofang_id);
				User user=(User) session.getAttribute("user_session");
				Map<String, Object> map=new HashMap<>();
				map.put("video_id", video_id);
				map.put("jibie", "ASC");
				List<CourseWare> courseWares=courseWareService.getCourseWareList(map);
				model.addAttribute("courseWares", courseWares);
				if(user!=null){
					map.put("user_id", user.getUser_id());
					MyCourse myCourse=myCourseService.getMyCourse(map);
					if(myCourse.getDaoqi_time()!=null){
						int tianshu=CommentDate.daysBetween(new Date(), myCourse.getDaoqi_time());
						myCourse.setTianshu(tianshu);
						if(tianshu<0){
							map.clear();
							map.put("my_course_id", myCourse.getMy_course_id());
							myCourseService.deleteMyCourse(map);
							get_course_video_player(model, session, video_id, bofang_id);//方法重写
						}
					}
					MyShoping myShoping=myShopingService.getMyShoping(map);
					model.addAttribute("myCourse", myCourse);
					model.addAttribute("myShoping", myShoping);
				}
				if(video_id!=null){
					CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
					if(courseVideo!=null){
						model.addAttribute("is_shoufei", courseVideo.getVideo_type());
						map.put("video_parent_id", video_id);
						map.put("jibie", "ASC");
						List<CourseVideo>courseVideos2=courseVideoService.getCourseVideoList(map);
						Integer course_num = 0;
						for (CourseVideo courseVideo2 : courseVideos2) {
							map.clear();
							map.put("video_parent_id", courseVideo2.getVideo_id());
							map.put("video_level", 3);
							 course_num +=courseVideoService.getCourseVideoNumber(map);
//							 course_num +=course_num;
						}
						courseVideo.setKeshi_number(course_num);//课时数
						//对id进行加密
						String des_video_id=DesUtil.encrypt(String.valueOf(courseVideo.getVideo_id()), "lu_course_pay");
						model.addAttribute("des_video_id", des_video_id.replaceAll("\\+", "%2B"));
						
						model.addAttribute("courseVideo", courseVideo);
						model.addAttribute("course_num", course_num);
						if(course_num>0){
							//添加播放次数
							CourseVideo courseVideo_up =new CourseVideo();
							courseVideo_up.setVideo_id(video_id);
							courseVideo_up.setPlay_times(courseVideo.getPlay_times()+1);
							courseVideoService.updateCourseVideo(courseVideo_up);
						}
						map.clear();
						map.put("video_parent_id", video_id);
						map.put("jibie", "ASC");
						List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);//二级章节
						for (CourseVideo courseVideo2 : courseVideos) {
							map.clear();
							map.put("jibie", "ASC");
							map.put("video_parent_id", courseVideo2.getVideo_id());
							List<CourseVideo> courseVideos_sanji=courseVideoService.getCourseVideoList(map);//二级章节
							courseVideo2.setCourseVideos_sanji(courseVideos_sanji);
						}
						model.addAttribute("courseVideos", courseVideos);
						if(courseVideo.getTeacher_id()!=null){
							map.clear();
							map.put("teacher_id", courseVideo.getTeacher_id());
							Teacher teacher=teacherService.getTeacher(map);
							model.addAttribute("teacher", teacher);
						}
						session.removeAttribute("video_id_error");
						session.setAttribute("video_id_error", video_id);
						return "/school/course_video/course_video";
					}else{
						return "/404";
					}
				}else{
					return "/404";
				}
			
			}
		};
		
	}
	/**
	 * 获取三级视频
	* @Title: CourseVideoController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 上午10:32:00 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("get_sanji_video.html")
	public Callable<String>  get_sanji_video(final Model model,final HttpSession session,
			@RequestParam(value="type",required=false,defaultValue="0") final Integer type,
			@RequestParam(value="video_id",required=false) final Integer video_id){
		return new Callable<String>() {
			@Override
			public String call() throws Exception {
				Map<String, Object> map=new HashMap<>();
				map.put("video_parent_id", video_id);
				List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);//二级章节
				model.addAttribute("courseVideos", courseVideos);
				if(type==0){
					session.removeAttribute("video_id_error");
					session.setAttribute("video_id_error", video_id);
					return "/school/course_video/sanji_video";
				}else{
					return "/school/course_video/muluzhangjie";
				}
			}
		};
		
	}
	/**
	 * 获取三级视频第一个播放的视频
	* @Title: CourseVideoController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月22日 下午3:36:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sanji_diyige_player.html")
	public void get_sanji_diyige_player(VideoKey videoKey,HttpServletResponse response,HttpSession session,
			@RequestParam(value="video_id",required=false)Integer video_id){
		if(video_id!=null){
			//User user = (User) session.getAttribute("user_session");
			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String format1 = df.format(new Date());
			videoKey.setVideo_key(format1);
			videoKey.setVideo_time(new Date());
			VideoKeyService.saveVideoKey(videoKey);
			Map<String, Object> map=new HashMap<>();
			map.put("video_id", video_id);
			CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
			session.removeAttribute("video_id_error");
			session.setAttribute("video_id_error", video_id);
			ResponseUtils.renderText(response, courseVideo.getVideo_file()+"?key="+format1);
//			map.clear();
//			map.put("video_id", video_id);
//			map.put("user_id", user.getUser_id());
//			List<VideoRecord> videoRecords=videoRecordService.getVideoRecordList(map);
//			if(videoRecords.size()>0){
//				ResponseUtils.renderuserJson(response, "file", courseVideo.getVideo_file()+"?key="+format1, "long_time", String.valueOf(videoRecords.get(0).getRecord_time()));
//			}else{
//				ResponseUtils.renderuserJson(response, "file", courseVideo.getVideo_file()+"?key="+format1, "long_time", "0");
//			}
		}
	}
	/**
	* @Title: CourseVideoController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO 视频错误页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月22日 上午10:15:36 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_video_error.html")
	public String get_video_error(HttpSession session,DownError downError,HttpServletRequest request){
		Integer video_id=(Integer) session.getAttribute("video_id_error");
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			downError.setVideo_id(video_id);
			downError.setDown_type("PC");
			downError.setDown_time(new Date());
			downError.setUser_id(user.getUser_id());
			downError.setUser_ip(GetIPUtil.getIpAddress(request));
			downErrorService.saveDownError(downError);
			session.removeAttribute("video_id_error");
		}
		return "/error/donw_error";
	}
}
