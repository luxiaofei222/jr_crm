package com.jingren.jing.common.api.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.common.api.bean.WebApi;
import com.jingren.jing.common.api.service.WebApiService;
import com.jingren.jing.util.ResponseUtils;
@Controller
@RequestMapping("web_api")
public class WebApiController {

	@Resource
	private WebApiService webApiService;
	
	@RequestMapping("/get_web_api_list.html")
	public String get_web_api_list(Model model){
		Map<String, Object> map=new HashMap<>();
		List<WebApi> apis=webApiService.getWebApiList(map);
		model.addAttribute("apis", apis);
		return "/school/webapi/add_web_api";
	}
	@RequestMapping("/save_web_api.html")
	public void save_web_api(HttpServletResponse response,
			WebApi webApi){
		webApi.setApi_time(new Date());
		if(webApiService.saveWebApi(webApi)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	
	@RequestMapping("/get_web_api.html")
	public void get_web_api(HttpServletResponse response){
		Map<String, Object> map=new HashMap<>();
		List<WebApi> apis=webApiService.getWebApiList(map);
		ResponseUtils.renderJson(response, "info", apis.get(0).getApi_info());
	}
}
