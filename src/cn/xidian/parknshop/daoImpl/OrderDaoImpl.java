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
	public List<Order> findOrderByName(String name){
		String sql = "select order_id, order_no, order_price, pay_way, post_way, status, buyer_name, seller_id "
				+ "from tb_order where buyer_name = :name";
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(sql);
		query.setParameter("name", name);
		return query.list();
	}
}
