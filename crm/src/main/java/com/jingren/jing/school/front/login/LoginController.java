package com.jingren.jing.school.front.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.ResponseUtils;
/**
 * 登录页面
* @Title: LoginController.java 
* @Package com.jingren.jing.school.front.login 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月9日 上午8:14:58 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("sc_login")
public class LoginController {

	@Resource
	private UserService userService;
	/**
	 * 获取登录页面
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 上午8:16:56 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_login.html")
	public String get_login(){
		return "/school/common/login";
	}
	/**
	 * 登录页面
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 上午11:53:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/login.html")
	public void login(HttpSession session,HttpServletResponse response,
			@RequestParam(value="user_phone",required=false)String user_phone,
			@RequestParam(value="user_mail",required=false)String user_mail,
			@RequestParam(value="user_password",required=false)String user_password){
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(user_phone)){
			map.put("user_phone", user_phone);
		}else{
			map.put("user_mail", user_mail);
		}
		User user=userService.getUser(map);
		if(user!=null){
			if(user.getUser_password().equals(DigestUtils.shaHex(user_password))){
				if(user.getUser_state().equals("限制")){
					ResponseUtils.renderText(response, "3");//该用户被限制
				}else{
					session.removeAttribute("user_session");
					session.setAttribute("user_session", user);
					session.removeAttribute("url");
					ResponseUtils.renderText(response, "1");//登录成功
				}
			}else{
				ResponseUtils.renderText(response, "2");//密码错误
			}
		}else{
			ResponseUtils.renderText(response, "0");//用户名不存在
		}
	}
	/**
	 * 退出用户
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 上午11:55:10 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/logout.html")
	public void logout(HttpSession session){
		session.removeAttribute("user_session");
	}
	/**
	 * 找回密码页面
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 下午1:02:37 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_update_pass.html")
	public String get_update_pass(String type){
		if(type.equals("phone")){
			return "/school/common/find_pass_phone";
		}else{
			return "/school/common/find_pass";
		}
	}
	/**
	 * 登录页面
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月24日 下午1:26:27 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/login_page.html")
	public String login_page(HttpSession session,HttpServletRequest request,
			@RequestParam(value="jr_url",required=false)String jr_url){
		session.removeAttribute("url");
		if(jr_url!=null){
			session.setAttribute("url", jr_url);
		}else{
			session.setAttribute("url", "/index.jsp");
		}
		return "/school/login/login";
	}
	/**
	 * 获取手机登录页面
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 下午4:22:43 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_phone_login.html")
	public String get_phone_login(){
		return "/school/login/phone_login";
	}
	/**
	 * 
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO 弹窗手机登陆页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月29日 下午1:38:58 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_phone_tanchuang_login.html")
	public String get_phone_tanchuang_login(){
		return "/school/common/phone_login";
	}
	/**
	 * 
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO 弹窗邮箱登录页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月29日 下午1:39:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_mail_tanchuang_login.html")
	public String get_mail_tanchuang_login(){
		return "/school/common/mail_login";
	}
	/**
	 * 邮箱登录页面
	* @Title: LoginController.java 
	* @Package com.jingren.jing.school.front.login 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 下午4:27:07 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_mail_page.html")
	public String get_mail_page(){
		return "/school/login/mail_login";
	}
}
