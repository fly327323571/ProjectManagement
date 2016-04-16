package cn.xidian.parknshop.service;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.Shop;

public interface AuditShopService {
	
	public List<Map<String, Object>> getShopInfo();
	
	public int updateApproveShop(Long id);
	
	public int updateDisapproveShop(Long id);
	
	public Commodity getAllSaleCommodity();
	
	public List<Shop> getAllAuditPersonInfo();
	
	public List<Map<String, Object>> getShopInfoByRegistTime();
	
	public List<Map<String, Object>> getShopInfoByStatus(Integer status);
	
	public List<Map<String, Object>> shopInfoByReaserch(String userName);
}
