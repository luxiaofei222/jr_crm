package com.jingren.jing.school.front.scnews;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.scnews.ScNews;
import com.jingren.jing.school.service.scnews.ScNewsService;

/**
 * 网校新闻
* @Title: ScNewsController.java 
* @Package com.jingren.jing.school.front.scnews 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月8日 下午1:19:21 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("sc_news")
public class ScNewsController {

	@Resource
	private ScNewsService scNewsService;
	
	/**
	 * 动态新闻列表首页
	* @Title: ScNewsController.java 
	* @Package com.jingren.jing.school.front.scnews 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 下午1:29:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_news_list.html")
	public String get_news_list(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("pageNumber", 0);
		map.put("limit", 10);
		List<ScNews> scNews=scNewsService.getNewsList(map);
		model.addAttribute("scNews", scNews);
		return "/school/index/news/news_list";
	}
	/**
	* @Title: ScNewsController.java 
	* @Package com.jingren.jing.school.front.scnews 
	* @Description: TODO 新闻动态三级页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午1:19:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_news_detail.html")
	public String get_news_detail(Model model,
			@RequestParam(value="news_id",required=false) Integer news_id){
		if(news_id!=null){
			Map<String, Object> map=new HashMap<>();
			map.put("news_id", news_id);
			ScNews news=scNewsService.getNews(map);
			model.addAttribute("news", news);
			return "/school/news/news_detail";
		}else{
			return "/404";
		}
	}
}
