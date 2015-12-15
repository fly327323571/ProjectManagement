$(function(){
	var config = {
		URL : {
			SHOP_OWNER_HOMEPAGE : 'store/index.do',
			VIEW_PROFILE : 'store/listMyStores/index.do',
			MODIFY_MY_PROFILE : "user/showProfile.do",
			MODIFY_SHOP_PROFILE : 'store/{storeId}/modifyShopProfile/index.do'.replace('{storeId}', _storeId),
			ORDER_HISTORY : 'store/{storeId}/orderHistory.do'.replace('{storeId}', _storeId),
			MANAGE_PRODUCT : 'product/{storeId}/list/index.do'.replace('{storeId}', _storeId),
			ADD_PRODUCT : 'product/{storeId}/add/index.do'.replace('{storeId}', _storeId),
			MANAGE_ADS : 'store/{storeId}/manageAd/index.do'.replace('{storeId}', _storeId),
			DASHBOARD : 'store/{storeId}/dashboard/index.do'.replace('{storeId}', _storeId)
		}
	};
	new ShopManageHomepage(config);
});