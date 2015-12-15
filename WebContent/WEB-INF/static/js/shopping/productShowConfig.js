$(function(){
	var config={
			URL:{
				BUY_NOW : 'business/buyNow.json',
				ADD_TO_CART:'cart/add.json',
				CONFIRM:'business/orderConfirm/index.do',
				ADD_FAVORITE_PRODUCT:'user/addToFavorite/product.json',
				ADD_FAVORITE_STORE : 'user/addToFavorite/store.json',
				LOAD_COMMENTS:'business/loadComments',
				MY_CART : 'cart/myCart.do',
				ADD_SUCCESS : 'cart/addSuccess.do',
				LOAD_PROVINCE : "province.json",
				LOAD_CITY : "{province}/cities.json",
				POSTAGE_CALC : "store/{storeId}/calculatePostage.json".replace("{storeId}",_storeId),
				ENTER_SHOP : "business/market/{storeId}/shopHomePage.do".replace("{storeId}",_storeId),
			}
	};
	new ProductShow(config);
});