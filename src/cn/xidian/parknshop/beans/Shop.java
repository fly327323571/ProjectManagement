package cn.xidian.parknshop.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Shop")
public class Shop {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id", unique = true, nullable = false)
	private int shopId;
	
	@Column(name = "shop_name",nullable=false,length=100)
	private String shopName;
	
	@Column(name = "shop_icon",length=100)
	private String shopIcon;
	
	@Column(name = "owner_telephone",length=20)
	private String ownerTel;
	
	@Column(name = "shop_address",length=100)
	private String shopAddr;
	
	@Column(name = "shop_description",length=500)
	private String shopDesc;
	
	@Column(name="status",length=2)
	private int status=0;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="user_id")
	private User shopOwner;
	
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
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
}
