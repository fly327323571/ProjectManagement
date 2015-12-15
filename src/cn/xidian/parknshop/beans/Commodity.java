package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="tb_Commodity")
public class Commodity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="commodity_no",nullable=false,unique=true)
	private int commoditNo;
	
	@Column(name="commodity_name",nullable=false,length=200)
	private String commodityName;
	
	@Column(name="commodity_brief_info",length=200)
	private String commodityInfo;
	
	@Column(name="commodity_image",length=100)
	private String commodityImg;
	
	@Column(name="commodity_price",nullable=false)
	private double commodityPrice;
	
	@Column(name="commodity_count",nullable=false)
	private int commodityCount;
	
	@Column(name="commodity_detail",length=500)
	private String commodityDetail;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;


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

	public int getCommoditNo() {
		return commoditNo;
	}

	public void setCommoditNo(int commoditNo) {
		this.commoditNo = commoditNo;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}
	
}
