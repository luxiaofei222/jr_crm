package com.jingren.jing.school.front.retrieval;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.course.CourseMenu;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.dictionary.Dictionary;
import com.jingren.jing.school.service.comment.VideoCommentService;
import com.jingren.jing.school.service.course.CourseMenuService;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.dictionary.DictionaryService;
import com.jingren.jing.util.Pagers;

/**
 * 检索页
* @Title: RetrievalController.java 
* @Package com.jingren.jing.school.front.retrieval 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月15日 下午3:38:45 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("retrieval")
public class RetrievalController {
	@Resource
	private CourseMenuService courseMenuService;
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private CourseVideoService courseVideoService;
	@Resource
	private VideoCommentService videoCommentService;

	/**
	 * 检索页默认显示内容
	* @Title: RetrievalController.java 
	* @Package com.jingren.jing.school.front.retrieval 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月15日 下午5:04:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_retrieval_main.html")
	public Callable<String> get_retrieval_main(final Model model,
			@RequestParam(value="course_id",required=false) final Integer course_id,
			@RequestParam(value = "limit", required = false,defaultValue="10") final Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1") final Integer pageNumber){
		return new Callable<String>() {

			@Override
			public String call() throws Exception {
				if(course_id!=null){
					Map<String, Object> map=new HashMap<>();
					map.put("no_parent", "zicaidan");
					map.put("course_id", course_id);
					List<Dictionary> dictionaries=dictionaryService.getDictionaryList(map);
					CourseMenu courseMenu=courseMenuService.getCouerseMenu(map);
					//课程视频列表
					map.put("pageNumber", (pageNumber - 1) * limit);
					map.put("limit", limit);
					map.put("video_level", 1);
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
//							 //course_num +=course_num;
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
					/*
					 * 分割线
					 */
					map.clear();
					map.put("course_id", courseMenu.getCourse_parent_id());
					CourseMenu courseMenu_par=courseMenuService.getCouerseMenu(map);
					map.clear();
					map.put("is_show", "是");
					map.put("course_parent_id", 0);
					List<CourseMenu> courseMenus=courseMenuService.getCourserMenuList(map);
					map.clear();
					map.put("is_show", "是");
					map.put("course_parent_id", courseMenu.getCourse_parent_id());
					List<CourseMenu> courseMenus_sub=courseMenuService.getCourserMenuList(map);
					model.addAttribute("courseMenu_par", courseMenu_par);//一级课程
					model.addAttribute("courseMenu", courseMenu);//被选中的课程
					model.addAttribute("courseMenus", courseMenus);//待选课程
					model.addAttribute("courseMenus_sub", courseMenus_sub);//课程方向
					model.addAttribute("dictionaries", dictionaries);//课程等级
					map.clear();
					map.put("dictionary_parent_id", 1);//班型
					List<Dictionary> dictionaries_banxing=dictionaryService.getDictionaryList(map);
					model.addAttribute("dictionaries_banxing", dictionaries_banxing);
					return "/school/retrieval/retrieval";
				}else{
					return "/404";
				}
			}
		};
		
	}
	/**
	 * 点击获取列表式
	* @Title: RetrievalController.java 
	* @Package com.jingren.jing.school.front.retrieval 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月15日 下午6:57:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_liebiaoshi_list.html")
	public String get_liebiaoshi_list(Model model,
			@RequestParam(value="zuire",required=false)String zuire,
			@RequestParam(value="jiage_paixu",required=false)String jiage_paixu,
			@RequestParam(value="type",required=false)String type,
			@RequestParam(value="banxing",required=false)String banxing,
			@RequestParam(value="dictionary_id",required=false)Integer dictionary_id,
			@RequestParam(value="course_id",required=false)Integer course_id,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		map.put("course_id", course_id);
		model.addAttribute("course_id", course_id);
		model.addAttribute("type", type);
		map.put("video_level", 1);
		model.addAttribute("show", "other");//判断翻页查询方式用的
		//课程视频列表
		if(pageNumber==null){
			pageNumber=1;
			limit=16;
		}
		if(StringUtils.isNotBlank(jiage_paixu)){
			model.addAttribute("jiage_paixu", jiage_paixu);
			if(jiage_paixu.equals("jiangxu")){
				
				map.put("jiage_paixu", "DESC");
			}else{
				map.put("jiage_paixu", "ASC");
			}
		}
		//最热排序
		if(StringUtils.isNotBlank(zuire)){
			model.addAttribute("zuire", zuire);
			if(zuire.equals("zuire")){
				map.put("zuire", "DESC");
			}
		}
		if(dictionary_id!=null){
			model.addAttribute("dictionary_id", dictionary_id);
			map.put("dictionary_id", dictionary_id);
		}
		if(StringUtils.isNotBlank(banxing)){
			model.addAttribute("banxing", banxing);
			map.put("banxing", banxing);
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
		if(type.equals("liebiao")){
			return "/school/retrieval/liebiaoshi";
		}else{
			return "/school/retrieval/biaogeshi";
		}
	}
	
	/**
	 * 检索页推荐课程
	* @Title: RetrievalController.java 
	* @Package com.jingren.jing.school.front.retrieval 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月15日 下午5:34:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/course_tuijian.html")
	public String course_tuijian(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("pageNumber", 0);
		map.put("limit", 5);
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
				 course_num+=courseVideoService.getCourseVideoNumber(map);
				 //course_num +=course_num;
			}
			courseVideo.setKeshi_number(course_num);
			map.clear();
			map.put("video_id", courseVideo.getVideo_id());
			Integer commentnum=videoCommentService.getCommentNumber(map);
			courseVideo.setComment_number(commentnum);
		}
		model.addAttribute("courseVideos", courseVideos);
		return "/school/retrieval/tuijian_retri";
	}
	/**
	 * 点击一级分类获取筛选条件
	* @Title: RetrievalController.java 
	* @Package com.jingren.jing.school.front.retrieval 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月16日 上午11:07:43 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_shaixuan.html")
	public String get_shaixuan(Model model,
			@RequestParam(value="course_par_id",required=false) Integer course_par_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_parent_id", course_par_id);
		List<CourseMenu> courseMenus_sub=courseMenuService.getCourserMenuList(map);//获取方向
		model.addAttribute("course_parent_id", course_par_id);
		model.addAttribute("courseMenus_sub", courseMenus_sub);//课程方向
		return "/school/retrieval/shaixuan";
	}
	/**
	 * 获取一级分类默认表格式
	* @Title: RetrievalController.java 
	* @Package com.jingren.jing.school.front.retrieval 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月16日 上午11:22:20 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_yijifenleishipin_list.html")
	public String get_yijifenleishipin_list(Model model,
			@RequestParam(value="type",required=false)String type,
			@RequestParam(value="course_parent_id",required=false)Integer course_parent_id,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		map.put("course_parent_id", course_parent_id);
		model.addAttribute("course_parent_id", course_parent_id);
		model.addAttribute("type", type);
		model.addAttribute("show", "yiji");
		//课程视频列表
		if(pageNumber==null){
			pageNumber=1;
			limit=16;
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("video_level", 1);
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
		if(type==null||type.equals("biaoge")){
			return "/school/retrieval/biaogeshi";	
		}else{
			return "/school/retrieval/liebiaoshi";
		}
	}
	/**
	 * 转换列表或者表格按钮
	* @Title: RetrievalController.java 
	* @Package com.jingren.jing.school.front.retrieval 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月16日 上午11:34:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("get_zhuanhuan_page.html")
	public String get_zhuanhuan_page(Model model,
			@RequestParam(value="dictionary_id",required=false) Integer dictionary_id,
			@RequestParam(value="course_id",required=false) Integer course_id,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="banxing",required=false) String banxing){
		model.addAttribute("type", type);
		model.addAttribute("course_id", course_id);
		if(dictionary_id!=null){
			model.addAttribute("dictionary_id", dictionary_id);
		}
		if(banxing!=null){
			model.addAttribute("banxing", banxing);
		}
		return "/school/retrieval/zhuanhuan_liebiao_biaoge";
	}
	/**
	 * 二级筛选条件
	* @Title: RetrievalController.java 
	* @Package com.jingren.jing.school.front.retrieval 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月16日 下午1:04:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_erji_shaixuan.html")
	public String get_erji_shaixuan(Model model,
			@RequestParam(value="course_id",required=false) Integer course_id){
		Map<String, Object> map=new HashMap<>();
		map.put("course_id", course_id);
		map.put("no_parent", "zicaidan");
		List<Dictionary> dictionaries=dictionaryService.getDictionaryList(map);
		if(dictionaries.size()>0){
			model.addAttribute("dictionaries", dictionaries);//课程等级
		}else{
			map.clear();
			map.put("course_id", 1001);
			List<Dictionary> dictionaries1=dictionaryService.getDictionaryList(map);	
			model.addAttribute("dictionaries", dictionaries1);
		}
		model.addAttribute("course_id", course_id);
		
		map.clear();
		map.put("dictionary_parent_id", 1);//班型
		List<Dictionary> dictionaries_banxing=dictionaryService.getDictionaryList(map);
		model.addAttribute("dictionaries_banxing", dictionaries_banxing);
		return "/school/retrieval/shaixuan_erji";
	}
}
