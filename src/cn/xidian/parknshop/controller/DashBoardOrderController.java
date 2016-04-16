package cn.xidian.parknshop.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.service.OrderDetailService;
import cn.xidian.parknshop.service.OrderService;
import cn.xidian.parknshop.utils.DictionaryUtils.OrderStatus;
import cn.xidian.parknshop.utils.OrderHistoryHelper;

@Controller
public class DashBoardOrderController {

	@Resource(name="orderService")
	private OrderService orderService;
	
	@Resource(name="OrderDetailService")
	private OrderDetailService orderDetailService;
	
	private static Logger log=Logger.getLogger(DashBoardOrderController.class);
	
	@RequestMapping("business/listOrderHistory/{shopNo}/")
	public @ResponseBody Map<String,ResultType> showOrderHistory(@PathVariable long shopNo,HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Map<String,String> QueryParamMap=new HashMap<String,String>();
		String pageIndex=request.getParameter("page[pageIndex]");
		String pageSize=request.getParameter("page[pageSize]");
		String columnFilterName_0=request.getParameter("columnFilters[0][name]");
		String columnFilterName_1=request.getParameter("columnFilters[1][name]");
		String columnFilterValue_0=request.getParameter("columnFilters[0][value][]");
		String columnFilterValue_1=request.getParameter("columnFilters[1][value][]");
		String columnFilterName_2=request.getParameter("columnFilters[2][name]");
		String columnFilterValue_2=request.getParameter("columnFilters[2][value][]");
		QueryParamMap.put("pageIndex", pageIndex);
		QueryParamMap.put("pageSize", pageSize);
		QueryParamMap.put(columnFilterName_0, columnFilterValue_0);
		QueryParamMap.put(columnFilterName_1, columnFilterValue_1);
		QueryParamMap.put(columnFilterName_2, columnFilterValue_2);
		List<OrderHistoryHelper> orders=null;
		try{
			orders=orderService.findOrderHistoryByShopNo(QueryParamMap, shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(orders);
		map.put("result", resultType);
		return map;
		
	}
	
	@RequestMapping("business/viewOrderDetails/{orderNo}")
	public ModelAndView viewOrderDetails(@PathVariable long orderNo,Model model){
		Order order=null;
		try{
			order=orderService.findOrderByOrderNo(orderNo);
		}
		catch(Exception e){
			log.error(e);
			return new ModelAndView("../views/error");
		}
		List<OrderDetail> orderDetails=null;
		try{
			orderDetails=orderDetailService.getDetailByOrderNo(orderNo);
		}
		catch(Exception e){
			log.error(e);
			return new ModelAndView("../views/error");
		}
		model.addAttribute("order", order);
		model.addAttribute("orderDetails", orderDetails);
		return new ModelAndView("../views/customer/viewOrderDetails");
		
	 }
	
	@RequestMapping("business/refund/{orderNo}")
	public @ResponseBody Map<String,ResultType> refund(@PathVariable long orderNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		try{
			orderService.updateOrderStatus(OrderStatus.refunded, orderNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db Busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("Success");
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("business/rejectHandle/{orderNo}")
	public @ResponseBody Map<String,ResultType> agree(@PathVariable long orderNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		try{
			orderService.updateOrderStatus(OrderStatus.refundRejected, orderNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db Busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("Success");
		map.put("result", resultType);
		return map;
	}
	
}
