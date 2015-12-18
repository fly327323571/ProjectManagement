package cn.xidian.parknshop.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

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
}
