package cn.xidian.parknshop.service;

import java.util.ArrayList;
import java.util.List;

import cn.xidian.parknshop.beans.HomePageCommodityAds;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.Share;
import cn.xidian.parknshop.beans.ShopEarnedDetail;
import cn.xidian.parknshop.utils.CommoditySimple;
import cn.xidian.parknshop.utils.IncomeInfo;

public interface SummaryService {
	List<Order> findOrderSucess(String shopName);
	ArrayList<CommoditySimple> findTop10();
	
	int findOrderNonPayment();
	int findOrderNotRecieving();
	int findOrderRecieved();
	
	int findOrderOnlinePayment();
	int findOrderCashOnDeliery();
	
	int findCommodityOfFood();
	int findCommodityOfClothes();
	
	int findShopStateApplyed();
	int findShopApplyFailure();
	int findShopShutdown();
	int findShopWarning();
	
	ArrayList<IncomeInfo> findShouldIncomeInfo(String shopName);
	
	List<Share> findShare(String shopName);
	
	void createShare(Share share);
	
	List<HomePageCommodityAds> findNotJudgedHomePageCommodityAds(String shopName);
	List<HomePageShopAds> findNotJudgedHomePageShopAds(String shopName);
	
	void updateHomePageCommodityAds(HomePageCommodityAds homePageCommodityAds);
	void updateHomePageShopAds(HomePageShopAds homePageShopAds);
	
	HomePageCommodityAds findHomePageCommodityAdsById(long id);
	public HomePageShopAds findHomePageShopAdsById(long id);
	
	double countTotalAdvertisementIncome();
	
	List<HomePageCommodityAds> findAllActiveHomePageCommodityAds();
	
	List<HomePageShopAds> findAllActiveHomePageShopAds();
	
	void createShopEarnedDetail(ShopEarnedDetail shopEarnedDetail);
}
