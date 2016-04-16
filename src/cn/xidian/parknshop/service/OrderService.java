package cn.xidian.parknshop.service;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.utils.OrderHistoryHelper;
import cn.xidian.parknshop.utils.DictionaryUtils.OrderStatus;


@Service
@Transactional
public interface OrderService {
	
	public List<Order> findOrdersByName(String name);
	public List<Order> findOrdersWithUnpayByName(String buyerName);
	public void updateOrder(Order order);
	public Order getOrder(long id);
	//author :FeiYue
	List<OrderHistoryHelper> findOrderHistoryByShopNo(Map<String,String> filters,long shopNo);
	
	Order findOrderByOrderNo(long orderNo);
	
	void updateOrderStatus(OrderStatus status,long orderNo);
}
	
