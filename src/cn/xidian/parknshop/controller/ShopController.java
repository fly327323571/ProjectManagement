package cn.xidian.parknshop.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.utils.DictionaryUtils;

@Controller
public class ShopController {

	@Resource(name="baseService")
	private BaseService<Shop> shopService;
	
	@Resource(name="baseService")
	private BaseService<User> userService;
	
	@RequestMapping("/shop/index")
	public String applyOrShowShops(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "/user/login";
		}
		else if(user.isSeller()){
			return "../views/shopOwner/shopList";
		}
		else{
			return "../views/shopOwner/shopRegister";
		}
	}
	
	@RequestMapping("/shop/register")
	public String readyToRegShop(Model model){
		List<String> categories=new ArrayList<String>(); 
		List<String> sourceTypes=new ArrayList<String>();
		for(DictionaryUtils.ShopCategory i:DictionaryUtils.ShopCategory.values()){
			categories.add(i.toString());
		}
		for(DictionaryUtils.ShopSourceType i:DictionaryUtils.ShopSourceType.values()){
			sourceTypes.add(i.toString());
		}
		model.addAttribute("categories",categories);
		model.addAttribute("sourceTypes",sourceTypes);
		return "../views/shopOwner/shopRegister";
	}
	
	@RequestMapping("/shop/apply")
	public String applyShop(Shop shop,HttpSession session){
		User shopOwner =(User)session.getAttribute("user");
		shop.setShopOwner(shopOwner);
		shop.setOwnerTel(shopOwner.getTel());
		shop.setShopNo(System.nanoTime());
		return "waitResolve";
	}
	

	public BaseService<Shop> getShopService() {
		return shopService;
	}

	public void setShopService(BaseService<Shop> shopService) {
		this.shopService = shopService;
	}

	public BaseService<User> getUserService() {
		return userService;
	}

	public void setUserService(BaseService<User> userService) {
		this.userService = userService;
	}

}
