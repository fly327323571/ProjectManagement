package cn.xidian.parknshop.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.dao.CommentsDao;
import cn.xidian.parknshop.service.CommentsService;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService{
	@Resource(name="commentsDao")
	private CommentsDao commentsDao;
	
	public void add(Comments c){
		commentsDao.add(c);
	}
}
