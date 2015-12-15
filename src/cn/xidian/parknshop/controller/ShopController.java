package cn.xidian.parknshop.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.BaseService;

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
			return "shop/showMyShops";
		}
		else{
			return "shop/applyShop";
		}
	}
	
	public String applyShop(Shop shop){
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
