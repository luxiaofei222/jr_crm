package com.jingren.jing.personal.controller.punch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: PunchController.java 
* @Package com.jingren.jing.personal.controller.punch 
* @Description: TODO 忘记打卡
* @author 鲁晓飞 MR.Lu   
* @date 2017年7月17日 下午1:49:40 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.organization.bean.Organization;
import com.jingren.jing.common.organization.service.OrganizationService;
import com.jingren.jing.personal.bean.punch.Punch;
import com.jingren.jing.personal.service.punch.PunchService;
import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.util.CommentDate;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("punch")
public class PunchController {

	@Resource
	private PunchService punchService;
	@Resource
	private OrganizationService organizationService;

	/**
	 * @Title: PunchController.java
	 * @Package com.jingren.jing.personal.controller.punch
	 * @Description: TODO 忘记打卡页面
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年7月17日 下午1:51:00
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_punch_main.jr")
	public String get_punch_main(Model model, HttpSession session) throws ParseException {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", 0);
		map.put("limit", 5);
		map.put("employee_id", employee.getEmployee_id());
		Integer punch_number = punchService.getPunchNumber(map);
		List<Punch> punchs = punchService.getPunchList(map);
		Pagers<Punch> pagers = new Pagers<>(punch_number, 1, 5);
		for (Punch punch : punchs) {
			Integer tianshu=CommentDate.daysBetween(punch.getDaka_time(), new Date());
			if(tianshu>180){
				punchService.deletePunch(punch.getPunch_id());
			}
		}
		pagers.setList(punchs);
		model.addAttribute("punchs", pagers);
		return "/personal/project/wangji_daka";
	}

	/**
	 * @Title: PunchController.java
	 * @Package com.jingren.jing.personal.controller.punch
	 * @Description: TODO 保存忘记打卡时间
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年7月17日 下午3:56:01
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_punch_employee.jr")
	public void save_punch_employee(Punch punch, HttpSession session, HttpServletResponse response,
			@RequestParam(value = "riqi_str", required = false) String riqi_str) throws ParseException {
		Employee employee = (Employee) session.getAttribute("employee_session");
		punch.setDaka_time(CommentDate.get_String_date(riqi_str));
		punch.setPunch_time(new Date());
		punch.setEmployee_id(employee.getEmployee_id());
		punch.setBumen_id(employee.getBumen_id());
		punch.setGangwei_id(employee.getOrganization_id());
		punch.setBumen_shangji(employee.getParent_id());
		punch.setEmployee_name(employee.getEmployee_name());
		punch.setRenshi_state(0);
		if (StringUtils.isBlank(punch.getPunch_info())) {
			punch.setPunch_info("暂无原因");
		}
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		punch.setNianyue(sdf.format(c.getTime()));
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		punch.setNianyueri(sdf2.format(new Date()));
		if (punchService.savePunch(punch)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: PunchController.java
	 * @Package com.jingren.jing.personal.controller.punch
	 * @Description: TODO 打卡记录列表
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年7月17日 下午4:12:11
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException 
	 */
	@RequestMapping("/get_punch_list.jr")
	public String get_punch_main(Model model, HttpSession session,
			@RequestParam(value = "limit", required = false, defaultValue = "5") final Integer limit,
			@RequestParam(value = "pageNumber", required = false) final Integer pageNumber) throws ParseException {
		Employee employee = (Employee) session.getAttribute("employee_session");
		Map<String, Object> map = new HashMap<>();
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("employee_id", employee.getEmployee_id());
		Integer punch_number = punchService.getPunchNumber(map);
		List<Punch> punchs = punchService.getPunchList(map);
		Pagers<Punch> pagers = new Pagers<>(punch_number, 1, 5);
		for (Punch punch : punchs) {
			Integer tianshu=CommentDate.daysBetween(punch.getDaka_time(), new Date());
			if(tianshu>180){
				punchService.deletePunch(punch.getPunch_id());
			}
		}
		pagers.setList(punchs);
		model.addAttribute("punchs", pagers);
		return "/personal/project/wangji_daka_list";
	}

	/**
	 * @Title: PunchController.java
	 * @Package com.jingren.jing.personal.controller.punch
	 * @Description: TODO 删除打卡记录
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年7月17日 下午4:17:55
	 * @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_daka.jr")
	public void delete_daka(HttpServletResponse response,
			@RequestParam(value = "punch_id", required = false) Integer punch_id) {
		if (punchService.deletePunch(punch_id)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}

	/**
	 * @Title: PunchController.java
	 * @Package com.jingren.jing.personal.controller.punch
	 * @Description: TODO 人事查看忘记打卡记录
	 * @author 鲁晓飞 MR.Lu
	 * @date 2017年7月17日 下午5:01:10
	 * @version 网校+CRM系统 V1.0
	 * @throws ParseException
	 */
	@RequestMapping("/get_oa_punch_list.jr")
	public String get_oa_punch_list(Model model,
			@RequestParam(value = "nianyue", required = false) final String nianyue,
			@RequestParam(value = "start_time_str", required = false) final String start_time_str,
			@RequestParam(value = "end_time_str", required = false) final String end_time_str,
			@RequestParam(value = "bumen_id", required = false) final Integer bumen_id,
			@RequestParam(value = "employee_id", required = false) final Integer employee_id,
			@RequestParam(value = "limit", required = false, defaultValue = "20") final Integer limit,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") final Integer pageNumber) throws ParseException {
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(start_time_str) && StringUtils.isNotBlank(end_time_str)) {
			map.put("start_time", CommentDate.get_String_date(start_time_str));
			long currentTime = CommentDate.get_String_date(end_time_str).getTime();
			currentTime += 24 * 60 * 60 * 1000 - 1000;// 加23小时59分59秒
			Date date = new Date(currentTime);
			map.put("end_time", date);
			model.addAttribute("start_time_str", start_time_str);
			model.addAttribute("end_time_str", end_time_str);
		}
		if (StringUtils.isNotBlank(nianyue)) {//年月
			map.put("nianyue", nianyue);
			model.addAttribute("nianyue", nianyue);
		}else{
			map.put("nianyue", sdf.format(c.getTime()));
			model.addAttribute("nianyue", sdf.format(c.getTime()));
		}
		if (employee_id != null) {
			map.put("employee_id", employee_id);
			model.addAttribute("employee_id", employee_id);
		}
		if (bumen_id != null) {
			map.put("bumen_id", bumen_id);
			model.addAttribute("bumen_id", bumen_id);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		model.addAttribute("limit", limit);
		Integer punch_number = punchService.getPunchNumber(map);
		List<Punch> punchs = punchService.getPunchList(map);
		for (Punch punch : punchs) {
			map.clear();
			map.put("organization_id", punch.getBumen_id());
			Organization organization=organizationService.getOranization(map);
			punch.setOrganization(organization);
			
			Integer tianshu=CommentDate.daysBetween(punch.getDaka_time(), new Date());
			if(tianshu>180){
				punchService.deletePunch(punch.getPunch_id());
			}
			
		}
		Pagers<Punch> pagers = new Pagers<>(punch_number, pageNumber, limit);
		pagers.setList(punchs);
		model.addAttribute("punchs", pagers);
		/**********************************条件***************************************/
		model.addAttribute("time_list", CommentDate.get_nianyuelist());
		Map<String, Object> map_leave = new HashMap<>();
		map_leave.put("organization_level", 1);
		List<Organization> organizations = organizationService.getOranizationList(map_leave);
		model.addAttribute("organizations", organizations);
		return "/oa/punch/punch_list";
	}
	/**
	* @Title: PunchController.java 
	* @Package com.jingren.jing.personal.controller.punch 
	* @Description: TODO 审核忘记打卡记录
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月17日 下午6:19:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_exam_punch.jr")
	public String to_exam_punch(Model model,@RequestParam(value="punch_id",required=false) Integer punch_id){
		Map<String, Object> map = new HashMap<>();
		map.put("punch_id", punch_id);
		Punch punch=punchService.getPunch(map);
		map.clear();
		map.put("organization_id", punch.getBumen_id());
		Organization organization=organizationService.getOranization(map);
		punch.setOrganization(organization);
		model.addAttribute("punch", punch);
		return "/oa/punch/check_punch";
	}
	/**
	* @Title: PunchController.java 
	* @Package com.jingren.jing.personal.controller.punch 
	* @Description: TODO 人事审批
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年7月17日 下午6:38:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_punch.jr")
	public void delete_daka(HttpServletResponse response,Punch punch) {
		if (punchService.updatePunch(punch)) {
			ResponseUtils.renderText(response, "1");
		} else {
			ResponseUtils.renderText(response, "0");
		}
	}
}
