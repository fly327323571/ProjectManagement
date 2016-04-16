package cn.xidian.parknshop.daoImpl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.dao.CommentsDao;

@Repository("commentsDao")
public class CommentsDaoImpl extends HibernateDaoSupport implements CommentsDao {
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	public void add(Comments c) {
		super.getSessionFactory().getCurrentSession().save(c);
	}
}
