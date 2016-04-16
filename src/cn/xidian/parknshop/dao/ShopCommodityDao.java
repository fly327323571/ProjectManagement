package cn.xidian.parknshop.dao;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.HomePageCommodityAds;
import cn.xidian.parknshop.beans.Order;

public interface ShopCommodityDao {

	boolean checkCommName(String commName,long shopNo);
	
	List<Commodity> findCommodityByShopNo(long shopNo);
	
	Commodity findCommodityByCommNo(long commNo);
	
	List<Commodity> findCommodityByFilters(Map<String, String> filters, long shopNo);
	
	List<HomePageCommodityAds> findHomePageCommodityAds();
	
	List<Commodity> findNotAdvCommodity(long shopNo);
	
	void updateOrderByDeleteCommodity(Commodity commodity);
}
