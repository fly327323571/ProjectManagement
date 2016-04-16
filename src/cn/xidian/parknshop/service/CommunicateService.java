package cn.xidian.parknshop.service;

import java.util.List;

import cn.xidian.parknshop.beans.Message;

public interface CommunicateService {

	public List<Message> findMessageList(String userName);
}
