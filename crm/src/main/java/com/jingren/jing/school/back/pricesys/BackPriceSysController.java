package com.jingren.jing.school.back.pricesys;

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

import com.jingren.jing.school.bean.pricesys.PriceSys;
import com.jingren.jing.school.service.pricesys.PriceSysService;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("back_price")
public class BackPriceSysController {

	@Resource
	private PriceSysService priceSysService;
	/**
	* @Title: BackPriceSysController.java 
	* @Package com.jingren.jing.school.back.pricesys 
	* @Description: TODO 低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午4:09:13 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_price_sys.jr")
	public String  get_price_sys(Model model,
			@RequestParam(value="price_sys_title",required=false)String price_sys_title,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(price_sys_title)){
			map.put("price_sys_title", price_sys_title);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		List<PriceSys> priceSys=priceSysService.getPriceSysList(map);
		Integer priceSys_number=priceSysService.getPriceSysNumber(map);
		Pagers<PriceSys> pagers = new Pagers<PriceSys>(priceSys_number,
				pageNumber, limit);
		pagers.setList(priceSys);
		model.addAttribute("priceSys", pagers);
		return "/pricesys/price_sys";
	}
	/**
	* @Title: BackPriceSysController.java 
	* @Package com.jingren.jing.school.back.pricesys 
	* @Description: TODO 添加低价体系弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午4:27:29 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_price.jr")
	public String to_add_price(Model model){
		
		return "/pricesys/add_price";
	}
	/**
	* @Title: BackPriceSysController.java 
	* @Package com.jingren.jing.school.back.pricesys 
	* @Description: TODO 添加低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午4:28:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_price.jr")
	public void save_price(PriceSys priceSys,HttpServletResponse response){
		priceSys.setPrice_sys_time(new Date());
		if(priceSysService.addPriceSys(priceSys)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "2");
		}
	}
	/**
	* @Title: BackPriceSysController.java 
	* @Package com.jingren.jing.school.back.pricesys 
	* @Description: TODO 修改低价体系弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午4:35:04 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_update_price.jr")
	public String to_update_price(Model model,
			@RequestParam(value="price_sys_id",required=false)Integer price_sys_id){
		Map<String, Object> map=new HashMap<>();
			map.put("price_sys_id", price_sys_id);
			PriceSys priceSys=priceSysService.getPriceSys(map);
			model.addAttribute("priceSys", priceSys);
		return "/pricesys/update_price";
	}
	/**
	* @Title: BackPriceSysController.java 
	* @Package com.jingren.jing.school.back.pricesys 
	* @Description: TODO 修改低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午4:35:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_price.jr")
	public void update_price(PriceSys priceSys,HttpServletResponse response){
		if(priceSysService.updatePriceSys(priceSys)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "2");
		}
	}
	/**
	* @Title: BackPriceSysController.java 
	* @Package com.jingren.jing.school.back.pricesys 
	* @Description: TODO 删除低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午4:41:56 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_price.jr")
	public void delete_clearance(HttpServletResponse response,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		for (String string : array) {
			int id=Integer.valueOf(string);
			priceSysService.deletePriceSys(id);
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: BackPriceSysController.java 
	* @Package com.jingren.jing.school.back.pricesys 
	* @Description: TODO 查看低价体系
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午4:52:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_price.jr")
	public String to_check_price(Model model,
			@RequestParam(value="price_sys_id",required=false)Integer price_sys_id){
		Map<String, Object> map=new HashMap<>();
			map.put("price_sys_id", price_sys_id);
			PriceSys priceSys=priceSysService.getPriceSys(map);
			model.addAttribute("priceSys", priceSys);
		return "/pricesys/check_price";
	}
}
