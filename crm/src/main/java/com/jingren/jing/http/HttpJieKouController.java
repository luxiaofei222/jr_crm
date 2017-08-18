package com.jingren.jing.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jingren.jing.common.videokey.bean.VideoKey;
import com.jingren.jing.common.videokey.service.VideoKeyService;
import com.jingren.jing.util.ResponseUtils;

/**
* @Title: HttpJieKouController.java 
* @Package com.jingren.jing.http 
* @Description: TODO 远程接口调用
* @author 鲁晓飞 MR.Lu   
* @date 2017年3月22日 上午9:01:31 
* @version 网校+CRM系统 V1.0
 */
@Controller
@RequestMapping("api")
public class HttpJieKouController {

	@Resource
	private VideoKeyService VideoKeyService;
	/**
	* @Title: HttpJieKouController.java 
	* @Package com.jingren.jing.http 
	* @Description: TODO 获取观看视频的key
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年3月22日 上午9:05:00 
	* @version 网校+CRM系统 V1.0
	 */
	@RequestMapping("/get_video_key.html")
	public void get_video_key(VideoKey videoKey,HttpServletResponse response){
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String format1 = df.format(new Date());
		videoKey.setVideo_key(format1);
		videoKey.setVideo_time(new Date());
		VideoKeyService.saveVideoKey(videoKey);
		ResponseUtils.renderJson(response, "key", format1);
	}
}
