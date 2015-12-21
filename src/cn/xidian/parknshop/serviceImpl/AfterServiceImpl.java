package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.AfterSaleService;
import cn.xidian.parknshop.daoImpl.AfterDaoImpl;
import cn.xidian.parknshop.service.AfterService;

@Service("afterService")
public class AfterServiceImpl implements AfterService {
	
	@Resource(name="afterDao")
	private AfterDaoImpl<AfterSaleService> after;

	@Override
	public List<AfterSaleService> getAfterSaleServiceInfo() {
		
		return after.getAfterSaleServiceInfo();
	}

	@Override
	public void updateApprove(long serviceNo, Integer serviceType) {
		after.updateApprove(serviceNo, serviceType);
		
	}

	@Override
	public void updateDisapprove(long serviceNo, Integer serviceType) {
		after.updateDisapprove(serviceNo, serviceType);
		
	}

	@Override
	public void updateStatus() {
		after.updateStatus();
		
	}

}
