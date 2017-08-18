package com.jingren.jing.school.front.order;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.school.bean.order.CourseOrder;
import com.jingren.jing.school.service.order.CourseOrderService;
import com.jingren.jing.util.ResponseUtils;

@Controller
@RequestMapping("fron_order")
public class CourseOrderController {

	@Resource
	private CourseOrderService courseOrderService;
	
	/**
	* @Title: CourseOrderController.java 
	* @Package com.jingren.jing.school.front.order 
	* @Description: TODO 判断用户是否付款
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月18日 下午12:56:12 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/check_course_order.html")
	public void check_course_order(HttpServletResponse response,HttpSession session){
		String order_id=(String) session.getAttribute("order_id");
		Map<String, Object> map=new HashMap<>();
		map.put("order_id", order_id);
		CourseOrder courseOrder=courseOrderService.getCourseOrder(map);
		if(courseOrder!=null){
			if(courseOrder.getOrder_state().equals("已付款")){
				ResponseUtils.renderText(response, "1");
			}else{
				ResponseUtils.renderText(response, "0");
			}
		}
	}
}
