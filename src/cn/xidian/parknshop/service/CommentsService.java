package cn.xidian.parknshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xidian.parknshop.beans.Comments;

@Service
@Transactional
public interface CommentsService {
	void add(Comments c);
}
