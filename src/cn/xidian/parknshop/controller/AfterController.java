package cn.xidian.parknshop.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xidian.parknshop.beans.AfterSaleService;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.service.AfterService;
import cn.xidian.parknshop.service.OrderDetailService;


@Controller
@RequestMapping("/afterSaleService")
public class AfterController {
	
	@Resource(name="afterService")
	private AfterService afterService;
	
	@Resource(name="OrderDetailService")
	private OrderDetailService orderDetailService;
	
	@RequestMapping("/view")
	public String allAfterService(HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> map = null;
		List<Map<String, Object>> list_view = new ArrayList<Map<String, Object>>();
		List<AfterSaleService> list = afterService.getAfterSaleServiceInfo();
		for(int i=0;i<list.size();i++){
			map = new HashMap<String, Object>();
			map.put("commodityNo", list.get(i).getCommodity().getCommodityNo());
			map.put("userName", list.get(i).getUser().getUserName());
			map.put("reason", list.get(i).getReasons());
			map.put("serviceNo", list.get(i).getServiceNo());
			map.put("serviceType", list.get(i).getServiceType());
			list_view.add(map);
		}
		model.addAttribute("map", list_view);
		afterService.updateStatus();
		return "../views/admin/AfterSaleService";
	}
	
	@RequestMapping("detail")
	public String Detail(HttpServletRequest request, HttpServletResponse response, Model model, String commodityNo, String userName){
		List<OrderDetail> list = orderDetailService.getDetailInfo(userName, Long.valueOf(commodityNo));
		Map<String, Object> map = null;
		for(int i=0;i<list.size();i++){
			map = new HashMap<String, Object>();
			map.put("orderNo", list.get(i).getOrder().getOrderNo());
			map.put("orderPrice", list.get(i).getOrder().getOrderPrice());
			map.put("payWay", list.get(i).getOrder().getPayWay());
			map.put("state", list.get(i).getOrder().getState());
			map.put("commodityName", list.get(i).getCommodity().getCommodityName());
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		mapList.add(map);
		model.addAttribute("mapList", mapList);
		return "../views/admin/orderDetail";
	}
	
	@RequestMapping("approve")
	public String Approve(HttpServletRequest request, HttpServletResponse response, Model model, String serviceNo, String serviceType){
		afterService.updateApprove(Long.valueOf(serviceNo), Integer.valueOf(serviceType));
		return "redirect:/afterSaleService/view";
	}
	
	@RequestMapping("disapprove")
	public String Disapprove(HttpServletRequest request, HttpServletResponse response, Model model, String serviceNo, String serviceType){
		afterService.updateDisapprove(Long.valueOf(serviceNo), Integer.valueOf(serviceType));
		return "redirect:/afterSaleService/view";
	}
	
	

}
