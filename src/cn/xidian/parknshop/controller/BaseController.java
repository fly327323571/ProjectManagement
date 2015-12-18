package cn.xidian.parknshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

	@RequestMapping("siteMap.do")
	public ModelAndView redirectSiteMap(){
		return new ModelAndView("../views/siteMap");
	}
	
	
}
