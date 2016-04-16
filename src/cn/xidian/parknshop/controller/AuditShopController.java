package cn.xidian.parknshop.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.xidian.parknshop.service.AuditShopService;



@Controller
@RequestMapping("/admin")
public class AuditShopController {
	
	@Resource(name="auditshopserviceimpl")
	private AuditShopService auditShopService;
	
	@RequestMapping("/registerShopInfo")
	public String ShopName(HttpServletRequest request, HttpServletResponse response, Model model){
		List<Map<String, Object>> list = auditShopService.getShopInfo();
		model.addAttribute("shopInfo", list);
		return "../views/admin/shopOwnerManage";
	}
	
	@RequestMapping("/shopInfoByRegistTime")
	public String ShopInfoByRegistTime(HttpServletRequest request, HttpServletResponse response, Model model){
		List<Map<String, Object>> list = auditShopService.getShopInfoByRegistTime();
		model.addAttribute("shopInfo", list);
		return "../views/admin/shopOwnerManage";
	}
	
	@RequestMapping("/approve")
	public String Approve(HttpServletRequest request, HttpServletResponse response, Model model, String id){
		System.out.println("Id:"+id);
		@SuppressWarnings("unused")
		int number = auditShopService.updateApproveShop(Long.valueOf(id));
		return "redirect:/admin/registerShopInfo";
	}
	
	@RequestMapping("disapprove")
	public String Disapprove(HttpServletRequest request, HttpServletResponse response, Model model, String id){
		auditShopService.updateDisapproveShop(Long.valueOf(id));
		return "redirect:/admin/registerShopInfo";
	}
	
	@RequestMapping("shopInfoByStatus")
	public String shopInfoByStatus(HttpServletRequest request, HttpServletResponse response, Model model, String status){
		int number_status;
		if ("Applying".equals(status)) {
			number_status=0;
		}else if ("Pass".equals(status)) {
			number_status=1;
		}else if ("NotPass".equals(status)) {
			number_status=2;
		}else if ("Shut".equals(status)) {
			number_status=3;
		}else if ("Warn".equals(status)) {
			number_status=4;
		}else {
			number_status=-1;
		}
		List<Map<String, Object>> list = auditShopService.getShopInfoByStatus(number_status);
		model.addAttribute("shopInfo", list);
		
		return "../views/admin/shopOwnerManage";
	}
	
	@RequestMapping("shopInfoByReaserch")
	public String shopInfoByReaserch(HttpServletResponse response, HttpServletRequest request, Model model){
		String userName = request.getParameter("userName");
		List<Map<String, Object>> list = auditShopService.shopInfoByReaserch(userName);
		model.addAttribute("shopInfo", list);
		return "../views/admin/shopOwnerManage";
	}
}
