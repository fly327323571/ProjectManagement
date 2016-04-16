$(function(){
	var config = {
		URL : {
			SHOP_OWNER_HOMEPAGE : 'shop/index.do',
			VIEW_PROFILE : 'shop/listMyStores/index.do',
			MODIFY_MY_PROFILE : "user/showProfile.do",
			MODIFY_SHOP_PROFILE : 'shop/{shopNo}/modifyShopProfile/index.do'.replace('{shopNo}', _storeId),
			ORDER_HISTORY : 'shop/{shopNo}/orderHistory.do'.replace('{shopNo}', _storeId),
			MANAGE_PRODUCT : 'product/{shopNo}/list/index.do'.replace('{shopNo}', _storeId),
			ADD_PRODUCT : 'product/{shopNo}/add/index.do'.replace('{shopNo}', _storeId),
			MANAGE_ADS : 'shop/{shopNo}/manageAd/index.do'.replace('{shopNo}', _storeId),
			DASHBOARD : 'shop/{shopNo}/dashboard/index.do'.replace('{shopNo}', _storeId),
			APPLY_ADS: 'shop/{shopNo}/applyAds/index.do'.replace('{shopNo}', _storeId),
			APPLY_COMMODITY_ADS:'product/{shopNo}/applyAds/index.do'.replace('{shopNo}', _storeId)
		}
	};
	new ShopManageHomepage(config);
});