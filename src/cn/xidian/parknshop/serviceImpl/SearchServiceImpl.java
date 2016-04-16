package cn.xidian.parknshop.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.dao.SearchDao;
import cn.xidian.parknshop.service.SearchService;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

	@Resource(name="searchDao")
	private SearchDao searchDao;
	
	@Override
	public ResultType search(String commName, Map<String, String> queryParam) {
		// TODO Auto-generated method stub
		return searchDao.search(commName, queryParam);
	}

	@Override
	public ResultType cusSearch(String commName) {
		// TODO Auto-generated method stub
		return searchDao.cusSearch(commName);
	}

	
}
