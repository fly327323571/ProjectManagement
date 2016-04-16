package cn.xidian.parknshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.xidian.parknshop.beans.Express;
import cn.xidian.parknshop.beans.Order;
import cn.xidian.parknshop.beans.ResultType;
import cn.xidian.parknshop.beans.ShopOwner;
import cn.xidian.parknshop.beans.User;
import cn.xidian.parknshop.service.BaseService;
import cn.xidian.parknshop.service.OrderService;
import cn.xidian.parknshop.service.ShopOwnerService;
import cn.xidian.parknshop.utils.DictionaryUtils;

@Controller
public class ShopOwnerController {

	@Resource(name="shopOwnerService")
	private ShopOwnerService shopOwnerService;
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	@Resource(name="baseService")
	private BaseService<Express> baseExpressService;
	
	private static Logger log=Logger.getLogger(ShopOwnerController.class);
	
	private String logoUrl="";
	
	@RequestMapping("user/register/saveShopOwner")
	public @ResponseBody Map<String,ResultType> regShopOwner(ShopOwner shopOwner,HttpSession session){
		User customer=(User)session.getAttribute("user");
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		if(customer==null){
			resultType.error().setResult("no Login!");
			map.put("result", resultType);
			return map;
			}
		
		try{
			shopOwnerService.createShopOwner(customer, shopOwner);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("db busy");
			return map;
		}
		
		resultType.success().setResult("Register ShopOwner Success!");
		map.put("result", resultType);
		return map;
	}
	
	@RequestMapping("user/upload/portraitUpload")
	public @ResponseBody Map<String,ResultType>  uploadLogo(@RequestParam("file") MultipartFile file,
			 							HttpSession session){
		User shopOwner=(User)session.getAttribute("user");
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		String Path=session.getServletContext().getRealPath("/WEB-INF/static/upload/shopOwnerAvatar/")+shopOwner.getNickName();
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
			logoUrl="static/upload/shopOwnerAvatar/"+shopOwner.getNickName()+"/"+UniqueName+"";
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
	
	@RequestMapping("business/ship/{orderId}")
	public @ResponseBody Map<String,ResultType> shipProduct(@PathVariable long orderId,long trackNumber,String expressType){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		Order order=null;
		try{
			order=orderService.findOrderByOrderNo(orderId);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		Express express=new Express();
		express.setExpressCompanyName(expressType);
		express.setExpressNo(trackNumber);
		express.setOrder(order);
		express.setFromAddr(order.getSeller().getAddress());
		express.setExpress_type(expressType);
		express.setToAddr(order.getToAddr());
		express.setPrice(5.5D);
		try{
			baseExpressService.create(express);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		order.setDeliveryStatus(1);
		try{
			orderService.updateOrder(order);
			orderService.updateOrderStatus(DictionaryUtils.OrderStatus.shipped, orderId);
		}
		catch(Exception e){
			log.error(e);
			resultType.error().setResult("Db busy");
			map.put("result", resultType);
			return map;
		}
		resultType.success().setResult("Success");
		map.put("result", resultType);
		return map;
		
	}
}
