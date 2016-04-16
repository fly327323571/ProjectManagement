$(function(){
	var config={
		URL:{
			ADVERTISE_IMG:'business/market/{shopNo}/showAds'.replace("{shopNo}", storeId),
			PRODUCT_LIST : "business/market/{shopNo}/showProducts".replace("{shopNo}", storeId),
			PRODUCT_SHOW : "product/{shopNo}/productDetail/{productId}.do".replace("{shopNo}", storeId),
			SHOP_LINKS : "shop/{shopNo}/shopLinks".replace("{shopNo}", storeId),
			SHOP_HOMEPAGE : "business/market/{shopNo}/shopHomePage.do",
		}
	};
	new ShopHomepage(config);
});