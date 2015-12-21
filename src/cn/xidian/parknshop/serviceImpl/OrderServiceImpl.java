package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.dao.OrderDao;
import cn.xidian.parknshop.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource(name="orderDao")
	private OrderDao orderDao;
	

	@Override
	public List<Order> findOrdersByName(String name) {
		
		return orderDao.findOrderByName(name);
	}
}