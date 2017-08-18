package com.jingren.jing.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public class CallBackUtil {

	
	/**
	* @Title: CallBackUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 跨域使用工具类
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月7日 下午5:59:44 
	* @version 网校+CRM系统 V1.0
	 */
	public static void get_callback(HttpServletResponse response,Map<String, Object> map,HttpServletRequest request) throws IOException{
		response.setContentType("text/plain");  
        response.setHeader("Pragma", "No-cache");  
        response.setHeader("Cache-Control", "no-cache"); 
        response.setCharacterEncoding("UTF-8");
        response.setDateHeader("Expires", 0);  
        PrintWriter out = response.getWriter();       
        JSONObject resultJSON = JSONObject.fromObject(map); //根据需要拼装json  
        String jsonpCallback = request.getParameter("callback");//客户端请求参数  
        out.println(jsonpCallback+"("+resultJSON.toString(1,1)+")");//返回jsonp格式数据  
        out.flush();  
        out.close();
	}
}
