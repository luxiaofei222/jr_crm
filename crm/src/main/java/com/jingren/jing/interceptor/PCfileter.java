package com.jingren.jing.interceptor;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import com.jingren.jing.util.CheckMobile;
import com.jingren.jing.util.GetIPUtil;
import com.jingren.jing.util.WordGuolvUtil;

/**
 * Servlet Filter implementation class PCfileter
 */
public class PCfileter implements Filter {

	private static final String LOGIN_URL = "/((upload_video)|(video)|(jrsystem/waring)).*";
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 boolean isFromMobile=false;  
		 HttpServletRequest hrequest = (HttpServletRequest)request;
		 HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
		   	//检查是否已经记录访问方式（移动端或pc端）  
	String path= hrequest.getRequestURI();
	String ip=GetIPUtil.getIpAddress(hrequest);
	if(ip!=null){
		if(WordGuolvUtil.get_ip_word(ip)){
			wrapper.sendRedirect("http://www.jingrenedu.com/jrsystem/waring.html");  
		}else{
			if(path.matches(LOGIN_URL)){
				chain.doFilter(request, response);
			}else{
				 try{  
			            //获取ua，用来判断是否为移动端访问  
			            String userAgent = hrequest.getHeader( "USER-AGENT" ).toLowerCase();    
			            if(null == userAgent){    
			                userAgent = "";    
			            }  
			           isFromMobile=CheckMobile.check(userAgent);  
			            //判断是否为移动端访问  
			            if(isFromMobile){  
			            	 wrapper.sendRedirect("http://m.jingrenedu.com");  
			            }else{
			            	chain.doFilter(request, response);
			            }
			        }catch(Exception e){
			        	e.printStackTrace();
			        }  
			}
		}
	}else{
		wrapper.sendRedirect("http://www.jingrenedu.com/jrsystem/waring.html");  
	}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
