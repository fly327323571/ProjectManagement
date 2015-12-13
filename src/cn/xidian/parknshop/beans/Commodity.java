package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="Commodity")
public class Commodity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="commodity_id")
	private int commodityId;
	
	@Column(name="shop_id",nullable=false)
	private int shopId;
	
	@Column(name="commodity_name",nullable=false)
	private String commodityName;
	
	@Column(name="commodity_brief_info")
	private String commodityInfo;
	
	@Column(name="commodity_image")
	private String commodityImg;
	
	@Column(name="commodity_price",nullable=false)
	private double commodityPrice;
	
	@Column(name="commodity_count",nullable=false)
	private int commodityCount;
	
	@Column(name="commodity_detail")
	private String commodityDetail;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="shop_id")
	private Shop shop;

	public int getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(int commodityId) {
		this.commodityId = commodityId;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityInfo() {
		return commodityInfo;
	}

	public void setCommodityInfo(String commodityInfo) {
		this.commodityInfo = commodityInfo;
	}

	public String getCommodityImg() {
		return commodityImg;
	}

	public void setCommodityImg(String commodityImg) {
		this.commodityImg = commodityImg;
	}

	public double getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(double commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public int getCommodityCount() {
		return commodityCount;
	}

	public void setCommodityCount(int commodityCount) {
		this.commodityCount = commodityCount;
	}

	public String getCommodityDetail() {
		return commodityDetail;
	}

	public void setCommodityDetail(String commodityDetail) {
		this.commodityDetail = commodityDetail;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
	
}
