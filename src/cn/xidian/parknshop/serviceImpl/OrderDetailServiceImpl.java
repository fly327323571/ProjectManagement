package cn.xidian.parknshop.serviceImpl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.xidian.parknshop.beans.OrderDetail;
import cn.xidian.parknshop.daoImpl.OrderDetailDaoImpl;
import cn.xidian.parknshop.service.OrderDetailService;

@Service("OrderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Resource(name="orderDetailDaoImpl")
	private OrderDetailDaoImpl orderDetailDaoImpl;

	@Override
	public List<OrderDetail> getDetailInfo(String userName, long commodityNo) {
		
		return orderDetailDaoImpl.getDetailInfo(userName, commodityNo);
	}

	@Override
	public List<OrderDetail> getDetailByOrderNo(long orderNo) {
		// TODO Auto-generated method stub
		return orderDetailDaoImpl.getDetailByOrderNo(orderNo);
	}
	
	public OrderDetail getOrderDetail(long id){
		
		return orderDetailDaoImpl.getOrderDetail(id);
	}

}
