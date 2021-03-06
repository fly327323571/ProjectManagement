package cn.xidian.parknshop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Advertisement;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.dao.ShopDao;
import cn.xidian.parknshop.service.ShopService;
import cn.xidian.parknshop.utils.HomePageAdvHelper;

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

	
	@Override
	public List<Shop> findShopByShopName(String shopname) {
		// TODO Auto-generated method stub
		return shopDao.findShopByShopName(shopname);
	}

	@Override
	public List<Shop> findShopsBySomeFilter(Map<String, String> filter, String userName) {
		// TODO Auto-generated method stub
		return shopDao.findShopsBySomeFilter(filter, userName);
	}

	@Override
	public List<Shop> findOtherShopsBySomeFilter(Map<String, String> filter, long shopNo,String userName) {
		// TODO Auto-generated method stub
		return shopDao.findOtherShopsBySomeFilter(filter, shopNo,userName);
	}

	@Override
	public List<Advertisement> findCommodityAdvertise(long shopNo) {
		// TODO Auto-generated method stub
		return shopDao.findCommodityAdvertise(shopNo);
	}

	@Override
	public boolean checkHomePageAd(long shopNo) {
		// TODO Auto-generated method stub
		return shopDao.checkHomePageAd(shopNo);
	}

	@Override
	public HomePageShopAds findHomePageShopAdByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		return shopDao.findHomePageShopAdByShopNo(shopNo);
	}

	@Override
	public List<HomePageShopAds> findHomePageShopAds() {
		// TODO Auto-generated method stub
		return shopDao.findHomePageShopAds();
	}

	@Override
	public List<HomePageAdvHelper> findCurHomePageAdByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		return shopDao.findCurHomePageAdByShopNo(shopNo);
	}
}