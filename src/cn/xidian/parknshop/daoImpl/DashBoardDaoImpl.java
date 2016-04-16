package cn.xidian.parknshop.daoImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Odium;
import cn.xidian.parknshop.dao.DashBoardDao;
import cn.xidian.parknshop.utils.DictionaryUtils;

@Repository("dashBoardDao")
public class DashBoardDaoImpl extends HibernateDaoSupport implements DashBoardDao {

	@Resource
	void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public List<Odium> findNewCommentsByShopNoAndFilters(Map<String, String> filters,long shopNo) {
		// TODO Auto-generated method stub
		StringBuilder hql=new StringBuilder();
		hql.append("from Comments c where c.shop.shopNo=").append(shopNo);
		hql.append(" and c.isRead=0");
		Query query=super.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		query.setMaxResults(Integer.valueOf(filters.get("pageSize")));
		query.setFirstResult((Integer.valueOf(filters.get("pageIndex"))-1)*Integer.valueOf(filters.get("pageSize")));
		@SuppressWarnings("unchecked")
		List<Odium> resultList=query.list();
		return resultList;
	}

	@Override
	public void createComplain(Comments comment, Odium odium) {
		// TODO Auto-generated method stub
		super.getSessionFactory().getCurrentSession().save(odium);
		comment.setIsRead(DictionaryUtils.CommentsIsRead.Read.numberOfCommentsIsRead());
		super.getSessionFactory().getCurrentSession().update(comment);
		super.getSessionFactory().getCurrentSession().flush();
	}

}
