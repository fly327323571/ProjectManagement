$(function(){
	
	var shopRegisterConfig = {
		URL : {//页面所有的URL配置
			STORE_NAME_CHECK : "store/*/checkStoreName.json",
			OPEN_STORE : "store/openStore.json",
			SHOP_LIST : "store/listMyStores/index.do",
			LOAD_PROVINCE : "province.json",
			LOAD_CITY : "{province}/cities.json"
		},
		validateConfig : [{
			id : 'shopName',
			name : 'shop name',
			asynValidate : function(value){
				ShopRegister.checkShopName(value);
			}
		},{
			id : 'category',
			name : 'category',
			remind : 'please select your store category!',
			validate : function(value){//此处必须返回值
				return ShopRegister.checkCategory(value);
			}
		},{
			id : 'address',
			name : 'address'
		},{
			id : "province",
			name : "province",
			remind : "please select your province"
		},{
			id : "city",
			name : "city",
			remind : "please select your city"
		}]
	};
	
	new ShopRegister(shopRegisterConfig);
});