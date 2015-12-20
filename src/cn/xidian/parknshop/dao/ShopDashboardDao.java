package cn.xidian.parknshop.dao;

public interface ShopDashboardDao {

	long getFavorCount(long shopNo);

	long getNewCommentsCount(long shopNo);

	long getTotalEarnedCount(long shopNo);

	long getNewOrderCount(long shopNo);

}
