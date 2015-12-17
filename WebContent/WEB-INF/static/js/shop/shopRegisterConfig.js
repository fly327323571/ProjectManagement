$(function(){
	
	var shopRegisterConfig = {
		URL : {//页面所有的URL配置
			STORE_NAME_CHECK : "shop/*/checkShopName",
			OPEN_STORE : "shop/apply",
			SHOP_LIST : "shop/showList"
//			LOAD_PROVINCE : "province.json",
//			LOAD_CITY : "{province}/cities.json"
		},
		validateConfig : [{
			id : 'shopName',
			name : 'shopName',
			asynValidate : function(value){
				ShopRegister.checkShopName(value);
			}
		},{
			id : 'shopCategories',
			name : 'shopCategories',
			remind : 'please select your store category!',
			validate : function(value){//此处必须返回值
				return ShopRegister.checkCategory(value);
			}
		},{
			id : 'shopAddr',
			name : 'shopAddr'
		},{
			id : "shopDesc",
			name : "shopDesc",
		},{
			id : 'shopSourse',
			name : 'shopSourse',
			remind : 'please select your store sourceType!',
			validate : function(value){//此处必须返回值
				return ShopRegister.checkCategory(value);
			}
		}]
	};
	
	new ShopRegister(shopRegisterConfig);
});