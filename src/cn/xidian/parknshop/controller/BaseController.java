package cn.xidian.parknshop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.TestService;

@Controller
public class BaseController {
	
	@Resource(name="testService")
	private TestService testService;

	private static Logger log=Logger.getLogger(BaseController.class);
	
	@RequestMapping("siteMap.do")
	public ModelAndView redirectSiteMap(){
		return new ModelAndView("../views/siteMap");
	}
	
	@RequestMapping("/user/register/customerReg")
	public String redirectReg(){
		return "../views/user/customerRegister";
		
	}
	
	@RequestMapping("/user/logout")
	public ModelAndView LogOut(HttpSession session){
		session.setAttribute("user", null);
		return new ModelAndView("../views/homepage");
	}
	
	@RequestMapping("/user/login")
	public ModelAndView redirectLogIn(){
		return new ModelAndView("../views/user/login");
	}
	
	@RequestMapping("cart/count")
	public @ResponseBody Map<String,ResultType> countCart(HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		long count=0;
		try{
			count=testService.countInCart(((User)request.getSession().getAttribute("user")).getUserName());
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(count);
		map.put("result", resultType);
		return map;
	}
}
