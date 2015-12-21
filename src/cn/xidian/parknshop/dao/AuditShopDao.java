package cn.xidian.parknshop.dao;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.Shop;


public interface AuditShopDao {
	
	public List<Map<String, Object>> getShopInfo();
	
	public int updateApproveShop(Long id);
	
	public int updateDisapproveShop(Long id);
	
	public Commodity getAllSaleCommodity();
	
	public List<Shop> getAllAuditPersonInfo();

}
