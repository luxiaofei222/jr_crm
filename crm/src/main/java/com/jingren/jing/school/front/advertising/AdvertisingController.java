package com.jingren.jing.school.front.advertising;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.school.bean.advertising.Advertising;
import com.jingren.jing.school.service.advertising.AdvertisingService;

/**
 * 广告信息
* @Title: AdvertisingController.java 
* @Package com.jingren.jing.school.front.advertising 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月8日 上午11:27:17 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("sc_ba")
public class AdvertisingController {
	@Resource
	private AdvertisingService advertisingService;

	/**
	 * 获取首页列表
	* @Title: AdvertisingController.java 
	* @Package com.jingren.jing.school.front.advertising 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 上午11:41:55 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_banner_list.html")
	public String get_banner_list(Model model){
		Map<String, Object> map=new HashMap<>();
		map.put("adver_type", "首页");
		map.put("is_show", "是");
		List<Advertising> getadvers=advertisingService.getAdvertisingList(map);
		model.addAttribute("getadvers", getadvers);
		return "/school/index/banner/banner_list";
	}
}
