package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="Delivery")
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="delivery_id")
	private int deliveryId;

	@Column(name="delivery_type",length=20)
	private String deliveryType;
	
	@Column(name="delivery_no",length=20)
	private String deliveryNo;
	
	@OneToOne
	@JoinColumn(name="order_id")
	private Orders order;

	public int getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(int deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}
}
