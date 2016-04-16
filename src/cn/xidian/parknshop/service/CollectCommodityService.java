package cn.xidian.parknshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xidian.parknshop.beans.CollectCommodity;
import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.User;

@Service
@Transactional
public interface CollectCommodityService {
	void add(CollectCommodity collectCommodity);
	List<CollectCommodity> findCollectCommodityByUser(User user);
	CollectCommodity findCollectCommodityByUserAndCommodity(User user, Commodity commodity);
}
