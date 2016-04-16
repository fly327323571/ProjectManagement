package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.CollectCommodity;
import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.CollectCommodityDao;
import cn.xidian.parknshop.service.CollectCommodityService;

@Service("collectCommodityService")
public class CollectCommodityServiceImpl implements CollectCommodityService{

	@Resource(name="collectCommodityDao")
	private CollectCommodityDao collectCommodityDao;

	@Override
	public void add(CollectCommodity collectCommodity) {
		collectCommodityDao.add(collectCommodity);		
	}
	
	public List<CollectCommodity> findCollectCommodityByUser(User user) {
		return collectCommodityDao.findCollectCommodityByUser(user);
	}

	@Override
	public CollectCommodity findCollectCommodityByUserAndCommodity(User user, Commodity commodity) {
		
		return collectCommodityDao.findCollectCommodityByUserAndCommodity(user, commodity);
	};
}
