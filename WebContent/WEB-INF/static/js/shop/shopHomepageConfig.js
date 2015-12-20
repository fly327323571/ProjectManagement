$(function(){
	var config={
		URL:{
			ADVERTISE_IMG:'business/market/{shopNo}/showAds.json'.replace("{shopNo}", storeId),
			PRODUCT_LIST : "business/market/{shopNo}/showProducts".replace("{shopNo}", storeId),
			PRODUCT_SHOW : "product/productDetail/{productId}.do",
			SHOP_LINKS : "shop/{shopNo}/shopLinks".replace("{shopNo}", storeId),
			SHOP_HOMEPAGE : "business/market/{shopNo}/shopHomePage.do",
		}
	};
	new ShopHomepage(config);
});