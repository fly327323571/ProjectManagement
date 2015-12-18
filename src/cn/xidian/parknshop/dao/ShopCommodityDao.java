package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.Commodity;

public interface ShopCommodityDao {

	boolean checkCommName(String commName,long shopNo);
	
	List<Commodity> findCommodityByShopNo(long shopNo);
}
