package com.jingren.jing.school.entrysystem.controller.entrypalce;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.entrysystem.bean.entryplace.EntryPlace;
import com.jingren.jing.school.entrysystem.service.entryplace.EntryPlaceService;

@Controller
@RequestMapping("entry_place")
public class EntryPlaceController {

	@Resource
	private EntryPlaceService entryPlaceService;
	
	/**
	* @Title: EntryPlaceController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.entrypalce 
	* @Description: TODO 获取子类报名地点
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月26日 上午9:37:01 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_sub_entryplace.html")
	public String  get_sub_entryplace(Model model,
			@RequestParam(value="plcae_id",required=false) Integer plcae_id){
		Map<String, Object> map=new HashMap<>();
		map.put("parent_id", plcae_id);
		List<EntryPlace> entryPlaces=entryPlaceService.getEntryPlaceList(map);
		model.addAttribute("entryPlaces", entryPlaces);
		return "/course_sign/entryplace";
		
	}
}
