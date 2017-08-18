package com.jingren.jing.school.entrysystem.controller.repair;

import java.text.ParseException;
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

import com.jingren.jing.school.entrysystem.bean.entryinfo.EntryInfo;
import com.jingren.jing.school.entrysystem.bean.repair.RepairMoney;
import com.jingren.jing.school.entrysystem.service.entryinfo.EntryInfoService;
import com.jingren.jing.school.entrysystem.service.repair.RepairMoneyService;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.ResponseUtils;
/**
* @Title: RepairMoneyController.java 
* @Package com.jingren.jing.school.entrysystem.controller.repair 
* @Description: TODO 学员 补费
* @author 鲁晓飞 MR.Lu   
* @date 2017年6月12日 下午2:00:41 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("repair")
public class RepairMoneyController {

	@Resource
	private EntryInfoService entryInfoService;
	@Resource
	private RepairMoneyService repairMoneyService;
	
	/**
	* @Title: RepairMoneyController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.repair 
	* @Description: TODO 进入补费页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 下午2:21:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_repair_fei.jr")
	public String to_add_repair_fei(Model model,
			@RequestParam(value="entryInfoId",required=false)Integer entryInfoId){
		Map<String, Object> map=new HashMap<>();
		map.put("entry_info_id", entryInfoId);
		List<RepairMoney> repairMoneys=repairMoneyService.getRepairMoneyList(map);
		model.addAttribute("repairMoneys", repairMoneys);
		model.addAttribute("entryInfoId", entryInfoId);
		return "/crm/myentry_info/add_repair_money";
	}
	/**
	* @Title: RepairMoneyController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.repair 
	* @Description: TODO 添加补费
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 下午2:33:47 
	* @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/add_repair_money.jr")
	public void add_repair_money(HttpServletResponse response,RepairMoney repairMoney,
			@RequestParam(value="pay_time_str",required=false) String pay_time_str) throws ParseException{
		Map<String, Object> map=new HashMap<>();
		map.put("entryInfoId", repairMoney.getEntry_info_id());
		EntryInfo  entryInfo=entryInfoService.getEntryInfo(map);
		repairMoney.setEntrycondition_id(entryInfo.getEntrycondition_id());
		repairMoney.setEntryplan_id(entryInfo.getEntryplanId());
		repairMoney.setPay_time(CommentDate.get_String_date(pay_time_str));
		repairMoney.setRepair_time(new Date());
		if(repairMoneyService.saveRepairMoney(repairMoney)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	* @Title: RepairMoneyController.java 
	* @Package com.jingren.jing.school.entrysystem.controller.repair 
	* @Description: TODO 修改补费信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年6月12日 下午2:55:47 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_repair_money.jr")
	public void update_repair_money(HttpServletResponse response,RepairMoney repairMoney,
			@RequestParam(value="pay_time_str",required=false) String pay_time_str) throws ParseException{
		if(StringUtils.isNotBlank(pay_time_str)){
			repairMoney.setPay_time(CommentDate.get_String_date(pay_time_str));
		}
		if(repairMoneyService.updateRepairMoney(repairMoney)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
