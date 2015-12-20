package cn.xidian.parknshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.xidian.parknshop.beans.Commodity;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.ShopCommodityService;
import cn.xidian.parknshop.service.ShopService;
import cn.xidian.parknshop.utils.DictionaryUtils;
import cn.xidian.parknshop.utils.DictionaryUtils.CommodityCategory;


@Controller
public class ShopCommodityController {

	@Resource(name="baseService")
	private BaseService<Commodity> commodityBaseService;
	
	@Resource(name="shopCommodityService")
	private ShopCommodityService shopCommodityService;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	private String logoUrl;
	
	private static Logger log = Logger.getLogger(ShopCommodityController.class);
	
	@RequestMapping("shop/{shopNo}/dashboard/index.do")
	public ModelAndView dashboardIndex(@PathVariable long shopNo,Model model){
		model.addAttribute("storeId",shopNo);
		return new ModelAndView("../views/shop/dashboard");
	}
	
	@RequestMapping("shop/{shopNo}/manageAd/index.do")
	public ModelAndView manageAdIndex(@PathVariable long shopNo,Model model){
		model.addAttribute("storeId",shopNo);
		List<Commodity> commodities=null;
		try{
			commodities=shopCommodityService.findCommByShopNo(shopNo);
		}
		catch(Exception e){
			log.error(e);
		}
		model.addAttribute("products", commodities);
		return new ModelAndView("../views/shop/advertiseManage");
	}
	
	@RequestMapping("product/{shopNo}/list/index.do")
	public ModelAndView commodityListIndex(@PathVariable long shopNo,Model model){
		model.addAttribute("storeId",shopNo);
		return new ModelAndView("../views/shop/productList");
	}
	
	@RequestMapping("product/{shopNo}/modify/{productNo}/index.do")
	public ModelAndView redirectModifyCommInfo(@PathVariable long shopNo,@PathVariable long productNo,Model model){
		model.addAttribute("storeId",shopNo);
		Commodity commodity=null;
		List<String> categories=new ArrayList<String>();
		for(CommodityCategory c:DictionaryUtils.CommodityCategory.values()){
			categories.add(c.toString());
		}
		model.addAttribute("categories",categories);
		try{
			commodity=shopCommodityService.findCommodityByCommNo(productNo);
		}
		catch(Exception e){
			log.error(e);
		}
		model.addAttribute("product", commodity);
		return new ModelAndView("../views/shop/modifyProductInfo");
		
	}
	
	@RequestMapping("product/{shopNo}/select")
	public @ResponseBody Map<String,ResultType> showCommList(@PathVariable long shopNo,HttpServletRequest request){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Map<String,String> QueryParamMap=new HashMap<String,String>();
		String pageIndex=request.getParameter("page[pageIndex]");
		String pageSize=request.getParameter("page[pageSize]");
		String columnFilterName_0=request.getParameter("columnFilters[0][name]");
		String columnFilterValue_0=request.getParameter("columnFilters[0][value][]");
		String orderFilters=request.getParameter("orderFilters[0][name]");
		String isAsc=request.getParameter("orderFilters[0][isAscending]");
		QueryParamMap.put("pageIndex", pageIndex);
		QueryParamMap.put("pageSize", pageSize);
		if(columnFilterName_0!=null){
			QueryParamMap.put(columnFilterName_0, columnFilterValue_0);
		}
		QueryParamMap.put("orderFilters", orderFilters);
		QueryParamMap.put("isAsc", isAsc);
		List<Commodity> list=null;
		try{
			list=shopCommodityService.findCommodityByFilters(QueryParamMap, shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult(list);
		map.put("result", resultType);
		return map;
	}
	
	
	@RequestMapping("product/{shopNo}/add/index.do")
	public ModelAndView redirectAddCommIndex(@PathVariable long shopNo,Model model){
		model.addAttribute("storeId",shopNo);
		List<String> categories=new ArrayList<String>();
		for(CommodityCategory c:DictionaryUtils.CommodityCategory.values()){
			categories.add(c.toString());
		}
		model.addAttribute("categories",categories);
		return new ModelAndView("../views/shop/addProduct");
		
	}
	
	@RequestMapping("product/{shopNo}/add")
	public @ResponseBody Map<String,ResultType> addCommodity(@PathVariable long shopNo,Commodity commodity){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Shop shop=null;
		try{
			shop=shopService.findShopByShopNo(shopNo);}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB busy.");
			map.put("result", resultType);
			return map;
		}
		if(shop==null){
			resultType.error().setResult("you must select a Shop.");
			map.put("result", resultType);
			return map;
		}
		commodity.setCommodityNo(System.nanoTime());
		commodity.setShop(shop);
		commodity.setCommodityImg(logoUrl);
		commodity.setAddTime(new Date());
		try{
			commodityBaseService.create(commodity);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB busy.");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("Add Success!");
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("product/{shopNo}/*/checkProductName")
	public @ResponseBody Map<String,ResultType> checkCommName(@PathVariable long shopNo,String commodityName){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		try{
			if(shopCommodityService.checkCommName(commodityName, shopNo)){
				resultType.success().setResult("Can Use");
				map.put("result", resultType);
			}
			else{
				resultType.error().setResult("This commodity name in use.");
				map.put("result", resultType);
			}
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		return map;
	}
	
	
	@RequestMapping("product/{shopNo}/upload/CommodityImg.do")
	public @ResponseBody Map<String,ResultType>  uploadLogo(@PathVariable long shopNo,@RequestParam("file") MultipartFile file,
			 							HttpSession session){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		String Path=session.getServletContext().getRealPath("/WEB-INF/static/upload/shop/")+shopNo;
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
			logoUrl="static/upload/shop/"+shopNo+"/"+UniqueName+"";
			resultType.success().setResult(logoUrl);
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
	
	@RequestMapping("product/{productId}/modify")
	public @ResponseBody Map<String,ResultType> modifyComm(@PathVariable long productId,Commodity commodity){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Commodity preCommodity=null;
		try{
			preCommodity=shopCommodityService.findCommodityByCommNo(productId);}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy!");
			map.put("result", resultType);
			return map;
		}
		if(preCommodity==null){
			resultType.error().setResult("error.please retry");
			map.put("result", resultType);
			return map;
		}
		preCommodity.setCategory(commodity.getCategory());
		preCommodity.setCommodityDetail(commodity.getCommodityDetail());
		preCommodity.setCommodityName(commodity.getCommodityName());
		preCommodity.setCommodityPrice(commodity.getCommodityPrice());
		preCommodity.setCommodityCount(commodity.getCommodityCount());
		try{
			commodityBaseService.update(preCommodity);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy!");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("Update Ok~");
		map.put("result", resultType);
		return map;
	}
}


