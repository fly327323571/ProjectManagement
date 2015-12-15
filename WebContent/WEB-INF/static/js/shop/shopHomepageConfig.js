$(function(){
	var config={
		URL:{
			ADVERTISE_IMG:'business/market/{storeId}/showAds.json'.replace("{storeId}", storeId),
			PRODUCT_LIST : "business/market/{storeId}/showProducts.json".replace("{storeId}", storeId),
			PRODUCT_SHOW : "product/productDetail/{productId}.do",
			SHOP_LINKS : "store/{storeId}/shopLinks.json".replace("{storeId}", storeId),
			SHOP_HOMEPAGE : "business/market/{storeId}/shopHomePage.do",
		}
	};
	new ShopHomepage(config);
});