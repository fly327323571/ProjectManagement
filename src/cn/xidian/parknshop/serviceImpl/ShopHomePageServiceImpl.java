package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.ShopLinks;
import cn.xidian.parknshop.dao.ShopHomePageDao;
import cn.xidian.parknshop.service.ShopHomePageService;

@Service("shopHomePageService")
public class ShopHomePageServiceImpl implements ShopHomePageService {

	@Resource(name="shopHomePageDao")
	private ShopHomePageDao shopHomePageDao;
	
	@Override
	public List<ShopLinks> findShopLinkByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		return shopHomePageDao.findShopLinkByShopNo(shopNo);
	}

}
