package interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import pojo.Dev_user;
import tools.Constants;

public class SysInterceptor extends HandlerInterceptorAdapter{
	private Logger logger=Logger.getLogger(SysInterceptor.class);
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception{
		logger.debug("SysInterceptor preHandle");
		HttpSession session=request.getSession();
		Dev_user user=(Dev_user)session.getAttribute(Constants.USER_SESSION);
		if(user==null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return  false;
		}
		return true;
	}
}