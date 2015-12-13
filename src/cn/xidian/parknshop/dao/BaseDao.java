package cn.xidian.parknshop.dao;

import java.util.List;

public interface BaseDao<T> {

	void create(T t);
	
	void delete(T t);
	 
	void update(T t);
	 
	T findObjById(int id,Class<T> type);
	
	int getTotalCount(Class<T> type);
	
	List<T>getPage(Class<T> type,int PageNo,int PageSize,int StartNo);
	
	List<T> getAll(Class<T> type);

}
