package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.dao.BaseDao;

@Repository("baseDao")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

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
	
}
