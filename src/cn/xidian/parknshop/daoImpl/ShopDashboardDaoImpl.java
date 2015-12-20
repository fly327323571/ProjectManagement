package cn.xidian.parknshop.daoImpl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.dao.ShopDashboardDao;

@Repository("shopDashboardDao")
public class ShopDashboardDaoImpl extends HibernateDaoSupport implements ShopDashboardDao {

	@Override
	public long getFavorCount(long shopNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNewCommentsCount(long shopNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getTotalEarnedCount(long shopNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getNewOrderCount(long shopNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
