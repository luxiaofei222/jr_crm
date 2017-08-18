package com.jingren.jing.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
/**
 * 
 * @author 修改代码之前请联系 郝明明
 *
 * 2015年7月17日下午5:21:31
 */
public class MyRequestWrapper extends HttpServletRequestWrapper{
	
	public MyRequestWrapper(HttpServletRequest request) {
		
		super(request);
	}
	public String[] getParameterValues(String str) {
		String[] param = super.getParameterValues(str);
		return toHTML(param);
	}
	public String[] toHTML(String[] value) {
		
		for(int i=0;i<value.length;i++){
			if (value[i] == null)		value[i] = "";
			value[i] = value[i].replaceAll("&", "&amp;");
			value[i] = value[i].replaceAll("<", "&lt;");
			value[i] = value[i].replaceAll(">", "&gt;");
			value[i] = value[i].replaceAll("\"", "&quot;");
			value[i] = value[i].replaceAll(" ", "&nbsp;");
			value[i] = value[i].replaceAll("\n", "<br>");
		}
		return value;
	}

}
