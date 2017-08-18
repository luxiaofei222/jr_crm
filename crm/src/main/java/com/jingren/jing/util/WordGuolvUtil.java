package com.jingren.jing.util;

import java.util.ArrayList;
import java.util.List;

public class WordGuolvUtil {

	/**
	* @Title: WordGuolvUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO 特殊字符过滤
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月3日 下午3:58:02 
	* @version 网校+CRM系统 V1.0
	 */
	public static String toHTML(String value) {
		if (value == null)
			value = "";
		value = value.replaceAll("&", "&amp;");
		value = value.replaceAll("<", "&lt;");
		value = value.replaceAll(">", "&gt;");
		value = value.replaceAll("\"", "&quot;");
		value = value.replaceAll(" ", "&nbsp;");
		value = value.replaceAll("\n", "<br>");
		return value;
	}
	public static void main(String[] args) {
		System.out.println(get_ip_word(null));
	}
	
	/**
	* @Title: WordGuolvUtil.java 
	* @Package com.jingren.jing.util 
	* @Description: TODO IP匹配拦截
	* @author 鲁晓飞 MR.Lu   
	* @date 2017年8月14日 上午9:15:40 
	* @version 网校+CRM系统 V1.0
	 */
	public static boolean get_ip_word(String ip){
		boolean bol=false;
		String [] IPS={"124.232.153.54","113.223.31.128","103.44.88.74","124.232.153.150","182.139.206.43",
				"103.45.13.34","123.151.138.59"};
		for (String string : IPS) {
			if(ip.equals(string)){
				bol=true;
				break;
			}else{
				bol=false;
			}
		}
		return bol;
	}
}
