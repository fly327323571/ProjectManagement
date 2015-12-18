package cn.xidian.parknshop.beans;

import javax.persistence.*;

@Entity
@Table(name="tb_advertisement")
public class Advertisement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private long id;
	
	@ManyToOne
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@OneToOne
	@JoinColumn(name="commodity_no",referencedColumnName="commodity_no",insertable=true,updatable=true)
	private Commodity commodity;
	
	@Column(name="ad_type")
	private int adType=1;
	
	@Column(name="ad_url")
	private String adUrl;
	
	@Column(name="ad_content",nullable=false,length=200)
	private String adContent;
	
	@Column(name="ad_img",length=100)
	private String adImg;

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

	public String getAdUrl() {
		return adUrl;
	}

	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}

	public int getAdType() {
		return adType;
	}

	public void setAdType(int adType) {
		this.adType = adType;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public String getAdImg() {
		return adImg;
	}

	public void setAdImg(String adImg) {
		this.adImg = adImg;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
}
