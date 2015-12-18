package cn.xidian.parknshop.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.xidian.parknshop.beans.ResultType;
import jxl.common.Logger;

@Controller
public class CommodityAdController {

	private static Logger log= Logger.getLogger(CommodityAdController.class);
	
	private String logoUrl;
	
	@RequestMapping("advertise/{shopNo}/upload/fileUpload")
	public @ResponseBody Map<String,ResultType> uploadAdImg(@RequestParam("file") MultipartFile file,@PathVariable long shopNo,HttpSession session){
		Map<String,ResultType> map=new HashMap<String,ResultType>();
		ResultType resultType=new ResultType();
		String Path=session.getServletContext().getRealPath("/WEB-INF/static/upload/shopAD/")+shopNo;
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
	
}
