package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.Shop;

public interface ShopDao {

	boolean checkShopName(String shopName);
	
	List<Shop> findShopByUserName(String userName);
}
