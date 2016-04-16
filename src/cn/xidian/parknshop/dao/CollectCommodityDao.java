package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.CollectCommodity;
import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.User;

public interface CollectCommodityDao {
	void add(CollectCommodity collectCommodity);
	List<CollectCommodity> findCollectCommodityByUser(User user);
	CollectCommodity findCollectCommodityByUserAndCommodity(User user, Commodity commodity);
}
