$(function(){
	var config={
			URL:{
				SEE_MORE:'',
				FETCH_SHOP_ADS : "admin/homepage/adver/shop",
				FETCH_PRODUCT_ADS : "admin/homepage/adver/product",
				PRODUCT_DETAIL : "product/productDetail/{productId}",
			}
	};
	new Homepage(config);
});