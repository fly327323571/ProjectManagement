package cn.xidian.parknshop.dao;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.utils.DictionaryUtils.OrderStatus;
import cn.xidian.parknshop.utils.OrderHistoryHelper;


public interface OrderDao {
	public List<Order> findOrderByName(String buyerName);
	public List<Order> findOrdersWithUnpayByName(String buyerName);
	public void updateOrder(Order order);
	public Order getOrder(long id);
	
	//author: FeiYue
	List<OrderHistoryHelper> findOrderHistoryByShopNo(Map<String,String> filters,long shopNo);
	
	//author: FeiYue
	Order findOrderByOrderNo(long orderNo);
	
	//author: FeiYue
	void updateOrderStatus(OrderStatus status,long orderNo);
	
	
}