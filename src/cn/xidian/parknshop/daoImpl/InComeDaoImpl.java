package cn.xidian.parknshop.daoImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.ShopEarnedDetail;
import cn.xidian.parknshop.dao.InComeDao;

@Repository("inComeDao")
public class InComeDaoImpl extends HibernateDaoSupport implements InComeDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public List<ShopEarnedDetail> findShopEarnByFilters(Map<String, String> filter, long shopNo) {
		// TODO Auto-generated method stub
		StringBuilder hql=new StringBuilder();
		hql.append("from ShopEarnedDetail t where t.shop.shopNo=").append(shopNo);
		hql.append(" and t.time>=:start");
			Query query=super.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date=null;
		try {
			date = sdf.parse(filter.get("time"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.setDate("start",date);
		query.setMaxResults(Integer.valueOf(filter.get("pageSize")));
		query.setFirstResult((Integer.valueOf(filter.get("pageIndex"))-1)*Integer.valueOf(filter.get("pageSize")));
		@SuppressWarnings("unchecked")
		List<ShopEarnedDetail> resultList=query.list();
		return resultList ;
	}

}
