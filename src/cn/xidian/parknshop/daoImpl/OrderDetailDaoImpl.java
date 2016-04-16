package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
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

	public OrderDetail getOrderDetail(long id){
		Session session = super.getSessionFactory().getCurrentSession();
		
		return (OrderDetail)session.get(OrderDetail.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderDetail> getDetailInfo(String userName, long commodityNo) {
		String hql = "from OrderDetail as od where od.order.buyer.userName=? and od.commodity.commodityNo=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, userName);
		query.setParameter(1, commodityNo);
		return query.list();
	}

	@Override
	public List<OrderDetail> getDetailByOrderNo(long orderNo) {
		// TODO Auto-generated method stub
		String hql="from OrderDetail od where od.order.orderNo="+orderNo;
		@SuppressWarnings("unchecked")
		List<OrderDetail> resultList=(List<OrderDetail>) factory.getCurrentSession().createQuery(hql).list();
		return resultList;
	}

}
