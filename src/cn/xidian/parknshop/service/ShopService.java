package cn.xidian.parknshop.service;

import java.util.List;

import cn.xidian.parknshop.beans.Shop;

public interface ShopService {

	boolean checkShopName(String shopName);
	
	List<Shop> findShopByUserName(String userName);
}
