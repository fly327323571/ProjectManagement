package cn.xidian.parknshop.service;

import java.util.*;

import cn.xidian.parknshop.beans.Commodity;

public interface ShopCommodityService {

	boolean checkCommName(String commName,long shopNo);
	
	List<Commodity> findCommByShopNo(long shopNo);
	
	Commodity findCommodityByCommNo(long commNo);
	
	List<Commodity> findCommodityByFilters(Map<String,String> filters,long shopNo);
}
