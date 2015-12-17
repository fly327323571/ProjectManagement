package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.dao.ShopDao;

@Repository("shopDao")
public class ShopDaoImpl extends HibernateDaoSupport implements ShopDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public boolean checkShopName(String shopName) {
		// TODO Auto-generated method stub
		String hql="from Shop s Where s.shopName=:shopName";
		List<?> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setString("shopName", shopName).list();
		if(list.isEmpty())
			return true;
		return false;
	}

}
