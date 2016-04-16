package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.ShopLinks;
import cn.xidian.parknshop.dao.ShopHomePageDao;

@Repository("shopHomePageDao")
public class ShopHomePageDaoImpl extends HibernateDaoSupport implements ShopHomePageDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	
	@Override
	public List<ShopLinks> findShopLinkByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		String hql="from ShopLinks sl where sl.shop_no="+shopNo;
		@SuppressWarnings("unchecked")
		List<ShopLinks> resultList=super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return resultList;
	}

}
