package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.HomePageCommodityAds;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.beans.Share;
import cn.xidian.parknshop.beans.ShopEarnedDetail;

public interface SummaryDao {
	List<Order> findOrderSucess(String shopName);
	List<Order> findOrderSucess();
	List<OrderDetail> findOrderDetailSuccess();
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
	
	
	List<Share> findShare();
	List<Share> findShare(String shopName);
	
	void createShare(Share share);
	
	List<HomePageCommodityAds> findAllNotJudgedHomePageCommodityAds();
	List<HomePageShopAds> findAllNotJudgedHomePageShopAds();
	
	List<HomePageCommodityAds> findNotJudgedHomePageCommodityAds(String shopName);
	List<HomePageShopAds> findNotJudgedHomePageShopAds(String shopName);
	
	
	void updateHomePageCommodityAds(HomePageCommodityAds homePageCommodityAds);
	void updateHomePageShopAds(HomePageShopAds homePageShopAds);
	
	HomePageCommodityAds findHomePageCommodityAdsById(long id);
	HomePageShopAds findHomePageShopAdsById(long id);
	
	List<HomePageCommodityAds> findAllSuccessHomePageCommodityAds();
	
	List<HomePageShopAds> findAllSucessHomePageShopAds();
	
	void createShopEarnedDetail(ShopEarnedDetail shopEarnedDetail);
}