package com.jingren.jing.school.front.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.pricesys.PriceSys;
import com.jingren.jing.school.bean.teacher.Teacher;
import com.jingren.jing.school.service.pricesys.PriceSysService;
import com.jingren.jing.school.service.teacher.TeacherService;
/**
* @Title: JrSystem.java 
* @Package com.jingren.jing.school.front.system 
* @Description: TODO 京人体系
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月15日 下午2:46:22 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("jrsystem")
public class JrSystemController {

	@Resource
	private TeacherService teacherService;
	@Resource
	private PriceSysService priceSysService;
	
	/**
	* @Title: JrSystem.java 
	* @Package com.jingren.jing.school.front.system 
	* @Description: TODO 服务体系页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午2:47:36 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_service_system.html")
	public String get_service_system(Model model,
			@RequestParam(value="on",required=false) Integer on){
		Map<String, Object> map=new HashMap<>();
		List<PriceSys> priceSys=priceSysService.getPriceSysList(map);
		List<Teacher> teachers=teacherService.getTeacherList(map);
		model.addAttribute("on", on);
		model.addAttribute("priceSys", priceSys);
		model.addAttribute("teachers", teachers);
		return "/school/jrsystem/system";
	}
	/**
	* @Title: JrSystemController.java 
	* @Package com.jingren.jing.school.front.system 
	* @Description: TODO 关于我们
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月15日 下午5:02:02 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/about_us.html")
	public String about_us(Model model){
		return "/school/aboutus/about_us";
	}
	/**
	* @Title: JrSystemController.java 
	* @Package com.jingren.jing.school.front.system 
	* @Description: TODO 如何学习
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 上午9:07:35 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/howstudy.html")
	public String howstudy(Model model){
		return "/school/aboutus/how_study";
	}
	/**
	* @Title: JrSystemController.java 
	* @Package com.jingren.jing.school.front.system 
	* @Description: TODO 网站警告
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年5月9日 上午11:00:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/waring.html")
	public String waring(Model model){
		return "/school/aboutus/waring";
	}
	/**
	* @Title: JrSystemController.java 
	* @Package com.jingren.jing.school.front.system 
	* @Description: TODO 如何注册用户
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月16日 上午9:14:14 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/howsregister.html")
	public String howsregister(Model model){
		return "/school/aboutus/how_register";
	}
	
	@RequestMapping("/howpaycourse.html")
	public String howpaycourse(Model model){
		return "/school/aboutus/how_paycourse";
	}
	
	@RequestMapping("/get_excel_page.html")
	public String get_excel_page(){
		
		return "/error/jiexi_excel";
		
	}
}
