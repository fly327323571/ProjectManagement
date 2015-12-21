package cn.xidian.parknshop.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.dao.OrderDao;


@Service
@Transactional
public interface OrderService {
	
	public List<Order> findOrdersByName(String name);

}
	
