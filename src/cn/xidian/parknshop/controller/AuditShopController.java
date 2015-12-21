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
		return "../views/admin/shopManage";
	}
	
	@RequestMapping("/approve")
	public String Approve(HttpServletRequest request, HttpServletResponse response, Model model, String id){
		System.out.println(Long.valueOf(id));
		int number = auditShopService.updateApproveShop(Long.valueOf(id));
		System.out.println(number);
		return "redirect:/admin/registerShopInfo";
	}
	
	@RequestMapping("disapprove")
	public String Disapprove(HttpServletRequest request, HttpServletResponse response, Model model, String id){
		int number = auditShopService.updateDisapproveShop(Long.valueOf(id));
		return "redirect:/admin/registerShopInfo";
	}
}
