package cn.xidian.parknshop.controller;

import javax.annotation.Resource;

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
	
	@RequestMapping("/seller/applyShop")
	public String applyShop(Shop shop){
		User shopOwner=userService.get(1, User.class);
		shop.setShopOwner(shopOwner);
		shopService.create(shop);
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
