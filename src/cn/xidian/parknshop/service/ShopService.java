package cn.xidian.parknshop.service;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Shop;

public interface ShopService {

	boolean checkShopName(String shopName);
	
	List<Shop> findShopByUserName(String userName);
	
	Shop findShopByShopNo(long shopNo);
	
	List<Shop> findShopsBySomeFilter(Map<String,String> filter);
}
