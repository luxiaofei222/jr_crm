package com.jingren.jing.school.extension;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @Title: ExtensionController.java 
* @Package com.jingren.jing.school.extension 
* @Description: TODO 京人教育推广页面
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月19日 下午2:22:09 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("exrebsion")
public class ExtensionController {

	/**
	* @Title: ExtensionController.java 
	* @Package com.jingren.jing.school.extension 
	* @Description: TODO 心理学推广页
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月19日 下午2:23:23 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_psychology.html")
	public String get_psychology(){
		return "/extension/psychology";
	}
}
