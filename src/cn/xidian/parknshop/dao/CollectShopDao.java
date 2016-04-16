package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.CollectShop;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;

public interface CollectShopDao {
	void add(CollectShop collectSHop);
	List<CollectShop> findCollectShopByUser(User user);
	CollectShop findCollectShopByUserAndShop(User user, Shop shop);
}
