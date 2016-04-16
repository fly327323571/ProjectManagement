package cn.xidian.parknshop.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="tb_order")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private long Id;
	
	@Column(name="order_no",nullable=false,unique=true)
	private long orderNo;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="buyer_name",referencedColumnName="username",insertable=true,updatable=true)
	private User buyer;
	

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="seller_name",referencedColumnName="username",insertable=true,updatable=true)
	private User seller;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@Column(name="status")
	private int state=0;
	
	@Column(name="delivery_status")
	private int deliveryStatus=0;
	
	@Column(name="pay_way")
	private int payWay=1;
	
	@Column(name="order_price",nullable=false)
	private double orderPrice=0.0;
	
	@Temporal(TemporalType.DATE)
	private Date addTime;
	
	@Column(name="toAddr",length=200,nullable=false)
	private String toAddr;

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public int getPayWay() {
		return payWay;
	}

	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}


	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}



	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getToAddr() {
		return toAddr;
	}

	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}

}
