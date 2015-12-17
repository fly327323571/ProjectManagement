package cn.xidian.parknshop.daoImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Shop;
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

	@Override
	public List<Shop> findShopByUserName(String userName) {
		// TODO Auto-generated method stub
		String sql="select s.Id from tb_shop s Where s.username=:userName";
		@SuppressWarnings("unchecked")
		List<BigInteger> Idlist=super.getSessionFactory().getCurrentSession().createSQLQuery(sql).setString("userName", userName).list();
		List<Shop> shopList=new ArrayList<Shop>();
		for(BigInteger id:Idlist){
			shopList.add((Shop)super.getSessionFactory().getCurrentSession().get(Shop.class, id.longValue()));
		}
		return shopList;
	}

	@Override
	public Shop findShopByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		String hql="from Shop s where s.shopNo=:shopNo";
		@SuppressWarnings("unchecked")
		List<Shop> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setLong("shopNo", shopNo).list();
		if(list.isEmpty())
			return null;
		return list.get(0);
	}

}
