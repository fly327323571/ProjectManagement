package cn.xidian.parknshop.utils;

import java.util.ArrayList;

import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.Shop;

public class IncomeInfo {
	String shopName;
	long shopNo;
	double transaction;
	Shop shop;
	ArrayList<Order> array = new ArrayList<Order>();
	
	public ArrayList<Order> getArray() {
		return array;
	}
	public void setArray(ArrayList<Order> array) {
		this.array = array;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public long getShopNo() {
		return shopNo;
	}
	public void setShopNo(long shopNo) {
		this.shopNo = shopNo;
	}
	public double getTransaction() {
		return transaction;
	}
	public void setTransaction(double transaction) {
		this.transaction = transaction;
	}
	
}
