package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Message;
import cn.xidian.parknshop.dao.CommunicateDao;

@Repository("communicateDao")
public class CommunicateDaoImpl extends HibernateDaoSupport implements CommunicateDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findMessageList(String userName) {
		// TODO Auto-generated method stub
		return super.getSessionFactory().getCurrentSession().createQuery("from Message where receiverName=?").setParameter(0, userName).list();
	}

}
