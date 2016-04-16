package cn.xidian.parknshop.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xidian.parknshop.beans.Admin;
import cn.xidian.parknshop.service.AdminService;
import cn.xidian.parknshop.utils.MD5Utils;

@Controller
@RequestMapping("adminLogin")
public class AdminLogin {
	@Resource(name="adminService")
	private AdminService adminService;
	
	@RequestMapping("login")
	public String adminLogin(HttpServletRequest request, HttpSession session, Model model){
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String MD_password = MD5Utils.MD5(password);
//		System.out.println("Md_password"+MD_password);
		List<Admin> admin=null;
		if (!(admin=adminService.adminLogin(username, MD_password)).isEmpty()) {
			session.setAttribute("user",admin.get(0));
			return "../views/admin/adminHomepage";
		}
		
		return "../views/admin/login";
	}
	
}
