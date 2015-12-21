package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.OrderDetail;

public interface OrderDetailDao {
	
	public List<OrderDetail> getDetailInfo(String userName, long commodityNo);
}
