package cn.xidian.parknshop.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.*;
import cn.xidian.parknshop.dao.TestDao;

@Repository("TestDao")
public class TestDaoImpl<T> extends HibernateDaoSupport implements TestDao<T> {
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void create(T t) {
		// TODO Auto-generated method stub
		super.getSessionFactory().getCurrentSession().save(t);
		super.getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		super.getSessionFactory().getCurrentSession().delete(t);
		super.getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		super.getSessionFactory().getCurrentSession().update(t);
		super.getSessionFactory().getCurrentSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findObjById(int id, Class<T> type) {
		// TODO Auto-generated method stub
		return (T) super.getSessionFactory().getCurrentSession().get(type,id);
	}

	@Override
	public int getTotalCount(Class<T> type) {
		// TODO Auto-generated method stub
		return (int) super.getSessionFactory().getCurrentSession().createQuery("select count(1) as num from "+
				type.getName()+"").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getPage(Class<T> type, int PageNo,int PageSize,int StartNo) {
		// TODO Auto-generated method stub
		Query query=super.getSessionFactory().getCurrentSession().createQuery("from "+type.getName()+"");
		query.setMaxResults(PageSize);
		query.setFirstResult(StartNo+PageNo*PageSize);
		return (List<T>)query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll(Class<T> type) {
		// TODO Auto-generated method stub
		return super.getSessionFactory().getCurrentSession().createQuery("from "+type.getName()+"").list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Commodity> searchPro(String coName) {
		// TODO Auto-generated method stub
		String hql = "from Commodity c where c.commodityName like '%"+ coName +"%'";
		Query query = super.getSessionFactory().getCurrentSession().createQuery(hql);
		List<Commodity> colist = (List<Commodity>)query.list();
		return colist;
	}

	@Override
	public List<Cart> addCart(long productId, double price, int quantity, Date addTime,String userName) {
		// TODO Auto-generated method stub
		String hql = "insert into tb_cart (commodity_count,sum_price,addTime,commodity_no,username,status) values(?,?,?,?,?,0)";
		Query query = super.getSessionFactory().getCurrentSession().createSQLQuery(hql);
		query.setInteger(0,quantity);
		query.setDouble(1,price);
		query.setDate(2,addTime);
		query.setLong(3,productId);
		query.setString(4,userName);
		query.executeUpdate();
		super.getSessionFactory().getCurrentSession().flush();
//		List<Cart> calist = (List<Cart>)query.list();
		return null;
	}

	@Override
	public Cart findLastAddCart(String username) {
		// TODO Auto-generated method stub
		String sql="select * from tb_cart c where c.username='"+username+"' order by Id desc limit 1";
		return (Cart) super.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(Cart.class).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cart> findCartByUserName(String userName, Map<String, String> filter) {
		// TODO Auto-generated method stub
		StringBuilder hql=new StringBuilder();
		hql.append("from Cart c where c.owner.userName='").append(userName).append("'");
		hql.append(" and c.status=0");
		hql.append(" order by c.addTime");
		String isAsc=filter.get("isAsc");
		if(Boolean.valueOf(isAsc)){
			hql.append(" asc");
		}
		else{
			hql.append(" desc");
		}
		Query query=super.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		query.setMaxResults(Integer.valueOf(filter.get("pageSize")));
		query.setFirstResult((Integer.valueOf(filter.get("pageIndex"))-1)*Integer.valueOf(filter.get("pageSize")));
		return query.list();
	}

	@Override
	public long countInCart(String userName) {
		// TODO Auto-generated method stub
		String hql="select count(1) from Cart cart where cart.owner.userName='"+userName+"' and cart.status=0";
		Long count= (Long)super.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
		return count;
	}
	
}
