package cn.xidian.parknshop.controller;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.service.ShopDashboardService;
import jxl.common.Logger;

@Controller
public class ShopDashboardController {

	@Resource(name="shopDashboardService")
	private ShopDashboardService service;
	
	private static Logger log=Logger.getLogger(ShopDashboardController.class);
	
	@RequestMapping("shop/{shopNo}/dashboard/favorites/count")
	public @ResponseBody Map<String,ResultType> countFavourites(@PathVariable long shopNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		long count=0;
		try{
			count=service.getFavorCount(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(count);
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("shop/{shopNo}/dashboard/newComments/count")
	public @ResponseBody Map<String,ResultType> countNewComments(@PathVariable long shopNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		long count=0;
		try{
			count=service.getNewCommentsCount(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(count);
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("shop/{shopNo}/dashboard/newOrders/count")
	public Map<String,ResultType> countNewOrders(@PathVariable long shopNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		long count=0;
		try{
			count=service.getNewOrderCount(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(count);
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("shop/{shopNo}/dashboard/income/count")
	public Map<String,ResultType> countIncomes(@PathVariable long shopNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		double count=0;
		try{
			count=service.getTotalEarnedCount(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(count);
		map.put("result", resultType);
		return map;
	}
}
