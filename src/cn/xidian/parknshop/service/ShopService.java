package cn.xidian.parknshop.service;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Advertisement;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.utils.HomePageAdvHelper;

public interface ShopService {

	boolean checkShopName(String shopName);
	
	List<Shop> findShopByUserName(String userName);
	
	Shop findShopByShopNo(long shopNo);
	
	List<Shop> findShopsBySomeFilter(Map<String,String> filter,String userName);

	List<Shop> findOtherShopsBySomeFilter(Map<String, String> filter,long shopNo,String userName);

	List<Shop> findShopByShopName(String shopname);
	
	List<Advertisement> findCommodityAdvertise(long shopNo);
	
	boolean checkHomePageAd(long shopNo);
	
	HomePageShopAds findHomePageShopAdByShopNo(long shopNo);
	
	List<HomePageShopAds> findHomePageShopAds();
	
	List<HomePageAdvHelper> findCurHomePageAdByShopNo(long shopNo);
}
