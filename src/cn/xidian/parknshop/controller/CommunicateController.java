package cn.xidian.parknshop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.Message;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.CommunicateService;

@Controller
public class CommunicateController {

	private static Logger log = Logger.getLogger(CommunicateController.class);
	
	@Resource(name="baseService")
	private BaseService<Message> baseMessageService;
	
	@Resource(name="communicateService")
	private CommunicateService communicateService;
	
	@RequestMapping("/communication/sendMessage")
	public @ResponseBody Map<String,ResultType> sendMessage(HttpServletRequest request,String content,String toUserId){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		User user=(User) request.getSession().getAttribute("user");
		Message message=new Message();
		message.setDate(new Date(System.currentTimeMillis()));
		message.setSenderName(user.getUserName());
		message.setReceiverName(toUserId);
		message.setMessage(content);
		try{
			baseMessageService.create(message);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("error");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(message);
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("/communication/getMessage")
	public @ResponseBody Map<String,ResultType> getMessage(HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		User user=(User) request.getSession().getAttribute("user");
		List<Message> messageList=null;
		try{
			messageList=communicateService.findMessageList(user.getUserName());}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(messageList);
		map.put("result", resultType);
		return map;
	}
	
}
