package cn.xidian.parknshop.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="tb_Shop")
public class Shop implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", unique = true, nullable = false)
	private long shopId;
	
	@Column(name = "shop_no", unique = true, nullable = false)
	private long shopNo;
	
	@Column(name="shop_category",nullable=false)
	private int shopCategories;
	
	@Column(name = "shop_name",nullable=false,length=100)
	private String shopName;
	
	@Column(name="shop_rank",nullable=false)
	private double shopRank=1;
	
	@Column(name = "shop_icon",length=200)
	private String shopIcon;
	
	@Column(name = "owner_telephone",length=20)
	private String ownerTel;
	
	@Column(name = "shop_address",length=100)
	private String shopAddr;
	
	@Column(name = "shop_description",length=500)
	private String shopDesc;
	
	@Column(name="shop_source",nullable=false)
	private int shopSourse;
	
	@Column(name="shop_state",length=2)
	private int status=0;
	
	@Column(name="remarks",length=200)
	private String remarks;
	
	@Column(name="registerTime",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date regTime;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="username",referencedColumnName="username",insertable =true,updatable =true)
	private User shopOwner;
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopIcon() {
		return shopIcon;
	}
	public void setShopIcon(String shopIcon) {
		this.shopIcon = shopIcon;
	}
	
	public String getOwnerTel() {
		return ownerTel;
	}
	public void setOwnerTel(String ownerTel) {
		this.ownerTel = ownerTel;
	}
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public User getShopOwner() {
		return shopOwner;
	}
	public void setShopOwner(User shopOwner) {
		this.shopOwner = shopOwner;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getShopNo() {
		return shopNo;
	}
	public void setShopNo(long shopNo) {
		this.shopNo = shopNo;
	}
	public double getShopRank() {
		return shopRank;
	}
	public void setShopRank(double shopRank) {
		this.shopRank = shopRank;
	}
	public long getShopId() {
		return shopId;
	}
	public void setShopId(long shopId) {
		this.shopId = shopId;
	}
	public int getShopSourse() {
		return shopSourse;
	}
	public void setShopSourse(int shopSourse) {
		this.shopSourse = shopSourse;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public int getShopCategories() {
		return shopCategories;
	}
	public void setShopCategories(int shopCategories) {
		this.shopCategories = shopCategories;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
}
