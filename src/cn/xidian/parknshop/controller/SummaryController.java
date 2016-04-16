package cn.xidian.parknshop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.HomePageCommodityAds;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.Share;
import cn.xidian.parknshop.beans.ShopEarnedDetail;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.SummaryService;
import cn.xidian.parknshop.utils.CommoditySimple;
import cn.xidian.parknshop.utils.DataDump;
import cn.xidian.parknshop.utils.IncomeInfo;

@Controller
@RequestMapping("/admin")
public class SummaryController {
	@Resource(name="baseService")
	private BaseService<Order> orderService;
	
	@Resource(name="SummaryService")
	private SummaryService summaryService;
	
	@RequestMapping("/businessTransaction")
	public String businessTrasation(Model model,String shopName){
		List<Order> array;
		if(shopName == null || shopName.equals("")){
			//没有查询条件
			model.addAttribute("shopName", "*");
			array = summaryService.findOrderSucess(null);
		}else{
			//有查询条件
			model.addAttribute("shopName",shopName);
			array = summaryService.findOrderSucess(shopName);
		}
		
		double sum = 0;
		for(int i=0; i < array.size();i++){
			sum += array.get(i).getOrderPrice();
		}
		model.addAttribute("shopTransaction", sum);
		model.addAttribute("transactionDetail", array);
		return "../views/admin/businessTransaction";
	}
	
	@RequestMapping("/database")
	public String database(){
		return "../views/admin/database";
	}
	
	@ResponseBody
	@RequestMapping("/dumpDatabase.do")
	public String dumpDatabase(HttpServletRequest request) throws Exception{
		DataDump dataDump = new DataDump(request);
		 if(dataDump.dumpDataBase())
			 return "true";
		 else
			 return "false";
	}
	
	@ResponseBody
	@RequestMapping("/recoverDatabase.do")
	public String recoverDatabase(HttpServletRequest request) throws Exception{
		DataDump dataDump = new DataDump(request);
		if(dataDump.recoverDateBase())
			return "true";
		else
			return "false";
	}
	
	@RequestMapping("/top10")
	public String top10(Model model){
		ArrayList<CommoditySimple> array =summaryService.findTop10();
		model.addAttribute("array",array);
		return "../views/admin/top10";
	}
	
	@RequestMapping("/summary")
	public String summary(Model model){
		//Order state
		int OrderNonPayment = summaryService.findOrderNonPayment();
		int OrderNotRecieving = summaryService.findOrderNotRecieving();
		int OrderRecieved = summaryService.findOrderRecieved();
		model.addAttribute("OrderNonPayment", OrderNonPayment);
		model.addAttribute("OrderNotRecieving", OrderNotRecieving);
		model.addAttribute("OrderRecieved", OrderRecieved);
		
		//pay type
		int OrderOnlinePayment = summaryService.findOrderOnlinePayment();
		int OrderCashOnDelivery = summaryService.findOrderCashOnDeliery();
		model.addAttribute("OrderOnlinePayment", OrderOnlinePayment);
		model.addAttribute("OrderCashOnDelivery", OrderCashOnDelivery);
		
		//commodity category
		int CommodityOfFood = summaryService.findCommodityOfFood();
		int CommodityOfClothes = summaryService.findCommodityOfClothes();
		model.addAttribute("CommodityOfFood", CommodityOfFood);
		model.addAttribute("CommodityOfClothes",CommodityOfClothes);
		
		
		//shop state
		int ShopApplyed = summaryService.findShopStateApplyed();
		int ShopApplyFailure = summaryService.findShopApplyFailure();
		int ShopShutdown = summaryService.findShopShutdown();
		int ShopWarning = summaryService.findShopWarning();
		model.addAttribute("ShopApplyed", ShopApplyed);
		model.addAttribute("ShopApplyFailure", ShopApplyFailure);
		model.addAttribute("ShopShutdown", ShopShutdown);
		model.addAttribute("ShopWarning", ShopWarning);
		return "../views/admin/summary";
	}
	
	@RequestMapping("/getIncome")
	public String getIncome(Model model, String shopName,HttpServletRequest request){
		if(shopName == null || shopName.equals("")){
			//没有搜索选项，选择所有
			model.addAttribute("shopName", "*");
			ArrayList<IncomeInfo> shouldIncomeInfo = summaryService.findShouldIncomeInfo(null);
			model.addAttribute("shouldIncomeInfo", shouldIncomeInfo);
			request.getSession().setAttribute("getIncome.shouldIncomeInfo", shouldIncomeInfo);
		}else{
			model.addAttribute("shopName",shopName);
			ArrayList<IncomeInfo> shouldIncomeInfo = summaryService.findShouldIncomeInfo(shopName);
			model.addAttribute("shouldIncomeInfo", shouldIncomeInfo);
			request.getSession().setAttribute("getIncome.shouldIncomeInfo", shouldIncomeInfo);
		}
		return "../views/admin/getIncome";
	}
	
	@RequestMapping("/setAll.do")
	public String setAll(String precent,HttpServletRequest request){
		System.out.println("here");
		double pre;
		try{
			pre = Double.parseDouble(precent);
		}catch(NumberFormatException e){
			return "redirect:getIncome";
		}
		if(pre < 0.0 || pre > 100.0)
			return "redirect:getIncome";
		//
		
		ArrayList<IncomeInfo> shouldIncomeInfo = (ArrayList<IncomeInfo>)request.getSession().getAttribute("getIncome.shouldIncomeInfo");
		if(shouldIncomeInfo == null)
			return "redirect:getIncome";
		
		
		request.getSession().setAttribute("getIncome.shouldIncomeInfo", null);
		for(int i=0; i < shouldIncomeInfo.size(); i++){
			Share share = new Share();
			share.setRate(pre);
			share.setTurnover(shouldIncomeInfo.get(i).getTransaction());
			share.setShop(shouldIncomeInfo.get(i).getShop());
			Date date = new Date(System.currentTimeMillis());
			share.setShareTime(date);
			share.setShareMoney(share.getTurnover() * share.getRate() * 0.01);
			summaryService.createShare(share);
			for(int j=0; j < shouldIncomeInfo.get(i).getArray().size(); j++){
				ShopEarnedDetail shopEarnedDetail = new ShopEarnedDetail();
				shopEarnedDetail.setShop(shouldIncomeInfo.get(i).getShop());
				shopEarnedDetail.setTime(date);
				shopEarnedDetail.setShareRate(pre);
				shopEarnedDetail.setRealEarn(shouldIncomeInfo.get(i).getArray().get(j).getOrderPrice() * ( 1 - share.getRate() * 0.01));
				shopEarnedDetail.setOrder(shouldIncomeInfo.get(i).getArray().get(j));
				summaryService.createShopEarnedDetail(shopEarnedDetail);
			}
		}
		
		
		return "redirect:getIncome";
	}
	
	@ResponseBody
	@RequestMapping("/setIncome.do")
	public void setIncome(long shopNo,String precent,HttpServletRequest request){
		double pre;
		try{
			pre = Double.parseDouble(precent);
		}catch(NumberFormatException e){
			return ;
		}
		if(pre < 0.0 || pre > 100.0)
			return ;
		
		System.out.println("shopNp:" + shopNo + "precent:" + pre);
		ArrayList<IncomeInfo> shouldIncomeInfo = (ArrayList<IncomeInfo>)request.getSession().getAttribute("getIncome.shouldIncomeInfo");
		if(shouldIncomeInfo == null){
			return ;
		}
		request.getSession().setAttribute("getIncome.shouldIncomeInfo", null);
		for(int i=0; i < shouldIncomeInfo.size(); i++){
			if(shouldIncomeInfo.get(i).getShop().getShopNo() == shopNo){
				Share share = new Share();
				share.setRate(pre);
				share.setTurnover(shouldIncomeInfo.get(i).getTransaction());
				share.setShop(shouldIncomeInfo.get(i).getShop());
				Date date = new Date(System.currentTimeMillis());
				share.setShareTime(date);
				share.setShareMoney(share.getTurnover() * share.getRate() * 0.01);
				summaryService.createShare(share);
				for(int j=0; j < shouldIncomeInfo.get(i).getArray().size(); j++){
					ShopEarnedDetail shopEarnedDetail = new ShopEarnedDetail();
					shopEarnedDetail.setShop(shouldIncomeInfo.get(i).getShop());
					shopEarnedDetail.setTime(date);
					shopEarnedDetail.setShareRate(pre);
					shopEarnedDetail.setRealEarn(shouldIncomeInfo.get(i).getArray().get(j).getOrderPrice() * ( 1 - share.getRate() * 0.01));
					shopEarnedDetail.setOrder(shouldIncomeInfo.get(i).getArray().get(j));
					summaryService.createShopEarnedDetail(shopEarnedDetail);
				}
				break;
			}
		}
	}
	@RequestMapping("/searchIncome")
	public String searchIncome(String shopName,Model model){
		if(shopName == null || shopName.equals("")){
			model.addAttribute("shopName","*");
			List<Share> array = summaryService.findShare(null);
			model.addAttribute("array", array);
		}else{
			model.addAttribute("shopName",shopName);
			List<Share> array = summaryService.findShare(shopName);
			model.addAttribute("array", array);
		}
		return "../views/admin/searchIncome";
	}
	
	@RequestMapping("/adManage")
	public String adManage(String shopName,Model model){
		List<HomePageCommodityAds>  array1;
		List<HomePageShopAds> array2;
		if(shopName == null || shopName.equals("")){
			model.addAttribute("shopName", "*");
			array1 = summaryService.findNotJudgedHomePageCommodityAds(null);
			array2 = summaryService.findNotJudgedHomePageShopAds(null);
		}
		else{
			model.addAttribute("shopName", shopName);
			array1 = summaryService.findNotJudgedHomePageCommodityAds(shopName);
			array2 = summaryService.findNotJudgedHomePageShopAds(shopName);
		}
		model.addAttribute("array1", array1);
		model.addAttribute("array2", array2);
		
		return "../views/admin/adManage";
	}
	
	@ResponseBody
	@RequestMapping("/agreeCommodityAd.do")
	public void agreeCommodity(long adId){
		HomePageCommodityAds ad = summaryService.findHomePageCommodityAdsById(adId);
		ad.setStatus(1);
		Date date = new Date(System.currentTimeMillis());
		ad.setStartTime(date);
		summaryService.updateHomePageCommodityAds(ad);
		
	}
	
	@ResponseBody
	@RequestMapping("/disagreeCommodityAd.do")
	public void disagreeCommodity(long adId){
		HomePageCommodityAds ad = summaryService.findHomePageCommodityAdsById(adId);
		ad.setStatus(2);
		Date date = new Date(System.currentTimeMillis());
		ad.setStartTime(date);
		summaryService.updateHomePageCommodityAds(ad);
	}
	
	@ResponseBody
	@RequestMapping("/agreeShopAd.do")
	public void agreeShop(long adId){
		HomePageShopAds ad = summaryService.findHomePageShopAdsById(adId);
		ad.setStatus(1);
		Date date = new Date(System.currentTimeMillis());
		ad.setStartTime(date);
		summaryService.updateHomePageShopAds(ad);
		
	}
	
	@ResponseBody
	@RequestMapping("/disagreeShopAd.do")
	public void disagreeShop(long adId){
		HomePageShopAds ad = summaryService.findHomePageShopAdsById(adId);
		ad.setStatus(2);
		Date date = new Date(System.currentTimeMillis());
		ad.setStartTime(date);
		summaryService.updateHomePageShopAds(ad);
		
	}
	
	@RequestMapping("/adSummary")
	public String adSummay(Model model){
		model.addAttribute("money", summaryService.countTotalAdvertisementIncome());
		model.addAttribute("array1", summaryService.findAllActiveHomePageCommodityAds());
		model.addAttribute("array2", summaryService.findAllActiveHomePageShopAds());
		return "../views/admin/adSummary";
	}
	
	
}
