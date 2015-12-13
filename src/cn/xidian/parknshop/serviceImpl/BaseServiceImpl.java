package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import cn.xidian.parknshop.dao.BaseDao;
import cn.xidian.parknshop.service.BaseService;

public class BaseServiceImpl<T> implements BaseService<T> {

	@Resource
	private BaseDao<T> baseDao;
	
	@Override
	public void create(T obj) {
		// TODO Auto-generated method stub
		baseDao.create(obj);
	}

	@Override
	public void delete(T obj) {
		// TODO Auto-generated method stub
		baseDao.delete(obj);
	}

	@Override
	public void update(T obj) {
		// TODO Auto-generated method stub
		baseDao.update(obj);
	}

	@Override
	public int getTotalCount(Class<T> type) {
		// TODO Auto-generated method stub
		return baseDao.getTotalCount(type);
	}

	@Override
	public List<T> getPage(Class<T> type, int PageNo,int PageSize,int StartNo) {
		// TODO Auto-generated method stub
		return baseDao.getPage(type, PageNo, PageSize, StartNo);
	}


	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public T get(int id, Class<T> type) {
		// TODO Auto-generated method stub
		return baseDao.findObjById(id, type);
	}

	@Override
	public List<T> getAll(Class<T> type) {
		// TODO Auto-generated method stub
		return baseDao.getAll(type);
	}

}
