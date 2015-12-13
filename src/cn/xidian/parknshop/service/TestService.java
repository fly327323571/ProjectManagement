package cn.xidian.parknshop.service;

import cn.xidian.parknshop.beans.TestBean;

public interface TestService {
	public void addTestBean(TestBean obj);
	
	public TestBean findBean(int id);
}
