package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Complaint;
import cn.xidian.parknshop.dao.AdminDao;

@Repository("adminDao")

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao{
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Complaint> findComplaintByState(int state) {
		// TODO Auto-generated method stub
		String hql="from Complaint c where c.complaint_state=:complaint_state";
		@SuppressWarnings("unchecked")
		List<Complaint> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("complaint_state", state).list();
		if(list.isEmpty())
			return null;
		return list ;
	}

	@Override
	public List<Comments> findCommentByState(int state) {
		// TODO Auto-generated method stub
		String hql="from Comments c where c.state=:state";
		@SuppressWarnings("unchecked")
		List<Comments> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("state", state).list();
		if(list.isEmpty())
			return null;
		return list;
	}

	@Override
	public Complaint findComplaintByComplaintNo(long complaintNo) {
		// TODO Auto-generated method stub
		String hql="from Complaint c where c.complaintNo=:complaintNo";
		@SuppressWarnings("unchecked")
		List<Complaint> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setLong("complaintNo", complaintNo).list();
		if(list.isEmpty())
			return null;
		return list.get(0) ;
	}

	@Override
	public Comments findCommentById(int commentsId) {
		// TODO Auto-generated method stub
		String hql="from Comments c where c.commentsId=:commentsId";
		@SuppressWarnings("unchecked")
		List<Comments> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setLong("commentsId",commentsId).list();
		if(list.isEmpty())
			return null;
		return list.get(0) ;
	}

}
