package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Odium;
import cn.xidian.parknshop.beans.Admin;
import cn.xidian.parknshop.beans.Complaint;
import cn.xidian.parknshop.dao.AdminDao;

@Repository("adminDao")

public class AdminDaoImpl extends HibernateDaoSupport implements AdminDao{
	
	@Autowired
	SessionFactory factory;
	
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
	public List<Odium> findOdiumByState(int state) {
		// TODO Auto-generated method stub
		String hql="from Odium o where o.state=:state";
		@SuppressWarnings("unchecked")
		List<Odium> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setInteger("state", state).list();
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
	public Odium findOdiumByNo(long number) {
		// TODO Auto-generated method stub
		String hql="from Odium o where o.number=:number";
		@SuppressWarnings("unchecked")
		List<Odium> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setLong("number",number).list();
		if(list.isEmpty())
			return null;
		return list.get(0) ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> adminLogin(String username, String password) {
		String hql = "from Admin admin where admin.adminAccountName=? and admin.adminPassword=?";
		Query query = super.getSessionFactory().getCurrentSession().createQuery(hql);
		//Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, username);
		query.setParameter(1, password);
		return query.list();
		
	}

}
