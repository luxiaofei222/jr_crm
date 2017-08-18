package com.jingren.jing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jingren.jing.school.bean.employee.Employee;


/**
 * 登录拦截器
* @Title: LoginInterceptor.java 
* @Package com.chengxin.cheng.common 
* @Description: TODO 
* @author 鲁晓飞   
* @date 2016年7月1日 上午10:40:04 
* @version V1.0
 */
public class LoginInterceptor implements HandlerInterceptor{
//	private static final String INTERCEPTOR_URL = "/admin.jr";
	private static final String LOGIN_URL = "/((login)|(login_employee)|(logout)).jr";
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session=request.getSession();
		String path = request.getServletPath();
		Employee employee=(Employee) session.getAttribute("employee_session");
		if(employee!=null){
			return true;
		}else{
			if(path.matches(LOGIN_URL)){
				return true;
			}else{
				response.sendRedirect("/reload.jsp");
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
