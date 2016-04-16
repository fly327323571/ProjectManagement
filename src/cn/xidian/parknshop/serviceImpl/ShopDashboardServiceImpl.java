package cn.xidian.parknshop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.dao.ShopDashboardDao;
import cn.xidian.parknshop.service.ShopDashboardService;

@Service("shopDashboardService")
public class ShopDashboardServiceImpl implements ShopDashboardService {

	@Resource(name="shopDashboardDao")
	private ShopDashboardDao shopDashboardDao;
	
	@Override
	public long getFavorCount(long shopNo) {
		// TODO Auto-generated method stub
		return shopDashboardDao.getFavorCount(shopNo);
	}

	@Override
	public long getNewCommentsCount(long shopNo) {
		// TODO Auto-generated method stub
		return shopDashboardDao.getNewCommentsCount(shopNo);
	}

	@Override
	public double getTotalEarnedCount(long shopNo) {
		// TODO Auto-generated method stub
		return shopDashboardDao.getTotalEarnedCount(shopNo);
	}

	@Override
	public long getNewOrderCount(long shopNo) {
		// TODO Auto-generated method stub
		return shopDashboardDao.getNewOrderCount(shopNo);
	}

}
