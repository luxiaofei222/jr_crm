package com.jingren.jing.school.front.course.video;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.bean.video.VideoRecord;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.video.VideoRecordService;
import com.jingren.jing.util.CallBackUtil;
import com.jingren.jing.util.ResponseUtils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("video_record")
public class VideoRecordController {

	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private VideoRecordService videoRecordService;
	
	/**
	* @Title: VideoRecordController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO 保存播放记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 上午10:34:39 
	* @version 网校+CRM系统 V1.0
	 * @throws EncoderException 
	 * @throws InputFormatException 
	 * @throws IOException 
	 */
	@RequestMapping("/save_video_record.html")
	public void save_video_record(HttpServletResponse response,HttpSession session,HttpServletRequest request,
			VideoRecord videoRecord,
			@RequestParam(value="type",required=false,defaultValue="0") Integer type,
			@RequestParam(value="user_id",required=false) Integer user_id) throws InputFormatException, EncoderException, IOException{
		User user=(User) session.getAttribute("user_session");
		Map<String, Object> map=new HashMap<>();
		if(user_id!=null){
			map.put("user_id", user_id);
		}else{
			map.put("user_id", user.getUser_id());
		}
		map.put("video_id", videoRecord.getVideo_id());
		List<VideoRecord> videoRecords=videoRecordService.getVideoRecordList(map);
		if(videoRecords.size()>0){//记录已经存在
			   if(type==1){//跨域使用-手机站
			        map.clear();  
			        map.put("record_id",videoRecords.get(0).getRecord_id());
			        CallBackUtil.get_callback(response, map, request);
	            }else{
	            	ResponseUtils.renderJson(response, "record_id",String.valueOf(videoRecords.get(0).getRecord_id()));
	            }
			//ResponseUtils.renderText(response, "3");
		}else{//记录不存在
			map.clear();
			map.put("video_id", videoRecord.getVideo_id());
			CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
			videoRecord.setPaly_time(new Date());
			if(user_id!=null){
				videoRecord.setUser_id(user_id);
			}else{
				videoRecord.setUser_id(user.getUser_id());
			}
			videoRecord.setVideo_parent_id(courseVideo.getVideo_parent_id());
			videoRecord.setCourse_id(courseVideo.getCourse_id());
			videoRecord.setRecord_time("0");
			map.clear();
			map.put("video_id", courseVideo.getVideo_parent_id());
			CourseVideo courseVideo_parent=courseVideoService.getCourseVideo(map);
			videoRecord.setVideo_parent(courseVideo_parent.getVideo_parent_id());
			String filePath=request.getServletContext().getRealPath("");
			 File source = new File(filePath+courseVideo.getVideo_file());
			Encoder encoder = new Encoder();
			MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration()/1000;
            videoRecord.setZong_shichang(String.valueOf(ls));
            if(type==1){//跨域使用-手机站
		        map.clear();  
		        if(videoRecordService.saveVideoRecord(videoRecord)){
    				map.put("record_id",videoRecord.getRecord_id());  
    			}else{
    				map.put("record_id",0);
    			}
		        CallBackUtil.get_callback(response, map, request);
            }else{
            	if(videoRecordService.saveVideoRecord(videoRecord)){
    				ResponseUtils.renderJson(response, "record_id",String.valueOf( videoRecord.getRecord_id()));
    			}else{
    				ResponseUtils.renderJson(response, "record_id",String.valueOf(0));
    			}
            }
			
		}
	}
	/**
	* @Title: VideoRecordController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO 记录观看时间
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 上午11:48:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/record_video_time.html")
	public void record_video_time(HttpServletResponse response,HttpSession session,
			VideoRecord videoRecord){
		Map<String, Object> map=new HashMap<>();
		map.put("record_id", videoRecord.getRecord_id());
		VideoRecord videoRecord2=videoRecordService.getVideoRecord(map);
		if(Integer.valueOf(videoRecord2.getRecord_time())<Integer.valueOf(videoRecord.getRecord_time())){
			if(Integer.valueOf(videoRecord.getRecord_time())<=Integer.valueOf(videoRecord2.getZong_shichang())){
				if(videoRecordService.updateVideoRecord(videoRecord)){
					ResponseUtils.renderText(response, "1");
				}else{
					ResponseUtils.renderText(response, "0");
				}
			}
		}
	}
	/**
	* @Title: VideoRecordController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO 获取播放时长
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月4日 下午6:09:34 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_video_time.html")
	public void get_video_time(HttpServletResponse response,HttpSession session,
			@RequestParam(value="video_id",required=false) Integer video_id){
		User user = (User) session.getAttribute("user_session");
		Map<String, Object> map=new HashMap<>();
		map.put("video_id", video_id);
		map.put("user_id", user.getUser_id());
		List<VideoRecord> videoRecords=videoRecordService.getVideoRecordList(map);
		if(videoRecords.size()>0){
			ResponseUtils.renderText(response, String.valueOf(videoRecords.get(0).getRecord_time()));
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: VideoRecordController.java 
	* @Package com.jingren.jing.school.front.course.video 
	* @Description: TODO 获取用户学习记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月5日 下午4:19:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_user_study_record.html")
	public String get_user_study_record(HttpSession session,Model model,
			@RequestParam(value="video_id",required=false) Integer video_id){
		User user = (User) session.getAttribute("user_session");
		Map<String, Object> map=new HashMap<>();
		map.put("video_parent", video_id);
		map.put("user_id", user.getUser_id());
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
		model.addAttribute("videoRecords", videoRecords);
		return "/school/person/video_record/video_record";
	}
}
