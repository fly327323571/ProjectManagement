package cn.xidian.parknshop.service;

import java.util.List;

public interface BaseService<T>{

    T get(long id,Class<T> type);
    
    void create(T obj);
    
    void delete(T obj);
    
    void update(T obj);
    
    int getTotalCount(Class<T> type);
    
    List<T> getPage(Class<T> type, int PageNo,int PageSize,int StartNo);
    
    List<T> getAll(Class<T> type);
}
