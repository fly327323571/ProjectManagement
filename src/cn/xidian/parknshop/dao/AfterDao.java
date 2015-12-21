package cn.xidian.parknshop.dao;

import java.util.List;
import cn.xidian.parknshop.beans.AfterSaleService;


public interface AfterDao<T> {
	
	public List<AfterSaleService> getAfterSaleServiceInfo();
	
	public void updateApprove(long serviceNo, Integer serviceType);
	
	public void updateDisapprove(long serviceNo, Integer serviceType);
	
	public void updateStatus();

}
