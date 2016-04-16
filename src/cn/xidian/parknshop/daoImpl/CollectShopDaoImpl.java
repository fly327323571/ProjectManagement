package cn.xidian.parknshop.daoImpl;



import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.CollectShop;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.CollectShopDao;

@Repository("collectShopDao")
public class CollectShopDaoImpl extends HibernateDaoSupport implements CollectShopDao{
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	public void add(CollectShop collectShop) {
		
		super.getSessionFactory().getCurrentSession().save(collectShop);
	}
	
	@SuppressWarnings("unchecked")
	public List<CollectShop> findCollectShopByUser(User user) {
		String hql = "from CollectShop as cs where cs.user = :user";
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("user", user);
		return query.list();
	}

	@Override
	public CollectShop findCollectShopByUserAndShop(User user, Shop shop) {
		String hql = "from CollectShop as cs where cs.user = :user and cs.shop = :shop";
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("user", user);
		query.setParameter("shop", shop);
		
		return (CollectShop)query.uniqueResult();
	};
}
