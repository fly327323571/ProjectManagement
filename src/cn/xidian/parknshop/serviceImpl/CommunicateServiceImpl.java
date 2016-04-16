package cn.xidian.parknshop.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Message;
import cn.xidian.parknshop.dao.CommunicateDao;
import cn.xidian.parknshop.service.CommunicateService;

@Service("communicateService")
public class CommunicateServiceImpl implements CommunicateService {

	@Resource(name="communicateDao")
	private CommunicateDao communicateDao;
	
	@Override
	public List<Message> findMessageList(String userName) {
		// TODO Auto-generated method stub
		return communicateDao.findMessageList(userName);
	}

}
