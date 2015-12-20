package cn.xidian.parknshop.service;

public interface ShopDashboardService {
	
	long getFavorCount(long shopNo);

	long getNewCommentsCount(long shopNo);

	double getTotalEarnedCount(long shopNo);

	long getNewOrderCount(long shopNo);
}
