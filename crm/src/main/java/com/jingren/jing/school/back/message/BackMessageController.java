package com.jingren.jing.school.back.message;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jingren.jing.school.bean.employee.Employee;
import com.jingren.jing.school.bean.message.Annexes;
import com.jingren.jing.school.bean.message.JRMessage;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.message.AnnexesService;
import com.jingren.jing.school.service.message.MessageService;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.UploadAddress;

/**
* @Title: BackMessageController.java 
* @Package com.jingren.jing.school.back.message 
* @Description: TODO 信息处理
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月13日 下午2:14:50 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("back_message")
public class BackMessageController {

	@Resource
	private MessageService messageService;
	@Resource
	private UserService userService;
	@Resource
	private AnnexesService annexesService;	
	
	private static final String UP_FRONT_FILE ="images/school/front/message";
	private static final String UP_ANNEX_FILE ="annexes";
	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 收件箱
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午2:28:08 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_shoujian_page.jr")
	public String get_shoujian_page(Model model,
			@RequestParam(value="user_nickname",required=false)String user_nickname,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(user_nickname)){
			map.put("user_nickname", user_nickname);
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("send_type", "前台");
		List<JRMessage> messages=messageService.getMessageList(map);
		Integer messages_number=messageService.getMessageNumber(map);
		for (JRMessage jrMessage : messages) {
			map.clear();
			map.put("user_id", jrMessage.getUser_id());
			User user=userService.getUser(map);
			jrMessage.setUser(user);
		}
		Pagers<JRMessage> pagers = new Pagers<JRMessage>(messages_number, pageNumber, limit);
		pagers.setList(messages);
		model.addAttribute("messages", pagers);
		return "/message/shoujian";
	}
	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 本地上传图片
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午2:57:07 
	* @version 网校+CRM系统 V1.0
	 */
	@ResponseBody
	@RequestMapping("/uploadpic.jr")
	public Map<String, Object> uploadpic(HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "ajaxTaskFile", required = false) MultipartFile ajaxTaskFile){
		Map<String,Object> map=new HashMap<>();
		String path = UploadAddress.getUploadDate(ajaxTaskFile, request,
				UP_FRONT_FILE);
		System.out.println(path);
		map.put("file_path", path);
		return map;
	}
	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 发送系统通知弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午3:32:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_add_message.jr")
	public String to_add_message(){
		
		return "/message/send_tongzhi";
	}
	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 发送系统通知
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午3:45:35 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/send_message.jr")
	public void send_message(HttpServletResponse response,HttpServletRequest request,
			JRMessage jrMessage,HttpSession session,
			@RequestParam(value="user_name",required=false) String user_name,
			@RequestParam(value = "file_up", required = false) MultipartFile file_up){
		Map<String, Object> map=new HashMap<>();
		Employee employee=(Employee) session.getAttribute("employee_session");
		if(employee!=null){
			jrMessage.setEmployee_id(employee.getEmployee_id());
		}
		jrMessage.setMessage_time(new Date());
		jrMessage.setMessage_type("系统通知");
		jrMessage.setSend_type("后台");
		if(file_up!=null){
			String path = UploadAddress.getUploadDate(file_up, request,
					UP_ANNEX_FILE);
			Annexes annexes =new Annexes();
			annexes.setAnnexes_file(path);
			annexes.setMessage_id(jrMessage.getMessage_id());
			annexes.setMessage_annexes(file_up.getOriginalFilename());
			annexesService.saveAnnexes(annexes);
		}
		if(StringUtils.isNotBlank(user_name)){
			if(DeleteFile.isMobileNO(user_name)){//是手机号
				map.put("user_phone", user_name);
				User user=userService.getUser(map);
				if(user!=null){
					map.put("user_id", jrMessage.getUser_id());
					jrMessage.setUser_id(user.getUser_id());
					jrMessage.setUser_nickname(user.getUser_nickname());
					messageService.saveMessage(jrMessage);
					ResponseUtils.renderText(response, "1");
				}else{
					ResponseUtils.renderText(response, "2");
				}
			}else{//不是手机号
				map.put("user_mail", user_name);
				User user=userService.getUser(map);
				if(user!=null){
					map.put("user_id", jrMessage.getUser_id());
					jrMessage.setUser_id(user.getUser_id());
					jrMessage.setUser_nickname(user.getUser_nickname());
					messageService.saveMessage(jrMessage);
					ResponseUtils.renderText(response, "1");
				}else{
					ResponseUtils.renderText(response, "3");
				}
			}
		}else{
			Integer userid=jrMessage.getUser_id();
			map.clear();
			if(userid!=null){
				map.put("user_id", jrMessage.getUser_id());
				User user=userService.getUser(map);
				jrMessage.setUser_nickname(user.getUser_nickname());
				messageService.saveMessage(jrMessage);
			}else{
				List<User> users=userService.getUserlist(map);
				for (User user : users) {
					jrMessage.setUser_id(user.getUser_id());
					jrMessage.setUser_nickname(user.getUser_nickname());
					messageService.saveMessage(jrMessage);
				}
			}
			ResponseUtils.renderText(response, "1");
		}
	}
	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 删除收件箱信息
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午3:52:39 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_message.jr")
	public void delete_message(HttpServletResponse response,
			@RequestParam(value="str",required=false)String str){
		String[] array=str.split(",");
		for (String string : array) {
			int id=Integer.valueOf(string);
			messageService.deleteMessage(id);
		}
		ResponseUtils.renderText(response, "1");
	}
	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 发件箱内容
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午3:59:11 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_fajian_page.jr")
	public String get_fajian_page(Model model,
			@RequestParam(value="user_phone",required=false)String user_phone,
			@RequestParam(value="user_nickname",required=false)String user_nickname,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		Map<String, Object> map = new HashMap<>();
		if(StringUtils.isNotBlank(user_nickname)){
			map.put("user_nickname", user_nickname);
		}
		if(StringUtils.isNotBlank(user_phone)){
			map.put("user_phone", user_phone);
			User user =userService.getUser(map);
			map.clear();
			map.put("user_id", user.getUser_id());
		}
		map.put("pageNumber", (pageNumber - 1) * limit);
		map.put("limit", limit);
		map.put("send_type", "后台");
		List<JRMessage> messages=messageService.getMessageList(map);
		Integer messages_number=messageService.getMessageNumber(map);
		for (JRMessage jrMessage : messages) {
			map.clear();
			map.put("user_id", jrMessage.getUser_id());
			User user=userService.getUser(map);
			jrMessage.setUser(user);
		}
		Pagers<JRMessage> pagers = new Pagers<JRMessage>(messages_number, pageNumber, limit);
		pagers.setList(messages);
		model.addAttribute("messages", pagers);
		return "/message/fajianxiang";
	}
	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 回复弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月13日 下午4:43:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_huifu_message.jr")
	public String to_huifu_message(Model model,
			@RequestParam(value="message_id",required=false) Integer message_id){
		Map<String, Object> map = new HashMap<>();
		map.put("message_id", message_id);
		JRMessage jrMessage=messageService.getMessage(map);
		model.addAttribute("jrMessage", jrMessage);
		return "/message/huifu_message";
	}

	/**
	* @Title: BackMessageController.java 
	* @Package com.jingren.jing.school.back.message 
	* @Description: TODO 查看消息
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月19日 上午9:59:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_message.jr")
	public String check_message(Model model,HttpServletRequest request,
			@RequestParam(value="message_id",required=false) Integer message_id){
		Map<String, Object> map = new HashMap<>();
		map.put("message_id", message_id);
		JRMessage jrMessage=messageService.getMessage(map);
		if(jrMessage.getSend_type().equals("前台")&&jrMessage.getIs_read().equals("否")){
			jrMessage.setIs_read("是");
			messageService.updateJrmessage(jrMessage);
		}
		List<Annexes> annexes=annexesService.getAnnexesList(map);
		for (Annexes annexes2 : annexes) {
			String file=DeleteFile.get_file_length(annexes2.getAnnexes_file(), request);
			String prefix1 = annexes2.getAnnexes_file().substring(annexes2.getAnnexes_file().lastIndexOf("."));
			annexes2.setFile_length(file);
			annexes2.setHouzhui(prefix1);
		}
		model.addAttribute("annexes", annexes);
		model.addAttribute("annexes_number", annexes.size());
		model.addAttribute("jrMessage", jrMessage);
		return "/message/check_message";
	}
}
