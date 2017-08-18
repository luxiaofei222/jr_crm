package com.jingren.jing.school.front.search;

import java.io.IOException;
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

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.service.comment.VideoCommentService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.util.DesUtil;
import com.jingren.jing.util.Pagers;

/**
 * 课程搜索
* @Title: SearchController.java 
* @Package com.jingren.jing.school.front.search 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月18日 下午2:54:37 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("/sc_search")
public class SearchController {

	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private VideoCommentService videoCommentService;
	/**
	 * 搜索首页
	* @Title: SearchController.java 
	* @Package com.jingren.jing.school.front.search 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月18日 下午2:58:07 
	* @version 网校+CRM系统 V1.0
	 * @throws Exception 
	 * @throws IOException 
	 */
	@RequestMapping("/get_search_main.html")
	public String get_search_main(HttpServletResponse response,Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value="s_id",required=false)String s_id,
			@RequestParam(value="message",required=false)String message) throws IOException, Exception{
		if(StringUtils.isNotBlank(s_id)&&StringUtils.isNotBlank(message)){
			String course_video_name=DesUtil.decrypt(message, s_id);
			Map<String, Object> map=new HashMap<>();
			//课程视频列表
			if(pageNumber==null){
				pageNumber=1;
				limit=16;
			}
			map.put("video_parent_id", 0);
			map.put("video_name", course_video_name);
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
			map.put("moren", "下架");//已下架的不显示
			List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);
			Integer course_videonumber=courseVideoService.getCourseVideoNumber(map);
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
			Pagers<CourseVideo> pagers = new Pagers<CourseVideo>(course_videonumber,
					pageNumber, limit);
			pagers.setList(courseVideos);
			model.addAttribute("search_name", course_video_name);
			model.addAttribute("courseVideos", pagers);//视频内容
			return "/school/search/search";
		}else{
			return "/404";
		}
	}
	/**
	 * 获取所有课程内容
	* @Title: SearchController.java 
	* @Package com.jingren.jing.school.front.search 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月18日 下午6:10:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_all_video.html")
	public String get_all_video(HttpServletResponse response,Model model,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "limit", required = false) Integer limit) throws IOException, Exception{
			Map<String, Object> map=new HashMap<>();
			//课程视频列表
			map.put("video_parent_id", 0);
			if(pageNumber==null){
				pageNumber=1;
				limit=16;
			}
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
			map.put("moren", "下架");//已下架的不显示
			List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);
			Integer course_videonumber=courseVideoService.getCourseVideoNumber(map);
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
			Pagers<CourseVideo> pagers = new Pagers<CourseVideo>(course_videonumber,
					pageNumber, limit);
			pagers.setList(courseVideos);
			model.addAttribute("courseVideos", pagers);//视频内容
			return "/school/search/search";
	}
	/**
	 * 排序
	* @Title: SearchController.java 
	* @Package com.jingren.jing.school.front.search 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月18日 下午6:23:16 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_zonghe_search_video.html")
	public String get_zonghe_search_video(HttpServletResponse response,Model model,
			@RequestParam(value="paixu",required=false)String paixu,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "limit", required = false) Integer limit) throws IOException, Exception{
			Map<String, Object> map=new HashMap<>();
			//课程视频列表
			map.put("video_parent_id", 0);
			if(pageNumber==null){
				pageNumber=1;
				limit=16;
			}
			if(StringUtils.isNotBlank(name)){
				map.put("video_name", name);
				model.addAttribute("search_name", name);
			}
			if(StringUtils.isNotBlank(paixu)){
				if(paixu.equals("zuire")){
					map.put("zuire", paixu);
				}
				if(paixu.equals("DESC")||paixu.equals("ASC")){
					map.put("jiage_paixu", paixu);
				}
				model.addAttribute("paixu", paixu);
			}
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
			map.put("moren", "下架");//已下架的不显示
			List<CourseVideo> courseVideos=courseVideoService.getCourseVideoList(map);
			Integer course_videonumber=courseVideoService.getCourseVideoNumber(map);
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
			Pagers<CourseVideo> pagers = new Pagers<CourseVideo>(course_videonumber,
					pageNumber, limit);
			pagers.setList(courseVideos);
			model.addAttribute("courseVideos", pagers);//视频内容
			return "/school/search/search_list";
	}
}
