package cn.xidian.parknshop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.UserService;
import cn.xidian.parknshop.utils.MD5Utils;
import jxl.common.Logger;

@Controller
public class UserBaseController {

	private static Logger log=Logger.getLogger(UserBaseController.class);
	
	@Resource(name="baseService")
	private BaseService<User> userBaseService;
	
	@Resource(name="userService")
	private UserService  userService;
	
	@RequestMapping("/user/login")
	public @ResponseBody  Map<String,ResultType> LogIn(String userName,String password,HttpSession session){
		String secPassWord=MD5Utils.MD5(password);
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType;
		try{
		if(userService.LogIn(userName, secPassWord)){
			resultType=new ResultType().success().setResult("Login Yes!");
			User user=userService.findUserByName(userName);
			session.setAttribute("user", user);
			map.put("result", resultType);
		}
		else{
			resultType=new ResultType().error().setResult("Account Error!");
			map.put("result", resultType);
		}}
		catch(Exception e){
			resultType=new ResultType().error().setResult("Database Busy!");
			map.put("result", resultType);
			log.error(e);
			return map;
		}
		return map;
	}
	
	
	@RequestMapping("/user/register/check")
	public @ResponseBody Map<String,ResultType> checkNameExist(String userName){
//		String userName=request.getParameter("userName");
		Map<String,ResultType> mav=new HashMap<String,ResultType>();
		ResultType resultType=null;
		try{
		if(userService.checkUserNameExist(userName)){
			resultType=new ResultType().success().setResult("UserName Can Use!");
		}
		else{
			resultType=new ResultType().error().setResult("UserName already inUse!");
		}}
		catch(Exception e){
			log.error(e);
		}
		mav.put("result", resultType);
		return mav;
	}
	
	@RequestMapping("/user/register")
	public String Register(User user){
		String secPassword=MD5Utils.MD5(user.getPassword());
		user.setPassword(secPassword);
		user.setRegisterTime(new Date());
		try{
			userBaseService.create(user);}
		catch(Exception e){
			log.error(e);
			return "error";
		}
		return "../views/user/login";
	}
	@RequestMapping("/user/register/customerReg")
	public String redirectReg(){
		return "../views/user/customerRegister";
		
	}
}
