package cn.xidian.parknshop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.dao.OrderDao;
import cn.xidian.parknshop.service.OrderService;
import cn.xidian.parknshop.utils.DictionaryUtils.OrderStatus;
import cn.xidian.parknshop.utils.OrderHistoryHelper;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource(name="orderDao")
	private OrderDao orderDao;
	
	@Override
	public Order getOrder(long id){
		return orderDao.getOrder(id);
	}
	
	@Override
	public List<Order> findOrdersByName(String name) {
		
		return orderDao.findOrderByName(name);
	}
	
	@Override
	public List<Order> findOrdersWithUnpayByName(String buyerName) {
		
		return orderDao.findOrdersWithUnpayByName(buyerName);
	}
	
	@Override
	public void updateOrder(Order order) {
		orderDao.updateOrder(order);
	}

	@Override
	public List<OrderHistoryHelper> findOrderHistoryByShopNo(Map<String,String> filters,long shopNo) {
		// TODO Auto-generated method stub
		return orderDao.findOrderHistoryByShopNo(filters,shopNo);
	}

	@Override
	public Order findOrderByOrderNo(long orderNo) {
		// TODO Auto-generated method stub
		return orderDao.findOrderByOrderNo(orderNo);
	}

	@Override
	public void updateOrderStatus(OrderStatus status, long orderNo) {
		// TODO Auto-generated method stub
		orderDao.updateOrderStatus(status, orderNo);
		
	}
	
}
