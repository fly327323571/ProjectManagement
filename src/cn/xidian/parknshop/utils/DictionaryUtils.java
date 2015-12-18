package cn.xidian.parknshop.utils;

import java.util.*;

public class DictionaryUtils {

	
	public static final String FILE_PATH="../Upload/Shop/";
	
	public static enum ShopSourceType{
		SelfSale,OfflineSale,Distribution,Undefined;
		private static HashMap<String,ShopSourceType> fromString=new HashMap<String,ShopSourceType>();
		static{	
			for(ShopSourceType c:ShopSourceType.values()){
				fromString.put(c.toString(), c);
			}
		} 
		public static ShopSourceType fromString(String typeName){
			return fromString.get(typeName);
		}
		
	};
	
	public static enum ShopCategory{
		Food,Clothes;
		private static HashMap<String,ShopCategory> fromString=new HashMap<String,ShopCategory>();
		static{	
			for(ShopCategory c:ShopCategory.values()){
				fromString.put(c.toString(), c);
			}
		} 
		public static ShopCategory fromString(String typeName){
			return fromString.get(typeName);
		}
		
	};
	
	public static enum UserAccountState{
		Normal,Warning,Suspend
	};
	
	
	public static enum CommodityCategory{
		Food,Clothes;
		private static HashMap<String,CommodityCategory> fromString=new HashMap<String,CommodityCategory>();
		static{	
			for(CommodityCategory c:CommodityCategory.values()){
				fromString.put(c.toString(), c);
			}
		} 
		public static CommodityCategory fromString(String typeName){
			return fromString.get(typeName);
		}
	}
}
