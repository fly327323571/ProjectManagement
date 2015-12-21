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
	public String customerHomePage(){
		
		return "../views/customer/customerHomepage";
	}
	
	@RequestMapping("/customer/orderDetails")
	public String showOrderDetails(HttpServletRequest request, Model model) {
		/*假用户名*/
		String name = "11111";
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			name = user.getUserName();
		}
		List<Order> orderList = orderService.findOrdersByName(name);
		model.addAttribute("orderList", orderList);
		
		return "../views/customer/customerBuyHistory";
	}
	
	@RequestMapping("/customer/confirmOrders")
	public String confirmOrders(HttpServletRequest request, Model model) {
		/*假用户名*/
		String name = "11111";
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			name = user.getUserName();
		}
		List<Order> orderList = orderService.findOrdersWithUnpayByName(name);
		
		double sumPrice = 0;
		for (Order order : orderList) {
			sumPrice += order.getOrderPrice();
		}
		model.addAttribute("orderList", orderList);
		model.addAttribute("sumPrice", sumPrice);
		
		return "../views/customer/confirmOrder";
	}
	
	@RequestMapping("/customer/redirectToPay")
	public String redirectToPay(Model model) {
		
		return "../views/customer/pay";
	}
	
	@RequestMapping("/customer/pay")
	public String pay(HttpServletRequest request, Model model){
		final int RECEIVING = 1;
		
		String name = null;
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			name = user.getUserName();
		
			List<Order> orderList = orderService.findOrdersWithUnpayByName(name);
			
			double sumPrice = 0;
			for (Order order : orderList) {
				sumPrice += order.getOrderPrice();
				order.setState(RECEIVING);
				orderService.updateOrder(order);
			}
			
			model.addAttribute("sumPrice", sumPrice);
		}
		return "../views/customer/paySuccess";
	}
}