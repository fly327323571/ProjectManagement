package cn.xidian.parknshop.dao;

import java.util.*;

import cn.xidian.parknshop.beans.ShopEarnedDetail;

public interface InComeDao {

	List<ShopEarnedDetail> findShopEarnByFilters(Map<String,String> filter,long shopNo);
}
