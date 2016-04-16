package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.CollectShop;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.CollectShopDao;
import cn.xidian.parknshop.service.CollectShopService;

@Service("collectShopService")
public class CollectShopServiceImpl implements CollectShopService{

	@Resource(name="collectShopDao")
	private CollectShopDao collectShopDao;

	@Override
	public void add(CollectShop collectShop) {
		collectShopDao.add(collectShop);		
	}
	
	public List<CollectShop> findCollectShopByUser(User user) {
		return collectShopDao.findCollectShopByUser(user);
	}

	@Override
	public CollectShop findCollectShopByUserAndShop(User user, Shop shop) {
		
		return collectShopDao.findCollectShopByUserAndShop(user, shop);
	};
}
