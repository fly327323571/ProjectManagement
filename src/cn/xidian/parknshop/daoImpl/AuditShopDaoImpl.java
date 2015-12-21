package cn.xidian.parknshop.daoImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.dao.AuditShopDao;


@Repository("auditshopdaoimpl")
public class AuditShopDaoImpl extends HibernateDaoSupport implements AuditShopDao {
	
	@Autowired
	SessionFactory factory;
	
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 获取店铺的信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getShopInfo() {
		String hql = "select new map(shop.shopId as shopId,shop.shopName as shopName,shop.shopCategories as shopCategories,shop.status as status,shop.regTime as regTime)from Shop as shop where shop.status=0";
		return factory.getCurrentSession().createQuery(hql).list();
		
	}
	
	/**
	 * 同意开店
	 */
	public int updateApproveShop(Long id) {
		String hql = "update Shop as shop set shop.status=1 where shop.shopId=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, id);
		return query.executeUpdate();
		
	};
	
	/**
	 * 不同意开店
	 */
	public int updateDisapproveShop(Long id) {
		String hql = "update Shop as shop set shop.status=2 where shop.shopId=?";
		Query query = factory.getCurrentSession().createQuery(hql);
		query.setParameter(0, id);
		return query.executeUpdate();
	};

	@Override
	public Commodity getAllSaleCommodity() {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Shop> getAllAuditPersonInfo() {
		String hql = "from Shop as shop where shop.status=0";
		return factory.getCurrentSession().createQuery(hql).list();
	}

}
