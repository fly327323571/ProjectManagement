package cn.xidian.parknshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.ShopService;
import cn.xidian.parknshop.utils.DictionaryUtils;
import jxl.common.Logger;

@Controller
public class ShopController {

	@Resource(name="baseService")
	private BaseService<Shop> shopBaseService;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	private String logoUrl="";
	
	private static Logger log=Logger.getLogger(ShopController.class);
	
	@RequestMapping("/shop/showList")
	public @ResponseBody Map<String,ResultType> showShopList(HttpSession session){
		User shopOwner=(User)session.getAttribute("user");
		ResultType resultType=new ResultType();
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		try{
		List<Shop> shopList=shopService.findShopByUserName(shopOwner.getUserName());

		resultType.success().setResult(shopList);
		map.put("result", resultType);}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB busy");
			map.put("result", resultType);
		}
		return map;
	}
	@RequestMapping("shop/*/shopHomePage.do")
	public ModelAndView ShopManage(Model model,HttpServletRequest request){
		String reUri=request.getRequestURI();
		String shopNoStr=reUri.substring(reUri.indexOf('/', 13)+1, reUri.lastIndexOf('/'));
		long shopNo=Long.valueOf(shopNoStr);
		Shop shop=shopService.findShopByShopNo(shopNo);
		model.addAttribute("shop",shop );
		return new ModelAndView("../views/shop/shopManageHomepage");
	}
	
	@RequestMapping("/shop/*/modifyShopProfile/index.do")
	public String ModifyShopIndex(Model model,HttpServletRequest request){
		String reUri=request.getRequestURI();
		String shopNoStr=reUri.substring(reUri.indexOf('/', 13)+1, reUri.indexOf('/',reUri.indexOf('/', 13)+1));
		long shopNo=Long.valueOf(shopNoStr);
		Shop shop=null;
		try{
			shop=shopService.findShopByShopNo(shopNo);}
		catch(Exception e){
			log.error(e);
			return "../views/error";
		}
		model.addAttribute("store", shop);
		List<String> categories=new ArrayList<String>();
		for(DictionaryUtils.ShopCategory i:DictionaryUtils.ShopCategory.values()){
			categories.add(i.toString());
		}
		model.addAttribute("categories", categories);
		return "../views/shop/modifyShopProfile";
	}
	
	@RequestMapping("/shop/index")
	public String applyOrShowShops(HttpServletRequest request,Model model){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "/user/login";
		}
		else if(user.isSeller()){
			return "../views/shopOwner/shopList";
		}
		else{
			List<String> categories=new ArrayList<String>(); 
			List<String> sourceTypes=new ArrayList<String>();
			for(DictionaryUtils.ShopCategory i:DictionaryUtils.ShopCategory.values()){
				categories.add(i.toString());
			}
			for(DictionaryUtils.ShopSourceType i:DictionaryUtils.ShopSourceType.values()){
				sourceTypes.add(i.toString());
			}
			model.addAttribute("categories",categories);
			model.addAttribute("sourceTypes",sourceTypes);
			return "../views/shopOwner/shopRegister";
		}
	}
	
	@RequestMapping("/shop/*/checkShopName")
	public @ResponseBody Map<String,ResultType> checkShopName(String shopName){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		try{
			if(shopService.checkShopName(shopName)){
				resultType.success().setResult("can Use");
				map.put("result", resultType);
			}
			else{
				resultType.error().setResult("Illeagal");
				map.put("result", resultType);
			}
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB busy");
			map.put("result", resultType);
			return map;
		}
		return map; 
	}
	
	@RequestMapping("/shop/register")
	public String readyToRegShop(Model model){
		List<String> categories=new ArrayList<String>(); 
		List<String> sourceTypes=new ArrayList<String>();
		for(DictionaryUtils.ShopCategory i:DictionaryUtils.ShopCategory.values()){
			categories.add(i.toString());
		}
		for(DictionaryUtils.ShopSourceType i:DictionaryUtils.ShopSourceType.values()){
			sourceTypes.add(i.toString());
		}
		model.addAttribute("categories",categories);
		model.addAttribute("sourceTypes",sourceTypes);
		return "../views/shopOwner/shopRegister";
	}
	
	@RequestMapping("/shop/apply")
	public String applyShop(Shop shop,HttpSession session){
		User shopOwner =(User)session.getAttribute("user");
		shop.setShopOwner(shopOwner);
		shop.setOwnerTel(shopOwner.getTel());
		shop.setShopNo(System.nanoTime());
		shop.setRegTime(new Date());
		shop.setShopIcon(logoUrl);
//		shop.setShopCategories(DictionaryUtils.ShopCategory.fromString(shop.getShopCategories()));
		shopBaseService.create(shop);
		return "../views/shopOwner/shopList";
	}
	
	@RequestMapping("/upload/fileUpload")
	public @ResponseBody Map<String,ResultType>  uploadLogo(@RequestParam("myfiles") MultipartFile file,
			 							HttpSession session){
		User shopOwner=(User)session.getAttribute("user");
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		String Path=session.getServletContext().getRealPath("/WEB-INF/static/upload/shopLogo/")+shopOwner.getNickName();
		ResultType resultType=new ResultType();
        String fileName = file.getOriginalFilename();
        String UniqueName=UUID.randomUUID().toString() + fileName;
        File tempFile = new File(Path,UniqueName);  
        if (!tempFile.getParentFile().exists()) {  
            tempFile.getParentFile().mkdir();  
        } 
        if(fileName.substring(fileName.indexOf('.')+1,fileName.length()).equals("jpg")
        	||fileName.substring(fileName.indexOf('.')+1,fileName.length()).equals("png")
        	||fileName.substring(fileName.indexOf('.')+1,fileName.length()).equals("gif")
        	||fileName.substring(fileName.indexOf('.')+1,fileName.length()).equals("jpeg")){
        if (!tempFile.exists()) {  
            try {
				tempFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error(e);
				resultType.error().setResult("File Upload Error,Please retry!");
				map.put("result", resultType);
				e.printStackTrace();
				return map;
			}  
        }  
        try {
			file.transferTo(tempFile);
			logoUrl="static/upload/shopLogo/"+shopOwner.getNickName()+"/"+UniqueName+"";
			resultType.success().setResult("static/upload/shopLogo/"+shopOwner.getNickName()+"/"+UniqueName);
			map.put("result", resultType);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			log.error(e);
			resultType.error().setResult("File Upload Error,Please retry!");
			map.put("result", resultType);
			e.printStackTrace();
			return map;
		}  }
        else{
        	resultType.error().setResult("File Type Error!");
        	map.put("result", resultType);
        }
		return map;
	}
	

	public BaseService<Shop> getShopService() {
		return shopBaseService;
	}

	public void setShopService(BaseService<Shop> shopBaseService) {
		this.shopBaseService = shopBaseService;
	}


}
