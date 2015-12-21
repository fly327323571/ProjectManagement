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
import cn.xidian.parknshop.utils.DictionaryUtils;

@Repository("shopDao")
public class ShopDaoImpl extends HibernateDaoSupport implements ShopDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public boolean checkShopName(String shopName) {
		// TODO Auto-generated method stub
		String sql="from Shop s Where s.shopName=:shopName";
		List<?> list=super.getSessionFactory().getCurrentSession().createQuery(sql).setString("shopName", shopName).list();
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
		String sql="from Shop s where s.shopNo=:shopNo";
		@SuppressWarnings("unchecked")
		List<Shop> list=super.getSessionFactory().getCurrentSession().createQuery(sql).setLong("shopNo", shopNo).list();
		if(list.isEmpty())
			return null;
		return list.get(0);
	}


	@Override
	public List<Shop> findShopsBySomeFilter(Map<String, String> filter,String userName) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("select s.Id from tb_shop s where s.username='").append(userName).append("'");
		
		if(filter.containsKey("category")){
			sql.append(" and s.shop_category=").append(DictionaryUtils.ShopCategory.fromString(filter.get("category")).numberOfShopCategory());
		}
		if(filter.containsKey("shopName")){
			sql.append(" and s.shop_name like '%").append(filter.get("shopName")).append("%'");
		}
		if(filter.get("orderFilters").equals("registerTime"))
			sql.append(" order by s.registerTime ");
		else{
			sql.append(" order by s.shop_rank ");
		}
		String isAsc=filter.get("isAsc");
		if(Boolean.valueOf(isAsc)){
			sql.append(" asc");
		}
		else{
			sql.append(" desc");
		}
		Query query=super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		query.setMaxResults(Integer.valueOf(filter.get("pageSize")));
		query.setFirstResult((Integer.valueOf(filter.get("pageIndex"))-1)*Integer.valueOf(filter.get("pageSize")));
		@SuppressWarnings("unchecked")
		List<BigInteger> list=query.list();
		List<Shop> resultList=new ArrayList<Shop>();
		for(BigInteger i:list){
			resultList.add((Shop) super.getSessionFactory().getCurrentSession().get(Shop.class, i.longValue()));
		}
		return resultList;
	}

	@Override
	public List<Shop> findOtherShopsBySomeFilter(Map<String, String> filter, long shopNo) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("select s.Id from tb_shop s where s.shop_no<>").append(shopNo);
		
		if(filter.containsKey("category")){
			sql.append(" and s.shop_category=").append(DictionaryUtils.ShopCategory.fromString(filter.get("category")).numberOfShopCategory());
		}
		if(filter.containsKey("shopName")){
			sql.append(" and s.shop_name like '%").append(filter.get("shopName")).append("%'");
		}
		if(filter.get("orderFilters").equals("registerTime"))
			sql.append(" order by s.registerTime ");
		else{
			sql.append(" order by s.shop_rank ");
		}
		String isAsc=filter.get("isAsc");
		if(Boolean.valueOf(isAsc)){
			sql.append(" asc");
		}
		else{
			sql.append(" desc");
		}
		Query query=super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		query.setMaxResults(Integer.valueOf(filter.get("pageSize")));
		query.setFirstResult((Integer.valueOf(filter.get("pageIndex"))-1)*Integer.valueOf(filter.get("pageSize")));
		@SuppressWarnings("unchecked")
		List<BigInteger> list=query.list();
		List<Shop> resultList=new ArrayList<Shop>();
		for(BigInteger i:list){
			resultList.add((Shop) super.getSessionFactory().getCurrentSession().get(Shop.class, i.longValue()));
		}
		return resultList;
	}
	
	@Override
	public List<Shop> findShopByShopName(String shopname) {
		// TODO Auto-generated method stub
		String hql="from Shop s where s.shopName=:shopName";
		@SuppressWarnings("unchecked")
		List<Shop> list=super.getSessionFactory().getCurrentSession().createQuery(hql).setString("shopName", shopname).list();
		if(list.isEmpty())
			return null;
		return list;
	
	}

}
