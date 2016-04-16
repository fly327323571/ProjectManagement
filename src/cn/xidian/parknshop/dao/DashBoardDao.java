package cn.xidian.parknshop.dao;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Odium;

public interface DashBoardDao {

	List<Odium> findNewCommentsByShopNoAndFilters(Map<String,String> filters,long shopNo);
	
	void createComplain(Comments comment,Odium odium);
}
