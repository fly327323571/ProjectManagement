package cn.xidian.parknshop.beans;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Orders")
public class Orders{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="buyer_id")
	private User buyer;
	
	@OneToMany(mappedBy="commodityId")
	private Set<Commodity> commodity;
	
	@Column(name="status")
	private int state=0;
	
	@Column(name="product_num")
	private int productNum=1;

	@Column(name="pay_way")
	private int payWay=1;
	
	@Column(name="post_way")
	private int postWay=0;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="seller_id")
	private User seller;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

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

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
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

	public Set<Commodity> getCommodity() {
		return commodity;
	}

	public void setCommodity(Set<Commodity> commodity) {
		this.commodity = commodity;
	}



}
