package cn.xidian.parknshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xidian.parknshop.beans.Odium;
import cn.xidian.parknshop.beans.Complaint;
import cn.xidian.parknshop.beans.HomePageCommodityAds;
import cn.xidian.parknshop.beans.HomePageShopAds;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.AdminService;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.ShopCommodityService;
import cn.xidian.parknshop.service.ShopService;
import cn.xidian.parknshop.service.UserService;

@Controller
public class AdminController {
		
	@Resource(name="baseService")	
	private BaseService<User> userService;
	
	@Resource(name="baseService")	
	private BaseService<Shop> shopService;
	
	@Resource(name="shopService")
	private ShopService shopService2;
	
	@Resource(name="shopCommodityService")
	private ShopCommodityService shopCommodityService;
	
	@Resource(name="baseService")	
	private BaseService<Complaint> complaintService;
	
	@Resource(name="baseService")	
	private BaseService<Odium> odiumService;
	
	@Resource(name="userService")	
	private UserService customerService;
	
	@Resource(name="shopService")	
	private ShopService storeService;
	
	@Resource(name="adminService")	
	private AdminService  adminService;
	
	private static Logger log=Logger.getLogger(AdminController.class);
	
	@RequestMapping("/admin/complaint")
	public String ComplaintManage(Complaint complaint,Model model,HttpServletRequest request) {
		
		List<Complaint> list=adminService.findComplaintByState(0);
		model.addAttribute("ComplaintList", list);
		return "../views/admin/complaintHandle";
		}
	
	@RequestMapping("/admin/complaintDetial")
	public String ComplaintDetial(Model model,long complaintNo){
		
		Complaint complaint=adminService.findComplaintByComplaintNo(complaintNo);	    
		model.addAttribute("ComplaintDetial",complaint);	
		return "../views/admin/complaintDetial";
	}
	@RequestMapping("/admin/{complaintNo}/handleComplaint")
	public String HandleComplaint(@PathVariable long complaintNo,int userstate,int shopstate ,
		int handleResult, String rank,int complaint_view){
		Complaint curcomplaint=adminService.findComplaintByComplaintNo(complaintNo);
	
		User user=curcomplaint.getComplaintUser();
		if(userstate==0||userstate==1||userstate==2){
		     user.setState(userstate);
		     userService.update(user);
		}
		Shop shop=curcomplaint.getComplaintedShop();
		if(shopstate==1||shopstate==4||shopstate==3){
		     shop.setStatus(shopstate);
		     shopService.update(shop);
		}		
		if(rank.equals("1")) {shop.setShopRank(1.0);  shopService.update(shop);}
		if(rank.equals("2")) {shop.setShopRank(2.0);  shopService.update(shop);}
		if(rank.equals("3")) {shop.setShopRank(3.0);  shopService.update(shop);}
		if(rank.equals("4")) {shop.setShopRank(4.0);  shopService.update(shop);}
		if(rank.equals("5")) {shop.setShopRank(5.0);  shopService.update(shop);}				
		curcomplaint.setComplaintUser(user);
		curcomplaint.setComplaintedShop(shop);
		curcomplaint.setHandleResult(handleResult);
		curcomplaint.setComplaint_view(complaint_view);
		curcomplaint.setComplaint_state(1);
		complaintService.update(curcomplaint);
		System.out.println(userstate);
		System.out.println(shopstate);
		System.out.println(rank);
		return "redirect:/admin/complaint";
	}
	
	@RequestMapping("/admin/odium")
	public String OdiumManage(Model model,HttpServletRequest request) {
		List<Odium> list=adminService.findOdiumByState(0);
		model.addAttribute("OdiumList", list);
		return "../views/admin/odiumHandle";
		}
	
	@RequestMapping("/admin/odiumDetial")
	public String OdiumDetial(Model model,long number){

		Odium odium = null;
		try {
			odium = adminService.findOdiumByNo(number);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("OdiumDetial", odium);
		return "../views/admin/odiumDetial";
	}
	@RequestMapping("/admin/{number}/handleOdium")
	public String HandleOdium(@PathVariable long number,int userstate,int shopstate ,String rank){

		Odium curodium=adminService.findOdiumByNo(number);
		User user=curodium.getUser();
		if(userstate==0||userstate==1||userstate==2){
		     user.setState(userstate);
		     userService.update(user);
		}
		Shop shop=curodium.getShop();
		if(shopstate==1||shopstate==4||shopstate==3){
		     shop.setStatus(shopstate);
		     shopService.update(shop);
		}		
		if(rank.equals("1")) {shop.setShopRank(1.0);  shopService.update(shop);}
		if(rank.equals("2")) {shop.setShopRank(2.0);  shopService.update(shop);}
		if(rank.equals("3")) {shop.setShopRank(3.0);  shopService.update(shop);}
		if(rank.equals("4")) {shop.setShopRank(4.0);  shopService.update(shop);}
		if(rank.equals("5")) {shop.setShopRank(5.0);  shopService.update(shop);}				
		curodium.setUser(user);
		curodium.setShop(shop);
		curodium.setState(1);
		odiumService.update(curodium);
		return "redirect:/admin/odium";
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
		ArrayList<String> categories=new ArrayList<String>();
		categories.add("Food");categories.add("Clothes");categories.add("Tool");
		System.out.print(categories);
		model.addAttribute("Categories",categories);
		return "../views/admin/shopManage";
	}

	@RequestMapping("/admin/handleShop")
	public String HandleShop(Model model,long shopNo){
		Shop currentshop=storeService.findShopByShopNo(shopNo);
		model.addAttribute("currentshop",currentshop);
		ArrayList<String> categories=new ArrayList<String>();
		categories.add("Food");categories.add("Clothes");categories.add("Tool");
		System.out.print(categories);
		model.addAttribute("Categories",categories);
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
	
	@RequestMapping("admin/homepage/adver/shop")
	public @ResponseBody Map<String,ResultType> adverShop(){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		List<HomePageShopAds> shopAds=null;
		try{
			shopAds=shopService2.findHomePageShopAds();
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(shopAds);
		map.put("result", resultType);
		return map;
		
	}
	
	@RequestMapping("admin/homepage/adver/product")
	public @ResponseBody Map<String,ResultType> adverProduct(){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		List<HomePageCommodityAds> commodityAds=null;
		try{
			commodityAds=shopCommodityService.findHomePageCommodityAds();
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(commodityAds);
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("admin/adminHomepage.do")
	public String adminHomepage(){
		return "../views/admin/adminHomepage";
	}
}





