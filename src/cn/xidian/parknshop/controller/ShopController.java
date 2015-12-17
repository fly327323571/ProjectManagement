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
	
	@RequestMapping("/shop/index")
	public String applyOrShowShops(HttpServletRequest request){
		User user=(User) request.getSession().getAttribute("user");
		if(user==null){
			return "/user/login";
		}
		else if(user.isSeller()){
			return "../views/shopOwner/shopList";
		}
		else{
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
