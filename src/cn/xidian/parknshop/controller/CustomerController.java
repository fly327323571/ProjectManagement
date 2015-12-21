package cn.xidian.parknshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.OrderService;
import cn.xidian.parknshop.service.ShopService;
import cn.xidian.parknshop.utils.DictionaryUtils;
import jxl.common.Logger;

@Controller
public class CustomerController {

	@Resource(name="baseService")
	private BaseService<User> customerBaseService;
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@RequestMapping("/customer/customerHomePage")
	public String redirctCustomerHomePage(){
		return "../views/customer/customerHomepage";
	}
	
	@RequestMapping("/customer/orderDetails")
	public String showOrderDetails(HttpServletRequest request, Model model, String buyerName) {
		/*假用户名*/
		String name = "11111";
		
		List<Order> orderList = orderService.findOrdersByName(name);
		model.addAttribute("orderList", orderList);
		
		return "../views/customer/customerBuyHistory";
	}
}