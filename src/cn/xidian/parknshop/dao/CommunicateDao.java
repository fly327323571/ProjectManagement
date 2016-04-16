package cn.xidian.parknshop.dao;

import java.util.List;

import cn.xidian.parknshop.beans.Message;

public interface CommunicateDao {
	List<Message> findMessageList(String userName);
}
