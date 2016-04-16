package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.ShopLinks;

public interface ShopHomePageDao {

	 List<ShopLinks> findShopLinkByShopNo(long shopNo);
}
