package cn.xidian.parknshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Odium;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.DashBoardService;

@Controller
public class DashBoardCommentsController {

	@Resource(name="dashBoardService")
	private DashBoardService dashBoardService;
	
	@Resource(name="baseService")
	private BaseService<Comments> baseCommentService;
	
	private static Logger log=Logger.getLogger(DashBoardCommentsController.class);
	
	@RequestMapping("store/{shopNo}/dashboard/comment/query")
	public @ResponseBody Map<String,ResultType> queryNewComments(@PathVariable long shopNo,HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Map<String,String> QueryParamMap=new HashMap<String,String>();
		String pageIndex=request.getParameter("page[pageIndex]");
		String pageSize=request.getParameter("page[pageSize]");
		QueryParamMap.put("pageIndex", pageIndex);
		QueryParamMap.put("pageSize", pageSize);
		List<Odium> comments=null;
		try{
			comments=dashBoardService.findNewCommentsByShopNoAndFilters(QueryParamMap, shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy!");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(comments);
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("business/comment/{commentId}/complain")
	public @ResponseBody Map<String,ResultType> complainOdium(@PathVariable long commentId,
																			String reason){
		Comments comment=null;
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		try{
			comment=baseCommentService.get(commentId, Comments.class);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		Odium odium=new Odium();
		odium.setCommentsTime(comment.getCommentsTime());
		odium.setUser(comment.getUser());
		odium.setNumber(System.nanoTime());
		odium.setReason(reason);
		odium.setComments(comment.getComments());
		odium.setCommodity(comment.getCommodity());
		odium.setShop(comment.getShop());
		odium.setRank(comment.getCommodity().getAvgRank());
		try{
			dashBoardService.createComplain(comment, odium);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("Operation Success!");
		map.put("result", resultType);
		return map;
	}
}
