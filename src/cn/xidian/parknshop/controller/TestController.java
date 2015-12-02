package cn.xidian.parknshop.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xidian.parknshop.beans.TestBean;
import cn.xidian.parknshop.service.TestService;

@Controller
@RequestMapping("/user")
public class TestController {
	@Resource
	private TestService testService;
	
	@RequestMapping("/test")
	public String add(String name,HttpServletRequest request){
//		String name2 =request.getParameter("name");
		TestBean obj=new TestBean();
		obj.setUserName(name);
		testService.test(obj);
		return "";
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}
