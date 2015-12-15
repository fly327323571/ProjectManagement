$(function(){
	var config={
			URL:{
				SEE_MORE:'',
				FETCH_SHOP_ADS : "admin/homepage/adver/shop.json",
				FETCH_PRODUCT_ADS : "admin/homepage/adver/product.json",
				PRODUCT_DETAIL : "product/productDetail/{productId}",
			}
	};
	new Homepage(config);
});