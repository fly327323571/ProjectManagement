package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.Order;


public interface OrderDao {
	public List<Order> findOrderByName(String buyerName);
	public List<Order> findOrdersWithUnpayByName(String buyerName);
}