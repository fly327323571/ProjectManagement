package cn.xidian.parknshop.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Shop")
public class Shop {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id", unique = true, nullable = false)
	private long shopId;
	@Column(name = "shop_name",nullable=false)
	private String shopName;
	@Column(name = "shop_icon")
	private String shopIcon;
	@Column(name = "owner_id")
	private long ownerId;
	@Column(name = "owner_telephone")
	private String ownerTel;
	@Column(name = "shop_address")
	private String shopAddr;
	@Column(name = "shop_description")
	private String shopDesc;
	public long getShopId() {
		return shopId;
	}
	public void setShopId(long shopId) {
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
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
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
}
