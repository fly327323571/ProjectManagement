package cn.xidian.parknshop.daoImpl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Share;
import cn.xidian.parknshop.dao.ShareDao;

@Repository("shareDao")
public class ShareDaoImpl extends HibernateDaoSupport implements ShareDao{
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	public void add(Share share) {		
		super.getSessionFactory().getCurrentSession().save(share);
	}

	
}
