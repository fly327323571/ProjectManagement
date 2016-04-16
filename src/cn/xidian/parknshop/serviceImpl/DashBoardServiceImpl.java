package cn.xidian.parknshop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Odium;
import cn.xidian.parknshop.dao.DashBoardDao;
import cn.xidian.parknshop.service.DashBoardService;

@Service("dashBoardService")
public class DashBoardServiceImpl implements DashBoardService {

	@Resource(name="dashBoardDao")
	private DashBoardDao dashBoardDao;
	@Override
	public List<Odium> findNewCommentsByShopNoAndFilters(Map<String, String> filters, long shopNo) {
		// TODO Auto-generated method stub
		return dashBoardDao.findNewCommentsByShopNoAndFilters(filters, shopNo);
	}
	@Override
	public void createComplain(Comments comment, Odium odium) {
		// TODO Auto-generated method stub
		dashBoardDao.createComplain(comment, odium);
	}

}
