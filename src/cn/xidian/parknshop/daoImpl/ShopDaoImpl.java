package cn.xidian.parknshop.daoImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Shop> findShopsBySomeFilter(Map<String, String> filter) {
		// TODO Auto-generated method stub
		StringBuilder hql=new StringBuilder();
		hql.append("from Shop s ");
		
		if(filter.containsKey("categoryFilters")){
			hql.append("where s.shopCategories=").append(filter.get("categoryFilters"));
			if(filter.containsKey("shopNameFilters")){
				hql.append(" and s.shopName like '%").append(filter.get("shopNameFilters")).append("%'");
			}
		}
		else if(filter.containsKey("shopNameFilters")){
			hql.append("where s.shopName='").append(filter.get("shopNameFilters")).append("'");
		}
		if(filter.get("orderFilters").equals("registerTime"))
			hql.append(" order by s.regTime ");
		else{
			hql.append(" order by s.shopRank ");
		}
		String isAsc=filter.get("isAsc");
		if(Boolean.valueOf(isAsc)){
			hql.append(" asc");
		}
		else{
			hql.append(" desc");
		}
		Query query=super.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		query.setMaxResults(Integer.valueOf(filter.get("pageSize")));
		query.setFirstResult((Integer.valueOf(filter.get("pageIndex"))-1)*Integer.valueOf(filter.get("pageSize")));
		return query.list();
	}

}
