package com.jingren.jing.interceptor;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.jingren.jing.common.videokey.bean.VideoKey;
import com.jingren.jing.common.videokey.service.VideoKeyService;
import com.jingren.jing.util.CheckMobile;

/**
 * Servlet Filter implementation class PCfileter
 */
public class Videofileter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		boolean isFromMobile = false;
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);
		ServletContext sc = hrequest.getSession().getServletContext();
		XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils
				.getWebApplicationContext(sc);
		VideoKeyService videoKeyService = (VideoKeyService) cxt.getBean("videoKeyService");
		String key = (String) hrequest.getParameter("key");
		System.err.println("reqest:" + key);
		String userAgent = hrequest.getHeader("USER-AGENT").toLowerCase();
		if (null == userAgent) {
			userAgent = "";
		}
		isFromMobile = CheckMobile.check(userAgent);
		if (isFromMobile) {// 如果是移动端验证
			if (key != null) {
				Map<String, Object> map = new HashMap<>();
				map.put("video_key", key);
				VideoKey loc_key = videoKeyService.getVideoKey(map);
				if (loc_key != null) {
					long time = (new Date()).getTime() - loc_key.getVideo_time().getTime();
					if (time > 600 * 1000) {
						videoKeyService.deleteVideoKey(key);
						wrapper.sendRedirect("http://m.jingrenedu.com/course_video/get_video_error.jrm");
					} else {
						chain.doFilter(request, response);
					}
				} else {
					wrapper.sendRedirect("http://m.jingrenedu.com/course_video/get_video_error.jrm");
				}
			} else {
				wrapper.sendRedirect("http://m.jingrenedu.com/course_video/get_video_error.jrm");
			}

		} else {// 如果是pc端通过
			if (key != null) {
				Map<String, Object> map = new HashMap<>();
				map.put("video_key", key);
				VideoKey loc_key = videoKeyService.getVideoKey(map);
				if (loc_key != null) {
					long time = (new Date()).getTime() - loc_key.getVideo_time().getTime();
					if (time > 5 * 1000) {
						videoKeyService.deleteVideoKey(key);
						wrapper.sendRedirect("/sc_coursevideo/get_video_error.html");
					} else {
						chain.doFilter(request, response);
					}
				} else {
					wrapper.sendRedirect("/sc_coursevideo/get_video_error.html");
				}
			} else {
				wrapper.sendRedirect("/sc_coursevideo/get_video_error.html");
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
