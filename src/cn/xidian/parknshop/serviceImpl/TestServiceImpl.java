package cn.xidian.parknshop.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Cart;
import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.TestBean;
import cn.xidian.parknshop.dao.BaseDao;
import cn.xidian.parknshop.dao.TestDao;
import cn.xidian.parknshop.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Resource(name="baseDao")
	private BaseDao<TestBean> baseDao;
	@Resource(name="TestDao")
	private TestDao<TestBean> testDao;
	@Override
	public void addTestBean(TestBean obj) {
		// TODO Auto-generated method stub
		baseDao.create(obj);
	}

	public BaseDao<TestBean> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<TestBean> baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public TestBean findBean(int id) {
		// TODO Auto-generated method stub
		return baseDao.findObjById(id, TestBean.class);
	}
	
	@Override
	public List<Commodity> searchPro(String coName) {
		// TODO Auto-generated method stub
		return testDao.searchPro(coName);
	}

	@Override
	public List<Cart> addCart(long productId, double price, int quantity, Date addTime,String userName) {
		// TODO Auto-generated method stub
		return testDao.addCart(productId, price,quantity,addTime,userName);
	}

	@Override
	public Cart findLastAddCart(String username) {
		// TODO Auto-generated method stub
		return testDao.findLastAddCart(username);
	}

	@Override
	public List<Cart> findCartByUserName(String userName, Map<String, String> filter) {
		// TODO Auto-generated method stub
		return testDao.findCartByUserName(userName, filter);
	}

	@Override
	public long countInCart(String userName) {
		// TODO Auto-generated method stub
		return testDao.countInCart(userName);
	}
	


}
