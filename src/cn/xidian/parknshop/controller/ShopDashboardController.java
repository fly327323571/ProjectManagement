package cn.xidian.parknshop.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.ResultType;

@Controller
public class ShopDashboardController {

	
	@RequestMapping("shop/{shopNo}/dashboard/favorites/shop")
	public @ResponseBody Map<String,ResultType> countFavourites(@PathVariable long shopNo){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		
		return map;
	}
}
