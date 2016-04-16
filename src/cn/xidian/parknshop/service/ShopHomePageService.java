package cn.xidian.parknshop.service;

import java.util.List;

import cn.xidian.parknshop.beans.ShopLinks;


public interface ShopHomePageService {
	 
	List<ShopLinks> findShopLinkByShopNo(long shopNo);
}
