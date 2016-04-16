package cn.xidian.parknshop.service;

import java.util.*;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.HomePageCommodityAds;

public interface ShopCommodityService {

	boolean checkCommName(String commName,long shopNo);
	
	List<Commodity> findCommByShopNo(long shopNo);
	
	Commodity findCommodityByCommNo(long commNo);
	
	List<Commodity> findCommodityByFilters(Map<String,String> filters,long shopNo);
	
	List<HomePageCommodityAds> findHomePageCommodityAds();
	
	List<Commodity> findNotAdvCommodity(long shopNo);
	
	void updateOrderByDeleteCommodity(Commodity commodity);
}
