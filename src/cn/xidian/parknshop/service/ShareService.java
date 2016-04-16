package cn.xidian.parknshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xidian.parknshop.beans.Share;

@Service
@Transactional
public interface ShareService {
	void add(Share share);
}
