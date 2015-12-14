package cn.xidian.parknshop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.service.BaseService;

@Controller
public class ApplyShopController {

	@Resource(name="baseService")
	private BaseService<Shop> shopService;
	
	public void applyShop(Shop shop){
		shopService.create(shop);
	}

	public BaseService<Shop> getShopService() {
		return shopService;
	}

	public void setShopService(BaseService<Shop> shopService) {
		this.shopService = shopService;
	}

}
