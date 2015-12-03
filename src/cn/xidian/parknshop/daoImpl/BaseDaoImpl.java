package cn.xidian.parknshop.daoImpl;

import javax.annotation.Resource;

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
	public void saveObj(T obj) {
		// TODO Auto-generated method stub
		super.getSessionFactory().getCurrentSession().save(obj);
		super.getSessionFactory().getCurrentSession().flush();
	}

}
