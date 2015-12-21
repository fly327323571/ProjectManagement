package cn.xidian.parknshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import jxl.common.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.Comments;
import cn.xidian.parknshop.beans.Complaint;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.AdminService;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.ShopService;
import cn.xidian.parknshop.service.UserService;

@Controller
public class AdminController {
		
	@Resource(name="baseService")	
	private BaseService<User> userService;
	
	@Resource(name="baseService")	
	private BaseService<Shop> shopService;
	
	@Resource(name="baseService")	
	private BaseService<Complaint> complaintService;
	
	@Resource(name="userService")	
	private UserService customerService;
	
	@Resource(name="shopService")	
	private ShopService storeService;
	
	@Resource(name="adminService")	
	private AdminService  adminService;
	
	private static Logger log=Logger.getLogger(AdminController.class);
	
	@RequestMapping("/admin/complaint")
	public String ComplaintManage(Complaint complaint,Model model,HttpServletRequest request) {
		
		/*User user=userService.get(1, User.class);
		Shop shop=shopService.get(1, Shop.class);
		complaint.setComplaintUser(user);
		complaint.setComplaintedShop(shop);
		complaintService.create(complaint);
		
		Complaint complaintDel=complaintService.get(2, Complaint.class);
		complaintDel.setComplaintedShop(null);
		complaintDel.setComplaintUser(null);
		complaintService.delete(complaintDel);*/
		List<Complaint> list=adminService.findComplaintByState(0);
		model.addAttribute("ComplaintList", list);
		return "../views/admin/complaintHandle";
		}
	@RequestMapping("/admin/odium")
	public String OdiumManage(Model model,HttpServletRequest request) {
		List<Comments> list=adminService.findCommentByState(0);
		model.addAttribute("CommentsList", list);
		return "../views/admin/commentsHandle";
		}
	
	@RequestMapping("/admin/odiumDetial")
	public String OdiumDetial(Model model,int commentsId){
		Comments comment=adminService.findCommentById(commentsId);
		model.addAttribute("Comments", comment);
		return "../views/admin/commentsDetial";
	}
	
	@RequestMapping("/admin/customerSearch")
	public String UserSearch(String nickname,Model model,HttpServletRequest request) {
		List<User> customerlist=customerService.findUserByNickName(nickname);
		model.addAttribute("Customerlist", customerlist);		
		return "../views/admin/customerManage";
	}
	@RequestMapping("/admin/shopSearch")
	public String ShopSearch(String shopname,Model model,HttpServletRequest request) {
		List<Shop> shoplist=storeService.findShopByShopName(shopname);
		model.addAttribute("ShopList", shoplist);

		return "../views/admin/shopManage";
	}
	
	@RequestMapping("/admin/customerManage/list")
	public @ResponseBody Map<String,ResultType> CustomerManage(String name,HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		List<User> customerlist=null;
		try{
			customerlist=userService.getAll(User.class);}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB busy");
			map.put("result",resultType);
			return map;
		}
		resultType.success().setResult(customerlist);
		map.put("result",resultType);
		return map;
	}
	@RequestMapping("/admin/customerManage")
	public String CustomerManage(String name,Model model,HttpServletRequest request){		
		List<User> customerlist=userService.getAll(User.class);
		model.addAttribute("Customerlist", customerlist);
		return "../views/admin/customerManage";
	}
	@RequestMapping("/admin/handleCustomer")
	public String HandleCustomer(Model model,HttpServletRequest request,String userName){				
		if (userName!=null) {
			User currentuser = customerService.findUserByName(userName);
			model.addAttribute("currentuser", currentuser);
		}
		return "../views/admin/customerInfo";
	}
	
	@RequestMapping("/admin/{userName}/editCustomer")
	public String ModifyCustomer(@PathVariable String userName,User user){
		User currentuser=customerService.findUserByName(userName);
		currentuser.setAddress(user.getAddress());
		currentuser.setEmail(user.getEmail());
		currentuser.setNickName(user.getNickName());
		currentuser.setPassword(user.getPassword());
		currentuser.setState(user.getState());
		currentuser.setTel(user.getTel());
		userService.update(currentuser);
		return "redirect:/admin/customerManage";
	}
	
	@RequestMapping("/admin/shopManage")
	public String ShopManage(String name,Model model,HttpServletRequest request){		
		List<Shop> shoplist=shopService.getAll(Shop.class);
		model.addAttribute("ShopList", shoplist);
		return "../views/admin/shopManage";
	}
	@RequestMapping("/admin/modifyComplaint_Shop")
	public String ModifyComplaint_Shop(Model model,long complaintNo){
		//修改投诉处理状态		
		if(complaintNo!=0){
			Complaint complaint=adminService.findComplaintByComplaintNo(complaintNo);
		    if(complaint!=null) {
			    complaint.setComplaint_state(1);//0未处理，1已处理
			    complaint.setComplaint_view(2);//0默认值,1虚假信息，2符合事实 
			    complaint.setHandleResult(2);//0协商,1买家责任,2卖家责任
			    complaintService.update(complaint);
		    }
		   model.addAttribute("currentshop",complaint.getComplaintedShop());
		}	
		return "../views/admin/shopInfo";
	}
	@RequestMapping("/admin/handleShop")
	public String HandleShop(Model model,long shopNo){
		Shop currentshop=storeService.findShopByShopNo(shopNo);
		model.addAttribute("currentshop",currentshop);
		return "../views/admin/shopInfo";
	}
	@RequestMapping("/admin/{shopNo}/editShop")
	public String ModifyShop(@PathVariable long shopNo,Shop shop){
		Shop currentshop=storeService.findShopByShopNo(shopNo);
		currentshop.setOwnerTel(shop.getOwnerTel());
		currentshop.setRemarks(shop.getRemarks());
		currentshop.setShopAddr(shop.getShopAddr());
		currentshop.setShopCategories(shop.getShopCategories());
		currentshop.setShopDesc(shop.getShopDesc());
		currentshop.setShopRank(shop.getShopRank());
		currentshop.setShopSourse(shop.getShopSourse());
		currentshop.setStatus(shop.getStatus());
		shopService.update(currentshop);
		return "redirect:/admin/shopManage";
	}
}





