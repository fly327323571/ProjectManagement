package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.dao.ShopDao;
import cn.xidian.parknshop.service.ShopService;

@Service("shopService")
public class ShopServiceImpl implements ShopService {

	@Resource(name="shopDao")
	private ShopDao shopDao;
	
	@Override
	public boolean checkShopName(String shopName) {
		// TODO Auto-generated method stub
		return shopDao.checkShopName(shopName);
	}

	@Override
	public List<Shop> findShopByUserName(String userName) {
		// TODO Auto-generated method stub
		return shopDao.findShopByUserName(userName);
	}

	@Override
	public Shop findShopByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		return shopDao.findShopByShopNo(shopNo);
	}

}