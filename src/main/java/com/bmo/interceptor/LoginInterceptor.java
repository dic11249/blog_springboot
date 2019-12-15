package com.bmo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author Bmo  2019-12-11  
 *
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler) throws Exception {
		if(request.getSession().getAttribute("user") == null) {
			response.sendRedirect("/admin/login");
			return false;
		}
		
		return true;
	}

	
}
