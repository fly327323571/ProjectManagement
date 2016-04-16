package cn.xidian.parknshop.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.*;

public interface TestDao<T> {

	void create(T t);
	
	void delete(T t);
	 
	void update(T t);
	 
	T findObjById(int id,Class<T> type);
	
	int getTotalCount(Class<T> type);
	
	List<T> getPage(Class<T> type,int PageNo,int PageSize,int StartNo);
	
	List<T> getAll(Class<T> type);

	List<Commodity> searchPro(String coName);

	List<Cart> addCart(long productId, double price, int quantity, Date addTime,String userName);
	
	Cart findLastAddCart(String username);
	
	List<Cart> findCartByUserName(String userName,Map<String,String> filter);
	
	long countInCart(String userName);


}
