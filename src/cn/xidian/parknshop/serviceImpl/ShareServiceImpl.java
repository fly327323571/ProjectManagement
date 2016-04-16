package cn.xidian.parknshop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Share;
import cn.xidian.parknshop.dao.ShareDao;
import cn.xidian.parknshop.service.ShareService;

@Service("shareService")
public class ShareServiceImpl implements ShareService{

	@Resource(name="shareDao")
	private ShareDao shareDao;

	@Override
	public void add(Share share) {
		shareDao.add(share);		
	}
}
