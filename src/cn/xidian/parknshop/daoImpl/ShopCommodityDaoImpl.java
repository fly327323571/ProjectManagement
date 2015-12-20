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

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.dao.ShopCommodityDao;

@Repository("shopCommodityDao")
public class ShopCommodityDaoImpl extends HibernateDaoSupport implements ShopCommodityDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	@Override
	public boolean checkCommName(String commName,long shopNo) {
		// TODO Auto-generated method stub
		String sql="select c.Id from tb_commodity c where c.commodity_name=:commName and c.shop_no=:shopNo";
		List<?> list=super.getSessionFactory().getCurrentSession().createSQLQuery(sql).setString("commName", commName)
																							.setLong("shopNo", shopNo).list();
		if(list.isEmpty())
			return true;
		return false;
	}
	@Override
	public List<Commodity> findCommodityByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		String sql ="select Id from tb_commodity c where c.shop_no=:shopNo";
		@SuppressWarnings("unchecked")
		List<BigInteger> list=super.getSessionFactory().getCurrentSession().createSQLQuery(sql).setLong("shopNo", shopNo).list();
		List<Commodity> commList=new ArrayList<Commodity>();
		for(BigInteger i : list){
			Commodity comm=(Commodity) super.getSessionFactory().getCurrentSession().get(Commodity.class, i.longValue());
			commList.add(comm);
		}
		return commList;
	}
	@Override
	public Commodity findCommodityByCommNo(long commNo) {
		// TODO Auto-generated method stub
		String hql="from Commodity c where c.commoditNo=:commNo";
		@SuppressWarnings("unchecked")
		List<Commodity> commList=super.getSessionFactory().getCurrentSession().createQuery(hql).setLong("commNo",commNo).list();
		if(commList.isEmpty())
			return null;
		return commList.get(0);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Commodity> findCommodityByFilters(Map<String, String> filters,long shopNo) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("select c.id from tb_commodity c where c.shop_no=").append(shopNo);
		if(filters.containsKey("productName")){
			sql.append(" and c.commodity_name like '%").append(filters.get("productName")).append("%'");
		}
		else if(filters.containsKey("productId")){
			sql.append(" and c.commodity_no like '%").append(filters.get("productId")).append("%'");
		}
		
		if(filters.get("orderFilters").equals("addTime")){
			sql.append(" order by c.commodity_addTime");
		}
		String isAsc=filters.get("isAsc");
		if(Boolean.valueOf(isAsc)){
			sql.append(" asc");
		}
		else{
			sql.append(" desc");
		}
		Query sqlQuery =super.getSessionFactory().getCurrentSession().createSQLQuery(sql.toString());
		sqlQuery.setMaxResults(Integer.valueOf(filters.get("pageSize")));
		sqlQuery.setFirstResult((Integer.valueOf(filters.get("pageIndex"))-1)*Integer.valueOf(filters.get("pageSize")));
		List<BigInteger> IdResult=sqlQuery.list();
		List<Commodity> resultList=new ArrayList<Commodity>();
		for(BigInteger id:IdResult){
			resultList.add((Commodity) super.getSessionFactory().getCurrentSession().get(Commodity.class, id.longValue()));
		}
			return resultList;

	}

}
