package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends HibernateDaoSupport implements UserDao{

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		String hql="from User u where u.userName=:userName";
		@SuppressWarnings("unchecked")
		List<User> list=(List<User>) super.getSessionFactory().getCurrentSession().createQuery(hql).setString("userName", userName).list();;
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean checkUserNameExist(String userName) {
		// TODO Auto-generated method stub
		String hql="from User u where u.userName=:userName";
		@SuppressWarnings("unchecked")
		List<User> list=(List<User>) super.getSessionFactory().getCurrentSession().createQuery(hql).setString("userName", userName).list();;
		if(list.isEmpty()){
			return true;
		}
		return false;
	}

	@Override
	public boolean LogIn(String userName, String secPassWord) {
		// TODO Auto-generated method stub
		String hql="from User u where u.userName=:userName and u.password=:secPassword";
		@SuppressWarnings("unchecked")
		List<User> list=(List<User>) super.getSessionFactory().getCurrentSession().createQuery(hql).setString("userName", userName)
							.setString("secPassword", secPassWord).list();
		if(list.isEmpty()){
			return false;
		}
		return true;
	}

}
