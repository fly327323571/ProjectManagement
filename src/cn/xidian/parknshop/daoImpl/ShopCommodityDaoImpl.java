package cn.xidian.parknshop.daoImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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

}
