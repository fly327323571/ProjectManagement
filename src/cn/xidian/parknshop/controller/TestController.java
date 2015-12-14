package cn.xidian.parknshop.controller;



import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xidian.parknshop.beans.TestBean;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.TestService;

@Controller
public class TestController {
	@Resource(name="testService")
	private TestService testService;
	
	@Resource(name="baseService")
	private BaseService<TestBean> baseService;
	
	@RequestMapping("/user/test")
	public String add(String name,Model model){

//		String name2 =request.getParameter("name");
		TestBean obj=new TestBean();
		obj.setUserName(name);
		model.addAttribute("name", name);
		baseService.create(obj);
		TestBean bean=baseService.get(1, TestBean.class);
		System.out.println(bean.getUserName());
		return "showResult";
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

	public BaseService<TestBean> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<TestBean> baseService) {
		this.baseService = baseService;
	}
}
