package cn.xidian.parknshop.daoImpl;

import cn.xidian.parknshop.dao.ExpressDao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Express;

@Repository("expressDao")
public class ExpressDaoImpl extends HibernateDaoSupport implements ExpressDao{
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	public Express getExpressByOrderNo(long orderNo){
		String hql = "from Express as e where e.order.orderNo = :orderNo";
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("orderNo", orderNo);
		
		return (Express) query.uniqueResult();
	}
}
