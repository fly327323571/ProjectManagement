package cn.xidian.parknshop.beans;

import javax.persistence.Id;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_shopLink")
public class ShopLinks {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="shop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop shop;
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinColumn(name="linkedshop_no",referencedColumnName="shop_no",insertable=true,updatable=true)
	private Shop linkedShop;
	
	@Column(name="linkedShopImg",nullable=true,length=255)
	private String linkedShopImg;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Shop getLinkedShop() {
		return linkedShop;
	}

	public void setLinkedShop(Shop linkedShop) {
		this.linkedShop = linkedShop;
	}

	public String getLinkedShopImg() {
		return linkedShopImg;
	}

	public void setLinkedShopImg(String linkedShopImg) {
		this.linkedShopImg = linkedShopImg;
	}
	
}
