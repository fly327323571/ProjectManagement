package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.CollectCommodity;
import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.CollectCommodityDao;

@Repository("collectCommodityDao")
public class CollectCommodityDaoImpl extends HibernateDaoSupport implements CollectCommodityDao{
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	public void add(CollectCommodity collectCommodity) {
		
		super.getSessionFactory().getCurrentSession().save(collectCommodity);
	}
	
	@SuppressWarnings("unchecked")
	public List<CollectCommodity> findCollectCommodityByUser(User user) {
		String hql = "from CollectCommodity as cc where cc.user = :user";
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("user", user);
		return query.list();
	}

	@Override
	public CollectCommodity findCollectCommodityByUserAndCommodity(User user, Commodity commodity) {
		String hql = "from CollectCommodity as cc where cc.user = :user and cc.commodity = :commodity";
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("user", user);
		query.setParameter("commodity", commodity);
		return (CollectCommodity)query.uniqueResult();
	};
}
