package com.jingren.jing.school.front.register;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.common.mail.bean.MailBean;
import com.jingren.jing.common.mail.service.MailService;
import com.jingren.jing.common.mail.util.MailUtil;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.ResponseUtils;
/**
 * 注册用户
* @Title: RegisterController.java 
* @Package com.jingren.jing.school.front.register 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月9日 上午8:03:20 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("sc_register")
public class RegisterController {


	@Resource
	private MailService mailService;
	@Resource
	private UserService userService;
	
	private final static String pic_user="/images/school/front/index/user_moren.png";
	/**
	 * 获取注册页面
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 上午8:51:37 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_register.html")
	public String get_register(){
		
		return "/school/common/register";
	}
	/**
	 * 跳转注册页
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月24日 下午2:37:42 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/register_page.html")
	public String register_page(Model model,
			@RequestParam(value="jr_url",required=false)String jr_url){
		model.addAttribute("jr_url", jr_url);
		return "/school/register/register";
	}
	/**
	 * 检查邮箱
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 下午1:42:38 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_mail.html")
	public void check_mail(HttpServletResponse response,
			@RequestParam(value="user_mail",required=false)String user_mail){
		Map<String, Object> map=new HashMap<>();
		if(StringUtils.isNotBlank(user_mail)){
			map.put("user_mail", user_mail);
			User user=userService.getUser(map);
			if(user!=null){
				ResponseUtils.renderText(response, "2");//邮箱已经注册过
			}else{
				ResponseUtils.renderText(response, "1");//邮箱可用
			}
		}else{
			ResponseUtils.renderText(response, "0");//邮箱是空的
		}
		
	}
	/**
	 * 发送邮箱验证码
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 上午8:51:46 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/send_mail.html")
	public void send_main(HttpSession session,HttpServletResponse response,
			@RequestParam(value="user_mail",required=false)String user_mail){
		int maile_code = (int) ((Math.random() * 9 + 1) * 100000);
		MailBean mailBean=mailService.getMailBean(1);//注册邮箱模板
		mailBean.setReceiver(user_mail);
		mailBean.setMessage(mailBean.getMessage()+maile_code+mailBean.getContent());
		if(MailUtil.send_mail(mailBean)){
			session.removeAttribute("mailcode");
			session.setAttribute("mailcode", maile_code);
			ResponseUtils.renderText(response, "1");//发送成功
		}else{
			ResponseUtils.renderText(response, "0");//发送失败
		}
	}
	/**
	 * 找回密码发送验证码
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 下午1:15:41 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/send_findpass_mail.html")
	public void send_findpass_mail(HttpSession session,HttpServletResponse response,
			@RequestParam(value="user_mail",required=false)String user_mail){
		int maile_code = (int) ((Math.random() * 9 + 1) * 100000);
		MailBean mailBean=mailService.getMailBean(2);//找回密码邮箱模板
		mailBean.setReceiver(user_mail);
		mailBean.setMessage(mailBean.getMessage()+maile_code+mailBean.getContent());
		if(MailUtil.send_mail(mailBean)){
			session.removeAttribute("findmailcode");
			session.setAttribute("findmailcode", maile_code);
			ResponseUtils.renderText(response, "1");//发送成功
		}else{
			ResponseUtils.renderText(response, "0");//发送失败
		}
	}
	/**
	 * 验证找回密码验证码
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 下午1:16:46 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_findpass_code.html")
	public void check_findpass_code(HttpSession session,HttpServletResponse response,
			@RequestParam(value="mail_code",required=false)Integer mail_code){
		if(mail_code!=null){
			Integer code=(Integer) session.getAttribute("findmailcode");
			if(code!=null&&code.equals(mail_code)){
				ResponseUtils.renderText(response, "1");//验证通过
			}else{
				ResponseUtils.renderText(response, "2");//验证码失效
			}
		}else{
			ResponseUtils.renderText(response, "0");//验证码空
		}
	}
	/**
	 * 验证验证码
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 上午9:13:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_code.html")
	public void check_code(HttpSession session,HttpServletResponse response,
			@RequestParam(value="mail_code",required=false)Integer mail_code){
		if(mail_code!=null){
			Integer code=(Integer) session.getAttribute("mailcode");
			if(code!=null&&code.equals(mail_code)){
				ResponseUtils.renderText(response, "1");//验证通过
			}else{
				ResponseUtils.renderText(response, "2");//验证码失效
			}
		}else{
			ResponseUtils.renderText(response, "0");//验证码空
		}
	}
	/**
	 * 注册用户
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 上午9:57:44 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/register.html")
	public void register(User user,HttpSession session,HttpServletResponse response,HttpServletRequest request){
		user.setUser_password(DigestUtils.shaHex(user.getUser_password()));
		user.setUser_time(new Date());
		user.setUser_pic(pic_user);
		if(StringUtils.isBlank(user.getUser_nickname())){
			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
	    	String num=format.format(new Date());
			user.setUser_nickname("JR"+num);
		}
		if(StringUtils.isNotBlank(user.getUser_phone())){
			Map<String, Object> map=new HashMap<>();
			map.put("user_phone", user.getUser_phone());
			User user2=userService.getUser(map);
			if(user2==null){
				user.setUser_ip(GetIPUtil.getIpAddress(request));
				if(userService.saveUser(user)){
					session.removeAttribute("user_session");
					session.setAttribute("user_session", user);//注册成功自动登录
					session.removeAttribute("url");
					ResponseUtils.renderText(response, "1");//注册成功
				}else{
					ResponseUtils.renderText(response, "0");//注册失败
				}
			}else{
				ResponseUtils.renderText(response, "3");
			}
		}else{
			Map<String, Object> map=new HashMap<>();
			map.put("user_mail", user.getUser_mail());
			User user2=userService.getUser(map);
			if(user2==null){
				user.setUser_ip(GetIPUtil.getIpAddress(request));
				if(userService.saveUser(user)){
					session.removeAttribute("user_session");
					session.setAttribute("user_session", user);//注册成功自动登录
					session.removeAttribute("url");
					ResponseUtils.renderText(response, "1");//注册成功
				}else{
					ResponseUtils.renderText(response, "0");//注册失败
				}
			}else{
				ResponseUtils.renderText(response, "3");
			}
		}
		
	}
	/**
	 * 修改密码
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月9日 下午1:43:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/update_pass.html")
	public void update_pass(User user,HttpSession session,HttpServletResponse response,HttpServletRequest request){
		user.setUser_password(DigestUtils.shaHex(user.getUser_password()));
		if(userService.updateUser(user)){
			Map<String, Object> map=new HashMap<>();
			if(StringUtils.isNotBlank(user.getUser_mail())){
				map.put("user_mail", user.getUser_mail());
			}else{
				map.put("user_phone", user.getUser_phone());
			}
			User user2=userService.getUser(map);
			session.removeAttribute("user_session");
			session.setAttribute("user_session", user2);//注册成功自动登录
			ResponseUtils.renderText(response, "1");//注册成功
		}else{
			ResponseUtils.renderText(response, "0");//注册失败
		}
	}
	/**
	 * 返回成功页面
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月10日 上午9:18:42 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_register_success.html")
	public String get_register_success(){
		return "/school/common/register_success";
	}
	/**
	 * 跳转注册成功页面
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月24日 下午3:10:09 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_register_success.html")
	public String to_register_success(Model model,
			@RequestParam(value="jr_url",required=false)String jr_url){
		model.addAttribute("jr_url", jr_url);
		return "/school/register/register_success";
	}
	/**
	 * 获取找回修改密码成功页面
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月10日 上午9:23:30 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_updatepass_success.html")
	public String get_updatepass_success(){
		return "/school/common/update_pass_success";
	}
	/**
	 * 跳转找回密码页面
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月24日 下午3:37:36 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_find_pass.html")
	public String to_find_pass(Model model,
			@RequestParam(value="type",required=false)String type,
			@RequestParam(value="jr_url",required=false)String jr_url){
		model.addAttribute("jr_url", jr_url);
		model.addAttribute("type", type);
		return "/school/find_pass/findpassword";
	}
	/**
	 * 跳转找回密码成功页面
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月24日 下午3:41:52 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_find_pass_success.html")
	public String to_find_pass_success(Model model,
			@RequestParam(value="jr_url",required=false)String jr_url){
		model.addAttribute("jr_url", jr_url);
		return "/school/find_pass/find_pass_success";
	}
	/**
	 * 
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO 跳转手机注册页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月29日 上午7:42:54 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_phone_register.html")
	public String to_phone_register(Model model,
			@RequestParam(value="jr_url",required=false)String jr_url){
		model.addAttribute("jr_url", jr_url);
		return "/school/register/phone_register";
	}
	/**
	 * 
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO 窗口手机注册页面
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月29日 下午2:08:51 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_register_phone_chuangkou.html")
	public String get_register_phone_chuangkou(Model model){
		
		return "/school/common/register_phone";
	}
	
	@RequestMapping("/get_register_mail_chuangkou.html")
	public String get_register_mail_chuangkou(Model model){
		
		return "/school/common/register_mail";
	}
	/**
	 * 跳转邮箱注册页面
	* @Title: RegisterController.java 
	* @Package com.jingren.jing.school.front.register 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月29日 上午7:42:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_mail_register.html")
	public String to_mail_register(Model model,
			@RequestParam(value="jr_url",required=false)String jr_url){
		model.addAttribute("jr_url", jr_url);
		return "/school/register/mail_register";
	}
}
