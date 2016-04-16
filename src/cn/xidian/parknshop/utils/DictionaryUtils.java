package cn.xidian.parknshop.utils;

import java.util.*;

public class DictionaryUtils {

	
	
	public static enum ShopSourceType{
		SelfSale(0),OfflineSale(1),Distribution(2),Undefined(3);
		private final int numberOfShopSourceType;
		
		
		ShopSourceType(int size){
			this.numberOfShopSourceType=size;
		}
		
		public int numberOfShopSourceType(){return numberOfShopSourceType;}
		
		private static HashMap<String,ShopSourceType> fromString=new HashMap<String,ShopSourceType>();
		
		private static HashMap<Integer,ShopSourceType> fromInteger=new HashMap<Integer,ShopSourceType>();
		static{	
			for(ShopSourceType c:ShopSourceType.values()){
				fromString.put(c.toString(), c);
			}
			for(ShopSourceType c:ShopSourceType.values()){
				fromInteger.put(c.numberOfShopSourceType, c);
			}
		} 
		public static ShopSourceType fromString(String typeName){
			return fromString.get(typeName);
		}
		
		public static ShopSourceType fromInteger(int i){
			return fromInteger(i);
		}
		
	};
	
	public static enum ShopCategory{
		Food(0),Clothes(1);
		
		private final int numberOfShopCategory;
		
		ShopCategory(int size) {
			// TODO Auto-generated constructor stub
			this.numberOfShopCategory=size;
		}
		
		private static HashMap<String,ShopCategory> fromString=new HashMap<String,ShopCategory>();
		static{	
			for(ShopCategory c:ShopCategory.values()){
				fromString.put(c.toString(), c);
			}
		} 
		public static ShopCategory fromString(String typeName){
			return fromString.get(typeName);
		}
		
		public static ShopCategory fromInteger(int i){
			for(ShopCategory c:ShopCategory.values()){
				if(c.numberOfShopCategory()==i){
					return c;
				}
			}
			return null;
		}
		
		public int numberOfShopCategory(){return numberOfShopCategory;}
		
	};
	
	public static enum UserAccountState{
		Normal,Warning,Suspend
	};
	
	public static enum CommentsIsRead{
		Read(1),NoRead(0);
		private final int numberOfCommentsIsRead;
		CommentsIsRead(int size){
			this.numberOfCommentsIsRead=size;
		}
		public int numberOfCommentsIsRead(){
			return numberOfCommentsIsRead;
		}
	}
	
	
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
	
	public static enum OrderStatus{
		Unpaid(1),unshipped(2),refunding(3),refundRejected(4),refunded(5),
		shipped(6),received(7),commented(8),done(9);
		private final int size;
		OrderStatus(int size){
			this.size=size;
		}
		public int getOrderStatusIntValue(){
			return size;}
	}
}
