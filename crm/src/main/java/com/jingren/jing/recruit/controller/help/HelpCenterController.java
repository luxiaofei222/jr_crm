package com.jingren.jing.recruit.controller.help;

import java.util.Date;
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

import com.jingren.jing.recruit.bean.chengkaoyuanxiao.CKZhaoSheng;
import com.jingren.jing.recruit.bean.help.HelpCenter;
import com.jingren.jing.recruit.service.chengkaoyuanxiao.CKZhaoShengService;
import com.jingren.jing.recruit.service.help.HelpCenterService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("help_center")
public class HelpCenterController {

	@Resource
	private CKZhaoShengService ckZhaoShengService;
	@Resource
	private HelpCenterService helpCenterService;
	
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 添加院校常见问题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午3:28:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_help_yuanxiao_info.jr")
	public String  to_add_help_info(Model model,
			@RequestParam(value="zhaosheng_id",required=false)Integer zhaosheng_id){
		Map<String, Object> map=new HashMap<>();
		map.put("zhaosheng_id", zhaosheng_id);
		CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
		model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		return "/recruit/help/add_yuanxiao_changjian";
	}
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 添加院校常见问题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午3:46:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_yuanxiao_changjian.jr")
	public void save_yuanxiao_changjian(HelpCenter center,HttpServletResponse response){
		center.setHelp_time(new Date());
		if(helpCenterService.saveHelpCenter(center)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 编辑院校常见问题页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午4:02:42 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_help_yuanxiao_info.jr")
	public String to_update_help_yuanxiao_info(Model model,
			@RequestParam(value="help_id",required=false)Integer help_id){
		Map<String, Object> map=new HashMap<>();
		map.put("help_id", help_id);
		HelpCenter helpCenter=helpCenterService.getHelpCenter(map);
		model.addAttribute("helpCenter", helpCenter);
		if(helpCenter.getZhaosheng_id()!=null){
			map.clear();
			map.put("zhaosheng_id", helpCenter.getZhaosheng_id());
			CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
			model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		}
		return "/recruit/help/update_yuanxiao_changjian";
	}
	
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 修改院校常见问题
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月22日 下午4:10:55 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_yuanxiao_changjian.jr")
	public void update_yuanxiao_changjian(HelpCenter center,HttpServletResponse response){
		if(helpCenterService.updateHelpCenter(center)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 获取帮助信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 上午11:07:38 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_help_info_main.jr")
	public String get_help_info_main(Model model,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "help_type", required = false) String help_type,
			@RequestParam(value = "limit", required = false,defaultValue="20") Integer limit,
			@RequestParam(value = "pageNumber", required = false,defaultValue="1") Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(title)){//标题
			map.put("title", title);
			model.addAttribute("title", title);
		}
		if(StringUtils.isNotBlank(help_type)){//类别
			map.put("help_type", help_type);
			model.addAttribute("help_type", help_type);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		Integer info_number=helpCenterService.getHelpCenterNumber(map);
		List<HelpCenter> helpCenters=helpCenterService.getHelpCenterList(map);
		Pagers<HelpCenter> pagers = new Pagers<HelpCenter>(info_number, pageNumber, limit);
		pagers.setList(helpCenters);
		model.addAttribute("helpCenters", pagers);
		return "/recruit/help/help_info";
	}
	
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 添加帮助信息页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 下午2:10:42 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_help_info.jr")
	public String  to_add_help_info(Model model){
		
		
		return "/recruit/help/add_help_info";
	}
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 修改帮助信息页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 下午2:18:55 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_help_info.jr")
	public String to_update_help_info(Model model,
			@RequestParam(value="help_id",required=false)Integer help_id){
		Map<String, Object> map=new HashMap<>();
		map.put("help_id", help_id);
		HelpCenter helpCenter=helpCenterService.getHelpCenter(map);
		model.addAttribute("helpCenter", helpCenter);
		return "/recruit/help/update_help_info";
	}
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 删除帮助资讯
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月23日 下午2:23:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_help_info.jr")
	public void delete_help_info(HttpServletResponse response,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		Map<String, Object> map=new HashMap<>();
		for (String string : array) {
			int id=Integer.valueOf(string);
			map.clear();
			map.put("help_id", id);
			helpCenterService.deleteHelpCenter(map);
		}
		ResponseUtils.renderText(response, "1");
		
	}
	/**
	* @Title: HelpCenterController.java 
	* @Package com.jingren.jing.recruit.controller.help 
	* @Description: TODO 查看帮助信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月26日 上午9:56:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_help_info.jr")
	public String to_check_help_info(Model model,
			@RequestParam(value="help_id",required=false)Integer help_id){
		Map<String, Object> map=new HashMap<>();
		map.put("help_id", help_id);
		HelpCenter helpCenter=helpCenterService.getHelpCenter(map);
		model.addAttribute("helpCenter", helpCenter);
		if(helpCenter.getZhaosheng_id()!=null){
			map.clear();
			map.put("zhaosheng_id", helpCenter.getZhaosheng_id());
			CKZhaoSheng ckZhaoSheng=ckZhaoShengService.getCKZhaoSheng(map);
			model.addAttribute("ckZhaoSheng", ckZhaoSheng);
		}
		return "/recruit/help/check_help_info";
	}
}
