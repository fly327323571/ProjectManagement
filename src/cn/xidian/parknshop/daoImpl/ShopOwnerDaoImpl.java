package cn.xidian.parknshop.daoImpl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.ShopOwner;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.ShopOwnerDao;

@Repository("shopOwnerDao")
public class ShopOwnerDaoImpl extends HibernateDaoSupport implements ShopOwnerDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void createShopOwner(User customer, ShopOwner shopOwner) {
		// TODO Auto-generated method stub
		shopOwner.setUser(customer);
		super.getSessionFactory().getCurrentSession().save(shopOwner);
		customer.setSeller(true);
		super.getSessionFactory().getCurrentSession().saveOrUpdate(customer);
		super.getSessionFactory().getCurrentSession().flush();
	}

}
