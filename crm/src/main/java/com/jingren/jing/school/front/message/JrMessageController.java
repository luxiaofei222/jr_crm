package com.jingren.jing.school.front.message;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 消息
* @Title: JrMessageController.java 
* @Package com.jingren.jing.school.front.message 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月28日 下午12:01:23 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.message.Annexes;
import com.jingren.jing.school.bean.message.JRMessage;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.message.AnnexesService;
import com.jingren.jing.school.service.message.MessageService;
import com.jingren.jing.util.DeleteFile;
import com.jingren.jing.util.Pagers;
import com.jingren.jing.util.ResponseUtils;
import com.jingren.jing.util.WordGuolvUtil;
@Controller
@RequestMapping("sc_message")
public class JrMessageController {

	@Resource
	private MessageService messageService;
	@Resource
	private AnnexesService annexesService;
	/**
	 * 发送消息
	* @Title: JrMessageController.java 
	* @Package com.jingren.jing.school.front.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 下午1:00:53 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_message.html")
	public void save_message(JRMessage jrMessage,HttpSession session,HttpServletResponse response){
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			jrMessage.setUser_nickname(user.getUser_nickname());
			jrMessage.setUser_id(user.getUser_id());
			jrMessage.setMessage_time(new Date());
			if(messageService.saveMessage(jrMessage)){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
	/**
	 * 获取收件箱列表
	* @Title: JrMessageController.java 
	* @Package com.jingren.jing.school.front.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 下午1:28:59 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_message_list.html")
	public String get_message_list(Model model, HttpSession session,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber){
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", user.getUser_id());
			map.put("send_type", "后台");
			map.put("is_show", "是");
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
			List<JRMessage> jrMessages=messageService.getMessageList(map);
			Integer jrMessagesNumber=messageService.getMessageNumber(map);
			for (JRMessage jrMessage : jrMessages) {
				map.clear();
				map.put("message_id", jrMessage.getMessage_id());
				List<Annexes> annexes=annexesService.getAnnexesList(map);
				if(annexes.size()>0){
					jrMessage.setAnnexes(annexes);
				}
			}
			Pagers<JRMessage> pagers = new Pagers<JRMessage>(jrMessagesNumber, pageNumber, limit);
			pagers.setList(jrMessages);
			model.addAttribute("jrMessages", pagers);
			return "/school/person/inbox";
		}else{
			return "/404";
		}
	}
	/**
	 * 获取未读信件的数量
	* @Title: JrMessageController.java 
	* @Package com.jingren.jing.school.front.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月29日 下午1:01:57 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_weidu_message_num.html")
	public void get_weidu_message_num(HttpServletResponse response,HttpSession session){
		User user = (User) session.getAttribute("user_session");
		if (user != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("user_id", user.getUser_id());
			map.put("send_type", "后台");
			map.put("is_read", "否");
			map.put("is_show", "是");
			Integer jrMessagesNumber=messageService.getMessageNumber(map);
			ResponseUtils.renderText(response, String.valueOf(jrMessagesNumber));
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
	/**
	 * 获取信件详情
	* @Title: JrMessageController.java 
	* @Package com.jingren.jing.school.front.message 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月28日 下午2:20:28 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_message_details.html")
	public String get_message_details(Model model,HttpServletRequest request,
			@RequestParam(value="message_id",required=false)Integer message_id){
		Map<String, Object> map = new HashMap<>();	
		map.put("message_id", message_id);
		JRMessage jrMessage=messageService.getMessage(map);
		List<Annexes> annexes=annexesService.getAnnexesList(map);
		for (Annexes annexes2 : annexes) {
			String file=DeleteFile.get_file_length(annexes2.getAnnexes_file(), request);
			annexes2.setFile_length(file);
		}
		jrMessage.setAnnexes(annexes);
		model.addAttribute("jrMessage", jrMessage);//信件详情
		model.addAttribute("annex_num", annexes.size());//附件数量
		JRMessage jrMessage2=new JRMessage();
		jrMessage2.setMessage_id(message_id);
		jrMessage2.setIs_read("是");
		messageService.updateJrmessage(jrMessage2);
		return "/school/person/message_details";
	}
	
}
