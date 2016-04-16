package cn.xidian.parknshop.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.HomePageCommodityAds;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.beans.Share;
import cn.xidian.parknshop.beans.ShopEarnedDetail;
import cn.xidian.parknshop.dao.SummaryDao;

@Repository("SummaryDao")
public class SummaryDaoImpl extends HibernateDaoSupport implements SummaryDao{
	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Order> findOrderSucess(String shopName) {
		String hql = "from Order o where o.shop.shopName=:shopName";
		@SuppressWarnings("unchecked")
		List<Order> list1 = super.getSessionFactory().getCurrentSession().createQuery(hql).setString("shopName", shopName).list();
		return list1;
		
	}

	@Override
	public List<Order> findOrderSucess() {
		String hql = "from Order o";
		@SuppressWarnings("unchecked")
		List<Order> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<OrderDetail> findOrderDetailSuccess() {
		String hql = "from OrderDetail o where o.order.state <> 0";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public int findOrderNonPayment() {
		String hql = "from Order o where o.state = 0";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findOrderNotRecieving() {
		String hql = "from Order o where o.state = 1";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findOrderRecieved() {
		String hql = "from Order o where o.state = 2";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findOrderOnlinePayment() {
		String hql = "from Order o where o.payWay = 0";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findOrderCashOnDeliery() {
		String hql = "from Order o where o.payWay = 1";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findCommodityOfFood() {
		String hql = "from Commodity c where c.category = 0";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findCommodityOfClothes() {
		String hql = "from Commodity c where c.category = 1";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findShopStateApplyed() {
		String hql = "from Shop s where s.status = 0";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findShopApplyFailure() {
		String hql = "from Shop s where s.status = 1";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findShopShutdown() {
		String hql = "from Shop s where s.status = 2";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public int findShopWarning() {
		String hql = "from Shop s where s.status = 3";
		@SuppressWarnings("unchecked")
		List<OrderDetail> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list.size();
	}

	@Override
	public List<Share> findShare() {
		String hql = "from Share s";
		@SuppressWarnings("unchecked")
		List<Share> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<Share> findShare(String shopName) {
		String hql = "from Share s where s.shop.shopName=:shopName";
		@SuppressWarnings("unchecked")
		List<Share> list1 = super.getSessionFactory().getCurrentSession().createQuery(hql).setString("shopName", shopName).list();
		return list1;
		
	}

	@Override
	public void createShare(Share share) {
		super.getSessionFactory().getCurrentSession().save(share);
		super.getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public List<HomePageCommodityAds> findAllNotJudgedHomePageCommodityAds() {
		String hql = "from HomePageCommodityAds h where h.status = 0";
		@SuppressWarnings("unchecked")
		List<HomePageCommodityAds> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<HomePageShopAds> findAllNotJudgedHomePageShopAds() {
		String hql = "from HomePageShopAds h where h.status = 0";
		@SuppressWarnings("unchecked")
		List<HomePageShopAds> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<HomePageCommodityAds> findNotJudgedHomePageCommodityAds(String shopName) {
		String hql = "from HomePageCommodityAds h where h.status = 0 and h.shop.shopName like '%" + shopName + "%'";
		@SuppressWarnings("unchecked")
		List<HomePageCommodityAds> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public List<HomePageShopAds> findNotJudgedHomePageShopAds(String shopName) {
		String hql = "from HomePageShopAds h where h.status = 0 and h.shop.shopName like '%" + shopName + "%'";
		@SuppressWarnings("unchecked")
		List<HomePageShopAds> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public void updateHomePageCommodityAds(HomePageCommodityAds homePageCommodityAds) {
		super.getSessionFactory().getCurrentSession().update(homePageCommodityAds);
		super.getSessionFactory().getCurrentSession().flush();
		
		
	}

	@Override
	public void updateHomePageShopAds(HomePageShopAds homePageShopAds) {
		super.getSessionFactory().getCurrentSession().update(homePageShopAds);
		super.getSessionFactory().getCurrentSession().flush();
		
	}

	@Override
	public HomePageCommodityAds findHomePageCommodityAdsById(long id) {
		String hql = "from HomePageCommodityAds h where h.id = " + id;
		HomePageCommodityAds ad = (HomePageCommodityAds)super.getSessionFactory().getCurrentSession().createQuery(hql).list().get(0);
		
		return ad;
	}

	@Override
	public HomePageShopAds findHomePageShopAdsById(long id) {
		String hql = "from HomePageShopAds h where h.id = " + id;
		HomePageShopAds ad = (HomePageShopAds)super.getSessionFactory().getCurrentSession().createQuery(hql).list().get(0);
		
		return ad;
	}

	@Override
	public List<HomePageCommodityAds> findAllSuccessHomePageCommodityAds() {
		String hql = "from HomePageCommodityAds h where h.status <> 0 and h.status <> 2";
		@SuppressWarnings("unchecked")
		List<HomePageCommodityAds> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	

	@Override
	public List<HomePageShopAds> findAllSucessHomePageShopAds() {
		String hql = "from HomePageShopAds h where h.status <> 0 and h.status <> 2";
		@SuppressWarnings("unchecked")
		List<HomePageShopAds> list = super.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return list;
	}

	@Override
	public void createShopEarnedDetail(ShopEarnedDetail shopEarnedDetail) {
		// TODO Auto-generated method stub
		super.getSessionFactory().getCurrentSession().save(shopEarnedDetail);
		super.getSessionFactory().getCurrentSession().flush();
	}

	
	
	
	
}
