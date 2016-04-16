package cn.xidian.parknshop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.ShopEarnedDetail;
import cn.xidian.parknshop.dao.InComeDao;
import cn.xidian.parknshop.service.InComeService;

@Service("inComeService")
public class InComeServiceImpl implements InComeService {

	@Resource(name="inComeDao")
	private InComeDao inComeDao;
	
	@Override
	public List<ShopEarnedDetail> findShopEarnByFilters(Map<String, String> filter, long shopNo) {
		// TODO Auto-generated method stub
		return inComeDao.findShopEarnByFilters(filter, shopNo);
	}

}
