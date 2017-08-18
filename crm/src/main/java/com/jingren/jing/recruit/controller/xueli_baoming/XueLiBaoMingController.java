package com.jingren.jing.recruit.controller.xueli_baoming;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: XueLiBaoMingController.java 
* @Package com.jingren.jing.recruit.controller.xueli_baoming 
* @Description: TODO京人官网学历报名
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月30日 下午2:24:27 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.ipcity.IpCity;
import com.jingren.jing.recruit.bean.xueli_baoming.XueLiBaoMing;
import com.jingren.jing.recruit.service.chengkaoyuanxiao.CKZhaoShengService;
import com.jingren.jing.recruit.service.xueli_baoming.XueLiBaoMingService;
import com.jingren.jing.util.IPAreaUtil;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("xueli_baoming")
public class XueLiBaoMingController {

	@Resource
	private CKZhaoShengService ckZhaoShengService;
	@Resource
	private XueLiBaoMingService xueLiBaoMingService;
	
	/**
	* @Title: XueLiBaoMingController.java 
	* @Package com.jingren.jing.recruit.controller.xueli_baoming 
	* @Description: TODO 学历报名列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月30日 下午2:31:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_baoming_list.jr")
	public String get_baoming_list(Model model,
			@RequestParam(value = "yuanxiao", required = false) String yuanxiao,
			@RequestParam(value = "cnegci", required = false) String cnegci,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "user_name", required = false) String user_name,
			@RequestParam(value = "user_phone", required = false) String user_phone,
			@RequestParam(value = "limit", required = false, defaultValue = "20") Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(yuanxiao)){
			map.put("yuanxiao", yuanxiao);
			model.addAttribute("yuanxiao", yuanxiao);
		}
		if(StringUtils.isNotBlank(cnegci)){
			map.put("cnegci", cnegci);
			model.addAttribute("cnegci", cnegci);
		}
		if(StringUtils.isNotBlank(type)){
			map.put("type", type);
			model.addAttribute("type", type);
		}
		if(StringUtils.isNotBlank(user_name)){
			map.put("user_name", user_name);
			model.addAttribute("user_name", user_name);
		}
		if(StringUtils.isNotBlank(user_phone)){
			map.put("user_phone", user_phone);
			model.addAttribute("user_phone", user_phone);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);		
		Integer xueli_number=xueLiBaoMingService.getXueLiBaoMingNumber(map);
		List<XueLiBaoMing> xueLiBaoMings=xueLiBaoMingService.getXueLiBaoMingList(map);
		Pagers<XueLiBaoMing> pagers = new Pagers<XueLiBaoMing>(xueli_number, pageNumber, limit);
		pagers.setList(xueLiBaoMings);
		model.addAttribute("xueLiBaoMings", pagers);
		return "/recruit/xueli_baoming/xuelibaoming";
	}
	/**
	* @Title: XueLiBaoMingController.java 
	* @Package com.jingren.jing.recruit.controller.xueli_baoming 
	* @Description: TODO 删除报名信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月30日 下午2:44:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_baoming.jr")
	public void delete_baoming(HttpServletResponse response,
			@RequestParam(value = "str", required = false) String str){
		String[] array=str.split(",");
		Map<String, Object> map=new HashMap<>();
		for (String string : array) {
			int id=Integer.valueOf(string);
			map.clear();
			map.put("baoming_id", id);
			xueLiBaoMingService.deleteXueLiBaoMing(map);
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: XueLiBaoMingController.java 
	* @Package com.jingren.jing.recruit.controller.xueli_baoming 
	* @Description: TODO 查看学历报考信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月30日 下午5:13:06 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_baoming.jr")
	public String to_check_baoming(Model model,
			@RequestParam(value = "baoming_id", required = false) Integer baoming_id){
		Map<String, Object> map=new HashMap<>();
		map.put("baoming_id", baoming_id);
		XueLiBaoMing xueLiBaoMing=xueLiBaoMingService.getXueLiBaoMing(map);
		String ip_res=IPAreaUtil.request(xueLiBaoMing.getUser_ip());
		JSONObject json_test = JSONObject.fromObject(ip_res);
		IpCity ipCity=(IpCity) JSONObject.toBean((JSONObject) json_test.get("Base_info"), IpCity.class);
		xueLiBaoMing.setIp_city(ipCity.getCountry()+"."+ipCity.getProvince()+"."+ipCity.getCity()+"."+ipCity.getIsp());
		model.addAttribute("xueLiBaoMing", xueLiBaoMing);
		return "/recruit/xueli_baoming/check_xueli_baoming";
	}
}
