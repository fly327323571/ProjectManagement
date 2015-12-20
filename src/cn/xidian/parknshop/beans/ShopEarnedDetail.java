package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_shopEarnedDetail")
public class ShopEarnedDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	

	@OneToOne
	@JoinColumn(name="order_no",referencedColumnName="order_no",insertable=true,updatable=true)
	private Order order;
	
	@Column(name="share_rate",nullable=false)
	private double shareRate;
	
	@Column(name="realEarn",nullable=false)
	private double realEarn;
	 
	@Column(name="finishTime",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date time;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public double getShareRate() {
		return shareRate;
	}

	public void setShareRate(double shareRate) {
		this.shareRate = shareRate;
	}

	public double getRealEarn() {
		return realEarn;
	}

	public void setRealEarn(double realEarn) {
		this.realEarn = realEarn;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	} 
}
