package cn.xidian.parknshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopCommodityCotroller {

	@RequestMapping("shop/*/dashboard/index.do")
	public ModelAndView dashboardIndex(Model model){
		return new ModelAndView("../views/shop/dashboard");
	}
}
