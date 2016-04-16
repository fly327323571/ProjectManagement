package cn.xidian.parknshop.utils;

import java.util.List;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.OrderDetail;


public class OrderHistoryHelper {

	private Order order;
	
	private List<OrderDetail> orderDetail;
	
	private List<Commodity> commodityOfOrder;
	
	private List<OrderDetailHelper> orderDetails;
	
	public static class OrderDetailHelper{
		
		private long commodityNo;
		
		private String commodityName;
		
		private int commodityCount;
		
		private String commodityImg;
		
		private double commodityPrice;
		
		private String message;
	

		public OrderDetailHelper(String commodityName,
								int commodityCount,String commodityImg,double commodityPrice,
								String message,long commodityNo){
			this.commodityName=commodityName;
			this.commodityCount=commodityCount;
			this.commodityImg=commodityImg;
			this.message=message;
			this.commodityPrice=commodityPrice;
			this.commodityNo=commodityNo;
		}
		
		public String getCommodityName() {
			return commodityName;
		}

		public void setCommodityName(String commodityName) {
			this.commodityName = commodityName;
		}

		public int getCommodityCount() {
			return commodityCount;
		}

		public void setCommodityCount(int commodityCount) {
			this.commodityCount = commodityCount;
		}

		public double getCommodityPrice() {
			return commodityPrice;
		}

		public void setCommodityPrice(double commodityPrice) {
			this.commodityPrice = commodityPrice;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getCommodityImg() {
			return commodityImg;
		}

		public void setCommodityImg(String commodityImg) {
			this.commodityImg = commodityImg;
		}

		public long getCommodityNo() {
			return commodityNo;
		}

		public void setCommodityNo(long commodityNo) {
			this.commodityNo = commodityNo;
		}
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Commodity> getCommodityOfOrder() {
		return commodityOfOrder;
	}

	public void setCommodityOfOrder(List<Commodity> commodityOfOrder) {
		this.commodityOfOrder = commodityOfOrder;
	}

	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<OrderDetailHelper> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailHelper> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
