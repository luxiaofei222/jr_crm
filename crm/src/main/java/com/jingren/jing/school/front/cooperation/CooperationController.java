package com.jingren.jing.school.front.cooperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.school.bean.cooperation.Cooperation;
import com.jingren.jing.school.service.cooperation.CooperationService;

/**
 * 友情链接
* @Title: CooperationController.java 
* @Package com.jingren.jing.school.front.cooperation 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月8日 上午8:18:41 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("sc_cooper")
public class CooperationController {

	@Resource
	private CooperationService cooperationService;
	
	/**
	 * 获取首页友情链接列表
	* @Title: CooperationController.java 
	* @Package com.jingren.jing.school.front.cooperation 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月8日 上午8:22:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_cooer_list.html")
	public String get_cooer_list(Model model){
		Map<String, Object> map=new HashMap<>();
		List<Cooperation> cooperations=cooperationService.getCooperationList(map);
		model.addAttribute("cooperations", cooperations);
		return "/school/index/cooperation";
	}
}
