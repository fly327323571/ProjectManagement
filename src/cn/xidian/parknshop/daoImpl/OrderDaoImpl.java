package cn.xidian.parknshop.daoImpl;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.dao.OrderDao;
import cn.xidian.parknshop.utils.DictionaryUtils.OrderStatus;
import cn.xidian.parknshop.utils.OrderHistoryHelper;
import cn.xidian.parknshop.utils.OrderHistoryHelper.OrderDetailHelper;

@Repository("orderDao")
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	@Resource
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}
	
	
	public Order getOrder(long id){
		Session session = super.getSessionFactory().getCurrentSession();
		
		return (Order)session.get(Order.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Order> findOrderByName(String buyerName) {
		String hql = "from Order as o where o.buyer.userName= :name and o.state <> 0 order by o.addTime desc";
		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", buyerName);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Order> findOrdersWithUnpayByName(String buyerName) {
		String hql = "from Order as o where o.state = 1 and o.buyer.userName= :name";

		Session session = super.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("name", buyerName);
		return query.list();
	}

	public void updateOrder(Order order) {
		super.getSessionFactory().getCurrentSession().merge(order);
	}

	// author: Fei Yue
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderHistoryHelper> findOrderHistoryByShopNo(Map<String, String> filters, long shopNo) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder();
		String productName=filters.get("productName");
		if(!productName.equals("")){
			hql.append("from OrderDetail o where o.commodity.commodityName like '%").append(productName).append("%'");
			hql.append(" and o.order.shop.shopNo=").append(shopNo);
			if(!filters.get("status").equals("0")){
				hql.append(" and o.order.state=").append(filters.get("status"));
			}
			hql.append(" and o.order.addTime>=:start");
			Query query = super.getSessionFactory().getCurrentSession().createQuery(hql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date date=null;
			try {
				date = sdf.parse(filters.get("addtime"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			query.setDate("start",date);
			List<OrderDetail> orderDetail=query.list();
			
			query.setMaxResults(Integer.valueOf(filters.get("pageSize")));
			query.setFirstResult(
					(Integer.valueOf(filters.get("pageIndex")) - 1) * Integer.valueOf(filters.get("pageSize")));
			List<Order> orderList=new ArrayList<Order>();
			for(OrderDetail od:orderDetail){
				orderList.add(od.getOrder());
			}
			List<OrderHistoryHelper> resultList = new ArrayList<OrderHistoryHelper>();
			for(int i=0;i<orderList.size();i++){
				OrderHistoryHelper helper = new OrderHistoryHelper();
				helper.setOrder(orderList.get(i));
				List<OrderDetailHelper> orderDetailList = new ArrayList<OrderDetailHelper>();
				orderDetailList.add(new OrderDetailHelper(orderDetail.get(i).getCommodity().getCommodityName(),orderDetail.get(i).getCount(),
						orderDetail.get(i).getCommodity().getCommodityImg(),orderDetail.get(i).getPrice(),
						orderDetail.get(i).getMessage(),orderDetail.get(i).getCommodity().getCommodityNo()));
				helper.setOrderDetails(orderDetailList);
				resultList.add(helper);
			}
			return resultList;
		}
		hql.append("from Order o where o.shop.shopNo=").append(shopNo);
		if(!filters.get("status").equals("0")){
			hql.append(" and o.state=").append(filters.get("status"));
		}
		hql.append(" and o.addTime>=:start");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date=null;
		try {
			date = sdf.parse(filters.get("addtime"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Query query = super.getSessionFactory().getCurrentSession().createQuery(hql.toString());
		query.setDate("start",date);
		query.setMaxResults(Integer.valueOf(filters.get("pageSize")));
		query.setFirstResult(
				(Integer.valueOf(filters.get("pageIndex")) - 1) * Integer.valueOf(filters.get("pageSize")));
		List<Order> orderHistory = query.list();
		List<OrderHistoryHelper> resultList = new ArrayList<OrderHistoryHelper>();
		for (Order o : orderHistory) {
			OrderHistoryHelper helper = new OrderHistoryHelper();
			//
			helper.setOrder(o);
			// String hql2="select new
			// OrderDetailHelper(c.commodityName,c.commodityPrice,"
			// +"o.count,o.message)"
			// + " from OrderDetail o "
			// + " inner join "
			// + " Commodity c"
			// + " on o.commodity.commodityNo=c.commodityNo"
			// + " where o.order.orderNo="+o.getOrderNo();
			List<OrderDetailHelper> orderDetailList = new ArrayList<OrderDetailHelper>();
			// List<OrderDetailHelper>
			// orderDetailList=super.getSessionFactory().getCurrentSession().createQuery(hql2).list();
			// helper.setOrderDetails(orderDetailList);
			String sql = "select " 
						+"c.commodity_name,o.commodity_count, c.commodity_image, c.commodity_price,o.message,c.commodity_no"
						+" from"
						+" tb_orderDetail o"
						+" inner join "
						+"tb_commodity c ON o.commodity_no = c.commodity_no"
						+" where o.order_no=" + o.getOrderNo();
			Query query4 = super.getSessionFactory().getCurrentSession().createSQLQuery(sql);
			List<Object[]> list = query4.list();
			for (Object[] objarray : list) {
				orderDetailList.add(new OrderDetailHelper((String) objarray[0], (int) objarray[1], (String) objarray[2],
						(double) objarray[3], (String) objarray[4],((BigInteger)objarray[5]).longValue()));
			}
			helper.setOrderDetails(orderDetailList);
			resultList.add(helper);
		}
		return resultList;
	}


	@Override
	public Order findOrderByOrderNo(long orderNo) {
		// TODO Auto-generated method stub
		String hql="from Order o where o.orderNo="+orderNo;
		return (Order) super.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
	}


	@Override
	public void updateOrderStatus(OrderStatus status,long orderNo) {
		// TODO Auto-generated method stub
		String hql="update Order o set o.state="+status.getOrderStatusIntValue()+" where o.orderNo="+orderNo;
		super.getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
	}
}
