package cn.xidian.parknshop.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tb_commodity")
public class Commodity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="commodity_no",nullable=false,unique=true)
	private long commodityNo;
	
	@Column(name="commodity_name",nullable=false,length=200)
	private String commodityName;
	
	@Column(name="commodity_brief_info",length=200)
	private String commodityInfo;
	
	@Column(name="commodity_category",length=2,nullable=false)
	private int category;
	
	@Column(name="commodity_salevolumn",nullable=false)
	private long saleVolume=0;
	
	@Column(name="commodity_image",length=100)
	private String commodityImg;
	
	@Column(name="commodity_avgRank")
	private double avgRank=0D;
	
	@Column(name="commodity_price",nullable=false)
	private double commodityPrice;
	
	@Column(name="commodity_count",nullable=false)
	private int commodityCount;
	
	@Column(name="commodity_detail",length=500)
	private String commodityDetail;
	
	@Column(name="commodity_addTime",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date addTime;
	
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
	
	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public long getSaleVolumn() {
		return saleVolume;
	}

	public void setSaleVolumn(long saleVolume) {
		this.saleVolume = saleVolume;
	}

	public double getAvgRank() {
		return avgRank;
	}

	public void setAvgRank(double avgRank) {
		this.avgRank = avgRank;
	}

	public long getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(long commodityNo) {
		this.commodityNo = commodityNo;
	}
	
	@Override
	public String toString(){
		return this.commodityNo+"-"+this.commodityName;
	}
}
