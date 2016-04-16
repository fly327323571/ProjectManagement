package cn.xidian.parknshop.beans;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name="tb_shopLink")
public class ShopLinks {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private long Id;
	
	@Column(name="shop_no",nullable=false)
	private long shop_no;
	
	@Column(name="linkedShop_no",nullable=false)
	private long linkedShop_no;
	
	@Column(name="linkedShopImg",nullable=true,length=255)
	private String linkedShopImg;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}


	public String getLinkedShopImg() {
		return linkedShopImg;
	}

	public void setLinkedShopImg(String linkedShopImg) {
		this.linkedShopImg = linkedShopImg;
	}

	public long getLinkedShop_no() {
		return linkedShop_no;
	}

	public void setLinkedShop_no(long linkedShop_no) {
		this.linkedShop_no = linkedShop_no;
	}

	public long getShop_no() {
		return shop_no;
	}

	public void setShop_no(long shop_no) {
		this.shop_no = shop_no;
	}
	
}
