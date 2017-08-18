package com.jingren.jing.school.back.baoming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.baoming.Baoming;
import com.jingren.jing.school.service.baoming.BaomingService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: BackBaomingController.java 
* @Package com.jingren.jing.school.back.baoming 
* @Description: TODO 网校报名管理
* @author 鲁晓飞 MR.Lu   
* @date 2017年4月5日 下午5:30:57 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_baoming")
public class BackBaomingController {

	@Resource
	private BaomingService baomingService;
	
	/**
	* @Title: BackBaomingController.java 
	* @Package com.jingren.jing.school.back.baoming 
	* @Description: TODO 报名信息列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 下午5:33:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_baoming_list.jr")
	public String get_baoming_list(Model model,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<Baoming> baomings=baomingService.getBaomingList(map);
		Integer baomings_number=baomingService.getBaomingNumber(map);
		Pagers<Baoming> pagers = new Pagers<Baoming>(baomings_number,
				pageNumber, limit);
		pagers.setList(baomings);
		model.addAttribute("baomings", pagers);
		return "/baoming/baoming";
	}
	/**
	* @Title: BackBaomingController.java 
	* @Package com.jingren.jing.school.back.baoming 
	* @Description: TODO 查看报名详情
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 下午5:48:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_baoming.jr")
	public String to_check_baoming(Model model,
			@RequestParam(value = "baoming_id", required = false) Integer baoming_id){
		Map<String, Object> map=new HashMap<>();
		map.put("baoming_id", baoming_id);
		Baoming baoming=baomingService.getBaoming(map);
		model.addAttribute("baoming", baoming);
		return "/baoming/check_baoming";	
		}
	/**
	* @Title: BackBaomingController.java 
	* @Package com.jingren.jing.school.back.baoming 
	* @Description: TODO 删除报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 下午5:54:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_baoming.jr")
	public void delete_baoming(HttpServletResponse response,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		for (String string : array) {
			int id=Integer.valueOf(string);
			baomingService.deleteBaoming(id);
		}
		ResponseUtils.renderText(response, "1");
	}
}
