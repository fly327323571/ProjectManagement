package cn.xidian.parknshop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.TestBean;
import cn.xidian.parknshop.dao.BaseDao;
import cn.xidian.parknshop.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Resource
	private BaseDao<TestBean> baseDao;
	
	@Override
	public void test(TestBean obj) {
		// TODO Auto-generated method stub
		baseDao.saveObj(obj);
	}

	public BaseDao<TestBean> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<TestBean> baseDao) {
		this.baseDao = baseDao;
	}

}
