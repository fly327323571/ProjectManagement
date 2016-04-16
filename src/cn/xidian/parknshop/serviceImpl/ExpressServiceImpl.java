package cn.xidian.parknshop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.service.ExpressService;
import cn.xidian.parknshop.dao.ExpressDao;
import cn.xidian.parknshop.beans.Express;

@Service("expressService")
public class ExpressServiceImpl implements ExpressService {
	
	@Resource(name="expressDao")
	ExpressDao expressDao;
	
	@Override
	public Express getExpressByOrderNo(long orderNo) {
		
		return expressDao.getExpressByOrderNo(orderNo);
	}
}
