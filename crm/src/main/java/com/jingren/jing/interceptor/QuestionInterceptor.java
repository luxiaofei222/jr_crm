package com.jingren.jing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jingren.jing.school.bean.user.User;

public class QuestionInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 拦截所有题库
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session=request.getSession();
		String url = "/questionindex/get_question_index.html";
		User user = (User) session.getAttribute("user_session");
		if(user!=null){
			return true;
		}else{
			session.removeAttribute("url");
			session.setAttribute("url", url);
			response.sendRedirect("/sc_login/login_page.html?jr_url="+url);
		}
		return false;
	}
}
