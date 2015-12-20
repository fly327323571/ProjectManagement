package cn.xidian.parknshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.service.ShopCommodityService;
import cn.xidian.parknshop.service.ShopService;
import jxl.common.Logger;

@Controller
public class ShopHomePageController {

	private static Logger log =Logger.getLogger(ShopHomePageController.class);
	
	@Resource(name="shopCommodityService")
	private ShopCommodityService shopCommodityService;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
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
		Shop shop=null;
		try{
			shop=shopService.findShopByShopNo(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(shop);
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
	
}
