package cn.xidian.parknshop.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.xidian.parknshop.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.*;

public class CommonInterceptor extends  HandlerInterceptorAdapter {

	private static Logger log=Logger.getLogger(CommonInterceptor.class);
	
	@Override    
    public boolean preHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler) throws Exception {
		Object user = request.getSession().getAttribute("user");
		if(user==null){
			response.sendRedirect("views/user/login.jsp"); 
			return false;
		}
			return true;    
		
    }    
    
    /**  
     * 在业务处理器处理请求执行完成后,生成视图之前执行的动作    
     * 可在modelAndView中加入数据，比如当前时间 
     */  
    @Override    
    public void postHandle(HttpServletRequest request,    
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {     
        log.info("==============执行顺序: 2、postHandle================");    
        if(modelAndView != null){  //加入当前时间    
            modelAndView.addObject("var", "测试postHandle");    
        }    
    }    
    
    /**  
     * 在DispatcherServlet完全处理完请求后被调用,可用于清理资源等   
     *   
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()  
     */    
    @Override    
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)    
            throws Exception {    
        log.info("==============执行顺序: 3、afterCompletion================");    
    }    
  
	
}
