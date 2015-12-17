$(function(){
	var config = {
		URL : {
			SHOP_OWNER_HOMEPAGE : 'shop/index.do',
			VIEW_PROFILE : 'shop/listMyStores/index.do',
			MODIFY_MY_PROFILE : "user/showProfile.do",
			MODIFY_SHOP_PROFILE : 'shop/{storeId}/modifyShopProfile/index.do'.replace('{storeId}', _storeId),
			ORDER_HISTORY : 'shop/{storeId}/orderHistory.do'.replace('{storeId}', _storeId),
			MANAGE_PRODUCT : 'product/{storeId}/list/index.do'.replace('{storeId}', _storeId),
			ADD_PRODUCT : 'product/{storeId}/add/index.do'.replace('{storeId}', _storeId),
			MANAGE_ADS : 'shop/{storeId}/manageAd/index.do'.replace('{storeId}', _storeId),
			DASHBOARD : 'shop/{storeId}/dashboard/index.do'.replace('{storeId}', _storeId)
		}
	};
	new ShopManageHomepage(config);
});