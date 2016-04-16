package cn.xidian.parknshop.dao;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.ResultType;

public interface SearchDao {

	ResultType search(String commName,Map<String, String> queryParam);
	
	ResultType cusSearch(String commName);
}
