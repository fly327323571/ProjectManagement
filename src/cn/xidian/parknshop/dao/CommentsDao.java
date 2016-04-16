package cn.xidian.parknshop.dao;

import org.springframework.stereotype.Repository;

import cn.xidian.parknshop.beans.Comments;

@Repository("orderDao")
public interface CommentsDao {
	void add(Comments c);
}
