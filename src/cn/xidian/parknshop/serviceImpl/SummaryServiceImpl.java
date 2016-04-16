package cn.xidian.parknshop.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.HomePageCommodityAds;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.beans.Share;
import cn.xidian.parknshop.beans.ShopEarnedDetail;
import cn.xidian.parknshop.dao.SummaryDao;
import cn.xidian.parknshop.service.SummaryService;
import cn.xidian.parknshop.utils.CommoditySimple;
import cn.xidian.parknshop.utils.IncomeInfo;
@Service("SummaryService")
public class SummaryServiceImpl implements SummaryService{
	@Resource(name="SummaryDao")
	private SummaryDao summaryDao;
	
	@Override
	public List<Order> findOrderSucess(String shopName) {
		if(shopName == null){
			return summaryDao.findOrderSucess();
		}else
			return summaryDao.findOrderSucess(shopName);
	}

	@Override
	public ArrayList<CommoditySimple> findTop10() {
		List<OrderDetail> list = summaryDao.findOrderDetailSuccess();
		ArrayList<CommoditySimple> array = new ArrayList<CommoditySimple>();
		int j;
		for(int i=0; i < list.size();i++){
			OrderDetail orderDetail = list.get(i);
			for(j=0; j < array.size();j++){
				if(orderDetail.getCommodity().getId() == array.get(j).getCommodityId()){
					array.get(j).setSumPrice(array.get(j).getSumPrice() + orderDetail.getPrice());
					break;
				}
			}
			if(j == array.size()){
				CommoditySimple simple = new CommoditySimple();
				simple.setCommodityId(orderDetail.getCommodity().getId());
				simple.setCommodityName(orderDetail.getCommodity().getCommodityName());
				simple.setShopId(orderDetail.getOrder().getShop().getShopId());
				simple.setShopName(orderDetail.getOrder().getShop().getShopName());
				simple.setSumPrice(orderDetail.getPrice());
				array.add(simple);
			}
		}
		list = null;
		//排序
		for(int i=0; i < array.size();i++)
			for(j=i+1; j < array.size();j++){
				if(array.get(i).getSumPrice() < array.get(j).getSumPrice()){
					CommoditySimple tmp = array.get(i);
					array.set(i, array.get(j));
					array.set(j, tmp);
				}
			}
		
		//取top10
		ArrayList<CommoditySimple> result = new ArrayList<CommoditySimple>();
		for(int i=0; i < array.size() && i < 10; i++){
			result.add(array.get(i));
		}
		
		
		return result;
	}

	@Override
	public int findOrderNonPayment() {
		// TODO Auto-generated method stub
		return summaryDao.findOrderNonPayment();
	}

	@Override
	public int findOrderNotRecieving() {
		// TODO Auto-generated method stub
		return summaryDao.findOrderNotRecieving();
	}

	@Override
	public int findOrderRecieved() {
		// TODO Auto-generated method stub
		return summaryDao.findOrderRecieved();
	}

	@Override
	public int findOrderOnlinePayment() {
		// TODO Auto-generated method stub
		return summaryDao.findOrderOnlinePayment();
	}

	@Override
	public int findOrderCashOnDeliery() {
		// TODO Auto-generated method stub
		return summaryDao.findOrderCashOnDeliery();
	}

	@Override
	public int findCommodityOfFood() {
		// TODO Auto-generated method stub
		return summaryDao.findCommodityOfFood();
	}

	@Override
	public int findCommodityOfClothes() {
		// TODO Auto-generated method stub
		return summaryDao.findCommodityOfClothes();
	}

	@Override
	public int findShopStateApplyed() {
		// TODO Auto-generated method stub
		return summaryDao.findShopStateApplyed();
	}

	@Override
	public int findShopApplyFailure() {
		// TODO Auto-generated method stub
		return summaryDao.findShopApplyFailure();
	}

	@Override
	public int findShopShutdown() {
		// TODO Auto-generated method stub
		return summaryDao.findShopShutdown();
	}

	@Override
	public int findShopWarning() {
		// TODO Auto-generated method stub
		return summaryDao.findShopWarning();
	}
	@Override
	public ArrayList<IncomeInfo> findShouldIncomeInfo(String shopName){
		List<Order> array = findOrderSucess(shopName);
		//保存所有交易值
		ArrayList<IncomeInfo> result = new ArrayList<IncomeInfo>();
		for(int i=0; i < array.size(); i++){
			int j = 0;
			for(j = 0; j < result.size();j++){
					if(array.get(i).getShop().getShopNo() == result.get(j).getShopNo()){
						result.get(j).setTransaction(result.get(j).getTransaction() + array.get(i).getOrderPrice());
						result.get(j).getArray().add(array.get(i));
						break;
					}
				}
				if(j == result.size()){
					IncomeInfo tmp = new IncomeInfo();
					tmp.setShopName(array.get(i).getShop().getShopName());
					tmp.setShopNo(array.get(i).getShop().getShopNo());
					tmp.setTransaction(array.get(i).getOrderPrice());
					tmp.setShop(array.get(i).getShop());
					tmp.getArray().add(array.get(i));
					result.add(tmp);
				}
			
		}
		
		//获取已经计算过的交易额
		HashMap<Long,Double> shareMap = new HashMap<Long,Double>();
		List<Share> share = findShare(shopName);
		for(int i=0; i < share.size(); i++){
				if(shareMap.containsKey(share.get(i).getShop().getShopNo())){
				Double tmp =shareMap.get(share.get(i).getShop().getShopNo()) + share.get(i).getTurnover();
				shareMap.put(share.get(i).getShop().getShopNo(), tmp);
				}else{//目前还不存在
					shareMap.put(share.get(i).getShop().getShopNo(),share.get(i).getTurnover());
				}
			
		}
		for(int i=0; i < result.size();i++){
			if(shareMap.containsKey(result.get(i).getShopNo())){
				double transaction = result.get(i).getTransaction();
				transaction = transaction - shareMap.get(result.get(i).getShopNo());
				result.get(i).setTransaction(transaction);
			}
		}
		for(int i=0; i < result.size();i++){
			if(result.get(i).getTransaction() <= 0){
				result.remove(i);
				i--;
			}
		}
		
		return result;
	}

	@Override
	public List<Share> findShare(String shopName) {
		if(shopName == null){
			return summaryDao.findShare();
		}else{
			return summaryDao.findShare(shopName);
		}
	}

	@Override
	public void createShare(Share share) {
		summaryDao.createShare(share);
		
	}

	@Override
	public List<HomePageCommodityAds> findNotJudgedHomePageCommodityAds(String shopName) {
		if(shopName == null){
			return summaryDao.findAllNotJudgedHomePageCommodityAds();
		}else{
			return summaryDao.findNotJudgedHomePageCommodityAds(shopName);
		}
	}

	@Override
	public List<HomePageShopAds> findNotJudgedHomePageShopAds(String shopName) {
		if(shopName == null){
			return summaryDao.findAllNotJudgedHomePageShopAds();
		}else{
			return summaryDao.findNotJudgedHomePageShopAds(shopName);
		}
	}

	@Override
	public void updateHomePageCommodityAds(HomePageCommodityAds homePageCommodityAds) {
		summaryDao.updateHomePageCommodityAds(homePageCommodityAds);
		
	}

	@Override
	public void updateHomePageShopAds(HomePageShopAds homePageShopAds) {
		summaryDao.updateHomePageShopAds(homePageShopAds);
		
	}

	@Override
	public HomePageCommodityAds findHomePageCommodityAdsById(long id) {
		return summaryDao.findHomePageCommodityAdsById(id);
	}

	@Override
	public HomePageShopAds findHomePageShopAdsById(long id) {
		return summaryDao.findHomePageShopAdsById(id);
	}

	@Override
	public double countTotalAdvertisementIncome() {
		double sum = 0.0;
		List<HomePageCommodityAds> cAd = summaryDao.findAllSuccessHomePageCommodityAds();
		for(int i=0; i < cAd.size(); i++){
			sum += cAd.get(i).getAd_rate();
		}
		List<HomePageShopAds> sAd = summaryDao.findAllSucessHomePageShopAds();
		for(int i=0; i < sAd.size(); i++){
			sum += sAd.get(i).getAd_rate();
		}
		return sum;
	}

	@Override
	public List<HomePageCommodityAds> findAllActiveHomePageCommodityAds() {
		List<HomePageCommodityAds> cAd = summaryDao.findAllSuccessHomePageCommodityAds();
		List<HomePageCommodityAds> result = new ArrayList<HomePageCommodityAds>();
		for(int i=0; i < cAd.size() ; i++){
			HomePageCommodityAds tmp = cAd.get(i);
			if(tmp.getStatus() == 1 && dateAvaliable(tmp.getStartTime(), tmp.getDays())){
				result.add(tmp);
			}
		}
		return result;
	}

	@Override
	public List<HomePageShopAds> findAllActiveHomePageShopAds() {
		List<HomePageShopAds> sAd = summaryDao.findAllSucessHomePageShopAds();
		List<HomePageShopAds> result = new ArrayList<HomePageShopAds>();
		for(int i=0; i < sAd.size(); i++){
			HomePageShopAds tmp = sAd.get(i);
			if(tmp.getStatus() == 1 && dateAvaliable(tmp.getStartTime(), tmp.getDays())){
				result.add(tmp);
			}
		}
		return result;
	}
	
	private boolean dateAvaliable(Date date,int days){
		Date now = new Date(System.currentTimeMillis());
		if((now.getTime() - date.getTime()) <= (days * 24 * 3600 * 1000)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public void createShopEarnedDetail(ShopEarnedDetail shopEarnedDetail) {
		summaryDao.createShopEarnedDetail(shopEarnedDetail);
		
	}
	
}
