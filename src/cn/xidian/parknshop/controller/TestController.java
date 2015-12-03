package cn.xidian.parknshop.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xidian.parknshop.beans.TestBean;
import cn.xidian.parknshop.service.TestService;

@Controller
public class TestController {
	@Resource(name="testService")
	private TestService testService;
	
	@RequestMapping("/user/test")
	public String add(String name,HttpServletRequest request,Model model){
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String name2 =request.getParameter("name");
		TestBean obj=new TestBean();
		obj.setUserName(name);
		model.addAttribute("name", name);
		testService.addTestBean(obj);
		return "showResult";
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}
