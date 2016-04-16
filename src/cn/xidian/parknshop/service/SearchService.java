package cn.xidian.parknshop.service;

import java.util.Map;

import cn.xidian.parknshop.beans.ResultType;

public interface SearchService {

	ResultType search(String commName,Map<String,String> queryParam);
	
	ResultType cusSearch(String commName);
}
