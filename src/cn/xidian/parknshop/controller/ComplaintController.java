package cn.xidian.parknshop.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xidian.parknshop.beans.Complaint;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.BaseService;

@Controller
public class ComplaintController {
//	@Resource(name="complaintService")
//	private ComplaintService complaintService;
	@Resource(name="baseService")	
	private BaseService<Complaint> complaintService;
	
	@Resource(name="baseService")	
	private BaseService<User> userService;
	
	@Resource(name="baseService")	
	private BaseService<Shop> shopService;
	@RequestMapping("/user/complaint")
	
	public String checkinfo(Complaint complaint,Model model,HttpServletRequest request) {
		
		User user=userService.get(1, User.class);
		Shop shop=shopService.get(1, Shop.class);
		complaint.setComplaintUser(user);
		complaint.setComplaintedShop(shop);
		complaintService.create(complaint);
		
		Complaint complaintDel=complaintService.get(2, Complaint.class);
		complaintDel.setComplaintedShop(null);
		complaintDel.setComplaintUser(null);
		complaintService.delete(complaintDel);
		List<Complaint> list=complaintService.getAll(Complaint.class);
		model.addAttribute("applyList", list);
		return "admin/handle";
		}
}


