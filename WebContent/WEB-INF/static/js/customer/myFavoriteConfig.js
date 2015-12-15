$(function(){
	var config = {
		URL : {
			LOAD_FAVORITE_PRODUCT : "user/myfavorites/product.json",
			LOAD_FAVORITE_STORE : "user/myfavorites/store.json",
			PRODUCT_DETAIL : "product/productDetail/{productId}.do",
			STORE_HOMEPAGE : "business/market/{storeId}/shopHomePage.do"
		}
	};
	new MyFavorite(config);
});