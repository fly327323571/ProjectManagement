package cn.xidian.parknshop.daoImpl;

import java.math.BigInteger;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.dao.ShopDashboardDao;

@Repository("shopDashboardDao")
public class ShopDashboardDaoImpl extends HibernateDaoSupport implements ShopDashboardDao {

	@Resource
	void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public long getFavorCount(long shopNo) {
		// TODO Auto-generated method stub
		String sql="select count(Id) as num from tb_collectshop cs where cs.shop_no=:shopNo";
		BigInteger num =(BigInteger) super.getSessionFactory().getCurrentSession().createSQLQuery(sql).setLong("shopNo", shopNo).uniqueResult();
		return num.longValue();
	}

	@Override
	public long getNewCommentsCount(long shopNo) {
		// TODO Auto-generated method stub
		String sql="select count(Id) as num from tb_comment c where c.shop_no=:shopNo and c.comment_isRead=0";
		BigInteger num =(BigInteger) super.getSessionFactory().getCurrentSession().createSQLQuery(sql).setLong("shopNo", shopNo).uniqueResult();
		return num.longValue();
	}

	@Override
	public double getTotalEarnedCount(long shopNo) {
		// TODO Auto-generated method stub
		String sql="select sum(realEarn) from tb_shopEarnedDetail where shop_no=:shopNo";
		double num =(double) super.getSessionFactory().getCurrentSession().createSQLQuery(sql).setLong("shopNo", shopNo).uniqueResult();
		return num;
	}

	@Override
	public long getNewOrderCount(long shopNo) {
		// TODO Auto-generated method stub
		String sql="select count(order_id) as num from tb_order o where o.shop_no=:shopNo and o.status=0";
		BigInteger num =(BigInteger) super.getSessionFactory().getCurrentSession().createSQLQuery(sql).setLong("shopNo", shopNo).uniqueResult();
		return num.longValue();
	}

}
