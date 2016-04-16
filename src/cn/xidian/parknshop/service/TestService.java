package cn.xidian.parknshop.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.*;


public interface TestService {
	public void addTestBean(TestBean obj);
	
	public TestBean findBean(int id);

	public List<Commodity> searchPro(String coName);

	public List<Cart> addCart(long productId, double price, int quantity, Date addTime,String userName);

	public Cart findLastAddCart(String username);
	
	List<Cart> findCartByUserName(String userName, Map<String, String> filter) ;
	
	long countInCart(String userName);
}
