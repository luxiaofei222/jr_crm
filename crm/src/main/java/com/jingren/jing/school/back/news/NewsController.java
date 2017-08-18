package com.jingren.jing.school.back.news;

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
/**
* @Title: NewsController.java 
* @Package com.jingren.jing.school.back.news 
* @Description: TODO 新闻动态
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月7日 下午12:00:13 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.school.bean.scnews.ScNews;
import com.jingren.jing.school.service.scnews.ScNewsService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;
@Controller
@RequestMapping("back_news")
public class NewsController {

	@Resource
	private ScNewsService scNewsService;
	private static final String UP_FRONT_FILE ="images/school/front/news";
	/**
	* @Title: NewsController.java 
	* @Package com.jingren.jing.school.back.news 
	* @Description: TODO 新闻列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午1:05:56 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_news_main.jr")
	public String get_news_main(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<ScNews> scNews=scNewsService.getNewsList(map);
		Integer news_number=scNewsService.getNewsNumber(map);
		Pagers<ScNews> pagers = new Pagers<ScNews>(news_number, pageNumber, limit);
		pagers.setList(scNews);
		model.addAttribute("scNews", pagers);
		return "/news/news_dongtai";
	}
	/**
	* @Title: NewsController.java 
	* @Package com.jingren.jing.school.back.news 
	* @Description: TODO 添加新闻动态弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午1:31:13 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_news.jr")
	public String to_add_news(){
		
		return "/news/add_dongtai";
	}
	/**
	* @Title: NewsController.java 
	* @Package com.jingren.jing.school.back.news 
	* @Description: TODO 本地上传图片
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午1:46:49 
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
		System.out.println(path);
		map.put("file_path", path);
		return map;
	}
	/**
	* @Title: NewsController.java 
	* @Package com.jingren.jing.school.back.news 
	* @Description: TODO 保存新闻动态
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午2:00:53 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_news.jr")
	public void save_news(HttpServletResponse response,ScNews scNews){
		scNews.setNews_time(new Date());
		scNews.setNews_type("新闻动态");
		if(scNewsService.saveNews(scNews)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: NewsController.java 
	* @Package com.jingren.jing.school.back.news 
	* @Description: TODO 删除新闻动态
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午2:26:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_news.jr")
	public void delete_news(HttpServletResponse response,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		for (String string : array) {
			int id=Integer.valueOf(string);
			scNewsService.deleteNews(id);
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: NewsController.java 
	* @Package com.jingren.jing.school.back.news 
	* @Description: TODO 修改meta窗口
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午2:44:31 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_meta.jr")
	public String to_update_meta(Model model,
			@RequestParam(value="news_id",required=false)Integer news_id){
		Map<String, Object> map=new HashMap<>();
		map.put("news_id", news_id);
		ScNews news=scNewsService.getNews(map);
		model.addAttribute("news", news);
		return "/news/update_meta";
	}
	/**
	* @Title: NewsController.java 
	* @Package com.jingren.jing.school.back.news 
	* @Description: TODO 修改新闻meta
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月7日 下午2:51:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_news_meta.jr")
	public void update_news_meta(ScNews scNews,HttpServletResponse response){
		String key=scNews.getMeta_key().replaceAll("，", ",");
		scNews.setMeta_key(key);
		if(scNewsService.updateNews(scNews)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: NewsController.java 
	* @Package com.jingren.jing.school.back.news 
	* @Description: TODO 查看新闻动态详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月22日 下午3:45:42 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_news.jr")
	public String to_check_news(Model model,
			@RequestParam(value="news_id",required=false)Integer news_id){
		Map<String, Object> map=new HashMap<>();
		map.put("news_id", news_id);
		ScNews news=scNewsService.getNews(map);
		model.addAttribute("news", news);
		return "/news/check_news";
	}
}
