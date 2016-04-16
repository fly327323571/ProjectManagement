package cn.xidian.parknshop.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.CollectCommodity;
import cn.xidian.parknshop.beans.CollectShop;
import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.Express;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.CollectCommodityService;
import cn.xidian.parknshop.service.CollectShopService;
import cn.xidian.parknshop.service.CommentsService;
import cn.xidian.parknshop.service.ExpressService;
import cn.xidian.parknshop.service.OrderService;
import cn.xidian.parknshop.service.ShareService;
import cn.xidian.parknshop.service.ShopCommodityService;
import cn.xidian.parknshop.service.ShopService;
import cn.xidian.parknshop.service.OrderDetailService;

@Controller
public class CustomerController {

	@Resource(name="baseService")
	private BaseService<User> customerBaseService;
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@Resource(name="OrderDetailService")
	private OrderDetailService orderDetailService;
	
	@Resource(name="expressService")
	private ExpressService expressService;
	
	@Resource(name="commentsService")
	private CommentsService commentsService;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	@Resource(name="shareService")
	private ShareService shareService;
	
	@Resource(name="collectShopService")
	private CollectShopService collectShopService;
	
	@Resource(name="shopCommodityService")
	private ShopCommodityService shopCommodityService;
	
	@Resource(name="collectCommodityService")
	private CollectCommodityService collectCommodityService;
	
	@RequestMapping("/customer/customerHomePage")
	public String customerHomePage(){
		
		return "../views/customer/customerHomepage";
	}
	
	@RequestMapping("/customer/orderDetails")
	public String showOrderDetails(HttpServletRequest request, Model model) {
		String name = "11111";
		User user = findLoginUser(request);
		if (user != null) {
			name = user.getUserName();
		}
		List<Order> orderList = orderService.findOrdersByName(name);
		model.addAttribute("orderList", orderList);
		
		return "../views/customer/customerBuyHistory";
	}
	
	private User findLoginUser(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		
		return user;
	}
	
	@RequestMapping("/customer/confirmOrders")
	public String confirmOrders(HttpServletRequest request, Integer isInner, Model model) {
		String name = null;
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			name = user.getUserName();	
		}	
		List<Order> orderList = orderService.findOrdersWithUnpayByName(name);
		double sumPrice = 0;
		int count = 0;
		for (Order order : orderList) {
			sumPrice += order.getOrderPrice();
			count++;
		}
		model.addAttribute("orderList", orderList);
		model.addAttribute("sumPrice", sumPrice);
		model.addAttribute("orderCount", count);
		
		if (isInner == null) isInner = 0;
		model.addAttribute("isInner", isInner);
		
		return "../views/customer/confirmOrder";
	}
	
	@RequestMapping("/customer/redirectToPay")
	public String redirectToPay(int orderCount, Integer isInner, Model model) {
		model.addAttribute("orderCount", orderCount);
		
		if (isInner == null) isInner = 0;
		model.addAttribute("isInner", isInner);
		
		return "../views/customer/pay";
	}
	
	@RequestMapping("/customer/pay")
	public String pay(HttpServletRequest request,int orderCount, Integer isInner, Model model){
		final int PAID = 2;
		
		String name = null;
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			name = user.getUserName();
		
			List<Order> orderList = orderService.findOrdersWithUnpayByName(name);
			
			double sumPrice = 0;
			int count = 0;
			for (Order order : orderList) {
				count++;
				sumPrice += order.getOrderPrice();
				order.setState(PAID);
				orderService.updateOrder(order);
			}
			if(count != orderCount) {
				model.addAttribute("operationSucceed", "Pay failed!Check the order's status,please!");
				return "../views/customer/operationSucceed";
			}
			model.addAttribute("sumPrice", sumPrice);
		}
		
		if (isInner == null) isInner = 0;
		model.addAttribute("isInner", isInner);
		
		return "../views/customer/paySuccess";
	}
	
	@RequestMapping("/customer/moreOrderDetail")
	public String showMoreOrderDetail(long orderId, Model model){
		Order order = (Order) orderService.getOrder(orderId);
		
		List<OrderDetail> orderDetails = orderDetailService.getDetailByOrderNo(order.getOrderNo());
		Express express = expressService.getExpressByOrderNo(order.getOrderNo());
		
		model.addAttribute("orderDetails", orderDetails);
		model.addAttribute("order", order);
		model.addAttribute("express", express);
		
		return "../views/customer/viewOrderDetails";
	}
	
	@RequestMapping("/customer/comment")
	public String comment(long orderDetailId, Model model){
		OrderDetail orderDetail = orderDetailService.getOrderDetail(orderDetailId);
		Order order = orderDetail.getOrder();
		Commodity commodity = orderDetail.getCommodity();
		Shop shop = order.getShop();
		User user = order.getBuyer();
		
		model.addAttribute("commodity", commodity);
		model.addAttribute("shop", shop);
		model.addAttribute("buyer", user);
		model.addAttribute("orderDetailId", orderDetailId);
		
		return "../views/customer/comment";
	}
	
	@RequestMapping("/customer/commentSubmit")
	public String commentSubmit(long orderDetailId, Comments comment, Model model) {
		OrderDetail orderDetail = orderDetailService.getOrderDetail(orderDetailId);
		Order order = orderDetail.getOrder();
		Commodity commodity = orderDetail.getCommodity();
		Shop shop = order.getShop();
		User user = order.getBuyer();
		
		if (orderDetail != null) {
			comment.setShop(shop);
			comment.setUser(user);
			comment.setCommodity(commodity);
			comment.setCommentsTime(new Date());
		}
		commentsService.add(comment);
		
		String commentSuccess = "You have commented the product successfully";
		model.addAttribute("operationSucceed", commentSuccess);
		
		return "../views/customer/operationSucceed";
	}
	
	@RequestMapping("/customer/signFor")
	public String signFor(long orderId, Model model) {
		Order order = (Order) orderService.getOrder(orderId);
		
		order.setState(7);
		orderService.updateOrder(order);
		
//		
//		Share share = new Share();
//		share.setRate(12);	
//		double orderPrice = order.getOrderPrice();
//		double shareMoney = orderPrice * (share.getRate() / 100);
//		share.setTurnover(orderPrice);
//		share.setShareMoney(shareMoney);
//		share.setShareTime(new Date());	
//		share.setShop(order.getShop());
//		shareService.add(share);
//		
		
		String signForSuccess = "You have sign for successfully";
		model.addAttribute("operationSucceed", signForSuccess);
		
		return "../views/customer/operationSucceed";
	}
	
	@RequestMapping("/customer/addFavoriteShop")
	public String addFavoriteShop(HttpServletRequest request, long shopNo, Model model) {
		Shop shop = shopService.findShopByShopNo(shopNo);
		User user = findLoginUser(request);
			
		if (collectShopService.findCollectShopByUserAndShop(user, shop) == null) {
			CollectShop collectShop = new CollectShop();
			collectShop.setAddTime(new Date());
			collectShop.setShop(shop);
			if (user != null) {
				collectShop.setUser(user);
			}
			collectShopService.add(collectShop);
		}
		
		return "False";
	}
	
	@RequestMapping("/customer/addFavoriteCommidity")
	public String addFavoriteCommidity(HttpServletRequest request, long commodityNo, Model model) {
		Commodity commodity = shopCommodityService.findCommodityByCommNo(commodityNo);
		User user = findLoginUser(request);
		
		if (collectCommodityService.findCollectCommodityByUserAndCommodity(user, commodity) == null) {
			CollectCommodity collectCommodity = new CollectCommodity();
			collectCommodity.setAddTime(new Date());
			collectCommodity.setCommodity(commodity);
			if (user != null) collectCommodity.setUser(user);
			collectCommodityService.add(collectCommodity);
		}
			
		
		return "False";
	}
	
	@RequestMapping("/customer/myFavorite")
	public String myFavorite(HttpServletRequest request, Model model) {
		User user = findLoginUser(request);
		List<CollectCommodity> collectCommodityList = collectCommodityService.findCollectCommodityByUser(user);
		List<CollectShop> collectShopList = collectShopService.findCollectShopByUser(user); 
		
		model.addAttribute("collectCommodityList", collectCommodityList);
		model.addAttribute("collectShopList", collectShopList);
		
		return "../views/customer/MyFavorite";
	}
	
}