package cn.xidian.parknshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.service.SearchService;

@Controller
public class SearchController {
	
	@Resource
	private SearchService searchService;
	
	private static Logger log=Logger.getLogger(SearchController.class);
	
	
	@RequestMapping("/search/updateData")
	public @ResponseBody Map<String,ResultType> searchCommodity(Model model,HttpServletRequest request){
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
		
		resultType=searchService.search(columnFilterValue_0, QueryParamMap);
		model.addAttribute("page", resultType.getResult());
	
		map.put("result", resultType);
		return map;
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/search/cusSearch.do")
	public String cusSearch(String query,Model model){
		ResultType resultType=searchService.cusSearch(query);
		String json=JSON.toJSONString((List<Commodity>)resultType.getResult());
		model.addAttribute("page", json);
		model.addAttribute("pageCount", resultType.getTotalPageCount());
		return "../views/customer/searchResult";
	}

}
