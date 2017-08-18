package com.jingren.jing.school.back.course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.video.VideoRecord;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.video.VideoRecordService;
/**
* @Title: BackVideoRecordController.java 
* @Package com.jingren.jing.school.back.course 
* @Description: TODO 后台查看用户学习记录
* @author 鲁晓飞 MR.Lu   
* @date 2017年7月6日 上午9:30:14 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_video_record")
public class BackVideoRecordController {

	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private VideoRecordService videoRecordService;
	
	/**
	* @Title: BackVideoRecordController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 获取用户学习记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月6日 上午9:34:06 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_user_study_record.jr")
	private String get_user_study_record(Model model,
			@RequestParam(value="user_id",required=false) Integer user_id){
		Map<String, Object> map=new HashMap<>();
		map.put("dis_video_parent", "dis_video_parent");
		map.put("user_id", user_id);
		List<VideoRecord> videoRecords=videoRecordService.getVideoRecordList(map);
		if(videoRecords.size()>0){
			for (VideoRecord videoRecord : videoRecords) {
				map.clear();
				map.put("video_id", videoRecord.getVideo_parent());
				CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
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
				videoRecord.setCourseVideo(courseVideo);
			}
		}
		model.addAttribute("videoRecords", videoRecords);
		return "/user/check_study_record";
	}
	/**
	* @Title: BackVideoRecordController.java 
	* @Package com.jingren.jing.school.back.course 
	* @Description: TODO 获取列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月6日 下午3:59:25 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_record.jr")
	private String get_sub_record(Model model,
			@RequestParam(value="video_parent",required=false) Integer video_parent,
			@RequestParam(value="user_id",required=false) Integer user_id){
		Map<String, Object> map=new HashMap<>();
		map.put("video_parent", video_parent);
		map.put("user_id", user_id);
		List<VideoRecord> videoRecords=videoRecordService.getVideoRecordList(map);
		for (VideoRecord videoRecord : videoRecords) {
			map.clear();
			map.put("video_id", videoRecord.getVideo_id());
			CourseVideo courseVideore=courseVideoService.getCourseVideo(map);
			videoRecord.setCourseVideo(courseVideore);
			map.clear();
			map.put("video_id", videoRecord.getVideo_parent_id());
			CourseVideo courseVideopa=courseVideoService.getCourseVideo(map);
			videoRecord.setParent_video(courseVideopa);
		}
		model.addAttribute("subRecords", videoRecords);
		return "/user/check_study_sub_record";
	}
}
