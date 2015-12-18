package cn.xidian.parknshop.service;

import java.util.*;

import cn.xidian.parknshop.beans.Commodity;

public interface ShopCommodityService {

	boolean checkCommName(String commName,long shopNo);
	
	List<Commodity> findCommByShopNo(long shopNo);
}
