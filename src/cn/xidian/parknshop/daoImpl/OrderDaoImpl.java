package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.dao.OrderDao;
import cn.xidian.parknshop.dao.ShopDao;

@Repository("orderDao")
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao{
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> findOrderByName(String buyerName){
		String hql = "from Order as o where o.buyer.userName= :name";
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", buyerName);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> findOrdersWithUnpayByName(String buyerName){
		String hql = "from Order as o where o.state = 0 and o.buyer.userName= :name";
	
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", buyerName);
		return query.list();
	}
}
