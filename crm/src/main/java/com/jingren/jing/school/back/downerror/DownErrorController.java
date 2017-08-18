package com.jingren.jing.school.back.downerror;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.common.downerror.bean.DownError;
import com.jingren.jing.common.downerror.service.DownErrorService;
import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.user.UserService;

@Controller
@RequestMapping("down_erro")
public class DownErrorController {

	@Resource
	private DownErrorService downErrorService;
	@Resource
	private UserService userService;
	@Resource
	private CourseVideoService courseVideoService;
	/**
	* @Title: DownErrorController.java 
	* @Package com.jingren.jing.school.back 
	* @Description: TODO 用户违规下载列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月14日 上午9:54:26 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_down_error.jr")
	public String get_down_error(Model model){
		Map<String, Object> map=new HashMap<>();
		List<DownError> downErrors=downErrorService.getDownErrorList(map);
		for (DownError downError : downErrors) {
			map.put("user_id", downError.getUser_id());
			if(downError.getUser_id()!=null){
				User user=userService.getUser(map);
				downError.setUser(user);
			}
			map.clear();
			map.put("video_id", downError.getVideo_id());
			if(downError.getVideo_id()!=null){
				CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
				downError.setCourseVideo(courseVideo);
			}
		}
		model.addAttribute("downErrors", downErrors);
		return "/downerror/down_error";
	}
}
