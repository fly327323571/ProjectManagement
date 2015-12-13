package cn.xidian.parknshop.dao;

public interface BaseDao<T> {

	public void saveObj(T obj);
	
	public T findObjById(int id,Class<T> type);
}
