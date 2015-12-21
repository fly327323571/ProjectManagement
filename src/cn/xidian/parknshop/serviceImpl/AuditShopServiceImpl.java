package cn.xidian.parknshop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.daoImpl.AuditShopDaoImpl;
import cn.xidian.parknshop.service.AuditShopService;


@Service("auditshopserviceimpl")
public class AuditShopServiceImpl implements AuditShopService {
	
	@Resource(name="auditshopdaoimpl")
	private AuditShopDaoImpl auditShopDaoImpl;

	@Override
	public List<Map<String, Object>> getShopInfo() {
		
		return auditShopDaoImpl.getShopInfo();
	}

	@Override
	public Commodity getAllSaleCommodity() {
		
		return auditShopDaoImpl.getAllSaleCommodity();
	}

	@Override
	public List<Shop> getAllAuditPersonInfo() {
	
		return auditShopDaoImpl.getAllAuditPersonInfo();
	}

	@Override
	public int updateApproveShop(Long id) {
		
		return auditShopDaoImpl.updateApproveShop(id);
	}

	@Override
	public int updateDisapproveShop(Long id) {
		
		return auditShopDaoImpl.updateDisapproveShop(id);
	}

}
