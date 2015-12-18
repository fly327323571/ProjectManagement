package cn.xidian.parknshop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.ShopOwner;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.dao.ShopOwnerDao;
import cn.xidian.parknshop.service.ShopOwnerService;

@Service("shopOwnerService")
public class ShopOwnerServiceImpl implements ShopOwnerService {

	@Resource(name="shopOwnerDao")
	private ShopOwnerDao shopOwnerDao;
	
	@Override
	public void createShopOwner(User customer, ShopOwner shopOwner) {
		// TODO Auto-generated method stub
		shopOwnerDao.createShopOwner(customer, shopOwner);
	}

}
