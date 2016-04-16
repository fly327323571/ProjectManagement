package cn.xidian.parknshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xidian.parknshop.beans.Advertisement;
import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.ShopLinks;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.ShopCommodityService;
import cn.xidian.parknshop.service.ShopHomePageService;
import cn.xidian.parknshop.service.ShopService;
@Controller
public class ShopHomePageController {

	private static Logger log =Logger.getLogger(ShopHomePageController.class);
	
	@Resource(name="shopCommodityService")
	private ShopCommodityService shopCommodityService;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	@Resource(name="shopHomePageService")
	private ShopHomePageService shopHomeService;
	
	@Resource(name="baseService")
	private BaseService<Commodity> commodityBaseService;
	
	@Resource(name="baseService")
	private BaseService<HomePageShopAds> shopAdsBaseService;
	
	@RequestMapping("business/market/{shopNo}/shopHomePage.do")
	private ModelAndView shopHomePageIndex(@PathVariable long shopNo,Model model){
		
		model.addAttribute("storeId", shopNo);
		Shop shop=null;
		try{
			shop=shopService.findShopByShopNo(shopNo);
		}
		catch(Exception e){
			log.error(e);
			return new  ModelAndView("../views/error");
		}
		model.addAttribute("store", shop);
		return new ModelAndView("../views/shop/shopHomepage");
		
	}
	@RequestMapping("shop/{shopNo}/shopLinks")
	public @ResponseBody Map<String,ResultType> shopLinks(@PathVariable long shopNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		List<ShopLinks> shopLinks=null;
		try{
			shopLinks=shopHomeService.findShopLinkByShopNo(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(shopLinks);
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("product/{shopNo}/delete/check")
	public @ResponseBody Map<String,ResultType> deleteCheck(@PathVariable long shopNo,long productId){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		resultType.success().setResult(productId);
		map.put("result", resultType);
		return map;
		
	}
	@RequestMapping("product/{shopNo}/delete")
	
	public @ResponseBody Map<String,ResultType> deleteCommodity(@PathVariable long shopNo,long productId){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Commodity commodity=null;
		try{
			commodity=shopCommodityService.findCommodityByCommNo(productId);}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB busy");
			map.put("result", resultType);
			return map;
		}
		commodity.setShop(null);
		try{
			shopCommodityService.updateOrderByDeleteCommodity(commodity);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("Success");
		map.put("result", resultType);
		return map;
		
	}
	
	
	@RequestMapping("business/market/{shopNo}/showProducts")
	public @ResponseBody Map<String,ResultType> showCommList(@PathVariable long shopNo,HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Map<String,String> QueryParamMap=new HashMap<String,String>();
		String pageIndex=request.getParameter("page[pageIndex]");
		String pageSize=request.getParameter("page[pageSize]");
		String columnFilterName_0=request.getParameter("columnFilters[0][name]");
		String columnFilterValue_0=request.getParameter("columnFilters[0][value][]");
		String orderFilters=request.getParameter("orderFilters[0][name]");
		String isAsc=request.getParameter("orderFilters[0][isAscending]");
		QueryParamMap.put("pageIndex", pageIndex);
		QueryParamMap.put("pageSize", pageSize);
		if(columnFilterName_0!=null){
			QueryParamMap.put(columnFilterName_0, columnFilterValue_0);
		}
		QueryParamMap.put("orderFilters", orderFilters);
		QueryParamMap.put("isAsc", isAsc);
		List<Commodity> list=null;
		try{
			list=shopCommodityService.findCommodityByFilters(QueryParamMap, shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(list);
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("product/{shopNo}/productDetail/{productId}.do")
	public ModelAndView showProductDetail(@PathVariable long productId,
										  @PathVariable long shopNo,
										  Model model){
		Commodity commodity=null;
		try{
			commodity=shopCommodityService.findCommodityByCommNo(productId);}
		catch(Exception e){
			log.error(e);
			return new ModelAndView("../views/error");
		}
		model.addAttribute("store", commodity.getShop());
		model.addAttribute("product", commodity);
		return new ModelAndView("../views/customer/productShow");
	}
	
	@RequestMapping("product/productDetail/{productId}")
	public ModelAndView showProductDetail(@PathVariable long productId,Model model){
		Commodity commodity=null;
		try{
			commodity=shopCommodityService.findCommodityByCommNo(productId);}
		catch(Exception e){
			log.error(e);
			return new ModelAndView("../views/error");
		}
		model.addAttribute("store", commodity.getShop());
		model.addAttribute("product", commodity);
		return new ModelAndView("../views/customer/productShow");
	}
	
	@RequestMapping("business/market/{shopNo}/showAds")
	public @ResponseBody Map<String,ResultType> showAds(@PathVariable long shopNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		List<Advertisement> ads=null;
		try{
			ads=shopService.findCommodityAdvertise(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(ads);
		map.put("result", resultType);
		return map;
		
	}
	
	@RequestMapping("store/{shopNo}/applyAd")
	public @ResponseBody Map<String,ResultType> applyAd(@PathVariable long shopNo,String totalPrice){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		double price=Double.valueOf(totalPrice.substring(1,totalPrice.length()));
		int days=(int) (price/20);
		Shop shop=null;
		try{
			shop=shopService.findShopByShopNo(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		if(shopService.checkHomePageAd(shopNo)){
			try{
			HomePageShopAds shopAds=shopService.findHomePageShopAdByShopNo(shopNo);
			shopAds.setDays(shopAds.getDays()+days);
			shopAdsBaseService.update(shopAds);}
			catch(Exception e){
				log.error(e);
				resultType.error().setResult("Db busy");
				map.put("result", resultType);
				return map;
			}
		}
		else{
		HomePageShopAds shopAds=new HomePageShopAds();
		shopAds.setAd_rate(price);
		shopAds.setDays(days);
		shopAds.setShop(shop);
		shopAds.setShopLink("business/market/"+shopNo+"/shopHomePage.do");
		try{
			shopAdsBaseService.create(shopAds);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}}
		resultType.success().setResult("success");
		map.put("result", resultType);
		return map;
	}
}
