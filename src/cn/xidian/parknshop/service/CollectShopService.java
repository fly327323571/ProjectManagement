package cn.xidian.parknshop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xidian.parknshop.beans.CollectShop;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;

@Service
@Transactional
public interface CollectShopService {
	void add(CollectShop collectShop);
	List<CollectShop> findCollectShopByUser(User user);
	CollectShop findCollectShopByUserAndShop(User user, Shop shop);
}
