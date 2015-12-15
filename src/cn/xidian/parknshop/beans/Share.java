package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tb_share")
public class Share {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long id;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@Column(name="share_rate",nullable=false)
	private double rate;
	
	@Column(name="turnover",nullable=false)
	private double turnover;
	
	@Column(name="share_money",nullable=false)
	private double shareMoney;
	
	@Column(name="share_time",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date shareTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	public double getShareMoney() {
		return shareMoney;
	}

	public void setShareMoney(double shareMoney) {
		this.shareMoney = shareMoney;
	}

	public Date getShareTime() {
		return shareTime;
	}

	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}
}
