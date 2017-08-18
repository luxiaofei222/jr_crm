package com.jingren.jing.school.front.baoming;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.school.bean.baoming.Baoming;
import com.jingren.jing.school.service.baoming.BaomingService;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.ResponseUtils;


@Controller
@RequestMapping("baoming")
public class BaomingController {

	@Resource
	private BaomingService baomingService;
	/**
	* @Title: BaomingController.java 
	* @Package com.jr.m.controller.baoming 
	* @Description: TODO 学员报名
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年4月5日 下午4:27:56 
	* @version 手机站 V1.0
	 */
	@RequestMapping("/save_baoming.html")
	public void save_baoming(Baoming baoming,HttpServletResponse response,HttpServletRequest request){
		baoming.setBaoming_time(new Date());
		baoming.setUser_ip(GetIPUtil.getIpAddress(request));
		if(baomingService.saveBaoming(baoming)){
			ResponseUtils.renderText(response, "提交成功！");
		}
	}
}
