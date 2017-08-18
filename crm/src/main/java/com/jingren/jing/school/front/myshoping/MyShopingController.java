package com.jingren.jing.school.front.myshoping;

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
 * 我的购物车
* @Title: MyCourseController.java 
* @Package com.jingren.jing.school.front.mycourse 
* @Description: TODO
* @author 鲁晓飞 MR.Lu   
* @date 2016年11月21日 下午1:23:35 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.myshoping.MyShoping;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.myshoping.MyShopingService;
import com.jingren.jing.util.ResponseUtils;
@Controller
@RequestMapping("sc_myshoping")
public class MyShopingController {

	@Resource
	private MyShopingService myShopingService;
	@Resource
	private CourseVideoService courseVideoService;
	/**
	 * 加入我的购物车
	* @Title: MyCourseController.java 
	* @Package com.jingren.jing.school.front.mycourse 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午1:34:17 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/save_myshoping.html")
	public void save_mycourse(MyShoping myShoping,HttpSession session,HttpServletRequest request,
			HttpServletResponse response){
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("video_id", myShoping.getVideo_id());
			map.put("user_id", user.getUser_id());
			MyShoping myShoping2=myShopingService.getMyShoping(map);
			if(myShoping2!=null){
				ResponseUtils.renderText(response, "1");//加入我的购物车成功
			}else{
				myShoping.setMy_shoping_time(new Date());
				myShoping.setUser_id(user.getUser_id());
				if(myShopingService.saveMyShoping(myShoping)){
					ResponseUtils.renderText(response, "1");//加入我的购物车成功
				}else{
					ResponseUtils.renderText(response, "0");
				}
			}
		}else{
			String url=request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			ResponseUtils.renderText(response, "3");//用户未登录
		}
	}
	/**
	 * 获取我的购物车数量
	* @Title: MyShopingController.java 
	* @Package com.jingren.jing.school.front.myshoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午3:05:03 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_my_shoping_number.html")
	public void get_my_shoping_number(HttpServletResponse response,HttpSession session){
		User user=(User) session.getAttribute("user_session");
		if(user!=null){
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("user_id", user.getUser_id());
			int shoping_number=myShopingService.getMyShopingNumber(map);
			String num=String.valueOf(shoping_number);
			ResponseUtils.renderText(response, num);
		}
	}
	/**
	 * 我的购物车列表
	* @Title: MyShopingController.java 
	* @Package com.jingren.jing.school.front.myshoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月21日 下午3:16:45 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("get_my_shoping_main.html")
	public String get_my_shoping_main(HttpSession session,Model model,
			HttpServletRequest request){
		User user=(User) session.getAttribute("user_session");
		Map<String, Object> map=new HashMap<String, Object>();
		if(user!=null){
			map.put("user_id", user.getUser_id());
			List<MyShoping> myShopings=myShopingService.getMyShopingList(map);
			for (MyShoping myShoping : myShopings) {
				map.clear();
				map.put("video_id", myShoping.getVideo_id());
				CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
				map.clear();
				map.put("video_parent_id", myShoping.getVideo_id());
				List<CourseVideo>courseVideos2=courseVideoService.getCourseVideoList(map);
				Integer course_num = 0;
				for (CourseVideo courseVideo2 : courseVideos2) {
					map.clear();
					map.put("video_parent_id", courseVideo2.getVideo_id());
					map.put("video_level", 3);
					 course_num=courseVideoService.getCourseVideoNumber(map);
					 course_num +=course_num;
				}
				courseVideo.setKeshi_number(course_num);//课时数
				myShoping.setCourseVideo(courseVideo);
			}
			model.addAttribute("myShopings", myShopings);
			return "/school/myshoping/myshoping";
		}else{
			String url=request.getServletPath();
			session.removeAttribute("url");
			session.setAttribute("url", url);
			return "/school/login/login";
		}
	}
	/**
	 * 删除我的购物车信息
	* @Title: MyShopingController.java 
	* @Package com.jingren.jing.school.front.myshoping 
	* @Description: TODO
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年11月23日 上午8:34:37 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/delete_shoping.html")
	public void delete_shoping(HttpServletResponse response,
			@RequestParam(value="my_shoping_id",required=false)Integer my_shoping_id){
		if(myShopingService.deleteMyShoping(my_shoping_id)){
			ResponseUtils.renderText(response, "1");
		}else{
			ResponseUtils.renderText(response, "0");
		}
	}
}
