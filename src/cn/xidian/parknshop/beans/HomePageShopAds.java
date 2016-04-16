package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_shop_ad")
public class HomePageShopAds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@OneToOne
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@Column(name="shop_link",length=200,nullable=false)
	private String shopLink;
	
	@Temporal(TemporalType.DATE)
	private Date startTime;
	
	@Column(name="days",nullable=false)
	private int days;
	
	@Column(name="ad_rate")
	private double ad_rate;
	
	@Column(name="status")
	private int status=0;

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

	public String getShopLink() {
		return shopLink;
	}

	public void setShopLink(String shopLink) {
		this.shopLink = shopLink;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public double getAd_rate() {
		return ad_rate;
	}

	public void setAd_rate(double ad_rate) {
		this.ad_rate = ad_rate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
}
