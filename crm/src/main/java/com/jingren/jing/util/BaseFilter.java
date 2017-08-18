package com.jingren.jing.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/*
 *  
 * @author 郝明明
 * 
 */  
public abstract class BaseFilter implements Filter {  
    private FilterConfig config = null;          
    private final String[] NULL_STRING_ARRAY = new String[0];  
    private final String URL_SPLIT_PATTERN = "[, ;\r\n]";
  
    private final PathMatcher pathMatcher = new AntPathMatcher();  
  
    private final Logger logger = LoggerFactory.getLogger("url.filter");  
  
    /** 
     * 白名单 
     */  
    private String[] whiteListURLs = null;  
  
    /** 
     * 黑名单 
     */  
    private String[] blackListURLs = null;  
  
    public final void init(FilterConfig config) throws ServletException {  
        this.config = config;  
        this.initConfig();  
        this.init();  
    }  
  
    /** 
     * 子类覆盖 
     *  
     * @throws ServletException 
     */  
    public void init() throws ServletException {  
  
    }  
  
    /** 
     * 1、黑名单匹配 2、白名单匹配 
     */  
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)   
        throws IOException, ServletException {  
  
        HttpServletRequest httpRequest = (HttpServletRequest) request;  
        HttpServletResponse httpResponse = (HttpServletResponse) response;  
  
        String currentURL = httpRequest.getServletPath();  
    	
        //logger.debug("url filter : current url : [{}]", currentURL);  
  
        try {
			if (isBlackURL(currentURL)) {
				
			    chain.doFilter(request, response);  
			    return;  
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}  
  
        try {
			if (isWhiteURL(currentURL,httpRequest)) {  
			    doFilter(httpRequest, httpResponse, chain);  
			    return;  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
        
        //logger.debug("url filter : no url list matches : [{}]  break", currentURL);  
        chain.doFilter(request, response);  
        return;  
    }  
  
    private boolean isWhiteURL (String currentURL,HttpServletRequest request) throws Exception{  
        for (String whiteURL : whiteListURLs) {  
            if (pathMatcher.match(whiteURL, currentURL)) { 
                //logger.debug("url filter : white url list matches : [{}] match [{}] continue", whiteURL, currentURL);  
            	String Path=request.getServletContext().getRealPath("/"); 
              File file =new File(Path  + "annexes/a.txt");
  		      Date currentTime = new Date();
              SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String dateString = formatter.format(currentTime);
  		      if(!file.exists()){
  		       file.createNewFile();
  		      }
  		      FileWriter fileWritter = new FileWriter(file,true);
  		             BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
  		             bufferWritter.write(currentURL+">"+dateString+"\n");
  		             bufferWritter.close();
                return true;  
            }  
            //logger.debug("url filter : white url list not matches : [{}] match [{}]", whiteURL, currentURL);  
        }  
        return false;  
    }  
  
    private boolean isBlackURL(String currentURL) throws Exception {  
        for (String blackURL : blackListURLs) {  
            if (pathMatcher.match(blackURL, currentURL)) {  
               // logger.debug("url filter : black url list matches : [{}] match [{}] break", blackURL, currentURL); 
            	
                return true;  
            }  
            logger.debug("url filter : black url list not matches : [{}] match [{}]", blackURL, currentURL);  
        }  
        return false;  
    }  
  
    /** 
     * 子类覆盖 
     *  
     * @param request 
     * @param response 
     * @param chain 
     * @throws IOException 
     * @throws ServletException 
     */  
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)  
            throws IOException, ServletException {  
        chain.doFilter(request, response);  
    }  
  
    /** 
     * 子类覆盖 
     */  
    public void destroy() {  
  
    }  
  
    private void initConfig() {  
        String whiteListURLStr = this.config.getInitParameter("whiteListURL");  
        whiteListURLs = strToArray(whiteListURLStr);  
  
        String blackListURLStr = this.config.getInitParameter("blackListURL");  
        blackListURLs = strToArray(blackListURLStr);  
  
    }  
  
    private String[] strToArray(String urlStr) {  
        if (urlStr == null) {  
            return NULL_STRING_ARRAY;  
        }  
        String[] urlArray = urlStr.split(URL_SPLIT_PATTERN);  
  
        List<String> urlList = new ArrayList<String>();  
  
        for (String url : urlArray) {  
            url = url.trim();  
            if (url.length() == 0) {  
                continue;  
            }  
  
            urlList.add(url);  
        }  
  
        return urlList.toArray(NULL_STRING_ARRAY);  
    }  
  
    public FilterConfig getConfig() {  
        return config;  
    }  
}  