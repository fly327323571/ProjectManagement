package cn.xidian.parknshop.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_commodity_ad")
public class HomePageCommodityAds {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@OneToOne
	@JoinColumn(name="commodity_no",referencedColumnName="commodity_no",insertable=true,updatable=true)
	private Commodity commodity;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@Column(name="commodity_link",length=200,nullable=false)
	private String commodityLink;
	
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

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getCommodityLink() {
		return commodityLink;
	}

	public void setCommodityLink(String commodityLink) {
		this.commodityLink = commodityLink;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}
}
