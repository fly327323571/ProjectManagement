package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tb_cart")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@OneToOne
	@JoinColumn(name="username",referencedColumnName="username",insertable=true,updatable=true)
	private User owner;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="commodity_no",referencedColumnName="commodity_no",insertable=true,updatable=true)
	private Commodity commodity;

	@Column(name="commodity_count")
	private int commodityNum;
	
	@Column(name="sum_price")
	private double price;
	
	@Column(name="status")
	private int status=0;
	
	@Temporal(TemporalType.DATE) 
	private Date addTime;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public int getCommodityNum() {
		return commodityNum;
	}

	public void setCommodityNum(int commodityNum) {
		this.commodityNum = commodityNum;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


}
