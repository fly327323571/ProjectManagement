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

import cn.xidian.parknshop.beans.Advertisement;
import cn.xidian.parknshop.beans.HomePageCommodityAds;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.dao.ShopDao;
import cn.xidian.parknshop.utils.DictionaryUtils;
import cn.xidian.parknshop.utils.HomePageAdvHelper;

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
		if(filter.containsKey("storeName")){
			sql.append(" and s.shop_name like '%").append(filter.get("storeName")).append("%'");
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
	public List<Shop> findOtherShopsBySomeFilter(Map<String, String> filter, long shopNo,String userName) {
		// TODO Auto-generated method stub
		StringBuilder sql=new StringBuilder();
		sql.append("select s.Id from tb_shop  s where s.shop_no<>").append(shopNo).append(" and s.username=")
					.append("'").append(userName).append("'");
		
		if(filter.containsKey("category")){
			sql.append(" and s.shop_category=").append(DictionaryUtils.ShopCategory.fromString(filter.get("category")).numberOfShopCategory());
		}
		if(filter.containsKey("shopName")){
			sql.append(" and s.shop_name like '%").append(filter.get("shopName")).append("%'");
		}
		
		sql.append(" and s.shop_no not in (select c.linkedshop_no from tb_shopLink c where c.shop_no=").append(shopNo).append(")");
		
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

	@Override
	public List<Advertisement> findCommodityAdvertise(long shopNo) {
		// TODO Auto-generated method stub
		String hql="from Advertisement ad where ad.shop.shopNo="+shopNo;
		@SuppressWarnings("unchecked")
		List<Advertisement> list=super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		if(list.isEmpty())
			return null;
		return list;
	}

	@Override
	public boolean checkHomePageAd(long shopNo) {
		// TODO Auto-generated method stub
		String hql="select count(1) from HomePageShopAds t where t.shop.shopNo="+shopNo;
		Long count=(Long)super.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
		if(count==0)
			return false;
		else return true;
	}

	@Override
	public HomePageShopAds findHomePageShopAdByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		String hql="from HomePageShopAds t where t.shop.shopNo="+shopNo;
		return (HomePageShopAds)super.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();

	}

	@Override
	public List<HomePageShopAds> findHomePageShopAds() {
		// TODO Auto-generated method stub
		String hql="from HomePageShopAds t where t.status=1";
		@SuppressWarnings("unchecked")
		List<HomePageShopAds> list=super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<HomePageAdvHelper> findCurHomePageAdByShopNo(long shopNo) {
		// TODO Auto-generated method stub
		String hql="from HomePageShopAds t where t.shop.shopNo="+shopNo;
		List<HomePageAdvHelper> homePageAdvList=new ArrayList<HomePageAdvHelper>();
		@SuppressWarnings("unchecked")
		List<HomePageShopAds> shopAdv=super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		for(HomePageShopAds homePageShopAds:shopAdv){
			HomePageAdvHelper homeAdvHelper=new HomePageAdvHelper();
			homeAdvHelper.setAd_rate(homePageShopAds.getAd_rate());
			homeAdvHelper.setAd_type("Shop");
			homeAdvHelper.setDays(homePageShopAds.getDays());
			homeAdvHelper.setStatus(homePageShopAds.getStatus());
			homePageAdvList.add(homeAdvHelper);
		}
		String hql2="from HomePageCommodityAds t where t.shop.shopNo="+shopNo;
		@SuppressWarnings("unchecked")
		List<HomePageCommodityAds> commodityAdvList=super.getSessionFactory().getCurrentSession().createQuery(hql2).list();
		for(HomePageCommodityAds homePageCommodityAds:commodityAdvList){
			HomePageAdvHelper homeAdvHelper=new HomePageAdvHelper();
			homeAdvHelper.setAd_rate(homePageCommodityAds.getAd_rate());
			homeAdvHelper.setAd_type("Commodity");
			homeAdvHelper.setDays(homePageCommodityAds.getDays());
			homeAdvHelper.setStatus(homePageCommodityAds.getStatus());
			homePageAdvList.add(homeAdvHelper);
		}
		return homePageAdvList;
	}

}
