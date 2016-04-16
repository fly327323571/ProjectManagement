package cn.xidian.parknshop.controller;

import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.ShopEarnedDetail;
import cn.xidian.parknshop.service.InComeService;

@Controller
public class DashBoardIncomeController {

	private static Logger log=Logger.getLogger(DashBoardIncomeController.class);
	
	@Resource(name="inComeService")
	private InComeService inComeService;
	
	@RequestMapping("store/{shopNo}/dashboard/income/query")
	public @ResponseBody Map<String,ResultType> viewIncome(@PathVariable long shopNo,
												HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Map<String,String> QueryParamMap=new HashMap<String,String>();
		String pageIndex=request.getParameter("page[pageIndex]");
		String pageSize=request.getParameter("page[pageSize]");
		String columnFilterName_0=request.getParameter("columnFilters[0][name]");
		String columnFilterValue_0=request.getParameter("columnFilters[0][value][]");
		QueryParamMap.put("pageIndex", pageIndex);
		QueryParamMap.put("pageSize", pageSize);
		QueryParamMap.put(columnFilterName_0, columnFilterValue_0);
		List<ShopEarnedDetail> shopRealEarned=null;
		try{
			shopRealEarned=inComeService.findShopEarnByFilters(QueryParamMap, shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db Busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(shopRealEarned);
		map.put("result", resultType);
		return map;
	}
}
