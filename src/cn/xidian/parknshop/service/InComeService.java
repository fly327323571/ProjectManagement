package cn.xidian.parknshop.service;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.ShopEarnedDetail;

public interface InComeService {

	List<ShopEarnedDetail> findShopEarnByFilters(Map<String, String> filter, long shopNo);
}
