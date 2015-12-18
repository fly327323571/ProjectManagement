package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.dao.ShopCommodityDao;
import cn.xidian.parknshop.service.ShopCommodityService;

@Service("shopCommodityService")
public class ShopCommodityServiceImpl implements ShopCommodityService {

	@Resource(name="shopCommodityDao")
	private ShopCommodityDao shopCommodityDao;
	
	@Override
	public boolean checkCommName(String commName,long shopNo) {
		// TODO Auto-generated method stub
		return shopCommodityDao.checkCommName(commName,shopNo);
	}

	@Override
	public List<Commodity> findCommByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		return shopCommodityDao.findCommodityByShopNo(shopNo);
	}

}