package cn.xidian.parknshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.xidian.parknshop.beans.Advertisement;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.Shop;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.ShopCommodityService;
import cn.xidian.parknshop.service.ShopService;

@Controller
public class CommodityAdController {

	@Resource(name="baseService")
	private BaseService<Advertisement> adBaseService;
	
	@Resource(name="shopService")
	private ShopService shopService;
	
	@Resource(name="shopCommodityService")
	private ShopCommodityService shopCommodityService;
	
	private static Logger log= Logger.getLogger(CommodityAdController.class);
	
	@RequestMapping("advertise/{shopNo}/upload/fileUpload")
	public @ResponseBody Map<String,ResultType> advImgUpload(@PathVariable long shopNo,
															 HttpSession session,
															 MultipartFile file1,
															 MultipartFile file2,
															 MultipartFile file3){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		String Path=session.getServletContext().getRealPath("/WEB-INF/static/upload/shopAD/")+shopNo;
		String fileName;
		MultipartFile file=null;
		if(file1!=null){
			fileName=file1.getOriginalFilename();
			file=file1;
		}
		else if(file2!=null){
			fileName=file2.getOriginalFilename();
			file=file2;
		}
		else{
			fileName=file3.getOriginalFilename();
			file=file3;
		}
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
			resultType.success().setResult("static/upload/shopAD/"+shopNo+"/"+UniqueName);
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
	
	@RequestMapping("store/{shopNo}/saveAd")
	public @ResponseBody Map<String,ResultType> saveAd(@PathVariable long shopNo,String adsJson){
		Shop adOwnerShop=null;
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		try{
			adOwnerShop=shopService.findShopByShopNo(shopNo);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB BUSY");
			map.put("result", resultType);
			return map;
		}
		
		String reJson="["+adsJson+"]";
		JSONArray jsonArray=JSONArray.parseArray(reJson);
		try{
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObj=jsonArray.getJSONObject(i);
			Advertisement ad=new Advertisement();
			ad.setShop(adOwnerShop);
			ad.setAdContent(jsonObj.getString("title"));
			ad.setAdImg(jsonObj.getString("adImg"));
			ad.setCommodity(shopCommodityService.findCommodityByCommNo(jsonObj.getLongValue("productId")));
			ad.setAdUrl("product/productDetail/"+jsonObj.getLongValue("productId"));
			adBaseService.create(ad);
		}}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("DB BUSY");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("SAVE OK");
		map.put("result", resultType);
		return map;
	}
}
