package cn.xidian.parknshop.dao;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Commodity;

public interface ShopCommodityDao {

	boolean checkCommName(String commName,long shopNo);
	
	List<Commodity> findCommodityByShopNo(long shopNo);
	
	Commodity findCommodityByCommNo(long commNo);

	List<Commodity> findCommodityByFilters(Map<String, String> filters, long shopNo);
}
