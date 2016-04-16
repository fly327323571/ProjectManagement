package cn.xidian.parknshop.service;

import java.util.List;

import cn.xidian.parknshop.beans.OrderDetail;

public interface OrderDetailService {
	
	public List<OrderDetail> getDetailInfo(String userName, long commodityNo);
	public OrderDetail getOrderDetail(long id);
	//author : FeiYue
	List<OrderDetail> getDetailByOrderNo(long orderNo);
}
