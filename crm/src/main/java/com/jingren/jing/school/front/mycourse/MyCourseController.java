package com.jingren.jing.school.front.mycourse;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 我的课程
* @Title: MyCourseController.java 
* @Package com.jingren.jing.school.front.mycourse 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月21日 下午1:23:35 
* @version 网校+CRM系统 V1.0
 */

import com.jingren.jing.school.bean.mycourse.MyCourse;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.mycourse.MyCourseService;
import com.jingren.jing.util.ResponseUtils;
@Controller
@RequestMapping("sc_mycourse")
public class MyCourseController {

	@Resource
	private MyCourseService myCourseService;
	/**
	 * 加入我的课程
	* @Title: MyCourseController.java 
	* @Package com.jingren.jing.school.front.mycourse 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:34:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_mycourse.html")
	public void save_mycourse(MyCourse myCourse,HttpSession session,
			HttpServletResponse response){
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("video_id", myCourse.getVideo_id());
			map.put("user_id", user.getUser_id());
			MyCourse myCourse2=myCourseService.getMyCourse(map);
			if(myCourse2!=null){
				ResponseUtils.renderText(response, "1");//加入我的课程成功
			}else{
				myCourse.setMy_course_time(new Date());
				myCourse.setUser_id(user.getUser_id());
				if(myCourseService.saveMyCourse(myCourse)){
					ResponseUtils.renderText(response, "1");//加入我的课程成功
				}else{
					ResponseUtils.renderText(response, "0");
				}
			}
		}else{
			ResponseUtils.renderText(response, "3");//用户未登录
		}
	}
}
