package com.jingren.jing.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author 修改代码之前请联系 郝明明
 *
 * 2015年7月17日下午5:21:39
 */
public class ReplaceFilter extends BaseFilter{
	@Override  
    public void init() throws ServletException {//初始化
        FilterConfig config = getConfig();  
    }  
      
      
    @Override  
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)  
        throws IOException, ServletException {  
    	
		chain.doFilter(new MyRequestWrapper(request), response);
    }  
      
    @Override  
    public void destroy() {//销毁
    }

}
