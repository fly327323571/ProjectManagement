package cn.xidian.parknshop.dao;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Shop;

public interface ShopDao {

	boolean checkShopName(String shopName);
	
	List<Shop> findShopByUserName(String userName);
	
	Shop findShopByShopNo(long shopNo);
	
	List<Shop> findShopsBySomeFilter(Map<String,String> filter,String userName);
	
	List<Shop> findOtherShopsBySomeFilter(Map<String,String>filter,long shopNo);
	
	List<Shop> findShopByShopName(String shopname);
}
