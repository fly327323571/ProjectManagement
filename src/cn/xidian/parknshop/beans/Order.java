package cn.xidian.parknshop.beans;


import javax.persistence.*;

@Entity
@Table(name="tb_Order")
public class Order{

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
	@JoinColumn(name="seller_id")
	private User seller;
	
	@Column(name="status")
	private int state=0;
	

	@Column(name="pay_way")
	private int payWay=1;
	
	@Column(name="post_way")
	private int postWay=0;
	
	@Column(name="order_price",nullable=false)
	private double orderPrice=0.0;


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

	public int getPostWay() {
		return postWay;
	}

	public void setPostWay(int postWay) {
		this.postWay = postWay;
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





}
