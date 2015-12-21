package cn.xidian.parknshop.daoImpl;

import java.util.List;


import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.AfterSaleService;
import cn.xidian.parknshop.dao.AfterDao;

@Repository("afterDao")
public class AfterDaoImpl<T> extends HibernateDaoSupport implements AfterDao<T> {
	
	@Autowired
	SessionFactory factory;
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 获取退换货信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<AfterSaleService> getAfterSaleServiceInfo() {
		String hql = "from AfterSaleService as after where after.status=0 or after.status=1";
		Query query = factory.getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	/**
	 * 处理完成
	 */
	@Override
	public void updateApprove(long serviceNo, Integer serviceType) {
		String hql = null;
		if (0==serviceType) {
			hql = "update AfterSaleService as after set after.status=2 where after.serviceNo=?";
		}
		if (1==serviceType) {
			hql = "update AfterSaleService as after set after.status=2 where after.serviceNo=?";
		}
		
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, serviceNo);
		query.executeUpdate();
		
	}
	
	/**
	 * 处理完成
	 */
	@Override
	public void updateDisapprove(long serviceNo, Integer serviceType) {
		String hql = null;
		if (0==serviceType) {
			hql = "update AfterSaleService as after set after.status=2 where after.serviceNo=?";
		}
		if (1==serviceType) {
			hql = "update AfterSaleService as after set after.status=2 where after.serviceNo=?";
		}
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, serviceNo);
		query.executeUpdate();
		
	}
	
	/**
	 * 售后处理中
	 */
	@Override
	public void updateStatus() {
		String hql = "update AfterSaleService as after set after.status=1 where after.status=0";
		factory.getCurrentSession().createQuery(hql).executeUpdate();
	}

	
	

}
