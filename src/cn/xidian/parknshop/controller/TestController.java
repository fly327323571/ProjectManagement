package cn.xidian.parknshop.controller;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.*;
import cn.xidian.parknshop.dao.CartDao;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.ShopCommodityService;
import cn.xidian.parknshop.service.TestService;
import cn.xidian.parknshop.utils.DictionaryUtils.OrderStatus;
import redis.clients.jedis.Jedis;

import org.apache.log4j.*;

@Controller
public class TestController {
	@Resource(name="testService")
	private TestService testService;
	
	@Resource(name="baseService")
	private BaseService<Cart> baseService;
	
	@Resource(name="baseService")
	private BaseService<OrderDetail> baseOrderDetail;
	
	@Resource(name="baseService")
	private BaseService<Order> baseOrder;
	
	
	@Resource(name="baseService")
	private BaseService<Commodity> baseCommodityService;
	
	@Resource(name="shopCommodityService")
	private ShopCommodityService shopCommodityService;
	
	private static Logger log=Logger.getLogger(TestController.class);
		
	@RequestMapping("/cart/add")
	public @ResponseBody Map<String,ResultType> addCart(long productId,double price,int quantity,HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		try{
			testService.addCart(productId,price*quantity,quantity,new Date(),((User)request.getSession().getAttribute("user")).getUserName());}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
		}
		resultType.success().setResult("add Success");
		map.put("result", resultType);
		return map;
	}
	
	/*
	@RequestMapping("/search/cusSearch")
	public String search(String query,Model model){
		List colist = testService.searchPro(query);
			model.addAttribute("page", colist);
			return "../views/customer/searchResult";
	}
	*/
	/* use Json not this
	@RequestMapping("/search/updateData")
	public String updateData(String type,Model model){
		List colist = testService.searchPro(type);
		System.out.println(type);
		if(colist.isEmpty())
		{
			return "../views/error";
		}else{
			model.addAttribute("productList", colist);
			return "../views/customer/searchResult";
		}
	}*/
	
	
	@RequestMapping("cart/addSuccess.do")
	public String addSuccess(Model model,HttpServletRequest request){
		
		Cart cart=null;
		try{
			cart=testService.findLastAddCart(((User)request.getSession().getAttribute("user")).getUserName());
		}
		catch(Exception e){
			log.error(e);
			return "../views/error";
		}
		model.addAttribute("cart", cart);
		return "../views/customer/addCartSuccess";
	}
	
	@RequestMapping("/cart/myCart")
	public String toCart(){
		return "../views/customer/myCart";
	}
	
	@RequestMapping("/cart/delete")
	public @ResponseBody Map<String,ResultType> deleteCart(long cartId){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Cart cart=null;
		try{
			cart=baseService.get(cartId, Cart.class);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		try{
			baseService.delete(cart);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("OK");
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("business/buyNow")
	public @ResponseBody Map<String,ResultType> buyNow(long productId,double price,int quantity,
														HttpServletRequest request,String toAddr){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		String toAddress;
		ResultType resultType=new ResultType();
//		Express express=new Express();
		User user=(User) request.getSession().getAttribute("user");
		Commodity commodity=null;
		try{
			commodity=shopCommodityService.findCommodityByCommNo(productId);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		if(toAddr==null)
			toAddress=user.getAddress();
		else{
			toAddress=toAddr;
		}
		Order order=new Order();
		OrderDetail orderDetail=new OrderDetail();
		order.setAddTime(new Date());
		order.setOrderNo(System.nanoTime());
		order.setPayWay(0);
		order.setDeliveryStatus(2);
		order.setState(OrderStatus.Unpaid.getOrderStatusIntValue());
		order.setBuyer(user);
		order.setSeller(commodity.getShop().getShopOwner());
		order.setOrderPrice(price*quantity);
		order.setToAddr(toAddress);
		order.setShop(commodity.getShop());
		orderDetail.setCommodity(commodity);
		orderDetail.setOrder(order);
		orderDetail.setCount(quantity);
		orderDetail.setPrice(price);
		try{
			baseOrder.create(order);
			baseOrderDetail.create(orderDetail);
			commodity.setCommodityCount(commodity.getCommodityCount()-quantity);
			baseCommodityService.update(commodity);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB Busy!");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("success");
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("cart/modify")
	public @ResponseBody Map<String,ResultType> modifyCart(long cartId,int quantity){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Cart cart=null;
		try{
			cart=baseService.get(cartId, Cart.class);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		cart.setCommodityNum(quantity);
		cart.setPrice(cart.getCommodity().getCommodityPrice()*quantity);
		try{
			baseService.update(cart);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("OK");
		map.put("result", resultType);
		return map;
		
	}

	@RequestMapping("cart/checkOut")
	public @ResponseBody Map<String,ResultType> checkOut(String cartIds,double totalPrice){
		String[] cartIdArray=cartIds.split(",");
		long[] cartIdsLongArray=new long[cartIdArray.length];
		for(int i=0;i<cartIdArray.length;i++){
			cartIdsLongArray[i]=Long.valueOf(cartIdArray[i]);
		}
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
//		Express express=new Express();
		Order order=new Order();
		OrderDetail orderDetail=new OrderDetail();
		try{
			for(int i=0;i<cartIdsLongArray.length;i++){
				Cart cart=baseService.get(cartIdsLongArray[i], Cart.class);
				order.setAddTime(new Date());
				order.setOrderNo(System.nanoTime());
				order.setPayWay(0);
				order.setDeliveryStatus(3);
				order.setState(OrderStatus.Unpaid.getOrderStatusIntValue());
				order.setBuyer(cart.getOwner());
				order.setSeller(cart.getCommodity().getShop().getShopOwner());
				order.setOrderPrice(cart.getPrice());
				order.setToAddr(cart.getOwner().getAddress());
				order.setShop(cart.getCommodity().getShop());
		
				orderDetail.setCommodity(cart.getCommodity());
				orderDetail.setOrder(order);
				orderDetail.setCount(cart.getCommodityNum());
				orderDetail.setPrice(cart.getCommodity().getCommodityPrice());
				cart.getCommodity().setCommodityCount(cart.getCommodity().getCommodityCount()-orderDetail.getCount());
				baseOrder.create(order);
				baseOrderDetail.create(orderDetail);
				cart.setStatus(1);
				baseService.update(cart);
				baseCommodityService.update(cart.getCommodity());
			}
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("OK");
		map.put("result", resultType);
		return map;
		
	}
	
	@RequestMapping("cart/list")
	public @ResponseBody Map<String,ResultType> findCart(HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		User user=(User) request.getSession().getAttribute("user");
		Map<String,String> QueryParamMap=new HashMap<String,String>();
		String pageIndex=request.getParameter("page[pageIndex]");
		String pageSize=request.getParameter("page[pageSize]");
		String orderFilters=request.getParameter("orderFilters[0][name]");
		String isAsc=request.getParameter("orderFilters[0][isAscending]");
		QueryParamMap.put("pageIndex", pageIndex);
		QueryParamMap.put("pageSize", pageSize);
		QueryParamMap.put("orderFilters", orderFilters);
		QueryParamMap.put("isAsc", isAsc);
		List<Cart> cartItems=null;
		try{
			cartItems=testService.findCartByUserName(user.getUserName(), QueryParamMap);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(cartItems);
		map.put("result", resultType);
		return map;
		
	}
	
	@RequestMapping("/user/productShow")
	public String search1(){
		return "../views/customer/productShow";
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}


}
