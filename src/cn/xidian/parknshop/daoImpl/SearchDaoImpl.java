package cn.xidian.parknshop.daoImpl;


import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.dao.SearchDao;

@Repository("searchDao")
public class SearchDaoImpl extends HibernateDaoSupport implements SearchDao {

	private static Logger log =Logger.getLogger(SearchDaoImpl.class);
	
	@Resource
	void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	@Override
	public ResultType search(String commName, Map<String,String> queryParam) {
		// TODO Auto-generated method stub
		ResultType resultType = new ResultType();
		try{
		String hqlPageCount="SELECT COUNT(1) ";
		
		StringBuilder hql=new StringBuilder();
		hql.append("from Commodity c where lower(c.commodityName) like lower('%");
		hql.append(commName);
		hql.append("%')");
		hql.append("order by ");
		hql.append(queryParam.get("orderFilters"));
		if(Boolean.valueOf(queryParam.get("isAsc"))){
			hql.append(" asc");
		}
		else{
		hql.append(" desc");}
		long pageCount=(long) super.getSessionFactory().getCurrentSession().createQuery(hqlPageCount+" "+hql.toString()).uniqueResult();
		resultType.setTotalPageCount(pageCount/Integer.valueOf(queryParam.get("pageSize"))+1);
		Query hqlQuery =  super.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		hqlQuery.setMaxResults(Integer.valueOf(queryParam.get("pageSize")));
		hqlQuery.setFirstResult((Integer.valueOf(queryParam.get("pageIndex"))-1)*Integer.valueOf(queryParam.get("pageSize")));
		resultType.setResult(hqlQuery.list());
		}
		catch(Exception e){
			log.error(e.getMessage());
			resultType.error().setResult("db busy");
			return resultType;
		}
		return resultType;
	}

	@Override
	public ResultType cusSearch(String commName) {
		// TODO Auto-generated method stub
		ResultType resultType = new ResultType();
		try{
		String hqlPageCount="SELECT COUNT(1) ";
		
		StringBuilder hql=new StringBuilder();
		hql.append("from Commodity c where lower(c.commodityName) like lower('%");
		hql.append(commName);
		hql.append("%')");
		hql.append("order by  c.saleVolume ");
		hql.append("desc");
		long pageCount=(long) super.getSessionFactory().getCurrentSession().createQuery(hqlPageCount+" "+hql.toString()).uniqueResult();
		resultType.setTotalPageCount(pageCount/12+1);
		Query hqlQuery =  super.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		hqlQuery.setMaxResults(12);
		hqlQuery.setFirstResult(0);
		resultType.setResult(hqlQuery.list());
		}
		catch(Exception e){
			log.error(e.getMessage());
			resultType.error().setResult("db busy");
			return resultType;
		}
		return resultType;
	}

	
}
