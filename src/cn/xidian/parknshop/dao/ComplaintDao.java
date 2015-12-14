package cn.xidian.parknshop.dao;

import java.util.List;
import java.util.Map;

import cn.xidian.parknshop.beans.Complaint;

public interface ComplaintDao<T> {

	public List<Complaint> findAll();

	/*public List<Map<String, String>> findUncheckedComplaint(String state);*/
}
