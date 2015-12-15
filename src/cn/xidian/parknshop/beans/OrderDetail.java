package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="tb_orderDetail")
public class OrderDetail {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private long id;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="commodity_no",referencedColumnName="commodity_no",insertable=true,
				updatable=true)
	private Commodity commodity;
	
	@Column(name="commodity_count")
	private int count=0;
	
	@Column(name="sum_price")
	private double price=0.0;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="order_no",referencedColumnName="order_no",insertable=true,
				updatable=true)
	private Order order;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
