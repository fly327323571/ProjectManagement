package cn.xidian.parknshop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.HomePageCommodityAds;
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

	@Override
	public Commodity findCommodityByCommNo(long commNo) {
		// TODO Auto-generated method stub
		return shopCommodityDao.findCommodityByCommNo(commNo);
	}

	@Override
	public List<Commodity> findCommodityByFilters(Map<String, String> filters, long shopNo) {
		// TODO Auto-generated method stub
		return shopCommodityDao.findCommodityByFilters(filters, shopNo);
	}

	@Override
	public List<HomePageCommodityAds> findHomePageCommodityAds() {
		// TODO Auto-generated method stub
		return shopCommodityDao.findHomePageCommodityAds();
	}

	@Override
	public List<Commodity> findNotAdvCommodity(long shopNo) {
		// TODO Auto-generated method stub
		return shopCommodityDao.findNotAdvCommodity(shopNo);
	}

	@Override
	public void updateOrderByDeleteCommodity(Commodity commodity) {
		// TODO Auto-generated method stub
		shopCommodityDao.updateOrderByDeleteCommodity(commodity);
	}
	
	

}