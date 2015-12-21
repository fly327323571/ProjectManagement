package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.dao.OrderDetailDao;

@Repository("orderDetailDaoImpl")
public class OrderDetailDaoImpl extends HibernateDaoSupport implements OrderDetailDao {
	
	@Autowired
	SessionFactory factory;
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> getDetailInfo(String userName, long commodityNo) {
		String hql = "from OrderDetail as od where od.order.buyer.userName=? and od.commodity.commoditNo=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, userName);
		query.setParameter(1, commodityNo);
		return query.list();
	}

}
