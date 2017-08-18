package com.jingren.jing.school.back.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
* @Title: BackOrderController.java 
* @Package com.jingren.jing.school.back.order 
* @Description: TODO 订单管理
* @author 鲁晓飞 MR.Lu   
* @date 2016年12月9日 下午4:53:54 
* @version 网校+CRM系统 V1.0
 */
import org.springframework.web.bind.annotation.RequestParam;

import com.jingren.jing.school.bean.course.CourseVideo;
import com.jingren.jing.school.bean.order.CourseOrder;
import com.jingren.jing.school.bean.user.User;
import com.jingren.jing.school.service.course.CourseVideoService;
import com.jingren.jing.school.service.order.CourseOrderService;
import com.jingren.jing.school.service.user.UserService;
import com.jingren.jing.util.Pagers;
@Controller
@RequestMapping("back_order")
public class BackOrderController {

	@Resource
	private CourseOrderService courseOrderService;
	@Resource
	private UserService userService;
	@Resource
	private CourseVideoService courseVideoService;
	
	/**
	* @Title: BackOrderController.java 
	* @Package com.jingren.jing.school.back.order 
	* @Description: TODO 订单列表
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月9日 下午5:46:40 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_order_list.jr")
	public String get_mycourse_order(Model model, 
			@RequestParam(value = "order_number", required = false) String order_number,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber) {
			Map<String, Object> map = new HashMap<>();
			if(StringUtils.isNotBlank(order_number)){//通过订单号查询
				map.put("order_number", order_number);
			}
			map.put("pageNumber", (pageNumber - 1) * limit);
			map.put("limit", limit);
			List<CourseOrder> courseOrders_check = courseOrderService.getCourseOrderList(map);
			for (CourseOrder courseOrder : courseOrders_check) {
				if(courseOrder.getOrder_state().equals("待付款")){
					long time=System.currentTimeMillis()-courseOrder.getOrder_time().getTime();
					if(time>48*60*60*1000){//超过48小时，删除订单
						courseOrder.setIs_show("删除");
						courseOrderService.updateCourseOrder(courseOrder);
					}
				}
			}
			List<CourseOrder> courseOrders = courseOrderService.getCourseOrderList(map);
			Integer order_number_int = courseOrderService.getCouseOrderNumber(map);
			for (CourseOrder courseOrder : courseOrders) {
				map.clear();
				map.put("video_id", courseOrder.getVideo_id());
				CourseVideo courseVideo=courseVideoService.getCourseVideo(map);
				courseOrder.setCourseVideo(courseVideo);
				map.clear();
				map.put("user_id", courseOrder.getUser_id());
				User user=userService.getUser(map);
				courseOrder.setUser(user);
			}
			Pagers<CourseOrder> pagers = new Pagers<CourseOrder>(order_number_int, pageNumber, limit);
			pagers.setList(courseOrders);
			model.addAttribute("courseOrders", pagers);
			return "/order/order";
	}
	/**
	* @Title: BackOrderController.java 
	* @Package com.jingren.jing.school.back.order 
	* @Description: TODO 查看用户订单信息弹窗
	* @author 鲁晓飞 MR.Lu   
	* @date 2016年12月12日 上午8:38:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/to_check_order.jr")
	public String get_order_details(Model model,
			@RequestParam(value="order_id",required=false)String order_id){
		Map<String, Object> map=new HashMap<>();
		map.put("order_id", order_id);
		CourseOrder courseOrder=courseOrderService.getCourseOrder(map);
		map.clear();
		map.put("video_id", courseOrder.getVideo_id());
		CourseVideo courseVideo = courseVideoService.getCourseVideo(map);
		map.clear();
		map.put("video_parent_id", courseVideo.getVideo_id());
		List<CourseVideo> courseVideos2 = courseVideoService.getCourseVideoList(map);
		Integer course_num = 0;
		for (CourseVideo courseVideo2 : courseVideos2) {
			map.clear();
			map.put("video_parent_id", courseVideo2.getVideo_id());
			map.put("video_level", 3);
			course_num += courseVideoService.getCourseVideoNumber(map);
		}
		courseVideo.setKeshi_number(course_num);// 课时数
		courseOrder.setCourseVideo(courseVideo);
		map.clear();
		map.put("user_id", courseOrder.getUser_id());
		User user=userService.getUser(map);
		courseOrder.setUser(user);
		model.addAttribute("courseOrder", courseOrder);
		return "/order/check_order";
	}
}
